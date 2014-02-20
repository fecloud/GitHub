/**
 * @(#) ImageUpLoadMessageExec.java Created on 2012-10-20
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server.message;

import java.io.File;

import org.apache.log4j.Logger;

import com.inspurworld.msg.APSMessage;
import com.inspurworld.msg.common.ImageUpLoadRequest;
import com.inspurworld.msg.common.ImageUpLoadRespone;
import com.inspurworld.server.message.listener.IMessageListener;
import com.inspurworld.server.util.FileUtil;

/**
 * The class <code>ImageUpLoadMessageExec</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class ImageUpLoadMessageExec implements IMessageListener {

    private Logger logger = Logger.getLogger(getClass());

    private ImageUpLoadRespone respone;

    private ImageUpLoadRequest request;

    private MessaExceFinishListener finishListener;

    public ImageUpLoadMessageExec(MessaExceFinishListener finishListener) {
        super();
        this.finishListener = finishListener;
    }

    public MessaExceFinishListener getFinishListener() {
        return finishListener;
    }

    public void setFinishListener(MessaExceFinishListener finishListener) {
        this.finishListener = finishListener;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.inspurworld.server.message.listener.IMessageListener#onMessage(com.inspurworld.msg.APSMessage)
     */
    @Override
    public boolean onMessage(APSMessage message) {
        logger.debug("onMessage...");
        switch (message.getType()) {
        case APSMessage.IU_REQ:
            request = (ImageUpLoadRequest) message;
            executeMessage();
            return false;
        }

        return true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.inspurworld.server.message.listener.IMessageListener#receiveMessage()
     */
    @Override
    public APSMessage receiveMessage() {
        return respone;
    }

    /**
     * 解开消息并处理
     */
    public void executeMessage() {
        final String name = request.getName();
        logger.info("executeMessage... name:" + name);
        final byte[] data = request.getImageMessage().getImageData();
        try {
            // 写入文件
            final String filename = System.getProperty("user.dir") + File.separator + "upload" + File.separator + name;
            FileUtil.writeFile(filename, data);
            messageExecFinish(filename);
        } catch (Exception e) {
            logger.debug("write file error", e);
        }
        this.respone = new ImageUpLoadRespone();
    }

    public void messageExecFinish(String path) {
        logger.debug("messageExecFinish...");
        if (null != this.finishListener)
            this.finishListener.onMessageExecFinish(path);// 通知
    }

}
