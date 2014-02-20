/**
 * @(#) ClientRestoreService.java Created on 2012-10-25
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server.restore;

import org.apache.log4j.Logger;

import com.inspurworld.server.task.ClientReStoreTask;
import com.inspurworld.server.task.Task;
import com.inspurworld.server.task.TaskContainer;

/**
 * The class <code>ClientRestoreService</code> <br>
 * <b>用户行为还原类<b/>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class ClientRestoreService implements Runnable {

    private Logger logger = Logger.getLogger(getClass());

    private TaskContainer container = new TaskContainer();
    
    private Thread thisThread;

    private boolean flag;

    private Object lock = new Object();

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        logger.debug("service start run...");
        Task task = null;
        try {
            while (flag) {
                synchronized (lock) {
                    task = container.getRuningTask();
                    if (null == task) {
                        // 没有任务,把连接关掉
                        // sendDisConnectionRequest();
                        // disconnect();
                        logger.debug("upload thread wait");
                        lock.wait();
                        logger.debug("upload thread runing");
                    } else {
                        // 执行任务
                        if (!mather(task)) {
                            // 如果任务失败,添加到失败组
                            container.addFailTask(task);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("", e);
        }

    }

    /**
     * 调用匹配
     * 
     * @param task
     * @return
     */
    protected boolean mather(Task task) {
        logger.debug("service mather onece...");
        return task.doTask();
    }

    /**
     * 
     * @return
     */
    public boolean uploadImageMather(String path) {
        logger.debug("uploadImageMather path:" + path);
        // 当所有任务都执行完成时,通知线程继续
        if (container.getFailTasks().size() < 1 && container.getWaitTasks().size() < 1) {
            synchronized (lock) {
                container.AddTask(new ClientReStoreTask(path));
                lock.notifyAll();
            }
        } else {
            container.AddTask(new ClientReStoreTask(path));
        }
        return true;
    }

    /**
     * 返回还原线程状态,true为运行,false 为stop;
     * 
     * @return
     */
    public boolean isAlive() {
        return flag;
    }

    public void start() {
        flag = true;
        if (null == thisThread) {
            thisThread = new Thread(this);
            thisThread.start();
        }

    }

    public void stop() {
        flag = false;
        this.thisThread = null;
        logger.debug("service stop");
    }
}
