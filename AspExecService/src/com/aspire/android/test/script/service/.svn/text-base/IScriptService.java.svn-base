/**
 * @(#) IScriptService.java Created on 2012-6-14
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.script.service;

import java.util.List;

import com.aspire.android.common.exception.MException;
import com.aspire.android.test.script.entity.AtsScript;
import com.aspire.android.test.script.entity.CaseScript;
import com.aspire.android.test.script.entity.CaseTestkeycode;
import com.aspire.android.test.script.entity.LogValues;
import com.aspire.android.test.script.entity.ScriptCase;
import com.aspire.android.test.script.entity.TestLog;
import com.aspire.android.test.script.entity.TestLogOnTaskItem;
import com.aspire.android.test.task.entity.Task;

/**
 * The class <code>IScriptService</code>
 * 
 * @author gouanming
 * @version 1.0
 */
public interface IScriptService {

    /**
     * 添加AtsScript
     * @param mAtsScript
     * @return
     * @throws MException
     */
    public AtsScript addAtsScript(AtsScript mAtsScript) throws MException;
    
    /**
     * 更新AtsScript
     * @param mAtsScript
     * @return
     * @throws MException
     */
    public AtsScript upDateAtsScript(AtsScript mAtsScript) throws MException;

    /**
     * 根据ID查找AtsScript
     * @param id
     * @return
     * @throws MException
     */
    public AtsScript findAtsScript(long id) throws MException;

    /**
     * 根据serviceCode,testKeyCode查找AtsScript
     * @param serviceCode 业务类型
     * @param testKeyCode 指标编码
     * @return
     * @throws MException
     */
    public AtsScript findAtsScript(String serviceCode, String testKeyCode) throws MException;

    /**
     * 根据scriptName,taskKeyName查找AtsScript
     * @param scriptName 脚本名称
     * @param taskKeyName 指标名称
     * @return
     * @throws MException
     */
    public List<AtsScript> findListAtsScript(String scriptName, String taskKeyName) throws MException;

    /**
     * 查找有的AtsScript
     * @return
     * @throws MException
     */
    public List<AtsScript> findListAtsScript() throws MException;
   
    /**
     * 得到AtsScript 最大id号
     * @return
     * @throws MException
     */
    public Long findMAXScriptID() throws MException;

    /**
     * add ScriptCase
     * @param mScriptCase
     * @return
     * @throws MException
     */
    public ScriptCase addScriptCase(ScriptCase mScriptCase) throws MException;

    /**
     * 根据casePaths 查找 ScriptCase
     * @param casePath
     * @return
     * @throws MException
     */
    public ScriptCase findScriptCase(String casePath) throws MException;

    /**
     * 更新ScriptCase
     * @param mScriptCase
     * @return
     * @throws MException
     */
    public ScriptCase updataScriptCase(ScriptCase mScriptCase) throws MException;

    /**
     * 得到所有的ScriptCase
     * @return
     * @throws MException
     */
    public List<ScriptCase> findScriptCases() throws MException;

    /**
     * 
     * 根据serviceCode,testKeyCode查找CaseTestkeycode
     * @param serviceCode 业务类型
     * @param testKeyCode 指标编码
     * @return
     * @throws MException
     */
    public List<CaseTestkeycode> getCaseTestkeycode(String testServType, String testkeycode) throws MException;

    /**
     * add CaseTestkeycode
     * @param mCaseTestkeycode
     * @return
     * @throws MException
     */
    public CaseTestkeycode addCaseTestkeycode(CaseTestkeycode mCaseTestkeycode) throws MException;

    /**
     * 更新 CaseTestkeycode
     * @param mCaseTestkeycode
     * @return
     * @throws MException
     */
    public CaseTestkeycode updataCaseTestkeycode(CaseTestkeycode mCaseTestkeycode) throws MException;

    /**
     *  根据 caseId查找 CaseTestkeycode
     * @param caseId
     * @return
     * @throws MException
     */
    public List<CaseTestkeycode> findScriptServiceIndex(Long caseId) throws MException;


    /**
     * 执行 案例
     * @param serviceCode 业务类型
     * @param testKeyCode 指标编码
     * @param logPath log路径
     * @param task
     * @return
     * @throws MException
     */
    public LogValues executeIScript(String testKeyCode, String serviceCode, String logPath,Task task)
            throws MException;

    /**
     * add TestLogOnTaskItem
     * @param taskItemBatchID
     * @param logId
     * @return
     * @throws MException
     */
    public TestLogOnTaskItem addTestLogOnTaskItem(long taskItemBatchID, long logId) throws MException;

    /**
     * 得到TestLog的最大id号
     * @return
     * @throws MException
     */
    public String findMAXLogID() throws MException;

    /**
     * add TestLogOnTaskItem
     * @param mTestLogOnTaskItem
     * @return
     * @throws MException
     */
    public TestLogOnTaskItem addTestLogOnTaskItem(TestLogOnTaskItem mTestLogOnTaskItem) throws MException;

    /**
     * 根据 taskItemBatchId查找 scriptid
     * @param taskItemBatchId
     * @return
     * @throws MException
     */
    public Long findScriptid(Long taskItemBatchId) throws MException;

    /**
     * 根据 itemBatchId查找 TestLogID
     * @param itemBatchId
     * @return
     * @throws MException
     */
    public String getTestLogID(Long itemBatchId) throws MException;

    /**
     * 根据 id查找 TestLog
     * @param id
     * @return
     * @throws MException
     */
    public TestLog findTestLog(long id) throws MException;

    /**
     * 得到所以的CaseScript
     */
    public List<CaseScript> findAllCaseScript() throws MException;
}
