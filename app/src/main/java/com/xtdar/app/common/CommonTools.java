package com.xtdar.app.common;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.text.TextUtils;
import android.view.ViewConfiguration;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hmxbanz on 2017/2/17.
 */

public class CommonTools {
    /**
     * 获取设备内容
     */
    public static String getDeviceInfo(Context context) {
        try{
            org.json.JSONObject json = new org.json.JSONObject();
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);

            String device_id = tm.getDeviceId();

            android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            String mac = wifi.getConnectionInfo().getMacAddress();
            json.put("mac", mac);

            if( TextUtils.isEmpty(device_id) ){
                device_id = mac;
            }
            if( TextUtils.isEmpty(device_id) ){
                device_id = android.provider.Settings.Secure.getString(context.getContentResolver(),android.provider.Settings.Secure.ANDROID_ID);
            }

            json.put("device_id", device_id);

            return json.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //请隐藏menu一直显示，不管手机有没有物理menu按键
    public static void setOverflowShowingAlways(Context context) {
        try {
            ViewConfiguration config = ViewConfiguration.get(context);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //时间格式化
    public static String formatDateTime(long time) {
        SimpleDateFormat mDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        if (0 == time) {
            return "";
        }
        return mDateFormat.format(new Date(time));
    }
    //检测包名的应用是否已经安装在手机
    public static boolean checkBrowser(Context context,String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(
                    packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
    //检查是否安装后第一次运行
    public static boolean isFristRun(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserConfig", context.MODE_PRIVATE);
        boolean isFirstRun = sharedPreferences.getBoolean("IsFirstRun", true);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (isFirstRun) {
            editor.putBoolean("IsFirstRun", false);
            editor.commit();
            return true;
        } else {
            return false;
        }
    }
    /**
     * 判断SDCard是否存在,并可写
     *
     * @return
     */
    public static boolean checkSDCard() {
        String  flag = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(flag);
    }
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
    /**
     * 判断 一个字段的值否为空
     *
     * @param s
     * @return
     * @author Michael.Zhang 2013-9-7 下午4:39:00
     */

    public static boolean isNull(String s) {
        if (null == s || s.equals("") || s.equalsIgnoreCase("null")) {
            return true;
        }
        return false;
    }
}
