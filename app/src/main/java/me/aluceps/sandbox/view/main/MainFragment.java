package me.aluceps.sandbox.view.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
        presenter.load();
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
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (!presenter.isLoading() && isLastPosition(manager)) {
                    presenter.load();
                }
            }
        });
        binding.recyclerView.setAdapter(getAdapter());
        binding.swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (presenter.isLoading()) {
                    binding.swiperefresh.setRefreshing(false);
                } else {
                    presenter.refresh();
                }
            }
        });
    }

    private boolean isLastPosition(LinearLayoutManager manager) {
        return manager.findLastVisibleItemPosition() >= manager.getItemCount() - 1;
    }

    private void notifyDataSetChanged() {
        binding.recyclerView.post(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
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
        if (events.size() > 0) {
            adapter.set(events);
        } else {
            adapter.clear();
        }
        notifyDataSetChanged();
    }

    @Override
    public void clear() {
        adapter.clear();
        notifyDataSetChanged();
    }

    @Override
    public void showProgressBar() {
        adapter.setLoading();
        notifyDataSetChanged();
    }

    @Override
    public void hideProgressBar() {
        binding.swiperefresh.setRefreshing(false);
        adapter.removeLoading();
        notifyDataSetChanged();
    }

    @Override
    public boolean checkConnectionState() {
        return super.checkConnectionState();
    }

    @Override
    public void connectedBehavior() {
    }

    @Override
    public void disconnecteBehavior() {
        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.network_error_title)
                .setMessage(R.string.network_error_message)
                .setPositiveButton(R.string.network_error_positive, null)
                .show();
    }
}
