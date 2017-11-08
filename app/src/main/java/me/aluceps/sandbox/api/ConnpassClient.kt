package me.aluceps.sandbox.api

import io.reactivex.Single
import me.aluceps.sandbox.api.service.ConnpassService
import me.aluceps.sandbox.model.ConnpassEvent
import me.aluceps.sandbox.model.RequestParams
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnpassClient @Inject
constructor(private val service: ConnpassService) {

    fun events(params: RequestParams): Single<ConnpassEvent> =
            service.events(params.offset, params.limit)
}
