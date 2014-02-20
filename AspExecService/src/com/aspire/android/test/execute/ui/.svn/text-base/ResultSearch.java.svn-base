/**
 * @(#) TaskSearch.java Created on 2012-7-24
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute.ui;

import java.util.ArrayList;
import java.util.List;

import roboguice.activity.RoboActivity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.aspire.android.common.exception.MException;
import com.aspire.android.test.execute.R;
import com.aspire.android.test.execute.adapter.ResultSearchAdapter;
import com.aspire.android.test.execute.adapter.ResultSearchAdapter.ResultSearchItemContent;
import com.aspire.android.test.execute.interpolator.BounceInterpolator;
import com.aspire.android.test.execute.interpolator.EasingType.Type;
import com.aspire.android.test.execute.ui.Panel.OnPanelListener;
import com.aspire.android.test.result.sync.DoUpload;
import com.aspire.android.test.task.entity.QueryResults;
import com.aspire.android.test.task.entity.TaskBathItem;
import com.aspire.android.test.task.service.ITaskService;
import com.google.inject.Inject;

/**
 * The class <code>TaskSearch</code>
 * 
 * @author andyshen
 * @version 1.0
 */
public class ResultSearch extends RoboActivity implements OnPanelListener{
    /**
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
	private Panel topPanel;
    private EditText searchContent, resultsearchContent = null;
    private Spinner run_status, test_result = null;
    private Button resultsearchButton = null;
    @Inject
    private ITaskService itaskService;
    @Inject
    private DoUpload doUpload;
    private List<QueryResults> taskList = new ArrayList<QueryResults>();
    TaskBathItem taskBathItem;
    /**
     * 显示所用的list
     */
    private List<ResultSearchItemContent> resultListData = null;
    /**
     * 删除所用的list
     */
    private List<ResultSearchItemContent> itemContents = new ArrayList<ResultSearchItemContent>();
    private ResultSearchAdapter adapter;
    private ListView resultListView = null;
    private QueryResults queryResults = null;
    private ArrayAdapter<String> run_adapter, test_adapter = null;
    private static final String[] status = { "失败", "成功", "全部" };
    private static final String[] result = { "待上报", "已上报", "全部" };

    /**
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_search);
        Panel panel;
		topPanel = panel = (Panel) findViewById(R.id.topPanel);
		panel.setOnPanelListener(this);
		panel.setInterpolator(new BounceInterpolator(Type.OUT));
        searchContent = (EditText) findViewById(R.id.searchContent);
        resultsearchContent = (EditText) findViewById(R.id.resultsearchContent);
        run_status = (Spinner) findViewById(R.id.run_status);
        test_result = (Spinner) findViewById(R.id.test_result);
        resultListView = (ListView) findViewById(R.id.resultList);
        resultsearchButton = (Button) findViewById(R.id.resultsearchButton);
        // 将Spinner里面的可选择内容通过ArrayAdapter连接起来
        run_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, status);
        test_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, result);
        // 设置Spinner的样式
        run_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        test_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 为对话框设置标题
        // 也可在XMl文件中通过“android:prompt”设置
        run_status.setPrompt("拨测结果");
        test_result.setPrompt("处理状态");
        // 为Spinner设置适配器
        run_status.setAdapter(run_adapter);
        test_result.setAdapter(test_adapter);
        /**
         * 调用查询接口
         */

        resultsearchButton.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                /**
                 * 得到要收索的内容
                 */
                String resultTaskName = searchContent.getText().toString();
                String resultIndexName = resultsearchContent.getText().toString();
                String status = String.valueOf(run_status.getSelectedItemPosition());
                String upstatus = String.valueOf(test_result.getSelectedItemPosition());
                System.out.println("处理状态:" + upstatus);
                System.out.println("拨测结果状态:" + status);

                resultListData = new ArrayList<ResultSearchItemContent>();
                int count = 0;
                try {
                    count = itaskService.getQueryResults(resultTaskName, resultIndexName, status, upstatus, null, null,
                            null, -1, -1, taskList);

                } catch (MException e) {
                    e.printStackTrace();
                }
                /**
                 * 判断查询的数据是否存在，如果存在迭代在listview中显示，不存在则提示用户没有查询的数据。
                 */
                if (taskList != null && taskList.size() > 0) {
                    for (QueryResults queryresults : taskList) {
                        ResultSearchItemContent itemContent = new ResultSearchItemContent();
                        itemContent.setQueryResults(queryresults);
                        resultListData.add(itemContent);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "没有查询的数据！", Toast.LENGTH_LONG).show();
                }
                adapter = new ResultSearchAdapter(ResultSearch.this);
                adapter.setResultListData(resultListData);
                adapter.setItemContents(itemContents);
                resultListView.setAdapter(adapter);
            }

        });
        /**
         * 查看单个结果详情
         */
        resultListView.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int postion, long id) {
                queryResults = taskList.get(postion);
                AlertDialog.Builder builder = new Builder(ResultSearch.this);
                builder.setMessage("任务名称:" + queryResults.getTaskName() + "\n" + "\n" + "指标名称:"
                        + queryResults.getTestKeyName() + "\n" + "\n" + "拨测结果状态:"
                        + switchStatus(queryResults.getStatus()) + "\n" + "\n" + "处理状态:"
                        + switchUpStatus(queryResults.getUpStatus()) + "\n" + "\n" + "拨测序号:"
                        + queryResults.getTaskItemBatchId() + "\n" + "\n" + "拨测值：" + queryResults.getTestValue());
                builder.setTitle("结果详情");
                builder.setPositiveButton(getResources().getString(R.string.OK), null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                // System.out.println("是否选中checkbox:" + resultListData.get(postion).isNeedUploadStatus());
                // System.out.println("拨测结果状态:" + queryResults.getStatus());
                // System.out.println("处理状态:" + queryResults.getUpStatus());
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
        case 0:
            return "失败";
        case 1:
            return "成功";
        }
        return null;
    }

    /**
     * 
     * @param upStatus
     * @return
     */
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

    /**
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu) 点击菜单事件，执行上传、 删除、重跑工作
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*
         * 
         * add()方法的四个参数，依次是：
         * 
         * 1、组别，如果不分组的话就写Menu.NONE,
         * 
         * 2、Id，这个很重要，Android根据这个Id来确定不同的菜单
         * 
         * 3、顺序，那个菜单现在在前面由这个参数的大小决定
         * 
         * 4、文本，菜单的显示文本
         */

        menu.add(Menu.CATEGORY_SYSTEM, Menu.FIRST + 1, 3, "删除").setIcon(

        android.R.drawable.ic_menu_delete);
        /*
         * setIcon()方法为菜单设置图标，这里使用的是系统自带的图标， 同学们留意一下,以 android.R开头的资源是系统提供的，我们自己提供的资源是以R开头的
         */
        menu.add(Menu.NONE, Menu.FIRST + 2, 2, "重跑").setIcon(

        android.R.drawable.ic_menu_edit);
        menu.add(Menu.NONE, Menu.FIRST + 3, 1, "上传").setIcon(

        android.R.drawable.ic_menu_send);
        return true;
    }

    /**
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

        case Menu.FIRST + 1: {
            /**
             * 删除功能
             */

            AlertDialog dialog = new AlertDialog.Builder(ResultSearch.this).setTitle("警告").setMessage("确定要删除所选内容吗？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            /**
                             * 调用删除接口，进行删除批处理操作! 得到选择的resultSearchData 集合
                             */
                            for (ResultSearchItemContent itemContent : itemContents) {
                                try {
                                    if (itemContent.isNeedUploadStatus()) {
                                        itaskService.deleteTaskCase(itemContent.getQueryResults().getTaskItemBatchId());
                                        resultListData.remove(itemContent);
                                    }
                                } catch (MException e) {
                                    Toast.makeText(getApplicationContext(), "删除失败", Toast.LENGTH_LONG).show();
                                }
                            }
                            itemContents.clear();
                            Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_LONG).show();
                            adapter.notifyDataSetChanged();
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create();
            dialog.show();

            break;
        }
        case Menu.FIRST + 2: {

            /**
             * 重跑功能
             */
            AlertDialog dialog = new AlertDialog.Builder(ResultSearch.this).setTitle("警告").setMessage("确定要重跑所选内容吗？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            /**
                             * 调用重跑接口，进行重跑批处理操作! 得到选择的resultSearchData 集合
                             */
                            for (ResultSearchItemContent itemContent : itemContents) {
                                try {
                                    if (itemContent.isNeedUploadStatus()) {

                                        itaskService
                                                .taskReiteration(itemContent.getQueryResults().getTaskItemBatchId());
                                        Log.d("resultserach_id", ""
                                                + itemContent.getQueryResults().getTaskItemBatchId());
                                        resultListData.remove(itemContent);
                                    }
                                } catch (MException e) {
                                    Toast.makeText(getApplicationContext(), "所选项重跑失败！", Toast.LENGTH_LONG).show();
                                }
                            }
                            itemContents.clear();
                            Toast.makeText(getApplicationContext(), "所选项已经加载到重跑队列中！", Toast.LENGTH_LONG).show();
                            adapter.notifyDataSetChanged();
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create();
            dialog.show();

            break;
        }
        case Menu.FIRST + 3: {
            /**
             * 上传功能
             */

            AlertDialog uploadDialog = new AlertDialog.Builder(ResultSearch.this).setTitle("警告")
                    .setMessage("确定要上传所选内容吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            /**
                             * 调用上传接口，进行上传批处理操作! 得到选择的resultSearchData 集合
                             */

                            List<Long> list = new ArrayList<Long>();
                            for (ResultSearchItemContent itemContent : itemContents) {
                                if (itemContent.isNeedUploadStatus()) {
                                    list.add(itemContent.getQueryResults().getTaskItemBatchId());
                                    Log.d("resultserach_id", "" + itemContent.getQueryResults().getTaskItemBatchId());
                                    resultListData.remove(itemContent);
                                }
                            }
                            final Long[] itemBatchList = new Long[list.size()];
                            list.toArray(itemBatchList);
                            try {
                                doUpload.doUploadResult(itemBatchList);
                            } catch (MException e) {
                                Toast.makeText(getApplicationContext(), "所选项上传失败！", Toast.LENGTH_LONG).show();
                            }

                            itemContents.clear();
                            Toast.makeText(getApplicationContext(), "所选项上传成功！", Toast.LENGTH_LONG).show();
                            adapter.notifyDataSetChanged();
                        }

                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create();
            uploadDialog.show();

            break;
        }
        }
        return false;
    }

    /**
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onOptionsMenuClosed(android.view.Menu)
     */
    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
    }

    /**
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onPrepareOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.v("ReesultSearch", "size = " + itemContents.size());
        if (itemContents != null && itemContents.size() > 0) {
            for (int item = 0; item < 3; item++) {
                menu.getItem(item).setEnabled(true);
            }

        } else {
            for (int item = 0; item < 3; item++) {
                menu.getItem(item).setEnabled(false);
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

	public void onPanelClosed(Panel panel) {
		// TODO Auto-generated method stub
		
	}

	public void onPanelOpened(Panel panel) {
		// TODO Auto-generated method stub
		
	}

}
