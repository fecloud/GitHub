package com.aspire.mpy.util;

import java.io.DataInputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.aspire.mpy.Const;
import com.aspire.mpy.MpyApp;
import com.aspire.mpy.R;
import com.aspire.mpy.bean.BindInfo;
import com.aspire.mpy.bean.CardID;
import com.aspire.mpy.config.Config;
import com.aspire.mpy.provider.ProviderUtil;

public class Tools {
	
	public static void insertError(Context mContext) {
		Resources re = mContext.getResources();
		DataInputStream bis = new DataInputStream(re.openRawResource(R.raw.error));
		String txt = "";
		int pos = 0;
		try {
			do {// 插入错误信息
				txt = bis.readLine();
				if (txt != null) {
					txt = new String(txt.trim().getBytes("ISO-8859-1"), "UTF-8");
					if ((pos = txt.indexOf(":")) != -1) {
						MpyApp.ERROR.put(txt.substring(0, pos), txt.substring(pos + 1));
					} else {
						continue;
					}
				}
			} while (txt != null && !"".equals(txt));
		} catch (IOException e) {
		}
	}
	
	public static void insertMPYError(Context mContext) {
		Resources re = mContext.getResources();
		DataInputStream bis = new DataInputStream(re.openRawResource(R.raw.mpy_errot));
		String txt = "";
		int pos = 0;
		try {
			do {// 插入错误信息
				txt = bis.readLine();
				if (txt != null) {
					txt = new String(txt.trim().getBytes("ISO-8859-1"), "UTF-8");
					if ((pos = txt.indexOf(":")) != -1) {
						MpyApp.MPY_ERROR.put(txt.substring(0, pos), txt.substring(pos + 1));
					} else {
						continue;
					}
				}
			} while (txt != null && !"".equals(txt));
		} catch (IOException e) {
		}
	}
	
	public static String findError(String key){
		if(MpyApp.ERROR.size() != 0){
			return MpyApp.ERROR.get(key);
		}else{
			return null;
		}
	}
	
	public static String findMPYError(String key){
		if(MpyApp.MPY_ERROR.size() != 0){
			return MpyApp.MPY_ERROR.get(key);
		}else{
			return null;
		}
	}
	
	public static String findALLError(String key){
		if(null != findError(key)){
			return findError(key);
		}else if (null != findMPYError(key)){
			return findMPYError(key);
		}else{
			return findMPYError("-10000");
		}
	
	}
	
	/**
	 * 检测网络连接是否可用
	 * @param ctx
	 * @return true 可用; false 不可用
	 */
	public static boolean CheckNetWork(Context ctx) {
		ConnectivityManager cm = 
			(ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
		if(cm == null) {
			return false;
		}
		NetworkInfo[] netinfo = cm.getAllNetworkInfo();
		if(netinfo == null) {
			return false;
		}
		for (int i = 0; i < netinfo.length; i++) {
			if(netinfo[i].isConnected()) {
				return true;
			}
		}
		return false;
	}
	
	  public static void isLegalHttpUrl(String url)
	  {
	        int idx = url.indexOf("://");
	        if(idx==-1)
	        {
	            throw new IllegalArgumentException(url+" is not an right http url,no '://'");
	        }
	        if(!url.startsWith("http"))
	        {
	             throw new IllegalArgumentException(url+" is not an right http url,no \"http\"");
	        }


	  }
	  
	  /**
		 * 检测当前网络类�?
		 * @return 1:cmnet,2:cmwap
		 */
	    public static int checkNetWorkType(){
	        String proxyHost =  android.net.Proxy.getDefaultHost();
	        if(proxyHost != null)
	        {
	          return Const.NetworkType.CMWAP;
	        }
	        else{
	         return Const.NetworkType.CMNET;
	        }
	    }
	    
	    /**
	     * 保存或者更新用户名片的id
	     * @param mActivity
	     * @param key
	     * @param value
	     */
	    public static void saveOrUpdatempy_id(Activity mActivity ,CardID cardID){
	    	SharedPreferences preferences = mActivity.getSharedPreferences("Config",Activity.MODE_PRIVATE );
	    	Editor editor = preferences.edit();
	    	editor.putString("USER_CardID_ID", ""+ cardID.getId());
	    	editor.putString("USER_CardID_Version", ""+ cardID.getVersion());
	    	editor.commit();
	    }
	    
	    /**
	     * 保存或者更新用户名片的id
	     * @param mActivity
	     * @param key
	     * @param value
	     */
	    public static CardID get_mpy_id(Activity mActivity ){
	    	CardID cardID = null;
	    	SharedPreferences preferences = mActivity.getSharedPreferences("Config",Activity.MODE_PRIVATE );
	    	String id = preferences.getString("USER_CardID_ID", null);
	    	String version = preferences.getString("USER_CardID_Version", null);
	    	if(null != id && null != version){
	    		cardID = new CardID();
	    		cardID.setId(Integer.parseInt(id));
	    		cardID.setVersion(Integer.parseInt(version));
	    	}
	    	return cardID;
	    }
	    
	    /**
	     * 保存用户绑定信息
	     * @param mActivity
	     * @param bindInfo
	     */
	    public static void saveOrUpdateBindInfo(Activity mActivity , BindInfo bindInfo){
	    	SharedPreferences preferences = mActivity.getSharedPreferences("Config",Activity.MODE_PRIVATE );
	    	Editor editor = preferences.edit();
	    	editor.putString("BindInfo_bindStr", bindInfo.getBindStr());
	    	editor.putString("BindInfo_password", bindInfo.getPassword());
	    	editor.commit();
	    }
	    
	    /**
	     * 获取用户绑定信息
	     * @param mActivity
	     * @return
	     */
	    public static BindInfo getBindInfo(Activity mActivity){
	    	BindInfo bindInfo = null;
	    	SharedPreferences preferences = mActivity.getSharedPreferences("Config",Activity.MODE_PRIVATE );
	    	String bindStr = preferences.getString("BindInfo_bindStr", null);
	    	String password = preferences.getString("BindInfo_password", null);
	    	if(null != bindStr && null != password){
	    		bindInfo = new BindInfo();
	    		bindInfo.setBindStr(bindStr);
	    		bindInfo.setPassword(password);
	    	}
	    	return bindInfo;
	    }
	    
	    /**
	     * 设置用户是否登录
	     * @param mActivity
	     * @param isLogin
	     */
	    public static void saveUserLoginState(Activity mActivity , boolean isLogin){
	    	SharedPreferences preferences = mActivity.getSharedPreferences("Config",Activity.MODE_PRIVATE );
	    	preferences.edit().putBoolean("Login", isLogin).commit();
	    }
	    
	    /**
	     * 用户是否登录
	     * @param mActivity
	     * @return
	     */
	    public static boolean getUserLoginState(Activity mActivity){
	    	SharedPreferences preferences = mActivity.getSharedPreferences("Config",Activity.MODE_PRIVATE );
	    	return preferences.getBoolean("Login", false);
	    }
	    
	    /**
	     * 清除用户的登录信息xml
	     * @param mActivity
	     */
	    public static void user_UnRegister(Activity mActivity){
	    	SharedPreferences preferences = mActivity.getSharedPreferences("Config",Activity.MODE_PRIVATE );
	    	Editor editor = preferences.edit();
	    	editor.clear().commit();
	    }
	    
	    /**
	     * 清除用户的登录信息DB
	     * @param mActivity
	     */
	    public static void user_UnRegisterDB(Activity mActivity){
	    	ProviderUtil.cleanUserInfo(mActivity);
	    }
	    
	    /**
	     * 获取当前gps的状态
	     * @param mContext
	     * @return
	     */
	    public static boolean getGpsState(Activity mActivity){
			LocationManager locationManager = (LocationManager) mActivity .getSystemService(Context.LOCATION_SERVICE);
	        if (locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
	        	return true;
	        }else{
//	        	ViewUtil.showShortToast(mActivity, R.string.contact_opengps);
//	            Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
//	            mActivity.startActivityForResult(intent,0); //此为设置完成后返回到获取界面
	            return false;
	        }
	    }
	    
	    /**
	     * 比较时间
	     * @param time1
	     * @param time2
	     * @return
	     */
	    public static boolean mathTime(long time1 , long time2){
	    	if((time2 - time1) >= 0 && (time2 - time1) < Config.GPS_TIME_OUT){
	    		return true;
	    	}else{
	    		return false;
	    	}
	    }
}
