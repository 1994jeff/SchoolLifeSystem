package com.my.app.schoollifesystem.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.my.app.schoollifesystem.R;
import com.my.app.schoollifesystem.base.BasePresenter;
import com.my.app.schoollifesystem.model.RegisterModel;
import com.my.app.schoollifesystem.ui.fragment.FragmentRegister;
import com.my.app.schoollifesystem.view.IRegisterView;

/**
 */

public class RegisterPresenter implements BasePresenter {

    private IRegisterView mRegisterView;
    private RegisterModel mModel;

    public RegisterPresenter(FragmentRegister register) {
        this.mRegisterView = register;
        mModel = new RegisterModel(register.getActivity(),mRegisterView);
    }

    public void registerUser() {
        String userName = mRegisterView.getLoginName();
        String userPsd = mRegisterView.getLoginPsd();
        String userPsd2 = mRegisterView.getLoginPsd2();
        if(TextUtils.isEmpty(userName)||TextUtils.isEmpty(userPsd)||TextUtils.isEmpty(userPsd2)){
            mRegisterView.showToast(R.string.epmtyparam);
        }else{
            if(userPsd.equals(userPsd2)){
                mModel.login(userName,userPsd);
            }else {
                mRegisterView.showToast(R.string.psdsame);
            }
        }
    }
}
