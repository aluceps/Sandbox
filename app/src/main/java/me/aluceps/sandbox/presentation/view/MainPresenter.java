package me.aluceps.sandbox.presentation.view;

public class MainPresenter implements MainContruct.Presenter<MainContruct.View> {

    private MainContruct.View view;

    private int counter = 0;

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
