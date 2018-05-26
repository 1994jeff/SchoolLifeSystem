package com.my.app.schoollifesystem.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.my.app.schoollifesystem.R;
import com.my.app.schoollifesystem.bean.Card;

import java.util.List;

/**
 * Created by yf on 18-5-26.
 */

public class CardListAdapter extends BaseAdapter {

    public static final int FLAG_NORMAL_LIST = 0x1;
    public static final int FLAG_SEARCH_LIST = 0x2;

    Context mContext;
    List<Card> mCardList;
    int flag;

    public CardListAdapter(Context context, List<Card> cardList, int flag) {
        this.mCardList = cardList;
        this.mContext = context;
        this.flag = flag;
    }

    @Override
    public int getCount() {
        return mCardList != null ? mCardList.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return mCardList != null ? mCardList.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup group) {

        ViewHolder viewHolder = null;
        if(view==null)
        {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_card_layout, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Card card = mCardList.get(i);
        viewHolder.cardId.setText(card.getId());
        viewHolder.cardNum.setText(card.getNum());
        if(card.getStatus().equals("1")){
            viewHolder.cardStatus.setText("挂失");
        }else{
            viewHolder.cardStatus.setText("否");
        }
        viewHolder.cardTime.setText(card.getLostDate());
        if(flag==FLAG_NORMAL_LIST){
            viewHolder.guaShi.setVisibility(View.GONE);
        }else if(flag==FLAG_SEARCH_LIST) {
            viewHolder.guaShi.setVisibility(View.VISIBLE);
        }
        viewHolder.guaShi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
        return view;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView cardId;
        public TextView cardNum;
        public TextView cardStatus;
        public TextView cardTime;
        public TextView guaShi;
        public LinearLayout listLayout;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.cardId = (TextView) rootView.findViewById(R.id.cardId);
            this.cardNum = (TextView) rootView.findViewById(R.id.cardNum);
            this.cardStatus = (TextView) rootView.findViewById(R.id.cardStatus);
            this.cardTime = (TextView) rootView.findViewById(R.id.cardTime);
            this.guaShi = (TextView) rootView.findViewById(R.id.guaShi);
            this.listLayout = (LinearLayout) rootView.findViewById(R.id.listLayout);
        }

    }
}
