package com.my.app.schoollifesystem.ui.fragment;

import android.view.View;

import com.my.app.schoollifesystem.base.BaseCoreFragment;
import com.my.app.schoollifesystem.presenter.WeatherPresenter;

/**
 * Created by yf on 18-5-22.
 */

public class FragmentWeather extends BaseCoreFragment<WeatherPresenter> {
    @Override
    public WeatherPresenter getPresenter() {
        return null;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected int getLayoutXml() {
        return 0;
    }
}
