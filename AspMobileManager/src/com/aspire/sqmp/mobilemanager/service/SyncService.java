/**
 * @(#) SyncService.java Created on 2012-7-16
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspire.common.config.Config;
import com.aspire.common.config.ConfigManager;
import com.aspire.common.exception.ExceptionHandler;
import com.aspire.common.exception.MException;
import com.aspire.common.util.FtpClient;
import com.aspire.common.util.HttpClient;
import com.aspire.common.util.MobileManagerConstants;
import com.aspire.mgt.ats.tm.sync.IFTPClient.FILE_TYPE;
import com.aspire.mgt.ats.tm.sync.util.ftp.ClientConfig;
import com.aspire.sqmp.mobilemanager.entity.Device;
import com.aspire.sqmp.mobilemanager.script.MScriptRequest;
import com.aspire.sqmp.mobilemanager.script.SyncScriptToMobile;
import com.aspire.sqmp.mobilemanager.service.adb.AdbHelper;
import com.aspire.sqmp.mobilemanager.service.adb.AdbHelper.TransferResult;
import com.aspire.sqmp.mobilemanager.task.TaskRequest;

/**
 * The class <code>SyncService</code>
 * 
 * @author linjunsui
 * @version 1.0
 */
public class SyncService implements ISyncService {

    protected Logger logger = LoggerFactory.getLogger(SyncService.class);

    /**
     * 保存读取config.ini配置文件
     */
    private Config config;

    /**
     * 存储ftpclient登录所需的配置信息
     */
    private ClientConfig clientConfig = null;

    /**
     * ftp登录器
     */
    private FtpClient ftpClient = null;

    /**
     * http连接器
     */
    private HttpClient httpClient = null;

    /**
     * 本地文件保存路径
     */
    private String ftpLocalPath = null;

    /**
     * 保存设备信息
     */
    private Device device;
    private DeviceManager deviceManager;
    private RemoteDeviceManager remoteDeviceManager = RemoteDeviceManager.getInstance();
    /**
     * 显示时间格式
     */
    private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private Shell shell;

    /**
     * Setter of device
     * 
     * @param device
     *            the device to set
     */
    public void setDevice(Device device) {
        this.device = device;
        ftpLocalPath = MobileManagerConstants.LOCAL_PRIVATE_PATH + "\\" + device.getFolderName();
    }

    /**
     * Setter of shell
     * 
     * @param shell
     *            the shell to set
     */
    public void setShell(Shell shell) {
        this.shell = shell;
    }

    public SyncService() {
        config = ConfigManager.getConfig("global");
        deviceManager = DeviceManager.getInstance();
    }

    // @Override
    // public void downloadPassword() throws MException {
    // UpdateResultPassword updateResultPassword = new UpdateResultPassword(config);
    // updateResultPassword.execute();
    // }

    // @Override
    // public void downloadResultResponse() throws MException {
    // String ftpPath_pre = "test_result\\resp";
    // int ftpPort = 21;
    // String ftpIp = "10.1.5.154";
    // String ftpUser = "szzw";
    // String ftpPwd = "szzw+888";
    // File xmlFile = new File(".\\data\\testresult.xml");
    // if(xmlFile.exists()){
    // FileInputStream input = null;
    // FileOutputStream output = null;
    // try {
    // try{
    // input = new FileInputStream(xmlFile);
    // TestResults results = (TestResults)XmlUtil.deSerialize(TestResults.class, input);
    // List<TestResultInfo> resultInfos = results.getTestResult();
    // input.close();
    // input = null;
    // clientConfig = new ClientConfig(ftpIp, ftpPort, ftpUser, ftpPwd);
    // ftpClient = new FtpClient(clientConfig);
    // FileResponseProcessor processor = null;
    // DownloadFileHandler responseHandler = null;
    // for(TestResultInfo resultInfo : resultInfos){
    // if(resultInfo.getRespFileName() == null || resultInfo.getRespFileName().equals("")){
    // String ftpPath = ftpPath_pre + File.separator + resultInfo.getUploadTime();
    // String fileName = "E_" + resultInfo.getUploadFileName();
    // File parent = new File(ftpLocalPath, ftpPath);
    // if (!parent.exists()){
    // parent.mkdirs();
    // }
    // File respFile = new File(parent,fileName);
    // responseHandler = new DownloadFileHandler();
    // responseHandler.setResponseFile(respFile);
    // processor = new FileResponseProcessor(responseHandler);
    // ftpClient.downloadFile(ftpPath, fileName, FILE_TYPE.BINARY_FILE_TYPE, processor);
    // if(responseHandler.isSucc()){
    // resultInfo.setRespFileName(fileName);
    // }
    // }
    // }
    // results.setTestResult(resultInfos);
    // output = new FileOutputStream(xmlFile);
    // XmlUtil.serialize(results, output);
    // output.flush();
    // output.close();
    // output = null;
    // }finally{
    // if(input != null){
    // input.close();
    // input = null;
    // }
    // if(output != null){
    // output.flush();
    // output.close();
    // output = null;
    // }
    // }
    // } catch (Exception e) {
    // MException mexception = ExceptionHandler.handle(e, "download result resp file");
    // if (mexception != null) {
    // throw mexception;
    // }
    // }
    // }
    // }

    @Override
    public void uploadResult(int position) throws MException {
        try {
            String ftpPathPrex = config.get(MobileManagerConstants.TEST_RESULT_FTP_PATH) + "\\upload";
            int ftpPort = Integer.parseInt(config.get(MobileManagerConstants.TEST_RESULT_FTP_PORT));
            String ftpIp = config.get(MobileManagerConstants.TEST_RESULT_FTP_IP);
            String ftpUser = config.get(MobileManagerConstants.TEST_RESULT_FTP_USER);
            String ftpPwd = config.get(MobileManagerConstants.TEST_RESULT_FTP_PWD);
            File filePath = new File(ftpLocalPath, ftpPathPrex);
            if (!filePath.exists()){
                logger.debug(filePath.getAbsolutePath() + " is not existing");
                return;
            }
            logger.debug("upload result begined");
            File[] resultFileParents = filePath.listFiles();
            if (resultFileParents != null && resultFileParents.length > 0) {
                for (File resultFileParent : resultFileParents) {
                    if (resultFileParent.isDirectory()) {
                        String ftpPath = ftpPathPrex + "\\" + resultFileParent.getName();
                        clientConfig = new ClientConfig(ftpIp, ftpPort, ftpUser, ftpPwd);
                        ftpClient = new FtpClient(clientConfig);
                        File[] resultFiles = resultFileParent.listFiles();
                        if (resultFiles != null && resultFiles.length > 0) {
                            for (File resultFile : resultFiles) {
                                FileInputStream local = null;
                                try {
                                    local = new FileInputStream(resultFile);
                                    String ftpFileName = ftpClient.uploadFile(ftpPath, resultFile.getName(),
                                            FILE_TYPE.BINARY_FILE_TYPE, local);
                                    local.close();
                                    local = null;
                                    if (ftpFileName != null) {
                                        resultFile.delete();
                                        device.setResultHasUpload(false);
                                        device.setResultLastUploadTime(dateformat.format(new Date()));
                                        deviceManager.updateDevice(position, device);
                                        logger.info("upload result file '" + resultFile.getName() + "' finished");
                                    } else {
                                        logger.error("fail to upload result file '" + resultFile.getName() + "'");
                                    }
                                    logger.debug("upload result finished");
                                } finally {
                                    if (local != null) {
                                        local.close();
                                        local = null;
                                    }
                                }

                            }
                            if (resultFileParent.listFiles().length == 0) {
                                resultFileParent.delete();
                            } else {
                                logger.error("folder :" + resultFileParent.getName() + "has not uploaded all files.");
                            }
                        } else {
                            resultFileParent.delete();
                            logger.warn("This folder does not contain any files, so delete this folder");
                        }
                    }
                }
            } else {
                logger.warn("No resultFile need to upload");
            }
        } catch (Exception e) {
            MException mexception = ExceptionHandler.handle(e, "upload result file");
            if (mexception != null) {
                throw mexception;
            }
        }

    }

    // @Override
    // public void downloadServicekey() throws MException {
    // String ftpPath = "sqmp\\szzw\\service_key";
    // int ftpPort = 21;
    // String ftpIp = "10.1.5.163";
    // String ftpUser = "ftpsvr";
    // String ftpPwd = "ftpsvr";
    // String serviceKeyFileName = config.get(MobileManagerConstants.SERVICEKEY_FILE_NAME);
    // try {
    // clientConfig = new ClientConfig(ftpIp, ftpPort, ftpUser, ftpPwd);
    // ftpClient = new FtpClient(clientConfig);
    // String fileName = ftpClient.download(ftpLocalPath, ftpPath, serviceKeyFileName);
    // if (fileName != null) {
    // config.set(MobileManagerConstants.SERVICEKEY_FILE_NAME, fileName);
    // }
    // } catch (Exception e) {
    // MException mexception = ExceptionHandler.handle(e, "");
    // if (mexception != null) {
    // throw mexception;
    // }
    // }
    // }

    @Override
    public void downloadCase(int position) throws MException {
        logger.debug("download case beginned");
        String url = config.get(MobileManagerConstants.TEST_SCRIPT_QUERY_URL);
        String ftpPath = config.get(MobileManagerConstants.TEST_SCRIPT_FTP_PATH);
        int ftpPort = Integer.parseInt(config.get(MobileManagerConstants.TEST_SCRIPT_FTP_PORT));
        String ftpIp = config.get(MobileManagerConstants.TEST_SCRIPT_FTP_IP);
        String ftpUser = config.get(MobileManagerConstants.TEST_SCRIPT_FTP_USER);
        String ftpPwd = config.get(MobileManagerConstants.TEST_SCRIPT_FTP_PWD);
        String companyCode = config.get(MobileManagerConstants.COMPANY_CODE);
        String provinceCode = device.getProvinceCode();
        String devType = device.getModel();
        String imei = device.getImei();
        String lastUpdate = device.getpCScriptLastUpdateTime();
        MScriptRequest scriptRequest = new MScriptRequest();
        try {
            clientConfig = new ClientConfig(ftpIp, ftpPort, ftpUser, ftpPwd);
            ftpClient = new FtpClient(clientConfig);
            httpClient = new HttpClient();
            scriptRequest.request(MobileManagerConstants.TEST_MODE_INPHONE, companyCode, provinceCode, url, devType,
                    lastUpdate, ftpClient, httpClient, imei, MobileManagerConstants.LOCAL_PARENT_PATH, ftpPath);
            device = deviceManager.getDeviceList().get(position);
            if (!device.isScriptHasUpdate()) {
                MessageDialog.openInformation(shell, "提示", "您当前已经拥有最新脚本，无需更新");
            }
            logger.debug("download case finished");
        } catch (ParseException e) {
            MException mexception = ExceptionHandler.handle(e, "Error while doDownloadTestCase, date format");
            if (mexception != null) {
                throw mexception;
            }
        } catch (Exception e) {
            MException mexception = ExceptionHandler.handle(e, "downloadCase");
            if (mexception != null) {
                throw mexception;
            }
        } finally {
            if (ftpClient != null) {
                ftpClient.close();
            }
        }
    }

    @Override
    public void downloadTask(int position) throws MException {
        logger.debug("download task beginned");
        String url = config.get(MobileManagerConstants.TASK_DOWNLOAD_URL);
        String companyCode = config.get(MobileManagerConstants.COMPANY_CODE);
        httpClient = new HttpClient();
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.request(url, httpClient, companyCode, device);
        logger.debug("download task finished");
    }

    @Override
    public void syncTask(int position) throws MException {
        if(!getSdcardPath())
            return;
        String localPath = MobileManagerConstants.LOCAL_PRIVATE_PATH + "\\" + device.getFolderName() + "\\"
                + MobileManagerConstants.TASK_FILE_PATH + "\\" + MobileManagerConstants.TASK_QUERY_FILE_NAME;
        File tmp = new File(localPath);
        if (tmp.exists()) {
            localPath = tmp.getAbsolutePath();
            localPath = localPath.replace(" ", "\" \"");
            String remotePath = device.getSdcardPath() + device.getDataParentPath() + MobileManagerConstants.MOBILE_LOCAL_ADDRESS + "\\"
                    + MobileManagerConstants.TASK_FILE_PATH + "\\" + MobileManagerConstants.TASK_QUERY_FILE_NAME;
            remotePath = replaceSeparator(remotePath).replace(" ", "\" \"");
            try {
                logger.debug("push task file beginned");
                TransferResult transferResult = AdbHelper.AdbPush(device.getAdbDeviceName(), localPath, remotePath);
                if (transferResult.isRunSuccess()) {
                    tmp.delete();
                    device.setTaskHasUpdate(false);
                    device.setTaskLastSyncTime(dateformat.format(new Date()));
                    deviceManager.updateDevice(position, device);
                    MessageDialog.openInformation(shell, "提示", "同步任务成功！");
                    logger.debug("push task file finished, and result is " + transferResult.isRunSuccess());
                } else {
                    MessageDialog.openInformation(shell, "警告", "同步任务失败，请重新同步！");
                    logger.debug("push task file failed, and the errMessage is " + transferResult.getErrorMsg());
                }
            } catch (IOException e) {
                MException mexception = ExceptionHandler.handle(e, "sync task file to mobile");
                if (mexception != null) {
                    throw mexception;
                }
            }
        } else {
            logger.error("task file is not exist,file path is " + localPath);
        }
    }

    @Override
    public void syncScript(int position) throws MException {
        if(!getSdcardPath())
            return;
        RemoteDevice remoteDevice = remoteDeviceManager.getRemoteDevice(device.getAdbDeviceName());
        String mobileScriptLastUpdateTime;
        try {
            mobileScriptLastUpdateTime = remoteDevice.syncGetCase(1);
            if(mobileScriptLastUpdateTime != null && !mobileScriptLastUpdateTime.equals("")){
                if(device.getpCScriptLastUpdateTime().compareTo(mobileScriptLastUpdateTime) <= 0){
                    MessageDialog.openInformation(shell, "提示", "终端设备无需更新脚本");
                    return;
                }
            }
            SyncScriptToMobile scriptToMobile = new SyncScriptToMobile(shell, config);
            scriptToMobile.setDevice(device);
            scriptToMobile.setPosition(position);
            scriptToMobile.syncToMobile();
        } catch (IOException e) {
            MException mexception = ExceptionHandler.handle(e, "sync script to mobile");
            if (mexception != null) {
                throw mexception;
            }
        }
    }

    @Override
    public boolean syncResult(int position) throws MException {
        if(!getSdcardPath())
            return false;
        String remotePath = device.getSdcardPath() + device.getDataParentPath() + "\\" + MobileManagerConstants.MOBILE_LOCAL_ADDRESS + "\\"
                + config.get(MobileManagerConstants.TEST_RESULT_FTP_PATH);
        remotePath = replaceSeparator(remotePath).replace(" ", "\" \"");
        String localPath = MobileManagerConstants.LOCAL_PRIVATE_PATH + "\\" + device.getFolderName() + "\\"
                + config.get(MobileManagerConstants.TEST_RESULT_FTP_PATH);
        File tmp = new File(localPath);
        if (!tmp.exists())
            tmp.mkdirs();
        localPath = tmp.getAbsolutePath().replace(" ", "\" \"");
        try {
            logger.debug("pull result file beginned");
            logger.debug(remotePath);
            logger.debug(localPath);
            TransferResult transferResult = AdbHelper.AdbPull(device.getAdbDeviceName(), remotePath, localPath);
            if (transferResult.isRunSuccess()) {
                device.setResultLastSyncTime(dateformat.format(new Date()));
                device.setResultHasUpload(true);
                deviceManager.updateDevice(position, device);
                MessageDialog.openInformation(shell, "提示", "同步拨测结果文件成功！");
            } else if (!transferResult.isRunSuccess()) {
                MessageDialog.openInformation(shell, "警告", "同步拨测结果文件失败，请重新同步！\n同步成功" + transferResult.getSuccessCount()
                        + "个文件,同步失败" + transferResult.getSkippedCount() + "个文件。");
            }
            logger.debug("pull result file finished");
            return transferResult.isRunSuccess();
        } catch (IOException e) {
            MException mexception = ExceptionHandler.handle(e, "sync result from mobile");
            if (mexception != null) {
                throw mexception;
            }
        }
        return false;
    }

    /**
     * 初始化分隔符
     * @param str
     * @return
     */
    private String replaceSeparator(String str) {
        if (str == null) {
            return str;
        }
        str = str.replace("\\", "/");
        return str;
    }
    
    /**
     * 获取手机中sdcard的根目录
     * @return
     * @throws MException
     */
    public boolean getSdcardPath() throws MException{
        RemoteDevice remoteDevice = remoteDeviceManager.getRemoteDevice(device.getAdbDeviceName());
        String sdcardPath = null;
        try {
            sdcardPath = remoteDevice.syncGetSdcardPath().getSdcardPath();
            if(device.getSdcardPath() == null || device.getSdcardPath().equals("")){
                if(sdcardPath == null || sdcardPath.equals("")){
                    logger.debug("get sdcard path error");
                    MessageDialog.openInformation(shell, "错误", "无法获取手机端的sdcard根目录！");
                    return false;
                }
                device.setSdcardPath(sdcardPath);
                return true;
            }
        } catch (IOException e) {
            logger.debug("get sdcard path error, errmessage is " + e.getMessage());
            ExceptionHandler.handle(e, "get sdcard path");
        }
        return false;
    }
}
