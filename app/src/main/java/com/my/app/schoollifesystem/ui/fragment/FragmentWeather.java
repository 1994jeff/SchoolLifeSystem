package com.my.app.schoollifesystem.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.VolleyError;
import com.my.app.schoollifesystem.R;
import com.my.app.schoollifesystem.base.BaseCoreFragment;
import com.my.app.schoollifesystem.bean.Location;
import com.my.app.schoollifesystem.bean.Now;
import com.my.app.schoollifesystem.bean.WeatherNow;
import com.my.app.schoollifesystem.common.VolleyInterface;
import com.my.app.schoollifesystem.common.VolleyRequest;
import com.my.app.schoollifesystem.presenter.WeatherPresenter;
import com.my.app.schoollifesystem.util.Constant;

import static com.my.app.schoollifesystem.common.VolleyRequest.mContext;

/**
 * 天气预报模块
 */

public class FragmentWeather extends BaseCoreFragment<WeatherPresenter> {

    private TextView cityName;
    private TextView weather;
    private TextView temptrue;
    private TextView pressure;
    private TextView wind_speed;
    private TextView visibility;
    private TextView wind_direction_degree;
    private TextView feels_like;
    private TextView humidity;
    private TextView startDay;
    private TextView text_day;
    private TextView max_temp;
    private TextView min_temp;
    private TextView startDay2;
    private TextView text_day2;
    private TextView max_temp2;
    private TextView min_temp2;
    private TextView startDay3;
    private TextView text_day3;
    private TextView max_temp3;
    private TextView min_temp3;
    private TextView addCity;
    private ImageView weatherImg;

    @Override
    public WeatherPresenter getPresenter() {
        return null;
    }

    @Override
    protected void initView(View view) {
        weatherImg = view.findViewById(R.id.weatherImg);
        cityName = view.findViewById(R.id.cityName);
        weather = view.findViewById(R.id.weather);
        temptrue = view.findViewById(R.id.temptrue);
        pressure = view.findViewById(R.id.pressure);
        wind_speed = view.findViewById(R.id.wind_speed);
        visibility = view.findViewById(R.id.visibility);
        wind_direction_degree = view.findViewById(R.id.wind_direction_degree);
        feels_like = view.findViewById(R.id.feels_like);
        humidity = view.findViewById(R.id.humidity);
        startDay = view.findViewById(R.id.startDay);
        text_day = view.findViewById(R.id.text_day);
        max_temp = view.findViewById(R.id.max_temp);
        min_temp = view.findViewById(R.id.min_temp);
        startDay2 = view.findViewById(R.id.startDay2);
        text_day2 = view.findViewById(R.id.text_day2);
        max_temp2 = view.findViewById(R.id.max_temp2);
        min_temp2 = view.findViewById(R.id.min_temp2);
        startDay3 = view.findViewById(R.id.startDay3);
        text_day3 = view.findViewById(R.id.text_day3);
        max_temp3 = view.findViewById(R.id.max_temp3);
        min_temp3 = view.findViewById(R.id.min_temp3);
        addCity = view.findViewById(R.id.addCity);
        addCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(new FragmentAddCity(),R.string.add_city);
            }
        });
        getWeatherFromInternet();
    }

    private void getWeatherFromInternet() {
        getNowWeather();
        getThreeDayWeather();
    }

    private void getThreeDayWeather() {
        String city = getString();
        String url  = Constant.WEATHER_API+city+Constant.WEATHER_API_END;
        VolleyRequest.requestGet(getActivity(),url,"",null,new VolleyInterface(mContext, VolleyInterface.mListener, VolleyInterface.mErrorListener){
            @Override
            public void onSuccess(String result) {
                JSONObject object = (JSONObject) JSON.parse(result);
                JSONArray jsonArray = (JSONArray) object.get("results");
                JSONObject jsonObject = (JSONObject) JSON.parse(jsonArray.get(0).toString());
                JSONArray dailyArray = (JSONArray) jsonObject.get("daily");
                int size = dailyArray.size();
                for(int i=0;i<size;i++){
                    JSONObject daily = (JSONObject) dailyArray.get(i);
                    if(i==0){
                        pressure.setText(" 正常");
                        wind_speed.setText(" "+daily.get("wind_speed")+" km/h");
                        visibility.setText(" 正常");
                        wind_direction_degree.setText(" "+daily.get("wind_direction_degree"));
                        feels_like.setText(" 正常");
                        humidity.setText(" 正常");
                        startDay.setText(daily.get("date")+" ");
                        text_day.setText(daily.get("text_day")+"");
                        max_temp.setText(daily.get("high")+"度");
                        min_temp.setText(daily.get("low")+"度");
                    }
                    if(i==1){
                        startDay2.setText(daily.get("date")+" ");
                        text_day2.setText(daily.get("text_day")+"");
                        max_temp2.setText(daily.get("high")+"度");
                        min_temp2.setText(daily.get("low")+"度");
                    }
                    if(i==2){
                        startDay3.setText(daily.get("date")+" ");
                        text_day3.setText(daily.get("text_day")+"");
                        max_temp3.setText(daily.get("high")+"度");
                        min_temp3.setText(daily.get("low")+"度");
                    }

                }
            }
            @Override
            public void onError(VolleyError error) {
            }
        });
    }

    private String getString() {
        String city = "北京";
        Bundle bundle = getArguments();
        if(bundle!=null){
            city = bundle.getString("city");
        }
        return city;
    }

    private void getNowWeather() {
        String city = getString();
        String url  = Constant.WEATHER_API_NOW+city+Constant.WEATHER_API_NOW_END;
        VolleyRequest.requestGet(getActivity(),url,"",null,new VolleyInterface(mContext, VolleyInterface.mListener, VolleyInterface.mErrorListener){
            @Override
            public void onSuccess(String result) {
                JSONObject object = (JSONObject) JSON.parse(result);
                JSONArray jsonArray = (JSONArray) object.get("results");
                JSONObject jsonObject = (JSONObject) JSON.parse(jsonArray.get(0).toString());
                JSONObject locationObj = (JSONObject) jsonObject.get("location");
                JSONObject nowObj = (JSONObject) jsonObject.get("now");
                setWeatherImage(nowObj.get("code")+"");
                cityName.setText(locationObj.get("name")+"");
                weather.setText(nowObj.get("text")+"");
                temptrue.setText(nowObj.get("temperature")+"度");
            }
            @Override
            public void onError(VolleyError error) {
                showFragmentToast("暂无天气信息,请更换城市");
            }
        });
    }

    private void setWeatherImage(String code) {
        switch (code){
            case "0":
                weatherImg.setImageResource(R.mipmap.w0);
                break;
            case "1":
                weatherImg.setImageResource(R.mipmap.w1);
                break;
            case "2":
                weatherImg.setImageResource(R.mipmap.w2);
                break;
            case "3":
                weatherImg.setImageResource(R.mipmap.w3);
                break;
            case "4":
                weatherImg.setImageResource(R.mipmap.w4);
                break;
            case "5":
                weatherImg.setImageResource(R.mipmap.w5);
                break;
            case "6":
                weatherImg.setImageResource(R.mipmap.w6);
                break;
            case "7":
                weatherImg.setImageResource(R.mipmap.w7);
                break;
            case "8":
                weatherImg.setImageResource(R.mipmap.w8);
                break;
            case "9":
                weatherImg.setImageResource(R.mipmap.w9);
                break;
            case "10":
                weatherImg.setImageResource(R.mipmap.w10);
                break;
            case "11":
                weatherImg.setImageResource(R.mipmap.w11);
                break;
            case "12":
                weatherImg.setImageResource(R.mipmap.w12);
                break;
            case "13":
                weatherImg.setImageResource(R.mipmap.w13);
                break;
            case "14":
                weatherImg.setImageResource(R.mipmap.w14);
                break;
            case "15":
                weatherImg.setImageResource(R.mipmap.w15);
                break;
            case "16":
                weatherImg.setImageResource(R.mipmap.w16);
                break;
            case "17":
                weatherImg.setImageResource(R.mipmap.w17);
                break;
            case "18":
                weatherImg.setImageResource(R.mipmap.w18);
                break;
            case "19":
                weatherImg.setImageResource(R.mipmap.w19);
                break;
            case "20":
                weatherImg.setImageResource(R.mipmap.w20);
                break;
            case "21":
                weatherImg.setImageResource(R.mipmap.w21);
                break;
            case "22":
                weatherImg.setImageResource(R.mipmap.w22);
                break;
            case "23":
                weatherImg.setImageResource(R.mipmap.w23);
                break;
            case "24":
                weatherImg.setImageResource(R.mipmap.w24);
                break;
            case "25":
                weatherImg.setImageResource(R.mipmap.w25);
                break;
            case "26":
                weatherImg.setImageResource(R.mipmap.w26);
                break;
            default:
                weatherImg.setImageResource(R.mipmap.w99);
                break;
        }
    }

    @Override
    protected int getLayoutXml() {
        return R.layout.fragment_weather_layout;
    }

}
