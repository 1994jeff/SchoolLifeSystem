package com.my.app.schoollifesystem.bean;

import java.io.Serializable;

/**
 * Created by yf on 18-5-23.
 */

public class WeatherNow implements Serializable {

    Result[] results;

    @Override
    public String toString() {
        return "WeatherNow{" +
                "result=" + results +
                '}';
    }

    public Result[] getResult() {
        return results;
    }

    public void setResult(Result[] result) {
        this.results = result;
    }
}
