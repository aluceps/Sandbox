package me.aluceps.sandbox.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import me.aluceps.sandbox.api.ConnpassClient;
import me.aluceps.sandbox.model.ConnpassEvent;

@Singleton
public class ConnpassRepository implements ConnpassRemoteDataSource {

    private final ConnpassClient client;

    @Inject
    public ConnpassRepository(ConnpassClient client) {
        this.client = client;
    }

    @Override
    public Single<ConnpassEvent> events() {
        return client.events();
    }
}
