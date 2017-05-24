package com.xtdar.app.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xtdar.app.R;
import com.xtdar.app.common.NToast;
import com.xtdar.app.presenter.DevicePresenter;
import com.xtdar.app.presenter.SettingPresenter;
import com.xtdar.app.widget.DialogWithYesOrNoUtils;

import java.io.File;


public class DeviceActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout mLayoutAbout,mLayoutClear,mLayoutFeeback,mLayoutLogoff;
    private DevicePresenter mDevicePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        initViews();
        mDevicePresenter=new DevicePresenter(this);
        mDevicePresenter.init();
    }
    public void initViews(){
        layout_back= (RelativeLayout) findViewById(R.id.layout_back);
        layout_back.setOnClickListener(this);

        mTextTitle =(TextView) findViewById(R.id.text_title);
        mTextTitle.setText("设备");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_back:
                finish();
                break;
        }
    }
}
