/**
 * @(#) CmdExecute.java Created on 2012-10-30
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * The class <code>CmdExecute</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class CmdExecute {

    /**
     * Execute a command
     * 
     * @param cmd
     * @return
     * @throws IOException
     */
    public static CmdResponse exec(String cmd) throws IOException {
        ReadInputStream msgReadding = null;
        ReadInputStream errorMsgReadding = null;
        CmdResponse respone = new CmdResponse();
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(cmd);
            // 获取进程的错误流
            errorMsgReadding = new ReadInputStream(process.getErrorStream());
            errorMsgReadding.start();
            // 获取正常流信息
            msgReadding = new ReadInputStream(process.getInputStream());
            msgReadding.start();
            // respone
            respone.setRunSuccess(process.waitFor() == 0);
            respone.setMsg(msgReadding.getMsg());
            respone.setErrorMsg(errorMsgReadding.getMsg());
        } catch (Exception e) {
            respone.setRunSuccess(false);
            respone.setErrorMsg(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (errorMsgReadding != null)
                    errorMsgReadding.toStop();
                if (msgReadding != null)
                    msgReadding.toStop();
                process.destroy();
            } catch (Exception e) {
            }
        }
        return respone;
    }

    /**
     * Execute a command
     * 
     * @param process
     * @param command
     * @return
     */
    public static CmdResponse exec(Process process, String command) {
        ReadInputStream msgReadding = null;
        ReadInputStream errorMsgReadding = null;
        CmdResponse respone = new CmdResponse();
        if (process == null) {
            respone.setRunSuccess(false);
            return respone;
        }
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(process.getOutputStream());
            dos.writeBytes(command + "\n");
            dos.writeBytes("exit\n");
            dos.flush();
            // 获取进程的错误流
            errorMsgReadding = new ReadInputStream(process.getErrorStream());
            errorMsgReadding.start();
            // 获取正常流信息
            msgReadding = new ReadInputStream(process.getInputStream());
            msgReadding.start();
            // respone
            respone.setRunSuccess(process.waitFor() == 0);
            respone.setMsg(msgReadding.getMsg());
            respone.setErrorMsg(errorMsgReadding.getMsg());
        } catch (Exception e) {
            respone.setRunSuccess(false);
            respone.setErrorMsg(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (dos != null)
                    dos.close();
                if (errorMsgReadding != null)
                    errorMsgReadding.toStop();
                if (msgReadding != null)
                    msgReadding.toStop();
                process.destroy();
            } catch (Exception e) {
            }
        }
        return respone;
    }

    /**
     * 
     * The class <code>ReadErrorInput</code>
     * 
     * @author wuyanlong
     * @version 1.0
     */
    static class ReadInputStream extends Thread {
        /**
         * Running
         */
        private boolean running = true;
        /**
         * Buffer of reading string
         */
        private StringBuffer readBuffer = new StringBuffer();
        /**
         * BufferedReader
         */
        private BufferedReader readBr;
        private boolean isReading = false;

        /**
         * 
         * Constructor
         * 
         * @param is
         */
        public ReadInputStream(InputStream is) {
            if (is != null)
                readBr = new BufferedReader(new InputStreamReader(is));
        }

        /**
         * (non-Javadoc)
         * 
         * @see java.lang.Thread#run()
         */
        @Override
        public void run() {
            try {
                while (running) {
                    readding();
                    sleep(250);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (readBr != null)
                    try {
                        readBr.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
            super.run();

        }

        /**
         * Reading
         * 
         * @throws IOException
         */
        private void readding() throws IOException {
            synchronized (this) {
                if (isReading)
                    return;
                isReading = true;
            }
            if (readBr == null)
                return;
            while (readBr.ready()) {
                readBuffer.append(readBr.readLine());
                readBuffer.append("\n");
            }
            synchronized (this) {
                isReading = false;
            }
        }

        /**
         * To stop thread.
         */
        public void toStop() {
            running = false;

        }

        /**
         * 
         * @return
         */
        public String getMsg() {
            try {
                readding();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return readBuffer.toString();
        }
    }

    /**
     * 
     * The class <code>CmdRespone</code>
     * 
     * Return response from execute a command
     * 
     * @author wuyanlong
     * @version 1.0
     */
    public static class CmdResponse {

        /**
         * Succes of Running
         */
        private boolean isRunSuccess;
        /**
         * Message of success or faild
         */
        private String msg;
        /**
         * Error message
         */
        private String errorMsg;

        /**
         * Getter of isRunSuccess cmdexecute进程运行成功
         * 
         * @return the isRunSuccess
         */
        public boolean isRunSuccess() {
            return isRunSuccess;
        }

        /**
         * Setter of isRunSuccess
         * 
         * @param isRunSuccess
         *            the isRunSuccess to set
         */
        public void setRunSuccess(boolean isRunSuccess) {
            this.isRunSuccess = isRunSuccess;
        }

        /**
         * Getter of msg
         * 
         * @return the msg
         */
        public String getMsg() {
            return msg;
        }

        /**
         * Setter of msg
         * 
         * @param msg
         *            the msg to set
         */
        public void setMsg(String msg) {
            this.msg = msg;
        }

        /**
         * Getter of errorMsg
         * 
         * @return the errorMsg
         */
        public String getErrorMsg() {
            return errorMsg;
        }

        /**
         * Setter of errorMsg
         * 
         * @param errorMsg
         *            the errorMsg to set
         */
        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }

    }

}
