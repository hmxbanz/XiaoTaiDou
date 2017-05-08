package com.xtdar.app.view.fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.xtdar.app.common.PhotoUtils;
import com.xtdar.app.view.activity.AlbumActivity;
import com.xtdar.app.view.activity.SettingActivity;
import com.xtdar.app.view.activity.FavorActivity;
import com.xtdar.app.view.activity.UpdateActivity;
import com.xtdar.app.view.widget.BottomMenuDialog;
import com.xtdar.app.view.widget.LoadDialog;
import com.xtdar.app.view.widget.SelectableRoundedImageView;
import com.xtdar.app.widget.ProgressBar.MaterialProgressBar;

import com.xtdar.app.R;


/**
 * Created by AMing on 16/6/21.
 * Company RongCloud
 */
public class MineFragment extends Fragment implements View.OnClickListener {
    private static final int COMPAREVERSION = 54;
    public static final String SHOWRED = "SHOWRED";
    public static MineFragment mFragment = null;
    private RelativeLayout mLayoutFavor,mLayoutFriend,mLayoutMsg,mLayoutHome;
    private LinearLayout mLayoutUpdate,mLayoutFriendCondition,mLayoutVisitRecord,mLayoutVisitedByMe,mLayoutOrder,
            mLayoutConfig,mlayoutCertification,mLayoutAblum;
    private View mView;

    private SelectableRoundedImageView mImageView;
    private ImageView mImageSetting;
    private PhotoUtils photoUtils;
    private BottomMenuDialog dialog;
    public static final int REQUEST_CODE_ASK_PERMISSIONS = 101;
    private Uri selectUri;
    private MaterialProgressBar progressBar;

    public static MineFragment getInstance() {
        if (mFragment == null) {
            mFragment = new MineFragment();
        }
        return mFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_mine, null);
        initViews();
        setPortraitChangeListener();

//        initData();
//        BroadcastManager.getInstance(getActivity()).addAction(SealConst.CHANGEINFO, new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                String userid = sp.getString("loginid", "");
//                String username = sp.getString("loginnickname", "");
//                String userportrait = sp.getString("loginPortrait", "");
//                mName.setText(username);
//                ImageLoader.getInstance().displayImage(TextUtils.isEmpty(userportrait) ? RongGenerate.generateDefaultAvatar(username, userid) : userportrait, imageView, App.getOptions());
//            }
//        });
//        compareVersion();
        return mView;
    }

    private void initViews() {
        mImageSetting = (ImageView) mView.findViewById(R.id.img_setting);
        mImageSetting.setOnClickListener(this);
        mImageView = (SelectableRoundedImageView) mView.findViewById(R.id.img_avator);
        mImageView.setOnClickListener(this);

        mLayoutFriendCondition= (LinearLayout) mView.findViewById(R.id.layout_friend_condition);
        mLayoutFriendCondition.setOnClickListener(this);

        mLayoutFavor= (RelativeLayout) mView.findViewById(R.id.layout_favor);
        mLayoutFavor.setOnClickListener(this);
        mLayoutVisitRecord= (LinearLayout) mView.findViewById(R.id.layout_visit_record);
        mLayoutVisitRecord.setOnClickListener(this);

        mLayoutAblum= (LinearLayout) mView.findViewById(R.id.layout_album);
        mLayoutAblum.setOnClickListener(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //BroadcastManager.getInstance(getActivity()).destroy(SealConst.CHANGEINFO);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.img_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.img_avator:
                showPhotoDialog();
                break;
//            case R.id.layout_update:
//                startActivity(new Intent(getActivity(), UpdateActivity.class));
//                break;
            case R.id.layout_favor:
                startActivity(new Intent(getActivity(), FavorActivity.class));
                break;
            case R.id.layout_album:
                startActivity(new Intent(getActivity(), AlbumActivity.class));
                break;
        }
    }

    /**
     * 弹出底部框
     */
    @TargetApi(23)
    private void showPhotoDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        dialog = new BottomMenuDialog(getContext());
        dialog.setConfirmListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                if (Build.VERSION.SDK_INT >= 23) {
                    int checkPermission = getContext().checkSelfPermission(Manifest.permission.CAMERA);
                    if (checkPermission != PackageManager.PERMISSION_GRANTED) {
                        if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                            requestPermissions(new String[] {Manifest.permission.CAMERA}, REQUEST_CODE_ASK_PERMISSIONS);
                        } else {
                            new AlertDialog.Builder(getContext())
                                    .setMessage("您需要在设置里打开相机权限。")
                                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            requestPermissions(new String[] {Manifest.permission.CAMERA}, REQUEST_CODE_ASK_PERMISSIONS);
                                        }
                                    })
                                    .setNegativeButton("取消", null)
                                    .create().show();
                        }
                        return;
                    }
                }
                photoUtils.takePicture(getActivity());
            }
        });
        dialog.setMiddleListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                photoUtils.selectPicture(getActivity());
            }
        });
        dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PhotoUtils.INTENT_CROP:
            case PhotoUtils.INTENT_TAKE:
            case PhotoUtils.INTENT_SELECT:
                photoUtils.onActivityResult(getActivity(), requestCode, resultCode, data);
                break;
        }
    }

    private void setPortraitChangeListener() {
        photoUtils = new PhotoUtils(new PhotoUtils.OnPhotoResultListener() {
            @Override
            public void onPhotoResult(Uri uri) {
                if (uri != null && !TextUtils.isEmpty(uri.getPath())) {
                    //selectUri = uri;
                    LoadDialog.show(getContext());
                    //request(GET_QI_NIU_TOKEN);
                }
            }

            @Override
            public void onPhotoCancel() {

            }
        });
    }

}
