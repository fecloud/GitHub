package com.aspire.mose.frame.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.aspire.eldmose.database.DBManager;
import com.aspire.mose.business.module.msgpush.MsgPushModule;
import com.aspire.mose.frame.config.Config;
import com.aspire.mose.frame.message.MessageManager;
import com.aspire.mose.frame.net.status.NetStatus;
import com.aspire.mose.shore.broadcast.BroadcastManager;
import com.aspire.mose.shore.status.StateMachine;

/**
 * MOSE消息推送服务
 */
public class MoseService extends Service {

	static final String TAG = "MOSE.Service";

	private final IBinder mBinder = new MoseServiceBinder();

	// 配置文件放在assets目录
	private static final String configConfigFile = "config/config.xml";

	public void onCreate() {
		super.onCreate();

		Log.d(TAG, "Service onCreate," + this);
		Context context = this.getApplicationContext();
		try {
			// 初始化状态机
			StateMachine.addStateMachine(MOSEStatus.class);
			StateMachine.addStateMachine(NetStatus.class);
			// 更新MOSEStatus状态机
			MOSEStatus.instance().updateState(MOSEStatus.STARTING);
			// 读取配置信息
			Config.getInstance().init(context, configConfigFile);
			// 初始化广播管理器
			BroadcastManager.setContext(context);
			// 初始化消息管理器
			MessageManager.initFromConfig(context);
			//设置推送监听
			MsgPushModule.getInstance().init(
					MessageManager.getInstance().getMessageCenter(
							MessageManager.D_MSG_CENTER));
			// 初始化推送数据库
			DBManager.getInstance().initDbManager(context);

			// 更新MOSE状态为启动成功
			MOSEStatus.instance().updateState(MOSEStatus.SUCC);
		} catch (Exception e) {
			Log.e(TAG, "MOSEService error!", e);
			// 出现异常更新MOSE状态为启动失败
			MOSEStatus.instance().updateState(MOSEStatus.ERROR);
		}
	}

	public void onDestroy() {
		Log.d(TAG, "Service onDestroy");
		// 停止服务,销毁消息管理
		MessageManager.destroy();
		super.onDestroy();
	}

	public void onLowMemory() {
		Log.d(TAG, "Service onLowMemory");
		super.onLowMemory();
	}

	public boolean onUnbind(Intent intent) {
		Log.d(TAG, "Service onUnbind");
		// 解除绑定
		return super.onUnbind(intent);
	}

	public IBinder onBind(Intent intent) {
		Log.d(TAG, "Service onBind");
		return mBinder;
	}

	public class MoseServiceBinder extends Binder {

		public MoseService getService() {
			return MoseService.this;
		}

	}

}
