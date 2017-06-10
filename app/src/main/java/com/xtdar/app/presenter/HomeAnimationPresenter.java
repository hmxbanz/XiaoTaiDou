package com.xtdar.app.presenter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.xtdar.app.R;
import com.xtdar.app.XtdConst;
import com.xtdar.app.adapter.ClassListAnimationAdapter;
import com.xtdar.app.adapter.HomeRecommendAdapter;
import com.xtdar.app.server.HttpException;
import com.xtdar.app.server.async.OnDataListener;
import com.xtdar.app.server.response.AdResponse;
import com.xtdar.app.server.response.ClassListResponse;
import com.xtdar.app.server.response.RecommendResponse;
import com.xtdar.app.view.widget.LoadDialog;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hmxbanz on 2017/4/5.
 */

public class HomeAnimationPresenter extends BasePresenter implements OnDataListener {
    private static final int GETADS = 1;
    private static final int GETANIMATION = 2;
    private Banner Banner;
    private RecyclerView recycleView;
    private GridLayoutManager gridLayoutManager;
    private ClassListAnimationAdapter dataAdapter;
    private List<ClassListResponse.DataBean> list;

    public HomeAnimationPresenter(Context context){
        super(context);
        //mActivity = (ContactsActivity) context;
        dataAdapter = new ClassListAnimationAdapter(mContext);
    }

    public void init(RecyclerView recycleView) {
        this.recycleView=recycleView;
        gridLayoutManager=new GridLayoutManager(mContext,2);
        recycleView.setLayoutManager(gridLayoutManager);

        LoadDialog.show(mContext);
        atm.request(GETADS,this);

    }

    @Override
    public Object doInBackground(int requestCode, String parameter) throws HttpException {
        switch (requestCode) {
            case GETADS:
                return mUserAction.getAds();
            case GETANIMATION:
                return mUserAction.getAnimations();
        }
        return null;
    }

    @Override
    public void onSuccess(int requestCode, Object result) {
        LoadDialog.dismiss(mContext);
        switch (requestCode) {
            case GETADS:
                AdResponse adResponse = (AdResponse) result;
                if (adResponse.getCode() == XtdConst.SUCCESS) {
                    List<String> images = new ArrayList<>();
                    for (String s : adResponse.getData()) {
                        s=XtdConst.IMGURI+s;
                        images.add(s);
                    }
                    dataAdapter.setAdImages(images);
                    atm.request(GETANIMATION,this);
                }
                break;
            case GETANIMATION:
                ClassListResponse classListResponse = (ClassListResponse) result;
                if (classListResponse.getCode() == XtdConst.SUCCESS) {
                    list=classListResponse.getData();
                    //设置列表
                    dataAdapter.setHeaderView(LayoutInflater.from(mContext).inflate(R.layout.recyclerview_header,null));
                    dataAdapter.setListItems(list);
                    //dataAdapter.setFooterView(LayoutInflater.from(mContext).inflate(R.layout.recyclerview_footer,null));
                    recycleView.setAdapter(dataAdapter);
                    recycleView.setNestedScrollingEnabled(false);
                }
                break;
        }
    }
}