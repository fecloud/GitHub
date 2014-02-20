/**
 * @(#) ImageAttachment.java Created on 2012-6-1
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.log.attachment;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * The class <code>ImageAttachment</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class ImageAttachment extends BinaryAttachment {
    /*
     * save image file path
     */
    private String filepath;

    public ImageAttachment(byte[] bytes, String filepath) {
        super(bytes);
        this.filepath = filepath;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.log.attachment.BinaryAttachment#saveAttachment()
     */
    @Override
    public String saveAttachment() throws Exception {
        final StringBuffer buffer = new StringBuffer();
        saveImage();
        buffer.append("save image attachment path:" + filepath);
        return buffer.toString();
    }

    /**
     * save image on disk filesystem
     * 
     * @throws IOException
     */
    public void saveImage() throws IOException {
        if (null != filepath) {
            final FileOutputStream out = new FileOutputStream(filepath);
            out.write(getBytes());
            out.flush();
            out.close();
        } else {
            throw new IllegalArgumentException("the save file path is null");
        }
    }

}
