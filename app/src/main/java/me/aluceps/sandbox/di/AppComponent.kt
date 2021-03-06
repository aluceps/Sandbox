package me.aluceps.sandbox.di

import dagger.Component
import me.aluceps.sandbox.MyApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (HttpClientModule::class)])
interface AppComponent {

    fun plus(module: ActivityModule): ActivityComponent

    fun inject(application: MyApplication)
}
