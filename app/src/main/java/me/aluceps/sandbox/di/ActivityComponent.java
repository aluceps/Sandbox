package me.aluceps.sandbox.di;

import dagger.Subcomponent;
import me.aluceps.sandbox.di.scope.ActivityScope;
import me.aluceps.sandbox.view.main.MainActivity;

@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity __);

    FragmentComponent plus(FragmentModule __);
}
