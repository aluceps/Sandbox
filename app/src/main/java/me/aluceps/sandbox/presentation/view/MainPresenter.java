package me.aluceps.sandbox.presentation.view;

import javax.inject.Inject;

public class MainPresenter implements MainContruct.Presenter<MainContruct.View> {

    private MainContruct.View view;

    private int counter = 0;

    @Inject
    MainPresenter() {
    }

    @Override
    public void setView(MainContruct.View view) {
        this.view = view;
    }

    protected void show() {
        view.increment(counter);
    }

    protected void increment() {
        view.increment(++counter);
    }
}
