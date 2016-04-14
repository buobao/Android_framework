package com.turman.framework;

import android.app.Application;

import com.turman.framework.components.ApplicationComponent;
import com.turman.framework.components.DaggerApplicationComponent;
import com.turman.framework.modules.ApplicationModule;
import com.turman.framework.net.ApiService;

import javax.inject.Inject;

/**
 * Created by dqf on 2016/4/13.
 */
public class TurmanApplication extends Application {

    private ApplicationComponent component;

    @Inject
    ApiService apiService;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        component.inject(this);
    }

    public ApiService getApiService(){
        return apiService;
    }

}
