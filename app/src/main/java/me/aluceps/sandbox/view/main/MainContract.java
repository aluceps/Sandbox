package me.aluceps.sandbox.view.main;

import me.aluceps.sandbox.model.ConnpassEvent;
import me.aluceps.sandbox.view.BasePresenter;
import me.aluceps.sandbox.view.BaseView;

public class MainContract {
    interface View extends BaseView {

        void setEvent(ConnpassEvent.Event event);

        void clear();
    }

    interface Presenter<T extends BaseView> extends BasePresenter<T> {

        void load();
    }
}
