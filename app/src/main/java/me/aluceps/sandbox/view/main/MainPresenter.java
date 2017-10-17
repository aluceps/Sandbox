package me.aluceps.sandbox.view.main;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.aluceps.sandbox.api.RetrofitManager;
import me.aluceps.sandbox.model.ConnpassEvent;
import timber.log.Timber;

public class MainPresenter implements MainContract.Presenter<MainContract.View> {

    private MainContract.View view;

    private RetrofitManager retrofitManager;

    @Override
    public void setView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void load(final boolean isRefresh) {
        view.clear();
        view.showProgressBar(isRefresh);
        retrofitManager = new RetrofitManager();
        retrofitManager.events()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ConnpassEvent>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Timber.d("onSubscribe");
                    }

                    @Override
                    public void onSuccess(@NonNull ConnpassEvent connpassEvent) {
                        Timber.d("onSuccess");
                        view.hideProgressBar(isRefresh);
                        view.setEvents(connpassEvent.getEvents());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.d("onError");
                    }
                });
    }
}
