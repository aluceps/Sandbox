package me.aluceps.sandbox.repository;

import io.reactivex.Single;
import me.aluceps.sandbox.model.ConnpassEvent;
import me.aluceps.sandbox.model.RequestParams;

public interface ConnpassRemoteDataSource {

    Single<ConnpassEvent> events(RequestParams params);
}
