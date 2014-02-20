/**

 * @(#) ATaskServiceImpl.java Created on 2012-5-21

 *

 * Copyright (c) 2012 Aspire. All Rights Reserved

 */
package com.aspire.android.test.task.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.util.Log;

import com.aspire.android.common.db.DatabaseHelper;
import com.aspire.android.common.exception.ExceptionHandler;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.PreferencesManager;
import com.aspire.android.test.application.ExecApplication;
import com.aspire.android.test.device.sync.DeviceSync;
import com.aspire.android.test.execute.CommandConstants;
import com.aspire.android.test.execute.NameConstants;
import com.aspire.android.test.execute.NameConstants.SharedPrefConstants;
import com.aspire.android.test.execute.TransactionData;
import com.aspire.android.test.execute.ui.PrefsActivity;
import com.aspire.android.test.log.RunLogger;
import com.aspire.android.test.script.entity.LogValues;
import com.aspire.android.test.script.service.IScriptService;
import com.aspire.android.test.servicekey.entity.AtsServiceIndexRelation;
import com.aspire.android.test.servicekey.service.IServiceKeyService;
import com.aspire.android.test.task.dao.ITaskBatchDao;
import com.aspire.android.test.task.dao.ITaskDao;
import com.aspire.android.test.task.dao.ITaskItemBatchDao;
import com.aspire.android.test.task.dao.ITaskItemDao;
import com.aspire.android.test.task.dao.IUploadDao;
import com.aspire.android.test.task.entity.IndexListing;
import com.aspire.android.test.task.entity.QueryResults;
import com.aspire.android.test.task.entity.Task;
import com.aspire.android.test.task.entity.TaskBatch;
import com.aspire.android.test.task.entity.TaskBathItem;
import com.aspire.android.test.task.entity.TaskItem;
import com.aspire.android.test.task.entity.TaskItemBatch;
import com.aspire.android.test.task.entity.TaskTaskItem;
import com.aspire.android.test.task.entity.Upload;
import com.aspire.mgt.ats.tm.sync.task.entity.ExecuteSchema;
import com.aspire.mgt.ats.tm.sync.task.entity.TaskInfo;
import com.aspire.mgt.ats.tm.sync.task.entity.TaskKey;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * 
 * The class <code>PublicServiceImpl</code>
 * 
 * 
 * 
 * @author gounming
 * 
 * @version 1.0
 */
@Singleton
public class TaskService implements ITaskService {

    private RunLogger runLogger = RunLogger.getInstance();

    /**
     * Log tag
     */
    protected static final String LOGTAG = TaskService.class.getSimpleName();

    private DeviceSync deviceSync;

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
    public IServiceKeyService mIServiceKeyService;

    @Inject
    protected IScriptService mIScriptService;

    public DatabaseHelper mDatabaseHelper;

    public Context mContext;
    private SharedPreferences preferences;
    private SharedPreferences hourPreferences;
    private PreferencesManager preferencesManager = PreferencesManager.getInstance();

    public Calendar c = Calendar.getInstance();
    public int year = c.get(Calendar.YEAR);
    public int month = c.get(Calendar.MONTH) + 1;
    public int day = c.get(Calendar.DATE);
    public int hour = c.get(Calendar.HOUR_OF_DAY);
    public int week = c.get(Calendar.WEEK_OF_MONTH);
    public static int executeTime = 1;
    public static int updateTime = 0;
    public static int endTime = 22;

    @Inject
    public TaskService(Context mContext) {
        this.mContext = mContext;
        deviceSync = new DeviceSync();
        preferences = preferencesManager.getPreferences();
        hourPreferences = mContext.getSharedPreferences("hour_preferences", Context.MODE_PRIVATE);
        Editor editor = hourPreferences.edit();
        editor.putInt(SharedPrefConstants.TASK_EXECUTE_OR_UPDATE_TIME, executeTime);
        editor.commit();
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.ITaskService#mergeTaskOnSync(java.lang.String,
     *      com.aspire.mgt.tm.sync.task.entity.TaskInfo)
     */
    public Task mergeTaskOnSync(String deviceType, TaskInfo taskInfo, HashMap<String, Object> map) throws MException {
        Task task = taskDao.getTaskByCode(taskInfo.getTestTaskCode());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        if (task == null) {
            map.put(NameConstants.TASK_UPDATE_STATUS, true);
            return addTaskOnTaskInfo(deviceType, taskInfo, sdf);
        }
        return task;
    }

    /**
     * 添加任务
     * 
     * @param deviceType
     * @param taskInfo
     * @param sdf
     * @return
     */
    private Task addTaskOnTaskInfo(String deviceType, TaskInfo taskInfo, SimpleDateFormat sdf) throws MException {
        Task task = new Task();
        setTask(taskInfo, sdf, task);
        task = taskDao.insert(task);
        if (taskInfo.getKeySchema() != null && taskInfo.getKeySchema().getTaskKeys() != null) {

            for (TaskKey taskKey : taskInfo.getKeySchema().getTaskKeys()) {
                TaskItem item = new TaskItem();
                item.setTaskId(task.getId());
                item.setDevType(deviceType);
                item.setServType(taskKey.getServiceCode());
                item.setTaskKeyCode(taskKey.getTestKeyCode());
                item.setDeviceCode(taskKey.getDevId());
                taskItemDao.insert(item);
            }
        }
        return task;
    }

    /**
     * SET TASK
     * 
     * @param taskInfo
     * @param sdf
     * @param task
     */
    private void setTask(TaskInfo taskInfo, SimpleDateFormat sdf, Task task) throws MException {
        task.setTaskCode(taskInfo.getTestTaskCode());
        task.setTaskName(taskInfo.getTestTaskName());
        task.setTaskDesc(taskInfo.getTestTaskDesc());
        task.setLogLevel(taskInfo.getLogLevel());
        task.setNetCapture(taskInfo.getNetCapture());
        task.setTaskType(taskInfo.getTestTaskType());
        task.setLastUpdateDate(new Date());
        if ("H".equals(taskInfo.getPriority())) {
            task.setPriority(Task.PRIORITY_HIGH);
        } else if ("M".equals(taskInfo.getPriority())) {
            task.setPriority(Task.PRIORITY_MEDIUM);
        } else {
            task.setPriority(Task.PRIORITY_LOW);
        }
        try {
            task.setStartDate(sdf.parse(taskInfo.getStartDate()));
            task.setEndDate(sdf.parse(taskInfo.getEndDate()));
        } catch (ParseException e) {
            MException mexception = ExceptionHandler.handle(e, "Error while parse taskinfo's startdate and enddate");
            if (mexception != null) {
                throw mexception;
            }
        }

        ExecuteSchema executeInfo = taskInfo.getExecuteSchema();
        task.setExeType(executeInfo.getExeType());
        task.setExeBeginTime(executeInfo.getExeBeginTime());
        task.setExeEndTime(executeInfo.getExeEndTime());
        task.setIterationType(executeInfo.getIterationType());
        if (executeInfo.getIterationNum() != null && executeInfo.getIterationNum().length() > 0) {
            task.setIterationNum(Integer.parseInt(executeInfo.getIterationNum()));
        }
        task.setInterval(Integer.parseInt(executeInfo.getInterval()));
        task.setJudgeResult(0);
        task.setStatus(1);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.ITaskService#getAllTask()
     */
    public List<Task> getAllTask() throws MException {
        return taskDao.findAll();
    }

    public List<Task> getNotTask(Object[] mObject) throws MException {
        return taskDao.getNotTask(mObject);
    }

    public Task updataTask(Task mTask) throws MException {
        return taskDao.update(mTask);
    }

    public List<Task> findTask(String taskName) throws MException {
        return taskDao.findTask(taskName);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.ITaskService#getAllTaskItem(long)
     */
    public List<TaskItem> getAllTaskItem(long taskId) throws MException {
        return taskItemDao.getTaskItem(taskId);
    }

    public TaskItem getTaskItem(long taskItemId) throws MException {
        return taskItemDao.findById(taskItemId);
    }

    public List<TaskItem> getAllTaskItem() throws MException {
        return taskItemDao.findAll();
    }

    public List<TaskItem> getAllTaskItem(String taskKeyCode, String servType) throws MException {
        return taskItemDao.getAllTaskItem(taskKeyCode, servType);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.ITaskService#getTaskBatch(long)
     */
    public TaskBatch getTaskBatch(long taskId) throws MException {
        return mITaskBatchDao.findTaskBatch(taskId);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.ITaskService#addTaskBatch(com.aspire.android.test.task.entity.TaskBatch)
     */
    public void addTaskBatch(TaskBatch taskBatch) throws MException {
        mITaskBatchDao.insert(taskBatch);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.ITaskService#updateTaskBatch(com.aspire.android.test.task.entity.TaskBatch)
     */
    public void updateTaskBatch(TaskBatch taskBatch) throws MException {
        mITaskBatchDao.update(taskBatch);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.ITaskService#getAllTaskBatch()
     */
    public List<TaskBatch> getAllTaskBatch() throws MException {
        return mITaskBatchDao.findAll();
    }

    public List<Task> getTaskForStatus(int status) throws MException {
        return taskDao.getTaskStatus(status);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.ITaskService#addTaskItemBatch(com.aspire.android.test.task.entity.TaskItemBatch)
     */
    public void addTaskItemBatch(TaskItemBatch taskItemBatch) throws MException {
        mITaskItemBatchDao.insert(taskItemBatch);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.ITaskService#motion()
     */
    public void motionTask() throws MException {
        runLogger.debug(getClass(), "execute task start");
        try {
            List<Task> listTask = null;
            listTask = taskDao.getTaskStatus(1);
            if (listTask != null && listTask.size() > 0) {

                /**
                 * 任务优先级： L：低 M：中 H：高
                 */
                List<Task> listHH = new ArrayList<Task>();
                List<Task> listMM = new ArrayList<Task>();
                List<Task> listLL = new ArrayList<Task>();
                for (Task task : listTask) {
                    // 判斷任務是不是還在执行時間之內
                    if (new Date().after(task.getStartDate()) && new Date().before(task.getEndDate())) {
                        if (task.getPriority().equals(Task.PRIORITY_HIGH)) {
                            listHH.add(task);
                        } else if (task.getPriority().equals(Task.PRIORITY_MEDIUM)) {
                            listMM.add(task);
                        } else if (task.getPriority().equals(Task.PRIORITY_LOW)) {
                            listLL.add(task);
                        }
                    }
                }
                // 得到任务优先级高的
                for (Task task : listHH) {
                    // 按類型执行任务
                    boolean bool = motionTypeTask(task);
                    if (bool)
                        return;
                }
                // 得到任务优先级中的
                for (Task task : listMM) {
                    // 按類型执行任务
                    boolean bool = motionTypeTask(task);
                    if (bool)
                        return;
                }
                // 得到任务优先级低的
                for (Task task : listLL) {
                    // 按類型执行任务
                    boolean bool = motionTypeTask(task);
                    if (bool)
                        return;
                }
            }
        } finally {
            runLogger.debug(getClass(), "execute task finish");
        }
    }

    /**
     * @param task
     * @throws MException
     */
    private boolean motionTypeTask(Task task) throws MException {
        String iterationType = null;
        Log.d(LOGTAG, "task.getIterationType() ::" + task.getIterationType());
        Log.d(LOGTAG, "task.getExeType() ::" + task.getExeType());
        Log.d(LOGTAG, "task.getExeType() ::" + task.getExeType().toString().equals("1"));

        List<TaskBatch> taskBatch = mITaskBatchDao.findTaskBatchEndTimeIsNull();
        for (TaskBatch taskBatch2 : taskBatch) {
            List<TaskItemBatch> listTaskItemBatch = mITaskItemBatchDao.findAllTaskItemBatch(taskBatch2.getId(), null);
            if (listTaskItemBatch.size() == 0) {
                mITaskBatchDao.detele(taskBatch2);
            }
        }

        // 执行类型：1：按时执行 2：按次执行
        if (task.getExeType().toString().equals("1") && !task.getIterationType().toString().equals("0")) {
            /**
             * 重复类型： 0：不限, 1：每天 ,2：每周 ,3：每月 ,执行类型为2时，默认为0 year+"_"+month+"_"+day+"_"+week
             */

            if (task.getIterationType().toString().equals("1")) {
                iterationType = year + "_" + month + "_" + day;
                long num = findMAXTaskBatchIterationTypeID(task.getId(), iterationType);
                long maxID = findMAXTaskBatchID(task.getId(), iterationType);
                return executeTimes(num, maxID, iterationType, task);

            } else if (task.getIterationType().toString().equals("2")) {
                iterationType = year + "/" + month + "/" + week;
                long num = findMAXTaskBatchIterationTypeID(task.getId(), iterationType);
                long maxID = findMAXTaskBatchID(task.getId(), iterationType);
                return executeTimes(num, maxID, iterationType, task);

            } else if (task.getIterationType().toString().equals("3")) {
                iterationType = year + "_" + month;
                long num = findMAXTaskBatchIterationTypeID(task.getId(), iterationType);
                ;
                long maxID = findMAXTaskBatchID(task.getId(), iterationType);
                return executeTimes(num, maxID, iterationType, task);
            }

        } else if (task.getExeType().toString().equals("2") || task.getIterationType().toString().equals("0")) {
            iterationType = "20";
            long num = findMAXTaskBatchIterationTypeID(task.getId(), iterationType);
            long maxID = findMAXTaskBatchID(task.getId(), iterationType);
            return executeTimes(num, maxID, iterationType, task);
        }
        return false;
    }

    /**
     * 
     * @param Times
     * @param task
     * @throws MException
     */
    private boolean executeTimes(long Times, long maxID, String iterationType, Task task) throws MException {
        // 循环次数
        if (Times < task.getIterationNum()) {
            if (task.getJudgeResult() == 1) {
                task.setJudgeResult(0);
                taskDao.update(task);
            }
            // 间隔时间，单位分钟
            if (task.getInterval() == 0) {
                return taskBatchExecute(iterationType, task);
            } else {
                if (maxID == 0) {
                    return taskBatchExecute(iterationType, task);
                } else {

                    TaskBatch mTaskBatch = findTaskBatch(maxID);
                    if (mTaskBatch != null & mTaskBatch.getEndTime() != null) {

                        if (new Date().getTime() > mTaskBatch.getEndTime().getTime() + task.getInterval() * 60000) {
                            return taskBatchExecute(iterationType, task);
                        }

                    } else {
                        return taskBatchExecute(iterationType, task);
                    }
                }
            }
        }
        return false;
    }

    /**
     * @param iterationType
     * @param task
     * @throws MException
     */
    private boolean taskBatchExecute(String iterationType, Task task) throws MException {
        String date = new SimpleDateFormat("HHmmss").format(new Date());
        Log.d(LOGTAG, "当前时间 ::" + date);
        // 任务开始执行时间区段
        if (Long.parseLong(date) > Long.parseLong(task.getExeBeginTime())
                && Long.parseLong(date) < Long.parseLong(task.getExeEndTime())) {
            return operationCases(iterationType, task);
        }
        return false;
    }

    /**
     * @param iterationType
     * @param task
     * @throws MException
     */
    private boolean operationCases(String iterationType, Task task) throws MException {
        boolean bool = false;
        TaskBatch mTaskBatch = new TaskBatch();
        mTaskBatch.setTaskId(task.getId());
        mTaskBatch.setIterationType(iterationType);
        mTaskBatch.setTaskCode(task.getTaskCode());
        mTaskBatch.setStartTime(new Date());
        mTaskBatch.setStatus(0);
        mTaskBatch.setUpstatus("1");
        TaskBatch taskBatch = null;
        taskBatch = mITaskBatchDao.insert(mTaskBatch);
        List<TaskItem> listTaskItem = taskItemDao.getTaskItem(task.getId());
        Long taskBatchId = taskBatch.getId();

        Map<String, TransactionData> dataMap = new HashMap<String, TransactionData>();
        Map<String, Long> logIDmap = new HashMap<String, Long>();

        // 得到所有指标
        for (TaskItem taskItem : listTaskItem) {
            // 运行案例
            moveCase(taskBatchId, dataMap, logIDmap, taskItem, task);
        }
        List<TaskItemBatch> mTaskItemBatchs = mITaskItemBatchDao.findAllTaskItemBatch(taskBatchId, "1");
        bool = true;
        // 更新TaskBatch
        if (mTaskItemBatchs != null) {
            TaskBatch upTaskBatch = mITaskBatchDao.findById(taskBatchId);
            upTaskBatch.setEndTime(new Date());
            upTaskBatch.setStatus(1);
            upTaskBatch = mITaskBatchDao.update(upTaskBatch);
        } else {
            mITaskBatchDao.deteleById(taskBatchId);
        }

        return bool;
    }

    /**
     * @param taskBatchId
     * @param dataMap
     * @param logIDmap
     * @param taskItem
     * @throws MException
     */
    private void moveCase(Long taskBatchId, Map<String, TransactionData> dataMap, Map<String, Long> logIDmap,
            TaskItem taskItem, Task task) throws MException {
        AtsServiceIndexRelation ast = mIServiceKeyService.findAtsServiceIndexRelation(taskItem.getServType(),
                taskItem.getTaskKeyCode());
        int result = 0;
        // 状态: 1-有效 /0-失效
        if (ast != null && ast.getStatus().toString().equals("1")) {
            long times = mITaskItemBatchDao.findTestkItemBatchAll(taskItem.getTaskId(), taskItem.getId());
            TaskItemBatch mTaskItemBatch = new TaskItemBatch();

            if (dataMap.size() == 0) {
                extracted(taskBatchId, dataMap, logIDmap, taskItem, task, times, mTaskItemBatch, mIScriptService);
            } else if (dataMap.get(taskItem.getServType() + taskItem.getTaskKeyCode()) != null) {
                TransactionData mTransactionData = dataMap.get(taskItem.getServType() + taskItem.getTaskKeyCode());
                mTaskItemBatch.setTimes(times + 1);
                mTaskItemBatch.setTaskId(taskItem.getTaskId());
                mTaskItemBatch.setTaskItemId(taskItem.getId());
                mTaskItemBatch.setTaskBatchId(taskBatchId);
                mTaskItemBatch.setStartTime(new Date(mTransactionData.getStartTime()));
                mTaskItemBatch.setEndTime(new Date(mTransactionData.getEndTime()));
                if (mTransactionData.getResult() != null) {
                    mTaskItemBatch.setStatus(mTransactionData.getResult().equals("00") ? "1" : "0");
                    mTaskItemBatch.setResult(mTransactionData.getResult());
                } else {
                    mTaskItemBatch.setStatus(Integer.toString(result).equals("1") ? "1" : "0");
                    mTaskItemBatch.setResult(Integer.toString(result).equals("1") ? "00" : "04");
                }
                mTaskItemBatch.setTestValue(mTransactionData.getValue() != null ? mTransactionData.getValue() : "");
                mTaskItemBatch.setUpstatus("0");
                TaskItemBatch taskItemBatch = mITaskItemBatchDao.insert(mTaskItemBatch);
                mIScriptService.addTestLogOnTaskItem(taskItemBatch.getId(),
                        logIDmap.get(taskItem.getServType() + taskItem.getTaskKeyCode()));
            } else {
                extracted(taskBatchId, dataMap, logIDmap, taskItem, task, times, mTaskItemBatch, mIScriptService);
            }
        }
    }

    /**
     * @param taskBatchId
     * @param dataMap
     * @param logIDmap
     * @param taskItem
     * @param logLevel
     * @param times
     * @param mTaskItemBatch
     * @param mIScriptService
     * @throws MException
     */
    private void extracted(Long taskBatchId, Map<String, TransactionData> dataMap, Map<String, Long> logIDmap,
            TaskItem taskItem, Task task, long times, TaskItemBatch mTaskItemBatch, IScriptService mIScriptService)
            throws MException {
        int result;
        String ftpLocalPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + preferences.getString(PrefsActivity.SET_SAVE_ADDRESS, null);
        String logPath = ftpLocalPath + "runlogs/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_"
                + taskItem.getTaskId() + "_" + taskBatchId;
        LogValues backValues = mIScriptService.executeIScript(taskItem.getTaskKeyCode(), taskItem.getServType(),
                logPath, task);

        Date startTime = new Date();
        if (backValues != null) {
            List<TransactionData> transactionData = null;
            if (backValues.getContentValues() != null) {
                transactionData = backValues.getContentValues().getTransactionDatas();
            }
            // 拨测结果
            result = backValues.getContentValues().getAsInteger(CommandConstants.KEY_CASE_TESTRESULT);
            if (transactionData != null && transactionData.size() > 0) {
                for (TransactionData transactionData1 : transactionData) {
                    dataMap.put(transactionData1.getName(), transactionData1);
                    logIDmap.put(transactionData1.getName(), backValues.getLogId());
                }
                TransactionData mTransactionData = dataMap.get(taskItem.getServType() + taskItem.getTaskKeyCode());
                mTaskItemBatch.setTimes(times + 1);
                mTaskItemBatch.setTaskId(taskItem.getTaskId());
                mTaskItemBatch.setTaskItemId(taskItem.getId());
                mTaskItemBatch.setTaskBatchId(taskBatchId);
                mTaskItemBatch.setStartTime(new Date(mTransactionData.getStartTime()));
                mTaskItemBatch.setEndTime(new Date(mTransactionData.getEndTime()));

                // 连续N次出错--03的处理
                // 如果是出现结果为03，判断前两条执行结果是否为03，14，是则更改03为14
                final String ERROR_RESULT_03 = "03";
                final String ERROR_RESULT_14 = "14";
                if (mTransactionData.getResult().equals(ERROR_RESULT_03)) {
                    long MAX_TIMES = 3;
                    // 连续三条，则需要查询前面两条数据
                    List<TaskItemBatch> taskItemBatchList = mITaskItemBatchDao.findOrderById(taskItem.getTaskId(),
                            taskItem.getId(), MAX_TIMES - 1L);
                    if (taskItemBatchList != null && taskItemBatchList.size() >= (MAX_TIMES - 1)) {
                        boolean isChange = true;
                        for (TaskItemBatch b : taskItemBatchList) {
                            if (!b.getResult().equals(ERROR_RESULT_03) && !b.getResult().equals(ERROR_RESULT_14)) {
                                isChange = false;
                                break;
                            }
                        }
                        // 满足条件，将前面查询数据修改
                        if (isChange) {
                            for (TaskItemBatch b : taskItemBatchList) {
                                b.setResult(ERROR_RESULT_14);
                                mITaskItemBatchDao.update(b);
                            }
                            deviceSync.deviceAlarm("119", "业务指标连续失败告警");
                            mTransactionData.setResult(ERROR_RESULT_14);
                        }
                    }
                }
                
                mTaskItemBatch.setResult(mTransactionData.getResult());
                mTaskItemBatch.setStatus(mTransactionData.getResult().equals("00") ? "1" : "0");


                mTaskItemBatch.setTestValue(mTransactionData.getValue() != null ? mTransactionData.getValue() : "0");
                mTaskItemBatch.setUpstatus("0");
                TaskItemBatch taskItemBatch = mITaskItemBatchDao.insert(mTaskItemBatch);
                if (logIDmap.get(taskItem.getServType() + taskItem.getTaskKeyCode()) != null) {
                    mIScriptService.addTestLogOnTaskItem(taskItemBatch.getId(),
                            logIDmap.get(taskItem.getServType() + taskItem.getTaskKeyCode()));
                } else {
                    mIScriptService.addTestLogOnTaskItem(taskItemBatch.getId(), backValues.getLogId());
                }

            } else {// transactionData ==null of transactionData.size() <=0
                mTaskItemBatch.setTimes(times + 1);
                mTaskItemBatch.setTaskId(taskItem.getTaskId());
                mTaskItemBatch.setTaskItemId(taskItem.getId());
                mTaskItemBatch.setTaskBatchId(taskBatchId);
                mTaskItemBatch.setStartTime(startTime);
                mTaskItemBatch.setEndTime(new Date());
                mTaskItemBatch.setStatus(Integer.toString(result).equals("1") ? "1" : "0");
                mTaskItemBatch.setResult(Integer.toString(result).equals("1") ? "00" : "04");

                mTaskItemBatch.setTestValue("0");
                mTaskItemBatch.setUpstatus("0");
                TaskItemBatch taskItemBatch = mITaskItemBatchDao.insert(mTaskItemBatch);
                if (logIDmap.get(taskItem.getServType() + taskItem.getTaskKeyCode()) != null) {
                    mIScriptService.addTestLogOnTaskItem(taskItemBatch.getId(),
                            logIDmap.get(taskItem.getServType() + taskItem.getTaskKeyCode()));
                } else {
                    mIScriptService.addTestLogOnTaskItem(taskItemBatch.getId(), backValues.getLogId());
                }
            }
        } else {// null of backValues
            mTaskItemBatch.setTimes(times + 1);
            mTaskItemBatch.setTaskId(taskItem.getTaskId());
            mTaskItemBatch.setTaskItemId(taskItem.getId());
            mTaskItemBatch.setTaskBatchId(taskBatchId);
            mTaskItemBatch.setStartTime(startTime);
            mTaskItemBatch.setEndTime(new Date());
            mTaskItemBatch.setStatus("1");
            mTaskItemBatch.setResult("05");
            mTaskItemBatch.setTestValue("0");
            mTaskItemBatch.setUpstatus("0");
            mITaskItemBatchDao.insert(mTaskItemBatch);

            Log.d(LOGTAG, "extracted,move calse null!!");
        }
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.ITaskService#findMAXTaskBatchID(long)
     */
    public Long findMAXTaskBatchID(long taskId, String iterationType) throws MException {
        return mITaskBatchDao.findMAXTaskBatchID(taskId, iterationType);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.ITaskService#findMAXTaskBatchIterationTypeID(long, java.lang.String)
     */
    public Long findMAXTaskBatchIterationTypeID(long taskId, String iterationType) throws MException {
        return mITaskBatchDao.findMAXTaskBatchIterationTypeID(taskId, iterationType);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.ITaskService#findTaskBatch(long)
     */
    public TaskBatch findTaskBatch(long id) throws MException {
        return mITaskBatchDao.findById(id);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.ITaskService#findTaskItemBatch(long)
     */
    public TaskItemBatch findTaskItemBatch(long id) throws MException {
        return mITaskItemBatchDao.findById(id);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.ITaskService#findAllTaskBatch(int)
     */
    public List<TaskBatch> findAllTaskBatch(int status) throws MException {
        return mITaskBatchDao.findAllTaskBatch(status);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.ITaskService#findAllTaskBatch(int)
     */
    public List<TaskBatch> findAllTaskBatch(String upstatus) throws MException {
        return mITaskBatchDao.findListTaskBatch(upstatus);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.ITaskService#findListTaskItemBatch(long)
     */
    public List<TaskItemBatch> findListTaskItemBatch(long taskItemid, long taskBatchId) throws MException {
        return mITaskItemBatchDao.findListTaskItemBatch(taskItemid, taskBatchId);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.ITaskService#findListTaskItemBatch(long)
     */
    public List<TaskItemBatch> findListTaskItemBatch(long taskItemid, String upStatus) throws MException {
        return mITaskItemBatchDao.findListTaskItemBatch(taskItemid, upStatus);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.ITaskService#allOrderbyTask()
     */
    public List<Task> allOrderbyTask() throws MException {
        return taskDao.allOrderbyTask();
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.ITaskService#allOrderbyTaskBatch(long)
     */
    public List<TaskBatch> allOrderbyTaskBatch(long TaskId) throws MException {
        return mITaskBatchDao.allOrderbyTaskBatch(TaskId);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.ITaskService#allOrderbyTaskItemBatch(long, long)
     */
    public List<TaskItemBatch> allOrderbyTaskItemBatch(long TaskId, long TaskBatchId) throws MException {
        return mITaskItemBatchDao.allOrderbyTaskItemBatch(TaskId, TaskBatchId);
    }

    public List<TaskBathItem> findTaskBathItem(long taskId, long TaskBatchId) throws MException {
        List<TaskBathItem> listTaskBathItem = new ArrayList<TaskBathItem>();

        List<TaskItemBatch> listTaskItemBatch = mITaskItemBatchDao.allOrderbyTaskItemBatch(taskId, TaskBatchId);
        Task mTask = taskDao.findById(taskId);
        for (TaskItemBatch taskItemBatch : listTaskItemBatch) {
            TaskBathItem mTaskBathItem = new TaskBathItem();
            TaskItem mTaskItem = taskItemDao.findById(taskItemBatch.getTaskItemId());
            AtsServiceIndexRelation mAtsServiceIndexRelation = mIServiceKeyService.findAtsServiceIndexRelation(
                    mTaskItem.getServType(), mTaskItem.getTaskKeyCode());
            mTaskBathItem.setTaskItemBatchId(taskItemBatch.getId());
            mTaskBathItem.setTestKeyCode(mTaskItem.getTaskKeyCode());
            mTaskBathItem.setTestKeyName(mAtsServiceIndexRelation.getTestKeyName());
            mTaskBathItem.setResult(taskItemBatch.getStatus());
            mTaskBathItem.setLogLevel(mTask.getLogLevel());
            listTaskBathItem.add(mTaskBathItem);
        }
        return listTaskBathItem;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.ITaskService#updateTaskItemBatch(com.aspire.android.test.task.entity.TaskItemBatch)
     */
    public TaskItemBatch updateTaskItemBatch(TaskItemBatch mTaskItemBatch) throws MException {
        return mITaskItemBatchDao.update(mTaskItemBatch);
    }

    public void updateTaskItemBatchById(long id, String[] columns, String[] values) throws MException {
        mITaskItemBatchDao.updateTaskItemBatchById(id, columns, values);
    }

    public List<TaskItemBatch> findTestkItemBatchUpStatus(long taskItemId, String upStatus) throws MException {
        return mITaskItemBatchDao.findTestkItemBatchUpStatus(taskItemId, upStatus);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.ITaskService#getTask(java.lang.Long)
     */
    public Task getTask(Long id) throws MException {
        return taskDao.findById(id);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.ITaskService#addUpload(com.aspire.android.test.task.entity.Upload)
     */
    public Upload addUpload(Upload mUpload) throws MException {
        return mIUploadDao.insert(mUpload);

    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.ITaskService#listUploads(java.lang.String)
     */
    public List<Upload> listUploads(String upstatus) throws MException {
        return mIUploadDao.listUploads(upstatus);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.ITaskService#listUploads(java.lang.String)
     */
    public List<Upload> listUploads(String upstatus, String resp) throws MException {
        return mIUploadDao.listUploads(upstatus, resp);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.ITaskService#updateUpload(com.aspire.android.test.task.entity.Upload)
     */
    public Upload updateUpload(Upload mUpload) throws MException {
        return mIUploadDao.update(mUpload);
    }

    /**
     * 
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.ITaskService#taskReiteration(long)
     */
    public void taskReiteration(long taskItemBatchId) throws MException {
        ExecApplication.instance().getRedoTaskHash().add(taskItemBatchId);

    }

    /**
     * 
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.ITaskService#caseReiteration()
     */
    public void caseReiteration() throws MException {
        HashSet<Long> redoTaskHash = ExecApplication.instance().getRedoTaskHash();
        Map<String, TransactionData> dataMap = new HashMap<String, TransactionData>();
        Map<String, Long> logIDmap = new HashMap<String, Long>();
        for (Long taskItemBatchId : redoTaskHash) {
            TaskItemBatch mTaskItemBatch = mITaskItemBatchDao.findById(taskItemBatchId);
            Task mTask = taskDao.findById(mTaskItemBatch.getTaskId());
            TaskItem taskItem = taskItemDao.findById(mTaskItemBatch.getTaskItemId());

            AtsServiceIndexRelation ast = mIServiceKeyService.findAtsServiceIndexRelation(taskItem.getServType(),
                    taskItem.getTaskKeyCode());
            int result = 0;
            // 状态: 1-有效 /0-失效
            if (ast != null && ast.getStatus().toString().equals("1")) {
                String ftpLocalPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                        + preferences.getString(PrefsActivity.SET_SAVE_ADDRESS, null);
                String logPath = ftpLocalPath + "runlogs/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
                        + "_" + taskItem.getTaskId() + "_" + mTaskItemBatch.getTaskBatchId();

                LogValues backValues = mIScriptService.executeIScript(taskItem.getTaskKeyCode(),
                        taskItem.getServType(), logPath, mTask);

                if (backValues != null) {
                    List<TransactionData> transactionData = null;
                    if (backValues.getContentValues() != null) {
                        transactionData = backValues.getContentValues().getTransactionDatas();
                    }

                    // 拨测结果
                    result = backValues.getContentValues().getAsInteger(CommandConstants.KEY_CASE_TESTRESULT);
                    if (transactionData != null && result == 1) {
                        for (TransactionData transactionData1 : transactionData) {
                            dataMap.put(transactionData1.getName(), transactionData1);
                            logIDmap.put(transactionData1.getName(), backValues.getLogId());
                        }
                        TransactionData tTaskItemBatch = dataMap
                                .get(taskItem.getServType() + taskItem.getTaskKeyCode());
                        mTaskItemBatch.setTimes(mTaskItemBatch.getTimes());
                        mTaskItemBatch.setTaskId(taskItem.getTaskId());
                        mTaskItemBatch.setTaskItemId(taskItem.getId());
                        mTaskItemBatch.setTaskBatchId(mTaskItemBatch.getTaskBatchId());
                        mTaskItemBatch.setStartTime(mTaskItemBatch.getStartTime());
                        mTaskItemBatch.setEndTime(mTaskItemBatch.getEndTime());
                        mTaskItemBatch.setStatus(tTaskItemBatch.getResult().equals("00") ? "1" : "0");
                        mTaskItemBatch.setResult(tTaskItemBatch.getResult());
                        mTaskItemBatch
                                .setTestValue(tTaskItemBatch.getValue() != null ? tTaskItemBatch.getValue() : "0");
                        mTaskItemBatch.setUpstatus("0");
                        TaskItemBatch taskItemBatch = mITaskItemBatchDao.insert(mTaskItemBatch);
                        mIScriptService.addTestLogOnTaskItem(taskItemBatch.getId(), backValues.getLogId());

                    } else {

                        mTaskItemBatch.setTimes(mTaskItemBatch.getTimes());
                        mTaskItemBatch.setTaskId(taskItem.getTaskId());
                        mTaskItemBatch.setTaskItemId(taskItem.getId());
                        mTaskItemBatch.setTaskBatchId(mTaskItemBatch.getTaskBatchId());
                        mTaskItemBatch.setStartTime(mTaskItemBatch.getStartTime());
                        mTaskItemBatch.setEndTime(mTaskItemBatch.getEndTime());
                        mTaskItemBatch.setStatus(Integer.toString(result).equals("1") ? "1" : "0");
                        mTaskItemBatch.setResult(Integer.toString(result).equals("1") ? "00" : "04");
                        mTaskItemBatch.setTestValue("0");
                        mTaskItemBatch.setUpstatus("0");
                        TaskItemBatch taskItemBatch = mITaskItemBatchDao.insert(mTaskItemBatch);
                        mIScriptService.addTestLogOnTaskItem(taskItemBatch.getId(), backValues.getLogId());

                        Log.d(LOGTAG, "executeIScript result: " + result + " transactionData is null.");
                    }
                } else {
                    mTaskItemBatch.setTimes(mTaskItemBatch.getTimes());
                    mTaskItemBatch.setTaskId(taskItem.getTaskId());
                    mTaskItemBatch.setTaskItemId(taskItem.getId());
                    mTaskItemBatch.setTaskBatchId(mTaskItemBatch.getTaskBatchId());
                    mTaskItemBatch.setStartTime(mTaskItemBatch.getStartTime());
                    mTaskItemBatch.setEndTime(mTaskItemBatch.getEndTime());
                    mTaskItemBatch.setStatus("1");
                    mTaskItemBatch.setResult("05");
                    mTaskItemBatch.setTestValue("0");
                    mTaskItemBatch.setUpstatus("0");
                    mITaskItemBatchDao.insert(mTaskItemBatch);

                    Log.d(LOGTAG, "executeIScript result Fail.");
                }

            }
            ExecApplication.instance().getRedoTaskHash().remove(taskItemBatchId);
        }

    }

    public void deleteTaskCase(long taskItemBatchId) throws MException {
        mITaskItemBatchDao.deteleById(taskItemBatchId);
    }

    public int deleteTaskCase(List<Long> taskItemBatchIds) throws MException {
        return mITaskItemBatchDao.deteleByIds(taskItemBatchIds);
    }

    public IndexListing getIndexListing(long taskItemId) throws MException {

        IndexListing mIndexListing = new IndexListing();
        TaskItem mTaskItem = taskItemDao.findById(taskItemId);
        AtsServiceIndexRelation mAtsServiceIndexRelation = mIServiceKeyService.findAtsServiceIndexRelation(
                mTaskItem.getServType(), mTaskItem.getTaskKeyCode());
        mIndexListing.setServiceName(mAtsServiceIndexRelation.getServiceName());
        mIndexListing.setTestKeyName(mAtsServiceIndexRelation.getTestKeyName());
        mIndexListing.setAllExecutionNumber(mITaskItemBatchDao.getAllExecutionNumber(taskItemId));
        mIndexListing.setExecutionNumber(mITaskItemBatchDao.getExecutionNumber(taskItemId));
        mIndexListing.setSucceedNumber(mITaskItemBatchDao.getSucceedNumber(taskItemId));
        mIndexListing.setLoseNumber(mITaskItemBatchDao.getLoseNumber(taskItemId));
        return mIndexListing;
    }

    // /**
    // * (non-Javadoc)
    // *
    // * @see com.aspire.android.test.task.service.ITaskService#getQueryResults(java.lang.String, java.lang.String,
    // * java.lang.String, java.lang.String)
    // */
    // public List<QueryResults> getQueryResults(String taskName, String testKeyName, String status, String upstatus)
    // throws MException {
    // List<QueryResults> mQueryResultss = new ArrayList<QueryResults>();
    // if (taskName != null) {
    // List<Task> listTasks = findTask(taskName);
    // for (Task task : listTasks) {
    // List<TaskItem> maskItem = getAllTaskItem(task.getId());
    // for (TaskItem taskItem : maskItem) {
    // AtsServiceIndexRelation mAtsServiceIndexRelation = mIServiceKeyService.findAtsServiceIndexRelation(
    // taskItem.getServType(), taskItem.getTaskKeyCode());
    // List<TaskItemBatch> list = mITaskItemBatchDao.findListTaskItemBatch(taskItem.getId(), status,
    // upstatus);
    // for (TaskItemBatch taskItemBatch : list) {
    // QueryResults mQueryResults = new QueryResults();
    // mQueryResults.setTaskName(task.getTaskName());
    // mQueryResults.setTestKeyName(mAtsServiceIndexRelation.getTestKeyName());
    // mQueryResults.setStatus(taskItemBatch.getStatus());
    // mQueryResults.setTaskItemBatchId(taskItemBatch.getId());
    // mQueryResults.setTestValue(taskItemBatch.getTestValue());
    // mQueryResults.setUpStatus(taskItemBatch.getUpstatus());
    // mQueryResults.setStartTime(taskItemBatch.getStartTime());
    // mQueryResults.setEndTime(taskItemBatch.getEndTime());
    // mQueryResultss.add(mQueryResults);
    // }
    // }
    // }
    // }
    //
    // if (mQueryResultss.size() == 0 && !testKeyName.trim().equals("")) {
    // List<AtsServiceIndexRelation> mAtsServiceIndexRelation = mIServiceKeyService
    // .findAtsServiceIndexRelation(testKeyName);
    // for (AtsServiceIndexRelation atsServiceIndexRelation : mAtsServiceIndexRelation) {
    // List<TaskItem> mTaskItem = getAllTaskItem(atsServiceIndexRelation.getTestKeyCode(),
    // atsServiceIndexRelation.getServiceCode());
    // for (TaskItem taskItem : mTaskItem) {
    // List<TaskItemBatch> list = mITaskItemBatchDao.findListTaskItemBatch(taskItem.getId(), status,
    // upstatus);
    // for (TaskItemBatch taskItemBatch : list) {
    // QueryResults mQueryResults = new QueryResults();
    // mQueryResults.setTaskName(getTask(taskItem.getTaskId()).getTaskName());
    // mQueryResults.setTestKeyName(atsServiceIndexRelation.getTestKeyName());
    // mQueryResults.setStatus(taskItemBatch.getStatus());
    // mQueryResults.setTaskItemBatchId(taskItemBatch.getId());
    // mQueryResults.setTestValue(taskItemBatch.getTestValue());
    // mQueryResults.setUpStatus(taskItemBatch.getUpstatus());
    // mQueryResults.setStartTime(taskItemBatch.getStartTime());
    // mQueryResults.setEndTime(taskItemBatch.getEndTime());
    // mQueryResultss.add(mQueryResults);
    // }
    // }
    // }
    // }
    // return mQueryResultss;
    // }

    /**
     * 
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.ITaskService#getUpload(int)
     */
    public List<Upload> getUpload(int status) throws MException {
        return mIUploadDao.getUpload(status);
    }

    /**
     * 
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.ITaskService#getTaskTaskItem(long)
     */
    public TaskTaskItem getTaskTaskItem(long taskId) throws MException {
        TaskTaskItem mTaskTaskItem = new TaskTaskItem();
        mTaskTaskItem.setTask(taskDao.findById(taskId));
        mTaskTaskItem.setListTaskItem(taskItemDao.getTaskItem(taskId));
        return mTaskTaskItem;
    }

    /**
     * 
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.task.service.ITaskService#judgeUploadResult()
     */
    public boolean judgeUploadResult() throws MException {
        String iterationType = null;
        List<Task> listTask = taskDao.findAll();
        for (Task task : listTask) {

            if (task.getExeType().toString().equals("1") && !task.getIterationType().toString().equals("0")) {
                if (task.getIterationType().toString().equals("1")) {
                    iterationType = year + "_" + month + "_" + day;
                    long num = findMAXTaskBatchIterationTypeID(task.getId(), iterationType);
                    if (num >= task.getIterationNum() && task.getJudgeResult() == 0) {
                        task.setJudgeResult(1);
                        taskDao.update(task);
                        return true;
                    } else {
                        if (hour < hourPreferences.getInt(SharedPrefConstants.TASK_EXECUTE_OR_UPDATE_TIME, 0)) {
                            Editor editor = hourPreferences.edit();
                            editor.putInt(SharedPrefConstants.TASK_EXECUTE_OR_UPDATE_TIME, updateTime);
                            editor.commit();
                            return true;
                        } else if (hour > endTime) {
                            Editor editor = hourPreferences.edit();
                            editor.putInt(SharedPrefConstants.TASK_EXECUTE_OR_UPDATE_TIME, executeTime);
                            editor.commit();
                        }
                    }

                } else if (task.getIterationType().toString().equals("2")) {
                    iterationType = year + "/" + month + "/" + week;
                    long num = findMAXTaskBatchIterationTypeID(task.getId(), iterationType);
                    if (num >= task.getIterationNum() && task.getJudgeResult() == 0) {
                        task.setJudgeResult(1);
                        taskDao.update(task);
                        return true;
                    } else {
                        if (hour < hourPreferences.getInt(SharedPrefConstants.TASK_EXECUTE_OR_UPDATE_TIME, 0)) {
                            Editor editor = hourPreferences.edit();
                            editor.putInt(SharedPrefConstants.TASK_EXECUTE_OR_UPDATE_TIME, updateTime);
                            editor.commit();
                            return true;
                        } else if (hour > endTime) {
                            Editor editor = hourPreferences.edit();
                            editor.putInt(SharedPrefConstants.TASK_EXECUTE_OR_UPDATE_TIME, executeTime);
                            editor.commit();
                        }
                    }
                } else if (task.getIterationType().toString().equals("3")) {
                    iterationType = year + "_" + month;
                    long num = findMAXTaskBatchIterationTypeID(task.getId(), iterationType);
                    if (num >= task.getIterationNum() && task.getJudgeResult() == 0) {
                        task.setJudgeResult(1);
                        taskDao.update(task);
                        return true;
                    } else {
                        if (hour < hourPreferences.getInt(SharedPrefConstants.TASK_EXECUTE_OR_UPDATE_TIME, 0)) {
                            Editor editor = hourPreferences.edit();
                            editor.putInt(SharedPrefConstants.TASK_EXECUTE_OR_UPDATE_TIME, updateTime);
                            editor.commit();
                            return true;
                        } else if (hour > endTime) {
                            Editor editor = hourPreferences.edit();
                            editor.putInt(SharedPrefConstants.TASK_EXECUTE_OR_UPDATE_TIME, executeTime);
                            editor.commit();
                        }
                    }
                }
            } else if (task.getExeType().toString().equals("2") || task.getIterationType().toString().equals("0")) {
                iterationType = "20";
                long num = findMAXTaskBatchIterationTypeID(task.getId(), iterationType);
                if (num >= task.getIterationNum() && task.getJudgeResult() == 0) {
                    task.setJudgeResult(1);
                    taskDao.update(task);
                    return true;
                } else {
                    if (hour < hourPreferences.getInt(SharedPrefConstants.TASK_EXECUTE_OR_UPDATE_TIME, 0)) {
                        Editor editor = hourPreferences.edit();
                        editor.putInt(SharedPrefConstants.TASK_EXECUTE_OR_UPDATE_TIME, updateTime);
                        editor.commit();
                        return true;
                    } else if (hour > endTime) {
                        Editor editor = hourPreferences.edit();
                        editor.putInt(SharedPrefConstants.TASK_EXECUTE_OR_UPDATE_TIME, executeTime);
                        editor.commit();
                    }
                }
            }
        }
        return false;
    }

    public int getQueryResults(String taskName, String testKeyName, String status, String upstatus, String errCode,
            String startTime, String endTime, long offset, long num, List<QueryResults> mQueryResultss)
            throws MException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        long now = new Date().getTime();
        if (taskName == null) {
            taskName = "";
        }
        if (testKeyName == null) {
            testKeyName = "";
        }
        if (status == null || status.trim().equals("")) {
            runLogger.error(getClass(), "status is not illegal");
            return 0;
        }
        List<Task> listTasks = findTask(taskName);
        List<AtsServiceIndexRelation> mAtsServiceIndexRelation = mIServiceKeyService
                .findAtsServiceIndexRelation(testKeyName);
        if (listTasks != null && listTasks.size() > 0) {
            List<Long> taskIds = new ArrayList<Long>();
            List<String> serviceCodes = new ArrayList<String>();
            List<String> testKeyCodes = new ArrayList<String>();
            for (Task task : listTasks) {
                taskIds.add(task.getId());
            }
            for (AtsServiceIndexRelation serviceKeyRelation : mAtsServiceIndexRelation) {
                serviceCodes.add(serviceKeyRelation.getServiceCode());
                testKeyCodes.add(serviceKeyRelation.getTestKeyCode());
            }
            List<TaskItem> taskItems = taskItemDao.getAllTaskItem(taskIds, serviceCodes, testKeyCodes);
            if (taskItems != null) {
                List<Long> taskItemIds = new ArrayList<Long>();
                for (TaskItem taskItem : taskItems) {
                    taskItemIds.add(taskItem.getId());
                }
                Date start = null;
                Date end = null;
                try {
                    if (startTime != null && endTime != null) {
                        start = dateFormat.parse(startTime);
                        end = dateFormat.parse(endTime);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long time1 = new Date().getTime();
                runLogger.debug(getClass(), "iv1 = " + (time1 - now) * 1000);
                List<TaskItemBatch> list = mITaskItemBatchDao.findListTaskItemBatch(taskItemIds, status, upstatus,
                        errCode, start, end, offset, num);
                int count = mITaskItemBatchDao.findListTaskItemBatchCount(taskItemIds, status, upstatus, errCode,
                        start, end);
                for (TaskItemBatch taskItemBatch : list) {
                    QueryResults mQueryResults = new QueryResults();
                    mQueryResults.setTaskName(getTask(taskItemBatch.getTaskId()).getTaskName());
                    mQueryResults.setTestKeyName(mIServiceKeyService.findAtsServiceIndexRelation(
                            getTaskItem(taskItemBatch.getTaskItemId()).getServType(),
                            getTaskItem(taskItemBatch.getTaskItemId()).getTaskKeyCode()).getTestKeyName());
                    mQueryResults.setStatus(taskItemBatch.getStatus());
                    mQueryResults.setTaskItemBatchId(taskItemBatch.getId());
                    mQueryResults.setTestValue(taskItemBatch.getTestValue());
                    mQueryResults.setUpStatus(taskItemBatch.getUpstatus());
                    mQueryResults.setStartTime(taskItemBatch.getStartTime());
                    mQueryResults.setEndTime(taskItemBatch.getEndTime());
                    mQueryResultss.add(mQueryResults);
                }
                long time2 = new Date().getTime();
                runLogger.debug(getClass(), "iv2 = " + (time2 - time1) * 1000);
                return count;
            }
        }
        return 0;
    }
}
