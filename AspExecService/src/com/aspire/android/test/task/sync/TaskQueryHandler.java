package com.aspire.android.test.task.sync;

/**
 * @(#) TaskQueryResponseParser.java Created on 2012-4-13
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.aspire.android.common.exception.MException;
import com.aspire.android.test.execute.NameConstants;
import com.aspire.android.test.log.RunLogger;
import com.aspire.android.test.task.entity.Task;
import com.aspire.android.test.task.service.ITaskService;
import com.aspire.mgt.ats.tm.sync.AbstractMessageHandler;
import com.aspire.mgt.ats.tm.sync.task.entity.TaskInfo;
import com.aspire.mgt.ats.tm.sync.task.entity.TaskQueryResponse;
import com.aspire.mgt.ats.tm.sync.util.SerializeUtil;

/**
 * The class <code>TaskQueryResponseParser</code>
 * 
 * @author gouanming
 * @version 1.0
 */
public class TaskQueryHandler extends AbstractMessageHandler<TaskQueryResponse> {

    private RunLogger runLogger = RunLogger.getInstance();

    private ITaskService service;

    protected String deviceType;

    /**
     * 
     * Constructor
     * 
     * @param service
     */
    public TaskQueryHandler(ITaskService service) {
        super();
        this.service = service;
    }

    /**
     * @return the deviceType
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * @param deviceType
     *            the deviceType to set
     */
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.mgt.tm.sync.AbstractMessageHandler#handleMessage(com.aspire.mgt.tm.sync.AbstractMessage)
     */
    @Override
    public void handle(TaskQueryResponse response) {
    }

    @Override
    public void handle(TaskQueryResponse response, HashMap<String, Object> map) {
        List<Task> listTask = new ArrayList<Task>();
        if ("0000".equals(response.getStatus())) {
            SerializeUtil serializeUtil = new SerializeUtil();
            String msg = serializeUtil.serialize(response); 
            runLogger.debug(getClass(),"taskinfo is : " + msg);
            if (response.getTaskInfos() != null && response.getTaskInfos().size() > 0) {
                for (TaskInfo taskInfo : response.getTaskInfos()) {
                    try {
                        Task mTask = service.mergeTaskOnSync(deviceType, taskInfo, map);
                        listTask.add(mTask);
                    } catch (Exception e) {
                        runLogger.error(getClass(), "Failed while insert task to db, and errmessage is " + e.getMessage());
                    }
                }
            }
        } else {
            runLogger.error(getClass(), "the response of download task is : status = " + response.getStatus()
                    + "; message = " + response.getMessage() + "; tasklist's size = " + listTask.size());
        }

        if (listTask.size() > 0) {

            Object[] mObject = new Object[listTask.size()];
            for (int i = 0; i < listTask.size(); i++) {
                Task mTask = listTask.get(i);
                mObject[i] = mTask.getId();
            }

            List<Task> listTasks = null;
            try {
                listTasks = service.getNotTask(mObject);
            } catch (MException e) {
                runLogger.error(getClass(), "Failed while get task in db, and errmessage is " + e.getMessage());
            }
            for (Task task : listTasks) {
                try {
                    map.put(NameConstants.TASK_UPDATE_STATUS, true);
                    task.setStatus(0);
                    service.updataTask(task);
                } catch (MException e) {
                    runLogger.error(getClass(), "Failed while update task to db, and errmessage is " + e.getMessage());
                }
            }
        }
    }

}
