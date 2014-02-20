/**
 * @(#) MainActivity.java Created on 2013-4-9
 *
 * Copyright (c) 2013 com.braver All Rights Reserved
 */
package com.braver.android;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.braver.android.screen.capture.R;
import com.braver.android.screen.capture.ScreenCapService;

/**
 * The class <code>MainActivity</code>
 * 
 * @author braver
 * @version 1.0
 */
public class MainActivity extends Activity {

	private ServiceState serviceState;

	private Intent capIntent = new Intent(ScreenCapService.ACTION);

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.main);
		super.onCreate(savedInstanceState);
		serviceState = new ServiceState();
		registerReceiver(serviceState, new IntentFilter(
				ScreenCapService.STATE_ACTION));
		startService(capIntent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		unregisterReceiver(serviceState);
		stopService(capIntent);
		super.onDestroy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		final MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onMenuItemSelected(int, android.view.MenuItem)
	 */
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		this.finish();
		return super.onMenuItemSelected(featureId, item);
	}

	private final class ServiceState extends BroadcastReceiver {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * android.content.BroadcastReceiver#onReceive(android.content.Context,
		 * android.content.Intent)
		 */
		@Override
		public void onReceive(Context context, Intent intent) {
			final int state = intent.getIntExtra("state", 0);

		}

	}

}
