package me.aluceps.sandbox.di

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import me.aluceps.sandbox.BuildConfig
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import javax.inject.Singleton

@Module
class HttpClientModule {

    @Provides
    @Singleton
    fun provideHttpClient(context: Context): OkHttpClient {
        val cacheDir = File(context.cacheDir, CACHE_FILE_NAME)
        val cache = Cache(cacheDir, MAX_CACHE_SIZE)

        val client = OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(createInterceptor())

        if (BuildConfig.DEBUG) {
            client.addNetworkInterceptor(StethoInterceptor())
            client.addNetworkInterceptor(HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY))
        }

        return client.build()
    }

    companion object {

        private val CACHE_FILE_NAME = "okhttp.cache"

        private val MAX_CACHE_SIZE = (5 * 1024 * 1024).toLong()

        private fun createInterceptor(): Interceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
            chain.proceed(request.build())
        }
    }
}
