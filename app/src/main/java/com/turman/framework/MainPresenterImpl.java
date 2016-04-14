package com.turman.framework;

import com.turman.framework.components.DaggerMainPresenterComponent;
import com.turman.framework.components.MainPresenterComponent;
import com.turman.framework.entity.MapResult;
import com.turman.framework.modules.ApiModule;
import com.turman.framework.net.ApiService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by dqf on 2016/4/13.
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;
    @Inject
    ApiService apiService;

    private MainPresenterComponent component;

    private List<Subscription> mSubscriptions = new ArrayList<>();

    public MainPresenterImpl(MainView mainView){
        this.mainView = mainView;
        component = DaggerMainPresenterComponent.builder().apiModule(new ApiModule()).build();
        //component = DaggerMainComponent.builder().build();
        component.inject(this);
    }

    @Override
    public void loadEntity(Map<String, Object> params) {
        mSubscriptions.add(Observable.just(params)
                .flatMap(new Func1<Map<String, Object>, Observable<MapResult>>() {
                    @Override
                    public Observable<MapResult> call(Map<String, Object> stringObjectMap) {
                        return (apiService.getAddress((String) stringObjectMap.get("latlng"), (boolean) stringObjectMap.get("sensor"), (String) stringObjectMap.get("language")));
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MapResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(MapResult mapResult) {
                        if ("OK".equals(mapResult.status)) {
                            mainView.setPlaces(mapResult);
                        }
                    }
                }));
    }

    @Override
    public void cancleLoad() {
        if (mSubscriptions.size() > 0) {
            for (Subscription s:mSubscriptions){
                if (s != null && !s.isUnsubscribed()){
                    s.unsubscribe();
                }
            }
        }
    }


}
