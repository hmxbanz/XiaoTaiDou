package com.xtdar.app.presenter;

import android.content.Context;

import com.xtdar.app.view.activity.LoginActivity;

/**
 * Created by hmxbanz on 2017/4/5.
 */

public class LoginPresenter {
    private Context mContext;
    private LoginActivity mActivity;
    public LoginPresenter(Context context){
        this.mContext=context;
        mActivity = (LoginActivity) context;
    }

    public void init() {
    }
}
