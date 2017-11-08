package me.aluceps.sandbox.view

interface BasePresenter<T> {
    fun setView(view: T)
}
