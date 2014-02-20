/*
 * Copyright (C) 2010 Aspire
 * 
 * TaskListener.java Version 1.0
 *
 */
package com.aspire.mpy.task;

import com.aspire.mpy.message.response.IResponseMsg;

/**
 * 任务的回调接口
 * @author x_liyajun
 *
 * 2010-8-22 上午10:50:41
 *  
 * TaskListener
 *
 */
public interface ITaskListener 
{
    /**
     * @param respMsg
     * @param status 1000:成功
     *               1001:APN切换失败
     *               1002:APN切换超时
     *               1005:解析消息异常
     *               9001:网络异常
     */
    public void notifyTask(IResponseMsg respMsg,int status);
}
