package com.turman.framework;

import java.util.Map;

/**
 * Created by dqf on 2016/4/13.
 */
public interface MainPresenter {
    void loadEntity(Map<String,Object> params);
    void cancleLoad();
}
