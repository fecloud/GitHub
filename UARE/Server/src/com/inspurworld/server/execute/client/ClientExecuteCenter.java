/**
 * @(#) ClientExecuteCenter.java Created on 2012-10-19
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server.execute.client;

import java.net.Socket;

import org.apache.log4j.Logger;

import com.inspurworld.server.OnClinentConn;
import com.inspurworld.server.message.ImageUpLoadMessageExec;
import com.inspurworld.server.message.MessaExceFinishListener;
import com.inspurworld.server.restore.ClientRestoreService;

/**
 * The class <code>ClientExecuteCenter</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class ClientExecuteCenter implements OnClinentConn, MessaExceFinishListener {

    private Logger logger = Logger.getLogger(getClass());

    private ClientRestoreService clientRestoreService;

    public ClientExecuteCenter() {
    }

    public ClientExecuteCenter(ClientRestoreService clientRestoreService) {
        super();
        this.clientRestoreService = clientRestoreService;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.inspurworld.server.OnClinentConn#clientConn(java.net.Socket)
     */
    @Override
    public void clientConn(Socket socket) {
        logger.debug("clientConn");
        final ClientExecuteThread executeThread = new ClientExecuteThread(socket);
        final ImageUpLoadMessageExec messageExec = new ImageUpLoadMessageExec(this);
        executeThread.addMessageListener(messageExec);
        executeThread.start();
        logger.debug("start a thread execute...");
    }

    public ClientRestoreService getClientRestoreService() {
        return clientRestoreService;
    }

    public void setClientRestoreService(ClientRestoreService clientRestoreService) {
        this.clientRestoreService = clientRestoreService;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.inspurworld.server.message.MessaExceFinishListener#onMessageExecFinish(java.lang.String)
     */
    @Override
    public void onMessageExecFinish(String path) {
        logger.info("a client thread finish a message , add to restore service");
        if (null != clientRestoreService) {
            if (!clientRestoreService.isAlive()) {
                clientRestoreService.start();
            }
            clientRestoreService.uploadImageMather(path);
        }

    }
}
