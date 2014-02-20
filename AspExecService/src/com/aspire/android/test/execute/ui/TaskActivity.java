/**
 * @(#) TaskActivity.java Created on 2012-6-18
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import roboguice.activity.RoboActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.aspire.android.common.exception.MException;
import com.aspire.android.test.execute.R;
import com.aspire.android.test.task.entity.Task;
import com.aspire.android.test.task.service.ITaskService;
import com.google.inject.Inject;

/**
 * The class <code>TaskActivity</code>
 * 
 * @author andyshen
 * @version 1.0
 */
public class TaskActivity extends RoboActivity {
    private ListView listView = null;
    private List<Map<String, String>> listData = null;
    private SimpleAdapter adapter;
    private List<Task> taskList = null;
    @Inject
    private ITaskService taskService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task);
        // 调用setTaskListData添加数据的函数
        setTaskListData();
        // 获取存放Tsak的liveView控件
        listView = (ListView) this.findViewById(R.id.list_task);
        // 配置listview适配器
        adapter = new SimpleAdapter(getApplicationContext(), listData, R.layout.task_item, new String[] { "task_title",
                "task_content" }, new int[] { R.id.task_title, R.id.task_content });
        listView.setAdapter(adapter);
        // 设置listview的监听事件
        listView.setOnItemClickListener(new OnItemClickListener() {
            // 根据postion来判断选择的任务
            public void onItemClick(AdapterView<?> parent, View view, int postion, long id) {

                Task task = taskList.get(postion);
                Intent intent = new Intent(TaskActivity.this, TaskExecutionActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("taskId", task.getId());
                intent.putExtra("taskName", task.getTaskName());
                // 把一个Activity转换成一个View
                Window w = TaskGroupActivity.group.getLocalActivityManager().startActivity("TaskExecutionActivity",
                        intent);
                View group_view = w.getDecorView();
                // 把View添加到ActivityGroup中
                TaskGroupActivity.group.setContentView(group_view);

            }

        });
    }

    // 设置listview数据
    public void setTaskListData() {
        listData = new ArrayList<Map<String, String>>();

        try {
            taskList = taskService.allOrderbyTask();

        } catch (MException e) {
            e.printStackTrace();
        }

        for (Task task : taskList) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("task_title", "任务名: " + task.getTaskName());

            if (task.getExeType().toString().equals("1")) {
                String exeType_one = "按时执行";
                map.put("task_content", "调度方式：" + exeType_one);
            } else if (task.getExeType().toString().equals("2")) {
                String exeType_two = "按次执行";
                map.put("task_content", "调度方式：" + exeType_two);
            }

            listData.add(map);
        }

    }

}
