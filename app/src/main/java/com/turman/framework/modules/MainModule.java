package com.turman.framework.modules;

import com.turman.framework.MainPresenter;
import com.turman.framework.MainPresenterImpl;
import com.turman.framework.MainView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dqf on 2016/4/13.
 */
@Module
@Singleton
public class MainModule {

    private MainView mainView;

    public MainModule(MainView mainView){
        this.mainView = mainView;
    }

    @Provides
    public MainPresenter providesMainPresenter(){
        return new MainPresenterImpl(mainView);
    }
}
