package com.xtdar.app.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xtdar.app.presenter.ForgetPasswordPresenter;

import com.xtdar.app.R;


public class ForgetPasswordActivity extends BaseActivity implements View.OnClickListener {
    private EditText mUserName,mCaptcha;
    private Button mBtnFindPassword;
    private ForgetPasswordPresenter mForgetPasswordPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        initViews();
        mForgetPasswordPresenter = new ForgetPasswordPresenter(this);
        mForgetPasswordPresenter.init();
    }

    private void initViews() {
        layout_back = (RelativeLayout) findViewById(R.id.layout_back);
        layout_back.setOnClickListener(this);
        txtTitle = (TextView) findViewById(R.id.text_title);
        txtTitle.setText("找回密码");
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
