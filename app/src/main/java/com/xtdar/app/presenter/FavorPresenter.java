package com.xtdar.app.presenter;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.xtdar.app.Interface.IFavorView;
import com.xtdar.app.adapter.FavorAdapter;
import com.xtdar.app.model.UserList;
import com.xtdar.app.view.activity.FavorActivity;

import com.xtdar.app.R;

/**
 * Created by hmxbanz on 2017/4/5.
 */

public class FavorPresenter {
    Context mContext;
    IFavorView mView;
    FavorActivity mActivity;
    ListView mListView;
    private FavorAdapter mFavorAdapter;

    public FavorPresenter(Context context) {
        this.mView=(IFavorView)context;
        mActivity= (FavorActivity) context;
        mContext=context;
    }

    public void init(){
        mView.initData();
        setListView();
    };

    public void setListView() {
        mListView=(ListView) mActivity.findViewById(R.id.listview);
        mFavorAdapter = new FavorAdapter(mContext);
        mFavorAdapter.setmList(UserList.getData());
        mFavorAdapter.setOnItemButtonClick(new FavorAdapter.OnItemButtonClick() {
            @Override
            public boolean onButtonClick(int position, View view, int status) {
                Toast.makeText(mContext,String.valueOf(position),Toast.LENGTH_LONG).show();
                return false;
            }
        });
        mListView.setAdapter(mFavorAdapter);
    }

}
