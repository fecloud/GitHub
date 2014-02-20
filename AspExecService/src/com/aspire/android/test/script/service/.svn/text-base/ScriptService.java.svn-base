/**
 * @(#) ScriptService.java Created on 2012-6-14
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.script.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.util.Log;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.execute.CommandConstants;
import com.aspire.android.test.execute.ContentValues;
import com.aspire.android.test.script.dao.ICaseTestkeycodeDao;
import com.aspire.android.test.script.dao.IScriptCaseDao;
import com.aspire.android.test.script.dao.IScriptDao;
import com.aspire.android.test.script.dao.ITestLogDao;
import com.aspire.android.test.script.dao.ITestLogOnTaskItemDao;
import com.aspire.android.test.script.entity.AtsScript;
import com.aspire.android.test.script.entity.CaseScript;
import com.aspire.android.test.script.entity.CaseTestkeycode;
import com.aspire.android.test.script.entity.LogValues;
import com.aspire.android.test.script.entity.ScriptCase;
import com.aspire.android.test.script.entity.TestLog;
import com.aspire.android.test.script.entity.TestLogOnTaskItem;
import com.aspire.android.test.servicekey.dao.IServiceIndexRelationDao;
import com.aspire.android.test.servicekey.entity.AtsServiceIndexRelation;
import com.aspire.android.test.task.entity.Task;
import com.aspire.android.util.ApkCaseUtil;
import com.aspire.android.util.LogUtil;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * The class <code>ScriptService</code>
 * 
 * @author gouanming
 * @version 1.0
 */
@Singleton
public class ScriptService implements IScriptService {
    @Inject
    public IScriptDao mIScriptDao;
    @Inject
    public IScriptCaseDao mIScriptCaseDao;
    @Inject
    public ITestLogDao mITestLogDao;
    @Inject
    public ITestLogOnTaskItemDao mITestLogOnTaskItemDao;
    @Inject
    public ICaseTestkeycodeDao mICaseTestkeycodeDao;
    @Inject
    public IServiceIndexRelationDao mIServiceIndexRelationDao;
    public Context mContext;
    
    @Inject
    public ScriptService(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.script.service.IScriptService#addAtsScript(com.aspire.android.test.script.entity.AtsScript)
     */
    public AtsScript addAtsScript(AtsScript mAtsScript) throws MException {
        return mIScriptDao.insert(mAtsScript);
    }
    /**
     * 
     * (non-Javadoc)
     * @see com.aspire.android.test.script.service.IScriptService#upDateAtsScript(com.aspire.android.test.script.entity.AtsScript)
     */
    public AtsScript upDateAtsScript(AtsScript mAtsScript) throws MException{
        return mIScriptDao.update(mAtsScript);
    }

    /**
     * (non-Javadoc)
     * 
     * @throws MException
     * @see com.aspire.android.test.script.service.IScriptService#findMAXScriptID()
     */
    public Long findMAXScriptID() throws MException {
        // TODO Auto-generated method stub
        return mIScriptDao.findMAXScriptID();
    }

    public AtsScript findAtsScript(String serviceCode, String testKeyCode) throws MException {
        return mIScriptDao.findAtsScript(serviceCode, testKeyCode);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.script.service.IScriptService#addScriptCase(com.aspire.android.test.script.entity.ScriptCase)
     */
    public ScriptCase addScriptCase(ScriptCase mScriptCase) throws MException {
        return mIScriptCaseDao.insert(mScriptCase);
    }

    public ScriptCase findScriptCase(String casePath) throws MException {
        return mIScriptCaseDao.findScriptCase(casePath);
    }

    public ScriptCase updataScriptCase(ScriptCase mScriptCase) throws MException {
        return mIScriptCaseDao.update(mScriptCase);
    }

    public List<ScriptCase> findScriptCases() throws MException {
        return mIScriptCaseDao.findAll();
    }

    public List<CaseTestkeycode> getCaseTestkeycode(String testServType, String testkeycode) throws MException {
        return mICaseTestkeycodeDao.getCaseTestkeycode(testServType, testkeycode);
    }

    public CaseTestkeycode addCaseTestkeycode(CaseTestkeycode mCaseTestkeycode) throws MException {
        return mICaseTestkeycodeDao.insert(mCaseTestkeycode);
    }

    public CaseTestkeycode updataCaseTestkeycode(CaseTestkeycode mCaseTestkeycode) throws MException {
        return mICaseTestkeycodeDao.update(mCaseTestkeycode);
    }

    public List<CaseTestkeycode> findScriptServiceIndex(Long caseId) throws MException {
        return mICaseTestkeycodeDao.findScriptServiceIndex(caseId);
    }


    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.script.service.IScriptService#executeIScript(java.lang.String, java.lang.String)
     */
    public LogValues executeIScript(String testKeyCode, String serviceCode, String logPath,  Task task)
            throws MException {

        LogValues mLogValues = new LogValues();
        ContentValues backValues = null;
        List<CaseTestkeycode> lsitCaseTestkeycode = null;
        try {
            lsitCaseTestkeycode = getCaseTestkeycode(serviceCode, testKeyCode);
        } catch (MException e) {
            e.printStackTrace();
        }
        if (lsitCaseTestkeycode.size() == 1) {
            for (CaseTestkeycode caseTestkeycode : lsitCaseTestkeycode) {
                Date startTime = new Date();
                ScriptCase mScriptCase = mIScriptCaseDao.findById(caseTestkeycode.getCaseId());
                
                
                // 卸载APK
                try {
                    ApkCaseUtil.getInstance(mContext).uninstall(mScriptCase.getPackageName());
                } catch (com.aspire.android.test.exception.MException e) {
                    Log.e(getClass().getSimpleName(), "Error  APK uninstall fail");
                }

                // 安装APK
                boolean bool = false;
                try {
                    bool = ApkCaseUtil.getInstance(mContext).install(mScriptCase.getApkPath());
                } catch (com.aspire.android.test.exception.MException e) {
                    Log.e(getClass().getSimpleName(), "Error  APK install fail");
                    return null;

                }
                if (bool) {
                    String logPaths = logPath + File.separator + mScriptCase.getCasePath();
                    File file = new File(logPaths);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    // 調運行接口
                    
                    List<CaseTestkeycode> listCaseTestkeycode  =findScriptServiceIndex(mScriptCase.getId()) ;
                    String case_index="";
                    for (int i = 0; i < listCaseTestkeycode.size(); i++) {
                        CaseTestkeycode mCaseTestkeycode =listCaseTestkeycode.get(i);
                        if(i < listCaseTestkeycode.size()-1){
                            case_index +=mCaseTestkeycode.getTestkeycode()+",";
                        }else{
                            case_index +=mCaseTestkeycode.getTestkeycode();
                        }
                    }
                    
                    
                    ContentValues params = new ContentValues();
                    params.put(CommandConstants.KEY_CASE_INDEX, case_index);
                    params.put(CommandConstants.KEY_CASE_CLASSNAME, mScriptCase.getCasePath());
                    params.put(CommandConstants.KEY_CASE_LOGLOCATION, logPaths);
                    params.put(CommandConstants.KEY_CASE_ATTACHMENT_LOCTION, mScriptCase.getAtsPath());
                    params.put(CommandConstants.KEY_CASE_CERTIFICATE_LOCATION, logPaths);
                    params.put(CommandConstants.KEY_CASE_TCPDUM_LOCATION, logPaths);
                    params.put(CommandConstants.KEY_CASE_GRABTCP, task.getNetCapture().trim());
                    params.put(CommandConstants.KEY_TEMP_LOCATION, logPaths);
                    params.put(CommandConstants.KEY_CASE_LOG_LEVEL, LogUtil.parseStrToLogLevel(task.getLogLevel().trim()));
                    try {
                        backValues = ApkCaseUtil.getInstance(mContext).runCase(params);
                        mLogValues.setContentValues(backValues);
                    } catch (com.aspire.android.test.exception.MException e) {
                        Log.e(getClass().getSimpleName(), "Error  ApkCaseUtil.getInstance(mContext).runCase(params)");
                        return null;
                    }

                    TestLog mTestLog = new TestLog();
                    mTestLog.setStartTime(startTime);
                    mTestLog.setEndTime(new Date());
                    mTestLog.setLogPath(logPath);
                    mTestLog.setCaseId(mScriptCase.getId());
                    TestLog testLog = mITestLogDao.insert(mTestLog);
                    mLogValues.setLogId(testLog.getId());
                }

                // 卸载APK
                try {
                    ApkCaseUtil.getInstance(mContext).uninstall(mScriptCase.getPackageName());
                } catch (com.aspire.android.test.exception.MException e) {
                    Log.e(getClass().getSimpleName(), "Error  APK uninstall fail");
                }
            }

            return mLogValues;
        }

        return null;
    }

    /**
     * 
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.script.service.IScriptService#addTestLogOnTaskItem(long, long)
     */
    public TestLogOnTaskItem addTestLogOnTaskItem(long taskItemBatchID, long logId) throws MException {
        TestLogOnTaskItem mTestLogOnTaskItem = new TestLogOnTaskItem();
        mTestLogOnTaskItem.setItemBatchId(taskItemBatchID);
        mTestLogOnTaskItem.setTestLogId(logId);

        return addTestLogOnTaskItem(mTestLogOnTaskItem);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.script.service.IScriptService#findMAXLogID()
     */
    public String findMAXLogID() throws MException {
        // TODO Auto-generated method stub
        return mITestLogDao.findTestLogMAXid();
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.script.service.IScriptService#addTestLogOnTaskItem(com.aspire.android.test.script.entity.TestLogOnTaskItem)
     */
    public TestLogOnTaskItem addTestLogOnTaskItem(TestLogOnTaskItem mTestLogOnTaskItem) throws MException {
        return mITestLogOnTaskItemDao.insert(mTestLogOnTaskItem);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.script.service.IScriptService#findScriptid(java.lang.Long)
     */
    public Long findScriptid(Long taskItemBatchId) throws MException {
        // TODO Auto-generated method stub
        return mITestLogDao.findScriptid(taskItemBatchId);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.script.service.IScriptService#getTestLogID(java.lang.Long)
     */
    public String getTestLogID(Long itemBatchId) throws MException {
        // TODO Auto-generated method stub
        return mITestLogOnTaskItemDao.getTestLogID(itemBatchId);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.script.service.IScriptService#findAtsScript(com.aspire.android.test.script.entity.AtsScript)
     */
    public AtsScript findAtsScript(long id) throws MException {
        // TODO Auto-generated method stub
        return mIScriptDao.findById(id);
    }

    /**
     * 
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.script.service.IScriptService#findListAtsScript(java.lang.String, java.lang.String)
     */
    public List<AtsScript> findListAtsScript(String scriptName, String taskKeyName) throws MException {
        List<AtsScript> listScript = new ArrayList<AtsScript>();
        List<AtsServiceIndexRelation> mAtsServiceIndexRelation = mIServiceIndexRelationDao
                .findAtsServiceIndexRelation(taskKeyName);
        Log.d(getClass().getSimpleName(), "mAtsServiceIndexRelation.size():" + mAtsServiceIndexRelation.size());
        if (mAtsServiceIndexRelation.size() > 0) {
            for (AtsServiceIndexRelation atsServiceIndexRelation : mAtsServiceIndexRelation) {

                Log.d(getClass().getSimpleName(), "mAtsServiceIndexRelation:" + atsServiceIndexRelation.getTestKeyName());

                List<AtsScript> list = mIScriptDao.findListAtsScript(scriptName,
                        atsServiceIndexRelation.getTestKeyCode());
                for (AtsScript atsScript : list) {
                    listScript.add(atsScript);
                }
            }
        } else {
            List<AtsScript> list = mIScriptDao.findListAtsScript(scriptName);
            for (AtsScript atsScript : list) {
                listScript.add(atsScript);
            }
        }

        if (listScript.size() > 0) {
            return listScript;
        }
        return null;
    }

    public List<AtsScript> findListAtsScript() throws MException {
        return mIScriptDao.findListAtsScript();
    }

    public TestLog findTestLog(long id) throws MException {
        return mITestLogDao.findById(id);
    }

    /**
     * 
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.script.service.IScriptService#findAllCaseScript()
     */
    public List<CaseScript> findAllCaseScript() throws MException {
        List<CaseScript> lsitCaseScript = new ArrayList<CaseScript>();
        List<ScriptCase> listScriptCase = findScriptCases();
        for (ScriptCase scriptCase : listScriptCase) {

            CaseScript mCaseScript = new CaseScript();
            mCaseScript.setCaseId(scriptCase.getId());
            mCaseScript.setCaseName(scriptCase.getCasePath());

            List<CaseTestkeycode> lsitCaseTestkeycode = findScriptServiceIndex(scriptCase.getId());
            for (CaseTestkeycode caseTestkeycode : lsitCaseTestkeycode) {
                mCaseScript.setTestKeyCode(caseTestkeycode.getTestkeycode());
                mCaseScript.setServiceCode(caseTestkeycode.getTestServType());
            }
            lsitCaseScript.add(mCaseScript);
        }
        return lsitCaseScript;

    }
}
