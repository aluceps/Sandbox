package me.aluceps.sandbox.presentation.di;

import dagger.Subcomponent;
import me.aluceps.sandbox.presentation.BaseActivity;
import me.aluceps.sandbox.presentation.di.scope.ActivityScope;

@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(BaseActivity __);

    FragmentComponent plus(FragmentModule __);
}
