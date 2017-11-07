package me.aluceps.sandbox

import android.app.Application

import com.facebook.stetho.Stetho

import me.aluceps.sandbox.di.AppComponent
import me.aluceps.sandbox.di.AppModule
import me.aluceps.sandbox.di.DaggerAppComponent
import timber.log.Timber

class MyApplication : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        initializeInjector()
        initializeDebugUtilities()
    }

    private fun initializeInjector() {
        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        component.inject(this)
    }

    private fun initializeDebugUtilities() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
        }
    }
}
