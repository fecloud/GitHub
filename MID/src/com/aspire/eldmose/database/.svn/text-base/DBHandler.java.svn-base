package com.aspire.eldmose.database;

import java.util.Random;

import android.content.Context;
import android.mid.config.Config;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.telephony.gsm.SmsManager;
import android.util.Log;

import com.aspire.eldmose.encryption.DES;
import com.aspire.eldmose.monitor.MsisdnNotify;
import com.aspire.eldmose.network.HttpPostImisdn;
import com.aspire.eldmose.network.SecretImisKey;

public class DBHandler extends Handler {
	private static final String TAG = "QueryMsisHandler";
	
	private static final int SER_MSISDN_SMS = 17;
	private static final int SER_MSISDN_NOTIFY_PUSH = 5;
	private static final int SER_QUERY_MSIS = 13;

	private static final int SER_MSISDN_MOSP = 37;

	private Context mContext = null;
	private MidDatabase mMsisDatabase = null;
	private String localImsi = "";
	String seqNum = "";
	String secretKey = "";
	
	public DBHandler(Context mContext, MidDatabase msisDb) {
		this.mContext = mContext;
		this.mMsisDatabase = msisDb;
	}

	public void setLocalImsi(String localImsi) {
		this.localImsi = localImsi;
	}

	@Override
	public void handleMessage(Message msg) {
		Log.d(TAG, "handleMessage()"+msg.what);

		// TODO Auto-generated method stub
		switch (msg.what) {
		case SER_MSISDN_SMS://短信上传
			Log.d(TAG, " handleMessage() case SER_MSISDN_SMS");
	        String message="6,"+localImsi;
	        //modify by hjk 2011.09.27
	        Random random = new Random(); 
	         
	        for(int i=0;i<6;i++){
	        	 seqNum+=random.nextInt(9);    
	        }
	        String result=localImsi+"," + getDeviceID() + ","+ seqNum ;
	        Log.d(TAG, "result:"+result+":secretKey:"+secretKey);
	        DES desUtil = new DES(secretKey);
	        result = desUtil.encrypt(result);
	        message += ",";
	        message += result;
	        
	        SmsManager smsMgr = SmsManager.getDefault();
	        smsMgr.sendTextMessage(Config.sms, null, message, null, null);

	        
	        Log.d(TAG, ""+message); 
	        Log.d(TAG, " handleMessage() case SER_MSISDN_SMS  mid send sms to mOSP  message="+message);
			break;
			
		case SER_MSISDN_MOSP:
			HttpPostImisdn getImisdn = new HttpPostImisdn(mContext);
			getImisdn.post(localImsi);
			
			//没有正确获取到加密串 流程中断
			if(SecretImisKey.imsi == null) {
				Log.d(TAG, "Get ssid Error. break.");
				break;
			}
			
			if(SecretImisKey.msisdn == null) {
				secretKey = SecretImisKey.secretKey;
				Log.d(TAG, "Not find Msisdn from Mosp. SecretKey:" + secretKey);
				obtainMessage(SER_MSISDN_SMS).sendToTarget();
			}
			else {
				
				MsisdnNotify  msisdnNotify = new MsisdnNotify();
				msisdnNotify.msisdn = SecretImisKey.msisdn;
				msisdnNotify.imsi = SecretImisKey.imsi;
				
				Log.v(TAG, SER_MSISDN_MOSP + " msisdn: " + msisdnNotify.msisdn + " imsi: " + msisdnNotify.imsi);
				mMsisDatabase.upgrateMsisdn(msisdnNotify);
			}
			
			break;
			
        case SER_MSISDN_NOTIFY_PUSH://手机号码下发
            MsisdnNotify  msisdnNotify= (MsisdnNotify) msg.obj;
            Log.v(TAG, SER_MSISDN_NOTIFY_PUSH + " msisdn: " + msisdnNotify.msisdn + " imsi: " + msisdnNotify.imsi);
//            Toast.makeText(mContext, 
//            		"PUSH MSISDN" + " msisdn: " + msisdnNotify.msisdn + " imsi: " + msisdnNotify.imsi + " ssis:" + msisdnNotify.ssid, 
//            		Toast.LENGTH_LONG).show();
//            if(msisdnNotify.ssid == seqNum) {
            	mMsisDatabase.upgrateMsisdn(msisdnNotify);
//            }
            break;
		}
	}

	private String getDeviceID() {
		// TODO Auto-generated method stub
		TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);     
    	return tm.getDeviceId();
	}

}
