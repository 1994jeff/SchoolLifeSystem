package com.my.app.schoollifesystem.ui.fragment;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.VolleyError;
import com.my.app.schoollifesystem.R;
import com.my.app.schoollifesystem.base.BaseCoreFragment;
import com.my.app.schoollifesystem.bean.CardDto;
import com.my.app.schoollifesystem.bean.ClassInfo;
import com.my.app.schoollifesystem.common.VolleyInterface;
import com.my.app.schoollifesystem.common.VolleyRequest;
import com.my.app.schoollifesystem.ui.adapter.ClassListAdapter;
import com.my.app.schoollifesystem.util.Constant;

import java.util.ArrayList;
import java.util.List;

import static com.my.app.schoollifesystem.base.BaseModel.FAILED;
import static com.my.app.schoollifesystem.base.BaseModel.RET_CODE;
import static com.my.app.schoollifesystem.base.BaseModel.RET_DATA;
import static com.my.app.schoollifesystem.base.BaseModel.RET_MSG;
import static com.my.app.schoollifesystem.base.BaseModel.SUCCESS;

/**
 * Created by yf on 18-5-28.
 */

public class FragmentClass extends BaseCoreFragment {

    ListView mListView;
    ClassListAdapter mClassListAdapter;
    List<ClassInfo> mClassList = new ArrayList<>();
    ClassInfo mClassInfo = new ClassInfo();

    @Override
    public Object getPresenter() {
        return null;
    }

    @Override
    protected void initView(View view) {
        mListView = view.findViewById(R.id.classList);
        mClassListAdapter = new ClassListAdapter(getActivity(),mClassList);
        mListView.setAdapter(mClassListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> view, View view1, int i, long l) {

            }
        });
        getClassInfo();

    }

    private void getClassInfo() {
        VolleyRequest.requestGet(getActivity(), Constant.BASE_API+Constant.CLASS_LIST,"",null,new VolleyInterface(getActivity(),VolleyInterface.mListener,VolleyInterface.mErrorListener){
            @Override
            public void onSuccess(String result) {
                String json = result.replace("\\", "");
                json = json.substring(1, json.length() - 1);
                JSONObject jsonObject = (JSONObject) JSON.parse(json);
                String code = jsonObject.getString(RET_CODE);
                String msg = jsonObject.getString(RET_MSG);
                if(code.equals(SUCCESS)){
                    mClassList.clear();
                    mClassInfo.setId("id");
                    mClassInfo.setClassNo("编号");
                    mClassInfo.setClassName("班级");
                    mClassInfo.setProfession("专业名称");
                    mClassInfo.setClassPeople("班主任");
                    mClassList.add(mClassInfo);
                    JSONArray jsonArray = jsonObject.getJSONArray(RET_DATA);
                    int size = jsonArray.size();
                    for(int i=0;i<size;i++){
                        mClassList.add(jsonArray.getObject(i, ClassInfo.class));
                    }
                    mClassListAdapter.notifyDataSetChanged();
                }else if(code.equals(FAILED)){
                    showFragmentToast(msg);
                }
            }

            @Override
            public void onError(VolleyError error) {

            }
        });
    }

    @Override
    protected int getLayoutXml() {
        return R.layout.fragment_class_layout;
    }
}
