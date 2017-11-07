package me.aluceps.sandbox.di

import dagger.Component
import me.aluceps.sandbox.MyApplication
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, HttpClientModule::class))
interface AppComponent {

    operator fun plus(module: ActivityModule): ActivityComponent

    fun inject(application: MyApplication)
}
