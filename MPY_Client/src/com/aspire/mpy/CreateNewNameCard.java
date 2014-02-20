package com.aspire.mpy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.aspire.mpy.bean.CardInfo;
import com.aspire.mpy.bean.UserInfo;
import com.aspire.mpy.config.Config;
import com.aspire.mpy.message.response.CreateNameCardResp;
import com.aspire.mpy.message.response.IResponseMsg;
import com.aspire.mpy.provider.ProviderUtil;
import com.aspire.mpy.util.HttpPostCallBack;
import com.aspire.mpy.util.HttpUtil;
import com.aspire.mpy.util.Tools;
import com.aspire.mpy.util.ViewUtil;

public class CreateNewNameCard extends Activity{

	protected CardInfo info;
	
	protected ImageView head;
	
	protected String headPath ; 
	
	protected boolean isuploadHead = true; //头像是否已成功上传
	protected boolean isuploadHeading= false; //头像是否上传中
	
	protected Integer photoID;//上传返回的头像id
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newnamecard);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);  
		head = (ImageView) findViewById(R.id.head);
	}
	
	protected void getCardInfo_View(){
		info.setName(ViewUtil.getEditTextValue(this, R.id.cardinfo_name));
		info.setDuty(ViewUtil.getEditTextValue(this, R.id.cardinfo_duty));
		info.setMobile(ViewUtil.getEditTextValue(this, R.id.cardinfo_mobile));
		info.setEmail(ViewUtil.getEditTextValue(this, R.id.cardinfo_email));
		info.setOfficeTel(ViewUtil.getEditTextValue(this, R.id.cardinfo_officeTel));
		info.setOfficeFax(ViewUtil.getEditTextValue(this, R.id.cardinfo_officeFax));
		info.setCompAddr(ViewUtil.getEditTextValue(this, R.id.cardinfo_compAddr));
		info.setCompName(ViewUtil.getEditTextValue(this, R.id.cardinfo_compName));
		info.setCompURL(ViewUtil.getEditTextValue(this, R.id.cardinfo_compURL));
		info.setExtrInfo(ViewUtil.getEditTextValue(this, R.id.cardinfo_extrInfo));
		info.setCreateTime("" + System.currentTimeMillis());
	}
	
	public void save(View view){
		if(null !=headPath && !isuploadHead){
			ViewUtil.showShortToast(this, R.string.newnamecard_heading_confim);
			return ;
		}
		
		info = new CardInfo();
		getCardInfo_View();
		ViewUtil.closeInput(this);
		HttpUtil.createNameCard(info, new HttpPostCallBack(this){
			
			@Override
			public void taskSucess(IResponseMsg respMsg) {
				CreateNameCardResp resp = (CreateNameCardResp) respMsg;
				if(null != resp.cardID){
					Tools.saveOrUpdatempy_id(CreateNewNameCard.this, resp.cardID);
					UserInfo userInfo = new UserInfo();
					userInfo.setCardID(resp.cardID);
					userInfo.setCardInfo(info);
					ProviderUtil.insertOrUpdateUserInfo(CreateNewNameCard.this, userInfo );
					
					 //上传头像
					if( null != headPath){
						//上传头像
						//uploadHead("" + resp.cardID.getId() , headPath);
					}else{
						ViewUtil.showShortToast(CreateNewNameCard.this, R.string.newnamecard_success);
						//跳转到绑定我的名片
						Intent newIntent = new Intent(CreateNewNameCard.this, BindNameCard.class);
						startActivity(newIntent);
						finish();
					}
					 
				}
			}

		});
	}
	
	/**
	 * 上传头像
	 */
	public void uploadHead(){
		if(null != headPath){
			isuploadHead = false;
			isuploadHeading = true;
			HttpUtil.uploadPhoto(headPath, new HttpPostCallBack(this , R.string.newnamecard_success) {
	
				@Override
				public void taskSucess(IResponseMsg respMsg) {
					isuploadHeading = false;
					super.taskSucess(respMsg);
					ViewUtil.showShortToast(CreateNewNameCard.this, R.string.newnamecard_success);
					isuploadHead = true;
					//保存到头像数据库
					ProviderUtil.saveOrUpdatePhoto(CreateNewNameCard.this, photoID, headPath);
				}

				@Override
				public void error() {
					isuploadHeading = false;
					ViewUtil.showShortToast(CreateNewNameCard.this, R.string.newnamecard_heading_confim);
				}
				
			});
		}
	}
	
	/**
	 * 取消上传头像
	 */
	public void uploadHeadReset(){
		headPath = null; 
		isuploadHead = true; //头像是否已成功上传
		isuploadHeading= false; //头像是否上传中
		photoID = null;//上传返回的头像id
		head.setImageResource(R.drawable.head);
		
	}
	
	public void cancel(View view){
		startActivity(new Intent(this, LoginSignup.class));
		this.finish();
	}
	
	/**
	 * 设置图片
	 */
	public void setPhoto(View view){
		if(!isuploadHeading){
			ViewUtil.createSelectHeadDailog(this, R.string.newnamecard_selecthaed , !isuploadHead);
		}else{
			ViewUtil.showShortToast(this, R.string.newnamecard_heading);
		}
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == PHTOS.NONE)    
            return;    
        // 拍照    
        if (requestCode == PHTOS.PHOTOHRAPH) {    
            //设置文件保存路径这里放在跟目录下    
            File picture = new File(Config.CAMER_PIC_HEAD_PATH);    
            startPhotoZoom(Uri.fromFile(picture));    
        }    
            
        if (data == null)    
            return;    
            
        // 读取相册缩放图片    
        if (requestCode == PHTOS.PHOTOZOOM) {    
            startPhotoZoom(data.getData());    
        }    
        // 处理结果    
        if (requestCode == PHTOS.PHOTORESOULT) {    
            Bundle extras = data.getExtras();    
            if (extras != null) {    
                Bitmap photo = extras.getParcelable("data");    
				try {
					FileOutputStream stream = new FileOutputStream(new File(Config.CAMER_PIC_HEAD_PATH));
					 photo.compress(Bitmap.CompressFormat.JPEG, 75, stream);// (0 - 100)压缩文件    
					 stream.flush();
					 stream.close();
		             head.setImageBitmap(photo);  
		             headPath = Config.CAMER_PIC_HEAD_PATH;
		             //开始上传
		             uploadHead();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}    
                 
            }    
        }
	}
        
        public void startPhotoZoom(Uri uri) {    
            Intent intent = new Intent("com.android.camera.action.CROP");    
            intent.setDataAndType(uri, PHTOS.IMAGE_UNSPECIFIED);    
            intent.putExtra("crop", "true");    
            // aspectX aspectY 是宽高的比例    
            intent.putExtra("aspectX", 1);    
            intent.putExtra("aspectY", 1);    
            // outputX outputY 是裁剪图片宽高    
            intent.putExtra("outputX", 72);    
            intent.putExtra("outputY", 72);    
            intent.putExtra("return-data", true);    
            startActivityForResult(intent, PHTOS.PHOTORESOULT);    
        }
	
        
	public interface PHTOS {
		
		public static final String IMAGE_UNSPECIFIED = "image/*";
		
		public static final int NONE = 0;
		
		public static final int PHOTOHRAPH = 1;// 拍照
		
		public static final int PHOTOZOOM = 2; // 缩放
		
		public static final int PHOTORESOULT = 3;// 结果
	}
	
}
