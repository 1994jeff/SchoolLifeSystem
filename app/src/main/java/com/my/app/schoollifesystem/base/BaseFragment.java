package com.my.app.schoollifesystem.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.my.app.schoollifesystem.common.ActivityManager;

/**
 */

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutXml(),container,false);
        initView(view);
        return view;
    }

    protected abstract void initView(View view);

    protected abstract int getLayoutXml();

    public void switchFragment(Fragment fragmentActivity,int title){
        ActivityManager.instance().switchFragment(fragmentActivity,title);
    }

    public void showFragmentToast(int id){
        Toast.makeText(getActivity(),getString(id),Toast.LENGTH_SHORT).show();
    }

    public void showFragmentToast(String msg){
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

}
