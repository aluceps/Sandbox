package me.aluceps.sandbox.presentation.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import me.aluceps.sandbox.R;
import me.aluceps.sandbox.databinding.ActivityMainBinding;
import me.aluceps.sandbox.presentation.BaseActivity;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        replaceFragment(binding.container.getId(), MainFragment.newInstance());
    }
}
