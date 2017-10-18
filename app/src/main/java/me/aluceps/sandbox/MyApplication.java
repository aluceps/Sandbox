package me.aluceps.sandbox;

import android.app.Application;
import android.support.annotation.NonNull;

import me.aluceps.sandbox.di.AppComponent;
import me.aluceps.sandbox.di.AppModule;
import me.aluceps.sandbox.di.DaggerAppComponent;
import timber.log.Timber;

public class MyApplication extends Application {

    AppComponent appComponent;

    @NonNull
    public AppComponent getComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
        initializeTimber();
    }

    private void initializeInjector() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }

    private void initializeTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
