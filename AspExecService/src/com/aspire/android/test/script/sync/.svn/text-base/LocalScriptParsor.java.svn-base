/**
 * @(#) LocalScriptParsor.java Created on 2012-10-29
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.script.sync;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.SharedPreferences;
import android.os.Environment;

import com.aspire.android.common.exception.ExceptionHandler;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.PreferencesManager;
import com.aspire.android.test.apk.entity.Case;
import com.aspire.android.test.apk.entity.Cases;
import com.aspire.android.test.apk.entity.TestKeyCodes;
import com.aspire.android.test.execute.NameConstants.SharedPrefConstants;
import com.aspire.android.test.execute.ui.PrefsActivity;
import com.aspire.android.test.log.RunLogger;
import com.aspire.android.test.script.entity.AtsScript;
import com.aspire.android.test.script.entity.CaseTestkeycode;
import com.aspire.android.test.script.entity.ScriptCase;
import com.aspire.android.test.script.service.IScriptService;
import com.aspire.android.util.FileUtil;
import com.aspire.common.util.XmlUtil;
import com.aspire.mgt.ats.tm.sync.script.entity.ScriptDetail;
import com.aspire.mgt.ats.tm.sync.script.entity.ScriptNote;
import com.aspire.mgt.ats.tm.sync.util.zip.Unzip;
import com.google.inject.Inject;

/**
 * The class <code>LocalScriptParsor</code>
 * 
 * @author likai
 * @version 1.0
 */
public class LocalScriptParsor {

    private RunLogger runLogger = RunLogger.getInstance();
    @Inject
    private IScriptService service;
    private SharedPreferences preferences;
    public HashMap<String, String> unzipFileSet = new HashMap<String, String>();

    /** 省份 */
    private String provinceCode;

    /** 解压脚本父zip后保存脚本zip的文件夹 */
    private String unZipPath;

    /** 最后一次更新脚本的时间戳 */
    private String lastUpdateTime;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    /** 解压脚本文件的目标地址 */
    private String scriptUnzipPath;

    @Inject
    public LocalScriptParsor() {

    }

    /**
     * 处理脚本提纲文件
     * 
     * @param xmlFile
     *            脚本提纲文件
     * @throws MException
     */
    public void parseScriptDetials(File xmlFile) throws MException {
        // 初始化一部分信息
        PreferencesManager manager = PreferencesManager.getInstance();
        preferences = manager.getPreferences();
        provinceCode = preferences.getString(PrefsActivity.PROVINCE, null);
        lastUpdateTime = preferences.getString(SharedPrefConstants.SCRIPT_LAST_UPDATE_TIME, null);

        if (lastUpdateTime == null) {
            lastUpdateTime = "";
        }
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(xmlFile);
        } catch (FileNotFoundException e1) {
            MException mException = ExceptionHandler.handle(e1, "xml is not found");
            if (mException != null) {
                throw mException;
            }
        }
        String zipFileName = xmlFile.getName().replace(".xml", ".zip");
        String dateForderName = dateFormat.format(new Date());
        String parentPath = xmlFile.getParent();
        File tmp = new File(parentPath, dateForderName);
        unZipPath = tmp.getAbsolutePath();
        File zipFile = new File(parentPath, zipFileName);
        if (!zipFile.exists()) {
            throw new MException("the " + zipFile.getAbsolutePath() + "is not found!");
        }
        if (!tmp.exists()) {
            tmp.mkdirs();
        }
        try {
            try {
                Unzip unzip = null;
                try {
                    unzip = new Unzip(zipFile, "UTF-8");
                    unzip.unZipByPath(tmp.getAbsolutePath());
                } finally {
                    if (unzip != null)
                        unzip.close();
                }
            } catch (IOException e1) {
                MException mException = ExceptionHandler.handle(e1, "unzip " + zipFile.getAbsolutePath() + "failed");
                if (mException != null) {
                    throw mException;
                }
            }

            ScriptNote scriptNote = (ScriptNote) XmlUtil.deSerialize(ScriptNote.class, inputStream);
            if (scriptNote != null && scriptNote.getScriptDetails() != null
                    && scriptNote.getScriptDetails().getScriptDetail() != null
                    && scriptNote.getScriptDetails().getScriptDetail().size() > 0) {
                List<ScriptDetail> detailList = mergeScriptByTarget(provinceCode, scriptNote.getScriptDetails()
                        .getScriptDetail());
                sortByUpdate(detailList);
                for (int i = 0; i < detailList.size(); i++) {
                    parse(detailList.get(i));
                }
            } else {
                runLogger.error(getClass(), "the file not contains useful scriptNote");
            }

        } finally {
            FileUtil.deleteFile(tmp);
        }
    }

    /**
     * 挑出适用与该省的脚本
     * 
     * @param targetId
     *            省编码
     * @param all
     *            全量的
     * @return
     */
    private List<ScriptDetail> mergeScriptByTarget(String targetId, List<ScriptDetail> all) {
        Map<ScriptDetailMapKey, ScriptDetail> map = new HashMap<ScriptDetailMapKey, ScriptDetail>();
        for (ScriptDetail it : all) {
            String[] targetIds = it.getTargetId().split(",");
            if (compareTarget(targetId, targetIds) || "common".equals(it.getTargetId())) {
                ScriptDetailMapKey key = new ScriptDetailMapKey(it.getServiceCode(), it.getTestKeyCode());
                ScriptDetail tmp = map.get(key);
                if (tmp != null) {
                    int compare = tmp.getUploaddate().compareTo(it.getUploaddate());
                    if (compare < 0) {
                        map.put(key, it);
                    } else if (compare == 0 && targetId.equals(it.getTargetId())) {
                        map.put(key, it);
                    }
                } else {
                    map.put(key, it);
                }
            }
        }
        List<ScriptDetail> details = new ArrayList<ScriptDetail>();
        if (map.size() > 0) {
            details.addAll(map.values());
        }
        return details;
    }

    /**
     * 比较是否包含该省份的脚本信息
     * 
     * @param targetId
     * @param targetIds
     * @return
     */
    private boolean compareTarget(String targetId, String[] targetIds) {
        for (int i = 0; i < targetIds.length; i++) {
            if (targetId.equals(targetIds[i]))
                return true;
        }
        return false;
    }

    /**
     * 按时间排序
     * 
     * @param scriptDetails
     */
    private void sortByUpdate(List<ScriptDetail> scriptDetails) {
        int h = 1;
        ScriptDetail temp;
        int inner, outer;
        // 希尔排序
        while (h <= scriptDetails.size() / 3)
            h = h * 3 + 1;
        while (h > 0) {
            for (outer = h; outer < scriptDetails.size(); outer++) {
                temp = scriptDetails.get(outer);
                inner = outer;
                while (inner > h - 1
                        && scriptDetails.get(inner - h).getUploaddate().compareTo(temp.getUploaddate()) > 0) {
                    scriptDetails.set(inner, scriptDetails.get(inner - h));
                    inner -= h;
                }
                scriptDetails.set(inner, temp);
            }
            h = (h - 1) / 3;
        }
    }

    class ScriptDetailMapKey {
        String serviceCode;

        String testKeyCode;

        /**
         * Constructor
         * 
         * @param serviceCode
         * @param testKeyCode
         */
        public ScriptDetailMapKey(String serviceCode, String testKeyCode) {
            super();
            this.serviceCode = serviceCode;
            this.testKeyCode = testKeyCode;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getOuterType().hashCode();
            result = prime * result + ((serviceCode == null) ? 0 : serviceCode.hashCode());
            result = prime * result + ((testKeyCode == null) ? 0 : testKeyCode.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            ScriptDetailMapKey other = (ScriptDetailMapKey) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (serviceCode == null) {
                if (other.serviceCode != null)
                    return false;
            } else if (!serviceCode.equals(other.serviceCode))
                return false;
            if (testKeyCode == null) {
                if (other.testKeyCode != null)
                    return false;
            } else if (!testKeyCode.equals(other.testKeyCode))
                return false;
            return true;
        }

        private LocalScriptParsor getOuterType() {
            return LocalScriptParsor.this;
        }
    }

    /**
     * 解压单个脚本
     * 
     * @param scriptDetail
     * @return
     * @throws MException
     */
    public void parse(ScriptDetail scriptDetail) throws MException {
        AtsScript atsScript = service.findAtsScript(scriptDetail.getServiceCode(), scriptDetail.getTestKeyCode());
        if (atsScript == null || scriptDetail.getUploaddate().compareTo(atsScript.getLastUpdateDate()) > 0) {
            File scriptZip = new File(unZipPath, scriptDetail.getScriptName());
            if (!scriptZip.exists()) {
                throw new MException("the script file is not found, it's path is " + scriptZip.getAbsolutePath());
            }
            if (!unzipFileSet.containsKey(scriptZip.getAbsolutePath())) {
                scriptUnzipPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                        + preferences.getString(PrefsActivity.SET_SAVE_ADDRESS, null)
                        + preferences.getString(PrefsActivity.SERVICE_PATH_THREE, null) + "/unzip/"
                        + dateFormat.format(new Date());
                try {
                    File tmp = new File(scriptUnzipPath);
                    if (!tmp.exists()) {
                        tmp.mkdirs();
                    }
                    Unzip unzip = null;
                    try {
                        unzip = new Unzip(scriptZip, "UTF-8");
                        unzip.unZipByPath(scriptUnzipPath);
                    } finally {
                        if (unzip != null)
                            unzip.close();
                    }
                } catch (IOException e1) {
                    MException mException = ExceptionHandler.handle(e1, "unzip " + scriptZip.getAbsolutePath()
                            + "failed");
                    if (mException != null) {
                        throw mException;
                    }
                }
            } else {
                scriptUnzipPath = unzipFileSet.get(scriptZip.getAbsolutePath());
            }

            if (atsScript != null) {
                atsScript.setScriptName(scriptDetail.getScriptName());
                atsScript.setScriptVersion(scriptDetail.getScriptVersion());
                atsScript.setSavePath(scriptUnzipPath);
                atsScript.setLastUpdateDate(scriptDetail.getUploaddate());
                atsScript.setServType(scriptDetail.getServiceCode());
                atsScript.setTaskKeyCode(scriptDetail.getTestKeyCode());
                atsScript.setUpDate(new Date());
                service.upDateAtsScript(atsScript);
            } else {

                AtsScript mAtsScript = new AtsScript();
                mAtsScript.setScriptName(scriptDetail.getScriptName());
                mAtsScript.setScriptVersion(scriptDetail.getScriptVersion());
                mAtsScript.setSavePath(scriptUnzipPath);
                mAtsScript.setLastUpdateDate(scriptDetail.getUploaddate());
                mAtsScript.setServType(scriptDetail.getServiceCode());
                mAtsScript.setTaskKeyCode(scriptDetail.getTestKeyCode());
                mAtsScript.setUpDate(new Date());
                service.addAtsScript(mAtsScript);
            }
            // 将解压后的文件路存到数据库中
            File file = new File(scriptUnzipPath);
            File[] files = file.listFiles();
            String atsPath = null;
            String apkPath = null;
            if (files.length > 0) {
                for (int i = 0; i < files.length; i++) {
                    if (!files[i].getAbsolutePath().endsWith(".apk") && !files[i].getAbsolutePath().endsWith(".xml")) {
                        atsPath = files[i].getAbsolutePath();
                    }
                }
                for (int i = 0; i < files.length; i++) {
                    if (files[i].getAbsolutePath().endsWith(".apk")) {
                        apkPath = files[i].getAbsolutePath();
                    }
                }

                getXml(files, atsPath, apkPath);
            }
            unzipFileSet.put(scriptZip.getAbsolutePath(), scriptUnzipPath);
        }
    }

    /**
     * @param scriptID
     * @param files
     * @param atsPath
     * @param apkPath
     */
    private void getXml(File[] files, String atsPath, String apkPath) throws MException {
        for (int i = 0; i < files.length; i++) {
            if (files[i].getAbsolutePath().endsWith(".xml")) {
                // cases.xml
                File xmlFile = new File(files[i].getAbsolutePath());
                InputStream fileInput = null;
                try {
                    fileInput = new FileInputStream(xmlFile);
                } catch (FileNotFoundException e) {
                    MException mException = ExceptionHandler.handle(e, xmlFile.getName() + "is not found");
                    if (mException != null) {
                        throw mException;
                    }
                }

                Cases mCases = null;
                try {
                    mCases = (Cases) XmlUtil.deSerialize(Cases.class, fileInput);
                } catch (Exception e2) {
                    MException mException = ExceptionHandler.handle(e2,
                            "parse cases xml failed, errmessage is " + e2.getMessage());
                    if (mException != null) {
                        throw mException;
                    }
                }

                List<Case> listCase = mCases.getListCase();
                for (Case case1 : listCase) {
                    ScriptCase mScriptCase = null;
                    List<TestKeyCodes> testkeycodes = case1.getTestkeycodes();
                    mScriptCase = service.findScriptCase(case1.getName());
                    if (mScriptCase != null) {
                        mScriptCase.setPackageName(mCases.getPackageName());
                        mScriptCase.setAtsPath(atsPath);
                        mScriptCase.setApkPath(apkPath);
                        mScriptCase.setUpDate(new Date());
                        service.updataScriptCase(mScriptCase);
                    } else {
                        mScriptCase = new ScriptCase();
                        mScriptCase.setPackageName(mCases.getPackageName());
                        mScriptCase.setAtsPath(atsPath);
                        mScriptCase.setCasePath(case1.getName());
                        mScriptCase.setApkPath(apkPath);
                        mScriptCase.setUpDate(new Date());
                        mScriptCase = service.addScriptCase(mScriptCase);
                    }

                    getCaseTestkey(mScriptCase, testkeycodes);
                }
            }
        }
    }

    /**
     * @param mScriptCase
     * @param testkeycodes
     */
    private void getCaseTestkey(ScriptCase mScriptCase, List<TestKeyCodes> testkeycodes) throws MException {
        if (mScriptCase.getId() != null) {
            for (TestKeyCodes testkeycode : testkeycodes) {
                List<CaseTestkeycode> lsitCaseTestkeycode = null;
                lsitCaseTestkeycode = service.getCaseTestkeycode(testkeycode.getTestServType(),
                        testkeycode.getTestkeycode());
                if (lsitCaseTestkeycode == null || lsitCaseTestkeycode.size() == 0) {
                    CaseTestkeycode mCaseTestkeycode = new CaseTestkeycode();
                    mCaseTestkeycode.setCaseId(mScriptCase.getId());
                    mCaseTestkeycode.setTestServType(testkeycode.getTestServType());
                    mCaseTestkeycode.setTestkeycode(testkeycode.getTestkeycode());
                    service.addCaseTestkeycode(mCaseTestkeycode);
                } else if (lsitCaseTestkeycode.size() == 1) {
                    for (CaseTestkeycode caseTestkeycode : lsitCaseTestkeycode) {
                        caseTestkeycode.setCaseId(mScriptCase.getId());
                        service.updataCaseTestkeycode(caseTestkeycode);
                    }
                }
            }
        }
    }

}
