package com.my.app.schoollifesystem.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.my.app.schoollifesystem.R;
import com.my.app.schoollifesystem.base.BaseCoreFragment;
import com.my.app.schoollifesystem.bean.Card;
import com.my.app.schoollifesystem.presenter.CardPresenter;
import com.my.app.schoollifesystem.ui.adapter.CardListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yf on 18-5-26.
 */

public class FragmentCard extends BaseCoreFragment<CardPresenter> implements View.OnClickListener {

    private EditText cardNum;
    private Button sendBtn;
    private ListView cardList;
    private ListView searchCardList;

    private CardListAdapter normalCardAdapter;
    private CardListAdapter searchCardAdapter;

    private List<Card> mList = new ArrayList<>();
    private List<Card> searchList = new ArrayList<>();

    @Override
    public CardPresenter getPresenter() {
        return mPresenter == null ? new CardPresenter() : mPresenter;
    }

    @Override
    protected void initView(View view) {
        searchCardList = view.findViewById(R.id.searchCardList);
        cardList = view.findViewById(R.id.cardList);
        cardNum = view.findViewById(R.id.cardNum);
        sendBtn = view.findViewById(R.id.sendBtn);
        normalCardAdapter = new CardListAdapter(getActivity(),getCardList(),CardListAdapter.FLAG_NORMAL_LIST);
        searchCardAdapter = new CardListAdapter(getActivity(),searchList,CardListAdapter.FLAG_SEARCH_LIST);
        sendBtn.setOnClickListener(this);
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
    }

    private List<Card> getCardList() {
        return mList;
    }

    @Override
    protected int getLayoutXml() {
        return R.layout.fragment_card_layout;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sendBtn:
                cardList.setVisibility(View.GONE);
                searchCardList.setVisibility(View.VISIBLE);
                break;
        }
    }
}
