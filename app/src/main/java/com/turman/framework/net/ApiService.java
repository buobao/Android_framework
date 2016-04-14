package com.turman.framework.net;

import com.turman.framework.entity.MapResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by dqf on 2016/4/13.
 */
public interface ApiService {
    @GET(NetClient.LOCATION)
    Observable<MapResult> getAddress(@Query("latlng")String latlng, @Query("sensor")boolean sensor, @Query("language")String language);
}
