package com.my.app.schoollifesystem.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.VolleyError;
import com.my.app.schoollifesystem.R;
import com.my.app.schoollifesystem.base.BaseCoreFragment;
import com.my.app.schoollifesystem.bean.City;
import com.my.app.schoollifesystem.common.VolleyInterface;
import com.my.app.schoollifesystem.common.VolleyRequest;
import com.my.app.schoollifesystem.presenter.AddCityPresenter;
import com.my.app.schoollifesystem.ui.adapter.CityListAdapter;
import com.my.app.schoollifesystem.util.Constant;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.my.app.schoollifesystem.base.BaseModel.FAILED;
import static com.my.app.schoollifesystem.base.BaseModel.RET_CODE;
import static com.my.app.schoollifesystem.base.BaseModel.RET_DATA;
import static com.my.app.schoollifesystem.base.BaseModel.RET_MSG;
import static com.my.app.schoollifesystem.base.BaseModel.SUCCESS;
import static com.my.app.schoollifesystem.common.VolleyRequest.mContext;

/**
 * Created by yf on 18-5-24.
 */

public class FragmentAddCity extends BaseCoreFragment<AddCityPresenter> implements View.OnFocusChangeListener {

    private EditText searchCity;
    private Button search;
    private ListView citySearch;
    private ListView cityList;
    List<City> mList = new ArrayList<City>();
    List<City> searchList = new ArrayList<City>();
    CityListAdapter mCityListAdapter;
    CityListAdapter searchCityAdapter;
    @Override
    public AddCityPresenter getPresenter() {
        return null;
    }

    @Override
    protected void initView(View view) {
        searchCity = view.findViewById(R.id.searchCity);
        search = view.findViewById(R.id.search);
        citySearch = view.findViewById(R.id.citySearch);
        cityList = view.findViewById(R.id.cityList);
        mCityListAdapter = new CityListAdapter(mList,getActivity());
        searchCityAdapter = new CityListAdapter(searchList,getActivity());
        cityList.setAdapter(mCityListAdapter);
        citySearch.setAdapter(searchCityAdapter);
        searchCity.addTextChangedListener(mTextWatcher);

        citySearch.setOnFocusChangeListener(this);
        search.setVisibility(View.GONE);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                citySearch.setVisibility(View.VISIBLE);
                cityList.setVisibility(View.GONE);
            }
        });

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> view, View view1, int i, long l) {
                if(mList!=null&&mList.size()>i){
                    String c = mList.get(i).getCity();
                    storeCity(c);
                    showFragmentToast(R.string.add_city_ok);
                }
            }
        });
        citySearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> view, View view1, int i, long l) {
                if(searchList!=null&&searchList.size()>i){
                    String c = searchList.get(i).getCity();
                    storeCity(c);
                    showFragmentToast(R.string.add_city_ok);
                    citySearch.setVisibility(View.GONE);
                    cityList.setVisibility(View.VISIBLE);
                    searchList.clear();
                    searchCityAdapter.notifyDataSetChanged();
                }
            }
        });

        initCityList();
    }

    private void storeCity(String c) {

        //获取sharedPreferences对象
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("city", Context.MODE_PRIVATE);
        //getString()第二个参数为缺省值，如果preference中不存在该key，将返回缺省值
        int length = sharedPreferences.getInt("length",0);
        length++;
        //获取editor对象
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        //存储键值对
        editor.putInt("length", length);
        editor.putString("city"+length, c);
        editor.commit();

    }

    private void initCityList() {
        String url  = Constant.BASE_API+Constant.CITY_LIST_API;
        VolleyRequest.requestGet(getActivity(),url,"",null,new VolleyInterface(mContext, VolleyInterface.mListener, VolleyInterface.mErrorListener){
            @Override
            public void onSuccess(String result) {
                mList.clear();
                String json = result.replace("\\", "");
                json = json.substring(1, json.length() - 1);
                JSONObject object = (JSONObject) JSON.parse(json);
                String code = object.getString(RET_CODE);
                String msg = object.getString(RET_MSG);
                if(code.equals(SUCCESS)){
                    JSONArray jsonArray = object.getJSONArray(RET_DATA);
                    int size = jsonArray.size();
                    for(int i=0;i<size;i++){
                        mList.add(jsonArray.getObject(i, City.class));
                    }
                    mCityListAdapter.notifyDataSetChanged();
                }else if(code.equals(FAILED)){
                    showFragmentToast(msg);
                }
            }
            @Override
            public void onError(VolleyError error) {
                showFragmentToast(error.getMessage());
            }
        });
    }

    @Override
    protected int getLayoutXml() {
        return R.layout.fragment_add_city_layout;
    }

    TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence sequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence sequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String city = searchCity.getText().toString();
            if(!TextUtils.isEmpty(city)){
                sendSearchRequest(city);
                citySearch.setVisibility(View.VISIBLE);
                cityList.setVisibility(View.GONE);
            }else{
                citySearch.setVisibility(View.GONE);
                cityList.setVisibility(View.VISIBLE);
            }

        }
    };

    private void sendSearchRequest(String city) {
        String url  = Constant.BASE_API+Constant.CITY_LIKE_API+"?city="+city;
        VolleyRequest.requestGet(getActivity(),url,"",null,new VolleyInterface(mContext, VolleyInterface.mListener, VolleyInterface.mErrorListener){
            @Override
            public void onSuccess(String result) {
                searchList.clear();
                String json = result.replace("\\", "");
                json = json.substring(1, json.length() - 1);
                JSONObject object = (JSONObject) JSON.parse(json);
                String code = object.getString(RET_CODE);
                String msg = object.getString(RET_MSG);
                if(code.equals(SUCCESS)){
                    JSONArray jsonArray = object.getJSONArray(RET_DATA);
                    int size = jsonArray.size();
                    for(int i=0;i<size;i++){
                        searchList.add(jsonArray.getObject(i, City.class));
                    }
                    searchCityAdapter.notifyDataSetChanged();
                }else if(code.equals(FAILED)){
                    showFragmentToast(msg);
                }
            }
            @Override
            public void onError(VolleyError error) {
                showFragmentToast(error.getMessage());
            }
        });
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if(b){
            citySearch.setVisibility(View.VISIBLE);
            cityList.setVisibility(View.GONE);
        }else{
            cityList.setVisibility(View.VISIBLE);
            citySearch.setVisibility(View.GONE);
        }
    }
}
