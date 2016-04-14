package com.turman.framework.components;

import com.turman.framework.MainPresenterImpl;
import com.turman.framework.modules.ApiModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by dqf on 2016/4/13.
 */
@Singleton
@Component(modules = {ApiModule.class})
public interface MainPresenterComponent {
    void inject(MainPresenterImpl mainPresenterImpl);
}
