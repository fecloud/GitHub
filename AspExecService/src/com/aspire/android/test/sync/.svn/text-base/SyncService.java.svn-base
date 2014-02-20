/**
 * @(#) SyncServiceImpl.java Created on 2012-5-16
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.sync;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;

import com.aspire.android.common.exception.ExceptionHandler;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.PreferencesManager;
import com.aspire.android.test.device.sync.DeviceRegisterHandler;
import com.aspire.android.test.device.sync.DeviceStatusHandler;
import com.aspire.android.test.device.sync.DeviceSync;
import com.aspire.android.test.execute.NameConstants;
import com.aspire.android.test.execute.NameConstants.SharedPrefConstants;
import com.aspire.android.test.execute.ui.PrefsActivity;
import com.aspire.android.test.log.RunLogger;
import com.aspire.android.test.password.UpdateResultPassword;
import com.aspire.android.test.result.sync.ResultResponseHandler;
import com.aspire.android.test.result.sync.WriteResultTxt;
import com.aspire.android.test.script.service.IScriptService;
import com.aspire.android.test.script.sync.ESScriptRequest;
import com.aspire.android.test.servicekey.service.IServiceKeyService;
import com.aspire.android.test.servicekey.sync.ServiceKeyHandler;
import com.aspire.android.test.sync.pub.LocalFtpClient;
import com.aspire.android.test.sync.pub.LocalHttpClient;
import com.aspire.android.test.sync.pub.RemoteFtpClient;
import com.aspire.android.test.sync.pub.RemoteHttpClient;
import com.aspire.android.test.task.entity.Task;
import com.aspire.android.test.task.entity.Upload;
import com.aspire.android.test.task.service.ITaskService;
import com.aspire.android.test.task.sync.TaskRequest;
import com.aspire.android.util.DesUtil;
import com.aspire.android.util.FileUtil;
import com.aspire.common.util.MobileManagerConstants;
import com.aspire.mgt.ats.tm.sync.FileResponseProcessor;
import com.aspire.mgt.ats.tm.sync.IFTPClient;
import com.aspire.mgt.ats.tm.sync.IHTTPClient;
import com.aspire.mgt.ats.tm.sync.XmlResponseProcessor;
import com.aspire.mgt.ats.tm.sync.device.entity.DeviceInfo;
import com.aspire.mgt.ats.tm.sync.device.entity.DeviceStatus;
import com.aspire.mgt.ats.tm.sync.util.ftp.ClientConfig;
import com.aspire.mgt.ats.tm.sync.util.zip.Zip;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * 
 * The class <code>SyncServiceImpl</code>
 * 
 * @author gouanming
 * @version 1.0
 */
@Singleton
public class SyncService implements ISyncService {

    private RunLogger runLogger = RunLogger.getInstance();
    /**
     * 与任务相关操作
     */
    @Inject
    private ITaskService taskService;
    /**
     * 与脚本相关操作
     */
    @Inject
    private IScriptService scriptService;
    /**
     * 与业务指标相关操作
     */
    @Inject
    private IServiceKeyService servieKeyService;

    private Context mContext;
    /**
     * 存储配置信息
     */
    private SharedPreferences preferences;
    private PreferencesManager preferencesManager = PreferencesManager.getInstance();
    /**
     * 发起设备注册和状态更新请求
     */
    private DeviceSync deviceSync;
    /**
     * 本地文件的主目录
     */
    private String ftpLocalPath;
    /**
     * 厂商编码
     */
    private String companyCode;
    /**
     * 省份编码
     */
    private String provinceCode;
    /**
     * 城市编码
     */
    private String cityCode;
    /**
     * 设备类型
     */
    private String devType;
    /**
     * 手机机器码
     */
    private String imei;
    /**
     * 任务列表
     */
    private List<Task> taskList;
    /**
     * 保存sharedpreference的editor
     */
    private Editor editor;
    /**
     * 获取本地文件的ftpclient
     */
    private LocalFtpClient localFtpClient = null;
    /**
     * 获取远程服务器上文件的ftpclient
     */
    private RemoteFtpClient remoteFtpClient = null;
    /**
     * 配置远程ftp服务器的
     */
    private ClientConfig config = null;
    /**
     * 连接http的client
     */
    private IHTTPClient httpClient = null;

    private boolean dataGetSwitch;

    /**
     * Constructor
     * 
     * @param taskService
     * @param scriptService
     * @param servieKeyService
     * @param mContext
     */
    @Inject
    public SyncService(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 初始化需要的配置信息 {@inheritDoc}
     * 
     * @see com.aspire.android.test.sync.ISyncService#initialSync()
     */
    public void initialSync() throws MException {
        preferences = preferencesManager.getPreferences();
        editor = preferences.edit();
        deviceSync = new DeviceSync();
        ftpLocalPath = Environment.getExternalStorageDirectory().getAbsolutePath() + preferences.getString(PrefsActivity.SET_SAVE_ADDRESS, null);
        runLogger.debug(getClass(), "ftpLocalPath = " + ftpLocalPath);
        companyCode = preferences.getString(PrefsActivity.COMPANY, null);
        provinceCode = preferences.getString(PrefsActivity.PROVINCE, null);
        cityCode = preferences.getString(PrefsActivity.CITY, null);
        devType = preferences.getString(PrefsActivity.TERMINAL, null);
        imei = preferences.getString(PrefsActivity.IMEI, null);
        dataGetSwitch = preferences.getBoolean(PrefsActivity.DATA_GET_SWITCH, true);
    }

    /**
     * 下载案例脚本 (non-Javadoc)
     * 
     * @see com.aspire.android.test.sync.ISyncService#downloadTestCase()
     */
    public void downloadTestCase() throws MException {
        boolean manUpdateScript = preferences.getBoolean(SharedPrefConstants.MAN_UPDATE_SCRIPT, false);
        // 手动获取
        if (manUpdateScript) {
            runLogger.debug(getClass(), "手动下载脚本");
            doDownloadTestCase();
            saveTime(SharedPrefConstants.SCRIPT_LAST_INQUIRE_TIME);
        } else {
            // 定时获取
            long lastDownloadTime = preferences.getLong(SharedPrefConstants.SCRIPT_LAST_INQUIRE_TIME, 0);
            if (lastDownloadTime == 0) {
                doDownloadTestCase();
                saveTime(SharedPrefConstants.SCRIPT_LAST_INQUIRE_TIME);
            } else {
                long now = (new Date()).getTime();
                String ivString = preferences.getString(PrefsActivity.SCRIPT_DOWNLOAD_TIME, "0");
                if (ivString == null || ivString.equals("")) {
                    ivString = "0";
                }
                long iv = Long.valueOf(ivString);
                if (iv == -1) {
                    runLogger.debug(getClass(), "the download script's interval is -1,so it will not to update script");
                    return;
                }
                if ((now - lastDownloadTime) > (iv * 1000)) {
                    doDownloadTestCase();
                    saveTime(SharedPrefConstants.SCRIPT_LAST_INQUIRE_TIME);
                } else {
                    runLogger.debug(getClass(),
                            "The interval of download script is not come, so it needn't to update script, the real interval is " + (now - lastDownloadTime)/1000);
                }
            }
        }
        if (manUpdateScript)
            setManStatus(SharedPrefConstants.MAN_UPDATE_SCRIPT, false);
    }

    private void doDownloadTestCase() throws MException {
        runLogger.debug(getClass(), "query and download script start");
        String url = null;
        String ftpPath = null;
        String separator = File.separator;
        ESScriptRequest scriptRequest = new ESScriptRequest(scriptService);
        if (dataGetSwitch) {
            url = preferences.getString(PrefsActivity.SCRIPT_DOWNLOAD_URL, null);
            String lastUpdate = preferences.getString(SharedPrefConstants.SCRIPT_LAST_UPDATE_TIME, null);

            ftpPath = preferences.getString(PrefsActivity.SERVICE_PATH_THREE, null);
            String ftpIp = preferences.getString(PrefsActivity.SERVICE_IP_THREE, null);
            String ftpPortString = preferences.getString(PrefsActivity.SERVICE_PORT_THREE, null);
            int ftpPort = 21;
            if(ftpPortString != null && !ftpPortString.trim().equals("")){
                ftpPort = Integer.parseInt(ftpPortString);
            }
            String ftpUserName = preferences.getString(PrefsActivity.SERVICE_USERNAME_THREE, null);
            String ftpPwd = preferences.getString(PrefsActivity.SERVICE_PASSWORD_THREE, null);
            String dataTimeOut = preferences.getString(PrefsActivity.FTP_DATA_TIMEOUT, "0");
            int timeOut = 0;
            if(dataTimeOut == null || dataTimeOut.trim().equals("")){
                dataTimeOut = "0";
            }
            //单位分钟
            timeOut = Integer.parseInt(dataTimeOut) * 1000 * 60;
            try {
                httpClient = new RemoteHttpClient();
                config = new ClientConfig(ftpIp, ftpPort, ftpUserName, ftpPwd);
                config.setDataTimeOut(timeOut);
                remoteFtpClient = new RemoteFtpClient(config);
                scriptRequest.request(NameConstants.TEST_MODE_INPHONE, companyCode, provinceCode, url, devType,
                        lastUpdate, remoteFtpClient, httpClient, imei, ftpLocalPath, ftpPath, separator);

            } catch (Exception e) {
                MException mexception = ExceptionHandler.handle(e, "Error while doDownloadTestCase");
                if (mexception != null) {
                    throw mexception;
                }
            } finally {
                if (remoteFtpClient != null) {
                    remoteFtpClient.close();
                }
            }
        } else {
            String scriptQueryPath = NameConstants.LOCAL_ADDRESS + File.separator
                    + preferences.getString(PrefsActivity.SERVICE_PATH_THREE, null) + File.separator
                    + NameConstants.LOCAL_SCRIPT_QUERY_NAME;
            ftpPath = NameConstants.SCRIPT_FILE_PATH;
            if (scriptQueryPath != null && ftpPath != null) {
                url = ftpLocalPath + scriptQueryPath;
                httpClient = new LocalHttpClient();
                localFtpClient = new LocalFtpClient(ftpLocalPath + NameConstants.LOCAL_ADDRESS);
                scriptRequest.request(NameConstants.TEST_MODE_INPHONE, companyCode, provinceCode, url, devType, null,
                        localFtpClient, httpClient, imei, ftpLocalPath, ftpPath, separator);
            }
        }
        runLogger.debug(getClass(), "query and download script finish");
    }

    /**
     * 下载业务指标 (non-Javadoc)
     * 
     * @see com.aspire.android.test.sync.ISyncService#downloadServiceKeys()
     */
    public void downloadServiceKeys() throws MException {
        // 定时获取
        long lastDownloadTime = preferences.getLong(SharedPrefConstants.SCRIPT_LAST_INQUIRE_TIME, 0);
        if (lastDownloadTime == 0) {
            doDownloadServiceKeys();
        } else {
            long now = (new Date()).getTime();
            String ivString = preferences.getString(PrefsActivity.SCRIPT_DOWNLOAD_TIME, "0");
            if (ivString == null || ivString.equals("")) {
                ivString = "0";
            }
            long iv = Long.valueOf(ivString);
            if (iv == -1) {
                runLogger.debug(getClass(),
                        "the download servicekeys's interval is -1,so it will not to update servicekeys");
                return;
            }
            if (iv != 0 && (now - lastDownloadTime) > (iv * 1000)) {
                doDownloadServiceKeys();
            } else {
                runLogger.debug(getClass(),
                        "The interval of download servicekeys is not come, so it needn't to update ServiceKeys, the real interval is " + (now - lastDownloadTime)/1000);
            }
        }

    }

    private void doDownloadServiceKeys() throws MException {
        runLogger.debug(getClass(), "download servicekeys start");
        String ftpPath = null;
        String lastFileName = preferences.getString(SharedPrefConstants.SERVICE_KEY_LAST_NAME, null);
        ftpPath = preferences.getString(PrefsActivity.SERVICE_PATH_TWO, null);
        String ftpIp = preferences.getString(PrefsActivity.SERVICE_IP_TWO, null);
        String ftpPortString = preferences.getString(PrefsActivity.SERVICE_PORT_TWO, null);
        int ftpPort = 21;
        if(ftpPortString != null && !ftpPortString.trim().equals("")){
            ftpPort = Integer.parseInt(ftpPortString);
        }
        String ftpUserName = preferences.getString(PrefsActivity.SERVICE_USERNAME_TWO, null);
        String ftpPwd = preferences.getString(PrefsActivity.SERVICE_PASSWORD_TWO, null);
        String dataTimeOut = preferences.getString(PrefsActivity.FTP_DATA_TIMEOUT, "0");
        int timeOut = 0;
        if(dataTimeOut == null || dataTimeOut.trim().equals("")){
            dataTimeOut = "0";
        }
        //单位分钟
        timeOut = Integer.parseInt(dataTimeOut) * 1000 * 60;
        try {
            config = new ClientConfig(ftpIp, ftpPort, ftpUserName, ftpPwd);
            config.setDataTimeOut(timeOut);
            remoteFtpClient = new RemoteFtpClient(config);
            lastFileName = remoteFtpClient.download(ftpLocalPath, ftpPath, lastFileName, new XmlResponseProcessor(
                    new ServiceKeyHandler(servieKeyService)));

        } catch (Exception e) {
            MException mexception = ExceptionHandler.handle(e, "Error while doDownloadServiceKeys");
            if (mexception != null) {
                throw mexception;
            }
        } finally {
            if (remoteFtpClient != null) {
                remoteFtpClient.close();
            }
        }
        if (lastFileName != null && !lastFileName.equals(""))
            editor.putString(SharedPrefConstants.SERVICE_KEY_LAST_NAME, lastFileName);
        runLogger.debug(getClass(), "download servicekeys finish");
    }

    /**
     * 判断是否需要更新任务
     * 
     * @see com.aspire.android.test.sync.ISyncService#downloadTask()
     */
    public void downloadTask() throws MException {
        boolean manUpdateTask = preferences.getBoolean(SharedPrefConstants.MAN_UPDATE_TASK, false);
        // 手动获取
        if (manUpdateTask) {
            runLogger.debug(getClass(), "手动下载任务");
            doDownloadTask();
            saveTime(SharedPrefConstants.TASK_LAST_INQUIRE_TIME);
        } else {
            // 定时获取
            long lastDownloadTime = preferences.getLong(SharedPrefConstants.TASK_LAST_INQUIRE_TIME, 0);

            if (lastDownloadTime == 0) {
                doDownloadTask();
                saveTime(SharedPrefConstants.TASK_LAST_INQUIRE_TIME);
            } else {
                long now = (new Date()).getTime();
                String ivString = preferences.getString(PrefsActivity.TASK_DOWNLOAD_TIME, "0");
                if(ivString.equals("")){
                    ivString = "0";
                }
                long iv = Long.parseLong(ivString);
                if (iv == -1) {
                    runLogger.debug(getClass(), "the download task's interval is -1,so it will not to update task");
                    return;
                }
                if ((now - lastDownloadTime) > (iv * 1000)) {
                    doDownloadTask();
                    saveTime(SharedPrefConstants.TASK_LAST_INQUIRE_TIME);
                } else {
                    runLogger.debug(getClass(),
                            "The interval of download task is not come so it needn't to download task, the real interval is " + (now - lastDownloadTime)/1000);
                }
            }
        }

        if (manUpdateTask)
            setManStatus(SharedPrefConstants.MAN_UPDATE_TASK, false);

    }

    /**
     * 更新任务
     */
    private void doDownloadTask() throws MException {
        runLogger.debug(getClass(), "download task start ");
        String url = null;
        String testTaskCode = null;
        if (dataGetSwitch) {
            url = preferences.getString(PrefsActivity.TASK_DOWNLOAD_URL, null);
            taskList = taskService.getTaskForStatus(1);

            if (taskList != null && taskList.size() != 0) {
                testTaskCode = taskList.get(0).getTaskCode();
                for (int i = 1; i < taskList.size(); i++) {
                    testTaskCode = testTaskCode + "," + taskList.get(i).getTaskCode();
                }
            }
            httpClient = new RemoteHttpClient();
        } else {
            httpClient = new LocalHttpClient();
            url = ftpLocalPath + NameConstants.LOCAL_ADDRESS + File.separator + MobileManagerConstants.TASK_FILE_PATH
                    + File.separator + NameConstants.LOCAL_TASK_QUERY_NAME;
        }
        TaskRequest taskRequest = new TaskRequest(taskService);
        if (url == null) {
            runLogger.error(getClass(), "doDownloadTask url is null");
            throw new MException("doDownloadTask url is null.");
        }
        taskRequest.request(ftpLocalPath, url, httpClient, NameConstants.TEST_MODE_INPHONE, companyCode, provinceCode,
                devType, testTaskCode, imei);
        runLogger.debug(getClass(), "download task finish");
    }

    /**
     * 更新密钥
     * 
     * @see com.aspire.android.test.sync.ISyncService#downloadCertificate()
     */
    public void updatePassword() throws MException {
        UpdateResultPassword password = new UpdateResultPassword();
        password.execute();
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.sync.ISyncService#uploadResultResponser()
     */
    public void uploadResultResponser() throws MException {
        // 定时获取
        long lastDownloadTime = preferences.getLong(SharedPrefConstants.LAST_DOWNLOAD_RESP_TIME, 0);

        if (lastDownloadTime == 0) {
            doDownloadResultRespFile();
            saveTime(SharedPrefConstants.LAST_DOWNLOAD_RESP_TIME);
        } else {
            long now = (new Date()).getTime();
            long iv = Long.valueOf(preferences.getString(PrefsActivity.TEST_RESULT_RESP_INTERVAL, null));
            if (iv == -1) {
                runLogger
                        .debug(getClass(), "the download respFile interval is -1,so it will not to download resp file");
                return;
            }
            if (iv != 0 && (now - lastDownloadTime) > (iv * 1000)) {
                doDownloadResultRespFile();
                saveTime(SharedPrefConstants.LAST_DOWNLOAD_RESP_TIME);
            } else {
                runLogger
                        .debug(getClass(),
                                "The interval of download result resp file is not come so it needn't to download ResultRespFile, the real interval is " + (now - lastDownloadTime)/1000);
            }
        }
    }

    private void doDownloadResultRespFile() throws MException {
        runLogger.debug(getClass(), "download result response file start");
        String ftpPath_pre = preferences.getString(PrefsActivity.SERVICE_PATH_FOUR, null);
        String ftpIp = preferences.getString(PrefsActivity.SERVICE_IP_FOUR, null);
        String ftpPortString = preferences.getString(PrefsActivity.SERVICE_PORT_FOUR, null);
        int ftpPort = 21;
        if(ftpPortString != null && !ftpPortString.trim().equals("")){
            ftpPort = Integer.parseInt(ftpPortString);
        }
        String ftpUserName = preferences.getString(PrefsActivity.SERVICE_USERNAME_FOUR, null);
        String ftpPwd = preferences.getString(PrefsActivity.SERVICE_PASSWORD_FOUR, null);
        String dataTimeOut = preferences.getString(PrefsActivity.FTP_DATA_TIMEOUT, "0");
        int timeOut = 0;
        if(dataTimeOut == null || dataTimeOut.trim().equals("")){
            dataTimeOut = "0";
        }
        //单位分钟
        timeOut = Integer.parseInt(dataTimeOut) * 1000 * 60;
        ResultResponseHandler responseHandler = null;
        FileResponseProcessor processor = null;
        List<Upload> uploads = taskService.listUploads("1", "");
        try {
            config = new ClientConfig(ftpIp, ftpPort, ftpUserName, ftpPwd);
            config.setDataTimeOut(timeOut);
            remoteFtpClient = new RemoteFtpClient(config);
            for (Upload upload : uploads) {
                String ftpPath = ftpPath_pre + "/resp" + File.separator + upload.getUploadTime();
                String fileName = "E_" + upload.getResultFileName();
                File parent = new File(ftpLocalPath, NameConstants.BACKUP_ADDRESS + "/"
                        + NameConstants.TEST_RESULT_FILE_PATH + "/resp/");
                if (!parent.exists()) {
                    parent.mkdirs();
                }
                File responLocalFile = new File(parent, fileName);
                responseHandler = new ResultResponseHandler(taskService);
                responseHandler.setResponseFile(responLocalFile);
                responseHandler.setUpload(upload);
                processor = new FileResponseProcessor(responseHandler);
                remoteFtpClient.downloadFile(ftpPath, fileName, IFTPClient.FILE_TYPE.BINARY_FILE_TYPE, processor);
            }
        } catch (Exception e) {

            MException mexception = ExceptionHandler.handle(e, "Error while upload testresultresponse");
            if (mexception != null) {
                throw mexception;
            }
        } finally {
            if (remoteFtpClient != null) {
                remoteFtpClient.close();
            }
        }
        runLogger.debug(getClass(), "download result response file finish");
    }

    /**
     * 判断上传方式
     * 
     * @see com.aspire.android.test.sync.ISyncService#uploadResult()
     */
    public void uploadResult() throws MException {
        String upload_type = preferences.getString(PrefsActivity.UPLOADS_TYPE, null);
        boolean manUploadResult = preferences.getBoolean(SharedPrefConstants.MAN_UPLOAD_RESULT, false);
        if (manUploadResult) {// 手动上传
            runLogger.debug(getClass(), "手动上传拨测结果");
            doUploadResult();
            saveTime(SharedPrefConstants.LAST_UPLOAD_RESULT_TIME);
        } else if ("1".equals(upload_type)) {// 立即上传
            doUploadResult();
            saveTime(SharedPrefConstants.LAST_UPLOAD_RESULT_TIME);
        } else if ("2".equals(upload_type)) {// 定时上传
            long lastUploadResultTime = preferences.getLong(SharedPrefConstants.LAST_UPLOAD_RESULT_TIME, 0);

            String ivString = preferences.getString(PrefsActivity.TEST_RESULT_UPLOAD_INTERVAL, "0");
            if (ivString == null || ivString.equals("")) {
                ivString = "0";
            }
            long iv = Long.valueOf(ivString);
            if (iv == -1) {
                runLogger.debug(getClass(), "the upload result's interval is -1, so it will not to upload result file");
                return;
            }
            long now = new Date().getTime();
            if (now - lastUploadResultTime > iv * 1000) {
                doUploadResult();
                saveTime(SharedPrefConstants.LAST_UPLOAD_RESULT_TIME);
            } else {
                runLogger
                        .debug(getClass(), "The interval of upload result is not come, so it needn't to upload result, the real interval is " + (now - lastUploadResultTime)/1000);
            }
        } else if("3".equals(upload_type)){
            runLogger
            .debug(getClass(), "the upload type is man uploading");
        }
        if (manUploadResult)
            setManStatus(SharedPrefConstants.MAN_UPLOAD_RESULT, false);
    }

    /**
     * 上传测试结果
     * 
     * @throws MException
     */
    private void doUploadResult() throws MException {
        runLogger.debug(getClass(), "upload result file start");
        String password = preferences.getString(SharedPrefConstants.RESULT_PWD_CURRENT, null);
        String pwdUpdateTime = preferences.getString(SharedPrefConstants.UPDATE_RESULT_PWD_TIME, null);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        String nowDate = dateFormat.format(new Date()) + "01";
        if (password == null || password.equals("")) {
            runLogger.error(getClass(), "The result password is null, so can't to encrypt txt and upload result");
        } else if (password != null && !password.equals("") && nowDate.equals(pwdUpdateTime)) {
            runLogger.error(getClass(),
                    "The result password is not the password of this month, so can't to encrypt txt and upload result");
        } else {
            String ftpPath = preferences.getString(PrefsActivity.SERVICE_PATH_FOUR, null);
            String ftpIp = preferences.getString(PrefsActivity.SERVICE_IP_FOUR, null);
            String ftpPortString = preferences.getString(PrefsActivity.SERVICE_PORT_FOUR, null);
            int ftpPort = 21;
            if(ftpPortString != null && !ftpPortString.trim().equals("")){
                ftpPort = Integer.parseInt(ftpPortString);
            }
            String ftpUserName = preferences.getString(PrefsActivity.SERVICE_USERNAME_FOUR, null);
            String ftpPwd = preferences.getString(PrefsActivity.SERVICE_PASSWORD_FOUR, null);
            String dataTimeOut = preferences.getString(PrefsActivity.FTP_DATA_TIMEOUT, "0");
            int timeOut = 0;
            if(dataTimeOut == null || dataTimeOut.trim().equals("")){
                dataTimeOut = "0";
            }
            //单位分钟
            timeOut = Integer.parseInt(dataTimeOut) * 1000 * 60;
            // 打包拨测结果
            String upload_type = preferences.getString(PrefsActivity.UPLOADS_TYPE, null);
            if ("1".equals(upload_type)) {// 立即上传
                if (taskService.judgeUploadResult()) {
                    zipResultFile(ftpPath);
                }
            } else {
                zipResultFile(ftpPath);
            }
            dateFormat = new SimpleDateFormat("yyyyMMdd");
            String today = dateFormat.format(new Date());
            ftpPath = ftpPath + File.separator + "upload" + File.separator + today;
            try {
                config = new ClientConfig(ftpIp, ftpPort, ftpUserName, ftpPwd);
                config.setDataTimeOut(timeOut);
                remoteFtpClient = new RemoteFtpClient(config);
                localFtpClient = new LocalFtpClient(ftpLocalPath + NameConstants.LOCAL_ADDRESS);
                List<Upload> lsitUpload = taskService.listUploads("0");

                for (Upload upload : lsitUpload) {
                    File upFile = new File(upload.getUploadPath());
                    FileInputStream input = new FileInputStream(upFile);
                    String ftpName = null;
                    if (dataGetSwitch) {
                        runLogger
                                .debug(getClass(), "upload result file to ftp server, and file is " + upFile.getName());
                        ftpName = remoteFtpClient.uploadFile(ftpPath, upFile.getName(),
                                RemoteFtpClient.FILE_TYPE.BINARY_FILE_TYPE, input);
                    } else {
                        runLogger.debug(getClass(), "upload result file to sdcard, and file is " + upFile.getName());
                        ftpName = localFtpClient.uploadFile(ftpPath, upFile.getName(), input);
                    }
                    input.close();
                    input = null;
                    if (ftpName != null) {
                        runLogger.debug(getClass(), "upload result file the back name is " + ftpName);
                        upload.setUploadTime(today);
                        upload.setResultFileName(ftpName);
                        upload.setResponseFile("");
                        upload.setUpstatus("1");
                        taskService.updateUpload(upload);
                        String backPath = ftpLocalPath + NameConstants.BACKUP_ADDRESS + "/"
                                + NameConstants.TEST_RESULT_FILE_PATH + "/upload/";
                        File backFile = new File(backPath, ftpName);
                        if (!backFile.getParentFile().exists())
                            backFile.getParentFile().mkdirs();
                        if (backFile.exists())
                            backFile.delete();
                        upFile.renameTo(backFile);
                        if (upFile.exists())
                            runLogger.debug(getClass(), "failed while backup result file " + upFile.getName());
                    } else {
                        runLogger.error(getClass(),
                                "the ftp result filename is empty, while upload result zipfile and zipfile's name is "
                                        + upFile.getName());
                    }
                }
            } catch (Exception e) {
                MException mexception = ExceptionHandler.handle(e, "Error while uploadresult");
                if (mexception != null) {
                    throw mexception;
                }
            } finally {
                if (remoteFtpClient != null) {
                    remoteFtpClient.close();
                }
            }
        }
        runLogger.debug(getClass(), "upload result file finish");
    }

    /**
     * 将测试结果打包
     * 
     * @param ftpPath
     *            测试结果所在的路径
     * @throws MException
     */
    private void zipResultFile(String ftpPath) throws MException {
        SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String todayString = simpleDataFormat.format(new Date());

        // 上testresult中txt的名字
        String txtName = devType + "_" + imei + "_" + NameConstants.TEST_MODE_INPHONE + "_" + provinceCode + "_"
                + cityCode + "_" + todayString + "_001.txt";
        // 上传的result的zip的名字
        String zipName = "RESULT_" + devType + "_" + imei + "_" + NameConstants.TEST_MODE_INPHONE + "_" + provinceCode
                + "_" + cityCode + "_" + todayString + ".zip";

        // 需要上传文件在本地文件系统中的主目录
        String parentPath = ftpLocalPath + ftpPath + File.separator + "upload";
        // 需要上传的已打包zip文件
        String zipPath = parentPath + File.separator + "zip";
        // 需要上传的未打包的文件
        String resultPath = parentPath + File.separator + todayString;
        WriteResultTxt mWriteResultTxt = new WriteResultTxt(taskService, scriptService, mContext, preferences);
        boolean writeTxtFinish = false;
        File txtFile = new File(resultPath, txtName);
        try {
            writeTxtFinish = mWriteResultTxt.UploadResult(txtFile.getParent(), txtFile.getName());
        } catch (MException e) {
            MException mexception = ExceptionHandler.handle(e, "Error while write result txt");
            if (mexception != null) {
                throw mexception;
            }
        }
        if (writeTxtFinish) {
            encryptTxt(txtFile);

            File parent = new File(zipPath);
            if (!parent.exists()) {
                parent.mkdirs();
            }
            File zipFile = new File(parent, zipName);
            Zip zip = null;
            try {
                zip = new Zip(zipFile, "UTF-8");
                // 将制定文件夹下的文件打包
                zip.zipFile(resultPath);
                zip.close();
                Upload mUpload = new Upload();
                mUpload.setName(zipName);
                mUpload.setStatus(0);
                mUpload.setUpDate(new Date());
                mUpload.setUpstatus("0");
                mUpload.setUploadPath(zipFile.getAbsolutePath());
                // 将需要上传的已打包的zip文件入库，并将状态设置成未上传
                taskService.addUpload(mUpload);
                FileUtil.deleteFile(txtFile.getParentFile());
            } catch (IOException e) {
                MException mException = ExceptionHandler.handle(e, "Error while zipResultFile");
                if (mException != null)
                    throw mException;
            }
        }
    }

    /**
     * 对文件进行加密
     * 
     * @param f
     *            需要加密的文件
     * @throws MException
     */
    private void encryptTxt(File file) throws MException {
        String pwd = preferences.getString(SharedPrefConstants.RESULT_PWD_CURRENT, null);
        if (pwd == null || pwd.equals("")) {
            return;
        }
        byte[] password = pwd.getBytes();
        byte[] encryptByte = null;

        try {
            encryptByte = DesUtil.encrypt(getFileByte(file), password);
            FileOutputStream out = new FileOutputStream(file);
            out.write(encryptByte);
            out.close();

        } catch (Exception e) {
            MException mexception = ExceptionHandler.handle(e, "Error while encryptTxt");
            if (mexception != null) {
                throw mexception;
            }
        }

    }

    /**
     * 获取文件的byte数组
     * 
     * @param file
     *            源文件
     * @return byte[] 文件的byte数组
     * @throws MException
     */
    private byte[] getFileByte(File file) throws MException {
        byte[] bf = null;
        try {
            FileInputStream input = new FileInputStream(file);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] b = new byte[128];
            try {
                int len = 0;
                while (-1 != (len = input.read(b))) {
                    output.write(b, 0, len);
                }
                bf = output.toByteArray();
                return bf;
            } finally {
                input.close();
                output.flush();
                output.close();
            }
        } catch (IOException e) {
            MException mexception = ExceptionHandler.handle(e, "Error while encryptTxt");
            if (mexception != null) {
                throw mexception;
            }
        }
        return null;
    }

    /**
     * 设备注册模块
     */
    public void deviceRegister() throws MException {
        runLogger.debug(getClass(), "register device start");
        try {
            String msin = preferences.getString(PrefsActivity.SET_PHONE_NUM, null);
            String devName = preferences.getString(PrefsActivity.DEVIC_NAME, null);

            List<DeviceInfo> devInfoList = new ArrayList<DeviceInfo>();
            DeviceInfo deviceInfo = new DeviceInfo();
            deviceInfo.setCityCode(cityCode);
            deviceInfo.setCompanyCode(companyCode);
            deviceInfo.setModel(devType);
            deviceInfo.setImei(imei);
            deviceInfo.setMsin(msin);
            deviceInfo.setName(devName);
            deviceInfo.setProvCode(provinceCode);
            deviceInfo.setStatus("00");
            deviceInfo.setTestMode(NameConstants.TEST_MODE_INPHONE);
            deviceInfo.setSwVersion(getVersionName());
            devInfoList.add(deviceInfo);
            DeviceRegisterHandler deviceInfoHandler = new DeviceRegisterHandler();
            deviceSync.registerDeviceInfo(devInfoList, deviceInfoHandler);
        } catch (Exception e) {
            MException mexception = ExceptionHandler.handle(e, "Error while getVersionName");
            if (mexception != null) {
                throw mexception;
            }
        } finally {
            runLogger.debug(getClass(), "register device finish");
        }
    }

    /**
     * 获取该包的版本号
     * 
     * @return 版本号
     * @throws Exception
     */
    private String getVersionName() throws Exception {
        // 获取packagemanager的实例
        PackageManager packageManager = mContext.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(mContext.getPackageName(), 0);
        String version = packInfo.versionName;
        return version;
    }

    /**
     * 设备状态变更模块
     */
    public void deviceStatusUpdate(String status) throws MException {
        long lastDeviceStatusTime = preferences.getLong(SharedPrefConstants.LAST_DEVICE_STATUS_TIME, 0);
        long nowTime = new Date().getTime();
        String ivString = preferences.getString(PrefsActivity.DEVICE_TIME_INTERVAL, "0");
        if (ivString == null || ivString.equals("")) {
            ivString = "0";
        }
        long iv = Long.parseLong(ivString);
        if (iv == -1) {
            runLogger.debug(getClass(), "the deivce status's interval is -1, so need not to update status ");
            return;
        }
        if ((nowTime - lastDeviceStatusTime) > iv * 1000) {
            runLogger.debug(getClass(), "update device status start, and status is " + status);
            List<DeviceStatus> devStatusList = new ArrayList<DeviceStatus>();
            DeviceStatus deviceStatus = new DeviceStatus();
            deviceStatus.setImei(imei);
            deviceStatus.setStatus(status);
            devStatusList.add(deviceStatus);
            DeviceStatusHandler deviceStatusHandler = new DeviceStatusHandler();
            deviceSync.updateDeviceStatus(devStatusList, deviceStatusHandler);
            runLogger.debug(getClass(), "update device status finish");
        } else {
            runLogger.debug(getClass(),
                    "the interval of update device status is not come, so need not to update status ");
        }
    }

    /**
     * 保存这次查询脚本更新的时间，因为有时间间隔
     * 
     * @param key
     */
    private void saveTime(String key) {
        long value = (new Date()).getTime();
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * 保存手动设置的状态
     * 
     * @param key
     *            表示要设置那种手动模式的状态
     * @param value
     *            状态值
     */
    public void setManStatus(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 清理从后端下载下来的数据文件 (non-Javadoc)
     * 
     * @see com.aspire.android.test.sync.ISyncService#deleteSyncFile()
     */
    public void deleteSyncFile() throws MException {
        final String parentPath = ftpLocalPath + NameConstants.BACKUP_ADDRESS + "/";
        final String taskParentPath = parentPath + NameConstants.TASK_FILE_PATH;
        final String scriptParentPath = parentPath + NameConstants.SCRIPT_FILE_PATH;
        final String serviceKeyParentPath = parentPath + NameConstants.SERVICE_KEY_FILE_PATH;
        final String resultUploadParentPath = parentPath + NameConstants.TEST_RESULT_FILE_PATH + "/upload";
        final String resultRespParentPath = parentPath + NameConstants.TEST_RESULT_FILE_PATH + "/resp";
        File taskParent = new File(taskParentPath);
        File scriptParent = new File(scriptParentPath);
        File serviceKeyParent = new File(serviceKeyParentPath);
        File resultUploadParent = new File(resultUploadParentPath);
        File resultRespParent = new File(resultRespParentPath);
        // weekIv一周；monthIv一月
        long weekIv = 86400 * 7;
        long monthIv = 86400 * 30;
        long iv = 0;
        // 清理任务xml，任务文件保存7天
        File[] taskFiles = taskParent.listFiles();
        if (taskFiles != null && taskFiles.length > 0) {
            for (File taskFile : taskFiles) {
                iv = (new Date().getTime() - taskFile.lastModified()) / 1000;
                if (iv > weekIv) {
                    runLogger.debug(getClass(), "clean task file : " + taskFile.getAbsolutePath());
                    taskFile.delete();
                }
            }
        }
        // 清理业务指标，业务指标保存7天
        File[] serviceKeyFiles = serviceKeyParent.listFiles();
        if (serviceKeyFiles != null && serviceKeyFiles.length > 0) {
            for (File serviceKeyFile : serviceKeyFiles) {
                iv = (new Date().getTime() - serviceKeyFile.lastModified()) / 1000;
                if (iv > weekIv) {
                    runLogger.debug(getClass(), "clean serviceKey file : " + serviceKeyFile.getAbsolutePath());
                    serviceKeyFile.delete();
                }
            }
        }
        // 清理脚本，脚本保存7天
        File[] scriptFiles = scriptParent.listFiles();
        if (scriptFiles != null && scriptFiles.length > 0) {
            for (File scriptFile : scriptFiles) {
                if (!scriptFile.isDirectory()) {
                    // scriptnote文件
                    iv = (new Date().getTime() - scriptFile.lastModified()) / 1000;
                    if (iv > weekIv) {
                        runLogger.debug(getClass(), "clean script xml file : " + scriptFile.getAbsolutePath());
                        scriptFile.delete();
                    }
                } else {
                    // scriptzip文件
                    File[] scriptZipFiles = scriptFile.listFiles();
                    for (File scriptZipFile : scriptZipFiles) {
                        iv = (new Date().getTime() - scriptZipFile.lastModified()) / 1000;
                        if (iv > weekIv) {
                            runLogger.debug(getClass(), "clean script zip file : " + scriptZipFile.getAbsolutePath());
                            scriptZipFile.delete();
                        }
                    }
                }
            }
        }
        // 清理拨测结果文件，结果文件保存一个月
        File[] resultUploadFiles = resultUploadParent.listFiles();
        if (resultUploadFiles != null && resultUploadFiles.length > 0) {
            for (File resultUploadFile : resultUploadFiles) {
                iv = (new Date().getTime() - resultUploadFile.lastModified()) / 1000;
                if (iv > monthIv) {
                    runLogger.debug(getClass(),
                            "clean script result upload file : " + resultUploadFile.getAbsolutePath());
                    resultUploadFile.delete();
                }
            }
        }
        // 清理拨测结果响应文件，响应文件保存一个月
        File[] resultRespFiles = resultRespParent.listFiles();
        if (resultRespFiles != null && resultRespFiles.length > 0) {
            for (File resultRespFile : resultRespFiles) {
                iv = (new Date().getTime() - resultRespFile.lastModified()) / 1000;
                if (iv > monthIv) {
                    runLogger.debug(getClass(), "clean result resp file : " + resultRespFile.getAbsolutePath());
                    resultRespFile.delete();
                }
            }
        }
    }
}
