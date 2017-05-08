package com.xtdar.app.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xtdar.app.Interface.IOrderView;
import com.xtdar.app.presenter.OrderPresenter;

import com.xtdar.app.R;

public class OrderActivity extends BaseActivity implements IOrderView,View.OnClickListener {
    private OrderPresenter orderPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initViews();
        orderPresenter =new OrderPresenter(this);
        orderPresenter.init();
    }
    public void initViews(){
        layout_back= (RelativeLayout) findViewById(R.id.layout_back);
        layout_back.setOnClickListener(this);
        mTextTitle=(TextView)findViewById(R.id.text_title);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_back:
                finish();
                break;
        }
    }

    @Override
    public void initData() {
        mTextTitle.setText("消费记录");
    }
}
