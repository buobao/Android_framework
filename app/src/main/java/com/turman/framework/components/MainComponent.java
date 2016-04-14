package com.turman.framework.components;

import com.turman.framework.MainActivity;
import com.turman.framework.modules.MainModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by dqf on 2016/4/13.
 */
@Singleton
@Component(modules = {MainModule.class})
public interface MainComponent {
    void inject(MainActivity activity);
}
