/**
 * @(#) AdbHelper.java Created on 2012-7-19
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.service.adb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspire.sqmp.mobilemanager.service.RemoteDevice;
import com.aspire.sqmp.mobilemanager.service.adb.CmdExecute.CmdResponse;

/**
 * The class <code>AdbHelper</code>
 * 
 * @author wuyanlong
 * @version 1.0
 */
public class AdbHelper {
    /**
     * Logger
     */
    private final static Logger logger = LoggerFactory.getLogger(AdbHelper.class);

    /**
     * Execute a command of adb forward.
     * <p>
     * forward socket connection
     * 
     * @throws IOException
     */
    public static boolean AdbForward(int loacl, int remote) throws Exception {
        String adbCommad = String.format("adb forward tcp:%d tcp:%d", loacl, remote);
        CmdResponse response = CmdExecute.exec(adbCommad);
        if (!response.isRunSuccess())
            logger.info("Add forward faild:" + response.getMsg());
        return response.isRunSuccess();
    }

    /**
     * Execute a command of adb forward.
     * <p>
     * forward socket connection
     * 
     * @throws IOException
     */
    public static boolean AdbForward(String serial, int loacl, int remote) throws Exception {
        String adbCommad = String.format("adb -s %s forward tcp:%d tcp:%d", serial, loacl, remote);
        logger.info(adbCommad);
        CmdResponse response = CmdExecute.exec(adbCommad);
        if (!response.isRunSuccess())
            logger.info("Adb forward faild:" + response.getMsg());
        return response.isRunSuccess();
    }

    /**
     * Execute a command of adb install .
     * 
     * @param serial
     * @param apkPath
     * @return
     * @throws IOException
     */
    public static boolean AdbInstall(String serial, String apkPath) throws IOException {
        String adbCommad = String.format("adb -s %s install %s", serial, apkPath);
        CmdResponse response = CmdExecute.exec(adbCommad);
        if (!response.isRunSuccess()) {
            logger.info("Adb install faild: CmdExecute running error");
            return false;
        }
        if (!response.getMsg().contains("Success")) {
            logger.info("Adb install faild:" + response.getMsg());
            return false;
        }
        return true;
    }

    /**
     * Execute a command of adb push .
     * 
     * 注意不传递中文名字文件或文件夹- 会乱码
     * <p>
     * 另外不稳定的地方主要在传输带有中文名的文件或文件夹，出现传输断开几率很高
     * 
     * @param serial
     * @param localPath
     * @param remotePath
     * @throws IOException
     */
    public static TransferResult AdbPush(String serial, String localPath, String remotePath) throws IOException {
        String adbCommad = String.format("adb -s %s push %s %s", serial, localPath, remotePath);
        CmdResponse response = CmdExecute.exec(adbCommad);
        return new TransferResult(response);
    }

    /**
     * 
     * The class <code>TransferResult</code>
     * <p>
     * 传输文件夹时 {@link TransferResult#successCount}, {@link TransferResult#skippedCount}才有值，否则为-1
     * <p>
     * 传输成功 {@link TransferResult#successCount}, {@link TransferResult#skippedCount}才有值，否则为-1
     * <p>
     * 当adbpull或adbpush 失败时通过{@link TransferResult#getErrorMsg()}获取失败信息
     * 
     * @author wuyanlong
     * @version 1.0
     */
    public static class TransferResult extends CmdResponse {
        private int successCount = -1;
        private int skippedCount = -1;

        /**
         * Constructor
         * 
         * @param response
         */
        public TransferResult(CmdResponse response) {
            setErrorMsg(response.getErrorMsg());
            setMsg(response.getMsg());
            setRunSuccess(response.isRunSuccess());
            if (isRunSuccess())
                resolveResult(getErrorMsg());
        }

        /**
         * To resolve result
         * 
         * @param msg
         */
        private void resolveResult(String msg) {
            String[] lines = msg.split("\n");
            String targetLine = null;
            for (String line : lines) {
                if (line.contains("files")) {
                    targetLine = line;
                    break;
                }
            }
            if (targetLine == null)
                return;
            final Pattern numPattern = Pattern.compile("^-?[1-9]\\d*|0$");
            String[] sections = targetLine.split(" ");
            boolean isFirst = true;
            try {
                for (String section : sections) {
                    final boolean isFind = numPattern.matcher(section).find();
                    if (isFind && isFirst) {
                        setSuccessCount(Integer.parseInt(section));
                        isFirst = !isFirst;
                    } else if (isFind && !isFirst) {
                        setSkippedCount(Integer.parseInt(section));
                    }
                }
            } catch (Exception e) {
            }
        }

        /**
         * Getter of successCount
         * 
         * @return the successCount
         */
        public int getSuccessCount() {
            return successCount;
        }

        /**
         * Setter of successCount
         * 
         * @param transferCount
         *            the successCount to set
         */
        public void setSuccessCount(int successCount) {
            this.successCount = successCount;
        }

        /**
         * Getter of skippedCount
         * 
         * @return the skippedCount
         */
        public int getSkippedCount() {
            return skippedCount;
        }

        /**
         * Setter of skippedCount
         * 
         * @param skippedCount
         *            the skippedCount to set
         */
        public void setSkippedCount(int skippedCount) {
            this.skippedCount = skippedCount;
        }

    }

    /**
     * Execute a command of adb pull .
     * <p>
     * copy file/dir from device
     * 
     * 注意不传递中文名字文件或文件夹-
     * <p>
     * 另外不稳定的地方主要在传输带有中文名的文件或文件夹，出现传输断开几率很高
     * 
     * @param serial
     * @param remotePath
     * @param localPath
     * @throws IOException
     */
    public static TransferResult AdbPull(String serial, String remotePath, String localPath) throws IOException {
        String adbCommad = String.format("adb -s %s pull %s %s", serial, remotePath, localPath);
        CmdResponse respone = CmdExecute.exec(adbCommad);
        return new TransferResult(respone);
    }

    /**
     * Get list of devices
     * 
     * @return
     * @throws IOException
     */
    public static List<RemoteDevice> AdbGetDevices() throws IOException {
        CmdResponse respone = CmdExecute.exec("adb devices");
        List<RemoteDevice> devicesList = new ArrayList<RemoteDevice>();
        if (!respone.isRunSuccess())
            return devicesList;
        if (respone.getMsg() == null)
            return devicesList;
        String result = respone.getMsg();
        String[] devices = result.split("\n");
        for (String d : devices) {
            String[] param = d.split("\t");
            if (param.length == 2) {
                devicesList.add(new RemoteDevice(param[0], DeviceAdbState.getState(param[1])));
            }
        }
        return devicesList;
    }

}
