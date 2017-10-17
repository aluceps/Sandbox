package me.aluceps.sandbox.view.main;

import java.util.List;

import me.aluceps.sandbox.model.ConnpassEvent;
import me.aluceps.sandbox.view.BasePresenter;
import me.aluceps.sandbox.view.BaseView;

public class MainContract {
    interface View extends BaseView {

        void initializeRecyclerView();

        MainAdapter getAdapter();

        void setEvents(List<ConnpassEvent.Event> events);
    }

    interface Presenter<T extends BaseView> extends BasePresenter<T> {

        void load();
    }
}
