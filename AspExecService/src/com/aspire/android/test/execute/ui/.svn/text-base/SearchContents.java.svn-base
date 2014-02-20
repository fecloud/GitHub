/**
 * @(#) SearchContents.java Created on 2012-7-30
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute.ui;

import java.text.SimpleDateFormat;

import roboguice.activity.RoboActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aspire.android.common.exception.MException;
import com.aspire.android.test.execute.R;
import com.aspire.android.test.task.entity.Task;
import com.aspire.android.test.task.service.ITaskService;
import com.google.inject.Inject;

/**
 * The class <code>SearchContents 该类用于显示查询出的tasksearch中单个任务详情！</code>
 * 
 * @author andyshen
 * @version 1.0
 */

public class SearchContents extends RoboActivity {
    private TextView contentsText = null;
    private TextView contentsText2 = null;
    private long taskId;
    private Task task = null;
    private Button index_button;
    @Inject
    private ITaskService taskService;
    // private Button button_back;
    // 获取ListView
    ListView index_view = null;

    /**
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contents);
        index_view = new ListView(this);
        contentsText = (TextView) findViewById(R.id.contents_text);
        contentsText2 = (TextView) findViewById(R.id.contents_text2);
        // button_back = (Button) findViewById(R.id.back_button);
        // button_back = (Button) findViewById(R.id.back_button);
        index_button = (Button) findViewById(R.id.index_button);
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        Intent task_intent = getIntent();
        Bundle bundle = task_intent.getExtras();
        taskId = bundle.getLong("taskId");

        try {

            task = taskService.getTask(taskId);
        } catch (MException e) {
            e.printStackTrace();
        }
        if (task != null) {
            contentsText.setText("拨测任务名称： " + task.getTaskName() + "\n" + "拨测任务编号： " + task.getTaskCode() + "\n"
                    + "任务描述： " + task.getTaskDesc()  + "\n" + "任务类型： "
                    + task.getTaskType() + "\n" + "任务状态： " + switchStatus(String.valueOf(task.getStatus())) + "\n"
                    + "优先级： " + task.getPriority() + "\n" + "任务开始时间： " + task.getExeBeginTime() + "\n" + "任务结束时间： "
                    + task.getExeEndTime() + "\n" );

            contentsText2.setText("执行类型： " + switchExeType(task.getExeType()) + "\n" + "开始执行时间区段： "
                    + dateformat.format(task.getStartDate()) + "\n" + "结束执行时间区段： "
                    + dateformat.format(task.getEndDate()) + "\n" + "循环次数： " + task.getIterationNum() + "\n" + "间隔时间： "
                    + task.getInterval() + "M" + "\n" + "重复类型： " + switchIterationNum(task.getIterationType()) + "\n");
        } else {
            Toast.makeText(getApplicationContext(), "没有显示的数据！", 1).show();
        }

        /**
         * 处理点击button按钮返回事件
         */
        /*
         * button_back.setOnClickListener(new OnClickListener() {
         * 
         * public void onClick(View v) { startActivity(new Intent(getApplicationContext(), TaskSearch.class)); } });
         */

        /**
         * 处理点击button按钮查看指标详情事件
         */
        index_button.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // 获取指标详情
                Intent intent = new Intent(SearchContents.this, IndexList.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("taskId", task.getId());
                startActivity(intent);
            }
        });

    }

    /**
     * 
     * @param status
     * @return
     */
    public String switchStatus(String status) {
        int type = Integer.parseInt(status);
        switch (type) {
        case 1:
            return "无效";
        case 2:
            return "有效";
        }
        return null;
    }

    public String switchExeType(String exetype) {
        int type = Integer.parseInt(exetype);
        switch (type) {
        case 0:
            return "按时执行";
        case 1:
            return "按次执行";
        }
        return null;
    }

    public String switchIterationNum(String iterationnum) {
        int type = Integer.parseInt(iterationnum);
        switch (type) {
        case 0:
            return "不限";
        case 1:
            return "每天";

        case 2:
            return "每周";

        case 3:
            return "每月";
        }
        return null;
    }

}
