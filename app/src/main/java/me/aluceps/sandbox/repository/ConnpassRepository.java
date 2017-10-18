package me.aluceps.sandbox.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import me.aluceps.sandbox.api.service.ConnpassService;
import me.aluceps.sandbox.model.ConnpassEvent;

@Singleton
public class ConnpassRepository implements ConnpassRemoteDataSource {

    private ConnpassService service;

    @Inject
    public ConnpassRepository(ConnpassService service) {
        this.service = service;
    }

    @Override
    public Single<ConnpassEvent> events() {
        return service.events();
    }
}
