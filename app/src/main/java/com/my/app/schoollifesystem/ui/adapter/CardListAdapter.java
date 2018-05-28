package com.my.app.schoollifesystem.ui.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.VolleyError;
import com.my.app.schoollifesystem.R;
import com.my.app.schoollifesystem.bean.Card;
import com.my.app.schoollifesystem.bean.CardDto;
import com.my.app.schoollifesystem.common.VolleyInterface;
import com.my.app.schoollifesystem.common.VolleyRequest;
import com.my.app.schoollifesystem.util.Constant;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.my.app.schoollifesystem.base.BaseModel.FAILED;
import static com.my.app.schoollifesystem.base.BaseModel.RET_CODE;
import static com.my.app.schoollifesystem.base.BaseModel.RET_DATA;
import static com.my.app.schoollifesystem.base.BaseModel.RET_MSG;
import static com.my.app.schoollifesystem.base.BaseModel.SUCCESS;

/**
 * Created by yf on 18-5-26.
 */

public class CardListAdapter extends BaseAdapter {

    public static final int FLAG_NORMAL_LIST = 0x1;
    public static final int FLAG_SEARCH_LIST = 0x2;

    Context mContext;
    List<CardDto> mCardList;
    int flag;
    Handler mHandler;

    public CardListAdapter(Context context, List<CardDto> cardList, int flag, Handler mHandler) {
        this.mCardList = cardList;
        this.mContext = context;
        this.flag = flag;
        this.mHandler = mHandler;
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
    public View getView(final int i, View view, ViewGroup group) {

        ViewHolder viewHolder = null;
        if(view==null)
        {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_card_layout, group,false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        CardDto card = mCardList.get(i);
        viewHolder.cardId.setText(card.getId());
        viewHolder.cardNum.setText(card.getCardNo());
        viewHolder.money.setText(card.getMoney());
        viewHolder.stuName.setText(card.getStuName());
        if(card.getStatus().equals("1")){
            viewHolder.cardStatus.setText("是");
            viewHolder.cardTime.setText(card.getLostDate());
        }else if(card.getStatus().equals("2")){
            viewHolder.cardStatus.setText("否");
            viewHolder.cardTime.setText("");
        }else{
            viewHolder.cardStatus.setText(card.getStatus());
        }
        if(flag==FLAG_NORMAL_LIST){
            viewHolder.guaShi.setVisibility(View.GONE);
            viewHolder.cardTime.setVisibility(View.VISIBLE);
        }else if(flag==FLAG_SEARCH_LIST) {
            if(mCardList.get(i).getLostDate().equals("操作"))
            {
                viewHolder.guaShi.setText("操作");
                viewHolder.guaShi.setClickable(false);
            }else{
                viewHolder.guaShi.setClickable(true);
                viewHolder.guaShi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setStatus(mCardList.get(i));
                    }
                });
            }
            viewHolder.guaShi.setVisibility(View.VISIBLE);
            viewHolder.cardTime.setVisibility(View.GONE);
        }

        return view;
    }

    private void setStatus(CardDto dto) {
        String url = "?cardNo="+dto.getCardNo();
        if (dto.getStatus().equals("2")) {
            url+="&lostDate='"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"'";
            url+="&status=1";
        }else{
            url+="&status=2";
        }
        Log.i("onclick",""+url);
        VolleyRequest.requestGet(mContext, Constant.BASE_API+Constant.CARD_STATUS+url,"",null,new VolleyInterface(mContext,VolleyInterface.mListener,VolleyInterface.mErrorListener){
            @Override
            public void onSuccess(String result) {
                Log.i("onclick",""+result);
                String json = result.replace("\\", "");
                json = json.substring(1, json.length() - 1);
                JSONObject jsonObject = (JSONObject) JSON.parse(json);
                String code = jsonObject.getString(RET_CODE);
                String msg = jsonObject.getString(RET_MSG);
                Message message = mHandler.obtainMessage();
                if(code.equals(SUCCESS)){
                    message.obj = "操作成功,刷新数据中";
                }else if(code.equals(FAILED)){
                    message.obj = "操作失败,刷新数据中";
                }
                mHandler.sendMessage(message);
            }

            @Override
            public void onError(VolleyError error) {
                Log.i("onclick",""+error.getMessage());
            }
        });
    }

    private String getStatus(CardDto dto) {

        return dto.getStatus().equals("2")?"1":"2";
    }

    public static class ViewHolder {
        public View rootView;
        public TextView cardId;
        public TextView cardNum;
        public TextView cardStatus;
        public TextView cardTime;
        public TextView guaShi;
        public TextView money;
        public TextView stuName;
        public LinearLayout listLayout;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.cardId = (TextView) rootView.findViewById(R.id.cardId);
            this.cardNum = (TextView) rootView.findViewById(R.id.cardNum);
            this.stuName = (TextView) rootView.findViewById(R.id.stuName);
            this.money = (TextView) rootView.findViewById(R.id.money);
            this.cardStatus = (TextView) rootView.findViewById(R.id.cardStatus);
            this.cardTime = (TextView) rootView.findViewById(R.id.cardTime);
            this.guaShi = (TextView) rootView.findViewById(R.id.guaShi);
            this.listLayout = (LinearLayout) rootView.findViewById(R.id.listLayout);
        }

    }
}
