package com.xtdar.app.presenter;

import android.content.Context;

import com.xtdar.app.view.activity.RegisterActivity;

/**
 * Created by hmxbanz on 2017/4/5.
 */

public class RegisterPresenter {
    private Context mContext;
    private RegisterActivity mActivity;
    public RegisterPresenter(Context context){
        this.mContext=context;
        mActivity = (RegisterActivity) context;
    }

    public void init() {
    }
}
