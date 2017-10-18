package me.aluceps.sandbox.view.main;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.aluceps.sandbox.model.ConnpassEvent;
import me.aluceps.sandbox.model.RequestParams;
import me.aluceps.sandbox.repository.ConnpassRepository;

public class MainPresenter implements MainContract.Presenter<MainContract.View> {

    private MainContract.View view;

    private ConnpassRepository repository;

    private CompositeDisposable disposable;

    private RequestParams requestParams;

    private boolean loading;

    @Inject
    public MainPresenter(@NonNull ConnpassRepository repository,
                         @NonNull CompositeDisposable disposable) {
        this.repository = repository;
        this.disposable = disposable;

        loading = false;
        requestParams = new RequestParams();
        requestParams.setLimit(RequestParams.LIMIT);
        requestParams.setOffset(0);
        requestParams.setTotal(1);
    }

    @Override
    public void setView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void load() {
        if (!view.checkConnectionState()) {
            view.hideProgressBar();
            return;
        }
        if (!loading && requestParams.getTotal() > requestParams.getOffset()) {
            view.showProgressBar();
            repository.events(requestParams)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<ConnpassEvent>() {
                        @Override
                        public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                            disposable.add(d);
                            loading = true;
                        }

                        @Override
                        public void onSuccess(@io.reactivex.annotations.NonNull ConnpassEvent connpassEvent) {
                            view.hideProgressBar();
                            view.setEvents(connpassEvent.getEvents());
                            requestParams.setOffset(connpassEvent.getResultsStart() + connpassEvent.getResultsReturned());
                            requestParams.setTotal(connpassEvent.getResultsAvailable());
                            loading = false;
                        }

                        @Override
                        public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                            loading = false;
                        }
                    });
        }
    }

    @Override
    public boolean isLoading() {
        return loading;
    }

    @Override
    public void refresh() {
        view.clear();
        requestParams.setOffset(0);
        requestParams.setTotal(1);
        load();
    }

    @Override
    public void destroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
