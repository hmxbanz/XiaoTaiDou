package com.xtdar.app.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xtdar.app.R;
import com.xtdar.app.presenter.RegisterPresenter;


public class RegisterActivity extends BaseActivity implements View.OnClickListener{
    private EditText mUsername, mPassword, mCaptcha;
    private Button mBtnRegister;
    private RegisterPresenter mRegisterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
        mRegisterPresenter = new RegisterPresenter(this);
        mRegisterPresenter.init();
    }

    private void initViews() {
        layout_back = (RelativeLayout) findViewById(R.id.layout_back);
        layout_back.setOnClickListener(this);
        mTextTitle = (TextView) findViewById(R.id.text_title);
        mTextTitle.setText("用户注册");
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
