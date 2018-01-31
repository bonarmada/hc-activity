package com.bonarmada.hc_activity.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.bonarmada.hc_activity.data.api.WeatherDataRemote;
import com.bonarmada.hc_activity.di.ViewModelKey;
import com.bonarmada.hc_activity.di.scope.AppScope;
import com.bonarmada.hc_activity.ui.detail.DetailViewModel;
import com.bonarmada.hc_activity.ui.main.MainViewModel;
import com.bonarmada.hc_activity.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import retrofit2.Retrofit;

/**
 * Created by bonbonme on 1/30/2018.
 */

@Module()
public abstract class ViewModelModule {

    @AppScope
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);

    @AppScope
    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel.class)
    abstract ViewModel bindDetailViewModel(DetailViewModel detailViewModel);


    // Factory
    @AppScope
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
