package com.aspire.android.test.execute.ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import roboguice.activity.RoboActivity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.aspire.android.common.exception.ExceptionHandler;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.Constants;
import com.aspire.android.test.PreferencesManager;
import com.aspire.android.test.execute.NameConstants;
import com.aspire.android.test.execute.NameConstants.SharedPrefConstants;
import com.aspire.android.test.execute.R;
import com.aspire.android.test.log.RunLogger;
import com.aspire.android.test.script.sync.LocalScriptParsor;
import com.aspire.android.util.ExportAndImportProperties;
import com.aspire.android.util.ShellAdb;
import com.google.inject.Inject;

public class OperationsActivity extends RoboActivity implements OnClickListener {

    private final int LOAD_LOCALSCRITP_DIALOG = 1;
    private final int CHANGE_TO_TEST_DIALOG = 2;
    private final int CHANGE_TO_NORMAL_DIALOG = 3;
    private final int SHOW_LOAD_SCRIPT_ERROR = 4;
    private RunLogger runLogger = RunLogger.getInstance();
    private Button start_button, stop_button, importLocalScriptBuntton;
    private Button man_upload_result_btn, man_update_script_btn, man_update_task_btn, permissionsdb_button;
    private Button export_button, import_button, test_version, official_version;
    private SharedPreferences preferences;
    private Editor editor;
    private ExportAndImportProperties exportAndImportProperties;
    private PreferencesManager preferencesManager = PreferencesManager.getInstance();
    private SharedPreferences preferencesType;
    @Inject
    private LocalScriptParsor localScriptParsor;
    private String[] scriptXmlNames;
    private File xmlFile;
    private ProgressDialog progressDialog;
    /** 导入本地脚本的目录 */
    private String parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferencesType = getSharedPreferences("type", Context.MODE_PRIVATE);
        setContentView(R.layout.operations);
        getControlsView();
        setControlsOnClick();

    }

    public void getControlsView() {
        // 根据ID得到按钮
        start_button = (Button) findViewById(R.id.start);
        stop_button = (Button) findViewById(R.id.stop);
        man_upload_result_btn = (Button) findViewById(R.id.man_upload_result);
        man_update_script_btn = (Button) findViewById(R.id.man_update_script);
        man_update_task_btn = (Button) findViewById(R.id.man_update_task);
        importLocalScriptBuntton = (Button) findViewById(R.id.import_localscript_button);
        export_button = (Button) findViewById(R.id.exportfile_button);
        import_button = (Button) findViewById(R.id.importfile_button);
        permissionsdb_button = (Button) findViewById(R.id.permissionsdb_button);
        official_version = (Button) findViewById(R.id.official_version);
        test_version = (Button) findViewById(R.id.test_version);
        int type = preferencesType.getInt(NameConstants.TYPE, NameConstants.TYPE_NORMAL);
        if (type == NameConstants.TYPE_NORMAL) {
            official_version.setVisibility(View.GONE);
            test_version.setVisibility(View.VISIBLE);
        } else if (type == NameConstants.TYPE_TEST) {
            official_version.setVisibility(View.VISIBLE);
            test_version.setVisibility(View.GONE);
        }
    }

    public void setControlsOnClick() {
        export_button.setOnClickListener(this);
        import_button.setOnClickListener(this);
        start_button.setOnClickListener(this);
        stop_button.setOnClickListener(this);
        man_upload_result_btn.setOnClickListener(this);
        man_update_script_btn.setOnClickListener(this);
        man_update_task_btn.setOnClickListener(this);
        importLocalScriptBuntton.setOnClickListener(this);
        permissionsdb_button.setOnClickListener(this);
        test_version.setOnClickListener(this);
        official_version.setOnClickListener(this);
        preferences = preferencesManager.getPreferences();
        editor = preferences.edit();
        isServiceRunning(getApplicationContext(), "com.aspire.android.test.execute.ExecuteService");
        exportAndImportProperties = new ExportAndImportProperties(preferences);
    }

    // 根据ID设置按钮的监听事件
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = null;
        switch (id) {
        case R.id.start:
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.service_start), Toast.LENGTH_LONG)
                    .show();
            intent = new Intent("com.aspire.android.test.execute.ExecuteService");
            startService(intent);
            isServiceRunning(getApplicationContext(), "com.aspire.android.test.execute.ExecuteService");
            break;
        case R.id.stop:
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.service_stop), Toast.LENGTH_LONG)
                    .show();
            intent = new Intent("com.aspire.android.test.execute.ExecuteService");
            stopService(intent);
            isServiceRunning(getApplicationContext(), "com.aspire.android.test.execute.ExecuteService");
            break;
        case R.id.man_upload_result:
            editor.putBoolean(SharedPrefConstants.MAN_UPLOAD_RESULT, true);
            editor.commit();
            break;
        case R.id.man_update_script:
            editor.putBoolean(SharedPrefConstants.MAN_UPDATE_SCRIPT, true);
            editor.commit();
            break;
        case R.id.man_update_task:
            editor.putBoolean(SharedPrefConstants.MAN_UPDATE_TASK, true);
            editor.commit();
            break;
        case R.id.import_localscript_button:
            createDialog(LOAD_LOCALSCRITP_DIALOG);
            break;
        // 导入按钮事件
        case R.id.importfile_button:
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                try {
                    exportAndImportProperties.importProperties();
                    Toast.makeText(this, R.string.im_success, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    ExceptionHandler.handle(e, "import property xmlFile");
                    Toast.makeText(this, R.string.import_error, Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, R.string.sdcarderror, Toast.LENGTH_LONG).show();
            }
            break;
        // 导出按钮事件
        case R.id.exportfile_button:
            // 判断sdcard是否存在手机上；并且可以读写访问
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                try {
                    exportAndImportProperties.exportProperties();
                    Toast.makeText(this, R.string.ex_success, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    ExceptionHandler.handle(e, "export property xmlFile");
                    Toast.makeText(this, R.string.export_error, Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, R.string.sdcarderror, Toast.LENGTH_LONG).show();
            }

            break;
        case R.id.permissionsdb_button:
            try {
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

                    SharedPreferences pref = this.getSharedPreferences("type", Context.MODE_PRIVATE);
                    int type = pref.getInt(NameConstants.TYPE, NameConstants.TYPE_NORMAL);
                    if (type == NameConstants.TYPE_TEST) {
                        ShellAdb.execCommand("chmod 777 /data/data/com.aspire.android.test.execute/databases/"
                                + Constants.DATABASE_FILENAME_TEST);
                    } else if (type == NameConstants.TYPE_NORMAL) {
                        ShellAdb.execCommand("chmod 777 /data/data/com.aspire.android.test.execute/databases/"
                                + Constants.DATABASE_FILENAME);
                    }
                    Toast.makeText(this, R.string.permissionsdb_success, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, R.string.permissionsdb_err, Toast.LENGTH_LONG).show();
                }
            } catch (IOException e) {
                Toast.makeText(this, R.string.permissionsdb_err, Toast.LENGTH_LONG).show();
            }
            break;
        case R.id.test_version:
            createDialog(CHANGE_TO_TEST_DIALOG);
            break;

        case R.id.official_version:
            createDialog(CHANGE_TO_NORMAL_DIALOG);
            break;
        }
    }

    private void createDialog(int id) {
        Dialog dialog = null;
        switch (id) {
        case LOAD_LOCALSCRITP_DIALOG:
            parent = Environment.getExternalStorageDirectory().getAbsolutePath()
                    + preferences.getString(PrefsActivity.SET_SAVE_ADDRESS, null) + NameConstants.LOCAL_SCRIPT;
            File scriptParent = new File(parent);
            if (!scriptParent.exists()) {
                scriptParent.mkdirs();
            }
            File[] scripts = scriptParent.listFiles();
            List<String> scriptXmlNameList = new ArrayList<String>();
            for (int i = 0; i < scripts.length; i++) {
                if (scripts[i].getName().endsWith(".xml")) {
                    scriptXmlNameList.add(scripts[i].getName());
                }
            }
            if (scriptXmlNameList == null || scriptXmlNameList.size() == 0) {
                this.createDialog(SHOW_LOAD_SCRIPT_ERROR);
                return;
            }
            scriptXmlNames = new String[scriptXmlNameList.size()];
            scriptXmlNameList.toArray(scriptXmlNames);
            dialog = new AlertDialog.Builder(this).setTitle("请选择脚本配置文件").setSingleChoiceItems(scriptXmlNames, -1, oc)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            new ProcessThread(handler).start();
                        }
                    }).create();
            break;
        case CHANGE_TO_TEST_DIALOG:
            dialog = new AlertDialog.Builder(OperationsActivity.this).setTitle("版本切换")
                    .setMessage("是否切换到测试版？确定后该系统会重新启动！").setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            Editor editor = preferencesType.edit();
                            editor.putInt(NameConstants.TYPE, NameConstants.TYPE_TEST);
                            editor.commit();
                            Intent intent = new Intent("com.aspire.android.test.execute.ExecuteService");
                            stopService(intent);
                            intent = new Intent("com.aspire.android.test.execute.RestartService");
                            startService(intent);
                            finish();
                            System.exit(0);
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create();
            break;
        case CHANGE_TO_NORMAL_DIALOG:
            dialog = new AlertDialog.Builder(OperationsActivity.this).setTitle("版本切换")
                    .setMessage("是否切换到正试版？确定后该系统会重新启动！").setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            Editor editor = preferencesType.edit();
                            editor.putInt(NameConstants.TYPE, NameConstants.TYPE_NORMAL);
                            editor.commit();
                            Intent intent = new Intent("com.aspire.android.test.execute.ExecuteService");
                            stopService(intent);
                            intent = new Intent("com.aspire.android.test.execute.RestartService");
                            startService(intent);
                            finish();
                            System.exit(0);
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create();
            break;
        case SHOW_LOAD_SCRIPT_ERROR:
            dialog = new AlertDialog.Builder(OperationsActivity.this).setTitle("警告：")
                    .setMessage(parent + "文件夹下没有脚本文件，请检查").setPositiveButton("确定", null).create();
            break;
        }
        if(dialog != null)
            dialog.show();
    }

    final OnDialogSpinnerClick oc = new OnDialogSpinnerClick();

    private class OnDialogSpinnerClick implements DialogInterface.OnClickListener {

        private int which = 0;

        public void onClick(DialogInterface dialog, int which) {
            this.which = which;
            Toast.makeText(OperationsActivity.this, "您当前选择的是 \n" + scriptXmlNames[which], Toast.LENGTH_LONG).show();
        }

        public int getWhich() {
            return which;
        }
    }

    /**
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onDestroy()
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 用来判断服务是否运行.
     * 
     * @param context
     * @param className
     *            判断的服务名字：包名+类名
     * @return true 在运行, false 不在运行
     */
    public boolean isServiceRunning(Context context, String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(Integer.MAX_VALUE);
        if (!(serviceList.size() > 0)) {
            return false;
        }
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(className) == true) {
                isRunning = true;
                start_button.setVisibility(View.GONE);
                stop_button.setVisibility(View.VISIBLE);
                break;
            } else {
                start_button.setVisibility(View.VISIBLE);
                stop_button.setVisibility(View.GONE);
            }
        }
        Log.i("service ", "service is running?==" + isRunning);
        return isRunning;

    }

    private class ProcessThread extends Thread {
        private Handler handler;

        public ProcessThread(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void run() {
            try {
                OperationsActivity.this.runOnUiThread(runnable);
                xmlFile = new File(parent, scriptXmlNames[oc.getWhich()]);
                localScriptParsor.parseScriptDetials(xmlFile);
            } catch (MException e) {
                runLogger.error(getClass(), "load local script failed, errmessage " + e.getMessage());
                AlertDialog alertDialog = new AlertDialog.Builder(OperationsActivity.this).setTitle("警告：")
                        .setMessage("导入本地脚本失败，请查看！").setPositiveButton("确定", null).create();
                alertDialog.show();
            } finally {
                Message message = handler.obtainMessage();
                message.what = 1;
                handler.sendMessage(message);
            }
        }
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (progressDialog != null)
                progressDialog.dismiss();
        }
    };

    private Runnable runnable = new Runnable() {
        public void run() {
            progressDialog = new ProgressDialog(OperationsActivity.this);
            // 设置进度条风格，风格为圆形，旋转的
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            // 设置ProgressDialog 标题
            progressDialog.setTitle("提示");
            // 设置ProgressDialog 提示信息
            progressDialog.setMessage("正在导入脚本，请稍等...");
            // 设置ProgressDialog 标题图标
            progressDialog.setIcon(android.R.drawable.ic_dialog_map);
            // 设置ProgressDialog 的进度条是否不明确
            progressDialog.setIndeterminate(false);
            // 设置ProgressDialog 是否可以按退回按键取消
            progressDialog.setCancelable(true);
            progressDialog.show();
        }
    };
}
