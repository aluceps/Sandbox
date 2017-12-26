package me.aluceps.sandbox.di

import dagger.Subcomponent
import me.aluceps.sandbox.di.scope.FragmentScope
import me.aluceps.sandbox.view.main.MainFragment

@FragmentScope
@Subcomponent(modules = [(FragmentModule::class)])
interface FragmentComponent {

    fun inject(fragment: MainFragment)
}
