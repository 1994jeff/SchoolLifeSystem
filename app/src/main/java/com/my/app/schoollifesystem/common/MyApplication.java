package com.my.app.schoollifesystem.common;

import android.app.Application;
import android.os.Environment;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.my.app.schoollifesystem.bean.User;
import com.my.app.schoollifesystem.util.TcpClient;

/**
 * Created by yf on 18-5-23.
 */

public class MyApplication extends Application {

    //建立全局的请求队列
    public static RequestQueue mQueues;
    public User user;

    @Override
    public void onCreate() {
        super.onCreate();
        mQueues= Volley.newRequestQueue(getApplicationContext());
    }

    public void setUser(User u){
        user = u;
    }

    public User getUser(){
        return user;
    }

    public static RequestQueue getHttpQueue(){
        return mQueues;
    }

}
