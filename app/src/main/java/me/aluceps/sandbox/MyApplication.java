package me.aluceps.sandbox;

import android.app.Application;

import timber.log.Timber;

public class MyApplication extends Application {

    private static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        initializeTimber();
    }

    private void initializeTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static Application getApplication() {
        return application;
    }
}
