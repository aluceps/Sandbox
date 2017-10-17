package me.aluceps.sandbox.view.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import me.aluceps.sandbox.R;
import me.aluceps.sandbox.databinding.ActivityMainBinding;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        replaceFragment(binding.container.getId(), MainFragment.newInstance());
    }

    private void replaceFragment(int layoutId, Fragment fragment) {
        Timber.d("replaceFragment");
        getSupportFragmentManager().beginTransaction()
                .replace(layoutId, fragment, fragment.getClass().getSimpleName())
                .commit();
    }
}
