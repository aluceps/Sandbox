package me.aluceps.sandbox.presentation.di;

import dagger.Module;
import me.aluceps.sandbox.MyApplication;

@Module
public class AppModule {

    private final MyApplication application;

    public AppModule(MyApplication application) {
        this.application = application;
    }
}
