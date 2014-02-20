/**
 * @(#) AgentApplication.java Created on 2012-10-23
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.uare.agent;

import android.app.Application;
import android.util.Log;

import com.aspire.uare.agent.util.GetRemoteAgentService;

/**
 * The class <code>AgentApplication</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class AgentApplication extends Application implements Runnable {

    private GetRemoteAgentService agentService;

    private static final String TAG = "AgentApplication";
    
    private Thread thread;

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        Log.d(TAG, "run");
        if (agentService == null) {
            agentService = new GetRemoteAgentService(this);
            agentService.bind();
        }

    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        super.onCreate();
        startScreenService();
    }

    public void startScreenService() {
        Log.d(TAG, "startScreenService");
        if (null == thread) {
            thread = new Thread(this);
            thread.start();
        }

    }

    public GetRemoteAgentService getAgentService() {
        if (null == agentService) {
            startScreenService();
        }
        return agentService;
    }

    public void setAgentService(GetRemoteAgentService agentService) {
        this.agentService = agentService;
    }

}
