package me.aluceps.sandbox.view

import android.content.Context
import android.net.ConnectivityManager
import android.support.v4.app.Fragment
import me.aluceps.sandbox.di.FragmentComponent
import me.aluceps.sandbox.di.FragmentModule
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    private var fragmentComponent: FragmentComponent? = null

    @Inject
    lateinit var connectivityManager: ConnectivityManager

    protected fun getComponent(): FragmentComponent {
        if (fragmentComponent != null) {
            return fragmentComponent as FragmentComponent
        }

        val activity = activity
        if (activity is BaseActivity) {
            fragmentComponent = activity.getComponent().plus(FragmentModule(this))
            return fragmentComponent as FragmentComponent
        } else {
            throw IllegalStateException("The activity of this fragment is not an instance of BaseActivity")
        }
    }

    private val isConnected: Boolean
        get() {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnectedOrConnecting
        }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        initializePresenter()
    }

    abstract fun initializePresenter()

    protected open fun checkConnectionState(): Boolean = if (isConnected) {
        connectedBehavior()
        true
    } else {
        disconnecteBehavior()
        false
    }

    abstract fun connectedBehavior()

    abstract fun disconnecteBehavior()
}
