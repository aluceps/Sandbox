package me.aluceps.sandbox.view.main

import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import me.aluceps.sandbox.model.ConnpassEvent
import me.aluceps.sandbox.model.RequestParams
import me.aluceps.sandbox.repository.ConnpassRepository
import javax.inject.Inject

class MainPresenter @Inject
constructor(private val repository: ConnpassRepository,
            private val disposable: CompositeDisposable) : MainContract.Presenter<MainContract.View> {

    private lateinit var view: MainContract.View

    private val requestParams: RequestParams = RequestParams()

    private var loading: Boolean = false

    init {
        requestParams.limit = RequestParams.LIMIT
        requestParams.offset = 0
        requestParams.total = 1
    }

    override fun setView(view: MainContract.View) {
        this.view = view
    }

    override fun load() {
        view.showProgressBar()
        if (!loading && requestParams.total > requestParams.offset) {
            repository.events(requestParams)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : SingleObserver<ConnpassEvent> {
                        override fun onSubscribe(@io.reactivex.annotations.NonNull d: Disposable) {
                            disposable.add(d)
                            loading = true
                        }

                        override fun onSuccess(@io.reactivex.annotations.NonNull connpassEvent: ConnpassEvent) {
                            view.hideProgressBar()
                            view.clear()
                            connpassEvent.events?.let {
                                view.setData(it)
                            }
                            requestParams.offset = connpassEvent.resultsStart + connpassEvent.resultsReturned
                            requestParams.total = connpassEvent.resultsAvailable
                            loading = false
                        }

                        override fun onError(@io.reactivex.annotations.NonNull e: Throwable) {
                            view.hideProgressBar()
                            loading = false
                        }
                    })
        }
    }

    override fun refresh() {
        view.clear()
        requestParams.offset = 0
        requestParams.total = 1
        load()
    }

    override fun isLoading(): Boolean = loading

    override fun destroy() {
        if (disposable != null && !disposable.isDisposed) {
            disposable.dispose()
        }
    }
}
