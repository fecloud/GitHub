package com.aspire.eldmose.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.aspire.eldmose.monitor.MsisdnNotify;

public class MidDatabase {
	private static final String DB_NAME = "MIDDatabase";
	private static final String TB_NAME = "MsisTable";
	private static final String MSGTB_NAME = "MsgTable";
	private static final int DB_VERSION = 1;
	private Context mContext = null;
	private static final String CREATEDB = "CREATE TABLE "
		+ TB_NAME + " ("
		+ MsisDbLine.MSISDN
		+ " CHAR(64) PRIMARY KEY,"
		+ MsisDbLine.IMSI + " CHAR(64),"
		+ MsisDbLine.SSID + " CHAR(64))";
	
	private static final String CREATEMSGDB = "CREATE TABLE "
		+ MSGTB_NAME + " ("
		+ MSGDbLine.MSGID + " CHAR(64) PRIMARY KEY,"
		+ MSGDbLine.MSGCONTENT + " TEXT,"
		+ MSGDbLine.MSGTIME + " NUMBER(20))";
	
	private MsisDbHelper mMsisDbHelper = null;
	private SQLiteDatabase mSQLiteDatabase = null;
	
	public MidDatabase(Context context) {
		this.mContext = context;
	}
	
	public void open() throws SQLException {
		mMsisDbHelper = new MsisDbHelper(mContext);
		mSQLiteDatabase = mMsisDbHelper.getWritableDatabase();
	}
	
	public void close() {
		mMsisDbHelper.close();
	}
	
	public long upgrateMsisdn(MsisdnNotify  msisdnNotify ) {
		Log.d("MidDatabase", msisdnNotify.toString());
		ContentValues value = new ContentValues();
		value.put(MsisDbLine.MSISDN, msisdnNotify.msisdn);
		value.put(MsisDbLine.IMSI, msisdnNotify.imsi);
		value.put(MsisDbLine.SSID, msisdnNotify.ssid);
		
		if(getMsisdn(msisdnNotify.imsi) == ""){
			return mSQLiteDatabase.insert(TB_NAME, null, value);
		}
		else {
			return mSQLiteDatabase.update(TB_NAME, value, null, null);
		}
	}
	
	public long insertMsg(String msgid, String msgcontent, long timenow) {
		Log.d("MidDatabase", "insert msg: "+msgid+ " "+ msgcontent+" "+timenow);
		ContentValues value = new ContentValues();
		value.put(MSGDbLine.MSGID, msgid);
		value.put(MSGDbLine.MSGCONTENT, msgcontent);
		value.put(MSGDbLine.MSGTIME, timenow);
		
		return mSQLiteDatabase.insert(MSGTB_NAME, null, value);
		
	}
	
	public boolean vetifyRepeateMsg(String msdid, long limit) {
		Log.d("MidDatabase", "vetifyRepeateMsg: " + msdid);
		
		Cursor mCursor = null;
		try {
			mCursor = mSQLiteDatabase.query(MSGTB_NAME, 
					null, 
					MSGDbLine.MSGID + "=\"" + msdid +"\"", null, null, null, null);
			if(mCursor != null && mCursor.getCount()!=0) {
//				mCursor.moveToFirst();
//				Long saveTime = mCursor.getLong(mCursor.getColumnIndex(MSGDbLine.MSGTIME));
//				return System.currentTimeMillis() - saveTime < limit;
				return true;
			}
		}
		catch(SQLException e) {
			Log.e("MidDatabase ERROR!", e.toString());
		}
		finally {
			if(mCursor!=null)
				mCursor.close();
		}
		
		return false;
		
	}
	
	public void releaseInvalidMsg(long limit) {
		long timeNow = System.currentTimeMillis();
		try {
			String releaseSqlCmd = timeNow + " - " + MSGDbLine.MSGTIME + " > " + limit;
			Log.d("MidDatabase", "releaseInvalidMsg SQL:" + releaseSqlCmd);
			
			mSQLiteDatabase.delete(MSGTB_NAME, 
					releaseSqlCmd,
					null);
		}
		catch(SQLException e) {
			Log.e("MidDatabase ERROR!", e.toString());
		}
	}

	
	public int deleteImsi(String imsi) {
		return mSQLiteDatabase.delete(DB_NAME, MsisDbLine.IMSI + "=" + imsi, null);
	}
	
	public String getMsisdn(String imsi) {
		String msisdn = "";
		if(imsi == "") return msisdn;
		
		Cursor mCursor = null;
		try {
			mCursor = mSQLiteDatabase.query(TB_NAME, 
					null, 
					MsisDbLine.IMSI + "=\"" + imsi +"\"", null, null, null, null);
			if(mCursor != null && mCursor.getCount()!=0) {
				mCursor.moveToFirst();
				msisdn = mCursor.getString(mCursor.getColumnIndex(MsisDbLine.MSISDN));
			}
		}
		catch(SQLException e) {
			Log.e("MidDatabase ERROR!", e.toString());
		}
		finally {
			if(mCursor!=null)
				mCursor.close();
		}
		
		return msisdn;
	}
	
	private static class MsisDbHelper extends SQLiteOpenHelper {

		public MsisDbHelper(Context context) {
			super(context, DB_NAME, null, DB_VERSION);
		}
		
		public MsisDbHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(CREATEDB);
			db.execSQL(CREATEMSGDB);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS MidDatabase");
			db.execSQL("DROP TABLE IF EXISTS MsgDatabase");
			onCreate(db);
		}
	}
	
	private static class MsisDbLine {
		private static String MSISDN = "msisdn";
		private static String IMSI = "imsi";
		private static String SSID = "ssid";

	}
	
	private static class MSGDbLine {
		private static String MSGID = "msgid";
		private static String MSGCONTENT = "msgcontent";
		private static String MSGTIME = "msgtime";
	}
	

}
