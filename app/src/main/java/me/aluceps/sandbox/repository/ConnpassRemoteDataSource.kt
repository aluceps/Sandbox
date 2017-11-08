package me.aluceps.sandbox.repository

import io.reactivex.Single
import me.aluceps.sandbox.model.ConnpassEvent
import me.aluceps.sandbox.model.RequestParams

interface ConnpassRemoteDataSource {

    fun events(params: RequestParams): Single<ConnpassEvent>
}
