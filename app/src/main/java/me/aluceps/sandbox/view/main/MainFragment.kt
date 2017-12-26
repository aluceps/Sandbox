package me.aluceps.sandbox.view.main

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.aluceps.sandbox.R
import me.aluceps.sandbox.databinding.FragmentMainBinding
import me.aluceps.sandbox.model.ConnpassEvent
import me.aluceps.sandbox.view.BaseFragment
import javax.inject.Inject

class MainFragment : BaseFragment(), MainContract.View {

    private lateinit var binding: FragmentMainBinding

    @Inject
    lateinit var presenter: MainPresenter

    private val adapter by lazy {
        MainAdapter()
    }

    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecyclerView()
        presenter.load()
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }

    override fun initializePresenter() {
        getComponent().inject(this)
        presenter.setView(this)
    }

    override fun initializeRecyclerView() {
        binding.run {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val manager = recyclerView!!.layoutManager as LinearLayoutManager
                    if (!presenter.isLoading() && isLastPosition(manager)) {
                        presenter.load()
                    }
                }
            })
            swiperefresh.setOnRefreshListener {
                if (presenter.isLoading()) {
                    swiperefresh.isRefreshing = false
                } else {
                    presenter.refresh()
                }
            }
        }
    }

    private fun isLastPosition(manager: LinearLayoutManager): Boolean {
        return manager.findLastVisibleItemPosition() >= manager.itemCount - 1
    }

    private fun notifyDataSetChanged() {
        binding.recyclerView.post { adapter.notifyDataSetChanged() }
    }

    override fun setData(data: List<ConnpassEvent.Event>) {
        if (data.isNotEmpty()) {
            adapter.set(data)
        } else {
            adapter.clear()
        }
        notifyDataSetChanged()
    }

    override fun clear() {
        adapter.clear()
        notifyDataSetChanged()
    }

    override fun showProgressBar() {
        adapter.setLoading()
        notifyDataSetChanged()
    }

    override fun hideProgressBar() {
        binding.swiperefresh.isRefreshing = false
        adapter.removeLoading()
        notifyDataSetChanged()
    }

    override fun checkConnectionState(): Boolean {
        return super.checkConnectionState()
    }

    override fun connectedBehavior() {}

    override fun disconnecteBehavior() {
        activity?.let {
            AlertDialog.Builder(it)
                    .setTitle(R.string.network_error_title)
                    .setMessage(R.string.network_error_message)
                    .setPositiveButton(R.string.network_error_positive, null)
                    .show()
        }
    }
}
