/**
 * @(#) IndexList.java Created on 2012-8-2
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
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.aspire.android.common.exception.MException;
import com.aspire.android.test.execute.R;
import com.aspire.android.test.servicekey.entity.AtsServiceIndexRelation;
import com.aspire.android.test.servicekey.service.IServiceKeyService;
import com.aspire.android.test.task.entity.IndexListing;
import com.aspire.android.test.task.entity.TaskItem;
import com.aspire.android.test.task.service.ITaskService;
import com.google.inject.Inject;

/**
 * The class <code>IndexList</code>
 * 
 * @author andyshen
 * @version 1.0
 */
public class IndexList extends RoboActivity {
    private ListView indexList = null;
    private SimpleAdapter adapter;
    private List<Map<String, String>> listData = null;
    private long taskId;
    private List<TaskItem> indexTaskItem = null;
    @Inject
    private ITaskService itaskService;
    @Inject
    private IServiceKeyService mIServiceKeyService;
    private TaskItem mTaskItem = null;
    private long TsakItemId;
    private IndexListing indexListing = null;

    // private Button index_list_button= null;

    /**
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_list);
        indexList = (ListView) findViewById(R.id.index_list);
        // index_list_button=(Button)findViewById(R.id.index_list_button);
        /**
         * 调用查询接口
         */
        Intent task_intent = getIntent();
        Bundle bundle = task_intent.getExtras();
        taskId = bundle.getLong("taskId");
        try {
            indexTaskItem = itaskService.getAllTaskItem(taskId);
        } catch (MException e) {
            e.printStackTrace();
        }
        listData = new ArrayList<Map<String, String>>();
        for (TaskItem taskItem : indexTaskItem) {
            // taskItem.getTaskKeyCode();

            try {
                AtsServiceIndexRelation mAtsServiceIndexRelation = mIServiceKeyService.findAtsServiceIndexRelation(
                        taskItem.getServType(), taskItem.getTaskKeyCode());
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("index_title", "指标名称:" + mAtsServiceIndexRelation.getTestKeyName());
                map.put("index_num", "指标编码:" + mAtsServiceIndexRelation.getTestKeyCode());
                listData.add(map);

            } catch (MException e) {
                e.printStackTrace();
            }
        }
        adapter = new SimpleAdapter(IndexList.this, listData, R.layout.task_item, new String[] { "index_title",
                "index_num" }, new int[] { R.id.task_title, R.id.task_content });
        indexList.setAdapter(adapter);
        /**
         * 设置指标列表的点击事件，查看单个指标详情
         */
        indexList.setOnItemClickListener(new OnItemClickListener() {

            /**
             * (non-Javadoc)
             * 
             * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView,
             *      android.view.View, int, long)
             */
            public void onItemClick(AdapterView<?> parent, View view, int postion, long id) {
                mTaskItem = indexTaskItem.get(postion);
                TsakItemId = mTaskItem.getId();
                try {
                    indexListing = itaskService.getIndexListing(TsakItemId);
                } catch (MException e) {
                    e.printStackTrace();
                }
                AlertDialog.Builder builder = new Builder(IndexList.this);
                builder.setMessage("业务类型名称:" + indexListing.getServiceName() + "\n" + "\n" + "拨测指标名称:"
                        + indexListing.getTestKeyName() + "\n" + "\n" + "当天执行次数:" + indexListing.getExecutionNumber()
                        + "\n" + "\n" + "共执行次数：" + indexListing.getAllExecutionNumber() + "\n" + "\n" + "成功执行次数："
                        + indexListing.getSucceedNumber() + "\n" + "\n" + "失败执行次数：" + indexListing.getLoseNumber());
                builder.setTitle("指标详情");
                builder.setPositiveButton(getResources().getString(R.string.OK), null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

        });

    }

}
