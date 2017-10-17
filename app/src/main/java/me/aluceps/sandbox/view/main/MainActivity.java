package me.aluceps.sandbox.view.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import me.aluceps.sandbox.R;
import me.aluceps.sandbox.databinding.ActivityMainBinding;
import me.aluceps.sandbox.view.BaseActivity;
import timber.log.Timber;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        Timber.d("onCreate");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        replaceFragment(MainFragment.newInstance(), binding.container.getId());
    }
}
