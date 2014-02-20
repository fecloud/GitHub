package com.aspire.android.test.script.sync;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;

import com.aspire.android.common.exception.ExceptionHandler;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.PreferencesManager;
import com.aspire.android.test.apk.entity.Case;
import com.aspire.android.test.apk.entity.Cases;
import com.aspire.android.test.apk.entity.TestKeyCodes;
import com.aspire.android.test.execute.NameConstants;
import com.aspire.android.test.execute.ui.PrefsActivity;
import com.aspire.android.test.log.RunLogger;
import com.aspire.android.test.script.entity.AtsScript;
import com.aspire.android.test.script.entity.CaseTestkeycode;
import com.aspire.android.test.script.entity.ScriptCase;
import com.aspire.android.test.script.service.IScriptService;
import com.aspire.common.util.XmlUtil;
import com.aspire.mgt.ats.tm.sync.script.ScriptFileDownloadHandler;
import com.aspire.mgt.ats.tm.sync.script.entity.ScriptDetail;
import com.aspire.mgt.ats.tm.sync.util.zip.Unzip;

public class ESScriptFileDownloadHandler extends ScriptFileDownloadHandler {

    private RunLogger runLogger = RunLogger.getInstance();

    private final String TAG = ESScriptFileDownloadHandler.class.getSimpleName();

    public HashMap<String, String> unzipFileSet = new HashMap<String, String>();

    private IScriptService service;

    private SharedPreferences preferences;
    private PreferencesManager preferencesManager = PreferencesManager.getInstance();

    public void setService(IScriptService service) {
        this.service = service;
    }

    private String ftpLocalPath;

    public void setFtpLocalPath(String ftpLocalPath) {
        this.ftpLocalPath = ftpLocalPath;
    }

    public ESScriptFileDownloadHandler() {
        super();
        preferences = preferencesManager.getPreferences();
    }

    @Override
    public void handle(InputStream is) {
        super.handle(is);
        // 将脚本zip扔到备份目录下
        if (scriptZipfile.exists()) {
            String filePath = ftpLocalPath + NameConstants.BACKUP_ADDRESS + "/" + NameConstants.SCRIPT_FILE_PATH + "/"
                    + provinceCode;
            File parent = new File(filePath);
            if (!parent.exists())
                parent.mkdirs();
            File scriptZipFileDest = new File(parent, scriptDetail.getScriptName());
            if (scriptZipFileDest.exists())
                scriptZipFileDest.delete();
            scriptZipfile.renameTo(scriptZipFileDest);
            if (scriptZipfile.exists())
                runLogger.error(ESScriptFileDownloadHandler.class,
                        "Failed while backup scriptZipFile " + scriptZipfile.getName());
        }
    }

    @Override
    public void OnDownloaded(ScriptDetail scriptDetail, SimpleDateFormat sdf, File f) throws ParseException {
        // 做时间的判断
        // String date = (new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date());
        AtsScript myAtsScript = null;
        try {
            myAtsScript = service.findAtsScript(scriptDetail.getServiceCode(), scriptDetail.getTestKeyCode());
        } catch (com.aspire.android.common.exception.MException e1) {
            runLogger.error(
                    ESScriptFileDownloadHandler.class,
                    "Failed while search script in databases by serviceCode and testKeyCode, errmessage is"
                            + e1.getMessage());
        }
        try {
            if (myAtsScript != null) {
                runLogger.debug(ESScriptFileDownloadHandler.class, "parse scriptZipFile start and the servicecode : "
                        + scriptDetail.getServiceCode() + "the testkeycode : " + scriptDetail.getTestKeyCode()
                        + " is exists , file is " + f.getAbsolutePath());
                execute(myAtsScript, scriptDetail, f);
                runLogger.debug(ESScriptFileDownloadHandler.class, "parse scriptZipFile finish and the servicecode : "
                        + scriptDetail.getServiceCode() + "the testkeycode : " + scriptDetail.getTestKeyCode()
                        + " is exists, file is " + f.getAbsolutePath());
            } else {
                runLogger.debug(ESScriptFileDownloadHandler.class,
                        "parse scriptZipFile start, file is " + f.getAbsolutePath());
                execute(null, scriptDetail, f);
                runLogger.debug(ESScriptFileDownloadHandler.class,
                        "parse scriptZipFile finish, file is " + f.getAbsolutePath());
            }
        } catch (MException e) {
            runLogger.error(ESScriptFileDownloadHandler.class,
                    "parse scriptZipFile failed, and errMessage is " + e.getMessage());
        }

    }

    /**
     * @param scriptDetail
     * @param f
     */
    private void execute(AtsScript atsScript, ScriptDetail scriptDetail, File f) throws MException {
        String ftpLocalPath = Environment.getExternalStorageDirectory().getAbsolutePath() + preferences.getString(PrefsActivity.SET_SAVE_ADDRESS, null);
        String ftpath = preferences.getString(PrefsActivity.SERVICE_PATH_THREE, null);
        String pathName = null;
        if (!unzipFileSet.containsKey(f.getAbsolutePath())) {
            pathName = ftpLocalPath + ftpath + File.separator + "unzip" + File.separator
                    + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            unZipScript(f, pathName);
        } else {
            pathName = unzipFileSet.get(f.getAbsolutePath());
        }

        if (atsScript != null) {
            atsScript.setScriptName(scriptDetail.getScriptName());
            atsScript.setScriptVersion(scriptDetail.getScriptVersion());
            atsScript.setSavePath(pathName);
            atsScript.setLastUpdateDate(scriptDetail.getUploaddate());
            atsScript.setServType(scriptDetail.getServiceCode());
            atsScript.setTaskKeyCode(scriptDetail.getTestKeyCode());
            atsScript.setUpDate(new Date());
            service.upDateAtsScript(atsScript);
        } else {

            AtsScript mAtsScript = new AtsScript();
            mAtsScript.setScriptName(scriptDetail.getScriptName());
            mAtsScript.setScriptVersion(scriptDetail.getScriptVersion());
            mAtsScript.setSavePath(pathName);
            mAtsScript.setLastUpdateDate(scriptDetail.getUploaddate());
            mAtsScript.setServType(scriptDetail.getServiceCode());
            mAtsScript.setTaskKeyCode(scriptDetail.getTestKeyCode());
            mAtsScript.setUpDate(new Date());
            service.addAtsScript(mAtsScript);
        }

        // 将解压后的文件路存到数据库中
        File file = new File(pathName);
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
        unzipFileSet.put(f.getAbsolutePath(), pathName);
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
                    mCases = (Cases)XmlUtil.deSerialize(Cases.class, fileInput);
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
                Log.d(TAG, "getTestServType:" + testkeycode.getTestServType());
                Log.d(TAG, "getTestkeycode:" + testkeycode.getTestkeycode());
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

    private void unZipScript(File f, String path) throws MException {
        runLogger.debug(getClass(), "unZip file " + f.getName() + " to " + path + " start");
        File parent = new File(path);
        if (!parent.exists()) {
            parent.mkdirs();
        }
        try {
            Unzip unzip = new Unzip(f, "UTF-8");
            try {
                unzip.unZipByPath(path);
            } finally {
                unzip.close();
            }
        } catch (IOException e) {
            runLogger.debug(getClass(), "unZip file " + f.getName() + " to " + path + " finish");
            MException mException = ExceptionHandler.handle(e, "Error fail to unzip the script");
            if (mException != null) {
                throw mException;
            }
        }
        runLogger.debug(getClass(), "unZip file " + f.getName() + " to " + path + " finish");
    }

}
