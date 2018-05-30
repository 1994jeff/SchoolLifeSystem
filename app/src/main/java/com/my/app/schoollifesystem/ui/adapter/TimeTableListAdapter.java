package com.my.app.schoollifesystem.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.my.app.schoollifesystem.R;
import com.my.app.schoollifesystem.bean.Student;
import com.my.app.schoollifesystem.bean.Timetable;

import java.util.List;

/**
 * Created by yf on 18-5-26.
 */

public class TimeTableListAdapter extends BaseAdapter {

    Context mContext;
    List<Timetable> mList;

    public TimeTableListAdapter(Context context, List<Timetable> cardList) {
        this.mList = cardList;
        this.mContext = context;
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
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_class_layout, group, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.id.setText(mList.get(i).getId());
        holder.classNo.setText(mList.get(i).getNo());
        holder.className.setText(mList.get(i).getName());
        holder.profession.setText(mList.get(i).getGrade());

        return view;
    }


    public static class ViewHolder {
        public View rootView;
        public TextView id;
        public TextView classNo;
        public TextView className;
        public TextView profession;
        public TextView classPeople;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.id = (TextView) rootView.findViewById(R.id.id);
            this.classNo = (TextView) rootView.findViewById(R.id.classNo);
            this.className = (TextView) rootView.findViewById(R.id.className);
            this.profession = (TextView) rootView.findViewById(R.id.profession);
            this.classPeople = (TextView) rootView.findViewById(R.id.classPeople);
            this.classPeople.setVisibility(View.GONE);
        }
    }
}