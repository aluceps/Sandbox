package me.aluceps.sandbox.presentation.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.aluceps.sandbox.MyApplication;

@Module
public class AppModule {

    private final MyApplication application;

    public AppModule(MyApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideAppContext() {
        return this.application;
    }
}
