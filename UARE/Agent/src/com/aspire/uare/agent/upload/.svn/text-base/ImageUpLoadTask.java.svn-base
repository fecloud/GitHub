/**
 * @(#) ImageUpLoadTask.java Created on 2012-10-23
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.uare.agent.upload;

import java.io.File;
import java.io.IOException;

import android.util.Log;

import com.aspire.uare.agent.util.FileUtil;
import com.inspurworld.msg.APSMessage;
import com.inspurworld.msg.common.ImageUpLoadRequest;
import com.inspurworld.msg.data.ImageMessage;

/**
 * The class <code>ImageUpLoadTask</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class ImageUpLoadTask implements Task {

    private static final String TAG = "ImageUpLoadTask";

    private String filename;

    private short taskState;

    public ImageUpLoadTask(String filename) {
        super();
        this.filename = filename;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.inspurworld.agent.upload.Task#doTask()
     */
    @Override
    public APSMessage doTask() {
        Log.d(TAG, "doTask");
        final ImageUpLoadRequest request = new ImageUpLoadRequest();
        final File file = new File(filename);
        request.setName(file.getName());
        final ImageMessage message = new ImageMessage();
        message.setCurrentPackageSequenceNumber(1);
        message.setTotalPackageCount(1);
        try {
            message.setImageData(FileUtil.readFile(filename));
            request.setImageMessage(message);
        } catch (IOException e) {
            Log.e(TAG, "readFile path:" + filename + "error", e);
        }
        return request;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.inspurworld.agent.upload.Task#taskState()
     */
    @Override
    public int taskState() {
        return taskState;
    }

}
