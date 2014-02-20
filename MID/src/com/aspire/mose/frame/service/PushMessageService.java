package com.aspire.mose.frame.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.aspire.mose.frame.service.MoseService.MoseServiceBinder;

/**
 * 些类用于启动或者停止消息推送服务
 * 
 * @author oyf
 * 
 */
public final class PushMessageService {

	private static final String TAG = PushMessageService.class.getSimpleName();

	private static boolean isBound = false;

	MoseService moseService;

	private Context mContext = null;

	private static final Intent INTENT = new Intent(MoseService.class.getName());

	private static PushMessageService instance;

	private ServiceConnection connection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.d(TAG, "onServiceConnected");
			MoseServiceBinder binder = (MoseServiceBinder) service;
			moseService = binder.getService();
			isBound = true;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.d(TAG, "onServiceDisconnected");
			isBound = false;
			moseService = null;
		}
	};

	private PushMessageService(Context mContext) {
		this.mContext = mContext;
	}

	public static PushMessageService getInstance(Context mContext) {
		if (null == instance) {
			instance = new PushMessageService(mContext);
		}
		return instance;
	}

	/**
	 * 启动消息推送服务
	 * 
	 * @return true 成功 ,false 失败
	 */
	public boolean startService() {
		boolean isSuccess = false;
		if (!isBound && null != instance) {
			isSuccess = mContext.bindService(INTENT, connection,
					Context.BIND_AUTO_CREATE);
		} else {
			Log.d(TAG, "the service is runing , no startService");
		}
		return isSuccess;
	}

	/**
	 * 停止消息推送服务
	 */
	public void stopService() {
		if (isBound && null != instance) {
			mContext.unbindService(connection);
			isBound = false;
		} else {
			Log.d(TAG, "the service is stop , no stopService");
		}
	}

}
