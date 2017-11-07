package me.aluceps.sandbox.repository

import io.reactivex.Single
import me.aluceps.sandbox.api.ConnpassClient
import me.aluceps.sandbox.model.ConnpassEvent
import me.aluceps.sandbox.model.RequestParams
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnpassRepository @Inject
constructor(private val client: ConnpassClient) : ConnpassRemoteDataSource {

    override fun events(params: RequestParams): Single<ConnpassEvent> = client.events(params)
}
