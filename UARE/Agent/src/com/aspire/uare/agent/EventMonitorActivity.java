/**
 * @(#) EventMonitorActivity.java Created on 2012-11-12
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.uare.agent;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aspire.uare.agent.monitor.event.InputEventReader;

/**
 * The class <code>EventMonitorActivity</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class EventMonitorActivity extends Activity {

    boolean flag;

    InputEventReader inputEventReader;

    /**
     * {@inheritDoc}
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.monitor);
        super.onCreate(savedInstanceState);
    }

    public void keystart(View view) {
        if (!flag) {
            inputEventReader = new InputEventReader();
            inputEventReader.init();
            inputEventReader.start();
            flag = true;
            ((Button) view).setText("键盘监控停止");
        } else {
            inputEventReader.stop();
            flag = false;
            ((Button) view).setText("键盘监控启动");
        }
    }

    public void touchstart(View view) {
        // if (!touchflag) {
        // touchMonitor = new TouchMonitor(TOUCH_DEVICE);
        // touchMonitor.start();
        // touchflag = true;
        // ((Button)view).setText("屏幕监控停止");
        // } else {
        // touchMonitor.stop();
        // touchflag = false;
        // ((Button)view).setText("屏幕监控启动");
        // }
    }

}
