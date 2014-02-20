/**
 * @(#) DoUpload.java Created on 2012-8-9
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.result.sync;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;

import com.aspire.android.common.exception.ExceptionHandler;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.PreferencesManager;
import com.aspire.android.test.application.ExecApplication;
import com.aspire.android.test.execute.NameConstants;
import com.aspire.android.test.execute.NameConstants.SharedPrefConstants;
import com.aspire.android.test.execute.ui.PrefsActivity;
import com.aspire.android.test.log.RunLogger;
import com.aspire.android.test.script.service.IScriptService;
import com.aspire.android.test.sync.pub.LocalFtpClient;
import com.aspire.android.test.sync.pub.RemoteFtpClient;
import com.aspire.android.test.task.entity.Upload;
import com.aspire.android.test.task.service.ITaskService;
import com.aspire.android.util.DesUtil;
import com.aspire.mgt.ats.tm.sync.util.ftp.ClientConfig;
import com.aspire.mgt.ats.tm.sync.util.zip.Zip;
import com.google.inject.Inject;

/**
 * The class <code>DoUpload</code>
 * 
 * @author gouanming
 * @version 1.0
 */
public class DoUpload {

    private Context mContext;

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
     * 本地文件的主目录
     */
    private String ftpLocalPath;
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
    private boolean dataGetSwitch;

    private SharedPreferences preferences;
    private PreferencesManager preferencesManager = PreferencesManager.getInstance();

    /**
     * Constructor
     * 
     * @param taskService
     * @param scriptService
     * @param mContext
     */
    @Inject
    public DoUpload(Context mContext) {
        this.mContext = mContext;
        this.preferences = preferencesManager.getPreferences();
    }

    /**
     * 初始化所需要的配置信息
     */
    public void initialUpload() throws MException {
        this.ftpLocalPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + preferences.getString(PrefsActivity.SET_SAVE_ADDRESS, null);
        this.provinceCode = preferences.getString(PrefsActivity.PROVINCE, null);
        this.cityCode = preferences.getString(PrefsActivity.CITY, null);
        this.devType = preferences.getString(PrefsActivity.TERMINAL, null);
        this.imei = preferences.getString(PrefsActivity.IMEI, null);
        this.dataGetSwitch = preferences.getBoolean(PrefsActivity.DATA_GET_SWITCH, true);
    }

    /**
     * 上传测试结果
     * 
     * @throws MException
     */
    public void doUploadResult(Long... taskItemBatchId) throws MException {
        ExecApplication.instance().getUploadList().add(taskItemBatchId);
    }

    public void uploadTestResult() throws MException {
        List<Long[]> uploadList = ExecApplication.instance().getUploadList();
        for (Long[] taskItemBatchId : uploadList) {

            String ftpPath = preferences.getString(PrefsActivity.SERVICE_PATH_FOUR, null);
            // 打包拨测结果
            Upload upload = zipResultFile(ftpPath, taskItemBatchId);

            if (upload != null) {
                uploadNet(ftpPath, upload, 1);
            }

            ExecApplication.instance().getRedoTaskHash().remove(taskItemBatchId);
        }
    }

    /**
     * 将测试结果打包
     * 
     * @param ftpPath
     *            测试结果所在的路径
     * @throws MException
     */
    private Upload zipResultFile(String ftpPath, Long... taskItemBatchId) throws MException {
        String pwd = preferences.getString(SharedPrefConstants.RESULT_PWD_CURRENT, null);
        if (pwd == null || pwd.equals(""))
            throw new MException("the result encrypt key is null");
        Upload upload = null;
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
            writeTxtFinish = mWriteResultTxt.UploadResult(txtFile.getParent(), txtFile.getName(), taskItemBatchId);
        } catch (MException e) {
            throw e;
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
                upload = taskService.addUpload(mUpload);
            } catch (IOException e) {
                MException mException = ExceptionHandler.handle(e, "Error while zipResultFile");
                throw mException;
            }
        }
        return upload;
    }

    /**
     * 
     * @param ftpPath
     * @param upload
     * @param type 1:手机端手动选择上传，2：pc端请求的上传
     * @return 上传的文件的绝对路径，对pc端使用
     * @throws MException
     */
    private String uploadNet(String ftpPath, Upload upload, int type) throws MException {
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
        if (dataTimeOut == null || dataTimeOut.trim().equals("")) {
            dataTimeOut = "0";
        }
        // 单位分钟
        timeOut = Integer.parseInt(dataTimeOut) * 1000 * 60;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String today = dateFormat.format(new Date());
        String zipParentPath = ftpPath + File.separator + "upload" + File.separator + today;
        try {

            File upFile = new File(upload.getUploadPath());
            FileInputStream input = new FileInputStream(upFile);
            String ftpName = null;
            if (dataGetSwitch && type == 1) {
                config = new ClientConfig(ftpIp, ftpPort, ftpUserName, ftpPwd);
                config.setDataTimeOut(timeOut);
                remoteFtpClient = new RemoteFtpClient(config);
                ftpName = remoteFtpClient.uploadFile(zipParentPath, upFile.getName(),
                        RemoteFtpClient.FILE_TYPE.BINARY_FILE_TYPE, input);
            } else {
                localFtpClient = new LocalFtpClient(ftpLocalPath + NameConstants.LOCAL_ADDRESS);
                ftpName = localFtpClient.uploadFile(zipParentPath, upFile.getName(), input);
            }
            if (ftpName != null) {
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
                String zipAbsolutePath = ftpLocalPath
                        + NameConstants.LOCAL_ADDRESS + "/" + zipParentPath + "/" + ftpName;
                return zipAbsolutePath;
            }
            input.close();
            input = null;
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
        return null;
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
        if (pwd == null || pwd.equals(""))
            throw new MException("the result encrypt key is null");
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

    public String zipResultForPc(List<Long> taskItemBatchIds) throws MException {
        String ftpPath = preferences.getString(PrefsActivity.SERVICE_PATH_FOUR, "test_result") + "_tmp";
        Long[] idArrays = new Long[taskItemBatchIds.size()];
        taskItemBatchIds.toArray(idArrays);
        // 打包拨测结果
        Upload upload = zipResultFile(ftpPath, idArrays);

        if (upload != null) {
            return uploadNet(ftpPath, upload, 2);
        }
        return null;
    }
}
