package com.turman.framework.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import javax.inject.Inject;

/**
 * Created by dqf on 2016/4/5.
 */
public class LocationUtils {

    @Inject
    public LocationUtils() {
    }

    public Location getLocation(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
            if (location == null) {
                location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
            }
            return location;
        }
        return null;
    }
}
