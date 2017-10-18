package me.aluceps.sandbox.di;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.File;
import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.aluceps.sandbox.BuildConfig;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class HttpClientModule {

    static final String CACHE_FILE_NAME = "okhttp.cache";

    static final long MAX_CACHE_SIZE = 5 * 1024 * 1024;

    @Provides
    @Singleton
    public OkHttpClient provideHttpClient(Context context) {
        File cacheDir = new File(context.getCacheDir(), CACHE_FILE_NAME);
        Cache cache = new Cache(cacheDir, MAX_CACHE_SIZE);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(createInterceptor());

        if (BuildConfig.DEBUG) {
            client.addNetworkInterceptor(new StethoInterceptor());
            client.addNetworkInterceptor(new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY));
        }

        return client.build();
    }

    private static Interceptor createInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder request = chain.request().newBuilder();
                return chain.proceed(request.build());
            }
        };
    }
}
