package com.aspire.eldmose.database;

import android.content.Context;
import android.content.IntentFilter;
import android.mid.config.Config;
import android.util.Log;

import com.aspire.eldmose.monitor.PushMsgReceiver;

public class DBManager {

	private static final int SER_MSISDN_SMS = 17;
	private static final int SER_MSISDN_MOSP = 37;	
	public static DBHandler mQueryMsisHandler = null;
	public PushMsgReceiver mPushMsgReceiver = null;
	
	public final static String ACTION_PUSH_NOTIFY = "com.aspire.mose.PUSH_MESSAGE_NOTIFY.android.mid";
	private static final String TAG = "QueryMsisManager";
	
	public Context mContext;
	public static MidDatabase mDb;
	
	// 清库间隔
	public static final long RELEASEMSGTIME = 24*60*60*1000;
	// 消息有效时间
	public static final long RELEASEMSGLIMIT = RELEASEMSGTIME;
	
	private static final DBManager instance = new DBManager();
	
	public DBManager(){
	}

	public DBManager(Context context) {
	}
	
    public static DBManager getInstance()
    {
        return instance;
    }
    
    public Context getContext()
    {
        return this.mContext;
    }
	
	public void initDbManager(Context context) {
		// TODO Auto-generated method stub
		this.mContext = context;

		Config.init(mContext);
		
		if(mDb == null) {
			mDb = new MidDatabase(mContext);
	        mDb.open();
		}
        
		mQueryMsisHandler = new DBHandler(mContext, mDb);
		mQueryMsisHandler.setLocalImsi(getLocalImsiID(mContext));
		
		if(mPushMsgReceiver != null) {
			mContext.unregisterReceiver(mPushMsgReceiver);
			mPushMsgReceiver = null;
		}
		mPushMsgReceiver = new PushMsgReceiver(this);
		IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_PUSH_NOTIFY);           
        mContext.registerReceiver(mPushMsgReceiver, filter);
        
        releaseMsg rm = new releaseMsg();
		Thread thread = new Thread(rm);
		thread.start();
	}
	
	public void reVerifyMsisdn() {
		Log.d("QueryMsisManager", "reVerifyMsisdn() mQueryMsisHandler= "+mQueryMsisHandler);
//		mQueryMsisHandler.obtainMessage(SER_MSISDN_MOSP).sendToTarget();
		
		reVerifyThread rt = new reVerifyThread();
		Thread thread = new Thread(rt);
		thread.start();
	}
	
	public static String GetMsisdnDb(Context context, boolean b) {
		// TODO Auto-generated method stub
		String msisdn = GetMsisdnDb(context);
		if(msisdn == "" && mQueryMsisHandler != null)
			mQueryMsisHandler.obtainMessage(SER_MSISDN_MOSP).sendToTarget();
		
		return msisdn;
	}   
	
	
	public static String GetMsisdnDb(Context context) {
		
		if(mDb == null) {
			mDb = new MidDatabase(context);
	        mDb.open();
		}
		
		String msisdn = mDb.getMsisdn(getLocalImsiID(context));
		
		if(msisdn == "") {
			Log.v("MsisDatabase", "Can't find Msisdn");
//			Toast.makeText(context, "Can't find Msisdn", Toast.LENGTH_LONG).show();
		}
		else {
			Log.v("MsisDatabase", "find Msisdn:"+msisdn);
//			Toast.makeText(context, "find Msisdn:"+msisdn, Toast.LENGTH_LONG).show();
		}
		
		return msisdn;
	}
	
	public static String getLocalImsiID(Context _context) {
		// TODO Auto-generated method stub
//		return ((TelephonyManager) _context
//				.getSystemService(Context.TELEPHONY_SERVICE)).getSubscriberId();
		return "460023135432888";
	}

	public boolean vetifyRepeateMsg(String msgid, long limit)
	{
		return mDb.vetifyRepeateMsg(msgid, limit);
	}
	
	public static void releaseInvalidMsg(long limit) {
		mDb.releaseInvalidMsg(limit);
	} 
	
	public long insertMsg(String msgid, String msgcontent, long timenow) {
		return mDb.insertMsg(msgid, msgcontent, timenow);
	}
	
	public void close() {
		// TODO Auto-generated method stub
		Log.d(TAG, "Close QueryMsisManager");
		mContext.unregisterReceiver(mPushMsgReceiver);
		mDb.close();
	}
	
    class reVerifyThread implements Runnable{
    	private int time = 0;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			do {
				mQueryMsisHandler.obtainMessage(SER_MSISDN_MOSP).sendToTarget();
				time++;
				Log.d(TAG, "reVerify Time:"+time);
				try {
					Thread.sleep(1800000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			while (mDb.getMsisdn(getLocalImsiID(mContext)) == "" && time <= 10);
		}
    	
    }
    
    class releaseMsg implements Runnable {
    	
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
				try {
					Thread.sleep(RELEASEMSGTIME);
					releaseInvalidMsg(RELEASEMSGLIMIT);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    	
    }

}
