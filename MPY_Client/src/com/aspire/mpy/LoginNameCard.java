package com.aspire.mpy;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.aspire.mpy.bean.BindInfo;
import com.aspire.mpy.contact.Contact;
import com.aspire.mpy.message.response.IResponseMsg;
import com.aspire.mpy.message.response.LoginNameCardResp;
import com.aspire.mpy.util.HttpPostCallBack;
import com.aspire.mpy.util.HttpUtil;
import com.aspire.mpy.util.Tools;
import com.aspire.mpy.util.ViewUtil;

public class LoginNameCard extends Activity {
	
	private BindInfo bindInfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginnamecard);
	}
	
	public void login(View view){
//		if(getBindInfoForView()){
//			//输入验证成功
//			HttpUtil.loginNameCard(bindInfo, new HttpPostCallBack(LoginNameCard.this) {
//
//				@Override
//				public void taskSucess(IResponseMsg respMsg) {
//					
//					LoginNameCardResp resp = (LoginNameCardResp) respMsg;
//					if(null != resp.cardID){
//						//绑定成功了,在config中加Login = true;用户再次进入时 不用绑定
//						Tools.saveOrUpdateBindInfo(LoginNameCard.this, bindInfo);
//						Tools.saveUserLoginState(LoginNameCard.this, true);
//						//保存名片信息
//						Tools.saveOrUpdatempy_id(LoginNameCard.this, resp.cardID);
//						ViewUtil.showShortToast(LoginNameCard.this, R.string.loginnamecard_sucess);
//						startActivity(new Intent(LoginNameCard.this, Contact.class)); // 在联系人界面
//						finish();
//					}
//					
//				}
//				
//			});
//		}
//		AsyncTask<Params, Progress, Result>
	}
	
	public void cancel(View view){
		startActivity(new Intent(this, LoginSignup.class));
		this.finish();
	}

	private boolean getBindInfoForView(){
		String bindStr = ViewUtil.getEditTextValue(this, R.id.loginnamecard_bindStr);
		String password = ViewUtil.getEditTextValue(this, R.id.loginnamecard_password);
		if(null == bindStr || "".equals(bindStr.trim())){
			ViewUtil.showShortToast(this, R.string.bindnamecard_BindStr_txt_hint);
		}else if(null == password || "".equals(password.trim())){
			ViewUtil.showShortToast(this, R.string.bindnamecard_Password_txt_hint);
		}else{
			bindInfo = new BindInfo();
			bindInfo.setBindStr(bindStr);
			bindInfo.setPassword(password);
			return true;
		}
		return false;
	}
	
}
