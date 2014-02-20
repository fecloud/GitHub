package com.aspire.mpy;

import java.util.Hashtable;

import com.aspire.mpy.util.Tools;

import android.app.Application;
import android.content.Context;

public class MpyApp extends Application{

	public static Hashtable<String, String> ERROR = new  Hashtable<String, String>(); //保存错误信息
	
	public static Hashtable<String, String> MPY_ERROR = new  Hashtable<String, String>(); //保存错误信息
	
	public static Context context;
	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
		Tools.insertError(context);
		Tools.insertMPYError(context);
	}
}
