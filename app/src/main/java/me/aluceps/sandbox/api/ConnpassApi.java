package me.aluceps.sandbox.api;

import io.reactivex.Single;
import me.aluceps.sandbox.model.ConnpassEvent;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ConnpassApi {

    String END_POINT = "https://connpass.com/api/v1/";

    @GET("event/")
    Single<ConnpassEvent> events();

    @GET("event/")
    Single<ConnpassEvent> event(
            @Query("event_id") long eventId
    );
}
