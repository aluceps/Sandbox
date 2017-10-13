package me.aluceps.sandbox.presentation.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import me.aluceps.sandbox.R;
import me.aluceps.sandbox.databinding.FragmentMainBinding;
import me.aluceps.sandbox.presentation.BaseFragment;
import timber.log.Timber;

public class MainFragment extends BaseFragment implements MainContruct.View {

    private FragmentMainBinding binding;

    @Inject
    MainPresenter presenter;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getComponent().inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.show();
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.increment();
            }
        });
    }

    @Override
    protected void initPresenter() {
        getComponent().inject(this);
        presenter.setView(this);
    }

    @Override
    public void increment(int value) {
        Timber.d("increment");
        binding.text.setText("count is: " + value);
    }
}
