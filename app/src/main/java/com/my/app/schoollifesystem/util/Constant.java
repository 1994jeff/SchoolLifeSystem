package com.my.app.schoollifesystem.util;

import android.os.Environment;

/**
 * Created by yf on 18-5-23.
 */

public class Constant {

    public static final String BASE_API = "http://139.199.182.22/schoollife";
    public static final String CITY_LIST_API = "/login/getAllCity.do";
    public static final String CITY_LIKE_API = "/login/getCityLike.do";
    public static final String LOGIN_API = "/login/toCheckLogin.do";
    public static final String REGISTER_API = "/user/insertUser.do";
    public static final String IP_ADDRESS = "139.199.182.22";
    public static final int PORT = 8888;

    //未来3天天气API
    public static final String WEATHER_API = "https://api.seniverse.com/v3/weather/daily.json?key=m9icgr1pa9k0ithe&location=";
    public static final String WEATHER_API_END = "&language=zh-Hans&unit=c&start=0&days=3";

    //实时天气API
    public static final String WEATHER_API_NOW = "https://api.seniverse.com/v3/weather/now.json?key=m9icgr1pa9k0ithe&location=";
    public static final String WEATHER_API_NOW_END = "&language=zh-Hans&unit=c";

    public static String cacheDir = Environment.getExternalStorageDirectory().getAbsolutePath()+"/city.txt";

}
