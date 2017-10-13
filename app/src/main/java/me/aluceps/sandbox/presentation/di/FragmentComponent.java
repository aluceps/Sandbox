package me.aluceps.sandbox.presentation.di;

import dagger.Subcomponent;
import me.aluceps.sandbox.presentation.di.scope.FragmentScope;
import me.aluceps.sandbox.presentation.view.MainFragment;

@FragmentScope
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(MainFragment __);
}
