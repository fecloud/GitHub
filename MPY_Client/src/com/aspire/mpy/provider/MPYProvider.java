package com.aspire.mpy.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

public class MPYProvider extends ContentProvider {
	
    private SQLiteDatabase mDatabase;  
    
    private MPYtDataBaseHelper mHelper;  
  
    private static final String DB_NAME = "mpy.db";  
  
    public static Context mContext;  
  
    private static final int DB_VERSION = 1;  
  
    private static final UriMatcher M_URI_MATCHER;  
    
    private static final int USERINFO = 1;
    private static final int ITEM_USERINFO = 2;
    
    private static final int PHOTOINFO = 3;
    private static final int ITEM_PHOTOINFO = 4;
    
    static{
    	 M_URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);  
         M_URI_MATCHER.addURI(MPYPad.AUTHORITY, MPYPad.TB_NAME_USER_INFO, USERINFO);  
         M_URI_MATCHER.addURI(MPYPad.AUTHORITY, MPYPad.TB_NAME_USER_INFO + "/#", ITEM_USERINFO);
         M_URI_MATCHER.addURI(MPYPad.AUTHORITY, MPYPad.TB_NAME_PHOTO_INFO, PHOTOINFO);
         M_URI_MATCHER.addURI(MPYPad.AUTHORITY, MPYPad.TB_NAME_PHOTO_INFO + "/#", ITEM_PHOTOINFO);
    }
  
    private SQLiteQueryBuilder qBuilder = new SQLiteQueryBuilder(); 
	
    @Override
	protected void finalize() throws Throwable {
    	mDatabase.close();
		super.finalize();
	}
    
    
    
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		String tableName = uri.getPathSegments().get(0);
		// Validate the requested uri
		if (tableName == null) {
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		int count;

		// Set the table we're querying.
		qBuilder.setTables(tableName);

		// If the query ends in a specific record number, we're
		// being asked for a specific record, so set the
		// WHERE clause in our query.
		if ((M_URI_MATCHER.match(uri)) >= 100) {
			// if ((sUriMatcher.match(uri)) >= 10) {
			qBuilder.appendWhere("_id=" + uri.getLastPathSegment());
		}

		count = mDatabase.delete(tableName, selection, selectionArgs);

		return count;
	}

	@Override
	public String getType(Uri uri) {
		switch (M_URI_MATCHER.match(uri)) {
		case USERINFO:
			return MPYPad.UserInfo.CONTENT_TYPE;
		case ITEM_USERINFO:
			return MPYPad.UserInfo.CONTENT_ITEM_TYPE;
		case PHOTOINFO:
			return MPYPad.PhotoInfo.CONTENT_ITEM_TYPE;
			case ITEM_PHOTOINFO:
				return MPYPad.PhotoInfo.CONTENT_ITEM_TYPE;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		String tableName = uri.getPathSegments().get(0);
		// Validate the requested uri
		if (tableName == null) {
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		if (values != null) {
			long rowId = mDatabase.insert(tableName, null, values);
			if (rowId > 0) {
				Uri itemUri = ContentUris.withAppendedId(uri, rowId);
				getContext().getContentResolver().notifyChange(itemUri, null);
				return itemUri;
			}
		}
		return null;
	}

	@Override
	public boolean onCreate() {
		mHelper = new MPYtDataBaseHelper(getContext());
		openDB();
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
			String sortOrder) {
		String tableName = uri.getPathSegments().get(0);
		// Validate the requested uri
		if (tableName == null) {
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		// Set the table we're querying.
		qBuilder.setTables(tableName);

		// If the query ends in a specific record number, we're
		// being asked for a specific record, so set the
		// WHERE clause in our query.
		if ((M_URI_MATCHER.match(uri)) >= 100) {
			// if ((sUriMatcher.match(uri)) >= 10) {
			qBuilder.appendWhere("_id=" + uri.getLastPathSegment());
		}

		// Make the query.

		Log.i("tag", "db=" + mDatabase);
		Log.i("tag", "projection" + projection);
		Log.i("tag", "selection=" + selection);
		Log.i("tag", "selectionArgs=" + selectionArgs);
		Log.i("tag", "sortOrder=" + sortOrder);

		Cursor c = qBuilder.query(mDatabase, projection, selection, selectionArgs,
				null, null, sortOrder);

		// Tell the cursor what uri to watch, so it knows when its source data
		// changes
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		String tableName = uri.getPathSegments().get(0);
		// Validate the requested uri
		if (tableName == null) {
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		// Set the table we're querying.
		qBuilder.setTables(tableName);

		// If the query ends in a specific record number, we're
		// being asked for a specific record, so set the
		// WHERE clause in our query.
		if ((M_URI_MATCHER.match(uri)) >= 100) {
			// if ((sUriMatcher.match(uri)) >= 10) {
			qBuilder.appendWhere("_id=" + uri.getLastPathSegment());
		}

		int count;

		count = mDatabase.update(tableName, values, selection, selectionArgs);

		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}
	
	/*
	 * 打开数据库
	 */
	void openDB() {
		try {
			mDatabase = mHelper.getWritableDatabase();
		} catch (SQLiteException ex) {
			mDatabase = mHelper.getReadableDatabase();
		}
	}
	
	class MPYtDataBaseHelper extends SQLiteOpenHelper{

		public MPYtDataBaseHelper(Context context) {
			super(context, DB_NAME, null, DB_VERSION);
			mContext = context;
		}


		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE "+MPYPad.TB_NAME_USER_INFO
					+" (" + MPYPad.UserInfo._ID  
                    + " INTEGER PRIMARY KEY AUTOINCREMENT," 
                    + MPYPad.UserInfo.ID + " INTEGER," 
                    + MPYPad.UserInfo.VERSION + " INTEGER," 
                    + MPYPad.UserInfo.NAME + " TEXT," 
                    + MPYPad.UserInfo.DUTY + " TEXT," 
                    + MPYPad.UserInfo.MOBILE + " TEXT," 
                    + MPYPad.UserInfo.EMAIL + " TEXT," 
                    + MPYPad.UserInfo.OFFICETEL + " TEXT," 
                    + MPYPad.UserInfo.OFFICEFAX + " TEXT," 
                    + MPYPad.UserInfo.COMPADDR + " TEXT," 
                    + MPYPad.UserInfo.COMPNAME + " TEXT," 
                    + MPYPad.UserInfo.COMPURL + " TEXT," 
                    + MPYPad.UserInfo.GROUPIDLIST + " TEXT," 
                    + MPYPad.UserInfo.PHOTOID + " INTEGER," 
                    + MPYPad.UserInfo.TEMPLATE + " INTEGER," 
                    + MPYPad.UserInfo.EXTRINFO + " TEXT," 
                    + MPYPad.UserInfo.TRADEADDR + " TEXT," 
                    + MPYPad.UserInfo.TRADETIME + " TEXT," 
                    + MPYPad.UserInfo.CREATETIME + " TEXT" 
                    +");"); 
			
			db.execSQL("CREATE TABLE "  +MPYPad.TB_NAME_PHOTO_INFO
					+" (" + MPYPad.PhotoInfo._ID  
                    + " INTEGER PRIMARY KEY AUTOINCREMENT," 
                    + MPYPad.PhotoInfo.PHOTOID + " INTEGER,"
                    + MPYPad.PhotoInfo.PHOTO + " BLOB,"
                    + MPYPad.PhotoInfo.PHOTOTHUM + " BLOB"
					+");"); 
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
		}
		
		
		
	}

}
