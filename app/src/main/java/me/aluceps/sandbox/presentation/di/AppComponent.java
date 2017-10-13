package me.aluceps.sandbox.presentation.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import me.aluceps.sandbox.presentation.BaseActivity;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(BaseActivity __);

    Context context();
}
