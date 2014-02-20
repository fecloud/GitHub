/**
 * @(#) MobileMsgBase.java Created on 2009-2-13
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.msg;

import com.aspire.Constants;
import com.aspire.msg.FieldMsgBase;
import com.aspire.util.ByteArray;
import com.aspire.util.ToolException;

/**
 * The class <code>MobileMsgBase</code>
 *
 * @author xuyong
 * @version 1.0
 */
public class MobileMsgBase extends FieldMsgBase {
    
    public static final int LEN_BODY_LEN = 4;
    public static final int LEN_SEQ = 4;
    public static final int LEN_TYPE = 4;
    public static final int LEN_HEADER = LEN_BODY_LEN + LEN_SEQ + LEN_TYPE;

    /**
     * The message sequence number
     */
    protected int nSeqNum = 1;
    
    /**
     * The message type
     */
    protected int nMsgType = -1;
    
    /**
     * Current sequence number
     */
    protected static int nCurSeqNum = 1;
    
    /**
     * Constructor
     * @param nMsgType Message type
     * @param sMsgName Message name
     */
    public MobileMsgBase(int nMsgType, String sMsgName) {
        super(sMsgName);
        this.nMsgType = nMsgType;
        nSeqNum = nCurSeqNum++;
    }

    /**
     * Sets message sequence number
     * @param nSeqNum The message sequence number
     */
    public void setSequence(int nSeqNum) {
        this.nSeqNum = nSeqNum;
    }

    /**
     * Gets sequence number
     * @return Returns message sequence number
     */
    public int getSequence() {
        return nSeqNum;
    }

    /**
     * Gets the message type
     * @return Returns the message type
     */
    public int getMsgType() {
        return nMsgType;
    }

    /**
     * 对消息进行解码，将消息序列解码成消息对象
     * 
     * @param byMsg 要解码的消息
     * @param start 消息的起始索引
     * @return 解码成功后，返回从消息中读取的总长度
     * @throws 当发生错误时，抛出ToolException异常
     */
    public int decode(byte[] byMsg, int start, int length) throws ToolException {

        int offset = start;

        // Decodes sequence number
        nSeqNum = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;
        
        // Decodes message type
        nMsgType = ByteArray.bytesToInt(byMsg, offset);
        offset += 4;

        return offset - start;
    }

    /**
     * 对消息进行编码
     * 
     * @param baMsg 用来保存编码后的消息数组
     * @return 编码成功后，返回添加到消息中总长度
     * @throws 当发生错误时，抛出ToolException异常
     */
    public int encode(ByteArray baMsg) throws ToolException {

        int nLen = baMsg.length();

        // encode sequence number
        baMsg.append(nSeqNum);

        // encode message type
        baMsg.append(nMsgType);

        return baMsg.length() - nLen;
    }

    /**
     * 将本消息格式化成可读形式
     * 
     * @param buf 用来保存格式化后的消息信息
     * @param sPrefix 格式化信息的前缀
     * @return 格式化成功后，返回添加到buf中的总长度
     * @throws 当发生错误时，抛出ToolException异常
     */
    public int format(StringBuffer buf, String sPrefix) throws ToolException {

        int nLen = buf.length();

        // Format sequence number
        buf.append(sPrefix).
            append("Sequence = ").
            append(nSeqNum).
            append(Constants.LINE_SEPARATOR);

        // Format message type
        buf.append(sPrefix).
            append("MessageType = ").
            append(nMsgType).
            append(Constants.LINE_SEPARATOR);

        return buf.length() - nLen;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        
        if (this == obj) return true;

        if (obj == null || !(obj instanceof MobileMsgBase)) {
            return false;
        }
        
        MobileMsgBase oMsgObj = (MobileMsgBase)obj;

        return nSeqNum == oMsgObj.nSeqNum &&
            nMsgType == oMsgObj.nMsgType;
    }
    
    /*
     * (non-Javadoc)
     * @see com.aspire.msg.MsgField#isPresent(java.lang.String)
     */
    public boolean isPresent(String field) {
        return buildFields.contains(field);
    }
}
