/*
 * Copyright (C) 2010 Aspire
 * 
 * ITask.java Version 1.0
 *
 */
package com.aspire.mpy.task;

import android.content.Context;

import com.aspire.mpy.message.request.IRequestMsg;
import com.aspire.mpy.message.response.IResponseMsg;


/**
 * @author x_liyajun
 *
 * 2010-8-22 下午06:15:17
 *  
 * ITask
 *
 */
public interface ITask 
{
    public static final int TASKSTATE_READY=0;     //新建的任务，尚未运行的状态，或者是从STOP后，通过restart进入这个状态
    public static final int TASKSTATE_RUNNING=1;   //通过start，处在运行状态
    public static final int TASKSTATE_STOPPED=2;   //被通过stop方法停止的状态
    public static final int TASKSTATE_END=3;       //正常运行结束的任务状态
    public static final int TASKSTATE_CANCELED=4;  //被cancel方法取消
    public static final int TASKSTATE_NOTNNETWORK=5;  //被cancel方法取消
    
    /*返回任务的的当前状态*/
    public int getTaskState();
//    public int getTaskId();
//    public void setTaskId(int id);
//    public void start() throws Exception;
//    public void stop() throws Exception;
//    public void restart()throws Exception; //在STOPPED状态的任务，通过restart()方法，重新进入READY状态
//    public void cancel();
    
    
    /**
     * 获取任务的响应
     * @return ResponseMessage
     */
    public IResponseMsg getResponse();
    
    public int getRead_time();

    /**
     * 获取任务的请求
     * @return RequestMessage
     */
    public IRequestMsg getRequest();
    
    /**
     * 获取任务的上下文
     * @return Context
     */
    public Context getContext();
    
    public ITaskListener getListener();
}
