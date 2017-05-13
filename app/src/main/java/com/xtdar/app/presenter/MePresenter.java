package com.xtdar.app.presenter;

import android.content.Context;

import com.xtdar.app.view.activity.MeActivity;
import com.xtdar.app.view.activity.SettingActivity;


/**
 * Created by hmxbanz on 2017/4/5.
 */

public class MePresenter {
    Context mContext;
    MeActivity mActivity;

    public MePresenter(Context context) {
        this.mActivity =(MeActivity)context;
        this.mContext=context;
    }
    public void init(){
    //mView.initData();
};


}
