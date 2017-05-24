package com.xtdar.app.server;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSONException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.IOException;


import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by AMing on 16/1/14.
 * Company RongCloud
 */
@SuppressWarnings("deprecation")
public class UserAction extends BaseAction {
    private final String TAG="USERACTION";
    private final String CONTENT_TYPE = "application/json";
    private final String ENCODING = "utf-8";
    private String result;
    public static UserAction instance;

    /**
     * 构造方法
     *
     * @param context 上下文
     */
    public UserAction(Context context) {
        super(context);
    }
    public static UserAction getInstance(Context context) {
        if (instance == null) {
            synchronized (UserAction.class) {
                if (instance == null) {
                    instance = new UserAction(context);
                }
            }
        }
        return instance;
    }

    /**
     * 登录: 登录成功后，会设置 Cookie，后续接口调用需要登录的权限都依赖于 Cookie。
     *
     * @param region   国家码
     * @param userName    手机号
     * @param password 密码
     * @throws HttpException
     */
//    public LoginResponse login(String region, String userName, String password) throws HttpException {
//        String uri = getURL("home/login");
//        String json = JsonMananger.beanToJson(new LoginRequest(region, userName, password));
//        Log.w(TAG, "请求的："+json);
//        Response response=null;
//        try {
//            response=OkHttpUtils
//                    .postString()
//                    .url(uri)
//                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
//                    .content(json)//.content(new Gson().toJson(new User("zhy", "123")))
//                    .build()
//                    .execute();
//            result =response.body().string();
//            Log.w(TAG, "接收的："+ result);
//        } catch (IOException e) {
//            e.printStackTrace();
//
//        }
//        LoginResponse loginResponse = null;
//        if (!TextUtils.isEmpty(result)) {
//            NLog.e("LoginResponse", result);
//            loginResponse = JsonMananger.jsonToBean(result, LoginResponse.class);
//        }
//        return loginResponse;
//    }
//    /**
//     * 注册:
//     *
//     * @param captcha   验证码
//     * @param userName    手机号
//     * @param password 密码
//     * @throws HttpException
//     */
//    public RegisterResponse register(String userName, String password,String captcha) throws HttpException {
//        String uri = getURL("home/register");
//        String json = JsonMananger.beanToJson(new RegisterRequest(userName, password,captcha));
//        Log.w(TAG, "请求的："+json);
//        Response response=null;
//        try {
//            response=OkHttpUtils
//                    .postString()
//                    .url(uri)
//                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
//                    .content(json)//.content(new Gson().toJson(new User("zhy", "123")))
//                    .build()
//                    .execute();
//            result =response.body().string();
//            Log.w(TAG, "接收的："+ result);
//        } catch (IOException e) {
//            e.printStackTrace();
//
//        }
//        RegisterResponse registerResponse = null;
//        if (!TextUtils.isEmpty(result)) {
//            NLog.e("RegisterResponse", result);
//            registerResponse = JsonMananger.jsonToBean(result, RegisterResponse.class);
//        }
//        return registerResponse;
//    }
//    //获取验证码
//    public CaptchaResponse getCaptcha(String cellPhone) throws HttpException
//    {
//        String uri = getURL("Home/GetCaptcha");
//        Response response=null;
//        try {
//            response=OkHttpUtils
//                    .get()
//                    .addParams("cellPhone",cellPhone)
//                    .url(uri)
//                    .build()
//                    .execute();
//            result =response.body().string();
//            Log.w(TAG, "接收的："+ result);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        CaptchaResponse captchaResponse = null;
//        if (!TextUtils.isEmpty(result)) {
//            NLog.e("CaptchaResponse", result);
//
//
//            try {
//                captchaResponse = JsonMananger.jsonToBean(result, CaptchaResponse.class);
//            } catch (JSONException e) {
//                NLog.d(TAG, "Get Captcha occurs JSONException e=" + e.toString());
//                return null;
//            }
//        }
//        return captchaResponse;
//
//    }
//    public CommonResponse newPassword(String userName, String password,String captcha) throws HttpException {
//        String uri = getURL("Home/FindPassword");
//        String json = JsonMananger.beanToJson(new NewPasswordRequest(userName, password,captcha));
//        Log.w(TAG, "请求的："+json);
//        Response response=null;
//        try {
//            response=OkHttpUtils
//                    .postString()
//                    .url(uri)
//                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
//                    .content(json)//.content(new Gson().toJson(new User("zhy", "123")))
//                    .build()
//                    .execute();
//            result =response.body().string();
//            Log.w(TAG, "接收的："+ result);
//        } catch (IOException e) {
//            e.printStackTrace();
//
//        }
//        CommonResponse commonResponse = null;
//        if (!TextUtils.isEmpty(result)) {
//            NLog.e("RegisterResponse", result);
//            commonResponse = JsonMananger.jsonToBean(result, CommonResponse.class);
//        }
//        return commonResponse;
//    }

}
