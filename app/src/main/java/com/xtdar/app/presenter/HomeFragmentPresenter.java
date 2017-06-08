package com.xtdar.app.presenter;

import android.content.Context;
import android.support.design.widget.TabLayout;

import com.xtdar.app.XtdConst;
import com.xtdar.app.server.HttpException;
import com.xtdar.app.server.async.OnDataListener;
import com.xtdar.app.server.response.TagResponse;
import com.xtdar.app.view.widget.LoadDialog;

import java.util.List;

/**
 * Created by hmxbanz on 2017/4/5.
 */

public class HomeFragmentPresenter extends BasePresenter implements OnDataListener {
    private static final int GETTAGS = 1;
    private TabLayout tabLayout;

    //private ContactsActivity mActivity;
    public HomeFragmentPresenter(Context context){
        super(context);
        //mActivity = (ContactsActivity) context;
    }

    public void init(TabLayout tabLayout) {
        this.tabLayout=tabLayout;
        LoadDialog.show(mContext);
        atm.request(GETTAGS,this);
    }

    @Override
    public Object doInBackground(int requestCode, String parameter) throws HttpException {
        switch (requestCode) {
            case GETTAGS:
                return mUserAction.getTags();
        }
        return null;
    }

    @Override
    public void onSuccess(int requestCode, Object result) {
        LoadDialog.dismiss(mContext);
        switch (requestCode) {
            case GETTAGS:
                TagResponse tagResponse = (TagResponse) result;
                if (tagResponse.getCode() == XtdConst.SUCCESS) {
                    List<TagResponse.ResultEntity> entities = tagResponse.getData();
                    for (int i=0;i<=entities.size();i++) {
                        tabLayout.getTabAt(i).setText(entities.get(i).getClass_name());
                    }
                }
                break;
        }
    }
}