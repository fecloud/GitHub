/**
 * @(#) TaskActivityGroup.java Created on 2012-7-9
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute.ui;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

/**
 * The class <code>TaskActivityGroup</code>
 *
 * @author andyshen
 * @version 1.0
 */
public class TaskGroupActivity extends ActivityGroup {
    /**
     * 一个静态的ActivityGroup变量，用于管理本Group中的Activity
     */
    public static ActivityGroup group;

    /**
     * (non-Javadoc)
     * @see android.app.ActivityGroup#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        group = this;
    }

    /**
     * (non-Javadoc)
     * @see android.app.ActivityGroup#onResume()
     */
    @Override
    protected void onResume() {
        super.onResume();
        // 把界面切换放到onResume方法中是因为，从其他选项卡切换回来时，
        // 调用的是onResume方法

        // 要跳转的界面
        Intent intent = new Intent(this, TaskActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // 把一个Activity转换成一个View
        Window w = group.getLocalActivityManager().startActivity("TaskActivity", intent);
        View view = w.getDecorView();
        // 把View添加到ActivityGroup中
        group.setContentView(view);
    }

    /**
     * (non-Javadoc)
     * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
     * 处理点击键盘后退按钮事件
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_BACK){
            return group.getLocalActivityManager()
                    .getCurrentActivity().onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }
    
}
