package me.aluceps.sandbox.view.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import me.aluceps.sandbox.R;
import me.aluceps.sandbox.databinding.FragmentMainBinding;
import me.aluceps.sandbox.model.ConnpassEvent;
import me.aluceps.sandbox.view.BaseFragment;

public class MainFragment extends BaseFragment implements MainContract.View {

    private FragmentMainBinding binding;

    @Inject
    MainPresenter presenter;

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
        presenter.load(false);
    }

    @Override
    public void onDestroy() {
        presenter.destroy();
        super.onDestroy();
    }

    @Override
    public void initializePresenter() {
        getComponent().inject(this);
        presenter.setView(this);
    }

    @Override
    public void initializeRecyclerView() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(getAdapter());
        binding.swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.load(true);
            }
        });
    }

    @Override
    public MainAdapter getAdapter() {
        adapter = new MainAdapter();
        return adapter;
    }

    @Override
    public void setEvents(List<ConnpassEvent.Event> events) {
        adapter.set(events);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void clear() {
        adapter.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressBar(boolean isRefresh) {
        if (!isRefresh) {
            binding.progressbar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgressBar(boolean isRefresh) {
        if (isRefresh) {
            binding.swiperefresh.setRefreshing(false);
        } else {
            binding.progressbar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean checkConnectionState() {
        return super.checkConnectionState();
    }

    @Override
    public void connectedBehavior() {
        Snackbar.make(binding.getRoot(), "通信可能です", BaseTransientBottomBar.LENGTH_SHORT).show();
    }

    @Override
    public void disconnecteBehavior() {
        Snackbar.make(binding.getRoot(), "通信不可です", BaseTransientBottomBar.LENGTH_SHORT).show();
        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.network_error_title)
                .setMessage(R.string.network_error_message)
                .setPositiveButton(R.string.network_error_positive, null)
                .show();
    }
}
