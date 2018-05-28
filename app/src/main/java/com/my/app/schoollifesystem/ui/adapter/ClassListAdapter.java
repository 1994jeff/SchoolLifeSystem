package com.my.app.schoollifesystem.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.my.app.schoollifesystem.R;
import com.my.app.schoollifesystem.bean.CardDto;
import com.my.app.schoollifesystem.bean.ClassInfo;

import java.util.List;

/**
 * Created by yf on 18-5-26.
 */

public class ClassListAdapter extends BaseAdapter {

    Context mContext;
    List<ClassInfo> mList;

    public ClassListAdapter(Context context, List<ClassInfo> cardList) {
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
        holder.classNo.setText(mList.get(i).getClassNo());
        holder.className.setText(mList.get(i).getClassName());
        holder.profession.setText(mList.get(i).getProfession());
        holder.classPeople.setText(mList.get(i).getClassPeople());

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
        }
    }
}
