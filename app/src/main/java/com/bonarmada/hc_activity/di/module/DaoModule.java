package com.bonarmada.hc_activity.di.module;

import com.bonarmada.hc_activity.data.dao.WeatherDataDao;
import com.bonarmada.hc_activity.data.vo.WeatherData;
import com.bonarmada.hc_activity.di.scope.AppScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by bonbonme on 1/30/2018.
 */

@Module
public class DaoModule {

    @AppScope
    @Provides
    WeatherDataDao provideWeatherDataDao(){
        return new WeatherDataDao();
    }
}
