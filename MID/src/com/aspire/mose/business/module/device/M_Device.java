package com.aspire.mose.business.module.device;


import android.content.Context;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.aspire.mose.frame.config.Config;


public class M_Device
{
	private static String imsiId = null;
	private static String iDeviceId = null;
	
    public static String getDeviceID()
    {
    	if(null ==  Config.getInstance().getContext()){
    		return "";
    	}
    	TelephonyManager tm = (TelephonyManager) Config.getInstance().getContext().getSystemService(Context.TELEPHONY_SERVICE);     
    	if(tm == null){
    		return "";
    	}
    	iDeviceId = tm.getDeviceId();
    	if(null == iDeviceId){
    		iDeviceId= "";
    	}
    	return iDeviceId;
    }
    
    public static String getImisID(){
       //TODO 读取设备的IMEI号。	
    	
    	//临时，使用了旧mose的代码。
    	if (null == imsiId && null != Config.getInstance().getContext()) {
             TelephonyManager tm = (TelephonyManager) Config.getInstance().getContext().getSystemService(Context.TELEPHONY_SERVICE);
             if(tm!=null)
             {
            	 imsiId = tm.getSubscriberId();
             }
             
        }

    	if(imsiId!=null) 	
    	{
    		Log.d("M_Device", "M_Device = "+imsiId);
    	}
    	else
    	{
    		Log.d("M_Device", "M_Device = null");
    	}

    	if(null == imsiId){
    		imsiId = "";
    	}

    	//end 
        return imsiId;
    }
    
    /**
     * 取得本机的Mac地址
     * @return
     */
    public static String getMac(){
    	WifiManager manager = (WifiManager) Config.getInstance().getContext().getSystemService(Context.WIFI_SERVICE);
    	return manager.getConnectionInfo().getMacAddress();
    }

}
