package com.turman.framework.modules;

import android.app.Application;

import com.turman.framework.TurmanApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dqf on 2016/4/13.
 */
@Module
public class ApplicationModule {
    TurmanApplication mApplication;

    public ApplicationModule(TurmanApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public Application providesApplication(){
        return mApplication;
    }
}
