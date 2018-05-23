package com.my.app.schoollifesystem.common;

import android.support.v4.app.Fragment;

import com.my.app.schoollifesystem.base.BaseActivity;

/**
 */

public class ActivityManager {

    private BaseActivity mActivity;

    public void register(BaseActivity mActivity){
        this.mActivity = mActivity;
    }

    public void switchFragment(Fragment fragment, int title){
        mActivity.switchFragment(fragment,title);
    }

    private ActivityManager(){};

    public static ActivityManager instance(){
        return A.activityManager;
    }

    private static class A{
        private static ActivityManager activityManager = new ActivityManager(){};
    }
}
