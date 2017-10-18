package me.aluceps.sandbox.view.main;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.aluceps.sandbox.model.ConnpassEvent;
import me.aluceps.sandbox.repository.ConnpassRepository;
import timber.log.Timber;

public class MainPresenter implements MainContract.Presenter<MainContract.View> {

    private MainContract.View view;

    private ConnpassRepository repository;

    private CompositeDisposable disposable;

    @Inject
    public MainPresenter(@NonNull ConnpassRepository repository,
                         @NonNull CompositeDisposable disposable) {
        this.repository = repository;
        this.disposable = disposable;
    }

    @Override
    public void setView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void load(final boolean isRefresh) {
        view.showProgressBar(isRefresh);
        repository.events()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ConnpassEvent>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        Timber.d("onSubscribe");
                        disposable.add(d);
                        view.clear();
                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull ConnpassEvent connpassEvent) {
                        Timber.d("onSuccess");
                        view.hideProgressBar(isRefresh);
                        view.setEvents(connpassEvent.getEvents());
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        Timber.d("onError");
                    }
                });
    }

    @Override
    public void destroy() {
        if (disposable != null && !disposable.isDisposed()) {
            Timber.d("dispose");
            disposable.dispose();
        }
    }
}
