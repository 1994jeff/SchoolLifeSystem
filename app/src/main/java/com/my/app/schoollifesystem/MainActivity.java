package com.my.app.schoollifesystem;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.my.app.schoollifesystem.base.BaseActivity;
import com.my.app.schoollifesystem.base.BaseFragment;
import com.my.app.schoollifesystem.common.ActivityManager;
import com.my.app.schoollifesystem.ui.fragment.FragmentFunctionList;
import com.my.app.schoollifesystem.ui.fragment.FragmentIndex;
import com.my.app.schoollifesystem.ui.fragment.FragmentLogin;
import com.my.app.schoollifesystem.ui.fragment.FragmentRegister;

public class MainActivity extends BaseActivity {

    private LinearLayout myFun;
    private LinearLayout shcoolInfo;
    private LinearLayout mainHead;
    private TextView title;
    private LinearLayout fragmentHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityManager.instance().register(this);
        initView();
        switchFragment(new FragmentIndex());
    }

    private void initView() {
        myFun = (LinearLayout) findViewById(R.id.myFun);
        shcoolInfo = (LinearLayout) findViewById(R.id.schoolLife);
        myFun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myFun.setBackgroundResource(R.drawable.layer_head_bg);
                shcoolInfo.setBackgroundResource(R.drawable.layer_text_bottom_bg);
                switchFragment(new FragmentFunctionList());
            }
        });
        shcoolInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shcoolInfo.setBackgroundResource(R.drawable.layer_head_bg);
                myFun.setBackgroundResource(R.drawable.layer_text_bottom_bg);
                switchFragment(new FragmentIndex());
            }
        });
        mainHead = (LinearLayout) findViewById(R.id.mainHead);
        title = (TextView) findViewById(R.id.title);
        fragmentHead = (LinearLayout) findViewById(R.id.fragmentHead);
    }

    @Override
    public void switchFragment(Fragment targetFragment) {
        super.switchFragment(targetFragment);
        mainHead.setVisibility(View.VISIBLE);
        fragmentHead.setVisibility(View.GONE);
    }

    @Override
    public void switchFragment(Fragment targetFragment, int title) {
        switchFragment(targetFragment);
        fragmentHead.setVisibility(View.VISIBLE);
        this.title.setText(title);
        mainHead.setVisibility(View.GONE);
    }

    long backClickTime = 0l;
    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if(fragment instanceof FragmentFunctionList || fragment instanceof FragmentIndex){
            isBackDoubleClick();
            return;
        }else if(fragment instanceof FragmentLogin){
            mainHead.setVisibility(View.VISIBLE);
            fragmentHead.setVisibility(View.GONE);
        }else if(fragment instanceof FragmentRegister){
            mainHead.setVisibility(View.GONE);
            this.title.setText(R.string.login);
            fragmentHead.setVisibility(View.VISIBLE);
        }
        super.onBackPressed();
    }

    private void isBackDoubleClick() {
        long currentTime = System.currentTimeMillis();
        long time = currentTime - backClickTime;
        backClickTime = currentTime;
        if(time>0 && time < 2000){
            finish();
        }else{
            Toast.makeText(this,getString(R.string.timeTooShort),Toast.LENGTH_SHORT).show();
        }

    }
}
