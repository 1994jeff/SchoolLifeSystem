package com.my.app.schoollifesystem.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.VolleyError;
import com.my.app.schoollifesystem.R;
import com.my.app.schoollifesystem.base.BaseCoreFragment;
import com.my.app.schoollifesystem.bean.CardDto;
import com.my.app.schoollifesystem.bean.Student;
import com.my.app.schoollifesystem.common.VolleyInterface;
import com.my.app.schoollifesystem.common.VolleyRequest;
import com.my.app.schoollifesystem.ui.adapter.StudentListAdapter;
import com.my.app.schoollifesystem.util.Constant;

import java.util.ArrayList;
import java.util.List;

import static com.my.app.schoollifesystem.base.BaseModel.FAILED;
import static com.my.app.schoollifesystem.base.BaseModel.RET_CODE;
import static com.my.app.schoollifesystem.base.BaseModel.RET_DATA;
import static com.my.app.schoollifesystem.base.BaseModel.RET_MSG;
import static com.my.app.schoollifesystem.base.BaseModel.SUCCESS;

/**
 * 学生信息模块
 */

public class FragmentStudent extends BaseCoreFragment {

    ListView stuList;
    StudentListAdapter mListAdapter;
    List<Student> mList = new ArrayList<>();
    Student mStudent = new Student();

    @Override
    public Object getPresenter() {
        return null;
    }

    @Override
    protected void initView(View view) {
        stuList = view.findViewById(R.id.stuList);
        mListAdapter = new StudentListAdapter(getActivity(),mList);
        stuList.setAdapter(mListAdapter);
        getStu();
    }

    private void getStu() {
        String url = Constant.BASE_API+Constant.STUDENT_LIST;
        Bundle bundle = getArguments();
        if(bundle!=null){
            String classNo = bundle.getString("classNo");
            if(!TextUtils.isEmpty(classNo)){
                url+="?classNo=";
                url+=classNo;
            }
        }
        VolleyRequest.requestGet(getActivity(),url ,"",null,new VolleyInterface(getActivity(),VolleyInterface.mListener,VolleyInterface.mErrorListener){
            @Override
            public void onSuccess(String result) {
                String json = result.replace("\\", "");
                json = json.substring(1, json.length() - 1);
                JSONObject jsonObject = (JSONObject) JSON.parse(json);
                String code = jsonObject.getString(RET_CODE);
                String msg = jsonObject.getString(RET_MSG);
                if(code.equals(SUCCESS)){
                    mList.clear();
                    mStudent.setId("id");
                    mStudent.setAge("  年龄");
                    mStudent.setClassNo("班级");
                    mStudent.setName("姓名");
                    mStudent.setStuNo("学号");
                    mList.add(mStudent);
                    JSONArray jsonArray = jsonObject.getJSONArray(RET_DATA);
                    int size = jsonArray.size();
                    if(size==0){
                        showFragmentToast(R.string.no_stu);
                        return;
                    }
                    for(int i=0;i<size;i++){
                        mList.add(jsonArray.getObject(i, Student.class));
                    }
                    mListAdapter.notifyDataSetChanged();
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
        return R.layout.fragment_student_layout;
    }
}
