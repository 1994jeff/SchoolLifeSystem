package com.my.app.schoollifesystem.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.my.app.schoollifesystem.R;
import com.my.app.schoollifesystem.base.BaseCoreFragment;
import com.my.app.schoollifesystem.base.BaseFragment;
import com.my.app.schoollifesystem.presenter.RegisterPresenter;
import com.my.app.schoollifesystem.view.IRegisterView;

/**
 */

public class FragmentRegister extends BaseCoreFragment<RegisterPresenter> implements IRegisterView, View.OnClickListener {

    private EditText mLoginName;
    private EditText mLoginPsd,loginPsdAgain;
    private Button mBack;
    private Button mRegister;

    @Override
    public RegisterPresenter getPresenter() {
        return mPresenter==null?new RegisterPresenter(this):mPresenter;
    }

    @Override
    protected void initView(View view) {
        mLoginName = view.findViewById(R.id.loginName);
        mLoginPsd = view.findViewById(R.id.loginPsd);
        loginPsdAgain = view.findViewById(R.id.loginPsdAgain);
        mBack = view.findViewById(R.id.back);
        mRegister = view.findViewById(R.id.register);

        mRegister.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mBack.setVisibility(View.GONE);
    }

    @Override
    protected int getLayoutXml() {
        return R.layout.fragment_register_layout;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register:
                getPresenter().registerUser();
                break;
            case R.id.back:
                break;
        }
    }

    @Override
    public String getLoginPsd2() {
        return loginPsdAgain.getText().toString();
    }

    @Override
    public String getLoginPsd() {
        return mLoginPsd.getText().toString();
    }

    @Override
    public String getLoginName() {
        return mLoginName.getText().toString();
    }

    @Override
    public void showToast(int id) {
        showFragmentToast(id);
    }

    @Override
    public void showToast(String msg) {
        showFragmentToast(msg);
    }

    @Override
    public void loginPage() {

    }
}
