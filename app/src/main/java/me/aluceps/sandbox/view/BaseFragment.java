package me.aluceps.sandbox.view;

import android.content.Context;
import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initializePresenter();
    }

    public abstract void initializePresenter();
}
