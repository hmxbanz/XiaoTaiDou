package com.xtdar.app.presenter;

import android.content.Context;
import android.widget.TextView;

import com.xtdar.app.XtdConst;
import com.xtdar.app.loader.GlideImageLoader;
import com.xtdar.app.server.HttpException;
import com.xtdar.app.server.response.UserInfoResponse;
import com.xtdar.app.view.activity.MeActivity;
import com.xtdar.app.view.activity.SettingActivity;
import com.xtdar.app.view.widget.SelectableRoundedImageView;


/**
 * Created by hmxbanz on 2017/4/5.
 */
public class MePresenter extends BasePresenter {
    private static final int GETINFO = 1;
    MeActivity mActivity;
    private TextView nickName;
    private SelectableRoundedImageView avator;
    private GlideImageLoader glideImageLoader;
    public MePresenter(Context context) {
        super(context);
        this.mActivity =(MeActivity)context;
        glideImageLoader=new GlideImageLoader();

    }

    public void init(SelectableRoundedImageView selectableRoundedImageView, TextView nickName) {
        this.avator=selectableRoundedImageView;
        this.nickName=nickName;
        //mView.initData();
        atm.request(GETINFO, this);
    }

    @Override
    public Object doInBackground(int requestCode, String parameter) throws HttpException {
        return mUserAction.getInfo();
    }

    @Override
    public void onSuccess(int requestCode, Object result) {
        UserInfoResponse userInfoResponse=(UserInfoResponse)result;
        if (userInfoResponse.getCode() == XtdConst.SUCCESS) {
            UserInfoResponse.ResultEntity entity=userInfoResponse.getData();
            glideImageLoader.displayImage(mContext,XtdConst.SERVERURI+entity.getImg_path(),this.avator);
            this.nickName.setText(entity.getNick_name());
        }
    }
}
