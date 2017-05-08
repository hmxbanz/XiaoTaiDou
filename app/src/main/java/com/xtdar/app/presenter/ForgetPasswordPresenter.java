package com.xtdar.app.presenter;

import android.content.Context;

import com.xtdar.app.view.activity.ForgetPasswordActivity;

/**
 * Created by hmxbanz on 2017/4/5.
 */

public class ForgetPasswordPresenter {
    private Context mContext;
    private ForgetPasswordActivity mActivity;

    public ForgetPasswordPresenter(Context context){
        this.mContext=context;
        this.mActivity= (ForgetPasswordActivity) context;
    }

    public void init() {
    }
}
