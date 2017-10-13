package me.aluceps.sandbox.presentation;

import android.support.annotation.NonNull;

public interface BasePresenter<T> {

    void setView(@NonNull T view);
}
