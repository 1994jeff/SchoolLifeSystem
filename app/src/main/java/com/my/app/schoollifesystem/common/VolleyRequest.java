package com.my.app.schoollifesystem.common;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by yf on 18-5-23.
 */

public class VolleyRequest {
    public static Context mContext;
    public static StringRequest mStringRequest;

    /**
     * get请求
     * @param mContext
     * @param url
     * @param tag
     * @param map
     * @param vif
     */
    public static void requestGet(Context mContext, String url, String tag, Map<String, String> map, VolleyInterface vif){
        if(!TextUtils.isEmpty(tag)){
            MyApplication.getHttpQueue().cancelAll(tag);
        }
        if(map!=null){
            Set<String> ketList = map.keySet();
            StringBuilder builder = new StringBuilder("?");
            for (String key:ketList){
                String value = map.get(key);
                builder.append(key).append("=").append(value).append("&");
            }
            String param = builder.toString();
            url += param.substring(0,param.length()-1);
        }
        mStringRequest=new StringRequest(Request.Method.GET,url,vif.loadingListener(),vif.errorListener());
        mStringRequest.setTag(tag);
        MyApplication.getHttpQueue().add(mStringRequest);
//        MyApplication.getHttpQueue().start();
    }

    /**
     * post请求（带有map传递参数）
     * @param mContext
     * @param url
     * @param tag
     * @param params
     * @param vif
     */
    public static void requestPost(Context mContext, String url, String tag, final Map<String, String> params,VolleyInterface vif){
        MyApplication.getHttpQueue().cancelAll(tag);
        mStringRequest = new StringRequest(Request.Method.POST,url, vif.loadingListener(), vif.errorListener()) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                System.out.println("params:"+params);
                return params;
            }
        };

        mStringRequest.setTag(tag);
        MyApplication.getHttpQueue().add(mStringRequest);
        MyApplication.getHttpQueue().start();

    }

    /**
     * post请求(带有参数map 重写传递参数方法)
     *
     * @param mContext
     * @param url 地址
     * @param tag  标签
     * @param params 参数
     * @param vif 接口
     * void
     *
     */
    public static void requestSpecPost(Context mContext, String url, String tag, final Map<String, String> params,
                                       VolleyInterface vif) {
        MyApplication.getHttpQueue().cancelAll(tag);
        mStringRequest = new StringRequest(Request.Method.POST,url, vif.loadingListener(), vif.errorListener()) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                System.out.println("params:"+params);
                return params;
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                Map<String, String> params = getParams();
                if (params != null && params.size() > 0) {
                    try {
                        return params.get("data").getBytes("UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        };

        mStringRequest.setTag(tag);
        MyApplication.getHttpQueue().add(mStringRequest);
        MyApplication.getHttpQueue().start();
    }
}