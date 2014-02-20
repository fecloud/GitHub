/**
 * @(#) ExecuteService.java Created on 2012-4-23
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute;

import java.util.List;

import roboguice.service.RoboService;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.aspire.android.common.exception.ExceptionHandler;
import com.aspire.android.common.exception.MException;
import com.aspire.android.common.share.AspShareUtil;
import com.aspire.android.test.execute.EsStatusManager.ExecuteStatus;
import com.aspire.android.test.execute.entity.ExecuteSet;
import com.aspire.android.test.log.RunLogger;
import com.aspire.android.test.result.sync.DoUpload;
import com.aspire.android.test.script.service.IScriptService;
import com.aspire.android.test.servicekey.service.IServiceKeyService;
import com.aspire.android.test.sync.ISyncService;
import com.aspire.android.test.task.service.IClearDbService;
import com.aspire.android.test.task.service.ITaskService;
import com.google.inject.Inject;

/**
 * The class <code>ExecuteService</code>
 * 
 * @author linjunsui
 * @version 1.0
 */
public class ExecuteService extends RoboService {

    private RunLogger runLogger = RunLogger.getInstance();

    /**
     * Sleep intervals, in ms
     */
    private static final int SLEEP_INTERVALS = 3000;

    @Inject
    protected ITaskService mITaskService;
    @Inject
    protected IScriptService mIScriptService;
    @Inject
    protected IServiceKeyService serviceKeyService;
    @Inject
    protected DoUpload mDoUpload;
    @Inject
    protected IClearDbService mIClearDbService;

    private boolean threadStatus;

    /**
     * syncService
     */
    @Inject
    protected ISyncService syncService;

    /**
     * task to be execute
     */
    protected List<ExecuteSet> executeList;

    /**
     * 
     * The class <code>ExecuteThread</code>
     * 
     * @author linjunsui
     * @version 1.0
     */
    public class ExecuteThread extends Thread {

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Thread#run()
         */
        @Override
        public void run() {
            if (threadStatus) {
                Log.d("ExecuteThread", "threadStatus: " + threadStatus);
                try {
                    syncService.deviceRegister();
                } catch (Exception e) {
                    runLogger.error(ExecuteService.class,
                            "Error while register device, and errmessage is " + e.getMessage());
                }
                EsStatusManager.instance().setState(ExecuteStatus.ES_RUNNING);
                while (threadStatus) {
                    // 设置运行状态
                    AspShareUtil.setWorking(ExecuteService.this, true);

                    try {
                        if (threadStatus) {
                            EsStatusManager.instance().setState(ExecuteStatus.MOTION_STATE);
                            syncService.deviceStatusUpdate(ISyncService.STATUES_OCCUPY);
                            // 案例重跑
                            mITaskService.caseReiteration();
                            // 上报测试结果
                            mDoUpload.uploadTestResult();
                            // 运行任务
                            mITaskService.motionTask();
                            // 运行中-不在任务调度中
                            syncService.deviceStatusUpdate(ISyncService.STATUES_IDLE);
                        }
                    } catch (MException e) {
                        runLogger.error(ExecuteService.class,
                                "Error while execute task, and errmessage is " + e.getMessage());
                    }
                    EsStatusManager.instance().setState(ExecuteStatus.UNMOTION_STATE);
                    // update password
                    try {
                        if (threadStatus) {
                            syncService.updatePassword();
                        }
                    } catch (Exception e) {
                        runLogger.error(ExecuteService.class,
                                "Error while update password, and errmessage is " + e.getMessage());
                    }

                    try {
                        if (threadStatus) {
                            syncService.uploadResultResponser();
                        }
                    } catch (Exception e) {
                        runLogger.error(ExecuteService.class,
                                "Error while download result respfile, and errmessage is " + e.getMessage());
                    }

                    // upload Result
                    try {
                        if (threadStatus) {
                            syncService.uploadResult();
                        }
                    } catch (Exception e) {
                        runLogger.error(ExecuteService.class,
                                "Error while upload Result, and errmessage is " + e.getMessage());
                    }

                    // download servicekeys
                    try {
                        if (threadStatus) {
                            syncService.downloadServiceKeys();
                        }
                    } catch (Exception e) {
                        runLogger.error(ExecuteService.class, "Error while download servicekeys, and errmessage is "
                                + e.getMessage());
                    }

                    // download testcase
                    try {
                        if (threadStatus) {
                            syncService.downloadTestCase();
                        }
                    } catch (Exception e) {
                        runLogger.error(ExecuteService.class,
                                "Error while dowload TestCase, and errmessage is " + e.getMessage());
                    }

                    // download Task
                    try {
                        if (threadStatus) {
                            syncService.downloadTask();
                        }
                    } catch (Exception e) {
                        runLogger.error(ExecuteService.class,
                                "Error while dowload Task, and errmessage is " + e.getMessage());
                    }

                    // 设置运行状态
                    AspShareUtil.setWorking(ExecuteService.this, false);

                    try {
                        sleep(SLEEP_INTERVALS);
                    } catch (InterruptedException e) {
                        ExceptionHandler.handle(e, "");
                    }
                    // 定时清理数据库
                    try {
                        mIClearDbService.TaskCear(365);
                        mIClearDbService.TestResultCear(62);
                        mIClearDbService.upResultCear(32);
                    } catch (MException e) {
                        runLogger.error(ExecuteService.class,
                                "Error while clear DB, and errmessage is " + e.getMessage());
                    }
                    // 定时数据文件
                    try {
                        syncService.deleteSyncFile();
                    } catch (MException e) {
                        runLogger.error(ExecuteService.class, "Error while file, and errmessage is " + e.getMessage());
                    }

                }
            }
            EsStatusManager.instance().setState(ExecuteStatus.ES_STOP_SERVICE);
        }
    }

    /**
     * excute thread
     */
    public ExecuteThread executeThread;

    /**
     * The class <code>ExecuteServiceBinder</code>
     * 
     * @author linjunsui
     * @version 1.0
     */
    public class ExecuteServiceBinder extends Binder {
        /**
         * getService
         * 
         * @return ExecuteService
         */
        public ExecuteService getService() {
            return ExecuteService.this;
        }
    }

    /**
     * Getter of syncService
     * 
     * @return the syncService
     */
    public ISyncService getSyncService() {
        return syncService;
    }

    /**
     * Setter of syncService
     * 
     * @param syncService
     *            the syncService to set
     */
    public void setSyncService(ISyncService syncService) {
        this.syncService = syncService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Service#onCreate()
     */
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            Log.v(getClass().getSimpleName(), "service start");
            syncService.initialSync();
            mDoUpload.initialUpload();
        } catch (MException e) {
            runLogger
                    .error(ExecuteService.class,
                            "SyncService or DoUpload initial preference in ExecuteService, and errmessage is "
                                    + e.getMessage());
        }
        threadStatus = true;
        executeThread = new ExecuteThread();
        executeThread.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * s (non-Javadoc)
     * 
     * @see android.app.Service#onDestroy()
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            runLogger.debug(getClass(), "executeServer on destroy!");
            syncService.deviceStatusUpdate(ISyncService.STATUES_IDLE);
            threadStatus = false;
        } catch (MException e) {
            runLogger.error(ExecuteService.class,
                    "error while set device status is idle, and errmessage is " + e.getMessage());
        }
    }

}
