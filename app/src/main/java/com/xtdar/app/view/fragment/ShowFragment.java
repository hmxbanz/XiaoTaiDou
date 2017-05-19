package com.xtdar.app.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.xtdar.app.R;
import com.xtdar.app.video.RecyclerBaseAdapter;
import com.xtdar.app.video.RecyclerItemNormalHolder;
import com.xtdar.app.video.RecyclerNormalAdapter;
import com.xtdar.app.video.VideoModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by AMing on 16/6/21.
 * Company RongCloud
 */
public class ShowFragment extends Fragment  {
    RelativeLayout layoutBack,layoutRight;
    //@BindView(R.id.list_item_recycler)
    RecyclerView videoList;

    LinearLayoutManager linearLayoutManager;

    RecyclerBaseAdapter recyclerBaseAdapter;

    List<VideoModel> dataList = new ArrayList<>();

    private View view;
    public static ShowFragment instance = null;


    public static ShowFragment getInstance() {
        if (instance == null) {
            instance = new ShowFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_show, null);
        videoList= (RecyclerView) view.findViewById(R.id.list_item_recycler);
        layoutBack= (RelativeLayout) view.findViewById(R.id.layout_back);
        layoutBack.setVisibility(View.INVISIBLE);
        TextView txtTitle= (TextView) view.findViewById(R.id.text_title);
        txtTitle.setText("秀场");
        TextView txtRight= (TextView) view.findViewById(R.id.text_right);
        txtRight.setVisibility(View.VISIBLE);
        txtRight.setTextColor(getResources().getColor(R.color.titleBlue));
        txtRight.setText("玩一把");
        resolveData();

        final RecyclerNormalAdapter recyclerNormalAdapter = new RecyclerNormalAdapter(getActivity(), dataList);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        videoList.setLayoutManager(linearLayoutManager);
        videoList.setAdapter(recyclerNormalAdapter);

        videoList.addOnScrollListener(new RecyclerView.OnScrollListener() {

            int firstVisibleItem, lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                firstVisibleItem   = linearLayoutManager.findFirstVisibleItemPosition();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                //大于0说明有播放
                if (GSYVideoManager.instance().getPlayPosition() >= 0) {
                    //当前播放的位置
                    int position = GSYVideoManager.instance().getPlayPosition();
                    //对应的播放列表TAG
                    if (GSYVideoManager.instance().getPlayTag().equals(RecyclerItemNormalHolder.TAG)
                            && (position < firstVisibleItem || position > lastVisibleItem)) {

                        //如果滑出去了上面和下面就是否，和今日头条一样
                        GSYVideoPlayer.releaseAllVideos();
                        recyclerNormalAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
        return view;
    }

    private void initViews() {

    }
    private void initMianViewPager() {

    }



    private void resolveData() {
        for (int i = 0; i < 19; i++) {
            VideoModel videoModel = new VideoModel();
            dataList.add(videoModel);
        }
        if (recyclerBaseAdapter != null)
            recyclerBaseAdapter.notifyDataSetChanged();
    }
    public boolean onBackPressed() {
        if (StandardGSYVideoPlayer.backFromWindowFull(getActivity())) {
            return true;
        }
        return false;
    }
}
