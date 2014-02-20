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
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.aspire.android.common.db.DatabaseHelper;
import com.aspire.android.common.db.DatabaseManager;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.execute.R;
import com.aspire.android.test.task.entity.TaskBathItem;
import com.aspire.android.test.task.service.ITaskService;
import com.google.inject.Inject;

/**
 * The class <code>TaskIndexActivity</code>
 * 
 * @author andyshen
 * @version 1.0
 */
public class TaskIndexActivity extends RoboActivity {
    private ListView listView = null;
    private List<Map<String, Object>> listData = null;
    private SimpleAdapter adapter;
    private long taskBatchId;
    private long taskId;
    List<TaskBathItem> taskbathitem = null;
    final String[] item = new String[] { "重跑", "删除" };
    private int clickWhich;

    DatabaseHelper mTestDatabaseHelper ;
    @Inject
    ITaskService taskService ;
    TaskBathItem taskBathItem ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_index_listview);
        mTestDatabaseHelper = DatabaseManager.getDBHelper();
//        taskBathItem =new TaskBathItem();
        Intent taskbatchid_intent = getIntent();
        Bundle bundle = taskbatchid_intent.getExtras();
        taskBatchId = bundle.getLong("taskBatchId");
        taskId = bundle.getLong("taskid");
        // 调用setTaskListData添加数据的函数
        setTaskListData();
        
        // 获取存放Tsak的liveView控件
        listView = (ListView) this.findViewById(R.id.task_index);
        // 配置listview适配器

        adapter = new SimpleAdapter(getApplicationContext(), listData, R.layout.task_index_item, new String[] {
                "index_name", "index_code", "index_result" }, new int[] { R.id.index_name, R.id.index_code,
                R.id.index_result });
        listView.setAdapter(adapter);
        // 设置listview的监听事件

        listView.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int postion, long id) {
                // 添加重跑和删除事件
                showDialog(1);
            }

        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog;
        Builder builder = new AlertDialog.Builder(TaskGroupActivity.group).setTitle("设置重跑和删除事件")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        clickWhich = which;
                        indexEvent();
                       
                        dialog.dismiss();
                    }

                });
        dialog = builder.create();
        return dialog;
    }

    /**
     * 设置listview数据
     */
    public void setTaskListData() {
        listData = new ArrayList<Map<String, Object>>();
        try {
            taskbathitem = taskService.findTaskBathItem(taskId, taskBatchId);

        } catch (MException e) {
            e.printStackTrace();
        }
        
        for (TaskBathItem taskBathItem: taskbathitem) {
            this.taskBathItem = taskBathItem;
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("index_name", "指标名: " + taskBathItem.getTestKeyName());
            map.put("index_code", "指标代码：" + taskBathItem.getTestKeyCode());
            map.put("index_result", "结果：" + taskBathItem.getResult());
            listData.add(map);
            
        }
        

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent intent = new Intent(this, TaskExecutionActivity.class);
        Window w = TaskGroupActivity.group.getLocalActivityManager().startActivity("TaskExecutionActivity", intent);
        View view = w.getDecorView();
        /**
         * 把View添加到ActivityGroup中
         */
        TaskGroupActivity.group.setContentView(view);
        return true;
    }

    /**
     * 任务指标重跑和删除方法
     */
    public void indexEvent() {

         

        if (clickWhich == 0) {
            try {
                taskService.taskReiteration(taskBathItem.getTaskItemBatchId());
            } catch (MException e) {
                Toast.makeText(getApplicationContext(), "重跑失败", 1).show();
            }
        } else {

            try {
                taskService.deleteTaskCase(taskBathItem.getTaskItemBatchId());
                
            } catch (MException e) {
                Toast.makeText(getApplicationContext(), "删除失败", 1).show();
            }
        }

    }

}
