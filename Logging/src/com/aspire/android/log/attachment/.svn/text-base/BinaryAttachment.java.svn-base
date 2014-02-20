/**
 * @(#) BinaryAttachment.java Created on 2012-6-1
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.log.attachment;

/**
 * The class <code>BinaryAttachment</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public abstract class BinaryAttachment implements LogAttachment {

    private byte[] bytes;

    public BinaryAttachment() {
    }

    public BinaryAttachment(byte[] bytes) {
        this.bytes = bytes;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.log.attachment.LogAttachment#saveAttachment()
     */
    @Override
    public String saveAttachment() throws Exception {
        return "";
    }

    /**
     * Setter of bytes
     * 
     * @param bytes
     *            the bytes to set
     */
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    /**
     * Getter of bytes
     * 
     * @return the bytes
     */
    public byte[] getBytes() {
        return bytes;
    }

}
