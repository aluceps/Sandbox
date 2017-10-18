package me.aluceps.sandbox.api;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import me.aluceps.sandbox.api.service.ConnpassService;
import me.aluceps.sandbox.model.ConnpassEvent;

@Singleton
public class ConnpassClient {

    private final ConnpassService connpassService;

    @Inject
    public ConnpassClient(ConnpassService connpassService) {
        this.connpassService = connpassService;
    }

    public Single<ConnpassEvent> events() {
        return connpassService.events();
    }
}
