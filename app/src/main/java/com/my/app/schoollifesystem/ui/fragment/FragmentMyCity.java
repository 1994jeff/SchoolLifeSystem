package com.my.app.schoollifesystem.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.my.app.schoollifesystem.R;
import com.my.app.schoollifesystem.base.BaseCoreFragment;
import com.my.app.schoollifesystem.bean.City;
import com.my.app.schoollifesystem.presenter.MyCityPresenter;
import com.my.app.schoollifesystem.ui.adapter.CityListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yf on 18-5-24.
 */

public class FragmentMyCity extends BaseCoreFragment<MyCityPresenter> {

    private ListView myCity;
    CityListAdapter mCityListAdapter;
    List<City> mCityList = new ArrayList<>();

    @Override
    public MyCityPresenter getPresenter() {
        return null;
    }

    @Override
    protected void initView(View view) {
        myCity = view.findViewById(R.id.myCity);
        getMyCityList();
        mCityListAdapter = new CityListAdapter(mCityList,getActivity());
        myCity.setAdapter(mCityListAdapter);

        myCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> view, View view1, int i, long l) {
                String city = mCityList.get(i).getCity();
                FragmentWeather fragmentWeather = new FragmentWeather();
                Bundle bundle = new Bundle();
                bundle.putString("city",city);
                fragmentWeather.setArguments(bundle);
                switchFragment(fragmentWeather,R.string.weather);
            }
        });
    }

    private void getMyCityList() {
        //获取sharedPreferences对象
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("city", Context.MODE_PRIVATE);
        //getString()第二个参数为缺省值，如果preference中不存在该key，将返回缺省值
        int length = sharedPreferences.getInt("length",0);
        City cc = null;
        mCityList.clear();
        for(int i=1;i<=length;i++){
            String c = "city"+i;
            String city = sharedPreferences.getString(c,"");
            cc = new City(city);
            mCityList.add(cc);
        }
    }

    @Override
    protected int getLayoutXml() {
        return R.layout.fragment_my_city_layout;
    }

}
