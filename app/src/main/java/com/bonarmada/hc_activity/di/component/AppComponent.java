package com.bonarmada.hc_activity.di.component;


import com.bonarmada.hc_activity.di.module.ViewModelModule;
import com.bonarmada.hc_activity.ui.detail.DetailFragment;
import com.bonarmada.hc_activity.ui.main.MainActivity;
import com.bonarmada.hc_activity.di.module.DaoModule;
import com.bonarmada.hc_activity.di.module.RemoteModule;
import com.bonarmada.hc_activity.di.scope.AppScope;
import com.bonarmada.hc_activity.ui.main.MainFragment;

import dagger.Component;

/**
 * Created by Vaughn on 8/18/17.
 */

@AppScope
@Component(modules = {RemoteModule.class, DaoModule.class, ViewModelModule.class})

public interface AppComponent {
    void inject(MainActivity activity);
    void inject(MainFragment activity);
    void inject(DetailFragment activity);
}
