package com.my.app.schoollifesystem.ui.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.my.app.schoollifesystem.R;
import com.my.app.schoollifesystem.base.BaseCoreFragment;

import java.util.Calendar;

/**
 */

public class FragmentFunctionList extends BaseCoreFragment implements View.OnClickListener {

    private TextView myInfo;
    private TextView schoolTalk;
    private TextView weather;
    private TextView cityList;
    private TextView clearCity;
    private TextView card;
    private TextView classList;
    private TextView stuList;
    private TextView timetable;
    private TextView setInfo;
    LinearLayout container;
    TimePickerDialog timePickerDialog;
    DatePickerDialog datePickerDialog;

    @Override
    protected void initView(View view) {
        myInfo = view.findViewById(R.id.myInfo);
        schoolTalk = view.findViewById(R.id.schoolTalk);
        weather = view.findViewById(R.id.weather);
        cityList = view.findViewById(R.id.cityList);
        clearCity = view.findViewById(R.id.clearCity);
        card = view.findViewById(R.id.card);
        classList = view.findViewById(R.id.classList);
        stuList = view.findViewById(R.id.stuList);
        timetable = view.findViewById(R.id.timetable);
        setInfo = view.findViewById(R.id.setInfo);

        container = view.findViewById(R.id.contain);

        myInfo.setOnClickListener(this);
        schoolTalk.setOnClickListener(this);
        weather.setOnClickListener(this);
        cityList.setOnClickListener(this);
        clearCity.setOnClickListener(this);
        card.setOnClickListener(this);
        classList.setOnClickListener(this);
        stuList.setOnClickListener(this);
        timetable.setOnClickListener(this);
        setInfo.setOnClickListener(this);
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
            case R.id.card:
                switchFragment(new FragmentCard(),R.string.card);
                break;
            case R.id.classList:
                switchFragment(new FragmentClass(),R.string.classs);
                break;
            case R.id.timetable:

                break;
            case R.id.stuList:
                switchFragment(new FragmentStudent(),R.string.stuList);
                break;
            case R.id.setInfo:
                showDateSelect();
                break;
        }
    }


    private void showDateSelect() {
        Log.i("jeff","showDateSelect");

        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int date = Calendar.getInstance().get(Calendar.DATE);
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int min = Calendar.getInstance().get(Calendar.MINUTE);
        timePickerDialog = new TimePickerDialog(getActivity(),0 ,new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker picker, int i, int i1) {
                String s= i+"-"+i1;
                showFragmentToast(s);
            }
        },hour,min,true);
        datePickerDialog = new DatePickerDialog(getActivity(),0, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker picker, int i, int i1, int i2) {
                String s= i+"-"+i1+"-"+i2;
                showFragmentToast(s);
                timePickerDialog.show();
            }
        },year,month,date);
        datePickerDialog.show();

    }

    private void clear() {
        //获取sharedPreferences对象
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("city", Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
        showFragmentToast(R.string.clear_ok);
    }
}
