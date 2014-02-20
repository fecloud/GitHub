/**
 * @(#) UpdateScriptToMobile.java Created on 2012-8-10
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.script;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspire.common.config.Config;
import com.aspire.common.exception.ExceptionHandler;
import com.aspire.common.exception.MException;
import com.aspire.common.util.MobileManagerConstants;
import com.aspire.common.util.XmlUtil;
import com.aspire.mgt.ats.tm.sync.script.entity.ScriptDetail;
import com.aspire.mgt.ats.tm.sync.script.entity.ScriptNote;
import com.aspire.sqmp.mobilemanager.entity.Device;
import com.aspire.sqmp.mobilemanager.service.DeviceManager;
import com.aspire.sqmp.mobilemanager.service.RemoteDevice;
import com.aspire.sqmp.mobilemanager.service.RemoteDeviceManager;
import com.aspire.sqmp.mobilemanager.service.adb.AdbHelper;
import com.aspire.sqmp.mobilemanager.service.adb.AdbHelper.TransferResult;

/**
 * The class <code>UpdateScriptToMobile</code>
 * 
 * @author likai
 * @version 1.0
 */
public class SyncScriptToMobile {

    protected Logger logger = LoggerFactory.getLogger(SyncScriptToMobile.class);
    private RemoteDeviceManager remoteDeviceManager = RemoteDeviceManager.getInstance();
    /**
     * 需同步的设备
     */
    private Device device;
    /**
     * 设备在list中的位置
     */
    private int position;
    /**
     * 窗口显示的shell
     */
    private Shell shell;
    /**
     * 获取common的配置信息
     */
    private Config config;

    /**
     * 存储设备信息用的类
     */
    private DeviceManager deviceManager = DeviceManager.getInstance();
    /**
     * 保存时间
     */
    private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 存储已经传送的zip，以免同文件再次传送
     */
    public Set<String> scriptFileSet = new HashSet<String>();

    /**
     * Getter of device
     * 
     * @return the device
     */
    public Device getDevice() {
        return device;
    }

    /**
     * Getter of position
     * 
     * @return the position
     */
    public int getPosition() {
        return position;
    }

    /**
     * Setter of device
     * 
     * @param device
     *            the device to set
     */
    public void setDevice(Device device) {
        this.device = device;
    }

    /**
     * Setter of position
     * 
     * @param position
     *            the position to set
     */
    public void setPosition(int position) {
        this.position = position;
    }

    public SyncScriptToMobile(Shell shell, Config config) {
        this.shell = shell;
        this.config = config;

    }

    /**
     * 开始传送scrpitquery.xml，以及发起后续动作
     * 
     * @throws MException
     */
    public void syncToMobile() throws MException {
        deleteMobileLocalScript();
        String localPath = MobileManagerConstants.LOCAL_PARENT_PATH + "\\"
                + config.get(MobileManagerConstants.TEST_SCRIPT_FTP_PATH) + "\\" + device.getModel() + "\\"
                + MobileManagerConstants.SCRIPT_QUERY_FILE_NAME;
        File tmp = new File(localPath);
        if (tmp.exists()) {
            localPath = tmp.getAbsolutePath();
            localPath = localPath.replace(" ", "\" \"");
            String remotePath = device.getSdcardPath() + device.getDataParentPath() + MobileManagerConstants.MOBILE_LOCAL_ADDRESS + "\\"
                    + config.get(MobileManagerConstants.TEST_SCRIPT_FTP_PATH) + "\\"
                    + MobileManagerConstants.SCRIPT_QUERY_FILE_NAME;
            remotePath = replaceSeparator(remotePath).replace(" ", "\" \"");
            try {
                logger.debug("push script query xmlfile beginned");
                TransferResult transferResult = AdbHelper.AdbPush(device.getAdbDeviceName(), localPath, remotePath);
                if (transferResult.isRunSuccess()) {
                    logger.debug("push script query xmlfile finished and successful");
                    parseScriptNote();
                } else {
                    MessageDialog.openError(shell, "提示", "同步脚本查询文件时失败，请查看！");
                    logger.error("push script query xmlfile failed, and the errMessage is "
                            + transferResult.getErrorMsg());
                }
            } catch (IOException e) {
                MException mexception = ExceptionHandler.handle(e, "sync script file to mobile");
                if (mexception != null) {
                    throw mexception;
                }
            }
        } else {
            logger.error("script query xmlfile is not exist,file path is " + localPath);
        }
    }

    /**
     * 解析文件成scriptNote对象
     * 
     * @throws MException
     */
    private void parseScriptNote() throws MException {
        final String pathName = MobileManagerConstants.LOCAL_PARENT_PATH + "\\"
                + config.get(MobileManagerConstants.TEST_SCRIPT_FTP_PATH) + "\\" + device.getModel() + "\\"
                + MobileManagerConstants.TEST_MODE_INPHONE;
        File inphone = new File(pathName);
        File[] files = inphone.listFiles();
        if (files != null && files.length > 1) {
            for (File f : files) {
                if (f.isFile() && f.getName().endsWith(".xml")) {
                    FileInputStream inputStream;
                    ScriptNote scriptNote;
                    try {
                        String localPath = f.getAbsolutePath().replace(" ", "\" \"");
                        String remotePath = device.getSdcardPath() + device.getDataParentPath() + MobileManagerConstants.MOBILE_LOCAL_ADDRESS
                                + "\\" + config.get(MobileManagerConstants.TEST_SCRIPT_FTP_PATH) + "\\"
                                + device.getModel() + "\\" + MobileManagerConstants.TEST_MODE_INPHONE + "\\"
                                + f.getName();
                        remotePath = replaceSeparator(remotePath).replace(" ", "\" \"");
                        logger.debug("push scriptnote xml to mobile beginned");
                        TransferResult transferResult = AdbHelper.AdbPush(device.getAdbDeviceName(), localPath,
                                remotePath);
                        if (transferResult.isRunSuccess()) {
                            logger.debug("push scriptnote xml to mobile finished , successfully");
                            inputStream = new FileInputStream(f);
                            scriptNote = (ScriptNote) XmlUtil.deSerialize(ScriptNote.class, inputStream);
                            parseScriptDetails(scriptNote);
                        } else {
                            MessageDialog.openError(shell, "错误", "同步脚本xml时出错，请检查");
                            logger.error("push scriptnote xml to mobile failed,err message : "
                                    + transferResult.getErrorMsg());
                        }
                    } catch (IOException e) {
                        MException mexception = ExceptionHandler
                                .handle(e, "adb push the scriptnote xml file to mobile");
                        if (mexception != null) {
                            throw mexception;
                        }
                    }
                }
            }
        } else {
            logger.error("there is no script file to sync");
        }
    }

    /**
     * 处理scriptdetial
     * 
     * @param scriptNote
     * @throws MException
     */
    private void parseScriptDetails(ScriptNote scriptNote) throws MException {
        if (scriptNote != null && scriptNote.getScriptDetails() != null
                && scriptNote.getScriptDetails().getScriptDetail() != null
                && scriptNote.getScriptDetails().getScriptDetail().size() > 0) {
            List<ScriptDetail> detailList = mergeScriptByTarget(device.getProvinceCode(), scriptNote.getScriptDetails()
                    .getScriptDetail());
            for (ScriptDetail it : detailList) {
                parse(it);
            }
            MessageDialog.openInformation(shell, "提示", "同步脚本完成！");
            device.setScriptLastSyncTime(dateformat.format(new Date()));
            device.setScriptHasUpdate(false);
            deviceManager.updateDevice(position, device);
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
            if (targetId.equals(it.getTargetId()) || "common".equals(it.getTargetId())) {
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
     * 发送下载脚本请求
     * 
     * @param scriptDetail
     * @param sdf
     * @throws Exception
     */
    public void parse(ScriptDetail scriptDetail) throws MException {
        if (device.getMobileScriptLastUpdateTime() == null
                || scriptDetail.getUploaddate().compareTo(device.getMobileScriptLastUpdateTime()) > 0) {
            final String filePath = MobileManagerConstants.LOCAL_PARENT_PATH + "\\"
                    + config.get(MobileManagerConstants.TEST_SCRIPT_FTP_PATH) + "\\" + device.getModel() + "\\"
                    + MobileManagerConstants.TEST_MODE_INPHONE + "\\" + scriptDetail.getTargetId() + "\\"
                    + scriptDetail.getScriptName();
            String remotePath = device.getSdcardPath() + device.getDataParentPath() + MobileManagerConstants.MOBILE_LOCAL_ADDRESS + "\\"
                    + config.get(MobileManagerConstants.TEST_SCRIPT_FTP_PATH) + "\\" + device.getModel() + "\\"
                    + MobileManagerConstants.TEST_MODE_INPHONE + "\\" + scriptDetail.getTargetId() + "\\"
                    + scriptDetail.getScriptName();
            remotePath = replaceSeparator(remotePath).replace(" ", "\" \"");
            File scriptFile = new File(filePath);
            final String localPath = scriptFile.getAbsolutePath().replace(" ", "\" \"");
            logger.debug("push script zip to mobile beginned");
            try {
                if (!scriptFileSet.contains(localPath)) {
                    TransferResult transferResult = AdbHelper.AdbPush(device.getAdbDeviceName(), localPath, remotePath);
                    if (transferResult.isRunSuccess()) {
                        scriptFileSet.add(localPath);
                        logger.debug("push script zip to mobile finished ,successfully");
                    } else {
                        MessageDialog.openError(shell, "错误", "同步脚本" + scriptFile.getName() + "时出错，请检查");
                        logger.error("push script zip to mobile failed, err message : " + transferResult.getErrorMsg());
                        return;
                    }
                }
            } catch (IOException e) {
                MException mexception = ExceptionHandler.handle(e, "push script zip to mobile");
                if (mexception != null) {
                    throw mexception;
                }
            }
        }
    }
    
    /**
     * 删除手机端local文件夹下的脚本相关文件
     * @throws MException
     */
    private void deleteMobileLocalScript() throws MException{
        RemoteDevice remoteDevice = remoteDeviceManager.getRemoteDevice(device.getAdbDeviceName());
        try {
            remoteDevice.syncGetCase(0);
        } catch (IOException e) {
            MException mexception = ExceptionHandler.handle(e, "send order to mobile , in order to delete local script file");
            if (mexception != null) {
                throw mexception;
            }
        }
    }
    
    private String replaceSeparator(String str) {
        if (str == null) {
            return str;
        }
        str = str.replace("\\", "/");
        return str;
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
    }
}
