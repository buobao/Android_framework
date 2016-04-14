package com.turman.framework.components;

import com.turman.framework.TurmanApplication;
import com.turman.framework.modules.ApiModule;
import com.turman.framework.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by dqf on 2016/4/13.
 */
@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface ApplicationComponent {
    void inject(TurmanApplication application);
}
