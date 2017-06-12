package com.xtdar.app.presenter;

import android.content.Context;

import com.xtdar.app.server.async.OnDataListener;
import com.xtdar.app.view.activity.SongAlbumActivity;
import com.xtdar.app.view.widget.LoadDialog;

/**
 * Created by hmxbanz on 2017/4/5.
 */

public class SongAlbumPresenter extends BasePresenter implements OnDataListener {
    private SongAlbumActivity mActivity;
    public SongAlbumPresenter(Context context){
        super(context);
        mActivity = (SongAlbumActivity) context;
    }

    public void init() {
        LoadDialog.show(mContext);
    }


}