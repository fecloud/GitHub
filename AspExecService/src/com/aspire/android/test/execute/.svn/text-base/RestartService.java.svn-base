/**
 * @(#) RestartService.java Created on 2012-9-19
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute;

import android.content.Intent;
import android.os.IBinder;
import roboguice.service.RoboService;

/**
 * The class <code>RestartService</code>
 *
 * @author likai
 * @version 1.0
 */
public class RestartService extends RoboService{

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * (non-Javadoc)
     * @see roboguice.service.RoboService#onCreate()
     */
    @Override
    public void onCreate() {
        super.onCreate();
        ExecuteService executeService = new ExecuteService();
        if(executeService.executeThread == null){
            startActivity();
        }else{
            while(executeService.executeThread.isAlive()){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
            }
            startActivity();
        }
    }

    private void startActivity() {
        Intent i;
        i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
        startActivity(i);
        i = new Intent("com.aspire.android.test.execute.RestartService");
        stopService(i);
    }

    /**
     * (non-Javadoc)
     * @see roboguice.service.RoboService#onDestroy()
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    
}
