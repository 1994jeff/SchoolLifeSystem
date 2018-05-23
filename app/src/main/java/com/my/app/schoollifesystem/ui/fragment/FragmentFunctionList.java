package com.my.app.schoollifesystem.ui.fragment;

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

    @Override
    protected void initView(View view) {
        myInfo = view.findViewById(R.id.myInfo);
        schoolTalk = view.findViewById(R.id.schoolTalk);
        weather = view.findViewById(R.id.weather);
        myInfo.setOnClickListener(this);
        schoolTalk.setOnClickListener(this);
        weather.setOnClickListener(this);
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
            case R.id.schoolTalk:
//                switchFragment();
                break;
            case R.id.weather:
                switchFragment(new FragmentWeather(),R.string.weather);
                break;
        }
    }
}
