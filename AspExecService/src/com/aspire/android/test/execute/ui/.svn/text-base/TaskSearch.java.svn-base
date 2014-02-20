/**
 * @(#) TaskSearch.java Created on 2012-7-24
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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.aspire.android.common.exception.MException;
import com.aspire.android.test.execute.R;
import com.aspire.android.test.execute.interpolator.BounceInterpolator;
import com.aspire.android.test.execute.interpolator.EasingType.Type;
import com.aspire.android.test.execute.ui.Panel.OnPanelListener;
import com.aspire.android.test.task.entity.Task;
import com.aspire.android.test.task.service.ITaskService;
import com.google.inject.Inject;

/**
 * The class <code>TaskSearch</code>
 * 
 * @author andyshen
 * @version 1.0
 */
public class TaskSearch extends RoboActivity implements OnPanelListener{
	private Panel topPanel;
    private EditText taskEditText = null;
    private Button taskButton = null;
    private ListView taskListView = null;

    @Inject
    private ITaskService itaskService;
    private List<Task> taskList = null;
    private List<Map<String, String>> listData = null;
    private SimpleAdapter adapter;

    /**
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_search);
        Panel panel;
		topPanel = panel = (Panel) findViewById(R.id.topPanel);
		panel.setOnPanelListener(this);
		panel.setInterpolator(new BounceInterpolator(Type.OUT));
        /**
         * 调用向listview添加数据方法，查询出所有的任务
         * 
         */
        setTaskListData();
        /**
         * 根据ID号得到各个控件
         */
        taskEditText = (EditText) findViewById(R.id.searchContent);
        taskButton = (Button) findViewById(R.id.searchButton);
        taskListView = (ListView) findViewById(R.id.taskSearchList);

        /**
         * 配置listview适配器
         */

        adapter = new SimpleAdapter(getApplicationContext(), listData, R.layout.task_item, new String[] { "task_title",
                "task_content", "business_content" }, new int[] { R.id.task_title, R.id.task_content,
                R.id.business_content });
        taskListView.setAdapter(adapter);

        /**
         * 添加查询按钮事件，查询指定的数据事件
         */
        taskButton.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                /**
                 * 得到要收索的内容
                 */
                String taskContent = taskEditText.getText().toString();
                /**
                 * 判断输入的内容是否为空，为空则提示用户输入内容不能为空，如果不为空则继续查询。
                 */
                if (!taskContent.trim().equals("")) {

                    listData = new ArrayList<Map<String, String>>();
                    try {
                        taskList = itaskService.findTask(taskContent);

                    } catch (MException e) {
                        e.printStackTrace();
                    }
                    /**
                     * 判断查询的数据是否存在，如果存在迭代在listview中显示，不存在则提示用户没有查询的数据。
                     */
                    if (taskList != null && taskList.size() > 0) {
                        for (Task task : taskList) {
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("task_title", "任务名: " + task.getTaskName());
                            map.put("task_content", "任务编号: " + task.getTaskCode());
                          //  map.put("business_content", "该任务指标数：" + task.getId());
                            listData.add(map);

                        }
                        adapter = new SimpleAdapter(getApplicationContext(), listData, R.layout.task_item,
                                new String[] { "task_title", "task_content" ,"business_content"}, new int[] { R.id.task_title,
                                        R.id.task_content, R.id.business_content});
                        taskListView.setAdapter(adapter);
                    } else {
                        Toast.makeText(getApplicationContext(), "没有查询的数据！", 1).show();
                    }

                } else {

                    Toast.makeText(getApplicationContext(), "输入数据不能为空！", 1).show();
                }

            }

        });
        /**
         * 设置listview的点击事件，查看单个任务详情；
         */
        taskListView.setOnItemClickListener(new OnItemClickListener() {

            /**
             * (non-Javadoc)
             * 
             * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView,
             *      android.view.View, int, long)
             */
            public void onItemClick(AdapterView<?> parent, View view, int postion, long id) {
                Task task = taskList.get(postion);
                Intent intent = new Intent(TaskSearch.this, SearchContents.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("taskId", task.getId());
                startActivity(intent);
            }

        });

    }

    // 查所有任务数据
    public void setTaskListData() {
        listData = new ArrayList<Map<String, String>>();
        try {
            taskList = itaskService.allOrderbyTask();

        } catch (MException e) {
            e.printStackTrace();
        }

        for (Task task : taskList) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("task_title", "任务名: " + task.getTaskName());
            map.put("task_content", "任务编号: " + task.getTaskCode());
       //     map.put("business_content", "该任务指标数：" + task.getId());
            listData.add(map);
        }

    }

	public void onPanelClosed(Panel panel) {
		// TODO Auto-generated method stub
		
	}

	public void onPanelOpened(Panel panel) {
		// TODO Auto-generated method stub
		
	}

}
