package com.xtdar.app.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.ImagePreviewActivity;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.xtdar.app.adapter.RecyclerViewAdapter;
import com.xtdar.app.model.UserList;
import com.xtdar.app.presenter.GetUserPresenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.hugeterry.coordinatortablayout.CoordinatorTabLayout;
import com.xtdar.app.R;
import com.xtdar.app.video.SampleListener;
import com.xtdar.app.view.fragment.AlubmFragment;
import com.xtdar.app.view.fragment.FriendConditionFragment;
import com.xtdar.app.view.fragment.InfoFragment;
import com.xtdar.app.view.fragment.LiftShareFragment;


public class DetailActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener,View.OnClickListener {
    public static List<?> images=new ArrayList<>();
    private RecyclerView recycleView;
    private RecyclerViewAdapter dataAdapter;
    public ScrollView scrollView;
    private View view;
    private GridLayoutManager gridLayoutManager;

    ///////////////////////////
    private CoordinatorTabLayout mCoordinatorTabLayout;
    private int[] mImageArray, mColorArray;
    private ArrayList<Fragment> mFragments;
    private final String[] mTabTitles = {"个人资料", "择偶条件", "动态", "相册"};
    private ViewPager mViewPager;
    private TextView mTextNickName, mTextIntro;
    private Button mBtnThumbsUp, mBtnAddFavor, mBtnAddFriend, mBtnAddMessage;
    private ImageView mSelectableRoundedImageView;
    private GetUserPresenter mGetUserPresenter;
    private StandardGSYVideoPlayer videoPlayer;
    private OrientationUtils orientationUtils;
    private boolean isPlay;
    private boolean isPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail);

        initViews();
        //initDatas();
        mGetUserPresenter = new GetUserPresenter(this);
        mGetUserPresenter.init();

        recycleView= (RecyclerView) findViewById(R.id.recyclerView);
        gridLayoutManager=new GridLayoutManager(this,2);
        recycleView.setLayoutManager(gridLayoutManager);
        dataAdapter = new RecyclerViewAdapter(UserList.getData(), this);
        dataAdapter.setFooterView(LayoutInflater.from(this).inflate(R.layout.recyclerview_footer,null));
        recycleView.setAdapter(dataAdapter);
        recycleView.setNestedScrollingEnabled(false);
        if(Build.VERSION.SDK_INT>=23)
            recycleView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (gridLayoutManager.findLastCompletelyVisibleItemPosition()==(UserList.getData().size()-1))
                    {}
                }
            });
        //recycleView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.HORIZONTAL));
        dataAdapter.setOnItemClickListener(this);


        AppBarLayout app_bar_layout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String url = "http://baobab.wdjcdn.com/14564977406580.mp4";
        videoPlayer= (StandardGSYVideoPlayer) findViewById(R.id.detail_player);
        videoPlayer.setUp(url, false, null, "");
        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.xxx1);
        videoPlayer.setThumbImageView(imageView);

        //resolveNormalVideoUI();

        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, videoPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);

        videoPlayer.setIsTouchWiget(true);
        //detailPlayer.setIsTouchWigetFull(false);
        //关闭自动旋转
        videoPlayer.setRotateViewAuto(false);
        videoPlayer.setLockLand(false);
        videoPlayer.setShowFullAnimation(false);
        videoPlayer.setNeedLockFull(true);
        //detailPlayer.setOpenPreView(false);
        videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                orientationUtils.resolveByClick();

                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                videoPlayer.startWindowFullscreen(DetailActivity.this, true, true);
            }
        });

        videoPlayer.setStandardVideoAllCallBack(new SampleListener() {
            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
                //开始播放了才能旋转和全屏
                orientationUtils.setEnable(true);
                isPlay = true;
            }

            @Override
            public void onAutoComplete(String url, Object... objects) {
                super.onAutoComplete(url, objects);
            }

            @Override
            public void onClickStartError(String url, Object... objects) {
                super.onClickStartError(url, objects);
            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {
                super.onQuitFullscreen(url, objects);
                if (orientationUtils != null) {
                    orientationUtils.backToProtVideo();
                }
            }
        });

        videoPlayer.setLockClickListener(new LockClickListener() {
            @Override
            public void onClick(View view, boolean lock) {
                if (orientationUtils != null) {
                    //配合下方的onConfigurationChanged
                    orientationUtils.setEnable(!lock);
                }
            }
        });


    }

    private void initViews() {
        //mCoordinatorTabLayout = (CoordinatorTabLayout) findViewById(R.id.coordinatortablayout);

        //initFragments();
        //initViewPager();
    }
    private void initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(InfoFragment.getInstance());
        mFragments.add(FriendConditionFragment.getInstance());
        for (String title : mTabTitles) {
            // mFragments.add(LiftShareFragment.getInstance(title));
            // break;
        }
        mFragments.add(LiftShareFragment.getInstance("个人资料"));

        mFragments.add(AlubmFragment.getInstance());

    }
    private void initViewPager() {
//        mViewPager = (ViewPager) findViewById(R.id.viewpage);
//        mViewPager.setOffscreenPageLimit(4);
//        mViewPager.setAdapter(new ViewPageAdapter(getSupportFragmentManager(), mFragments, mTabTitles));
    }
    private void initDatas() {
        mImageArray = new int[]{
                R.drawable.bg_getuser1,
                R.drawable.bg_getuser1,
                R.drawable.bg_getuser1,
                R.drawable.bg_getuser1
        };
        mColorArray = new int[]{
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_orange_light
        };

        mCoordinatorTabLayout.setTitle("")
                .setBackEnable(true)
                .setImageArray(mImageArray, mColorArray)
                .setupWithViewPager(mViewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_avator:
                ArrayList<ImageInfo> imageInfo=new ArrayList<>();
                ImageInfo img=new ImageInfo();
                img.setImageViewWidth(5);
                img.setImageViewHeight(5);
                img.setImageViewX(200);
                img.setImageViewY(200);
                img.setBigImageUrl("http://www.xtdar.com//Images/User/2017/02/02/2017020223391971_b.jpg");
                img.setThumbnailUrl("http://www.xtdar.com/Images/User/2017/02/02/2017020223391971_s.jpg");

                imageInfo.add(img);

                NineGridView.setImageLoader(new GlideImageLoader());

                Intent intent = new Intent(this, ImagePreviewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(ImagePreviewActivity.IMAGE_INFO, (Serializable) imageInfo);
                bundle.putInt(ImagePreviewActivity.CURRENT_ITEM, 0);
                intent.putExtras(bundle);
                this.startActivity(intent);
                ((Activity) this).overridePendingTransition(0, 0);
                break;
            case R.id.layout_register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.text_forget_password:
                startActivity(new Intent(this,ForgetPasswordActivity.class));
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_report:
                startActivity(new Intent(this,FeedbackActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /** Glide 加载 */
    private class GlideImageLoader implements NineGridView.ImageLoader {
        @Override
        public void onDisplayImage(Context context, ImageView imageView, String url) {
            Glide.with(context).load(url)//
                    .placeholder(R.drawable.ic_default_color)//
                    .error(R.drawable.ic_default_color)//
                    .diskCacheStrategy(DiskCacheStrategy.ALL)//
                    .into(imageView);
        }

        @Override
        public Bitmap getCacheImage(String url) {
            return null;
        }
    }

    private void resolveNormalVideoUI() {
        //增加title
        videoPlayer.getTitleTextView().setVisibility(View.GONE);
        videoPlayer.getTitleTextView().setText("测试视频");
        videoPlayer.getBackButton().setVisibility(View.GONE);
    }
    @Override
    public void onItemClick(int position, String data) {
        startActivity(new Intent(this, Main2Activity.class));
        Toast.makeText(this,"你点击了位置："+String.valueOf(position)+"-标题："+data,Toast.LENGTH_SHORT).show();
    }


}
