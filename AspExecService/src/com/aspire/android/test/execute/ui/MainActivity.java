/**
 * @(#) MainActivity.java Created on 2012-4-23
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute.ui;

import roboguice.activity.RoboTabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.aspire.android.common.db.DatabaseManager;
import com.aspire.android.common.exception.ExceptionHandler;
import com.aspire.android.common.exception.MException;
import com.aspire.android.common.exception.MUncaughtExceptionHandler;
import com.aspire.android.daemon.remote.aidl.DaemonRemoteServiceManager;
import com.aspire.android.test.Constants;
import com.aspire.android.test.TestDatabaseHelper;
import com.aspire.android.test.execute.ITestService;
import com.aspire.android.test.execute.R;
import com.aspire.android.test.server.ExecServer;
import com.google.inject.Inject;

/**
 * The class <code>MainActivity</code>
 * 
 * @author linjunsui
 * @version 1.0
 */
public class MainActivity extends RoboTabActivity {

    @Inject
    private ExecServer execServer;
    @Inject 
    private MUncaughtExceptionHandler mUncaughtExceptionHandler;
    /**
     * execute service
     */

    public TabHost mth;
    public RadioGroup radioGroup;
    protected ITestService testService;
    public static final String TAB_START_STOP = "首页";
    public static final String TAB_SET_UP = "设置";
    public static final String TAB_VERSION = "版本";
    public static final String TAB_MANAGER = "测试管理";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mth = this.getTabHost();

        TabSpec ts_start = mth.newTabSpec(TAB_START_STOP).setIndicator(TAB_START_STOP);
        ts_start.setContent(new Intent(MainActivity.this, OperationsActivity.class));
        mth.addTab(ts_start);

        TabSpec ts_set = mth.newTabSpec(TAB_SET_UP).setIndicator(TAB_SET_UP);
        Intent intent = new Intent(MainActivity.this, PrefsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        ts_set.setContent(intent);
        mth.addTab(ts_set);

        TabSpec ts_version = mth.newTabSpec(TAB_VERSION).setIndicator(TAB_VERSION);
        ts_version.setContent(new Intent(MainActivity.this, VersionActivity.class));
        mth.addTab(ts_version);

        TabSpec ts_manager = mth.newTabSpec(TAB_MANAGER).setIndicator(TAB_MANAGER);
        ts_manager.setContent(new Intent(MainActivity.this, ManagerMenu.class));
        mth.addTab(ts_manager);

        this.radioGroup = (RadioGroup) findViewById(R.id.main_radio);
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                case R.id.radio_button0:
                    mth.setCurrentTabByTag(TAB_START_STOP);
                    break;
                case R.id.radio_button1:
                    mth.setCurrentTabByTag(TAB_SET_UP);
                    break;
                case R.id.radio_button2:
                    mth.setCurrentTabByTag(TAB_VERSION);
                    break;
                case R.id.radio_button5:
                    mth.setCurrentTabByTag(TAB_MANAGER);
                    break;
                }
            }
        });

        try {
            initDB();
        } catch (Exception e) {
            ExceptionHandler.handle(e, "Error open database" + Constants.DATABASE_FILENAME);
            return;
        }
        //启动adb相关服务，与pc交互
        new DaemonRemoteServiceManager(this);
        Thread.setDefaultUncaughtExceptionHandler(mUncaughtExceptionHandler);
        try {
            execServer.start(false);
        } catch (MException e) {
            e.printStackTrace();
        }
    }

    /**
     * init the db for program
     * 
     * @throws MException
     */
    private void initDB() throws MException {
        TestDatabaseHelper testDatabaseHelper = new TestDatabaseHelper(this, Constants.DATABASE_FILENAME, null,
                Constants.DATABASE_VERSION);
        DatabaseManager.addDatabaseHelper(testDatabaseHelper);
        DatabaseManager.setDefaultDatabase(Constants.DATABASE_FILENAME);
    }
//
//    @Override
//    public boolean dispatchKeyEvent(KeyEvent event) {
//        // TODO Auto-generated method stub
//        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
//            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
//                AlertDialog dlg = new AlertDialog.Builder(MainActivity.this).setTitle(R.string.login_page_tip)
//                        .setMessage(R.string.login_page_tip_message)
//                        .setPositiveButton(R.string.login_page_tip_sure, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface arg0, int arg1) {
//                                Intent intent = new Intent("com.aspire.android.test.execute.ExecuteService");
//                                stopService(intent);
//                                finish();
//                            }
//                        }).setNegativeButton(R.string.login_page_cancel, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int arg1) {
//                                // TODO Auto-generated method stub
//                                dialog.dismiss();
//                            }
//                        }).create();
//                dlg.show();
//            }
//            return true;
//        }
//        return super.dispatchKeyEvent(event);
//
//    }

}
