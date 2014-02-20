/**
 * @(#) FileLogAppender.java Created on 2007-7-25
 * 
 * Copyright (c) 2007 Aspire. All Rights Reserved
 */
package com.aspire.android.log.appender;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.aspire.android.log.LogEvent;
import com.aspire.android.log.LogLevel;
import com.aspire.android.log.layout.LogLayout;

/**
 * The class <code>FileLogAppender</code> is used to write log information to a file
 * 
 * @version 1.0
 * @author xuyong
 */
public class FileLogAppender extends LogAppender {

    /*
     * The name of the file used to store the log information
     */
    protected String fileName;

    /*
     * The file writer
     */
    protected BufferedWriter bufWriter = null;

    /*
     * The max file length
     */
    protected long maxLogSize = 5 * 1024 * 1024;

    /*
     * 当前bak的日志数量
     */
    protected static int baklogNumber = 0;

    /*
     * 是否追加
     */
    protected boolean append;

    /**
     * Constructs a new FileLogAppender with <code>null</code> as its LogLayout, and LogLevel.INFO as its log level
     * filter
     * 
     * @param fileName
     *            The name of the file used to store the log information
     */
    public FileLogAppender() {
    }

    public FileLogAppender(String fileName) throws RuntimeException {
        super();
        this.fileName = fileName;
        openFile();
    }

    /**
     * Constructs a new FileLogAppender with the specified log layout as its LogLayout and LogLevel.INFO as its log
     * level filter
     * 
     * @param fileName
     *            The name of the file used to store the log information
     * @param layout
     *            The special log layout. The log layout is saved for later retrieval by the {@link #getLayout()}
     *            method.
     */
    public FileLogAppender(String fileName, LogLayout layout) throws Exception {
        super(layout);
        this.fileName = fileName;
        openFile();
    }

    /**
     * Constructs a new FileLogAppender with the specified log layout as its LogLayout and the specified level filter as
     * its log level filter
     * 
     * @param fileName
     *            The name of the file used to store the log information
     * @param layout
     *            The special log layout. The log layout is saved for later retrieval by the {@link #getLayout()}
     *            method.
     * @param levelFilter
     *            The special log filter. The log level filter is saved for later retrieval by the
     *            {@link #getLevelFilter()} method.
     */
    public FileLogAppender(String fileName, LogLayout layout, LogLevel levelFilter) throws Exception {
        super(layout, levelFilter);
        this.fileName = fileName;
        openFile();
    }

    /**
     * Sets log file
     * 
     * @param fileName
     *            The name of the file used to store the log information
     * @throws Throws
     *             Exception if any error occurs
     */
    public synchronized void setFileProperties(String fileName, boolean append) throws RuntimeException {
        this.fileName = fileName;
        this.append = append;
        openFile();
    }

    /**
     * set log file max size
     * 
     * @param size
     */
    public synchronized void setFileMaxLogSize(int size) {
        if (size > 0) {
            this.maxLogSize = size * 1024;
        }
    }

    /**
     * Close the file/socket, etc has being opened
     * 
     * @throws Exception
     */
    @Override
    public synchronized void close() throws Exception {
        if (bufWriter != null) {
            bufWriter.flush();
            bufWriter.close();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.log.LogAppender#write(com.aspire.log.LogEvent)
     */
    @Override
    protected void write(LogEvent evt) throws Exception {
        try {
            if (layout != null && evt.formative) {
                bufWriter.write(layout.format(evt));
            } else {
                bufWriter.write(evt.message);
            }
            bufWriter.flush();
            // 更新文件名称；当日志文件超过大小时
            updateLogFile();
        } catch (IOException ie) {
            throw new Exception("Write log error: " + ie.getMessage());
        }
    }

    /**
     * Create and open the file to write
     * 
     * @throws Throws
     *             Exception if any error occurs
     */
    protected void openFile() throws RuntimeException {

        try {
            createFile(fileName);
            FileWriter fw = new FileWriter(fileName, append);

            if (bufWriter != null) {
                bufWriter.close();
            }
            bufWriter = new BufferedWriter(fw);
        } catch (IOException ie) {
            throw new RuntimeException("Open log file error: " + ie.getMessage());
        }
    }

    /**
     * 检查日志文件大小，当日志文件超过大小时，备份原来的文件， 并重新打开日志文件
     */
    protected synchronized void updateLogFile() throws Exception {
        try {
            File logFile = new File(fileName);
            long fileLen = logFile.length();

            if (fileLen >= maxLogSize) {
                baklogNumber++;

                File bakLogName = new File(fileName + "_bak" + baklogNumber);
                bufWriter.flush();
                bufWriter.close();
                logFile.renameTo(bakLogName);

                // 重新打开文件
                bufWriter = null;
                openFile();
            }
        } catch (IOException ex) {
            throw new Exception("Update log file name error: " + ex.getMessage());
        }
    }

    private void createFile(String filename) throws IOException {
        final File file = new File(filename);
        if (!file.exists()) {
            final String parentPath = file.getParent();
            final File parent = new File(parentPath);
            if (!parent.exists()) {
                parent.mkdirs();
            } else {
                file.createNewFile();
            }
        }
    }
}
