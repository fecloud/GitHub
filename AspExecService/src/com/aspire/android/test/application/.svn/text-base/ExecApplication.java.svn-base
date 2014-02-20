/**
 * @(#) ExecApplication.java Created on 2012-7-23
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import roboguice.application.RoboApplication;

import com.aspire.android.daemon.remote.aidl.DaemonRemoteServiceManager;
import com.aspire.android.test.ExecuteServiceModule;
import com.aspire.android.test.OphoneNetWork;
import com.aspire.android.test.log.RunLogger;
import com.aspire.android.test.server.ExecServer;
import com.google.inject.Inject;
import com.google.inject.Module;

/**
 * The class <code>ExecApplication</code>
 * 
 * @author wuyanlong
 * @version 1.0
 */
public class ExecApplication extends RoboApplication {

    private RunLogger runLogger = null;
    /**用来判断是否是ophone，以便来激活网络*/
    private OphoneNetWork netWork = null;
    @Inject
    private ExecServer execServer;
    /**
     * Out of instance
     */
    private static ExecApplication mOurInstance;
    private HashSet<Long> redoTaskHash;
    private List<Long[]> uploadList;

    /**
     * Constructor
     */
    public ExecApplication() {
        mOurInstance = this;

        redoTaskHash = new HashSet<Long>();
        uploadList = new ArrayList<Long[]>();
    }

    /**
     * Instance
     * 
     * @return
     */
    public static ExecApplication instance() {
        return mOurInstance;
    }

    @Override
    protected void addApplicationModules(List<Module> modules) {
        modules.add(new ExecuteServiceModule());
    }

    /**
     * (non-Javadoc)
     * 
     * @see android.app.Application#onCreate()
     */
    @Override
    public void onCreate() {
        super.onCreate();
        runLogger = RunLogger.getInstance();
        runLogger.debug(getClass(), "--------ExecApplication onCreate!--------");
        netWork = new OphoneNetWork(this);
        netWork.ActiveNetWorkByMode("wap");
        netWork.ActiveNetWorkByMode("internet");
    }

    /**
     * (non-Javadoc)
     * 
     * @see android.app.Application#onTerminate()
     */
    @Override
    public void onTerminate() {
        runLogger.debug(getClass(), "--------ExecApplication onTerminate!--------");
        netWork.DestroyNetWorkByMode("wap");
        netWork.DestroyNetWorkByMode("internet");
        DaemonRemoteServiceManager.instance().destory();
        execServer.destory();
        super.onTerminate();
    }

    /**
     * Getter of redoTaskHash
     * 
     * @return the redoTaskHash
     */
    public HashSet<Long> getRedoTaskHash() {
        return redoTaskHash;
    }

    public List<Long[]> getUploadList() {
        return uploadList;
    }

}
