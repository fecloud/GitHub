package com.aspire.eldmose.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Tools {
	
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
		 * 检测当前网络类型
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
}
