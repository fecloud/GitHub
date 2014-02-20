package com.aspire.mpy.contact;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.aspire.mpy.CreateNewNameCard;
import com.aspire.mpy.R;
import com.aspire.mpy.bean.CardID;
import com.aspire.mpy.bean.CardInfo;
import com.aspire.mpy.bean.UserInfo;
import com.aspire.mpy.config.Config;
import com.aspire.mpy.message.response.IResponseMsg;
import com.aspire.mpy.message.response.ModifyNameCardResp;
import com.aspire.mpy.provider.ProviderUtil;
import com.aspire.mpy.util.HttpPostCallBack;
import com.aspire.mpy.util.HttpUtil;
import com.aspire.mpy.util.Tools;
import com.aspire.mpy.util.ViewUtil;

public class ContactEdit extends CreateNewNameCard {
	
	private static final String TAG = "ContactEdit";
	
	private Integer id; // 联系人名片标识
	
	private void setValue (){
		UserInfo userInfo = ProviderUtil.getUserInfoByID(this, id);
		CardInfo cardInfo = userInfo.getCardInfo();
		((EditText)findViewById(R.id.cardinfo_name)).setText(cardInfo.getName());
		((EditText)findViewById(R.id.cardinfo_duty)).setText(cardInfo.getDuty());
		((EditText)findViewById(R.id.cardinfo_mobile)).setText(cardInfo.getMobile());
		((EditText)findViewById(R.id.cardinfo_email)).setText(cardInfo.getEmail());
		((EditText)findViewById(R.id.cardinfo_officeTel)).setText(cardInfo.getOfficeTel());
		((EditText)findViewById(R.id.cardinfo_officeFax)).setText(cardInfo.getOfficeFax());
		((EditText)findViewById(R.id.cardinfo_compAddr)).setText(cardInfo.getCompAddr());
		((EditText)findViewById(R.id.cardinfo_compName)).setText(cardInfo.getCompName());
		((EditText)findViewById(R.id.cardinfo_compURL)).setText(cardInfo.getCompURL());
		((EditText)findViewById(R.id.cardinfo_extrInfo)).setText(cardInfo.getExtrInfo());
		setHead(userInfo);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		id = getIntent().getIntExtra("id", 0);
		Log.i(TAG, "onCreate get id :" + id);
		ViewUtil.setActivityTitle(this, R.string.contactedit_title);
		setValue();
	}

	@Override
	public void save(View view) {
		if(null !=headPath && !isuploadHead){
			ViewUtil.showShortToast(this, R.string.newnamecard_heading_confim);
			return ;
		}
		info = new CardInfo();
		getCardInfo_View();
		ViewUtil.closeInput(this);
		final UserInfo userInfo = new UserInfo();
		userInfo.setCardInfo(info);
		userInfo.setCardID(Tools.get_mpy_id(this));
		HttpUtil.modifyNameCard(userInfo, Tools.getBindInfo(this), new HttpPostCallBack(this) {

			@Override
			public void taskSucess(IResponseMsg respMsg) {
				ViewUtil.showShortToast(getBaseContext(),R.string.contactedit_save_success );
				ModifyNameCardResp resp = (ModifyNameCardResp) respMsg;
				CardID reCardID = resp.cardID;
				Tools.saveOrUpdatempy_id(ContactEdit.this, reCardID);
				userInfo.setCardID(reCardID);
				ProviderUtil.insertOrUpdateUserInfo(getBaseContext(), userInfo);
				super.taskSucess(respMsg);
				setResult(Config.ActivitCode.ContactEdit_RESULTCODE); //设置返回值
				finish();
			}
			
		});
	}
	
	/**
	 * 设置用户的头像 显示
	 * @param userInfo
	 */
	private void setHead(UserInfo userInfo){
		if(null != userInfo && null != userInfo.getCardInfo().getPhotoID()){
			Bitmap bitmap = ProviderUtil.getPhoto(this, userInfo.getCardInfo().getPhotoID());
			head.setImageBitmap(bitmap);
		}
	}
	
}
