/**
 * @(#) ExecCommand.java Created on 2012-5-7
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

/**
 * The class <code>ExecCommand</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class ExecCommand {

    private static Logger logger = Logger.getLogger("ExecCommand");

    public static void execCommand(String[] cmds) throws Exception {
        execCommands(Runtime.getRuntime().exec("cmd.exe") , cmds);
    }

    public static void execCommand(String cmd) throws Exception {
        execCommand(Runtime.getRuntime().exec("cmd.exe") , cmd);
    }

    public static void execCommands(Process proc, String[] cmds) throws Exception {
        DataOutputStream os = null;
        BufferedReader br = null;
        try {
            os = new DataOutputStream(proc.getOutputStream());
            for (String cmd : cmds) {
                logger.debug("Exec shell command: " + cmd);
                os.writeBytes(cmd + "\n");
            }
            os.writeBytes("exit\n");
            os.flush();
            br = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            String line = null;

            if (proc.waitFor() != 0) {
                StringBuilder sb = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                logger.debug("InputStream: " + sb.toString());
                throw new Exception(sb.toString());
            }
            logger.debug("Exec command Success.");
        } finally {
            proc.destroy();
        }
    }

    public static String execCommand(Process proc, String cmd) throws Exception {
        logger.debug("Exec shell command: " + cmd);
        DataOutputStream os = null;
        BufferedReader brErr = null;
        BufferedReader br = null;
        try {
            os = new DataOutputStream(proc.getOutputStream());
            os.writeBytes(cmd + "\r\n");
            os.writeBytes("exit \r\n");
            os.flush();
            brErr = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
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
        logger.debug("Exec shell command: ");
        DataOutputStream os = null;
        BufferedReader brErr = null;
        BufferedReader br = null;
        try {
            os = new DataOutputStream(proc.getOutputStream());
            os.flush();

            brErr = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
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
                logger.debug("proc.destroy Error: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        execCommand("ipconfig");
//        System.out.println(execCommand(Runtime.getRuntime().exec("java /c dir")));;
    }
}
