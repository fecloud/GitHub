/**
 * @(#) ExecCommand.java Created on 2012-5-7
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.uare.agent.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

import android.util.Log;

/**
 * The class <code>ExecCommand</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class ExecCommand {
    public final static String TAG = "ExecCommand";
    private static final int Length_ProcStat = 9;

    public static void execCommandsAsRoot(String[] cmds) throws Exception {
        execCommands(Runtime.getRuntime().exec("su"), cmds);
    }

    public static void execCommandAsRoot(String cmd) throws Exception {
        execCommand(Runtime.getRuntime().exec("su"), cmd);
    }

    public static void killProcess(String processName) throws Exception {
        String result = execCommand(Runtime.getRuntime().exec("top -n 1"));
        String[] rows = result.split("[\n]+");
        String[] columns = null;
        for (String tempString : rows) {
            if (tempString.indexOf("PID") == -1) {
                tempString = tempString.trim();
                columns = tempString.split("[ ]+");
                if (columns.length == Length_ProcStat) {
                    if (columns[8].contains(processName)) {
                        Log.d(TAG, "processName: " + processName);
                        execCommandAsRoot("kill -9 " + columns[0]);
                    }
                }
                if (columns.length == 10) {
                    if (columns[9].contains(processName)) {
                        Log.d(TAG, "processName: " + processName);
                        execCommandAsRoot("kill -9 " + columns[0]);
                    }
                }
            }
        }
    }

    public static void execCommands(Process proc, String[] cmds)
            throws Exception {
        DataOutputStream os = null;
        BufferedReader br = null;
        try {
            os = new DataOutputStream(proc.getOutputStream());
            for (String cmd : cmds) {
                Log.i(TAG, "Exec shell command: " + cmd);
                os.writeBytes(cmd + "\n");
            }
            os.writeBytes("exit\n");
            os.flush();
            br = new BufferedReader(
                    new InputStreamReader(proc.getErrorStream()));
            String line = null;

            if (proc.waitFor() != 0) {
                StringBuilder sb = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                Log.i(TAG, "InputStream: " + sb.toString());
                throw new Exception(sb.toString());
            }
            Log.i(TAG, "Exec command Success.");
        } finally {
            proc.destroy();
        }
    }

    public static String execCommand(Process proc, String cmd) throws Exception {
        Log.i(TAG, "Exec shell command: " + cmd);
        DataOutputStream os = null;
        BufferedReader brErr = null;
        BufferedReader br = null;
        try {
            os = new DataOutputStream(proc.getOutputStream());
            os.writeBytes(cmd + "\n");
            os.writeBytes("exit\n");
            os.flush();
            brErr = new BufferedReader(new InputStreamReader(
                    proc.getErrorStream()));
            br = new BufferedReader(
                    new InputStreamReader(proc.getInputStream()));
            String line = null;

            if (proc.waitFor() != 0) {
                StringBuilder sbErr = new StringBuilder();
                while ((line = brErr.readLine()) != null) {
                    sbErr.append(line).append("\n");
                }
                throw new Exception(sbErr.toString());
            }

            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } finally {
            proc.destroy();
        }
    }

    public static String execCommand(final Process proc) throws Exception {
        Log.i(TAG, "Exec shell command: ");
        DataOutputStream os = null;
        BufferedReader brErr = null;
        BufferedReader br = null;
        try {
            os = new DataOutputStream(proc.getOutputStream());
            os.flush();

            brErr = new BufferedReader(new InputStreamReader(
                    proc.getErrorStream()));
            br = new BufferedReader(
                    new InputStreamReader(proc.getInputStream()));
            String line = null;

            if (proc.waitFor() != 0) {
                StringBuilder sbErr = new StringBuilder();
                while ((line = brErr.readLine()) != null) {
                    sbErr.append(line).append("\n");
                }
                throw new Exception(sbErr.toString());
            }

            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } finally {
            try {
                proc.destroy();
            } catch (Exception e) {
                Log.e(TAG, "proc.destroy Error: " + e.getMessage());
            }
        }
    }
}
