/**
 * @(#) SmsReceiver.java Created on 2012-7-9
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute;

import com.aspire.android.test.PreferencesManager;
import com.aspire.android.test.execute.NameConstants.SharedPrefConstants;
import com.aspire.android.test.execute.ui.PrefsActivity;
import com.aspire.android.test.log.RunLogger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;

/**
 * The class <code>SmsReceiver</code>
 * 
 * @author likai
 * @version 1.0
 */
public class SmsReceiver extends BroadcastReceiver {

    private RunLogger runLogger = RunLogger.getInstance();
    
    /**
     *  声明静态字符串,并使用android.provider.Telephony.SMS_RECEIVED作为Action为短信的依据 
     */
    private static final String mACTION = "android.provider.Telephony.SMS_RECEIVED";

    /**
     * 存储配置信息
     */
    private SharedPreferences preferences;
    private PreferencesManager preferencesManager = PreferencesManager.getInstance();
    
    private Editor editor;
    
    /**
     * (non-Javadoc)
     * 
     * @see android.content.BroadcastReceiver#onReceive(android.content.Context,
     *      android.content.Intent)
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        preferences = preferencesManager.getPreferences();
        editor = preferences.edit();
        String smsControlNumber = preferences.getString(PrefsActivity.SMS_CONTROAL_NUMBER, null);
        if (intent.getAction().equals(mACTION) && smsControlNumber != null) {
            /* 接收由Intent传来的数据 */
            Bundle bundle = intent.getExtras();
            /* 判断Intent是有资料 */
            if (bundle != null) {
                /*
                 * pdus为 android内建短信参数 identifier 透过bundle.get("")并传一个包含pdus的对象
                 */
                Object[] myOBJpdus = (Object[]) bundle.get("pdus");
                /* 建构短信对象array,并依据收到的对象长度来建立array的大小 */
                SmsMessage[] messages = new SmsMessage[myOBJpdus.length];
                for (int i = 0; i < myOBJpdus.length; i++) {
                    messages[i] = SmsMessage
                            .createFromPdu((byte[]) myOBJpdus[i]);
                }

                /* 将送来的短信合并自定义信息于StringBuilder当中 */
                for (SmsMessage currentMessage : messages) {
                    /* 来讯者的电话号码 */
                    String sourceMobile = currentMessage.getDisplayOriginatingAddress();
                    /* 取得传来讯息的BODY */
                    String smsContent = currentMessage.getDisplayMessageBody();

                    runLogger.debug(SmsReceiver.class, "smsmobileNumber is " + sourceMobile + ", smsContent is" + smsContent);
                    if(sourceMobile.startsWith("+86")){
                        sourceMobile = sourceMobile.substring(3);
                    }else if(sourceMobile.startsWith("86")){
                        sourceMobile = sourceMobile.substring(2);
                    }
                    if (sourceMobile.equals(smsControlNumber)){
                        smsControlAction(smsContent);
                    }
                    
                }
            }
        }
    }
    public void smsControlAction(String content){
        int id = Integer.parseInt(content);
        switch(id){
            case 1:
                editor.putBoolean(SharedPrefConstants.MAN_UPDATE_SCRIPT, true);
                editor.commit();
                break;
            case 2:
                editor.putBoolean(SharedPrefConstants.MAN_UPDATE_TASK, true);
                editor.commit();
                break;
        }
    }
}
