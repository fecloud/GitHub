/**
 * @(#) TestDemoActivity.java Created on 2012-5-16
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute.ui;

import java.util.Iterator;
import java.util.Map.Entry;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.aspire.android.test.execute.CommandConstants;
import com.aspire.android.test.execute.ContentValues;
import com.aspire.android.test.execute.ITestService;
import com.aspire.android.test.execute.R;

/**
 * The class <code>TestDemoActivity</code>
 *
 * @author ouyangfeng
 * @version 1.0
 */
public class TestDemoActivity extends Activity implements OnClickListener{

    /**
     * execute service
     */
    protected ITestService testService; 
    
    /**
     * service connection
     */
    protected ServiceConnection serviceConnection = new ServiceConnection() {  
        public void onServiceConnected(ComponentName className, IBinder service) {  
            try {
                testService = ITestService.Stub.asInterface(service);
                ContentValues params = new ContentValues();
                params.put("boolean", true);
                params.put("int", 1);
                try {
                    ContentValues result = testService.execute(1, params);
                    for (Iterator<Entry<String, Object>> iterator = result.valueSet().iterator(); iterator.hasNext();) {
                        Entry<String, Object> entry =  iterator.next();
                        System.out.print(entry.getKey());
                        System.out.print(entry.getValue());
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Enviroment", "onServiceConnected", e);
            }
        }  
  
        public void onServiceDisconnected(ComponentName className) {  
            testService = null;  
        }  
    };
    
    /**
     * (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testdemo);
        findViewById(R.id.btnruntestcase).setOnClickListener(this);
        Intent intent = new Intent("com.aspire.android.test.execute.TestService");
        this.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    /**
     * (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    public void onClick(View v) {
        if(null == testService){
            Toast.makeText(this, "testService bind fail", Toast.LENGTH_SHORT).show();
        }else {
//            final ContentValues params = new ContentValues();
//            final String className = "com.aspire.android.test.demo.DemoTestCase";
//            params.put(CommandConstants.KEY_CASE_CLASSNAME, className);
            String receivers = "18665934114";
            String content = "1234567890";
            final ContentValues params = new ContentValues(2);
            params.put(CommandConstants.KEY_SENDMESSAGE_RECEIVERS, receivers);
            params.put(CommandConstants.KEY_SENDMESSAGE_CONTENT, content);
            try {
                testService.execute(CommandConstants.TYPE_RUNTEST, params );
                final ContentValues result = testService.execute(CommandConstants.TYPE_SENDMESSAGE, params);
                Toast.makeText(this,"" + result.getAsBoolean(CommandConstants.KEY_METHOD_RESULT), Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }
    
    /**
     * (non-Javadoc)
     * @see android.app.Activity#onDestroy()
     */
    @Override
    protected void onDestroy() {
        unbindService(serviceConnection);
        super.onDestroy();
    }
    
}
