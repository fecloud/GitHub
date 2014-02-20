package com.aspire.mpy.provider;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Log;

import com.aspire.mpy.bean.CardID;
import com.aspire.mpy.bean.CardInfo;
import com.aspire.mpy.bean.UserInfo;
import com.aspire.mpy.bean.PhotoInfo;


public class ProviderUtil {

	private static final String TAG = "ProviderUtil";
	/**
	 * 绑定标识
	 */
	public static String ACITIVATE_FLAG = "activate_flag";
	/**
	 * 查询where参数占位
	 */
	public static String QUERY_WHERE_ARG = "=? ";
	/**
	 * 查询where参数占位
	 */
	public static String QUERY_WHERE_NOT_ARG = "!=? ";
	/**
	 * 查询and 关键字
	 */
	public static String QUERY_WHERE_AND = " and ";
	/**
	 * 查询or 关键字
	 */
	public static String QUERY_WHERE_OR = " or ";

	/**
	 * 左边括号
	 */
	public static String QUERY_WHERE_LEFT = " ( ";
	/**
	 * 右边括号
	 */
	public static String QUERY_WHERE_RIGHT = " ) ";
	
	/**
	 * 插入或者更新一个名片的信息
	 * @param cxt
	 * @param userInfo
	 * @return
	 */
	public static int insertOrUpdateUserInfo(Context cxt , UserInfo userInfo){
		ContentValues values = new ContentValues();
		values.put(MPYPad.UserInfo.ID, userInfo.getCardID().getId());
		values.put(MPYPad.UserInfo.VERSION, userInfo.getCardID().getVersion());
		values.put(MPYPad.UserInfo.NAME, userInfo.getCardInfo().getName());
		values.put(MPYPad.UserInfo.DUTY, userInfo.getCardInfo().getDuty());
		values.put(MPYPad.UserInfo.MOBILE, userInfo.getCardInfo().getMobile());
		values.put(MPYPad.UserInfo.EMAIL, userInfo.getCardInfo().getEmail());
		values.put(MPYPad.UserInfo.OFFICETEL, userInfo.getCardInfo().getOfficeTel());
		values.put(MPYPad.UserInfo.OFFICEFAX, userInfo.getCardInfo().getOfficeFax());
		values.put(MPYPad.UserInfo.COMPADDR, userInfo.getCardInfo().getCompAddr());
		values.put(MPYPad.UserInfo.COMPNAME, userInfo.getCardInfo().getCompName());
		values.put(MPYPad.UserInfo.COMPURL, userInfo.getCardInfo().getCompURL());
		values.put(MPYPad.UserInfo.GROUPIDLIST, userInfo.getCardInfo().getGroupIDList());
		values.put(MPYPad.UserInfo.PHOTOID, userInfo.getCardInfo().getPhotoID());
		values.put(MPYPad.UserInfo.TEMPLATE, userInfo.getCardInfo().getTemplate());
		values.put(MPYPad.UserInfo.EXTRINFO, userInfo.getCardInfo().getExtrInfo());
		values.put(MPYPad.UserInfo.TRADEADDR, userInfo.getCardInfo().getTradeAddr());
		values.put(MPYPad.UserInfo.TRADETIME, userInfo.getCardInfo().getTradeTime());
		values.put(MPYPad.UserInfo.CREATETIME, userInfo.getCardInfo().getCreateTime());
		String selection = MPYPad.UserInfo.ID + QUERY_WHERE_ARG;
		String[] selectionArgs = {  "" + userInfo.getCardID().getId() };
		Cursor c = cxt.getContentResolver().query(MPYPad.UserInfo.CONTENT_URI,
				null, selection, selectionArgs, null);
		if (null != c && c.getCount() != 0) {
			cxt.getContentResolver().update(MPYPad.UserInfo.CONTENT_URI, values,
					selection, selectionArgs);
		} else {
			cxt.getContentResolver().insert(MPYPad.UserInfo.CONTENT_URI, values);
		}

		if (c != null) {
			c.close();
			c = null;
		}
		return 0;
	}
	
	/**
	 * 获取一个名片的信息
	 * @param cxt
	 * @param id
	 * @return
	 */
	public static UserInfo getUserInfoByID(Context cxt , Integer id){
		UserInfo userInfo = null;
		String selection = MPYPad.UserInfo.ID + QUERY_WHERE_ARG;
		String[] selectionArgs = {  "" + id };
		Cursor c = cxt.getContentResolver().query(MPYPad.UserInfo.CONTENT_URI,
				null, selection, selectionArgs, null);
		if (null != c && c.getCount() != 0) {
			c.moveToFirst();
			userInfo = new UserInfo();
			userInfo.setCardID(getCardID(c));
			userInfo.setCardInfo(getCardInfo(c));
		} 
		if (c != null) {
			c.close();
			c = null;
		}
		return userInfo;
	}
	
	/**
	 * 插入或者更新一个名片的ID信息
	 * @param cxt
	 * @param cardID
	 * @return
	 */
	public static int insertOrUpdateCardID(Context cxt , CardID cardID){
		ContentValues values = new ContentValues();
		values.put(MPYPad.UserInfo.ID, cardID.getId());
		values.put(MPYPad.UserInfo.VERSION, cardID.getVersion());
		String selection = MPYPad.UserInfo.ID + QUERY_WHERE_ARG;
		String[] selectionArgs = {  "" + cardID.getId() };
		Cursor c = cxt.getContentResolver().query(MPYPad.UserInfo.CONTENT_URI,
				null, selection, selectionArgs, null);
		if (null != c && c.getCount() != 0) {
			cxt.getContentResolver().update(MPYPad.UserInfo.CONTENT_URI, values,
					selection, selectionArgs);
		} else {
			cxt.getContentResolver().insert(MPYPad.UserInfo.CONTENT_URI, values);
		}

		if (c != null) {
			c.close();
			c = null;
		}
		return 0;
	}
	
	/**
	 * 获取一个用户的ID信息
	 * @param cxt
	 * @param id
	 * @return
	 */
	public static CardID getCardIDByID(Context cxt , Integer id){
		CardID cardID = null;
		String selection = MPYPad.UserInfo.ID + QUERY_WHERE_ARG;
		String[] selectionArgs = {  "" + id };
		Cursor c = cxt.getContentResolver().query(MPYPad.UserInfo.CONTENT_URI,
				null, selection, selectionArgs, null);
		if (null != c && c.getCount() != 0) {
			c.moveToFirst();
			cardID = new CardID();
			cardID.setId(c.getInt((c.getColumnIndexOrThrow(MPYPad.UserInfo.ID))));
			cardID.setVersion(c.getInt((c.getColumnIndexOrThrow(MPYPad.UserInfo.VERSION))));
		} 
		if (c != null) {
			c.close();
			c = null;
		}
		return cardID;
	}
	
	/**
	 * 获取头像
	 * @param cxt
	 * @param photoID
	 * @return
	 */
	public static Bitmap getPhoto(Context cxt , Integer photoID){
		Bitmap bitmap = null;
		String selection = MPYPad.PhotoInfo.PHOTOID + QUERY_WHERE_ARG;
		String [] selectionArgs = {"" + photoID};
		Cursor c = cxt.getContentResolver().query(MPYPad.PhotoInfo.CONTENT_URI, null, selection, selectionArgs, null);
		if(null != c && c.getCount() != 0){
			c.moveToFirst();
			byte [] data = c.getBlob(c.getColumnIndexOrThrow(MPYPad.PhotoInfo.PHOTO));
			Options opts = new BitmapFactory.Options();
			bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, opts );
		}
		if (c != null) {
			c.close();
			c = null;
		}
		
		return bitmap;
	}
	
	/**
	 * 插入或者更新头像
	 * @param cxt
	 * @param photoID
	 * @param path
	 */
	public static void saveOrUpdatePhoto(Context cxt , Integer photoID ,String path){
		ContentValues values = new ContentValues();
		values.put(MPYPad.PhotoInfo.PHOTOID, photoID);
		values.put(MPYPad.PhotoInfo.PHOTO, bitmapToBytes(BitmapFactory.decodeFile(path)));
		String selection = MPYPad.PhotoInfo.PHOTOID + QUERY_WHERE_ARG;
		String [] selectionArgs = {"" + photoID};
		Cursor c = cxt.getContentResolver().query(MPYPad.PhotoInfo.CONTENT_URI, null, selection, selectionArgs, null);
		if(null != c && c.getCount() != 0){
			cxt.getContentResolver().update(MPYPad.PhotoInfo.CONTENT_URI, values, selection, selectionArgs);
		}else{
			cxt.getContentResolver().insert(MPYPad.PhotoInfo.CONTENT_URI, values);
		}
		
		if (c != null) {
			c.close();
			c = null;
		}
	}
	
	/**
	 * 插入或者更新头像
	 * @param cxt
	 * @param photoID
	 * @param bitmap
	 */
	public static void saveOrUpdatePhoto(Context cxt , Integer photoID ,Bitmap bitmap){
		ContentValues values = new ContentValues();
		values.put(MPYPad.PhotoInfo.PHOTOID, photoID);
		values.put(MPYPad.PhotoInfo.PHOTO, bitmapToBytes(bitmap));
		String selection = MPYPad.PhotoInfo.PHOTOID + QUERY_WHERE_ARG;
		String [] selectionArgs = {"" + photoID};
		Cursor c = cxt.getContentResolver().query(MPYPad.PhotoInfo.CONTENT_URI, null, selection, selectionArgs, null);
		if(null != c && c.getCount() != 0){
			cxt.getContentResolver().update(MPYPad.PhotoInfo.CONTENT_URI, values, selection, selectionArgs);
		}else{
			cxt.getContentResolver().insert(MPYPad.PhotoInfo.CONTENT_URI, values);
		}
		
		if (c != null) {
			c.close();
			c = null;
		}
	}
	
	private static byte[] bitmapToBytes(Bitmap icon) {    
        if (icon == null) {    
        	Log.e(TAG, "bitmapToBytes: icon is null." );
            return null;    
        }    
        // BLOB类型    
        final ByteArrayOutputStream os = new ByteArrayOutputStream();    
        // 将Bitmap压缩成PNG编码，质量为100%存储            
        icon.compress(Bitmap.CompressFormat.PNG, 100, os);     
        // 写入数据库的Browser.BookmarkColumns.TOUCH_ICON字段    
        return os.toByteArray(); 
    }
	
	/**
	 * 清除用户的登录信息DB
	 * @param cxt
	 */
	public static void cleanUserInfo(Context cxt){
		cxt.getContentResolver().delete(MPYPad.UserInfo.CONTENT_URI, null, null);
	}
	
	/**
	 * 取得用户的所有的交换名片
	 * @param cxt
	 * @param notId
	 * @return
	 */
	public static List<UserInfo> getUserAllTrade(Context cxt , int notId){
		List<UserInfo> infos = null;
		UserInfo userInfo = null;
		String selection = MPYPad.UserInfo.ID + QUERY_WHERE_NOT_ARG;
		String[] selectionArgs = {  "" + notId };
		Cursor c = cxt.getContentResolver().query(MPYPad.UserInfo.CONTENT_URI,
				null, selection, selectionArgs, null);
		if (null != c && c.getCount() != 0) {
			c.moveToFirst();
			infos = new ArrayList<UserInfo>();
			userInfo = new UserInfo();
			userInfo.setCardID(getCardID(c));
			userInfo.setCardInfo(getCardInfo(c));
			infos.add(userInfo);
			while(c.moveToNext()){
				userInfo = new UserInfo();
				userInfo.setCardID(getCardID(c));
				userInfo.setCardInfo(getCardInfo(c));
				infos.add(userInfo);
			}
		} 
		if (c != null) {
			c.close();
			c = null;
		}
		return infos;
	}
	
	private static CardID getCardID(Cursor c){
		CardID cardID = new CardID();
		cardID.setId(c.getInt((c.getColumnIndexOrThrow(MPYPad.UserInfo.ID))));
		cardID.setVersion(c.getInt((c.getColumnIndexOrThrow(MPYPad.UserInfo.VERSION))));
		return cardID;
	}
	
	private static CardInfo getCardInfo(Cursor c){
		CardInfo cardInfo = new CardInfo();
		//cardinfo
		cardInfo.setName(c.getString(c.getColumnIndexOrThrow(MPYPad.UserInfo.NAME)));
		cardInfo.setDuty(c.getString(c.getColumnIndexOrThrow(MPYPad.UserInfo.DUTY)));
		cardInfo.setMobile(c.getString(c.getColumnIndexOrThrow(MPYPad.UserInfo.MOBILE)));
		cardInfo.setEmail(c.getString(c.getColumnIndexOrThrow(MPYPad.UserInfo.EMAIL)));
		cardInfo.setOfficeTel(c.getString(c.getColumnIndexOrThrow(MPYPad.UserInfo.OFFICETEL)));
		cardInfo.setOfficeFax(c.getString(c.getColumnIndexOrThrow(MPYPad.UserInfo.OFFICEFAX)));
		cardInfo.setCompAddr(c.getString(c.getColumnIndexOrThrow(MPYPad.UserInfo.COMPADDR)));
		cardInfo.setCompName(c.getString(c.getColumnIndexOrThrow(MPYPad.UserInfo.COMPNAME)));
		cardInfo.setCompURL(c.getString(c.getColumnIndexOrThrow(MPYPad.UserInfo.COMPURL)));
		cardInfo.setGroupIDList(c.getString(c.getColumnIndexOrThrow(MPYPad.UserInfo.GROUPIDLIST)));
		cardInfo.setPhotoID(c.getInt(c.getColumnIndexOrThrow(MPYPad.UserInfo.PHOTOID)));
		cardInfo.setTemplate(c.getInt(c.getColumnIndexOrThrow(MPYPad.UserInfo.TEMPLATE)));
		cardInfo.setExtrInfo(c.getString(c.getColumnIndexOrThrow(MPYPad.UserInfo.EXTRINFO)));
		cardInfo.setTradeAddr(c.getString(c.getColumnIndexOrThrow(MPYPad.UserInfo.TRADEADDR)));
		cardInfo.setTradeTime(c.getString(c.getColumnIndexOrThrow(MPYPad.UserInfo.TRADETIME)));
		cardInfo.setCreateTime(c.getString(c.getColumnIndexOrThrow(MPYPad.UserInfo.CREATETIME)));
		return cardInfo;
	}
	
	public static void insertOrUpdatePhotoInfo(Context cxt , PhotoInfo photoInfo){
		ContentValues values = new ContentValues();
		values.put(MPYPad.PhotoInfo.PHOTOID,photoInfo.getPhotoID() );
		values.put(MPYPad.PhotoInfo.PHOTO, bitmapToBytes(photoInfo.getPhoto()));
		values.put(MPYPad.PhotoInfo.PHOTOTHUM, bitmapToBytes(photoInfo.getPhotoThum()));
		String selection = MPYPad.PhotoInfo.PHOTOID + QUERY_WHERE_ARG ;
		String [] selectionArgs = {"" + photoInfo.getPhotoID()};
		Cursor c = cxt.getContentResolver().query(MPYPad.PhotoInfo.CONTENT_URI, null, selection, selectionArgs, null);
		if(null != c && c.getCount() != 0){
			cxt.getContentResolver().update(MPYPad.PhotoInfo.CONTENT_URI, values, selection, selectionArgs);
		}else{
			cxt.getContentResolver().insert(MPYPad.PhotoInfo.CONTENT_URI, values);
		}
		if (c != null) {
			c.close();
			c = null;
		}
	}
	
	
	public static PhotoInfo getPhotoInfoByPhotoID(Context cxt , Integer photoID){
		PhotoInfo photoInfo = null;
		String selection = MPYPad.PhotoInfo.PHOTOID + QUERY_WHERE_ARG;
		String [] selectionArgs = {"" + photoID};
		Cursor c = cxt.getContentResolver().query(MPYPad.PhotoInfo.CONTENT_URI, null, selection, selectionArgs, null);
		if(null != c && c.getCount() != 0){
			if(c.moveToFirst()){
				photoInfo = new PhotoInfo();
				photoInfo.setPhotoID(c.getInt(c.getColumnIndexOrThrow(MPYPad.PhotoInfo.PHOTOID)));
				byte [] photo = c.getBlob(c.getColumnIndexOrThrow(MPYPad.PhotoInfo.PHOTO));
				Bitmap bitmapPhoto = BitmapFactory.decodeByteArray(photo, 0, photo.length);
				photoInfo.setPhoto(bitmapPhoto);
				byte [] photoThum = c.getBlob(c.getColumnIndexOrThrow(MPYPad.PhotoInfo.PHOTOTHUM));
				Bitmap bitmapPhotoThum = BitmapFactory.decodeByteArray(photoThum, 0, photoThum.length);
				photoInfo.setPhoto(bitmapPhotoThum);
			}
		}
		if (c != null) {
			c.close();
			c = null;
		}
		return photoInfo;
	}
}
