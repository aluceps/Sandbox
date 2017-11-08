package me.aluceps.sandbox.view

import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

import me.aluceps.sandbox.MyApplication
import me.aluceps.sandbox.di.ActivityComponent
import me.aluceps.sandbox.di.ActivityModule

abstract class BaseActivity : AppCompatActivity() {

    private var activityComponent: ActivityComponent? = null

    @NonNull
    fun getComponent(): ActivityComponent {
        if (activityComponent == null) {
            val myApplication = application as MyApplication
            activityComponent = myApplication.component.plus(ActivityModule(this))
        }
        return activityComponent as ActivityComponent
    }

    fun replaceFragment(fragment: Fragment, @IdRes @LayoutRes layoutResId: Int) {
        supportFragmentManager.beginTransaction()
                .replace(layoutResId, fragment, fragment.javaClass.simpleName)
                .commit()
    }
}
