
package com.aspire.mpy.task;

import java.io.InputStream;
import java.util.Vector;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.aspire.mpy.Const;
import com.aspire.mpy.MpyApp;
import com.aspire.mpy.message.HttpConnectWrapper;
import com.aspire.mpy.message.response.IResponseMsg;
import com.aspire.mpy.util.Tools;

/**
 * 任务管理
 * 
 * @author x_liyajun 2010-9-12 下午11:01:17 TaskManager
 */
public class TaskManager {
    private static final String TAG = "TaskManager";

   // private final Context context;

    private static TaskManager instance;

    private static WorkThread workThread;
    
    private static final int TASK_NOTIFY = 101;// 任务完成通知
    
    private byte[] lock = new byte[0]; // 特殊的instance变量

    private Handler mHandler = new Handler() 
    {

        @Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			default:
				synchronized (lock) {
					Log.i(TAG, "network state =" + msg.arg1);
					ITask currTask = ((ITask) msg.obj);
					IResponseMsg respMsg = (IResponseMsg) currTask.getResponse();
					currTask.getListener().notifyTask(respMsg, msg.arg1); //  通知调用者
				}
			}
		}
    };

    
    public TaskManager() 
    {
       // context = MpyApp.context;
        workThread = new WorkThread();
        workThread.start();
    }

    public static TaskManager getInstance() 
    {
        if (instance == null) 
        {
            instance = new TaskManager();
        }
        return instance;
    }

    public void addFgTask(ITask task) 
    {
        workThread.addTask(task);
    }

    class WorkThread extends Thread {
        protected Vector<ITask> _taskList = new Vector<ITask>();
        
        private ITask currentTask = null; //当前任务
        
        volatile boolean stopped;

        public int getTaskListSize() 
        {
            synchronized (_taskList) 
            {
                return _taskList.size();
            }
        }

        public ITask getTask(int taskid) 
        {
            synchronized (_taskList) 
            {
                _taskList.notify();
                return null;
            }
        }

        public void addTask(ITask task) 
        {
            synchronized (_taskList) 
            {
                _taskList.addElement(task);
                _taskList.notify();
            }
        }

        private ITask getFirstTask() 
        {
            synchronized (_taskList) 
            {
                ITask task = null;
                if (_taskList.size() == 0) 
                {
                    try 
                    {
                        _taskList.wait();
                    }
                    catch (InterruptedException e) 
                    {
                        e.printStackTrace();
                    }
                    
                    if (_taskList.size() != 0) 
                    {
                        task = (ITask) _taskList.elementAt(0);
                        _taskList.removeElementAt(0);
                    }
                } 
                else 
                {
                    task = (ITask) _taskList.elementAt(0);
                    _taskList.removeElementAt(0);
                }
                return task;
            }
        }
        
        @Override
        public void run() 
        {
            // TODO Auto-generated method stub
            while (!stopped) 
            {
            	this.currentTask = getFirstTask();
            	int currentTaskState = Const.TaskState.SUCCESS;
            	if(Tools.CheckNetWork(MpyApp.context)){
            		 HttpConnectWrapper httpConn = new HttpConnectWrapper();
            		
            		 try 
                     {
            			 InputStream is = httpConn.httpPost(currentTask.getRequest().getURL(), 
                                 currentTask.getRequest().getContentType(), 
                                 currentTask.getRequest().getData(),
                                 currentTask.getRequest().getNetworkType(), 
                                 currentTask.getRequest().getNeedRespHeaderProps(),
                                 currentTask.getRead_time());
            			currentTask.getResponse().parseInputStream(is , httpConn.getConnection());

                     } catch (Exception ex) {
                    	 ex.printStackTrace();
                         Log.e(TAG, "get a Throwable!!!!" ,ex);
                         currentTaskState = Const.TaskState.FAIL;
                     }
                     finally {
                         httpConn.releaseConnect();
                         android.os.Message m = mHandler.obtainMessage(TASK_NOTIFY,currentTaskState, 0, currentTask);
                         mHandler.sendMessage(m);
                         
                     }
            	}else{
            		//没有网络
            		 currentTaskState = Const.TaskState.NO_NETWORK;
            		 android.os.Message m = mHandler.obtainMessage(TASK_NOTIFY,currentTaskState, 0, currentTask);
                     mHandler.sendMessage(m);
            	}
            }
        }
    }

    
}
