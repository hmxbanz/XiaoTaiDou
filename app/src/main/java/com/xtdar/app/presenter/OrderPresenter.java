package com.xtdar.app.presenter;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xtdar.app.Interface.IOrderView;
import com.xtdar.app.adapter.OrderAdapter;
import com.xtdar.app.model.UserList;

import com.xtdar.app.R;

import com.xtdar.app.view.activity.OrderActivity;

/**
 * Created by hmxbanz on 2017/4/5.
 */

public class OrderPresenter {
    private Context mContext;
    private IOrderView mView;
    private OrderActivity mActivity;
    private ListView mListView;
    private TextView mTextTitle;
    private OrderAdapter orderAdapter;

    public OrderPresenter(Context context) {
        this.mActivity =(OrderActivity)context;
        this.mView=(IOrderView)context;
        this.orderAdapter = new OrderAdapter(mContext);
        this.mContext=context;
    }
    public void init(){
        mView.initData();
        setListView();
};
    public void setListView() {
        mListView= (ListView) mActivity.findViewById(R.id.listview);
        orderAdapter.setmList(UserList.getData());
        orderAdapter.setOnItemButtonClick(new OrderAdapter.OnItemButtonClick() {
            @Override
            public boolean onButtonClick(int position, View view, int status) {
                Toast.makeText(mContext,String.valueOf(position),Toast.LENGTH_LONG).show();
                return false;
            }
        });
        mListView.setAdapter(orderAdapter);
    }

}
