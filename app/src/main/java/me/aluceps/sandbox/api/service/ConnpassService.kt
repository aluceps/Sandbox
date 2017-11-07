package me.aluceps.sandbox.api.service

import io.reactivex.Single
import me.aluceps.sandbox.model.ConnpassEvent
import retrofit2.http.GET
import retrofit2.http.Query

interface ConnpassService {

    @GET("event/")
    fun events(
            @Query("start") start: Int,
            @Query("count") count: Int
    ): Single<ConnpassEvent>
}
