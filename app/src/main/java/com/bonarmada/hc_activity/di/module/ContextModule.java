package com.bonarmada.hc_activity.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by bonbonme on 1/30/2018.
 */

@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideContext() {
        return context;
    }
}
