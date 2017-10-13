package me.aluceps.sandbox.presentation.di;

import javax.inject.Singleton;

import dagger.Component;
import me.aluceps.sandbox.MyApplication;
import me.aluceps.sandbox.presentation.BaseActivity;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(MyApplication __);

    ActivityComponent plus(ActivityModule __);
}
