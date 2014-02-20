package com.aspire.mpy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.view.View;

import com.aspire.mpy.contact.Contact;
import com.aspire.mpy.util.Tools;
import com.aspire.mpy.util.ViewUtil;

public class LoginSignup extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginsignup);
		boolean isLogin = Tools.getUserLoginState(this);
		if(isLogin){
			startActivity(new Intent(this, Contact.class)); // 跳转到联系人界面
			finish();
		}
	}

	public void createNew(View view){
		Intent intent = new Intent(this,CreateNewNameCard.class);
		startActivity(intent);
		super.finish();
	}
	
	public void login(View view){
		Intent intent = new Intent(this,LoginNameCard.class);
		startActivity(intent);
		super.finish();
	}
	
	
	@Override
	public void onBackPressed() {
		 ViewUtil.createDailg(this, R.string.exit_title, R.string.exit_confm);
	}
	
	@Override
	public void finish() {
		super.finish();
		Process.killProcess(Process.myPid());
		System.exit(1);
	}
}
