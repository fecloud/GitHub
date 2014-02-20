/**
 * @(#) DaemonProperty.java Created on 2012-7-20
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.daemon.remote.aidl.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * The class <code>DaemonProperty</code>
 * 
 * @author wuyanlong
 * @version 1.0
 */
@XStreamAlias("DaemonPreference")
public class DaemonPreference implements Parcelable {
    public DaemonPreference() {

    }

    /**
     * 主机地址
     */
    @XStreamAlias("hostname")
    private String hostname;
    /**
     * 端口
     */
    @XStreamAlias("port")
    private String port;
    /**
     * 路径
     */
    @XStreamAlias("path")
    private String path;
    /**
     * 用户名
     */
    @XStreamAlias("userName")
    private String userName;
    /**
     * 密码
     */
    @XStreamAlias("password")
    private String password;

    /**
     * Getter of hostname
     * 
     * @return the hostname
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * Setter of hostname
     * 
     * @param hostname
     *            the ip to set
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * Getter of port
     * 
     * @return the port
     */
    public String getPort() {
        return port;
    }

    /**
     * Setter of port
     * 
     * @param port
     *            the port to set
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * Getter of path
     * 
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Setter of path
     * 
     * @param path
     *            the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Getter of userName
     * 
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter of userName
     * 
     * @param userName
     *            the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter of password
     * 
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter of password
     * 
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    // ftp------------------------
    /**
     * the interval of update
     */
    @XStreamAlias("checkUpdateInterval")
    private long checkUpdateInterval;
    /**
     * the interval oc checking process
     */
    @XStreamAlias("checkProcessInterval")
    private long checkProcessInterval;
    /**
     * the email to receive error msg
     */
    @XStreamAlias("emailRecipients")
    private String emailRecipients;
    /**
     * max size of logger file
     */
    @XStreamAlias("loggerFileSize")
    private int loggerFileSize;

    /**
     * loglevel
     * <p>
     * 'A':ALL,<br>
     * 'V':verbose,<br>
     * 'D':debug,<br>
     * 'I':info,<br>
     * 'W':warning<br>
     * 'E':error<br>
     */
    @XStreamAlias("loglevelChar")
    private char loglevelChar;

    /**
     * Getter of checkUpdateInterval
     * 
     * @return the checkUpdateInterval
     */
    public long getCheckUpdateInterval() {
        return checkUpdateInterval;
    }

    /**
     * Setter of checkUpdateInterval
     * 
     * @param checkUpdateInterval
     *            the checkUpdateInterval to set
     */
    public void setCheckUpdateInterval(long checkUpdateInterval) {
        this.checkUpdateInterval = checkUpdateInterval;
    }

    /**
     * Getter of checkProcessInterval
     * 
     * @return the checkProcessInterval
     */
    public long getCheckProcessInterval() {
        return checkProcessInterval;
    }

    /**
     * Setter of checkProcessInterval
     * 
     * @param checkProcessInterval
     *            the checkProcessInterval to set
     */
    public void setCheckProcessInterval(long checkProcessInterval) {
        this.checkProcessInterval = checkProcessInterval;
    }

    /**
     * Getter of emailRecipients
     * 
     * @return the emailRecipients
     */
    public String getEmailRecipients() {
        return emailRecipients;
    }

    /**
     * Setter of emailRecipients
     * 
     * @param emailRecipients
     *            the emailRecipients to set
     */
    public void setEmailRecipients(String emailRecipients) {
        this.emailRecipients = emailRecipients;
    }

    /**
     * Getter of loggerFileSize
     * 
     * @return the loggerFileSize
     */
    public int getLoggerFileSize() {
        return loggerFileSize;
    }

    /**
     * Setter of loggerFileSize
     * 
     * @param loggerFileSize
     *            the loggerFileSize to set
     */
    public void setLoggerFileSize(int loggerFileSize) {
        this.loggerFileSize = loggerFileSize;
    }

    /**
     * Getter of loglevelChar
     * 
     * @return the loglevelChar
     */
    public char getLoglevelChar() {
        return loglevelChar;
    }

    /**
     * Setter of loglevelChar
     * 
     * @param loglevelChar
     *            the loglevelChar to set
     */
    public void setLoglevelChar(char loglevelChar) {
        this.loglevelChar = loglevelChar;
    }

    @Override
    public String toString() {
        return "hostname:" + hostname + ",port:" + port + ",path:" + path + ",userName:" + userName + ",password:"
                + password + ",checkUpdateInterval:" + checkUpdateInterval + ",checkProcessInterval:"
                + checkProcessInterval + ",emailRecipients:" + emailRecipients + ",loggerFileSize:" + loggerFileSize
                + ",loglevelChar:" + loglevelChar;
    }

    /** --------------------Parcelable code------------------------------- */
    /**
     * CREATOR of Parcelable
     */
    public static final Parcelable.Creator<DaemonPreference> CREATOR = new Parcelable.Creator<DaemonPreference>() {
        /**
         * 
         * (non-Javadoc)
         * 
         * @see android.os.Parcelable.Creator#createFromParcel(android.os.Parcel)
         */
        public DaemonPreference createFromParcel(Parcel arg0) {
            return new DaemonPreference(arg0);
        }

        /**
         * 
         * (non-Javadoc)
         * 
         * @see android.os.Parcelable.Creator#newArray(int)
         */
        public DaemonPreference[] newArray(int arg0) {
            return new DaemonPreference[arg0];
        }

    };

    /**
     * 
     * Constructor of Parcelable
     * 
     * @param in
     */
    private DaemonPreference(Parcel in) {
        readFromParcel(in);
    }

    /**
     * Read reom parcel
     * 
     * @param in
     */
    private void readFromParcel(Parcel in) {
        hostname = in.readString();
        port = in.readString();
        path = in.readString();
        userName = in.readString();
        password = in.readString();
        // ---
        checkUpdateInterval = in.readLong();
        checkProcessInterval = in.readLong();
        emailRecipients = in.readString();
        loggerFileSize = in.readInt();
        char[] chars = new char[1];
        in.readCharArray(chars);
        if (chars != null && chars.length != 0)
            loglevelChar = chars[0];
    }

    /**
     * (non-Javadoc)
     * 
     * @see android.os.Parcelable#describeContents()
     */
    public int describeContents() {
        return 0;
    }

    /**
     * (non-Javadoc)
     * 
     * @see android.os.Parcelable#writeToParcel(android.os.Parcel, int)
     */
    public void writeToParcel(Parcel arg0, int arg1) {
        arg0.writeString(hostname);
        arg0.writeString(port);
        arg0.writeString(path);
        arg0.writeString(userName);
        arg0.writeString(password);
        // ---
        arg0.writeLong(checkUpdateInterval);
        arg0.writeLong(checkProcessInterval);
        arg0.writeString(emailRecipients);
        arg0.writeInt(loggerFileSize);
        arg0.writeCharArray(new char[] { loglevelChar });
    }

}
