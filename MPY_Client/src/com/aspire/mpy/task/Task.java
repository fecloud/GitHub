/*
 * Copyright (C) 2010 Aspire
 * 
 * Task.java Version 1.0
 *
 */
package com.aspire.mpy.task;

import android.content.Context;

import com.aspire.mpy.message.request.IRequestMsg;
import com.aspire.mpy.message.response.IResponseMsg;

/**
 * @author x_liyajun
 *
 * 2010-8-22 下午08:36:01
 *  
 * Task
 *
 */
public abstract class Task implements ITask 
{
    protected int taskState=ITask.TASKSTATE_READY;
    /**任务的Listener*/
    private ITaskListener listener = null;
    
    /**任务请求，任务启动后会回调该接口获得向网络层发送的数据*/
    private IRequestMsg requestMsg = null;
    
    /**任务的响应，用来接受网络层返回的数据*/
    private IResponseMsg responseMsg = null;
    
    public Task(IRequestMsg requestMsg, IResponseMsg responseMsg, ITaskListener listener)
    {
        super();
        this.listener = listener;
        this.requestMsg = requestMsg;
        this.responseMsg = responseMsg;
    }
    
    public Task(String downloadURL,IRequestMsg requestMsg, IResponseMsg responseMsg, ITaskListener listener)
    {
        super();
        this.listener = listener;
        this.requestMsg = requestMsg;
        this.responseMsg = responseMsg;
    }
    
    public IRequestMsg getRequest() 
    {
        // TODO Auto-generated method stub
        return requestMsg;
    }

    public IResponseMsg getResponse() 
    {
        // TODO Auto-generated method stub
        return responseMsg;
    }

    public ITaskListener getListener() 
    {
        // TODO Auto-generated method stub
        return listener;
    }

    @Override
    public Context getContext() 
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getTaskState() {
        // TODO Auto-generated method stub
        return taskState;
    }
    
    
}
