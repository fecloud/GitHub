/**
 * 
 */
package com.braver.bluetoothchat;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.braver.bluetoothchat.util.Utils;

/**
 * @author braver
 * 
 */
public class StartActivity extends Activity {

	private static final String TAG = "StartActivity";

	private static final int REQUEST_ENABLE_BT = 1;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		if (!Utils.haveBluetoothAdapter()) {
			// 没有蓝牙适配器,退出
		} else if (!Utils.bluetoothIsEnable()) {
			// 没有打开蓝牙
			// Utils.enableBluetoothAdapter();
			Intent enableBtIntent = new Intent(
					BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
			Log.d(TAG, "enable bluetoothAdapter");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onActivityResult(int, int,
	 * android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case REQUEST_ENABLE_BT:
			
			break;

		default:
			break;
		}
	}

}
