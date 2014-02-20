/**
 * @(#) Logger.java Created on 2012-9-14
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.log;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import android.os.Environment;
import android.util.Log;

import com.aspire.android.log.Logger;
import com.aspire.android.test.PreferencesManager;
import com.aspire.android.test.application.ExecApplication;
import com.aspire.android.test.execute.ui.PrefsActivity;

/**
 * The class <code>RunLogger</code>
 * 
 * @author likai
 * @version 1.0
 */
public class RunLogger{

    private Logger logger;
    // ALL(0), VERBOSE(1), DEBUG(2), INFO(3), WARN(4), ERROR(5), FATAL(6), OFF(7);
    private final static String LOG_LEVEL = "debug";

    private static RunLogger runLogger;
    private PreferencesManager preferencesManager = PreferencesManager.getInstance();

    private RunLogger() {
        initialLogger();
    }

    private void initialLogger() {
        String saveParentPath = preferencesManager.getPreferences().getString(PrefsActivity.SET_SAVE_ADDRESS, "/aspire/es/");
        if(saveParentPath.trim().equals("")){
            saveParentPath = "/aspire/es/";
        }
        String logPath = Environment.getExternalStorageDirectory()
                + saveParentPath + "es_run_log";
        HashMap<String, String> environment = new HashMap<String, String>();
        environment.put("LOG_LEVEL", LOG_LEVEL);
        environment.put("KEY_CASE_LOGLOCATION", logPath);
        try {
            InputStream in = ExecApplication.instance().getBaseContext().getAssets().open("syslogconfig.properties");
            logger = Logger.getLogger(ExecApplication.instance().getBaseContext(), "testExecute", in, environment);
        } catch (IOException e) {
        }
    }

    public static RunLogger getInstance() {
        if (runLogger == null) {
            runLogger = new RunLogger();
        }
        return runLogger;
    }

    public void error(Class<?> clazz, String msg) {
        String errorMsg = " [" + clazz.getSimpleName() + "]: " + msg;
        Log.e(clazz.getSimpleName(), msg);
        logger.error(errorMsg);
    }

    public void warn(Class<?> clazz, String msg) {
        String warnMsg = " [" + clazz.getSimpleName() + "]: " + msg;
        Log.w(clazz.getSimpleName(), msg);
        logger.warn(warnMsg);
    }

    public void info(Class<?> clazz, String msg) {
        String infoMsg = " [" + clazz.getSimpleName() + "]: " + msg;
        Log.i(clazz.getSimpleName(), msg);
        logger.info(infoMsg);
    }

    public void debug(Class<?> clazz, String msg) {
        String debugMsg = " [" + clazz.getSimpleName() + "]: " + msg;
        Log.d(clazz.getSimpleName(), msg);
        logger.debug(debugMsg);
    }

    public void onSaveParentPathChange() {
        logger.dispose();
        initialLogger();
    }

}
