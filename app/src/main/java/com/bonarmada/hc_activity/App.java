package com.bonarmada.hc_activity;

import android.app.Application;

import com.bonarmada.hc_activity.di.component.AppComponent;
import com.bonarmada.hc_activity.di.component.DaggerAppComponent;
import com.bonarmada.hc_activity.di.module.ContextModule;
import com.bonarmada.hc_activity.di.module.NetworkModule;

import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by bonbonme on 1/30/2018.
 */

public class App extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        Timber.plant(new Timber.DebugTree());
        component = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .networkModule(new NetworkModule(Constants.BASE_URL))
                .build();
    }

    public AppComponent getComponent() {
        return component;
    }
}

