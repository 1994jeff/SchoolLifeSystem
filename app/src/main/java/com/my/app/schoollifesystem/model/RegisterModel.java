package com.my.app.schoollifesystem.model;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.VolleyError;
import com.my.app.schoollifesystem.base.BaseModel;
import com.my.app.schoollifesystem.base.IBaseView;
import com.my.app.schoollifesystem.common.VolleyInterface;
import com.my.app.schoollifesystem.common.VolleyRequest;
import com.my.app.schoollifesystem.util.Constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yf on 18-5-23.
 */

public class RegisterModel implements BaseModel{

    private Context mContext;
    private IBaseView mView;

    public RegisterModel(Context mContext,IBaseView mView){
        this.mContext = mContext;
        this.mView = mView;
    }

    public void login(String name, String psd) {
        Map<String, String> map = new HashMap<>();
        map.put("userName", name);
        map.put("userPsd", psd);
        VolleyRequest.requestGet(mContext, Constant.BASE_API + Constant.REGISTER_API, "", map, new VolleyInterface(mContext, VolleyInterface.mListener, VolleyInterface.mErrorListener) {
            @Override
            public void onSuccess(String result) {
                String json = result.replace("\\", "");
                json = json.substring(1, json.length() - 1);
                JSONObject object = (JSONObject) JSON.parse(json);
                String code = object.getString(RET_CODE);
                String msg = object.getString(RET_MSG);
                mView.showToast(msg);
            }

            @Override
            public void onError(VolleyError error) {
            }
        });
    }

}
