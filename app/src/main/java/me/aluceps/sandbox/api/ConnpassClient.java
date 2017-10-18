package me.aluceps.sandbox.api;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import me.aluceps.sandbox.api.service.ConnpassService;
import me.aluceps.sandbox.model.ConnpassEvent;
import me.aluceps.sandbox.model.RequestParams;

@Singleton
public class ConnpassClient {

    private final ConnpassService connpassService;

    @Inject
    public ConnpassClient(ConnpassService connpassService) {
        this.connpassService = connpassService;
    }

    public Single<ConnpassEvent> events(RequestParams params) {
        return connpassService.events(params.getOffset(), params.getLimit());
    }
}
