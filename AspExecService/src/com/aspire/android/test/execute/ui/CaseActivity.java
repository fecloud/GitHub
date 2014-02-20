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
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.aspire.android.common.exception.MException;
import com.aspire.android.test.execute.R;
import com.aspire.android.test.script.entity.CaseScript;
import com.aspire.android.test.script.service.IScriptService;
import com.google.inject.Inject;

/**
 * The class <code>EventActivity</code>
 * 
 * @author andyshen
 * @version 1.0
 */
public class CaseActivity extends RoboActivity {
    private ListView listView = null;
    private List<Map<String, Object>> listData = null;
    private SimpleAdapter adapter;
    @Inject
    private IScriptService scriptService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.case_listview);

        // 调用setTaskListData添加数据的函数
        setTaskListData();
        // 获取存放Tsak的liveView控件
        listView = (ListView) this.findViewById(R.id.list_task);
        // 配置listview适配器
        adapter = new SimpleAdapter(getApplicationContext(), listData, R.layout.task_item, new String[] { "text",
                "content" }, new int[] { R.id.task_title, R.id.task_content });
        listView.setAdapter(adapter);
        // 设置listview的监听事件
        listView.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int postion, long id) {
                // 预留事件接口
            }

        });
    }

    // 设置listview数据
    public void setTaskListData() {
        listData = new ArrayList<Map<String, Object>>();
        List<CaseScript> casescriptlist = null;
        try {
            casescriptlist = scriptService.findAllCaseScript();

        } catch (MException e) {
            e.printStackTrace();
        }
        for (CaseScript caseScript : casescriptlist) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("text", "案例名: " + caseScript.getCaseName());
            map.put("content", "指标编码：" + caseScript.getTestKeyCode() + "\n" + "业务编码" + caseScript.getServiceCode());
            listData.add(map);
        }

    }

}
