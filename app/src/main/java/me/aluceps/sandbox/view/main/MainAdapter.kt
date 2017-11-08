package me.aluceps.sandbox.view.main

import android.support.v4.util.Pair
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import me.aluceps.sandbox.R
import me.aluceps.sandbox.model.ConnpassEvent
import java.util.*

class MainAdapter : RecyclerView.Adapter<MainViewModel>() {

    private val events: List<ConnpassEvent.Event>

    private val pairs: MutableList<Pair<Type, ConnpassEvent.Event>>

    enum class Type(val id: Int) {
        PROGRESS(0),
        DATA(1)
    }

    init {
        events = ArrayList()
        pairs = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewModel =
            when (viewType) {
                Type.PROGRESS.id -> MainViewModel(LayoutInflater.from(parent.context).inflate(R.layout.cell_progress, parent, false))
                Type.DATA.id -> MainViewModel(LayoutInflater.from(parent.context).inflate(R.layout.cell_event, parent, false))
                else -> MainViewModel(LayoutInflater.from(parent.context).inflate(R.layout.cell_event, parent, false))
            }

    override fun onBindViewHolder(holder: MainViewModel, position: Int) {
        holder.set(pairs[position].first, pairs[position].second)
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return pairs.size
    }

    override fun getItemViewType(position: Int): Int = when (pairs[position].first) {
        Type.PROGRESS -> Type.PROGRESS.id
        else -> Type.DATA.id
    }

    fun set(events: List<ConnpassEvent.Event>) {
        events.mapTo(pairs) { Pair(Type.DATA, it) }
    }

    fun clear() {
        pairs.clear()
    }

    fun setLoading() {
        pairs.add(Pair(Type.PROGRESS, ConnpassEvent.Event()))
    }

    fun removeLoading() {
        if (pairs.size > 0) {
            pairs.removeAt(pairs.size - 1)
        }
    }
}
