package me.aluceps.sandbox.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import me.aluceps.sandbox.MyApplication;
import me.aluceps.sandbox.presentation.di.AppComponent;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getAppComponent().inject(this);
    }

    protected AppComponent getAppComponent() {
        return ((MyApplication) getApplication()).getAppComponent();
    }

    protected void replaceFragment(int containerId, Fragment fragment) {
        this.getSupportFragmentManager().beginTransaction()
                .replace(containerId, fragment)
                .commit();
    }
}
