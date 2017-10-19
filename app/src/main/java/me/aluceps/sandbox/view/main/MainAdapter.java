package me.aluceps.sandbox.view.main;

import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.aluceps.sandbox.R;
import me.aluceps.sandbox.model.ConnpassEvent;

public class MainAdapter extends RecyclerView.Adapter<MainViewModel> {

    private static final int TYPE_PROGRESS = 0;

    private static final int TYPE_DATA = 1;

    private List<ConnpassEvent.Event> events;

    private List<Pair<Type, ConnpassEvent.Event>> pairs;

    enum Type {
        PROGRESS,
        DATA
    }

    public MainAdapter() {
        events = new ArrayList<>();
        pairs = new ArrayList<>();
    }

    @Override
    public MainViewModel onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_PROGRESS:
                return new MainViewModel(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_progress, parent, false));
            case TYPE_DATA:
            default:
                return new MainViewModel(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_event, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(MainViewModel holder, int position) {
        holder.set(pairs.get(position).first, pairs.get(position).second);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return pairs.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (pairs.get(position).first) {
            case PROGRESS:
                return TYPE_PROGRESS;
            case DATA:
            default:
                return TYPE_DATA;
        }
    }

    public void set(List<ConnpassEvent.Event> events) {
        for (ConnpassEvent.Event e : events) {
            pairs.add(Pair.create(Type.DATA, e));
        }
    }

    public void clear() {
        pairs.clear();
    }

    public void setLoading() {
        pairs.add(Pair.create(Type.PROGRESS, new ConnpassEvent.Event()));
    }

    public void removeLoading() {
        if (pairs.size() > 0) {
            pairs.remove(pairs.size() - 1);
        }
    }
}
