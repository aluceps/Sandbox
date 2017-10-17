package me.aluceps.sandbox.view.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import me.aluceps.sandbox.databinding.FragmentMainBinding;
import me.aluceps.sandbox.model.ConnpassEvent;
import me.aluceps.sandbox.view.BaseFragment;
import timber.log.Timber;

public class MainFragment extends BaseFragment implements MainContract.View {

    private FragmentMainBinding binding;

    private CompositeDisposable disposable = new CompositeDisposable();

    private MainPresenter presenter;

    private MainAdapter adapter;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeRecyclerView();
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.load();
            }
        });
    }

    @Override
    public void onDestroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        super.onDestroy();
    }

    @Override
    public void initializePresenter() {
        Timber.d("initializePresenter");
        presenter = new MainPresenter();
        presenter.setView(this);
    }

    @Override
    public void initializeRecyclerView() {
        Timber.d("initializeRecyclerView");
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(getAdapter());
    }

    @Override
    public MainAdapter getAdapter() {
        Timber.d("getAdapter");
        adapter = new MainAdapter();
        return adapter;
    }

    @Override
    public void setEvents(List<ConnpassEvent.Event> events) {
        Timber.d("setEvents");
        adapter.set(events);
        adapter.notifyDataSetChanged();
    }
}
