package me.aluceps.sandbox.api.service;

import io.reactivex.Single;
import me.aluceps.sandbox.model.ConnpassEvent;
import retrofit2.http.GET;

public interface ConnpassService {

    @GET("event/")
    Single<ConnpassEvent> events();
}
