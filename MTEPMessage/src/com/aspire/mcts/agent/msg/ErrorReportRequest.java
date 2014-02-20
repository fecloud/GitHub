/**
 * @(#) ErrorReportRequest.java Created on 2012-9-13
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.mcts.agent.msg;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import com.aspire.util.ToolException;

/**
 * The class <code>ErrorReportRequest</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class ErrorReportRequest extends APSMessage {

    private int errorCode;

    /**
     * UTF-8,100字符以内
     */
    private String message;

    /**
     * Constructor
     * 
     * @param type
     */
    public ErrorReportRequest() {
        super(APSMessage.ER_REQ);
    }

    public ErrorReportRequest(int errorCode) {
        this();
        this.errorCode = errorCode;
    }

    @Override
    protected void decodeBody(ByteBuffer source) throws ToolException {
        if (source.remaining() < LEN_CODE_LEN) {
            throw new ToolException("Bad message cardFileOpen body length, need [" + LEN_CODE_LEN
                    + "], but remaining [" + source.remaining() + "]");
        }
        this.errorCode = source.getInt();
        if (source.remaining() > 0) {
            final byte[] messages = new byte[source.remaining()];
            source.get(messages);
            try {
                this.message = new String(messages, 0, messages.length, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new ToolException(e);
            }
        }
    }

    @Override
    protected int encodeBody(ByteBuffer destination) throws ToolException {
        int nLen = 0;
        destination.putInt(errorCode);
        nLen += 4;
        if (null != message && !"".equals(message.trim())) {
            try {
                byte [] strbs = message.getBytes("UTF-8");
                destination.put(strbs);
                nLen += strbs.length;
            } catch (UnsupportedEncodingException e) {
                throw new ToolException(e);
            }
        }

        return nLen;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * <p>
     * 告警码定义 The class <code>ErrorCode</code>
     * 
     * @author ouyangfeng
     * @version 1.0
     */
    public static final class ErrorCode {

        /**
         * 移动网络注册失败
         */
        public static final int MN_REG_FAIL = 501;

        /**
         * 数据网络连接断开
         */
        public static final int N_DATA_DISCONN = 502;

        /**
         * 屏幕被关闭
         */
        public static final int SCREEN_CLOSED = 503;

        /**
         * 屏幕被锁屏
         */
        public static final int SCREEN_LOCKED = 504;

        /**
         * SD卡被移除
         */
        public static final int SDCARD_ABSENT = 505;

        /**
         * CPU占用超限值告警
         */
        public static final int CPU_OVERRUN = 506;
        /**
         * 内存使用超限值告警
         */
        public static final int MEM_OVERRUN = 507;
        /**
         * 存储空间超限值告警
         */
        public static final int STORAGE_OVERRUN = 508;

        /**
         * 网络流量超限值告警
         */
        public static final int NW_OVERRUN = 509;

        /**
         * 低电量告警
         */
        public static final int LOWER_POWER = 510;

        /**
         * 弱信号告警
         */
        public static final int LOWER_SIGNAL = 511;

        /**
         * 温度告警
         */
        public static final int HIGH_TEMP = 512;

    }

}
