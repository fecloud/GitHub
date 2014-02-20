package android.mid.activity;

import android.app.Activity;
import android.mid.R;
import android.os.Bundle;
import android.view.View;

import com.aspire.mose.frame.service.PushMessageService;

public class MainActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void startService(View view) {
		PushMessageService.getInstance(this).startService();
	}

	public void stopService(View view) {
		PushMessageService.getInstance(this).stopService();
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		PushMessageService.getInstance(this).stopService();
		System.exit(0);
	}
	
}
