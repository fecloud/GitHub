package com.aspire.eldmose.monitor;


import android.mid.config.Config;
import android.util.Log;

public class PushFactory {
    
    private static final String TAG = "PushFactory";
	private PushFactory() {
	}

	public static PushObject getPushObject(String smsStr) {

		// WapPush push = null;
            if(null == smsStr) return null;  //add by hjk 2011.09.23
            Log.d(TAG,"smsStr !=null");
			String[] strs = smsStr.split(",");
            if (strs != null) {
            	int pushType = 0;
            	try{
            		pushType = Integer.valueOf(strs[0]);
            	}catch(NumberFormatException a){
            		 Log.d(TAG,"smsStr !=null");
            		return null;
            	}
            	if(pushType == PushType.SMS_WARN){
            		Log.i(TAG,"receive data sms  type:sms warn");
            		SmsWarn warn = new SmsWarn();
            		warn.content = smsStr.subSequence(2, smsStr.length()).toString();
            		return warn;
            	}
            	if (pushType == PushType.BIND_PUSH) {
            		Log.i(TAG,"receive data sms  type:binding sms");
            		BindPush push = new BindPush();
            		push.feeMsisdn = strs[1];
            		push.status = strs[2];
            		return push;
            	} else if (pushType == PushType.CONTENT_PUSH) {
            		Log.i(TAG,"receive data sms  type:content push sms");
            		ContentPush push = new ContentPush();
            		push.contentID = strs[1];
            		Config.msiadnId=strs[1];
            		return push;
            	} else if (pushType == PushType.SUB_CHANGED_PUSH) {
            		Log.i(TAG,"receive data sms  type:sub status changed  push sms");
            		SubStatusChangedPush push = new SubStatusChangedPush();
            		push.Msisdn = strs[1];
            		push.platformCode = strs[2];
            		push.serviceCode = strs[3];
            		push.actionID = Integer.valueOf(strs[4]);
            		return push;
            	} else if (pushType == PushType.FORCE_LOGOUT)
            	{
            		Log.i(TAG,"receive data sms  type:force logout");
            	    ForceLogout logout = new ForceLogout();
            	    logout.msisdn = strs[1];
            	    logout.logoutTime = strs[2];
            	    return logout;
            	}
            	 else if (pushType == PushType.MSISDN_NOTIFY)
                 {
            		 Log.d(TAG,"pushType == PushType.MSISDN_NOTIFY");
                     MsisdnNotify msis = new MsisdnNotify(); 
                     /**IMSI卡串号*/
                     msis.imsi = strs[1];
                     /**用户手机号码*/
                     msis.msisdn = strs[2];   
                     /**随机号*/
                     msis.ssid = strs[3];
                     return msis;
                 }
            }
		return null;
	}
}
