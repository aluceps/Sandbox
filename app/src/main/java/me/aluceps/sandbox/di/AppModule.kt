package me.aluceps.sandbox.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import me.aluceps.sandbox.BuildConfig
import me.aluceps.sandbox.api.service.ConnpassService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(application: Application) {

    private val context: Context = application

    @Provides
    fun provideContext(): Context = context

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @Singleton
    fun provideConnectivityManager(): ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Provides
    @Singleton
    fun provideConnpassService(client: OkHttpClient): ConnpassService = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(createGson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .baseUrl(BuildConfig.API_ROOT)
            .build()
            .create(ConnpassService::class.java)

    private fun createGson(): Gson = GsonBuilder().create()
}
