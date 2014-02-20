package com.aspire.mpy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.aspire.mpy.bean.BindInfo;
import com.aspire.mpy.bean.CardID;
import com.aspire.mpy.contact.Contact;
import com.aspire.mpy.message.response.IResponseMsg;
import com.aspire.mpy.util.HttpPostCallBack;
import com.aspire.mpy.util.HttpUtil;
import com.aspire.mpy.util.Tools;
import com.aspire.mpy.util.ViewUtil;

/**
 * 绑定名片
 * @author Administrator
 *
 */
public class BindNameCard extends Activity {

	private CardID cardID;
	
	private BindInfo info;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bindnamecard);
	}
	
	public void bind(View view){
		cardID = Tools.get_mpy_id(this);
		
		if(getBindInfoForView()){
			//输入验证成功
			HttpUtil.bindNameCard(cardID, info, new HttpPostCallBack(this) {

				@Override
				public void taskSucess(IResponseMsg respMsg) {
					//绑定成功了,在config中加Login = true;用户再次进入时 不用绑定
					Tools.saveOrUpdateBindInfo(BindNameCard.this, info);
					Tools.saveUserLoginState(BindNameCard.this, true);
					startActivity(new Intent(BindNameCard.this, Contact.class)); // 在联系人界面
					finish();
				}
				
			});
		}
		
	}
	
	
	private boolean getBindInfoForView(){
		String bindStr = ViewUtil.getEditTextValue(this, R.id.bindnamecard_bindStr);
		String password = ViewUtil.getEditTextValue(this, R.id.bindnamecard_password);
		if(null == bindStr || "".equals(bindStr.trim())){
			ViewUtil.showShortToast(this, R.string.bindnamecard_BindStr_txt_hint);
		}else if(null == password || "".equals(password.trim())){
			ViewUtil.showShortToast(this, R.string.bindnamecard_Password_txt_hint);
		}else{
			info = new BindInfo();
			info.setBindStr(bindStr);
			info.setPassword(password);
			return true;
		}
		return false;
	}
	
	public void cancel(View view){
		startActivity(new Intent(this, LoginSignup.class));
		this.finish();
	}
}
