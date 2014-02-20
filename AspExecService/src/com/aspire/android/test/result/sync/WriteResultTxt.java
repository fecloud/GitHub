/**

 * @(#) OutputTextUtil.java Created on 2012-5-31

 *

 * Copyright (c) 2012 Aspire. All Rights Reserved

 */
package com.aspire.android.test.result.sync;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.aspire.android.common.exception.ExceptionHandler;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.execute.NameConstants;
import com.aspire.android.test.execute.ui.PrefsActivity;
import com.aspire.android.test.script.entity.TestLog;
import com.aspire.android.test.script.service.IScriptService;
import com.aspire.android.test.task.entity.TaskItem;
import com.aspire.android.test.task.entity.TaskItemBatch;
import com.aspire.android.test.task.service.ITaskService;
import com.aspire.mgt.ats.tm.sync.util.zip.Zip;

/**
 * 
 * The class <code>OutputTextUtil</code>
 * 
 * 
 * 
 * @author gouanming
 * 
 * @version 1.0
 */
public class WriteResultTxt {
    private ITaskService taskService;
    private IScriptService mIScriptService;
    private String devType;
    private String devId;
    private String mobileNum;
    private String venderCode;
    private Context mContext;

    public WriteResultTxt(ITaskService taskService, IScriptService mIScriptService, Context mContext,
            SharedPreferences preferences) {
        this.taskService = taskService;
        this.mIScriptService = mIScriptService;
        devType = preferences.getString(PrefsActivity.TERMINAL, null);
        devId = preferences.getString(PrefsActivity.IMEI, null);
        mobileNum = preferences.getString(PrefsActivity.SET_PHONE_NUM, null);
        venderCode = preferences.getString(PrefsActivity.COMPANY, null);
        this.mContext = mContext;
    }

    /**
     * 
     * @param mFileHead
     * @param path
     * @param textName
     */
    public void writeText(FileHead mFileHead, String path, String textName) {
        BufferedWriter mBufferedWriter = null;
        StringBuffer sb = new StringBuffer();
        try {

            mBufferedWriter = new BufferedWriter(new FileWriter(new File(path + File.separator + textName)));
            sb.append(mFileHead.getVenderCode() + "\t").append(mFileHead.getDevType() + "\t")
                    .append(mFileHead.getDevId() + "\t").append(mFileHead.getIp() + "\t")
                    .append(mFileHead.getMobileNum() + "\t").append(mFileHead.getTotalNum() + "\t");
            for (Code mCode : mFileHead.getmCode()) {
                sb.append(mCode.getServiceCode() + "\t").append(mCode.getTestKeyCode() + "\t")
                        .append(mCode.getCount() + "\t");
            }
            sb.append("\r\n");

            for (TestResult mTestResult : mFileHead.getmTestResult()) {
                sb.append(mTestResult.getServiceCode() + "\t").append(mTestResult.getTestKeyCode() + "\t")
                        .append(mTestResult.getTestTaskCode() + "\t").append(mTestResult.getTestMode() + "\t")
                        .append(mTestResult.getTestBeginTime() + "\t").append(mTestResult.getTestEndTime() + "\t")
                        .append(mTestResult.getTestNum() + "\t").append(mTestResult.getTestResult() + "\t")
                        .append(mTestResult.getTestValue() + "\t").append(mTestResult.getNetworkFlag() + "\t")
                        .append(mTestResult.getAttachFileType() + "\t")
                        .append(mTestResult.getAttachFileName() + "\r\n");
            }

            mBufferedWriter.write(sb.toString());
            mBufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 
     * @param temPath
     * @param textName
     * @return
     * @throws MException
     */
    public boolean UploadResult(String temPath, String textName) throws MException {
        boolean boo = false;

        FileHead mFileHead = new FileHead();
        mFileHead.setVenderCode(venderCode);
        mFileHead.setDevType(devType);
        mFileHead.setDevId(devId);
        if (getLocalIpAddress() != null) {
            mFileHead.setIp(getLocalIpAddress());
        } else {
            mFileHead.setIp("");
        }
        mFileHead.setMobileNum(mobileNum);

        List<Code> listCode = new ArrayList<Code>();
        List<TestResult> listTestResult = new ArrayList<TestResult>();
        List<TaskItemBatch> listTaskItemBatch = new ArrayList<TaskItemBatch>();
        int numAll = 0;
        int i = 1;
        int j = 1;
        List<TaskItem> mTaskItem = taskService.getAllTaskItem();
        for (TaskItem taskItem : mTaskItem) {
            j++;
            List<TaskItemBatch> mTaskItemBatch = taskService.findListTaskItemBatch(taskItem.getId(), "0");
            int numValue = 0;
            for (TaskItemBatch taskItemBatch : mTaskItemBatch) {
                numAll++;
                numValue++;
                i++;
                TestResult mTestResult = new TestResult();

                mTestResult.setServiceCode(taskItem.getServType());
                mTestResult.setTestKeyCode(taskItem.getTaskKeyCode());
                mTestResult.setTestTaskCode(taskService.getTask(taskItemBatch.getTaskId()).getTaskCode());
                mTestResult.setTestMode(NameConstants.TEST_MODE_INPHONE);

                String testLogPath = null;
                String taskItemBatchid = mIScriptService.getTestLogID(taskItemBatch.getId());
                if (taskItemBatchid != null) {
                    TestLog mTestLog = mIScriptService.findTestLog(Long.parseLong(taskItemBatchid));
                    if (mTestLog != null) {
                        testLogPath = mTestLog.getLogPath();
                    }
                }

                mTestResult
                        .setTestBeginTime(new SimpleDateFormat("yyyyMMddHHmmss").format(taskItemBatch.getStartTime()));
                mTestResult.setTestEndTime(new SimpleDateFormat("yyyyMMddHHmmss").format(taskItemBatch.getEndTime()));

                mTestResult.setTestNum(taskItemBatch.getTimes());
                mTestResult.setTestResult(taskItemBatch.getResult());
                mTestResult.setTestValue(taskItemBatch.getTestValue());

                TelephonyManager telMgr = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
                switch (telMgr.getNetworkType()) {
                case 4:
                case 7:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case 11:
                    mTestResult.setNetworkFlag("2G");
                    break;
                case TelephonyManager.NETWORK_TYPE_UMTS:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                    mTestResult.setNetworkFlag("2G");
                    break;
                case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                    mTestResult.setNetworkFlag("0");
                    break;
                }

                if (testLogPath != null) {
                    File mFile = new File(testLogPath);
                    if (mFile.isDirectory() && mFile.list().length > 0) {
                        mTestResult.setAttachFileType("1");

                        String zipName = "log_" + j + i + ".zip";

                        File file = new File(temPath + "/FILE");
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        // 打成zip包
                        upZip(temPath + "/FILE", testLogPath, zipName);
                        mTestResult.setAttachFileName(zipName);
                    } else {
                        mTestResult.setAttachFileType("0");
                        mTestResult.setAttachFileName("");

                        File file = new File(temPath);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                    }
                } else {
                    mTestResult.setAttachFileType("0");
                    mTestResult.setAttachFileName("");

                    File file = new File(temPath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                }
                listTestResult.add(mTestResult);

                listTaskItemBatch.add(taskItemBatch);
                if (numAll >= 10)
                    break;
            }

            if (numValue > 0) {
                Code mCode = new Code();
                mCode.setServiceCode(taskItem.getServType());
                mCode.setTestKeyCode(taskItem.getTaskKeyCode());
                mCode.setCount(numValue);
                listCode.add(mCode);
            }

            if (numAll >= 10)
                break;
        }
        if (listCode.size() > 0) {
            mFileHead.setTotalNum(numAll);
            mFileHead.setmCode(listCode);
            mFileHead.setmTestResult(listTestResult);
            writeText(mFileHead, temPath, textName);
            for (TaskItemBatch taskItemBatch : listTaskItemBatch) {
                taskItemBatch.setUpstatus("1");
                taskService.updateTaskItemBatch(taskItemBatch);
            }
            boo = true;
        }
        return boo;

    }

    /**
     * @param temPath
     * @param testLogPath
     * @param zipName
     */
    private void upZip(String temPath, String testLogPath, String zipName) {
        File parent = new File(temPath);
        if (!parent.exists()) {
            parent.mkdirs();
        }
        File zipFile = new File(parent, zipName);
        Zip zip = null;
        try {
            zip = new Zip(zipFile, "UTF-8");
            zip.zipFile(testLogPath);
            zip.close();
        } catch (IOException e) {
            ExceptionHandler.handle(e, "Error while encryptTxt");
        }
    }

    /**
     * 得到ip
     * 
     * @return
     */
    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e(getClass().getSimpleName(), ex.toString());
        }
        return null;
    }

    /**
     * 
     * @param temPath
     * @param textName
     * @return
     * @throws MException
     */
    public boolean UploadResult(String temPath, String textName, Long... taskItemBatchId) throws MException {
        boolean boo = false;

        FileHead mFileHead = new FileHead();
        mFileHead.setVenderCode(venderCode);
        mFileHead.setDevType(devType);
        mFileHead.setDevId(devId);
        if (getLocalIpAddress() != null) {
            mFileHead.setIp(getLocalIpAddress());
        } else {
            mFileHead.setIp("");
        }
        mFileHead.setMobileNum(mobileNum);

        List<Code> listCode = new ArrayList<Code>();
        List<TestResult> listTestResult = new ArrayList<TestResult>();
        List<TaskItemBatch> listTaskItemBatch = new ArrayList<TaskItemBatch>();
        Map<String, Integer> map = new HashMap<String, Integer>();

        for (int i = 0; i < taskItemBatchId.length; i++) {
            TaskItemBatch taskItemBatch = taskService.findTaskItemBatch(taskItemBatchId[i]);
            if (taskItemBatch != null) {
                listTaskItemBatch.add(taskItemBatch);
                TaskItem taskItem = taskService.getTaskItem(taskItemBatch.getTaskItemId());
                String key = taskItem.getServType() + "," + taskItem.getTaskKeyCode();
                int num = (map.get(key) == null) ? 0 : map.get(key);
                if (num == 0) {
                    map.put(taskItem.getServType() + "," + taskItem.getTaskKeyCode(), 1);
                } else {
                    map.put(taskItem.getServType() + "," + taskItem.getTaskKeyCode(), num + 1);
                }

                TestResult mTestResult = new TestResult();

                mTestResult.setServiceCode(taskItem.getServType());
                mTestResult.setTestKeyCode(taskItem.getTaskKeyCode());
                mTestResult.setTestTaskCode(taskService.getTask(taskItemBatch.getTaskId()).getTaskCode());
                mTestResult.setTestMode(NameConstants.TEST_MODE_INPHONE);

                String testLogPath = null;
                String taskItemBatchid = mIScriptService.getTestLogID(taskItemBatch.getId());
                if (taskItemBatchid != null) {
                    TestLog mTestLog = mIScriptService.findTestLog(Long.parseLong(taskItemBatchid));
                    if (mTestLog != null) {
                        testLogPath = mTestLog.getLogPath();
                    }
                }

                mTestResult
                        .setTestBeginTime(new SimpleDateFormat("yyyyMMddHHmmss").format(taskItemBatch.getStartTime()));
                mTestResult.setTestEndTime(new SimpleDateFormat("yyyyMMddHHmmss").format(taskItemBatch.getEndTime()));

                mTestResult.setTestNum(taskItemBatch.getTimes());
                mTestResult.setTestResult(taskItemBatch.getResult());
                mTestResult.setTestValue(taskItemBatch.getTestValue());

                TelephonyManager telMgr = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
                switch (telMgr.getNetworkType()) {
                case 4:
                case 7:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case 11:
                    mTestResult.setNetworkFlag("2G");
                    break;
                case TelephonyManager.NETWORK_TYPE_UMTS:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                    mTestResult.setNetworkFlag("2G");
                    break;
                case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                    mTestResult.setNetworkFlag("0");
                    break;
                }
                if (testLogPath != null) {
                    File mFile = new File(testLogPath);
                    if (mFile.isDirectory() && mFile.list().length > 0) {
                        mTestResult.setAttachFileType("1");

                        String zipName = "log_" + ++i + ".zip";

                        File file = new File(temPath + "/FILE");
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        // 打成zip包
                        upZip(temPath + "/FILE", testLogPath, zipName);
                        mTestResult.setAttachFileName(zipName);
                    } else {
                        mTestResult.setAttachFileType("0");
                        mTestResult.setAttachFileName("");

                        File file = new File(temPath);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                    }
                } else {
                    mTestResult.setAttachFileType("0");
                    mTestResult.setAttachFileName("");

                    File file = new File(temPath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                }
                listTestResult.add(mTestResult);
            }
        }

        int numAll = 0;
        Set<String> key = map.keySet();
        for (Iterator<String> it = key.iterator(); it.hasNext();) {
            String s = (String) it.next();
            String[] sre = s.split(",");
            Code mCode = new Code();
            mCode.setServiceCode(sre[0]);
            mCode.setTestKeyCode(sre[1]);
            mCode.setCount(map.get(s));
            numAll += map.get(s);
            listCode.add(mCode);
        }

        if (listCode.size() > 0) {
            mFileHead.setTotalNum(numAll);
            mFileHead.setmCode(listCode);
            mFileHead.setmTestResult(listTestResult);
            writeText(mFileHead, temPath, textName);
            for (TaskItemBatch taskItemBatch : listTaskItemBatch) {
                taskItemBatch.setUpstatus("1");
                taskService.updateTaskItemBatch(taskItemBatch);
            }
            boo = true;
        }
        return boo;

    }
}
