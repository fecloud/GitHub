/**
 * @(#) ClientStoreTask.java Created on 2012-10-25
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server.task;

import org.apache.log4j.Logger;

import com.inspurworld.server.restore.RestoreCenter;

/**
 * The class <code>ClientStoreTask</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class ClientReStoreTask implements Task {

    private Logger logger = Logger.getLogger(getClass());

    private String filename;

    private RestoreCenter restoreCenter;

    private short taskState;

    public ClientReStoreTask(String filename) {
        super();
        this.filename = filename;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.inspurworld.server.task.Task#taskState()
     */
    @Override
    public int taskState() {
        logger.debug("taskState");
        return taskState;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.inspurworld.server.task.Task#doTask()
     */
    @Override
    public boolean doTask() {
        logger.debug("doTask");
        restoreCenter = new RestoreCenter(filename);
        return restoreCenter.doRestore();
    }

}
