package me.aluceps.sandbox.view.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.aluceps.sandbox.R;
import me.aluceps.sandbox.model.ConnpassEvent;

public class MainAdapter extends RecyclerView.Adapter<MainViewModel> {

    private List<ConnpassEvent.Event> events;

    public MainAdapter() {
        events = new ArrayList<>();
    }

    @Override
    public MainViewModel onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewModel(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_event, parent, false));
    }

    @Override
    public void onBindViewHolder(MainViewModel holder, int position) {
        holder.set(events.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public void set(List<ConnpassEvent.Event> events) {
        for (ConnpassEvent.Event e : events) {
            this.events.add(e);
        }
    }

    public void clear() {
        events.clear();
    }
}
