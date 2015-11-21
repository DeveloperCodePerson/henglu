package com.henglu.haiway.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.GpsStatus.NmeaListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.widget.LinearLayout.LayoutParams;

public class Util {
	public static void saveSharedPreferences(Context ct, String key, String val) {
		SharedPreferences sharedPreferences = ct.getSharedPreferences(key,
				Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putString(key, val);
		editor.commit();
	}

	public static void removeKeySharedPreferences(Context ct, String key) {
		SharedPreferences sharedPreferences = ct.getSharedPreferences(key,
				Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.remove(key);
		editor.commit();
	}

	public static String getSharedPreferences(Context ct, String key) {
		SharedPreferences sharedPreferences = ct.getSharedPreferences(key,
				Context.MODE_PRIVATE);
		return sharedPreferences.getString(key, null);
	}

	/**
	 * 字符串是否空
	 * 
	 */
	public static boolean isBlank(String str) {
		if (str == null)
			return true;
		if ("".equals(str.trim()) || "null".equals(str.trim()))
			return true;
		return false;
	}

	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^1[3|4|5|7|8][0-9]\\d{8}");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	public static boolean checkPassword(String pwd) {
		Pattern p = Pattern.compile("^[0-9a-zA-Z]{6,16}$");
		Matcher m = p.matcher(pwd);
		return m.matches();
	}

	public static boolean isNetworkConnected(Context act) {
		ConnectivityManager manager = (ConnectivityManager) act
				.getApplicationContext().getSystemService(
						Context.CONNECTIVITY_SERVICE);
		if (manager == null) {
			return false;
		}
		NetworkInfo networkinfo = manager.getActiveNetworkInfo();
		if (networkinfo == null || !networkinfo.isAvailable()) {
			return false;
		}
		return true;
	}

	// 二进制转换图�?
	public static Bitmap getBitmapFromByte(byte[] temp) {
		if (temp != null) {
			Bitmap bitmap = BitmapFactory.decodeByteArray(temp, 0, temp.length);
			return bitmap;
		} else {
			return null;
		}
	}

	public static boolean isAmountInput(String amount) {
		Pattern p = Pattern
				.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$");
		Matcher m = p.matcher(amount);
		return m.matches();
	}

	// 是否是金额数�?
	public static boolean isAmount(String amount) {
		Pattern p = Pattern.compile("^[0-9]*(\\.[0-9]{1,2})?$");
		Matcher m = p.matcher(amount);
		return m.matches();
	}

	/*
	 * 身份证验�?
	 */
	public static boolean isIDCard(String amount) {
		Pattern p = Pattern
				.compile("(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[Xx])$)$");
		Matcher m = p.matcher(amount);
		Pattern p1 = Pattern
				.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");
		Matcher m1 = p1.matcher(amount);
		return m.matches() || m1.matches();
	}

	/*
	 * 银行卡号验证
	 */
	public static boolean isBankCard(String cardid) {
		Pattern p = Pattern.compile("(^\\d{19}$");
		Matcher m = p.matcher(cardid);
		return m.matches();
	}

	public static File getFileByPath(String path, String filename) {
		String path1 = Environment.getExternalStorageDirectory()
				+ File.separator + Config.APP_RESOURCE_ROOT_PATH
				+ File.separator + path;
		File path2 = new File(path1);
		if (!path2.exists()) {
			path2.mkdirs();
		}
		File file = new File(path1 + File.separator + filename);
		return file;
	}

	/**
	 * @Description:把list转换为一个用逗号分隔的字符串
	 */
	public static String listToString(List list) {
		StringBuilder sb = new StringBuilder();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (i < list.size() - 1) {
					sb.append(list.get(i) + ",");
				} else {
					sb.append(list.get(i));
				}
			}
		}
		return sb.toString();
	}

	public static String refFormatNowDate() {
		Date nowTime = new Date(System.currentTimeMillis());
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String retStrFormatNowDate = sdFormatter.format(nowTime);
		return retStrFormatNowDate;
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static String addComma(String str) {
		 str = new StringBuilder(str).reverse().toString();     //先将字符串颠倒顺序  
	        String str2 = "";  
	        for(int i=0;i<str.length();i++){  
	            if(i*3+3>str.length()){  
	                str2 += str.substring(i*3, str.length());  
	                break;  
	            }  
	            str2 += str.substring(i*3, i*3+3)+",";  
	        }  
	        if(str2.endsWith(",")){  
	            str2 = str2.substring(0, str2.length()-1); 
	        }  
	        String resultStr =new StringBuilder(str2).reverse().toString();
	        return resultStr;
	}
	public static String getSplit(String str){
		 String a[] = str.split("\\.");
		 String number = a[0];
		 String number_ = a[1];
		 String  Str = addComma(number)+"."+number_;	
		return 	Str;
	}
	 public static boolean isNetworkConnected(Activity activity) {
		    Context context = activity.getApplicationContext();
	        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
	        NetworkInfo ni = cm.getActiveNetworkInfo();
	        return ni != null && ni.isConnectedOrConnecting();
	    }
}
