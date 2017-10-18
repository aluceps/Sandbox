package me.aluceps.sandbox.api.service;

import android.support.annotation.NonNull;

import io.reactivex.Single;
import me.aluceps.sandbox.model.ConnpassEvent;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ConnpassService {

    @GET("event/")
    Single<ConnpassEvent> events(
            @Query("start") @NonNull int start,
            @Query("count") @NonNull int count
    );
}
