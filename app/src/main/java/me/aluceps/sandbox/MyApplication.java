package me.aluceps.sandbox;

import android.app.Application;

import me.aluceps.sandbox.presentation.di.AppComponent;
import me.aluceps.sandbox.presentation.di.AppModule;
import me.aluceps.sandbox.presentation.di.DaggerAppComponent;
import timber.log.Timber;

public class MyApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
        initializeDebug();
    }

    private void initializeInjector() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }

    private void initializeDebug() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public AppComponent getComponent() {
        return appComponent;
    }
}
