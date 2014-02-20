/**
 * @(#) RequestHandle.java Created on 2012-7-25
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.server;

import java.io.File;
import java.util.List;

import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;

import com.aspire.android.common.exception.MException;
import com.aspire.android.test.PreferencesManager;
import com.aspire.android.test.execute.NameConstants;
import com.aspire.android.test.execute.NameConstants.SharedPrefConstants;
import com.aspire.android.test.execute.ui.PrefsActivity;
import com.aspire.android.test.log.RunLogger;
import com.aspire.android.test.task.service.OperateResultFromPc;
import com.aspire.android.util.FileUtil;
import com.aspire.sqmp.mobilemanager.entity.CPage;
import com.aspire.sqmp.mobilemanager.entity.MobileSharedFile;
import com.aspire.sqmp.mobilemanager.entity.ResultQueryBuilder;
import com.aspire.sqmp.mobilemanager.entity.TestResults;
import com.aspire.sqmp.mobilemanager.service.request.AspCommand;
import com.aspire.sqmp.mobilemanager.service.request.AspRequest;
import com.aspire.sqmp.mobilemanager.service.request.AspResponse;
import com.aspire.sqmp.mobilemanager.service.request.GCaseRequest;
import com.aspire.sqmp.mobilemanager.service.request.GCaseResponse;
import com.aspire.sqmp.mobilemanager.service.request.GResultRequest;
import com.aspire.sqmp.mobilemanager.service.request.GResultResponse;
import com.aspire.sqmp.mobilemanager.service.request.GSettingRequest;
import com.aspire.sqmp.mobilemanager.service.request.GSettingResponse;
import com.aspire.sqmp.mobilemanager.service.request.GStoreInfoReqeust;
import com.aspire.sqmp.mobilemanager.service.request.GStoreInfoResponse;
import com.aspire.sqmp.mobilemanager.service.request.QueryTestResultRequest;
import com.aspire.sqmp.mobilemanager.service.request.QueryTestResultResponse;
import com.aspire.sqmp.mobilemanager.service.request.SSettingRequest;
import com.aspire.sqmp.mobilemanager.service.request.SSettingResponse;
import com.aspire.sqmp.mobilemanager.service.request.STaskRequest;
import com.aspire.sqmp.mobilemanager.service.request.STaskResponse;
import com.aspire.sqmp.mobilemanager.service.request.TestResulOperateRequest;
import com.aspire.sqmp.mobilemanager.service.request.TestResulOperateResponse;
import com.esotericsoftware.kryonet.Connection;
import com.google.inject.Inject;

/**
 * The class <code>RequestHandle</code>
 * 
 * @author wuyanlong
 * @version 1.0
 */
public class RequestHandle implements IServerMessageHandle {

    private String TAG = "RequestHandle";
    @Inject
    private OperateResultFromPc operateResultFromPc;

    private RunLogger runLogger = RunLogger.getInstance();

    @Inject
    public RequestHandle() {

    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.sqmp.mobilemanager.service.request.IMessageHandle#handleMessage(com.aspire.sqmp.mobilemanager.service.request.AspMessage)
     */
    public boolean handleMessage(Connection connection, Object message) {
        PreferencesManager preferencesManager = PreferencesManager.getInstance();
        SharedPreferences preferences = preferencesManager.getPreferences();
        if (!(message instanceof AspRequest))
            return false;
        final AspRequest aspRequest = (AspRequest) message;
        Log.d(TAG, "Server receive request.command:" + aspRequest.getCommand() + ",syncFlag" + aspRequest.getSyncFlag());
        switch (aspRequest.getCommand()) {
        case AspCommand.S_TASK_REQ:
            if (aspRequest instanceof STaskRequest) {
                // STaskRequest request = (STaskRequest) aspRequest;
                // do something
                STaskResponse response = new STaskResponse(aspRequest.getSyncFlag());
                connection.sendTCP(response);
            }
            break;
        case AspCommand.S_CASE_REQ:
            break;
        case AspCommand.G_CASE_REQ:
            if (aspRequest instanceof GCaseRequest) {
                GCaseResponse response = new GCaseResponse(aspRequest.getSyncFlag());
                response.setMobileScriptLastUpdateTime(preferences.getString(
                        SharedPrefConstants.SCRIPT_LAST_UPDATE_TIME, null));
                connection.sendTCP(response);
            }
            break;
        case AspCommand.S_SERVICEKEY_REQ:
            break;
        case AspCommand.S_PASSWORD_REQ:
            break;
        case AspCommand.G_RESULT_REQ:
            if (aspRequest instanceof GResultRequest) {
                GResultRequest request = (GResultRequest) aspRequest;
                GResultResponse response = new GResultResponse(aspRequest.getSyncFlag());
                String path = Environment.getExternalStorageDirectory().getAbsolutePath()
                        + preferences.getString(PrefsActivity.SET_SAVE_ADDRESS, null) + NameConstants.LOCAL_ADDRESS
                        + "/" + preferences.getString(PrefsActivity.SERVICE_PATH_FOUR, null) + "/upload";
                File resultParent = new File(path);
                if (request.getRequestType() != null && request.getRequestType().equals("1")) {
                    boolean haveFile = inquireFile(resultParent);
                    response.setHaveFile(haveFile);
                } else if (request.getRequestType() != null && request.getRequestType().equals("2")) {
                    boolean deleteResult = FileUtil.deleteFile(resultParent);
                    response.setDeleteResult(deleteResult);
                }
                connection.sendTCP(response);
            }
            break;
        case AspCommand.S_SETTING_REQ:
            if (aspRequest instanceof SSettingRequest) {
                SSettingRequest request = (SSettingRequest) aspRequest;
                MobileSharedFile mobileSharedFile = request.getMobileSharedFile();
                PropertyManager propertyManager = new PropertyManager(mobileSharedFile, preferences);
                propertyManager.setPreference();
                SSettingResponse response = new SSettingResponse(aspRequest.getSyncFlag());
                connection.sendTCP(response);
            }
            break;
        case AspCommand.G_SETTING_REQ:
            if (aspRequest instanceof GSettingRequest) {
                PropertyManager propertyManager = new PropertyManager(null, preferences);
                MobileSharedFile mobileSharedFile = propertyManager.getMobileSharedFile();
                GSettingResponse response = new GSettingResponse(aspRequest.getSyncFlag());
                response.setMobileSharedFile(mobileSharedFile);
                connection.sendTCP(response);
            }
            break;
        case AspCommand.G_STORE_PATH_REQ:
            if (aspRequest instanceof GStoreInfoReqeust) {
                GStoreInfoResponse response = new GStoreInfoResponse(aspRequest.getSyncFlag());
                String sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
                response.setSdcardPath(sdcardPath);
                connection.sendTCP(response);
            }
            break;
        case AspCommand.QUERY_TEST_RESULT_REQ:
            if (aspRequest instanceof QueryTestResultRequest) {
                QueryTestResultResponse response = new QueryTestResultResponse(aspRequest.getSyncFlag());
                ResultQueryBuilder queryBuilder = ((QueryTestResultRequest) aspRequest).getResultQueryBuilder();
                List<TestResults> testResults = operateResultFromPc.getTestResults(queryBuilder);
                CPage cPage = operateResultFromPc.getcPage();
                response.setTestResultList(testResults);
                response.setPage(cPage);
                connection.sendTCP(response);
            }
            break;
        case AspCommand.TEST_RESULT_OPERATE_REQ:
            if (aspRequest instanceof TestResulOperateRequest) {
                TestResulOperateResponse response = new TestResulOperateResponse(aspRequest.getSyncFlag());
                TestResulOperateRequest request = (TestResulOperateRequest) aspRequest;
                int type = request.getType();
                List<Long> taskItemBatchIds = request.getTaskItemBatchIdlist();
                if (type == 0) {// 删除数据库中的执行结果
                    if (taskItemBatchIds != null && taskItemBatchIds.size() > 0) {
                        int deleteSize = operateResultFromPc.deleteTaskItemBatchs(taskItemBatchIds);
                        if (deleteSize == taskItemBatchIds.size()) {
                            response.setMsg("All delete successfully");
                        } else {
                            response.setResult(AspResponse.RESULT_FAIL);
                            response.setMsg("the total ids's size is " + taskItemBatchIds + ", but deleted number is "
                                    + deleteSize);
                        }
                    } else {
                        response.setResult(AspResponse.RESULT_FAIL);
                        response.setMsg("the Ids is null, or it's size is 0");
                    }
                } else if (type == 1) {// 打包
                    String zipAbsolutePath = null;
                    try {
                        zipAbsolutePath = operateResultFromPc.zipResultFile(taskItemBatchIds);
                        response.setResultAbsolutePath(zipAbsolutePath);
                    } catch (MException e) {
                        response.setResult(AspResponse.RESULT_FAIL);
                        response.setMsg(e.getMessage());
                    }
                } else if (type == 2) {// 删除已上传的zip
                    String parentPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                            + preferences.getString(PrefsActivity.SET_SAVE_ADDRESS, "/aspire/es/")
                            + NameConstants.LOCAL_ADDRESS + "/"
                            + preferences.getString(PrefsActivity.SERVICE_PATH_FOUR, "test_result") + "_tmp";
                    File parent = new File(parentPath);
                    if (parent.exists()) {
                        FileUtil.deleteFile(parent);
                    }
                } else if (type == 3) {// 回滚数据库，并删除zip
                    String parentPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                            + preferences.getString(PrefsActivity.SET_SAVE_ADDRESS, "/aspire/es/")
                            + NameConstants.LOCAL_ADDRESS + "/"
                            + preferences.getString(PrefsActivity.SERVICE_PATH_FOUR, "test_result") + "_tmp";
                    File parent = new File(parentPath);
                    if (parent.exists()) {
                        FileUtil.deleteFile(parent);
                    }
                    List<TestResults> testResults = request.getTestResults();
                    operateResultFromPc.setDBtoStart(testResults);
                }
                connection.sendTCP(response);
            }
            break;
        }
        return true;

    }

    private boolean inquireFile(File file) {
        if (file.exists()) { // 判断文件是否存在
            if (file.isFile() && file.getName().endsWith(".zip")) { // 判断是否是zip文件
                return true;
            } else if (file.isDirectory()) { // 否则如果它是一个目录
                File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
                for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
                    if (this.inquireFile(files[i]))
                        return true; // 把每个文件 用这个方法进行迭代
                }
            }
            return false;
        }
        return false;
    }
}
