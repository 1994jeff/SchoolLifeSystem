package com.my.app.schoollifesystem.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.my.app.schoollifesystem.R;
import com.my.app.schoollifesystem.base.BaseCoreFragment;
import com.my.app.schoollifesystem.presenter.LoginPresenter;
import com.my.app.schoollifesystem.view.ILoginView;

/**
 */

public class FragmentLogin extends BaseCoreFragment<LoginPresenter> implements ILoginView, View.OnClickListener {

    private EditText mLoginName;
    private EditText mLoginPsd;
    private Button mLogin;
    private Button mRegister;

    @Override
    protected void initView(View view) {
        mLoginName = view.findViewById(R.id.loginName);
        mLoginPsd = view.findViewById(R.id.loginPsd);
        mLogin = view.findViewById(R.id.login);
        mRegister = view.findViewById(R.id.register);

        setOnClickListener();
    }

    private void setOnClickListener() {
        mLogin.setOnClickListener(this);
        mRegister.setOnClickListener(this);
    }

    @Override
    protected int getLayoutXml() {
        return R.layout.fragment_login_layout;
    }

    @Override
    public LoginPresenter getPresenter() {
        return mPresenter==null?new LoginPresenter(this):mPresenter;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                getPresenter().doLogin();
                break;
            case R.id.register:
                switchFragment(new FragmentRegister(),R.string.register);
                break;
        }
    }

    @Override
    public void showToast(int epmtyparam) {
        showFragmentToast(epmtyparam);
    }

    @Override
    public void showToast(String msg) {
        showFragmentToast(msg);
    }

    @Override
    public String getLoginPsd() {
        return mLoginPsd.getText().toString();
    }

    @Override
    public String getLoginName() {
        return mLoginName.getText().toString();
    }
}
