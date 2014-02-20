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
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.aspire.android.common.exception.MException;
import com.aspire.android.test.PreferencesManager;
import com.aspire.android.test.execute.R;
import com.aspire.android.test.execute.interpolator.BounceInterpolator;
import com.aspire.android.test.execute.interpolator.EasingType.Type;
import com.aspire.android.test.execute.ui.Panel.OnPanelListener;
import com.aspire.android.test.task.entity.Upload;
import com.aspire.android.test.task.service.ITaskService;
import com.google.inject.Inject;

/**
 * The class <code>TaskSearch</code>
 * 
 * @author andyshen
 * @version 1.0
 */
public class VerifySearch extends RoboActivity implements OnPanelListener{
    private Spinner verify_spinner = null;
    private Button resultsearchButton = null;
    private ArrayAdapter<String> verify_adapter = null;
    private ListView verify_List = null;
    private static final String[] verify = { "失败", "成功", "待反馈", "全部" };

    @Inject
    private ITaskService itaskService;
    private List<Upload> listUpload = null;
    private List<Map<String, String>> listData = new ArrayList<Map<String, String>>();
    private SimpleAdapter adapter;
    private SharedPreferences prefs;
    private PreferencesManager preferencesManager = PreferencesManager.getInstance();
    private Panel topPanel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_search);
        Panel panel;
		topPanel = panel = (Panel) findViewById(R.id.topPanel);
		panel.setOnPanelListener(this);
		panel.setInterpolator(new BounceInterpolator(Type.OUT));
        resultsearchButton = (Button) findViewById(R.id.resultsearchButton);
        verify_spinner = (Spinner) findViewById(R.id.verify_spinner);
        verify_List = (ListView) findViewById(R.id.verify_List);
        // 将Spinner里面的可选择内容通过ArrayAdapter连接起来
        verify_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, verify);
        verify_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 为对话框设置标题
        verify_spinner.setPrompt("上报结果类型");

        // 为Spinner设置适配器
        verify_spinner.setAdapter(verify_adapter);

        resultsearchButton.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                prefs = preferencesManager.getPreferences();

                // 获取输入值
                int verifys = verify_spinner.getSelectedItemPosition();

                try {
                    listUpload = itaskService.getUpload(verifys);
                } catch (MException e) {
                    e.printStackTrace();
                }

                if (listUpload != null && listUpload.size() > 0) {

                    for (Upload upload : listUpload) {
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("device_type", "设备类型: " + prefs.getString("set_terminal", "设备类型没有配置！"));
                        map.put("device_num", "设备编码: " + prefs.getString("set_IMEI", null));
                        map.put("business_content", "上报时间：" + upload.getUploadTime());
                        listData.add(map);
                    }

                    adapter = new SimpleAdapter(getApplicationContext(), listData, R.layout.task_item, new String[] {
                            "device_type", "device_num", "business_content" }, new int[] { R.id.task_title,
                            R.id.task_content, R.id.business_content });
                    verify_List.setAdapter(adapter);
                } else {
                    Toast.makeText(getApplicationContext(), "没有查询的数据！", Toast.LENGTH_LONG).show();
                }

            }

        });
        verify_List.setOnItemClickListener(new OnItemClickListener() {

            // 获取输入值
            int verifys = verify_spinner.getSelectedItemPosition();

            public void onItemClick(AdapterView<?> parent, View view, int postion, long id) {
                prefs = preferencesManager.getPreferences();

                Upload upload = listUpload.get(postion);

                try {
                    listUpload = itaskService.getUpload(verifys);
                } catch (MException e) {
                    e.printStackTrace();
                }
                AlertDialog.Builder builder = new Builder(VerifySearch.this);
                builder.setTitle("确认结果查询详情");
                builder.setMessage("设备类型: " + prefs.getString("set_terminal", "设备类型没有配置！") + "\n"+ "\n" + "设备编码:"
                        + prefs.getString("set_IMEI", null) + "\n" + "\n"+ "上报结果包名:" + upload.getResponseFile() + "\n"+ "\n"
                        + "上报时间:" + upload.getUpDate() + "\n" + "\n"+ "确认结果包名:" + upload.getResponseFile() + "\n"+ "\n" + "获取时间:"
                        + upload.getResultDate() + "\n"+ "\n" + "处理状态：" +upload.getStatus() + "\n"

                );
                builder.setPositiveButton(getResources().getString(R.string.OK), null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
        
        
    }
    public String switchUpStatus(String upStatus) {
        int type = Integer.parseInt(upStatus);

        switch (type) {
        case 0:
            return "待上报";
        case 1:
            return "已上报";
        }
        return null;
    }
  
	public void onPanelClosed(Panel panel) {
		// TODO Auto-generated method stub
		
	}
	public void onPanelOpened(Panel panel) {
		// TODO Auto-generated method stub
		
	}

}
