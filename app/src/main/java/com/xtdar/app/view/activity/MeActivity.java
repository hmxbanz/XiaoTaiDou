package com.xtdar.app.view.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xtdar.app.R;
import com.xtdar.app.common.PhotoUtils;
import com.xtdar.app.presenter.MePresenter;
import com.xtdar.app.view.widget.BottomMenuDialog;
import com.xtdar.app.view.widget.LoadDialog;
import com.xtdar.app.view.widget.SelectableRoundedImageView;


public class MeActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout mLayoutAbout,mLayoutClear, mLayoutModifyPass;
    private MePresenter mMePresenter;

    private PhotoUtils photoUtils;
    private BottomMenuDialog dialog;
    public static final int REQUEST_CODE_ASK_PERMISSIONS = 101;
    private Uri selectUri;
    private SelectableRoundedImageView selectableRoundedImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        initViews();
        mMePresenter =new MePresenter(this);
        mMePresenter.init();
        setPortraitChangeListener();
    }
    public void initViews(){
        layout_back= (RelativeLayout) findViewById(R.id.layout_back);
        layout_back.setOnClickListener(this);

        mLayoutModifyPass = (LinearLayout) findViewById(R.id.layout_modify_pass);
        mLayoutModifyPass.setOnClickListener(this);

        mTextTitle =(TextView) findViewById(R.id.text_title);
        mTextTitle.setText("资料");
        selectableRoundedImageView = (SelectableRoundedImageView) findViewById(R.id.img_avator);
        selectableRoundedImageView.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.img_avator:
                showPhotoDialog();
                break;
            case R.id.layout_modify_pass:
                startActivity(new Intent(this,ForgetPasswordActivity.class));
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

        dialog = new BottomMenuDialog(this);
        dialog.setConfirmListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                if (Build.VERSION.SDK_INT >= 23) {
                    int checkPermission = checkSelfPermission(Manifest.permission.CAMERA);
                    if (checkPermission != PackageManager.PERMISSION_GRANTED) {
                        if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                            requestPermissions(new String[] {Manifest.permission.CAMERA}, REQUEST_CODE_ASK_PERMISSIONS);
                        } else {
                            new AlertDialog.Builder(MeActivity.this)
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
                photoUtils.takePicture(MeActivity.this);
            }
        });
        dialog.setMiddleListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                photoUtils.selectPicture(MeActivity.this);
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
                photoUtils.onActivityResult(this, requestCode, resultCode, data);
                break;
        }
    }

    private void setPortraitChangeListener() {
        photoUtils = new PhotoUtils(new PhotoUtils.OnPhotoResultListener() {
            @Override
            public void onPhotoResult(Uri uri) {
                if (uri != null && !TextUtils.isEmpty(uri.getPath())) {
                    selectUri = uri;
                    LoadDialog.show(MeActivity.this);
                    //request(GET_QI_NIU_TOKEN);
                }
            }

            @Override
            public void onPhotoCancel() {

            }
        });
    }
}
