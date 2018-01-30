package com.bonarmada.hc_activity.di.module;

import com.bonarmada.hc_activity.data.api.WeatherDataRemote;
import com.bonarmada.hc_activity.di.scope.AppScope;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by bonbonme on 1/30/2018.
 */

@Module(includes = NetworkModule.class)
public class RemoteModule {

    @AppScope
    @Provides
    WeatherDataRemote provideWeatherDataRemote(Retrofit retrofit){
        return retrofit.create(WeatherDataRemote.class);
    }
}
