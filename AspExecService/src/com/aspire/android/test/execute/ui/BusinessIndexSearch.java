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
import com.aspire.android.test.servicekey.entity.AtsServiceIndexRelation;
import com.aspire.android.test.servicekey.service.IServiceKeyService;
import com.google.inject.Inject;

/**
 * The class <code>TaskSearch</code>
 * 
 * @author andyshen
 * @version 1.0
 */
public class BusinessIndexSearch extends RoboActivity implements OnPanelListener{
	private Panel topPanel;
    private EditText businessContent, indexContent = null;
    private Button searchButton = null;
    private SimpleAdapter adapter;
    private ListView businessIndexSearchList = null;
    private List<Map<String, String>> businessIndexData = null;
    private List<AtsServiceIndexRelation> atsserviceindexrelationList = null;
    @Inject
    private IServiceKeyService iServiceKeyService ;

    /**
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_index_search);
        Panel panel;
		topPanel = panel = (Panel) findViewById(R.id.topPanel);
		panel.setOnPanelListener(this);
		panel.setInterpolator(new BounceInterpolator(Type.OUT));
        businessContent = (EditText) findViewById(R.id.businessContent);
        indexContent = (EditText) findViewById(R.id.indexContent);
        searchButton = (Button) findViewById(R.id.searchButton);
        businessIndexSearchList = (ListView) findViewById(R.id.businessIndexSearchList);
        searchAllBusinessIndex();
        /**
         * 配置listview适配器
         */
        adapter = new SimpleAdapter(getApplicationContext(), businessIndexData, R.layout.task_item, new String[] {
                "title", "content","business_content"  }, new int[] { R.id.task_title, R.id.task_content ,R.id.business_content});
        businessIndexSearchList.setAdapter(adapter);

        searchButton.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                String business_Content = businessContent.getText().toString();
                String index_Content = indexContent.getText().toString();
                /**
                 * 判断输入的内容是否为空，为空则提示用户输入内容不能为空，如果不为空则继续查询。
                 */
                if (!business_Content.trim().equals("") && !index_Content.trim().equals("")) {

                    businessIndexData = new ArrayList<Map<String, String>>();
                    try {
                        atsserviceindexrelationList = iServiceKeyService.findLsitAtsServiceIndexRelation(
                                business_Content, index_Content);
                    } catch (MException e) {
                        e.printStackTrace();
                    }
                    /**
                     * 判断查询的数据是否存在，如果存在迭代在listview中显示，不存在则提示用户没有查询的数据。
                     */
                    if (atsserviceindexrelationList != null || atsserviceindexrelationList.size() > 0) {
                        for (AtsServiceIndexRelation atsserviceindexrelation : atsserviceindexrelationList) {
                            Map<String, String> map = new HashMap<String, String>();
                            map.put("title", "业务类型名称: " + atsserviceindexrelation.getServiceName());
                            map.put("business_content", "指标名称:" + atsserviceindexrelation.getTestKeyName());
                            map.put("content", "指标编码：" + atsserviceindexrelation.getTestKeyCode());
                            adapter = new SimpleAdapter(getApplicationContext(), businessIndexData, R.layout.task_item,
                                    new String[] { "title", "content","business_content" },
                                    new int[] { R.id.task_title, R.id.task_content ,R.id.business_content});
                            businessIndexSearchList.setAdapter(adapter);
                            businessIndexData.add(map);

                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "没有查询的数据！", 1).show();
                    }

                } else {

                    Toast.makeText(getApplicationContext(), "输入数据不能为空！", 0).show();
                }

            }
        });

        businessIndexSearchList.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int postion, long id) {
                AtsServiceIndexRelation atsserviceindexrelation = atsserviceindexrelationList.get(postion);
                try {
                    atsserviceindexrelationList = iServiceKeyService.findAllAtsServiceIndexRelation();
                } catch (MException e) {
                    e.printStackTrace();
                }
                AlertDialog.Builder builder = new Builder(BusinessIndexSearch.this);
                builder.setMessage("业务类型名称: " + atsserviceindexrelation.getServiceName() + "\n"+ "\n" + "指标名称: "
                        + atsserviceindexrelation.getTestKeyName() + "\n" + "\n" + "指标编码："
                        + atsserviceindexrelation.getTestKeyCode() + "\n"+ "\n" + "业务编码："
                        + atsserviceindexrelation.getServiceCode() + "\n"+ "\n"+"状态:"+switchStatus(atsserviceindexrelation.getStatus()));
                builder.setTitle("指标详情");
                builder.setPositiveButton(getResources().getString(R.string.OK), null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }
    public String switchStatus(String status) {
        int type = Integer.parseInt(status);
        switch (type) {
        case 0:
            return "无效";
        case 1:
            return "有效";
        }
        return null;
    }

    /**
     * 查询出所有的指标
     */
    private void searchAllBusinessIndex() {
        businessIndexData = new ArrayList<Map<String, String>>();
        try {
            atsserviceindexrelationList = iServiceKeyService.findAllAtsServiceIndexRelation();
        } catch (MException e) {
            e.printStackTrace();
        }
        for (AtsServiceIndexRelation atsserviceindexrelation : atsserviceindexrelationList) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("title", "业务类型名称: " + atsserviceindexrelation.getServiceName());
            map.put("business_content", "指标名称:" + atsserviceindexrelation.getTestKeyName());
            map.put("content", "指标编码：" + atsserviceindexrelation.getTestKeyCode());
            businessIndexData.add(map);
        }
    }
	public void onPanelClosed(Panel panel) {
		// TODO Auto-generated method stub
		
	}
	public void onPanelOpened(Panel panel) {
		// TODO Auto-generated method stub
		
	}
}
