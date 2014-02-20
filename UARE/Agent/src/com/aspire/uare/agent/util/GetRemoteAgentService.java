/**
 * @(#) GetRemoteGrabScreen.java Created on 2012-10-19
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.uare.agent.util;

import com.aspire.uare.agent.service.RemoteAgentService;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

/**
 * The class <code>GetRemoteGrabScreen</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class GetRemoteAgentService implements ServiceConnection {

    private static final String TAG = "GetRemoteAgentService";

    private static final Intent INTENT = new Intent("com.inspurworld.agent.service.ScreenService");

    private boolean isconneted;

    private Context mcContext;

    private RemoteAgentService remoteAgentService;

    public GetRemoteAgentService(Context mcContext) {
        super();
        this.mcContext = mcContext;
    }

    public void bind() {
        if (!isconneted) {
            mcContext.bindService(INTENT, this, Context.BIND_AUTO_CREATE);
        }
    }

    public void unBind() {
        mcContext.unbindService(this);
        this.isconneted = false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see android.content.ServiceConnection#onServiceConnected(android.content.ComponentName, android.os.IBinder)
     */
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        remoteAgentService = RemoteAgentService.Stub.asInterface(service);
        Log.d(TAG, "onServiceConnected");
        this.isconneted = true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see android.content.ServiceConnection#onServiceDisconnected(android.content.ComponentName)
     */
    @Override
    public void onServiceDisconnected(ComponentName name) {
        this.isconneted = false;

    }

    public RemoteAgentService getRemoteAgentService() {
        bind();
        for (int i = 1000; i > 0; i--) {
            if (remoteAgentService != null) {
                break;
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        }
        return remoteAgentService;
    }
}
