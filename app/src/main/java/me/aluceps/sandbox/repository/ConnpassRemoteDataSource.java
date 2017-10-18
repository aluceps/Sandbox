package me.aluceps.sandbox.repository;

import io.reactivex.Single;
import me.aluceps.sandbox.model.ConnpassEvent;

public interface ConnpassRemoteDataSource {

    Single<ConnpassEvent> events();
}
