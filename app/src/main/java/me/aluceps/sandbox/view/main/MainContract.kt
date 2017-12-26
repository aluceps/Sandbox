package me.aluceps.sandbox.view.main

import me.aluceps.sandbox.model.ConnpassEvent
import me.aluceps.sandbox.view.BasePresenter
import me.aluceps.sandbox.view.BaseView

class MainContract {
    interface View : BaseView {

        fun initializeRecyclerView()

        fun setData(data: List<ConnpassEvent.Event>)

        fun clear()

        fun showProgressBar()

        fun hideProgressBar()
    }

    interface Presenter<T : BaseView> : BasePresenter<T> {

        fun isLoading(): Boolean

        fun load()

        fun refresh()

        fun destroy()
    }
}
