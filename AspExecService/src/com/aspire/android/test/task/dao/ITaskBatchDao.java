/**

 * @(#) TaskBatchDao.java Created on 2012-5-22

 *

 * Copyright (c) 2012 Aspire. All Rights Reserved

 */
package com.aspire.android.test.task.dao;

import java.util.List;

import com.aspire.android.common.db.IBaseDao;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.task.entity.TaskBatch;

/**

 * The class <code>TaskBatchDao</code>

 *

 * @author Administrator

 * @version 1.0

 */
public interface ITaskBatchDao  extends IBaseDao<TaskBatch, Long>{

    /**
     * @return
     */
    List<TaskBatch> getNotOverTaskbatchs() throws MException;

    /**
     * @param taskId
     * @return
     */
    TaskBatch findTaskBatch(long taskId) throws MException;
    List<TaskBatch> findTaskBatchs(long taskId) throws MException;
    List<TaskBatch> findAllTaskBatch(String sql,String... arguments) throws MException;
    Long findMAXID() throws MException;
    Long findMAXTaskBatchID(long taskId,String iterationType) throws MException;
    Long findMAXTaskBatchIterationTypeID(long taskId,String iterationType) throws MException;
 
    List<TaskBatch> getOrderbyTaskbatchs() throws MException;
    
    public List<TaskBatch>findAllTaskBatch(int status) throws MException;

    /**
     * @param upstatus
     * @return
     */
    List<TaskBatch> findListTaskBatch(String upstatus)throws MException;
    
    public List<TaskBatch> allOrderbyTaskBatch(long TaskId) throws MException;
    
    public  List<TaskBatch>  findTaskBatchEndTimeIsNull()throws MException; 
}
