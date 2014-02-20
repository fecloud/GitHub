/**
 * @(#) DaemonManager.java Created on 2012-3-2
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.uare.agent.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.aspire.uare.agent.util.ExecCommand;

/**
 * The class <code>DaemonManager</code>
 * 
 * @author Link Wang
 * @version 1.0
 */
public class DaemonManager {

    public final static String TAG = "DaemonManager";
    public final static String DAEMON_APP_NAME = "agentdaemon.jar";
    public final static String DAEMON_APP_NAME_3 = "agentdaemon1.5/agentdaemon.jar";
    public final static String START_UP_SHELL = "startAgent";
    public final static String DAEMON_JNI_23 = "libagentjni23.so";
    public final static String DAEMON_JNI_22 = "libagentjni22.so";
    public final static String DAEMON_JNI_22_3 = "agentdaemon1.5/libagentjni22.so";
    public final static String DAEMON_JNI_FB = "libagentjnifb.so";
    public final static String DAEMON_JNI_TOUCH = "libagentjnitouch.so";
    public final static String DAEMON_JPEG = "libmyjpeg.so";
    public final static String DAEMON_BMPZOOM = "libbmpzoom.so";
    public final static String TCPDUMP = "tcpdump.zip";

    public final static String DAEMON_CONF_FILE = "AgentConfig";
    public final static String DAEMON_LOCATION = "/data/local/tmp/inspur/agent/";

    public final static String[] DAEMON_APP_FILES = new String[] { DAEMON_APP_NAME, DAEMON_CONF_FILE, DAEMON_JNI_23,
            DAEMON_JPEG, DAEMON_JNI_FB, DAEMON_JNI_TOUCH };

    /**
     * Start agent daemon process.
     * 
     * @param context
     * @return
     * @throws Exception
     */
    public static void startAgentDaemon(Context context) throws Exception {
        File daemon = new File(DAEMON_LOCATION);
        ExecCommand.execCommandAsRoot("chmod 777 /data/local/tmp");
        AssetManager am = context.getAssets();
        if (daemon.exists()) {
            ExecCommand.killProcess("app_process");
            ExecCommand.execCommandAsRoot("rm -r " + DAEMON_LOCATION);
        }
        if (!daemon.mkdirs()) {
            throw new Exception("Fail to create daemon app home.");
        }

        for (String file : DAEMON_APP_FILES) {
            createFileFromAssert(DAEMON_LOCATION, file, file, am);
        }
        if (getSdkVersion() > 3) {
            createFileFromAssert(DAEMON_LOCATION, DAEMON_APP_NAME, DAEMON_APP_NAME, am);
            createFileFromAssert(DAEMON_LOCATION, DAEMON_JNI_22, DAEMON_JNI_22, am);
        } else {
            createFileFromAssert(DAEMON_LOCATION, DAEMON_APP_NAME, DAEMON_APP_NAME_3, am);
            createFileFromAssert("/system/lib/", DAEMON_JNI_22, DAEMON_JNI_22_3, am);
        }
        prepareShellCmd();

        // Start daemon app process
        ExecCommand.execCommandsAsRoot(new String[] { "cd " + DAEMON_LOCATION, "chmod 777 *",
                "sh ./" + START_UP_SHELL + " & > /dev/null" });
        Log.i(TAG, "Start agent daemon ok.");
    }

    public static void prepareShellCmd() throws Exception {
        // TODO Auto-generated method stub
        File file = new File(DAEMON_LOCATION + START_UP_SHELL);

        if (file.exists()) {
            ExecCommand.execCommandAsRoot("rm -r " + DAEMON_LOCATION + START_UP_SHELL);
        }
        if (!file.createNewFile()) {
            throw new Exception("Fail to create daemon start shell");
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        String ldPath = "export LD_LIBRARY_PATH=" + DAEMON_LOCATION + ":$LD_LIBRARY_PATH" + "\n";
        String clsPath = "export CLASSPATH=" + DAEMON_LOCATION + DAEMON_APP_NAME + "\n";

        // SharedPreferences settings = MyApplication.getSharedPreferences();
        // String key_delay = settings.getString("set_key_delay", String.valueOf(Constants.DEFAULT_KEY_DELAY));
        // String touch_delay = settings.getString("set_touch_delay", String.valueOf(Constants.DEFAULT_TOUCH_DELAY));
        // String buffer_size = settings.getString("set_buffer_size", String.valueOf(APSMessage.MAX_MSG_LEN / 1024));

        // String pic_transfer = "1.0";
        // if (settings.getBoolean("set_pic_transfer", false)) {
        // pic_transfer = "0.5";
        // }

        // startCmd.append("exec app_process /system/bin com.aspire.test.AgentDaemon ").append("50").append(" ")
        // .append("100").append(" ").append("500").append(" ").append(pic_transfer).append(" ")
        // .append(settings.getInt(Constants.WIGHT_KEY, 0)).append(" ")
        // .append(settings.getInt(Constants.HEIGHT_KEY, 0)).append(" ")
        // .append(settings.getString("set_rgb_format", "1")).append("\n");
        StringBuilder startCmd = new StringBuilder();
        startCmd.append("exec app_process /system/bin com.aspire.test.AgentDaemon ").append("50").append(" ")
                .append("100").append(" ").append("500").append("\n");
        bw.write(ldPath);
        bw.write(clsPath);
        bw.write(startCmd.toString());

        bw.close();

    }

    private static void createFileFromAssert(String outDir, String outName, String fileName, AssetManager am)
            throws IOException {
        InputStream in = null;
        FileOutputStream fout = null;
        ZipInputStream zipIn = null;
        Log.i(TAG, "Create agent daemon app file: " + fileName);
        in = am.open(fileName);

        try {
            if (fileName.endsWith(".zip")) {
                zipIn = new ZipInputStream(in);
                ZipEntry entry = zipIn.getNextEntry();
                File file = null;
                while (entry != null) {
                    if (entry.isDirectory()) {
                        String name = entry.getName();
                        file = new File(outDir + File.separator + name);
                        file.mkdir();

                    } else {
                        fout = new FileOutputStream(outDir + File.separator + entry.getName());
                        writeBuffer(fout, zipIn);
                        fout.close();
                        fout = null;
                    }
                    // 读取下一个ZipEntry
                    entry = zipIn.getNextEntry();
                }
                return;
            }
            fout = new FileOutputStream(outDir + outName);
            writeBuffer(fout, in);
        } catch (Exception ex) {
            throw new IOException("Fail to create daemon app file: " + fileName);
        } finally {
            if (zipIn != null) {
                try {
                    zipIn.close();
                } catch (Exception e) {
                    Log.w(TAG, "Close daemon app file zipInput-stream error.", e);
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    Log.w(TAG, "Close daemon app file input-stream error.", e);
                }
            }
            if (fout != null) {
                try {
                    fout.close();
                } catch (Exception e) {
                    Log.w(TAG, "Close daemon app file output-stream error.", e);
                }
            }

        }
    }

    private static void writeBuffer(FileOutputStream fout, InputStream in) {
        int read = 0;
        FileChannel fc = fout.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            while ((read = in.read(buffer.array())) != -1) {
                buffer.limit(read);
                fc.write(buffer);
                buffer.rewind();
            }
        } catch (Exception e) {
            if (fc != null) {
                try {
                    fc.close();
                } catch (Exception ex) {
                    Log.w(TAG, "Close daemon app file output-channel error.", e);
                }
            }
        }
    }

    /**
     * 获取系统SDK版本号
     * 
     * @return
     */
    public static int getSdkVersion() {
        int sdkVersion;
        try {
            sdkVersion = Integer.valueOf(android.os.Build.VERSION.SDK);
        } catch (NumberFormatException e) {
            sdkVersion = 0;
        }
        return sdkVersion;
    }

}
