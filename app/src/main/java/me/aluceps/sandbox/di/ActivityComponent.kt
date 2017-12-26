package me.aluceps.sandbox.di

import dagger.Subcomponent
import me.aluceps.sandbox.di.scope.ActivityScope
import me.aluceps.sandbox.view.main.MainActivity

@ActivityScope
@Subcomponent(modules = [(ActivityModule::class)])
interface ActivityComponent {

    fun plus(module: FragmentModule): FragmentComponent

    fun inject(activity: MainActivity)
}
