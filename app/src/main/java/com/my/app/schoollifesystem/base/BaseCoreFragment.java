package com.my.app.schoollifesystem.base;

/**
 */

public abstract class BaseCoreFragment<T> extends BaseFragment {

    public T mPresenter;

    public  abstract T getPresenter();

}
