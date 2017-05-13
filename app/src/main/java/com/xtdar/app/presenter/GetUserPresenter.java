package com.xtdar.app.presenter;

import android.content.Context;

import com.xtdar.app.view.activity.DetailActivity;

/**
 * Created by hmxbanz on 2017/4/5.
 */

public class GetUserPresenter {
    private Context mContext;
    private DetailActivity mActivity;

    public GetUserPresenter(Context context){
        this.mContext=context;
        this.mActivity= (DetailActivity) context;
    }

    public void init() {
    }
}
