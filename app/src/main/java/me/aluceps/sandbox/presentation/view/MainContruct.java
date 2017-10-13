package me.aluceps.sandbox.presentation.view;

import me.aluceps.sandbox.presentation.BasePresenter;
import me.aluceps.sandbox.presentation.BaseView;

public class MainContruct {

    public interface View extends BaseView {

        void increment(int value);
    }

    public interface Presenter<T extends BaseView> extends BasePresenter<T> {
    }
}
