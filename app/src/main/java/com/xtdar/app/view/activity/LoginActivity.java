package com.xtdar.app.view.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xtdar.app.presenter.LoginPresenter;

import com.xtdar.app.R;


public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText mUsername,mPassword;
    private Button mBtnLogin;
    private RelativeLayout mLayoutRegister;
    private TextView mTextForgetPassword;
    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        mLoginPresenter = new LoginPresenter(this);
        mLoginPresenter.init();
    }

    private void initViews() {
        mTextTitle =(TextView) findViewById(R.id.text_title);
        mTextTitle.setText("用户登录");
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox);
        Drawable drawable = this.getResources().getDrawable(R.drawable.selector_checkbox);
        drawable.setBounds(0,0,50,50);
        if(Build.VERSION.SDK_INT>=21)
            drawable.setTint(getResources().getColor(R.color.mainColorBlue));
        checkBox.setCompoundDrawables(drawable,null,null,null);

        mTextForgetPassword = (TextView) findViewById(R.id.text_forget_password);
        mLayoutRegister = (RelativeLayout) findViewById(R.id.layout_register);
        mLayoutRegister.setOnClickListener(this);
        mTextForgetPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.text_forget_password:
                startActivity(new Intent(this,ForgetPasswordActivity.class));
        }
    }
}
