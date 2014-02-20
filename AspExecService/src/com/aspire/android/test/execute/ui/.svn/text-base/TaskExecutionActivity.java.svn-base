/**
 * @(#) TaskActivity.java Created on 2012-6-18
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import roboguice.activity.RoboActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.aspire.android.common.exception.MException;
import com.aspire.android.test.execute.R;
import com.aspire.android.test.task.entity.TaskBatch;
import com.aspire.android.test.task.service.ITaskService;
import com.google.inject.Inject;

/**
 * The class <code>TaskExecutionActivity</code>
 * 
 * @author andyshen
 * @version 1.0
 */
public class TaskExecutionActivity extends RoboActivity {
    private ListView listView = null;
    private List<Map<String, Object>> listData = null;
    private SimpleAdapter adapter;
    private long taskId;
    private String taskName;
    @Inject
    private ITaskService taskService;
    List<TaskBatch> taskBatchList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_execution_listview);

        Intent task_intent = getIntent();
        Bundle bundle = task_intent.getExtras();
        taskId = bundle.getLong("taskId");
        taskName = bundle.getString("taskName");
        Log.v(getClass().toString(), "taskId=" + taskId);

        // 调用setTaskListData添加数据的函数
        setTaskListData();
        // 获取存放Tsak的liveView控件
        listView = (ListView) this.findViewById(R.id.task_execution);
        // 配置listview适配器
        adapter = new SimpleAdapter(getApplicationContext(), listData, R.layout.task_execution_item, new String[] {
                "task_name", "content" }, new int[] { R.id.task_name, R.id.content });
        listView.setAdapter(adapter);
        // 设置listview的监听事件
        listView.setOnItemClickListener(new OnItemClickListener() {
            // 根据postion来判断选择的任务
            public void onItemClick(AdapterView<?> parent, View view, int postion, long id) {
                TaskBatch taskbatch = taskBatchList.get(postion);
                Intent intent = new Intent(TaskExecutionActivity.this, TaskIndexActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("taskBatchId", taskbatch.getId());
                intent.putExtra("taskid", taskbatch.getTaskId());
                // 把一个Activity转换成一个View
                Window w = TaskGroupActivity.group.getLocalActivityManager().startActivity("TaskIndexActivity", intent);
                View view11 = w.getDecorView();
                // 把View添加到ActivityGroup中
                TaskGroupActivity.group.setContentView(view11);

            }

        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent intent = new Intent(this, TaskActivity.class);
        Window w = TaskGroupActivity.group.getLocalActivityManager().startActivity("TaskActivity", intent);
        View view = w.getDecorView();
        // 把View添加到ActivityGroup中
        TaskGroupActivity.group.setContentView(view);
        return true;
    }

    // 设置listview数据
    public void setTaskListData() {

        listData = new ArrayList<Map<String, Object>>();
        try {
            taskBatchList = taskService.allOrderbyTaskBatch(taskId);

        } catch (MException e) {
            e.printStackTrace();
        }

        for (TaskBatch taskBatch : taskBatchList) {
            Map<String, Object> map = new HashMap<String, Object>();
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
            map.put("task_name", "任务名: " + taskName);

            if (taskBatch.getStartTime() != null && taskBatch.getEndTime() != null) {
                map.put("content", "开始时间为:" + dateformat.format(taskBatch.getStartTime()) + "\n" + "结束时间为:"
                        + dateformat.format(taskBatch.getEndTime()));
            } else if (taskBatch.getStartTime() != null && taskBatch.getEndTime() == null) {

                map.put("content", "开始时间：" + dateformat.format(taskBatch.getStartTime()) + "\n" + "结束时间为空");
            } else if (taskBatch.getStartTime() == null && taskBatch.getEndTime() != null) {
                map.put("content", "开始时间为空" + "\n" + "结束时间:" + dateformat.format(taskBatch.getEndTime()));
            } else {
                map.put("content", "开始时间和结束时间都为空.");
            }
            listData.add(map);
        }

    }

}
