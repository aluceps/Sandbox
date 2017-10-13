package me.aluceps.sandbox.presentation;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import me.aluceps.sandbox.MyApplication;
import me.aluceps.sandbox.presentation.di.ActivityComponent;
import me.aluceps.sandbox.presentation.di.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected ActivityComponent getComponent() {
        if (activityComponent == null) {
            MyApplication myApplication = (MyApplication) getApplication();
            activityComponent = myApplication.getComponent().plus(new ActivityModule(this));
        }
        return activityComponent;
    }

    protected void replaceFragment(@IdRes @LayoutRes int containerId, @NonNull Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(containerId, fragment, fragment.getClass().getSimpleName())
                .commit();
    }
}
