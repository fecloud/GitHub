/**
 * @(#) MainActivity.java Created on 2013-1-10
 *
 * Copyright (c) 2013 Aspire. All Rights Reserved
 */
package com.aspire.input.event.monitor;

import com.aspire.mbre.agent.monitor.event.InputEventReader;
import com.aspire.mbre.agent.monitor.event.key.KeyEventProcess;
import com.aspire.mbre.agent.monitor.event.key.KeyEventProcess.KeyEventListener;
import com.aspire.mbre.agent.monitor.event.screen.ScreenEventProcess;
import com.aspire.mbre.agent.monitor.event.screen.ScreenEventProcess.ScreenEventListener;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * The class <code>MainActivity</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class MainActivity extends Activity implements OnClickListener, KeyEventListener, ScreenEventListener {

    private static final String TAG = "MainActivity";

    private boolean flag;

    private Button button;

    private InputEventReader reader;

    private InputBroadcastReceiver receiver = new InputBroadcastReceiver();

    /**
     * {@inheritDoc}
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        button = (Button) findViewById(R.id.start_btn);
        button.setOnClickListener(this);

        final IntentFilter filter = new IntentFilter();
        filter.addAction(InputBroadcastReceiver.START_ACTION);
        filter.addAction(InputBroadcastReceiver.STOP_ACTION);
        registerReceiver(receiver, filter);

    }

    /**
     * {@inheritDoc}
     * 
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
        if (!flag) {
            start();
        } else {
            stop();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see android.app.Activity#onDestroy()
     */
    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    public void start() {
        Log.d(TAG, "start... start");
        flag = true;
        if (null == reader) {
            reader = new InputEventReader();
            reader.addInputEventProcess(new KeyEventProcess(this));
            reader.addInputEventProcess(new ScreenEventProcess(this));
            reader.init();
        }
        reader.start();
        button.setText("停止");
        Log.d(TAG, "start... end");
    }

    public void stop() {
        Log.d(TAG, "stop....start");
        flag = false;

        if (null != reader) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    reader.stop();
                }
            }).start();
        }

        button.setText("开始");

        Log.d(TAG, "stop....end");
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.mbre.agent.monitor.event.key.KeyEventProcess.KeyEventListener#onKeyDown(int)
     */
    @Override
    public void onKeyDown(int keyCode) {
        Log.d(TAG, "onKeyDown keyCode:" + keyCode);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.mbre.agent.monitor.event.key.KeyEventProcess.KeyEventListener#onKeyUp(int)
     */
    @Override
    public void onKeyUp(int keyCode) {
        Log.d(TAG, "onKeyUp keyCode:" + keyCode);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.mbre.agent.monitor.event.screen.ScreenEventProcess.ScreenEventListener#onTouch(int, int)
     */
    @Override
    public void onTouch(int x, int y) {
        Log.d(TAG, "onTouch x:" + x + " y:" + y);
    }

    /**
     * 
     * The class <code>InputBroadcastReceiver</code>
     * 
     * @author ouyangfeng
     * @version 1.0
     */
    private class InputBroadcastReceiver extends BroadcastReceiver {

        private static final String START_ACTION = "com.aspire.input.event.monitor.InputBroadcastReceiver.Start";

        private static final String STOP_ACTION = "com.aspire.input.event.monitor.InputBroadcastReceiver.Stop";

        /**
         * {@inheritDoc}
         * 
         * @see android.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
         */
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (action.equals(START_ACTION)) {
                start();
            } else if (action.equals(STOP_ACTION)) {
                stop();
            }
        }

    }

}
