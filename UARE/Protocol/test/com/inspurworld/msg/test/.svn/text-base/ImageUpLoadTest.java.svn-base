/**
 * @(#) ImageUpLoadTest.java Created on 2012-10-22
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.msg.test;

import java.nio.ByteBuffer;

import com.inspurworld.msg.APSMessage;
import com.inspurworld.msg.codec.APSMessageCodec;
import com.inspurworld.msg.common.ImageUpLoadRequest;
import com.inspurworld.msg.data.ImageMessage;
import com.inspurworld.msg.exception.ToolException;

/**
 * The class <code>ImageUpLoadTest</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class ImageUpLoadTest {

    /**
     * @param args
     * @throws ToolException
     */
    public static void main(String[] args) throws ToolException {
        final ImageUpLoadRequest request = new ImageUpLoadRequest();
        final ImageMessage image = new ImageMessage();
        image.setCurrentPackageSequenceNumber(1);
        image.setTotalPackageCount(1);
        image.setImageData(new byte[] { 1, 2, 3 });
        request.setName("name");
        request.setImageMessage(image);
        APSMessageCodec codec = new APSMessageCodec();
        final ByteBuffer buffer = codec.encode(request);
        System.out.println(buffer.limit());
        final APSMessage message = codec.decode(buffer);
        System.out.println(message);
    }

}
