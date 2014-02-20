/**
 * @(#) MainActivity.java Created on 2012-9-14
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * The class <code>MainActivity</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class MainActivity extends Activity implements OnClickListener {

    Intent service = new Intent("com.aspire.android.AlarmSerivce");

    private Button btn;

    /**
     * {@inheritDoc}
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.main);
        super.onCreate(savedInstanceState);
        this.btn = (Button) findViewById(R.id.btn);
        this.btn.setOnClickListener(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    public void onClick(View v) {
        startService(service);
    }

    /**
     * {@inheritDoc}
     * 
     * @see android.app.Activity#onDestroy()
     */
    @Override
    protected void onDestroy() {
        stopService(service);
        super.onDestroy();
    }
}
