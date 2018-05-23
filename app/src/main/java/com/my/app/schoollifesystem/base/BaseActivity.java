package com.my.app.schoollifesystem.base;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.my.app.schoollifesystem.R;

/**
 */

public abstract class BaseActivity extends FragmentActivity {

    public void switchFragment(Fragment targetFragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, targetFragment);
        transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();
    }

    public abstract void switchFragment(Fragment targetFragment, int title);
}
