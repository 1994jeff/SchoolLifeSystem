package com.my.app.schoollifesystem.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.my.app.schoollifesystem.R;
import com.my.app.schoollifesystem.bean.City;

import java.util.List;

/**
 * Created by yf on 18-5-24.
 */

public class CityListAdapter extends BaseAdapter {

    List<City> mList;
    Context mContext;

    public CityListAdapter(List<City> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return mList != null ? mList.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup group) {

        ViewHolder holder = null;
        if(view==null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_list_view_layout, group, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        holder.cityItemName.setText(mList.get(i).getCity());

        return view;
    }


    public static class ViewHolder {
        public View rootView;
        public TextView cityItemName;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.cityItemName = (TextView) rootView.findViewById(R.id.cityItemName);
        }

    }
}
