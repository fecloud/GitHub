package com.aspire.android.test.weibocase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import com.aspire.android.test.exception.MException;
import com.aspire.android.test.execute.CommandConstants;
import com.aspire.android.test.execute.ContentValues;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WeiboTestCaseActivity extends Activity implements OnClickListener, Runnable {

    private ApkCaseUtil util;

    private TestState state = TestState.START;

    private Button testButton;

    private TextView textView;

    private String logPath;

    private Handler handler = new Handler() {

        /**
         * (non-Javadoc)
         * 
         * @see android.os.Handler#handleMessage(android.os.Message)
         */
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case 0:
                Toast.makeText(getApplicationContext(), (String) msg.obj, Toast.LENGTH_SHORT).show();
                state = TestState.FINISH;
                testButton.setEnabled(true);
                break;
            case 1:
                readLog((String) msg.obj);
                break;
            default:
                break;
            }

            super.handleMessage(msg);
        }

    };

    /**
     * 读日志文件
     */
    protected void readLog(String filename) {
        final File file = new File(filename);
        if (!file.exists())
            return;
        final StringBuffer buffer = new StringBuffer();

        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            // String
            char[] buf = new char[128];
            int len = 0;
            while (-1 != (len = reader.read(buf))) {
                buffer.append(buf, 0, len);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            textView.setText(buffer.toString());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        testButton = (Button) findViewById(R.id.startTest);
        testButton.setOnClickListener(this);
        textView = (TextView) findViewById(R.id.textView2);
    }

    /**
     * (non-Javadoc)
     * 
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
        new Thread(this).start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (state != TestState.FINISH) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    showLog();
                }
            }
        }).start();
        v.setEnabled(false);
    }

    /**
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        try {
            state = TestState.TESTING;
            util = ApkCaseUtil.getInstance(this);
            final ContentValues params = new ContentValues();
            final String className = "com.aspire.android.test.demo.FetionTestCase";
            params.put(CommandConstants.KEY_CASE_CLASSNAME, className);
            logPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + className;
            params.put(CommandConstants.KEY_CASE_LOGLOCATION, logPath);
            final ContentValues values = util.runCase(params);
            // final int isTestSuccess = values.getAsInteger(CommandConstants.KEY_CASE_TESTRESULT);
            // if (1 == isTestSuccess) {
            handler.sendMessage(handler.obtainMessage(0, getString(R.string.testSuccess)));
            // }

        } catch (MException e) {
            handler.sendMessage(handler.obtainMessage(0, getString(R.string.testfail)));
            throw new RuntimeException(e);
        }

    }

    /**
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onDestroy()
     */
    @Override
    protected void onDestroy() {
        if (null != util)
            util.shutDown();
        super.onDestroy();
    }

    protected void showLog() {
        if (null == logPath)
            return;
        final String filename = logPath + File.separator + "run.log";
        handler.sendMessage(handler.obtainMessage(1, filename));
    }

    enum TestState {
        START, TESTING, FINISH
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d("WeiboTestCaseActivity", "keyCode=" + keyCode);
        return super.onKeyDown(keyCode, event);
    }
}
