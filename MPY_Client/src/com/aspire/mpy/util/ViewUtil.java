package com.aspire.mpy.util;

import java.io.File;
import java.io.InputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aspire.mpy.CreateNewNameCard;
import com.aspire.mpy.R;
import com.aspire.mpy.CreateNewNameCard.PHTOS;
import com.aspire.mpy.bean.CardInfo;
import com.aspire.mpy.bean.PhotoInfo;
import com.aspire.mpy.bean.UserInfo;
import com.aspire.mpy.config.Config;
import com.aspire.mpy.contact.Contact;

public class ViewUtil {

	private static final String TAG = "ViewUtil";

	/**
	 * 添加倒影，原理，先翻转图片，由上到下放大透明度
	 * 
	 * @param originalImage
	 * @return
	 */
	public static Bitmap createReflectedImage(Bitmap originalImage) {
		// The gap we want between the reflection and the original image
		final int reflectionGap = 4;

		int width = originalImage.getWidth();
		int height = originalImage.getHeight();

		// This will not scale but will flip on the Y axis
		Matrix matrix = new Matrix();
		matrix.preScale(1, -1);

		// Create a Bitmap with the flip matrix applied to it.
		// We only want the bottom half of the image
		Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0,
				height / 2, width, height / 2, matrix, false);

		// Create a new bitmap with same width but taller to fit reflection
		Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
				(height + height / 4),Bitmap.Config.ARGB_8888);

		// Create a new Canvas with the bitmap that's big enough for
		// the image plus gap plus reflection
		Canvas canvas = new Canvas(bitmapWithReflection);
		// Draw in the original image
		canvas.drawBitmap(originalImage, 0, 0, null);
		// Draw in the gap
		Paint defaultPaint = new Paint();
		canvas.drawRect(0, height, width, height + reflectionGap, defaultPaint);
		// Draw in the reflection
		canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

		// Create a shader that is a linear gradient that covers the reflection
		Paint paint = new Paint();
		LinearGradient shader = new LinearGradient(0,
				originalImage.getHeight(), 0, bitmapWithReflection.getHeight()
						+ reflectionGap, 0x70ffffff, 0x00ffffff, TileMode.CLAMP);
		// Set the paint to use this shader (linear gradient)
		paint.setShader(shader);
		// Set the Transfer mode to be porter duff and destination in
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		// Draw a rectangle using the paint with our linear gradient
		canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
				+ reflectionGap, paint);

		return bitmapWithReflection;
	}
	
	public static AlertDialog createDailg(final Activity mActivity ,int titleId , int  contentId){
		
		final AlertDialog dialog=new AlertDialog.Builder(mActivity).create();
		dialog.show();
		dialog.setContentView(R.layout.dailog);
		TextView alert_title = (TextView) dialog.findViewById(R.id.alert_title);
		alert_title.setText(titleId);
		TextView message = (TextView) dialog.findViewById(R.id.message);
		message.setText(contentId);
		dialog.findViewById(R.id.alert_android_ok).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				mActivity.finish();
			}
		});
		dialog.findViewById(R.id.alert_android_cancel).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		return dialog;
	}
	
	/**
	 * 打开Gps对话框
	 * @param mActivity
	 * @param titleId
	 * @param contentId
	 * @return
	 */
	public static AlertDialog createDailgOpenGPS(final Activity mActivity ,int titleId , int  contentId){
		
		final AlertDialog dialog=new AlertDialog.Builder(mActivity).create();
		dialog.show();
		dialog.setContentView(R.layout.dailog);
		TextView alert_title = (TextView) dialog.findViewById(R.id.alert_title);
		alert_title.setText(titleId);
		TextView message = (TextView) dialog.findViewById(R.id.message);
		message.setText(contentId);
		dialog.findViewById(R.id.alert_android_ok).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
				mActivity.startActivityForResult(intent, Config.ActivitCode.OPENGPS_RESULTCODE); //此为设置完成后返回到获取界面
			}
		});
		dialog.findViewById(R.id.alert_android_cancel).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				Contact contact = (Contact) mActivity;
				contact.loadingUserInfo();
			}
		});
		dialog.setCancelable(false);
		return dialog;
	}
	
	public static AlertDialog createLoadingDailg(final Activity mActivity ,int  contentId){
		final AlertDialog dialog = new AlertDialog.Builder(mActivity).create();
		dialog.show();
		dialog.setContentView(R.layout.loading);
		if(contentId != 0){
			TextView loading_title = (TextView) dialog.findViewById(R.id.loading_title);
			loading_title.setText(contentId);
		}
		Handler handler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				View image = dialog.findViewById(R.id.loadingImage);
				((AnimationDrawable)image.getBackground()).start();
				super.handleMessage(msg);
			}
			
		};
		handler.sendMessageDelayed(handler.obtainMessage() , 10);
//		dialog.findViewById(R.id.alert_android_ok).setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				mActivity.finish();
//			}
//		});
//		dialog.findViewById(R.id.alert_android_cancel).setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				dialog.dismiss();
//			}
//		});
		dialog.setCancelable(false);
		return dialog;
	}
	
	/**
	 * 设置头像选择拍照还是文件
	 * @param mActivity
	 * @param titleId
	 * @return
	 */
	public static AlertDialog createSelectHeadDailog(final Activity mActivity ,int titleId ,boolean istry){
		final CreateNewNameCard nameCard = (CreateNewNameCard) mActivity;
		final AlertDialog dialog=new AlertDialog.Builder(mActivity).create();
		dialog.show();
		dialog.setContentView(R.layout.dailoghead);
		
		if(istry){
			dialog.findViewById(R.id.newnamecard_tryupload_it).setVisibility(View.VISIBLE);
			Button tryupload = (Button) dialog.findViewById(R.id.newnamecard_tryupload);
			tryupload.setVisibility(View.VISIBLE);
			tryupload.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					nameCard.uploadHead();
				}
			});
		}else{
			dialog.findViewById(R.id.newnamecard_tryupload_it).setVisibility(View.GONE);
			dialog.findViewById(R.id.newnamecard_tryupload).setVisibility(View.GONE);
		}
		TextView alert_title = (TextView) dialog.findViewById(R.id.alert_title);
		alert_title.setText(titleId);
		Button camer = (Button) dialog.findViewById(R.id.newnamecard_formcamer);
		camer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				 dialog.dismiss();
				 Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);    
	             intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Config.CAMER_PIC_HEAD_PATH)));    
	             mActivity.startActivityForResult(intent, PHTOS.PHOTOHRAPH); 
	            
			}
		});
		Button file = (Button) dialog.findViewById(R.id.newnamecard_formfile);
		file.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
			    intent.addCategory(Intent.CATEGORY_OPENABLE);
			    intent.setType("image/*");
			    intent.putExtra("crop", "true");
			    intent.putExtra("aspectX", 1);
			    intent.putExtra("aspectY", 1);
			    intent.putExtra("outputX", 72);
			    intent.putExtra("outputY", 72);
			    intent.putExtra("return-data", true);
			    mActivity.startActivityForResult(intent, PHTOS.PHOTORESOULT);
				
			}
		});
		dialog.findViewById(R.id.alert_android_cancel).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				nameCard.uploadHeadReset();
			}
		});
		return dialog;
	}
	
	/**
	 * 关闭软键盘输入法
	 * @param mActivity
	 */
	public static void closeInput(Activity mActivity) {
		InputMethodManager inputMethodManager = (InputMethodManager) mActivity
				.getSystemService(Context.INPUT_METHOD_SERVICE);

		inputMethodManager.hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(),

		InputMethodManager.HIDE_NOT_ALWAYS);
	}
	
	/**
	 * 取得文本框的值
	 * @param mActivity
	 * @param id
	 * @return
	 */
	public static String getEditTextValue(Activity mActivity , int id){
		return ((EditText)mActivity.findViewById(id)).getEditableText().toString();
	}
	
	public static void showShortToast(Context mContext,int durtion ,String msg){
		View layout = null ;
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layout = inflater.inflate(R.drawable.toast, null);
		TextView textView = (TextView) layout.findViewById(R.id.toast_text);
		textView.setText(msg);
		Toast toast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
		toast.setView(layout);
		toast.show();
	}
	
	public static void showShortToast(Context mContext,String msg){
		showShortToast(mContext, Toast.LENGTH_SHORT, msg);
	}
	
	public static void showShortToast(Context mContext ,int id){
		showShortToast(mContext , Toast.LENGTH_SHORT,mContext.getString(id));
	}
	
	public static void showToast(Context mContext , String msg){
		showShortToast(mContext,Toast.LENGTH_LONG , msg);
	}
	
	public static void showToast(Context mContext ,int id){
		showShortToast(mContext,Toast.LENGTH_LONG , mContext.getString(id));
	}
	
	public static void setActivityTitle(Activity mActivity , int titleStrId){
		View view = mActivity.findViewById(R.id.title_bar_title);
		if(view instanceof TextView ){
			((TextView)view).setText(titleStrId);
		}else{
			Log.e(TAG, "not set activity title");
		}
	}
	
	public static void setActivityTitle(Activity mActivity , String title){
		View view = mActivity.findViewById(R.id.title_bar_title);
		if(view instanceof TextView ){
			((TextView)view).setText(title);
		}else{
			Log.e(TAG, "not set activity title");
		}
	}
	
	/*
	public static boolean drawMPThum(Activity mActivity , CardInfo cardInfo ,int basePicId ){
		//TODO 还没有写完
		Resources re = mActivity.getResources();
		InputStream is = re.openRawResource(basePicId);
		BitmapDrawable mapdraw = new BitmapDrawable(is);
		Bitmap bitmap = mapdraw.getBitmap();
//		Bitmap to = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight());  
		Bitmap head = new BitmapDrawable(re.openRawResource(R.drawable.hi_mycard_bg_item_nor)).getBitmap();
		Canvas canvas = new Canvas();
		canvas.drawBitmap(bitmap, 0, 0, null);
		canvas.drawBitmap(head, 10, 10, null);
		canvas.save();
		return true;
	}
	*/
	
	/**
	 *  画出个人名片的缩略图
	 * @param mActivity
	 * @param cardInfo
	 * @param basePicId
	 * @return
	 */
	public static Bitmap drawMPThum(Activity mActivity ,CardInfo cardInfo, int basePicId ){
		//TODO 还没有写完
		Resources re = mActivity.getResources();
		InputStream is = re.openRawResource(basePicId);
		BitmapDrawable mapdraw = new BitmapDrawable(is);
		Bitmap bitmap = mapdraw.getBitmap();
		
		Bitmap rebit = Bitmap.createBitmap(bitmap.getWidth() , bitmap.getHeight() , android.graphics.Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(rebit);
		canvas.drawBitmap(bitmap, 0, 0, null);
		canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG)); 
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setTextSize(25);
		paint.setAntiAlias(true);
		canvas.drawText(cardInfo.getName(), 230, 113, paint );
		canvas.drawText("职务： "+ cardInfo.getDuty(), 80, 215, paint );
		canvas.drawText("手机号码： " + cardInfo.getMobile(), 80, 278, paint );
		canvas.drawText("邮件地址： " + cardInfo.getEmail(), 80, 340, paint );
		canvas.drawText("办公电话： " + cardInfo.getOfficeTel(), 80, 403, paint );
		canvas.save();
		return rebit;
	}
	/**
	 * 取得屏幕的大小
	 * @param mActivity
	 * @return
	 */
	public static DisplayMetrics getSrceent(Activity mActivity){
		DisplayMetrics  dm = new DisplayMetrics(); 
		mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm); 
		return dm;
	}
	
	/**
	 * 生成缩略图
	 * @param mActivity
	 * @param userInfo
	 * @param basePicId
	 * @return
	 */
	public static Bitmap savePhotoThum(Activity mActivity , UserInfo userInfo , int basePicId){
		PhotoInfo photoInfo = new PhotoInfo();
		photoInfo.setPhotoID(userInfo.getCardID().getId());
		Bitmap photoThum = drawMPThum(mActivity, userInfo.getCardInfo(), basePicId);
		photoInfo.setPhotoThum(photoThum );
		//ProviderUtil.insertOrUpdatePhotoInfo(mActivity, photoInfo);//插入到数据库中
		return photoThum;
	}
}
