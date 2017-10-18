package me.aluceps.sandbox.view;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import me.aluceps.sandbox.MyApplication;
import me.aluceps.sandbox.di.ActivityComponent;
import me.aluceps.sandbox.di.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;

    @NonNull
    public ActivityComponent getComponent() {
        if (activityComponent == null) {
            MyApplication myApplication = (MyApplication) getApplication();
            activityComponent = myApplication.getComponent().plus(new ActivityModule(this));
        }
        return activityComponent;
    }

    public final void replaceFragment(@NonNull Fragment fragment, @IdRes @LayoutRes int layoutResId) {
        getSupportFragmentManager().beginTransaction()
                .replace(layoutResId, fragment, fragment.getClass().getSimpleName())
                .commit();
    }
}
