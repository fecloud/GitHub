/**
 * @(#) ImageUpLoad.java Created on 2012-10-20
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.msg.common;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import com.inspurworld.msg.APSMessage;
import com.inspurworld.msg.data.ImageMessage;
import com.inspurworld.msg.exception.ToolException;

/**
 * The class <code>ImageUpLoad</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class ImageUpLoadRequest extends APSMessage {

    private String name;

    /**
     * the image message
     */
    private ImageMessage imageMessage;

    public ImageUpLoadRequest() {
        super(APSMessage.IU_REQ);
    }

    @Override
    protected void decodeBody(ByteBuffer source) throws ToolException {

        if (source.remaining() > 4) {
            final int nameBsLen = source.getInt();
            if (source.remaining() >= nameBsLen) {
                final byte[] nameBs = new byte[nameBsLen];
                source.get(nameBs);
                try {
                    this.name = new String(nameBs, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }

        if (source.remaining() > 4) {
            final ImageMessage imageMessage = new ImageMessage();
            imageMessage.setTotalPackageCount(source.getInt());
            if (source.remaining() > 4) {
                imageMessage.setCurrentPackageSequenceNumber(source.getInt());
                if (source.remaining() > 1) {
                    int bodylen = source.remaining();
                    byte[] srtbyte = new byte[bodylen];
                    source.get(srtbyte);
                    imageMessage.setImageData(srtbyte);
                    setImageMessage(imageMessage);
                }
            }
        }
    }

    @Override
    protected int encodeBody(ByteBuffer destination) throws ToolException {
        // 正常返回0
        int nLen = 0;
        if (null != name && !"".equals(name)) {
            try {
                final byte[] nameBs = name.getBytes("UTF-8");
                final int nameBsLen = nameBs.length;
                destination.putInt(nameBsLen);
                nLen += 4;
                destination.put(nameBs);
                nLen += nameBsLen;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (null != imageMessage) {
            destination.putInt(imageMessage.getTotalPackageCount());
            nLen += 4;
            destination.putInt(imageMessage.getCurrentPackageSequenceNumber());
            nLen += 4;
            destination.put(imageMessage.getImageData());
            nLen += imageMessage.getImageData().length;
        }
        return nLen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageMessage getImageMessage() {
        return imageMessage;
    }

    public void setImageMessage(ImageMessage imageMessage) {
        this.imageMessage = imageMessage;
    }

}
