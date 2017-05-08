package com.xtdar.app.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.ImagePreviewActivity;
import com.xtdar.app.presenter.GetUserPresenter;

import java.io.Serializable;
import java.util.ArrayList;

import cn.hugeterry.coordinatortablayout.CoordinatorTabLayout;
import com.xtdar.app.R;
import com.xtdar.app.adapter.ViewPageAdapter;
import com.xtdar.app.view.fragment.AlubmFragment;
import com.xtdar.app.view.fragment.FriendConditionFragment;
import com.xtdar.app.view.fragment.InfoFragment;
import com.xtdar.app.view.fragment.LiftShareFragment;


public class GetUserActivity extends AppCompatActivity implements View.OnClickListener {
    private CoordinatorTabLayout mCoordinatorTabLayout;
    private int[] mImageArray, mColorArray;
    private ArrayList<Fragment> mFragments;
    private final String[] mTabTitles = {"个人资料", "择偶条件", "动态", "相册"};
    private ViewPager mViewPager;
    private TextView mTextNickName, mTextIntro;
    private Button mBtnThumbsUp, mBtnAddFavor, mBtnAddFriend, mBtnAddMessage;
    private ImageView mSelectableRoundedImageView;
    private GetUserPresenter mGetUserPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_getuser);

        initViews();
        initDatas();
        mGetUserPresenter = new GetUserPresenter(this);
        mGetUserPresenter.init();

    }

    private void initViews() {
        mCoordinatorTabLayout = (CoordinatorTabLayout) findViewById(R.id.coordinatortablayout);
        mSelectableRoundedImageView = (ImageView) findViewById(R.id.img_avator);
        mTextNickName = (TextView) findViewById(R.id.text_nickname);
        mTextIntro = (TextView) findViewById(R.id.text_intro);
        mBtnThumbsUp =(Button)findViewById(R.id.btnThumbsUp) ;
        mBtnAddFavor = (Button) findViewById(R.id.btn_add_favor);
        mSelectableRoundedImageView.setOnClickListener(this);
        mBtnAddFavor.setOnClickListener(this);
        mBtnThumbsUp.setOnClickListener(this);
        Drawable drawable = this.getResources().getDrawable(R.drawable.icon_thumbsup);
        drawable.setBounds(0,0,55,55);
        if(Build.VERSION.SDK_INT>=21)
        drawable.setTint(getResources().getColor(R.color.white));
        mBtnThumbsUp = (Button) findViewById(R.id.btnThumbsUp);
        mBtnThumbsUp.setCompoundDrawables(drawable,null,null,null);

        mBtnAddFavor = (Button) findViewById(R.id.btn_add_favor);
        Drawable drawable2 = this.getResources().getDrawable(R.drawable.icon_add_favor);
        drawable2.setBounds(0,0,50,50);
        if(Build.VERSION.SDK_INT>=21)
        drawable2.setTint(getResources().getColor(R.color.white));
        mBtnAddFavor.setCompoundDrawables(drawable2,null,null,null);

        mBtnAddFriend = (Button) findViewById(R.id.btn_add_friend);
        Drawable drawable3 = this.getResources().getDrawable(R.drawable.icon_add_friend);
        drawable3.setBounds(0,0,55,55);
        if(Build.VERSION.SDK_INT>=21)
        drawable3.setTint(getResources().getColor(R.color.white));
        mBtnAddFriend.setCompoundDrawables(drawable3,null,null,null);

        mBtnAddMessage = (Button) findViewById(R.id.btn_add_message);
        //mBtnAddMessage.setBackground(getResources().getDrawable(R.drawable.bg_button_pink));
        Drawable drawable4 = this.getResources().getDrawable(R.drawable.icon_message);
        drawable4.setBounds(0,0,50,50);
        if(Build.VERSION.SDK_INT>=21)
        drawable4.setTint(getResources().getColor(R.color.white));
        mBtnAddMessage.setCompoundDrawables(drawable4,null,null,null);

        initFragments();
        initViewPager();
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
        mViewPager = (ViewPager) findViewById(R.id.viewpage);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(new ViewPageAdapter(getSupportFragmentManager(), mFragments, mTabTitles));
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

        mTextNickName.setText("昵称：美娘");
        mTextIntro.setText("我是可以爱的人");
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
            case R.id.menu_blacklist:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.getuser_menu, menu);
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

}
