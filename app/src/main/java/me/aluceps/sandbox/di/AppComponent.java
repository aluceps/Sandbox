package me.aluceps.sandbox.di;

import javax.inject.Singleton;

import dagger.Component;
import me.aluceps.sandbox.MyApplication;

@Singleton
@Component(modules = {AppModule.class, HttpClientModule.class})
public interface AppComponent {

    void inject(MyApplication application);

    ActivityComponent plus(ActivityModule __);
}
