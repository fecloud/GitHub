/**
 * @(#) CustomizeMenu.java Created on 2012-7-24
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute.ui;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import com.aspire.android.test.execute.R;

/**
 * The class <code>CustomizeMenu</code>
 * 
 * @author andyshen
 * @version 1.0
 */
public class ManagerMenu extends Activity {

    AlertDialog menuDialog;// menu菜单Dialog
    GridView menuGrid;
    View menuView;

    private final int ITEM_TASK = 0;// 任务查询
    private final int ITEM_SCRIPT = 1;// 脚本查询
    private final int ITEM_RESULT = 2;// 结果查询
    private final int ITEM_BUSINESS_INDEX = 3;// 业务指标查询
    private final int ITEM_QUEREN = 4;// 业务指标查询

    /** 菜单图片 **/
    int[] menu_image_array = { R.drawable.menu_search, R.drawable.menu_filemanager, R.drawable.menu_downmanager,
            R.drawable.menu_fullscreen, R.drawable.menu_novel_mode };
    /** 菜单文字 **/
    String[] menu_name_array = { "任务查询", "脚本查询", "结果查询", "指标查询", "确认结果查询" };

    /**
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        menuView = View.inflate(this, R.layout.test_manager, null);
        // 创建AlertDialog
        menuDialog = new AlertDialog.Builder(this).create();
        menuDialog.setView(menuView);
        /*
         * menuDialog.setOnKeyListener(new OnKeyListener() { public boolean onKey(DialogInterface dialog, int keyCode,
         * KeyEvent event) { if (keyCode == R.id.radio_button5)// 监听按键 dialog.dismiss(); return false; } });
         */

        menuGrid = (GridView) menuView.findViewById(R.id.gridview);
        menuGrid.setAdapter(getMenuAdapter(menu_name_array, menu_image_array));
        /** 监听menu选项 **/
        menuGrid.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                switch (arg2) {
                case ITEM_TASK:// 任务查询
                    // Toast.makeText(getApplicationContext(), getResources().getString(R.string.version_message),
                    // 1).show();
                    // 要跳转的界面
                    startActivity(new Intent(getApplicationContext(), TaskSearch.class));
                    break;
                case ITEM_SCRIPT:// 脚本查询
                    startActivity(new Intent(getApplicationContext(), ScriptSerach.class));
                    break;
                case ITEM_RESULT:// 结果查询
                    startActivity(new Intent(getApplicationContext(), ResultSearch.class));
                    break;
                case ITEM_BUSINESS_INDEX:// 业务指标查询
                    startActivity(new Intent(getApplicationContext(), BusinessIndexSearch.class));
                    break;
                case ITEM_QUEREN:// 确认结果查询
                    startActivity(new Intent(getApplicationContext(), VerifySearch.class));
                    break;

                }

            }
        });

    }

    /**
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onResume()
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (menuDialog == null) {
            menuDialog = new AlertDialog.Builder(this).setView(menuView).show();
        } else {
            menuDialog.show();
        }

    }

    private SimpleAdapter getMenuAdapter(String[] menuNameArray, int[] imageResourceArray) {
        ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < menuNameArray.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemImage", imageResourceArray[i]);
            map.put("itemText", menuNameArray[i]);
            data.add(map);
        }
        SimpleAdapter simperAdapter = new SimpleAdapter(this, data, R.layout.item_menu, new String[] { "itemImage",
                "itemText" }, new int[] { R.id.item_image, R.id.item_text });
        return simperAdapter;
    }

}
