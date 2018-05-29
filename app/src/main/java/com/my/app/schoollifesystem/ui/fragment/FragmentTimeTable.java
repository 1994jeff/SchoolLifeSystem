package com.my.app.schoollifesystem.ui.fragment;

import android.view.View;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.VolleyError;
import com.my.app.schoollifesystem.R;
import com.my.app.schoollifesystem.base.BaseCoreFragment;
import com.my.app.schoollifesystem.bean.Student;
import com.my.app.schoollifesystem.bean.Timetable;
import com.my.app.schoollifesystem.common.VolleyInterface;
import com.my.app.schoollifesystem.common.VolleyRequest;
import com.my.app.schoollifesystem.ui.adapter.TimeTableListAdapter;
import com.my.app.schoollifesystem.util.Constant;

import java.util.ArrayList;
import java.util.List;

import static com.my.app.schoollifesystem.base.BaseModel.FAILED;
import static com.my.app.schoollifesystem.base.BaseModel.RET_CODE;
import static com.my.app.schoollifesystem.base.BaseModel.RET_DATA;
import static com.my.app.schoollifesystem.base.BaseModel.RET_MSG;
import static com.my.app.schoollifesystem.base.BaseModel.SUCCESS;

/**
 * Created by yf on 18-5-29.
 */

public class FragmentTimeTable extends BaseCoreFragment {

    ListView timeTable;
    TimeTableListAdapter mAdapter;
    List<Timetable> mList = new ArrayList<>();
    Timetable mTimetable = new Timetable();

    @Override
    public Object getPresenter() {
        return null;
    }

    @Override
    protected void initView(View view) {
        timeTable = view.findViewById(R.id.timeTable);
        mAdapter = new TimeTableListAdapter(getActivity(),mList);
        timeTable.setAdapter(mAdapter);
        getList();
    }

    private void getList() {
        VolleyRequest.requestGet(getActivity(), Constant.BASE_API+Constant.TIMETABLE_LIST,"",null,new VolleyInterface(getActivity(),VolleyInterface.mListener,VolleyInterface.mErrorListener){
            @Override
            public void onSuccess(String result) {
                String json = result.replace("\\", "");
                json = json.substring(1, json.length() - 1);
                JSONObject jsonObject = (JSONObject) JSON.parse(json);
                String code = jsonObject.getString(RET_CODE);
                String msg = jsonObject.getString(RET_MSG);
                if(code.equals(SUCCESS)){
                    mList.clear();
                    mTimetable.setId("id");
                    mTimetable.setGrade("成绩");
                    mTimetable.setNo("课表号");
                    mTimetable.setName("课表名");
                    mList.add(mTimetable);
                    JSONArray jsonArray = jsonObject.getJSONArray(RET_DATA);
                    int size = jsonArray.size();
                    if(size==0){
                        showFragmentToast(R.string.no_stu);
                        return;
                    }
                    for(int i=0;i<size;i++){
                        mList.add(jsonArray.getObject(i, Timetable.class));
                    }
                    mAdapter.notifyDataSetChanged();
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
        return R.layout.fragment_timetable_layout;
    }
}
