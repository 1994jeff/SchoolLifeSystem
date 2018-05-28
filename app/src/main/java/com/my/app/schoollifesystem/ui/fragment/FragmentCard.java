package com.my.app.schoollifesystem.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
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
import com.my.app.schoollifesystem.base.BaseModel;
import com.my.app.schoollifesystem.bean.Card;
import com.my.app.schoollifesystem.bean.CardDto;
import com.my.app.schoollifesystem.bean.City;
import com.my.app.schoollifesystem.common.VolleyInterface;
import com.my.app.schoollifesystem.common.VolleyRequest;
import com.my.app.schoollifesystem.presenter.CardPresenter;
import com.my.app.schoollifesystem.ui.adapter.CardListAdapter;
import com.my.app.schoollifesystem.util.Constant;

import java.util.ArrayList;
import java.util.List;

import static com.my.app.schoollifesystem.base.BaseModel.FAILED;
import static com.my.app.schoollifesystem.base.BaseModel.RET_CODE;
import static com.my.app.schoollifesystem.base.BaseModel.RET_DATA;
import static com.my.app.schoollifesystem.base.BaseModel.RET_MSG;
import static com.my.app.schoollifesystem.base.BaseModel.SUCCESS;

/**
 * Created by yf on 18-5-26.
 */

public class FragmentCard extends BaseCoreFragment<CardPresenter> implements View.OnClickListener {

    private EditText cardNum;
    private Button sendBtn;
    private Button sendAllBtn;
    private ListView cardList;
    private ListView searchCardList;

    private CardListAdapter normalCardAdapter;
    private CardListAdapter searchCardAdapter;

    private List<CardDto> mList = new ArrayList<>();
    private List<CardDto> searchList = new ArrayList<>();
    CardDto defaultD = new CardDto();

    @Override
    public CardPresenter getPresenter() {
        return mPresenter == null ? new CardPresenter() : mPresenter;
    }

    @Override
    protected void initView(View view) {
        searchCardList = view.findViewById(R.id.searchCardList);
        searchCardList.setVisibility(View.GONE);
        cardList = view.findViewById(R.id.cardList);
        cardList.setVisibility(View.VISIBLE);
        cardNum = view.findViewById(R.id.cardNum);
        sendBtn = view.findViewById(R.id.sendBtn);
        sendAllBtn = view.findViewById(R.id.sendAllBtn);
        normalCardAdapter = new CardListAdapter(getActivity(),mList,CardListAdapter.FLAG_NORMAL_LIST);
        searchCardAdapter = new CardListAdapter(getActivity(),searchList,CardListAdapter.FLAG_SEARCH_LIST);
        cardList.setAdapter(normalCardAdapter);
        searchCardList.setAdapter(searchCardAdapter);
        sendBtn.setOnClickListener(this);
        sendAllBtn.setOnClickListener(this);
        cardList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> view, View view1, int i, long l) {

            }
        });
        searchCardList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> view, View view1, int i, long l) {
                searchCardList.setVisibility(View.GONE);
                cardList.setVisibility(View.VISIBLE);
            }
        });

        getCardList();
    }

    private void getCardList() {
        VolleyRequest.requestGet(getActivity(), Constant.BASE_API+Constant.CARD_LIST,"",null,new VolleyInterface(getActivity(),VolleyInterface.mListener,VolleyInterface.mErrorListener){
            @Override
            public void onSuccess(String result) {
                String json = result.replace("\\", "");
                json = json.substring(1, json.length() - 1);
                JSONObject jsonObject = (JSONObject) JSON.parse(json);
                String code = jsonObject.getString(RET_CODE);
                String msg = jsonObject.getString(RET_MSG);
                if(code.equals(SUCCESS)){
                    mList.clear();
                    defaultD.setId("id");
                    defaultD.setCardNo("卡号");
                    defaultD.setStuName("姓名");
                    defaultD.setStatus("挂失");
                    defaultD.setMoney("余额");
                    defaultD.setLostDate("挂失时间");
                    mList.add(defaultD);
                    JSONArray jsonArray = jsonObject.getJSONArray(RET_DATA);
                    int size = jsonArray.size();
                    for(int i=0;i<size;i++){
                        mList.add(jsonArray.getObject(i, CardDto.class));
                        Log.i("jeff",jsonArray.getObject(i, CardDto.class).getCardNo());
                    }
                    normalCardAdapter.notifyDataSetChanged();
                }else if(code.equals(FAILED)){
                    showFragmentToast(msg);
                }
            }

            @Override
            public void onError(VolleyError error) {

            }
        });
        cardList.setVisibility(View.VISIBLE);
        searchCardList.setVisibility(View.GONE);
    }

    @Override
    protected int getLayoutXml() {
        return R.layout.fragment_card_layout;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sendBtn:

                getSearchList();

                break;
            case R.id.sendAllBtn:
                getCardList();

                break;
        }
    }

    private void getSearchList() {
        String num = cardNum.getText().toString();
        if(TextUtils.isEmpty(num)){
            showFragmentToast("please input card num");
            return;
        }
        VolleyRequest.requestGet(getActivity(), Constant.BASE_API+Constant.CARD_LIST+"?cardNo="+num,"",null,new VolleyInterface(getActivity(),VolleyInterface.mListener,VolleyInterface.mErrorListener){
            @Override
            public void onSuccess(String result) {
                String json = result.replace("\\", "");
                json = json.substring(1, json.length() - 1);
                JSONObject jsonObject = (JSONObject) JSON.parse(json);
                String code = jsonObject.getString(RET_CODE);
                String msg = jsonObject.getString(RET_MSG);
                if(code.equals(SUCCESS)){
                    searchList.clear();
                    defaultD.setId("id");
                    defaultD.setCardNo("卡号");
                    defaultD.setStuName("姓名");
                    defaultD.setStatus("挂失");
                    defaultD.setMoney("余额");
                    defaultD.setLostDate("操作");
                    searchList.add(defaultD);
                    JSONArray jsonArray = jsonObject.getJSONArray(RET_DATA);
                    int size = jsonArray.size();
                    for(int i=0;i<size;i++){
                        searchList.add(jsonArray.getObject(i, CardDto.class));
                    }
                    searchCardAdapter.notifyDataSetChanged();
                }else if(code.equals(FAILED)){
                    showFragmentToast(msg);
                }
            }

            @Override
            public void onError(VolleyError error) {

            }
        });
        cardList.setVisibility(View.GONE);
        searchCardList.setVisibility(View.VISIBLE);
    }
}
