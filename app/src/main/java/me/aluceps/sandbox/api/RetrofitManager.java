package me.aluceps.sandbox.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Single;
import me.aluceps.sandbox.model.ConnpassEvent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private OkHttpClient okHttpClient;

    private Retrofit retrofit;

    private Gson gson;

    public RetrofitManager() {
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        gson = new GsonBuilder()
                .serializeNulls()
                .create();

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(ConnpassApi.END_POINT)
                .build();
    }

    public Single<ConnpassEvent> event(long id) {
        return retrofit.create(ConnpassApi.class).event(id);
    }
}
