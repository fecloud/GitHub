/**
 * @(#) ITaskDao.java Created on 2012-5-9
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.task.dao;

import java.util.List;

import com.aspire.android.common.db.IBaseDao;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.task.entity.Task;

/**
 * The class <code>ITaskDao</code>
 *
 * @author linjunsui
 * @version 1.0
 * @param <T>
 */
public interface ITaskDao extends IBaseDao<Task, Long> {
	 /**
     * 获取包含该机型任务明细的任务
     * @param devType 设备型号
     * @return 任务
     */
    List<Task> getTaskByDeviceType(String devType, String imei) throws MException;

    /**
     * 通过任务编号查找任务
     * @param taskCode
     * @return
     */
    Task getTaskByCode(String testTaskCode) throws MException;
    
    public List<Task> allOrderbyTask() throws MException;
    
    public List<Task> getNotTask(Object[] mObject)throws MException;
    
    public List<Task> getTaskStatus(int mun)throws MException;
    
    public List<Task> findTask(String taskNam) throws MException;

}
