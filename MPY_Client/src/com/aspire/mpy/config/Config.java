package com.aspire.mpy.config;

import java.io.File;

import android.os.Environment;

public class Config {
	
	public static final int HTTP_CONNECT_TIMEOUT = 1000 * 20; // 网络连接超时时间
	
	public static final int HTTP_READ_TIMEOUT = 1000 * 20; // 读入超时时间
	
	public static final int APN_SWITCH_TIMEOUT = 20 * 1000; // APN切换超时时间时间戳
	
	public static final String URL_SERVICE = "http://119.78.1.130:8080/client";// 业务支撑平台
	
	public static final String URL_RESOURCE = "http://119.78.1.130:8080/resource"; //资源平台
	
	public static final String URL_SERVICE_GSM_GOOGLE = "http://www.google.com/loc/json";// 业务支撑平台
	
	public static final int GPS_TIME_OUT =  1000 * 90;
	
	public static final String CAMER_PIC_HEAD_PATH = Environment.getExternalStorageDirectory() + File.separator +"mpy.jpg";//拍照的照片
	
	public static interface ActivitCode{
		
		public static final int OPENGPS_RESULTCODE = 2000;
		
		public static final int ContactEdit_RESULTCODE = 1000;
		
	}
}
