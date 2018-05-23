package com.my.app.schoollifesystem.bean;

import java.io.Serializable;

/**
 * Created by yf on 18-5-23.
 */

public class Result implements Serializable {

    String last_update;
    Location location;
    Now now;
    Daily[] daily;

    public Daily[] getDaily() {
        return daily;
    }

    public void setDaily(Daily[] daily) {
        this.daily = daily;
    }

    @Override
    public String toString() {
        return "Result{" +
                "last_update='" + last_update + '\'' +
                ", location=" + location +
                ", now=" + now +
                '}';
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Now getNow() {
        return now;
    }

    public void setNow(Now now) {
        this.now = now;
    }
}
