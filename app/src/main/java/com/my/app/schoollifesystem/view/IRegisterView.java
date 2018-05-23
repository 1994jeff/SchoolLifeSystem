package com.my.app.schoollifesystem.view;

import com.my.app.schoollifesystem.base.IBaseView;

/**
 * Created by yf on 18-5-22.
 */

public interface IRegisterView extends IBaseView {
    String getLoginPsd2();

    String getLoginPsd();

    String getLoginName();

    void loginPage();
}
