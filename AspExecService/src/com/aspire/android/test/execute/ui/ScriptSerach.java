/**
 * @(#) ScriptSerach.java Created on 2012-7-24
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
import com.aspire.android.test.script.entity.AtsScript;
import com.aspire.android.test.script.service.IScriptService;
import com.google.inject.Inject;

/**
 * The class <code>ScriptSerach</code>
 * 
 * @author andyshen
 * @version 1.0
 */
public class ScriptSerach extends RoboActivity implements OnPanelListener{
    private ListView scriptSearchList = null;
    private List<Map<String, String>> listForScriptData = null;
    private List<AtsScript> atsScriptList = null;
    private EditText indexContent, scriptSearchContent;
    private Button scriptSearchButton;
    private String indexName, scriptName = null;
    private SimpleAdapter adapter;
    @Inject
    private IScriptService searchScriptService;
    private Panel topPanel;
    /**
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.script_search);
        Panel panel;
		topPanel = panel = (Panel) findViewById(R.id.topPanel);
		panel.setOnPanelListener(this);
		panel.setInterpolator(new BounceInterpolator(Type.OUT));
        scriptSearchList = (ListView) findViewById(R.id.scriptSearchList);
        indexContent = (EditText) findViewById(R.id.indexContent);
        scriptSearchContent = (EditText) findViewById(R.id.scriptSearchContent);
        scriptSearchButton = (Button) findViewById(R.id.scriptSearchButton);

        /**
         * 调用向listview添加数据方法，查询出所有的脚本
         * 
         */
        searchAllScript();
        /**
         * 配置listview适配器
         */
        adapter = new SimpleAdapter(getApplicationContext(), listForScriptData, R.layout.task_item, new String[] {
                "script_title", "script_content", "business_content" }, new int[] { R.id.task_title, R.id.task_content,
                R.id.business_content });
        scriptSearchList.setAdapter(adapter);

        /**
         * 单击查询按钮事件
         * 
         */
        scriptSearchButton.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // 得到查询的指标名
                indexName = indexContent.getText().toString();
                // 得到查询的脚本名
                scriptName = scriptSearchContent.getText().toString();
                /**
                 * 判断输入的内容是否为空，为空则提示用户输入内容不能为空，如果不为空则继续查询。
                 */
                if (!indexName.trim().equals("") && !scriptName.trim().equals("")) {

                    listForScriptData = new ArrayList<Map<String, String>>();
                    try {
                        atsScriptList = searchScriptService.findListAtsScript(scriptName, indexName);

                    } catch (MException e) {
                        e.printStackTrace();
                    }
                    /**
                     * 判断查询的数据是否存在，如果存在迭代在listview中显示，不存在则提示用户没有查询的数据。
                     */
                    if (atsScriptList != null && atsScriptList.size() > 0) {
                        for (AtsScript atsscript : atsScriptList) {
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("script_title", "脚本名称: " + atsscript.getScriptName());
                            map.put("script_content", "脚本编号: " + atsscript.getId());
                            map.put("business_content", "指标编码: " + atsscript.getTaskKeyCode());
                            listForScriptData.add(map);

                        }
                        adapter = new SimpleAdapter(getApplicationContext(), listForScriptData, R.layout.task_item,
                                new String[] { "script_title", "script_content", "business_content" }, new int[] {
                                        R.id.task_title, R.id.task_content, R.id.business_content });
                        scriptSearchList.setAdapter(adapter);

                    } else {
                        Toast.makeText(getApplicationContext(), "没有查询的数据！", 1).show();
                    }

                } else {

                    Toast.makeText(getApplicationContext(), "输入数据不能为空！", 1).show();
                }

            }
        });

        /**
         * Listview单击查看脚本详情事件
         */

        scriptSearchList.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int postion, long id) {
                AtsScript atsscript = atsScriptList.get(postion);

                try {
                    atsScriptList = searchScriptService.findListAtsScript();
                } catch (MException e) {
                    e.printStackTrace();
                }
                AlertDialog.Builder builder = new Builder(ScriptSerach.this);
                builder.setMessage("脚本名称:" + atsscript.getScriptName() + "\n" + "\n" + "脚本编码：" + atsscript.getId()
                        + "\n" + "\n" + "指标编码：" + atsscript.getTaskKeyCode() + "\n" + "\n" + "脚本版本号:"
                        + atsscript.getScriptVersion() + "\n" + "\n" + "脚本更新时间:" + atsscript.getLastUpdateDate());
                builder.setTitle("脚本详情");
                builder.setPositiveButton(getResources().getString(R.string.OK), null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

        });

    }

    /**
     * 查询出所有的脚本
     */
    private void searchAllScript() {
        listForScriptData = new ArrayList<Map<String, String>>();
        try {
            atsScriptList = searchScriptService.findListAtsScript();
        } catch (MException e) {
            e.printStackTrace();
        }
        for (AtsScript atsscript : atsScriptList) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("script_title", "脚本名称: " + atsscript.getScriptName());
            map.put("script_content", "脚本编号: " + atsscript.getId());
            map.put("business_content", "指标编码: " + atsscript.getTaskKeyCode());
            listForScriptData.add(map);
        }
    }

	public void onPanelClosed(Panel panel) {
		// TODO Auto-generated method stub
		
	}

	public void onPanelOpened(Panel panel) {
		// TODO Auto-generated method stub
		
	}

}
