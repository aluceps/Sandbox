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

    @Override
    public void setView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void load() {
        RetrofitManager manager = new RetrofitManager();
        manager.event(65989)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ConnpassEvent>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Timber.d("onSubscribe");
                        view.clear();
                    }

                    @Override
                    public void onSuccess(@NonNull ConnpassEvent connpassEvent) {
                        Timber.d("onSuccess");
                        ConnpassEvent.Event e = connpassEvent.getEvents().get(0);
                        view.setEvent(e);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.d("onError");
                    }
                });
    }
}
