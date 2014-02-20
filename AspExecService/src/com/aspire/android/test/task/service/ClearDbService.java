/**
 * @(#) ClearDbService.java Created on 2012-8-30
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.task.service;

import java.util.Date;
import java.util.List;

import com.aspire.android.common.exception.MException;
import com.aspire.android.test.task.dao.ITaskBatchDao;
import com.aspire.android.test.task.dao.ITaskDao;
import com.aspire.android.test.task.dao.ITaskItemBatchDao;
import com.aspire.android.test.task.dao.ITaskItemDao;
import com.aspire.android.test.task.dao.IUploadDao;
import com.aspire.android.test.task.entity.Task;
import com.aspire.android.test.task.entity.TaskBatch;
import com.aspire.android.test.task.entity.TaskItem;
import com.aspire.android.test.task.entity.TaskItemBatch;
import com.aspire.android.test.task.entity.Upload;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * The class <code>ClearDbService</code>
 * 
 * @author gouanming
 * @version 1.0
 */
@Singleton
public class ClearDbService implements IClearDbService {

    @Inject
    public ITaskDao taskDao;
    @Inject
    public ITaskItemDao taskItemDao;
    @Inject
    public ITaskItemBatchDao mITaskItemBatchDao;
    @Inject
    public ITaskBatchDao mITaskBatchDao;
    @Inject
    public IUploadDao mIUploadDao;

    @Inject
    public ClearDbService() {
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.IClearDbService#TaskCear(int)
     */
    public void TaskCear(long dayNum) throws MException {
        // TODO Auto-generated method stub

        if (dayNum > 0) {
            List<Task> taskAll = taskDao.findAll();
            Date myDate = new Date();
            for (Task task : taskAll) {
                if ((myDate.getTime() - (dayNum * 24 * 60 * 60 * 1000)) > task.getLastUpdateDate().getTime()) {
                    List<TaskItem> allTaskItem = taskItemDao.getTaskItem(task.getId());

                    for (TaskItem taskItem : allTaskItem) {
                        taskItemDao.detele(taskItem);
                    }
                    taskDao.detele(task);
                }
            }
        }

    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.IClearDbService#TestResultCear(int)
     */
    public void TestResultCear(long dayNum) throws MException {
        // TODO Auto-generated method stub

        if (dayNum > 0) {
            List<TaskBatch> TaskBatchAll = mITaskBatchDao.findAll();
            Date myDate = new Date();
            for (TaskBatch taskBatch : TaskBatchAll) {
                if ((myDate.getTime() - (dayNum * 24 * 60 * 60 * 1000)) > taskBatch.getStartTime().getTime()) {
                    List<TaskItemBatch> listTaskItemBatch = mITaskItemBatchDao.findTaskItemBatch(taskBatch.getId());
                    for (TaskItemBatch taskItemBatch : listTaskItemBatch) {
                        mITaskItemBatchDao.detele(taskItemBatch);
                    }
                    mITaskBatchDao.detele(taskBatch);
                }
            }
        }
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.IClearDbService#upResultCear(int)
     */
    public void upResultCear(long dayNum) throws MException {
        // TODO Auto-generated method stub
        if (dayNum > 0) {
            List<Upload> uploadAll = mIUploadDao.findAll();
            Date myDate = new Date();
            for (Upload upload : uploadAll) {
                if ((myDate.getTime() - (dayNum * 24 * 60 * 60 * 1000)) > upload.getUpDate().getTime()) {
                    mIUploadDao.detele(upload);
                }
            }
        }
    }

}
