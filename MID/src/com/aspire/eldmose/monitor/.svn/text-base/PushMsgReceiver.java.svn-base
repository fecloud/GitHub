package com.aspire.eldmose.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.aspire.eldmose.database.DBManager;

public class PushMsgReceiver extends BroadcastReceiver{

	public final static String ACTION_PUSH_NOTIFY = "com.aspire.mose.PUSH_MESSAGE_NOTIFY.android.mid";

    public final static String EXTRA_MESSAGE = "message";

	private static final String TAG = "PushMsgReceiver";
	
    private final DBManager mQueryMsisManager;
    
    public PushMsgReceiver(DBManager mQueryMsisManager){
    	this.mQueryMsisManager = mQueryMsisManager;
    }
    
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if(intent.getAction().equals(ACTION_PUSH_NOTIFY)){
			dealPushData(intent);
		}
	}

	//处理数据,分发handler
    private void dealPushData(Intent intent) {
    	Bundle bundle = intent.getExtras();
    	if(null != bundle){
    		String pushData = bundle.getString(EXTRA_MESSAGE);
    		Log.v(TAG, pushData);
		    PushObject push = PushFactory.getPushObject(pushData);
			if (push != null) {
				mQueryMsisManager.mQueryMsisHandler.obtainMessage(push.smsType, push).sendToTarget();
			}
		}
    }
}
