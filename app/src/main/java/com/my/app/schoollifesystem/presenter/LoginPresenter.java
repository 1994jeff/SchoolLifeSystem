package com.my.app.schoollifesystem.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.VolleyError;
import com.my.app.schoollifesystem.R;
import com.my.app.schoollifesystem.base.BasePresenter;
import com.my.app.schoollifesystem.bean.User;
import com.my.app.schoollifesystem.common.MyApplication;
import com.my.app.schoollifesystem.common.VolleyInterface;
import com.my.app.schoollifesystem.common.VolleyRequest;
import com.my.app.schoollifesystem.ui.fragment.FragmentLogin;
import com.my.app.schoollifesystem.ui.fragment.FragmentTalk;
import com.my.app.schoollifesystem.ui.fragment.FragmentWeather;
import com.my.app.schoollifesystem.util.Constant;
import com.my.app.schoollifesystem.view.ILoginView;

import java.util.HashMap;
import java.util.Map;

import static com.my.app.schoollifesystem.base.BaseModel.RET_CODE;
import static com.my.app.schoollifesystem.base.BaseModel.RET_DATA;
import static com.my.app.schoollifesystem.base.BaseModel.RET_MSG;
import static com.my.app.schoollifesystem.common.VolleyRequest.mContext;

/**
 */

public class LoginPresenter implements BasePresenter {

    private ILoginView mView;

    public LoginPresenter(FragmentLogin mView){
        this.mView = mView;
    }

    public void doLogin() {
        final String userName = mView.getLoginName();
        String userPsd = mView.getLoginPsd();
        if(TextUtils.isEmpty(userName)||TextUtils.isEmpty(userPsd)){
            mView.showToast(R.string.epmtyparam);
        }else{
            Map<String, String> map = new HashMap<>();
            map.put("userName", mView.getLoginName());
            map.put("userPsd", mView.getLoginPsd());
            VolleyRequest.requestGet(((FragmentLogin)(mView)).getActivity(), Constant.BASE_API + Constant.LOGIN_API, "", map, new VolleyInterface(mContext, VolleyInterface.mListener, VolleyInterface.mErrorListener) {
                @Override
                public void onSuccess(String result) {
                    String json = result.replace("\\", "");
                    json = json.substring(1, json.length() - 1);
                    JSONObject object = (JSONObject) JSON.parse(json);
                    String code = object.getString(RET_CODE);
                    String msg = object.getString(RET_MSG);
                    if(code.equals(SUCCESS)){
                        JSONArray array = object.getJSONArray(RET_DATA);
                        User user = array.getObject(0,User.class);
                        MyApplication application = (MyApplication) ((FragmentLogin)(mView)).getContext().getApplicationContext();
                        application.setUser(user);
                        ((FragmentLogin)(mView)).switchFragment(new FragmentTalk(),R.string.talk);
                    }else {
                        mView.showToast(msg);
                    }
                }

                @Override
                public void onError(VolleyError error) {
                }
            });
        }
    }
}
