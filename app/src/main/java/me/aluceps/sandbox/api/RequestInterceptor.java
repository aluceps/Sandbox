package me.aluceps.sandbox.api;

import android.content.Context;
import android.net.ConnectivityManager;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class RequestInterceptor implements Interceptor {

    final Context context;

    final ConnectivityManager connectivityManager;

    @Inject
    public RequestInterceptor(Context context, ConnectivityManager connectivityManager) {
        this.context = context;
        this.connectivityManager = connectivityManager;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder request = chain.request().newBuilder();
        return chain.proceed(request.build());
    }
}
