package com.my.app.schoollifesystem.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.my.app.schoollifesystem.R;
import com.my.app.schoollifesystem.base.BaseCoreFragment;

/**
 */

public class FragmentFunctionList extends BaseCoreFragment implements View.OnClickListener {

    private TextView myInfo;
    private TextView schoolTalk;
    private TextView weather;
    private TextView cityList;
    private TextView clearCity;

    @Override
    protected void initView(View view) {
        myInfo = view.findViewById(R.id.myInfo);
        schoolTalk = view.findViewById(R.id.schoolTalk);
        weather = view.findViewById(R.id.weather);
        cityList = view.findViewById(R.id.cityList);
        clearCity = view.findViewById(R.id.clearCity);
        myInfo.setOnClickListener(this);
        schoolTalk.setOnClickListener(this);
        weather.setOnClickListener(this);
        cityList.setOnClickListener(this);
        clearCity.setOnClickListener(this);
    }

    @Override
    protected int getLayoutXml() {
        return R.layout.fragment_function_list_layout;
    }

    @Override
    public Object getPresenter() {
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.myInfo:
                switchFragment(new FragmentLogin(),R.string.login);
                break;
            case R.id.cityList:
                switchFragment(new FragmentMyCity(),R.string.city_list);
                break;
            case R.id.weather:
                switchFragment(new FragmentWeather(),R.string.weather);
                break;
            case R.id.clearCity:
                clear();
                break;
        }
    }

    private void clear() {
        //获取sharedPreferences对象
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("city", Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
        showFragmentToast(R.string.clear_ok);
    }
}
