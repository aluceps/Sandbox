package me.aluceps.sandbox.view;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import me.aluceps.sandbox.di.FragmentComponent;
import me.aluceps.sandbox.di.FragmentModule;

public abstract class BaseFragment extends Fragment {

    private FragmentComponent fragmentComponent;

    @Inject
    ConnectivityManager connectivityManager;

    @NonNull
    public FragmentComponent getComponent() {
        if (fragmentComponent != null) {
            return fragmentComponent;
        }

        Activity activity = getActivity();
        if (activity instanceof BaseActivity) {
            fragmentComponent = ((BaseActivity) activity).getComponent().plus(new FragmentModule(this));
            return fragmentComponent;
        } else {
            throw new IllegalStateException("The activity of this fragment is not an instance of BaseActivity");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initializePresenter();
    }

    public abstract void initializePresenter();

    protected boolean isConnected() {
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    protected boolean checkConnectionState() {
        if (isConnected()) {
            connectedBehavior();
            return true;
        } else {
            disconnecteBehavior();
            return false;
        }
    }

    public abstract void connectedBehavior();

    public abstract void disconnecteBehavior();
}
