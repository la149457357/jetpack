package com.wdx.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.telephony.TelephonyManager;

/**
 * 
 * 类名称：SystemUtil 
 * 类描述： 系统操作工具类  
 * 创建人：谢明峰
 * 创建时间：2014-12-23 下午2:24:12
 * @version
 * 
 */
public class SystemUtil {

	// 打电话
	public static void callPhone(Activity context, String phoneNumber) {

		Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: "+ phoneNumber));
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

	public static String getAppVersionName(Activity activity) {

		PackageInfo info = null;

		try {
			info = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return info.versionName;
	}
	
	public static String getAppVersionName(Context context) {

		PackageInfo info = null;

		try {
			info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return info.versionName;
	}
	
	public static String getDeviceNO(Context context){
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
	}

}
