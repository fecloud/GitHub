/**
 * @(#) FileConfigMonitor.java Created on 2012-8-14
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.braver.ip.config.monitor;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * The class <code>FileConfigMonitor</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class FileConfigMonitor extends Monitor {

    private String filePath;

    private long lastModified;

    private FileChangThread thread;

    public FileConfigMonitor(String filePath) {
        super();
        this.filePath = filePath;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.braver.ip.config.monitor.ConfigMonitor#startMonitor()
     */
    @Override
    public void startMonitor() {
        logger.debug("FileConfigMonitor startMonitor");
        thread = new FileChangThread(this);
        thread.setFlag(true);
        thread.start();
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.braver.ip.config.monitor.ConfigMonitor#stopMonitor()
     */
    @Override
    public void stopMonitor() {
        if (null != thread)
            thread.setFlag(false);

    }

    private final class FileChangThread extends Thread {

        private Logger logger = Logger.getLogger(FileConfigMonitor.class);

        private boolean flag;

        private long modifiedTime;

        private FileConfigMonitor monitor;

        public FileChangThread(FileConfigMonitor monitor) {
            super();
            this.monitor = monitor;
        }

        @Override
        public void run() {
            logger.debug("FileChangThread run...");
            while (flag) {
                try {
                    modifiedTime = getFileMofifyTime();
                    if (lastModified < modifiedTime) {
                        monitor.onChange();
                        lastModified = modifiedTime;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }

        public long getFileMofifyTime() throws IOException {
            final File file = new File(filePath);
            if (!file.exists())
                throw new IOException("the monitor file is not found! " + filePath);
            return file.lastModified();
        }

        public synchronized void setFlag(boolean flag) {
            this.flag = flag;
        }

    }
}
