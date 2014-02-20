/**
 * @(#) DeviceEntity.java Created on 2012-4-28
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.environment;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import android.os.RemoteException;

import com.aspire.android.test.exception.MException;
import com.aspire.android.test.execute.CommandConstants;
import com.aspire.android.test.execute.ContentValues;
import com.aspire.android.test.execute.ITestService;

/**
 * The class <code>DeviceEntity</code>
 * 
 * @author linjunsui
 * @version 1.0
 */
public class DeviceEntity implements IDeviceEntity {

    public static final String TAG = "DeviceEntity";

    private Hashtable<String, Long> transactions = new Hashtable<String, Long>();

    /**
     * aidl testService
     */
    protected ITestService testService;

    /**
     * Constructor
     * 
     * @param testService
     */
    public DeviceEntity(ITestService testService) {
        super();
        this.testService = testService;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#getParams()
     */
    public ContentValues getParams() throws MException {
        try {
            return testService.execute(CommandConstants.TYPE_GETPARAMS, null);
        } catch (RemoteException e) {
            throw new MException("getParams RemoteException");
        }
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#saveResult(android.content.ContentValues)
     */
    public void saveResult(ContentValues resultMap) throws MException {
        try {
            testService.execute(CommandConstants.TYPE_SAVERESULT, resultMap);
        } catch (RemoteException e) {
            throw new MException("saveResult RemoteException");
        }
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#getSetting()
     */
    public ContentValues getSetting() throws MException {
        try {
            final ContentValues result = testService.execute(CommandConstants.TYPE_GETSETTING, null);
            return result;
        } catch (RemoteException e) {
            throw new MException("getSetting RemoteException");
        }

    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#getGlobalVariables()
     */
    public ContentValues getGlobalVariables() throws MException {
        try {
            final ContentValues result = testService.execute(CommandConstants.TYPE_GETGLOBALVARIABLES, null);
            return result;
        } catch (RemoteException e) {
            throw new MException("getGlobalVariables RemoteException");
        }

    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#saveTransaction(java.lang.String, java.lang.String,
     *      java.lang.String)
     * 
     *      public void saveTransaction(String name, String value, String attachment) throws MException { final
     *      ContentValues params = new ContentValues(3); params.put(CommandConstants.KEY_SAVETRANSACTION_NAME, name);
     *      params.put(CommandConstants.KEY_SAVETRANSACTION_VALUE, value);
     *      params.put(CommandConstants.KEY_SAVETRANSACTION_ATTACHMENT, attachment);
     *      executeCommand(CommandConstants.TYPE_SAVETRANSACTION, params); }
     */

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#log(int, java.lang.String)
     */
    public void log(int logLevel, String message) throws MException {
        final ContentValues params = new ContentValues(2);
        params.put(CommandConstants.KEY_LOG_LOGLEVEL, logLevel);
        params.put(CommandConstants.KEY_LOG_MESSAGE, message);
        executeCommand(CommandConstants.TYPE_LOG, params);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#logScreen(int, java.lang.String)
     */
    public void logScreen(int logLevel, String message) throws MException {
        final ContentValues params = new ContentValues(2);
        params.put(CommandConstants.KEY_LOG_LOGLEVEL, logLevel);
        params.put(CommandConstants.KEY_LOG_MESSAGE, message);
        executeCommand(CommandConstants.TYPE_LOGSCREEN, params);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#ocrScreen(int)
     */
    public String ocrScreen(int lang) throws MException {
        final ContentValues params = new ContentValues(1);
        params.put(CommandConstants.KEY_OCR_LANG, lang);
        final ContentValues result = executeCommand(CommandConstants.TYPE_OCRSCREEN, params);
        return result.getAsString(CommandConstants.KEY_METHOD_RESULT);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#ocrRect(int, int, int, int, int)
     */
    public String ocrRect(int startX, int startY, int width, int height, int lang) throws MException {
        final ContentValues params = new ContentValues();
        params.put(CommandConstants.KEY_OCRRECT_STARTX, startX);
        params.put(CommandConstants.KEY_OCRRECT_STARTY, startY);
        params.put(CommandConstants.KEY_OCRRECT_WIDTH, width);
        params.put(CommandConstants.KEY_OCRRECT_HEIGHT, height);
        params.put(CommandConstants.KEY_OCR_LANG, lang);
        final ContentValues result = executeCommand(CommandConstants.TYPE_OCRRECT, params);
        return result.getAsString(CommandConstants.KEY_METHOD_RESULT);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#screenDown(int, int)
     */
    public void screenDown(int x, int y) throws MException {
        final ContentValues params = new ContentValues(2);
        params.put(CommandConstants.KEY_POINT_X, x);
        params.put(CommandConstants.KEY_POINT_Y, y);
        executeCommand(CommandConstants.TYPE_SCREENDOWN, params);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#screenMove(int, int)
     */
    public void screenMove(int x, int y) throws MException {
        final ContentValues params = new ContentValues(2);
        params.put(CommandConstants.KEY_POINT_X, x);
        params.put(CommandConstants.KEY_POINT_Y, y);
        executeCommand(CommandConstants.TYPE_SCREENMOVE, params);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#screenUp(int, int)
     */
    public void screenUp(int x, int y) throws MException {
        final ContentValues params = new ContentValues(2);
        params.put(CommandConstants.KEY_POINT_X, x);
        params.put(CommandConstants.KEY_POINT_Y, y);
        executeCommand(CommandConstants.TYPE_SCREENUP, params);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#screenClick(int, int)
     */
    public void screenClick(int x, int y) throws MException {
        final ContentValues params = new ContentValues(2);
        params.put(CommandConstants.KEY_POINT_X, x);
        params.put(CommandConstants.KEY_POINT_Y, y);
        executeCommand(CommandConstants.TYPE_SCREENCLICK, params);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#screenTouch(int[][])
     */
    public void screenTouch(int x, int y, int toX, int toY) throws MException {
        // final List<ContentValues> paramsList = new ArrayList<ContentValues>();
        // ContentValues params = null;
        // for (int[] xy : xys) {
        // if (xy.length == 2) {
        // params = new ContentValues(2);
        // params.put(CommandConstants.KEY_POINT_X, xy[0]);
        // params.put(CommandConstants.KEY_POINT_Y, xy[1]);
        // paramsList.add(params);
        // }
        // }
        // executeListCommand(CommandConstants.TYPE_SCREENTOUCH, paramsList);
        final ContentValues params = new ContentValues(4);
        params.put(CommandConstants.KEY_POINT_X, x);
        params.put(CommandConstants.KEY_POINT_Y, y);
        params.put(CommandConstants.KEY_POINT_X1, toX);
        params.put(CommandConstants.KEY_POINT_Y1, toY);
        executeCommand(CommandConstants.TYPE_SCREENTOUCH, params);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#keyUp(int)
     */
    public void keyUp(int key) throws MException {
        final ContentValues params = new ContentValues(1);
        params.put(CommandConstants.KEY_KEYCODE, key);
        executeCommand(CommandConstants.TYPE_KEYUP, params);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#keyDown(int)
     */
    public void keyDown(int key) throws MException {
        final ContentValues params = new ContentValues(1);
        params.put(CommandConstants.KEY_KEYCODE, key);
        executeCommand(CommandConstants.TYPE_KEYDOWN, params);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#verifyScreen(int, int, java.lang.String, int, int, int,
     *      int, int, int)
     */
    public boolean verifyScreen(int inputStartX, int inputStartY, String templateImagePath, int templateStartX,
            int templateStartY, int w, int h, int colorToleranceAsPercent, int pixelTolerance) throws MException {

        if (colorToleranceAsPercent < 0 && colorToleranceAsPercent > 100) {
            throw new MException("参数'colorToleranceAsPercent'应在0到100间");
        }

        final ContentValues params = new ContentValues();
        params.put(CommandConstants.KEY_INPUTSTARTX, inputStartX);
        params.put(CommandConstants.KEY_INPUTSTARTY, inputStartY);
        params.put(CommandConstants.KEY_TEMPLATEIMAGEPATH, templateImagePath);
        params.put(CommandConstants.KEY_TEMPLATESTARTX, templateStartX);
        params.put(CommandConstants.KEY_TEMPLATESTARTY, templateStartY);
        params.put(CommandConstants.KEY_W, w);
        params.put(CommandConstants.KEY_H, h);
        params.put(CommandConstants.KEY_COLORTOLERANCEASPERCENT, colorToleranceAsPercent);
        params.put(CommandConstants.KEY_PIXELTOLERANCE, pixelTolerance);
        final ContentValues result = executeCommand(CommandConstants.TYPE_VERIFYSCREEN, params);
        return result.getAsBoolean(CommandConstants.KEY_METHOD_RESULT);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#findInScreen(java.lang.String, int, int, int, int, int)
     */
    public int[] findInScreen(String templateImagePath, int templateStartX, int templateStartY, int w, int h,
            int colorToleranceAsPercent) throws MException {
        return findInScreen(0, 0, -1, -1, templateImagePath, templateStartX, templateStartY, w, h,
                colorToleranceAsPercent);
    }

    /**
     * 
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#install(java.lang.String)
     */
    public boolean install(String apkPath) throws MException {
        final ContentValues params = new ContentValues(1);
        params.put(CommandConstants.KEY_APKPATH, apkPath);
        final ContentValues result = executeCommand(CommandConstants.TYPE_INSTALL, params);
        return result.getAsBoolean(CommandConstants.KEY_METHOD_RESULT);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#input(java.lang.String)
     */
    public void input(String text) throws MException {
        final ContentValues params = new ContentValues(1);
        params.put(CommandConstants.KEY_INPUT_TEXT, text);
        executeCommand(CommandConstants.TYPE_INPUT, params);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#uninstall(java.lang.String)
     */
    public boolean uninstall(String packageName) throws MException {
        final ContentValues params = new ContentValues(1);
        params.put(CommandConstants.KEY_PACKAGENAME, packageName);
        final ContentValues result = executeCommand(CommandConstants.TYPE_UNINSTALL, params);
        return result.getAsBoolean(CommandConstants.KEY_METHOD_RESULT);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#runApp(java.lang.String)
     */
    public boolean runApp(String packageName) throws MException {
        final ContentValues params = new ContentValues(1);
        params.put(CommandConstants.KEY_PACKAGENAME, packageName);
        final ContentValues result = executeCommand(CommandConstants.TYPE_RUNAPP, params);
        return result.getAsBoolean(CommandConstants.KEY_METHOD_RESULT);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#sendMessage(java.lang.String, java.lang.String)
     */
    public boolean sendMessage(String receivers, String content) throws MException {
        final ContentValues params = new ContentValues(2);
        params.put(CommandConstants.KEY_SENDMESSAGE_RECEIVERS, receivers);
        params.put(CommandConstants.KEY_SENDMESSAGE_CONTENT, content);
        final ContentValues result = executeCommand(CommandConstants.TYPE_SENDMESSAGE, params);
        return result.getAsBoolean(CommandConstants.KEY_METHOD_RESULT);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#getMessage(com.aspire.android.test.execute.ContentValues)
     */
    public List<ContentValues> getMessage(ContentValues params) throws MException {
        return executeWithList(CommandConstants.TYPE_GETMESSAGE, params);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#getMessage(int)
     */
    public List<ContentValues> getMessage(int messageCount) throws MException {
        return getMessage("", messageCount, -1);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#getMessage(java.lang.String, int, int)
     */
    public List<ContentValues> getMessage(String from, int messageCount, int messageType) throws MException {
        final ContentValues params = new ContentValues();
        params.put(CommandConstants.KEY_GETMESSAGE_TYPE, 0x01);
        params.put(CommandConstants.KEY_GETMESSAGE_COUNT, messageCount);
        if (null != from)
            params.put(CommandConstants.KEY_GETMESSAGE_DESTADDRESSES, from);
        // -1为全部,0,为未读 1为已读
        if (messageType > -1 && messageType < 2) {
            params.put(CommandConstants.KEY_GETMESSAGE_ISREAD, messageType);
        }
        return getMessage(params);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#getMessage(java.lang.String, int)
     */
    public List<ContentValues> getMessage(String from, int messageCount) throws MException {
        return getMessage(from, messageCount, -1);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#setAllMessageReaded()
     */
    public void setAllMessageReaded() throws MException {
        executeCommand(CommandConstants.TYPE_SETALLMESSAGEREADED, null);

    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#deleteAllMessage()
     */
    public void deleteAllMessage() throws MException {
        executeCommand(CommandConstants.TYPE_DELETEALLMESSAGE, null);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#saveTransaction(long, long, java.lang.String,
     *      java.lang.String, boolean, boolean)
     */
    public void saveTransaction(long begintime, long endtime, String name, String value, String result,
            boolean saveCertificate) throws MException {
        final ContentValues params = new ContentValues(6);
        params.put(CommandConstants.KEY_SAVETRANSACTION_BEGINTIME, begintime);
        params.put(CommandConstants.KEY_SAVETRANSACTION_ENDTIME, endtime);
        params.put(CommandConstants.KEY_SAVETRANSACTION_NAME, name);
        params.put(CommandConstants.KEY_SAVETRANSACTION_VALUE, value);
        params.put(CommandConstants.KEY_SAVETRANSACTION_RESULT, result);
        params.put(CommandConstants.KEY_SAVETRANSACTION_SAVECERTIFICATE, saveCertificate);
        executeCommand(CommandConstants.TYPE_SAVETRANSACTION, params);
        transactions.remove(name);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#beginTransaction(java.lang.String)
     */
    public void beginTransaction(String name) throws MException {
        Long startTime = System.currentTimeMillis();
        transactions.put(name, startTime);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#endTransaction(java.lang.String, java.lang.String,
     *      boolean)
     */
    // public void endTransaction(String name, String value, boolean isSucess) throws MException {
    // endTransaction(name, value, isSucess, false);
    // }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#endTransaction(java.lang.String, java.lang.String,
     *      boolean, boolean)
     */
    // public void endTransaction(String name, String value, boolean isSucess, boolean saveCertificate) throws
    // MException {
    // if (transactions.containsKey(name)) {
    // final long begintime = transactions.get(name);
    // final long endtime = System.currentTimeMillis();
    // String result = null;
    // if (isSucess) {
    // result = "00";
    // } else {
    // result = "03";
    // }
    // saveTransaction(begintime, endtime, name, value, result, saveCertificate);
    // } else {
    // // no beginTransaction
    // throw new MException("transaction not begin or transaction is end");
    // }
    //
    // }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#endTransaction(java.lang.String, java.lang.String,
     *      boolean)
     */
    public void endTransaction(String name, String result, boolean saveCertificate) throws MException {
        endTransaction(name, null, result, saveCertificate);
    }

    /**
     * 
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#endTransaction(java.lang.String, java.lang.String,
     *      java.lang.String, boolean)
     */
    public void endTransaction(String name, String value, String result, boolean saveCertificate) throws MException {
        if (transactions.containsKey(name)) {
            final long begintime = transactions.get(name);
            final long endtime = System.currentTimeMillis();
            saveTransaction(begintime, endtime, name, value, result, saveCertificate);
        } else {
            // no beginTransaction
            throw new MException("transaction not begin or transaction is end");
        }
    }

    /**
     * check invoke aidl result params if params container error messae throw MException
     * 
     * @param params
     * @throws MException
     * @see java.lang.MException
     */
    public ContentValues executeCommand(int commandType, ContentValues params) throws MException {
        try {
            final ContentValues result = testService.execute(commandType, params);
            if (null == result) {
                throw new MException("this params is null ,please check result");
            } else if (result.getAsInteger(CommandConstants.KEY_ERROR_CODE) != CommandConstants.KEY_ERROR_CODE_SUCESS) {
                throw new MException("invoke aidl testService result is error the error message:"
                        + result.getAsString(CommandConstants.KEY_ERROR_MESSAGE));
            } else {
                return result;
            }
        } catch (RemoteException e) {
            throw new MException("executeCommand RemoteException");
        }

    }

    /**
     * check invoke aidl result params if params container error messae throw MException
     * 
     * @param params
     * @throws MException
     * @see java.lang.MException
     */
    public List<ContentValues> executeListCommand(int commandType, List<ContentValues> paramsList) throws MException {
        try {
            final List<ContentValues> results = testService.executeWithList(commandType, paramsList);
            if (null == results)
                throw new MException("this params is null ,please check result");
            if (results.size() > 0) {
                final ContentValues values = results.get(0);
                if (values.getAsInteger(CommandConstants.KEY_ERROR_CODE) != CommandConstants.KEY_ERROR_CODE_SUCESS) {
                    throw new MException("invoke aidl testService result is error the error message:"
                            + values.getAsString(CommandConstants.KEY_ERROR_MESSAGE));
                }
            }
            return results;
        } catch (RemoteException e) {
            throw new MException("executeListCommand RemoteException");
        }

    }

    /**
     * execute command
     * 
     * @param commandType
     * @param paramsList
     * @return
     * @throws MException
     */
    public ContentValues executeListCommandOne(int commandType, List<ContentValues> paramsList) throws MException {
        final ContentValues result = executeListCommand(commandType, paramsList).get(0);
        return result;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#waitMillis(long)
     */
    public void waitMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception ex) {
            //
        }

    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#saveOcrCertificate(java.lang.String, java.lang.String)
     */
    public boolean saveOcrScreenCertificate(String expect, String reality) throws MException {
        final ContentValues params = new ContentValues(2);
        params.put(CommandConstants.KEY_SAVEOCRCERTIFICATE_EXPECT, expect);
        params.put(CommandConstants.KEY_SAVEOCRCERTIFICATE_REALITY, reality);
        final ContentValues result = executeCommand(CommandConstants.TYPE_SAVEOCRSCREENCERTIFICATE, params);
        return result.getAsBoolean(CommandConstants.KEY_METHOD_RESULT);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#saveOcrRectCertificate(java.lang.String, java.lang.String)
     */
    public boolean saveOcrRectCertificate(String expect, String reality) throws MException {
        final ContentValues params = new ContentValues(2);
        params.put(CommandConstants.KEY_SAVEOCRCERTIFICATE_EXPECT, expect);
        params.put(CommandConstants.KEY_SAVEOCRCERTIFICATE_REALITY, reality);
        final ContentValues result = executeCommand(CommandConstants.TYPE_SAVEOCRRECTCERTIFICATE, params);
        return result.getAsBoolean(CommandConstants.KEY_METHOD_RESULT);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#saveVerifyCertificate()
     */
    public boolean saveVerifyCertificate() throws MException {
        final ContentValues result = executeCommand(CommandConstants.TYPE_SAVEVERIFYCERTIFICATE, null);
        return result.getAsBoolean(CommandConstants.KEY_METHOD_RESULT);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#saveFindInCertificate()
     */
    public boolean saveFindInCertificate() throws MException {
        final ContentValues result = executeCommand(CommandConstants.TYPE_SAVEFINDINCERTIFICATE, null);
        return result.getAsBoolean(CommandConstants.KEY_METHOD_RESULT);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#wapOpe(com.aspire.android.test.execute.ContentValues)
     */
    public boolean wapOpen(String url) throws MException {
        return wapOpen(url, false);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#getMode()
     */
    public String getModel() throws MException {
        try {
            final ContentValues values = testService.execute(CommandConstants.TYPE_GETMODEL, null);
            return values.getAsString(CommandConstants.KEY_METHOD_RESULT);
        } catch (Exception e) {
            throw new MException(e, "getModel RemoteException");
        }
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#setDebugParams(com.aspire.android.test.execute.ContentValues)
     */
    public void setDebugParams(ContentValues params) throws MException {
        executeCommand(CommandConstants.TYPE_SETDEBUGPARAMS, params);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#getDebugResult()
     */
    public ContentValues getDebugResult() throws MException {
        try {
            return testService.execute(CommandConstants.TYPE_GETDEBUGRESULT, null);
        } catch (RemoteException e) {
            throw new MException("getResult error");
        }
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#keyClick(int)
     */
    public void keyClick(int key) throws MException {
        final ContentValues params = new ContentValues(1);
        params.put(CommandConstants.KEY_KEYCODE, key);
        executeCommand(CommandConstants.TYPE_KEYCLICK, params);

    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#keyLongClick(int)
     */
    public void keyLongClick(int key) throws MException {
        final ContentValues params = new ContentValues(1);
        params.put(CommandConstants.KEY_KEYCODE, key);
        executeCommand(CommandConstants.TYPE_KEYLONGCLICK, params);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#wapOpen(java.lang.String, boolean)
     */
    public boolean wapOpen(String url, boolean widgetTestEnable) throws MException {
        final ContentValues params = new ContentValues(1);
        params.put(CommandConstants.KEY_WAPOPE_URL, url);
        params.put(CommandConstants.KEY_WAPOPE_WIDGETTESTENABLE, widgetTestEnable);
        final ContentValues result = executeCommand(CommandConstants.TYPE_WAPOPEN, params);
        return result.getAsBoolean(CommandConstants.KEY_METHOD_RESULT);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#startGrabTcpPackage()
     */
    public void startGrabTcpPackage() throws MException {
        executeCommand(CommandConstants.TYPE_STARTGRABTCPPACKAGE, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#stopGrabTcpPackage()
     */
    public void stopGrabTcpPackage() throws MException {
        executeCommand(CommandConstants.TYPE_STOPGRABTCPPACKAGE, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#findWidget(java.lang.String, java.lang.String)
     */
    public List<ContentValues> findWidget(String key, String value) throws MException {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#countWidget(java.lang.String, java.lang.String)
     */
    public int countWidget(String key, String value) throws MException {
        // TODO 没有测试
        final ContentValues params = new ContentValues(2);
        params.put(CommandConstants.KEY_KEY, key);
        params.put(CommandConstants.KEY_VALUE, value);
        final ContentValues result = executeCommand(CommandConstants.TYPE_COUNTWIDGET, params);
        return result.getAsInteger(CommandConstants.KEY_METHOD_RESULT);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#clickWidget(java.lang.String, java.lang.String)
     */
    public boolean clickWidget(String key, String value) throws MException {
        // TODO 没有测试
        final ContentValues params = new ContentValues(2);
        params.put(CommandConstants.KEY_KEY, key);
        params.put(CommandConstants.KEY_VALUE, value);
        final ContentValues result = executeCommand(CommandConstants.TYPE_CLICKWIDGET, params);
        return result.getAsBoolean(CommandConstants.KEY_METHOD_RESULT);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#getWidgetText(java.lang.String, java.lang.String)
     */
    public String getWidgetText(String key, String value) throws MException {
        // TODO 没有测试
        final ContentValues params = new ContentValues(2);
        params.put(CommandConstants.KEY_KEY, key);
        params.put(CommandConstants.KEY_VALUE, value);
        final ContentValues result = executeCommand(CommandConstants.TYPE_GETWIDGETTEXT, params);
        return result.getAsString(CommandConstants.KEY_METHOD_RESULT);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#findWebWidget(java.lang.String, java.lang.String)
     */
    public List<ContentValues> findWebWidget(String key, String value) throws MException {
        final ContentValues params = new ContentValues(2);
        params.put(CommandConstants.KEY_KEY, key);
        params.put(CommandConstants.KEY_VALUE, value);
        return executeWithList(CommandConstants.TYPE_FINDWEBWIDGET, params);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#countWebWidget(java.lang.String, java.lang.String)
     */
    public int countWebWidget(String key, String value) throws MException {
        // TODO 没有测试
        final ContentValues params = new ContentValues(2);
        params.put(CommandConstants.KEY_KEY, key);
        params.put(CommandConstants.KEY_VALUE, value);
        final ContentValues result = executeCommand(CommandConstants.TYPE_COUNTWEBWIDGET, params);
        return result.getAsInteger(CommandConstants.KEY_METHOD_RESULT);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#clickWebWidget(java.lang.String, java.lang.String)
     */
    public boolean clickWebWidget(String key, String value) throws MException {
        // TODO 没有测试
        final ContentValues params = new ContentValues(2);
        params.put(CommandConstants.KEY_KEY, key);
        params.put(CommandConstants.KEY_VALUE, value);
        final ContentValues result = executeCommand(CommandConstants.TYPE_CLICKWEBWIDGET, params);
        return result.getAsBoolean(CommandConstants.KEY_METHOD_RESULT);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#getWebWidgetText(java.lang.String, java.lang.String)
     */
    public String getWebWidgetText(String key, String value) throws MException {
        // TODO 没有测试
        final ContentValues params = new ContentValues(2);
        params.put(CommandConstants.KEY_KEY, key);
        params.put(CommandConstants.KEY_VALUE, value);
        final ContentValues result = executeCommand(CommandConstants.TYPE_GETWEBWIDGETTEXT, params);
        return result.getAsString(CommandConstants.KEY_METHOD_RESULT);
    }

    /**
     * execute command
     * 
     * @param commandType
     * @param params
     * @return
     * @throws MException
     */
    public List<ContentValues> executeWithList(int commandType, ContentValues params) throws MException {
        final List<ContentValues> paramsList = new ArrayList<ContentValues>();
        paramsList.add(params);
        return executeListCommand(commandType, paramsList);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#wapClose()
     */
    public void wapClose() throws MException {
        executeCommand(CommandConstants.TYPE_WAPCLOSE, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#getResource(java.lang.String)
     */
    public String getResource(String key) throws MException {
        final ContentValues params = new ContentValues(1);
        params.put(CommandConstants.KEY_KEY, key);
        final ContentValues result = executeCommand(CommandConstants.TYPE_GETRESOURCE, params);
        return result.getAsString(CommandConstants.KEY_METHOD_RESULT);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#getResourceInt(java.lang.String)
     */
    public int getResourceInt(String key) throws MException {
        final String str = getResource(key);
        if (null == str)
            throw new MException("get the resoure key:" + key + " not found");
        try {
            final int value = Integer.parseInt(str);
            return value;
        } catch (Exception e) {
            throw new MException("get the resoure key:" + key + " return value:" + str + " not parser to int");
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#ocrWithResource(java.lang.String)
     */
    public boolean ocrWithResource(String key) throws MException {
        final ContentValues params = new ContentValues(1);
        params.put(CommandConstants.KEY_KEY, key);
        final ContentValues result = executeCommand(CommandConstants.TYPE_OCRWITHRESOURCE, params);
        return result.getAsBoolean(CommandConstants.KEY_METHOD_RESULT);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#alarm(java.lang.String, java.lang.String)
     */
    public void alarm(String code, String message) throws MException {
        final ContentValues params = new ContentValues(2);
        params.put(CommandConstants.KEY_ALARM_CODE, code);
        params.put(CommandConstants.KEY_ALARM_MESSAGE, message);
        executeCommand(CommandConstants.TYPE_ALARM, params);

    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#ocrScreenMatch(int, java.lang.String)
     */
    public boolean ocrScreenMatch(int lang, String expect) throws MException {
        final ContentValues params = new ContentValues(2);
        params.put(CommandConstants.KEY_OCR_LANG, lang);
        params.put(CommandConstants.KEY_OCR_EXPECT, expect);
        final ContentValues result = executeCommand(CommandConstants.TYPE_OCRSCREENMATCH, params);
        return result.getAsBoolean(CommandConstants.KEY_METHOD_RESULT);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#ocrRectMatch(int, int, int, int, int, java.lang.String)
     */
    public boolean ocrRectMatch(int startX, int startY, int width, int height, int lang, String expect)
            throws MException {
        final ContentValues params = new ContentValues();
        params.put(CommandConstants.KEY_OCRRECT_STARTX, startX);
        params.put(CommandConstants.KEY_OCRRECT_STARTY, startY);
        params.put(CommandConstants.KEY_OCRRECT_WIDTH, width);
        params.put(CommandConstants.KEY_OCRRECT_HEIGHT, height);
        params.put(CommandConstants.KEY_OCR_LANG, lang);
        params.put(CommandConstants.KEY_OCR_EXPECT, expect);
        final ContentValues result = executeCommand(CommandConstants.TYPE_OCRRECTMATCH, params);
        return result.getAsBoolean(CommandConstants.KEY_METHOD_RESULT);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#saveOcrScreenMatchCertificate()
     */
    public boolean saveOcrScreenMatchCertificate() throws MException {
        final ContentValues result = executeCommand(CommandConstants.TYPE_SAVEOCRSCREENMATCHCERTIFICATE, null);
        return result.getAsBoolean(CommandConstants.KEY_METHOD_RESULT);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#saveOcrRectMatchCertificate()
     */
    public boolean saveOcrRectMatchCertificate() throws MException {
        final ContentValues result = executeCommand(CommandConstants.TYPE_SAVEOCRRECTMATCHCERTIFICATE, null);
        return result.getAsBoolean(CommandConstants.KEY_METHOD_RESULT);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#getFileSize(java.lang.String, java.lang.String)
     */
    public long getFileSize(String path, String fileName) throws MException {
        if (null == path || (fileName != null && "".equals(fileName.trim())))
            throw new MException("the path and fileName not null!");
        final ContentValues params = new ContentValues(2);
        params.put(CommandConstants.KEY_PATH, path);
        params.put(CommandConstants.KEY_FILENAME, fileName);
        final ContentValues result = executeCommand(CommandConstants.TYPE_GETFILESIZE, params);
        return result.getAsLong(CommandConstants.KEY_METHOD_RESULT);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#deleteFile(java.lang.String, java.lang.String)
     */
    public boolean deleteFile(String path, String fileName) throws MException {
        if (null != path && null != fileName && !"".equals(fileName.trim())) {
            final ContentValues params = new ContentValues(2);
            params.put(CommandConstants.KEY_PATH, path);
            params.put(CommandConstants.KEY_FILENAME, fileName);
            final ContentValues result = executeCommand(CommandConstants.TYPE_DELETEFILE, params);
            return result.getAsBoolean(CommandConstants.KEY_METHOD_RESULT);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#screenMutilDown(int[][])
     */
    public boolean screenMutilDown(int[]... xys) throws MException {
        if (null != xys) {
            final List<ContentValues> list = new ArrayList<ContentValues>();
            ContentValues params = null;
            for (int[] xy : xys) {
                if (xy == null || xy.length < 2) {
                    return false;
                } else {
                    params = new ContentValues(2);
                    params.put(CommandConstants.KEY_POINT_X, xy[0]);
                    params.put(CommandConstants.KEY_POINT_Y, xy[1]);
                    list.add(params);
                }
            }
            final ContentValues result = executeListCommandOne(CommandConstants.TYPE_SCREENMUTILDOWN, list);
            return result.getAsBoolean(CommandConstants.KEY_METHOD_RESULT);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#screenMutilMove(int[][])
     */
    public boolean screenMutilMove(int[]... xys) throws MException {
        if (null != xys) {
            final List<ContentValues> list = new ArrayList<ContentValues>();
            ContentValues params = null;
            for (int[] xy : xys) {
                if (xy == null || xy.length < 2) {
                    return false;
                } else {
                    params = new ContentValues(2);
                    params.put(CommandConstants.KEY_POINT_X, xy[0]);
                    params.put(CommandConstants.KEY_POINT_Y, xy[1]);
                    list.add(params);
                }
            }
            final ContentValues result = executeListCommandOne(CommandConstants.TYPE_SCREENMUTILMOVE, list);
            return result.getAsBoolean(CommandConstants.KEY_METHOD_RESULT);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#screenMutilUp(int[][])
     */
    public boolean screenMutilUp(int[]... xys) throws MException {
        if (null != xys) {
            final List<ContentValues> list = new ArrayList<ContentValues>();
            ContentValues params = null;
            for (int[] xy : xys) {
                if (xy == null || xy.length < 2) {
                    return false;
                } else {
                    params = new ContentValues(2);
                    params.put(CommandConstants.KEY_POINT_X, xy[0]);
                    params.put(CommandConstants.KEY_POINT_Y, xy[1]);
                    list.add(params);
                }
            }
            final ContentValues result = executeListCommandOne(CommandConstants.TYPE_SCREENMUTILUP, list);
            return result.getAsBoolean(CommandConstants.KEY_METHOD_RESULT);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#screenZoom(int, int, int, int, int, int, int, int)
     */
    public boolean screenZoom(int sx, int sy, int sx1, int sy1, int ex, int ey, int ex1, int ey1) throws MException {
        return screenZoom(sx, sy, sx1, sy1, -1, -1, ex, ey, ex1, ey1, -1, -1);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#screenZoom(int, int, int, int, int, int, int, int, int,
     *      int, int, int)
     */
    public boolean screenZoom(int sx, int sy, int sx1, int sy1, int sx2, int sy2, int ex, int ey, int ex1, int ey1,
            int ex2, int ey2) throws MException {

        final List<ContentValues> list = new ArrayList<ContentValues>();

        final ContentValues startParams = new ContentValues(6);
        startParams.put(CommandConstants.KEY_POINT_X, sx);
        startParams.put(CommandConstants.KEY_POINT_Y, sy);
        startParams.put(CommandConstants.KEY_POINT_X1, sx1);
        startParams.put(CommandConstants.KEY_POINT_Y1, sy1);
        startParams.put(CommandConstants.KEY_POINT_X2, sx2);
        startParams.put(CommandConstants.KEY_POINT_Y2, sy2);
        list.add(startParams);

        final ContentValues endParams = new ContentValues(6);
        endParams.put(CommandConstants.KEY_POINT_X, ex);
        endParams.put(CommandConstants.KEY_POINT_Y, ey);
        endParams.put(CommandConstants.KEY_POINT_X1, ex1);
        endParams.put(CommandConstants.KEY_POINT_Y1, ey1);
        endParams.put(CommandConstants.KEY_POINT_X2, ex2);
        endParams.put(CommandConstants.KEY_POINT_Y2, ey2);
        list.add(endParams);

        final ContentValues result = executeListCommandOne(CommandConstants.TYPE_SCREENZOOM, list);
        return result.getAsBoolean(CommandConstants.KEY_METHOD_RESULT);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#screenShrink(int, int, int, int, int, int, int, int)
     */
    public boolean screenShrink(int sx, int sy, int sx1, int sy1, int ex, int ey, int ex1, int ey1) throws MException {
        return screenShrink(sx, sy, sx1, sy1, -1, -1, ex, ey, ex1, ey1, -1, -1);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#screenShrink(int, int, int, int, int, int, int, int, int,
     *      int, int, int)
     */
    public boolean screenShrink(int sx, int sy, int sx1, int sy1, int sx2, int sy2, int ex, int ey, int ex1, int ey1,
            int ex2, int ey2) throws MException {

        final List<ContentValues> list = new ArrayList<ContentValues>();

        final ContentValues startParams = new ContentValues(6);
        startParams.put(CommandConstants.KEY_POINT_X, sx);
        startParams.put(CommandConstants.KEY_POINT_Y, sy);
        startParams.put(CommandConstants.KEY_POINT_X1, sx1);
        startParams.put(CommandConstants.KEY_POINT_Y1, sy1);
        startParams.put(CommandConstants.KEY_POINT_X2, sx2);
        startParams.put(CommandConstants.KEY_POINT_Y2, sy2);
        list.add(startParams);

        final ContentValues endParams = new ContentValues(6);
        endParams.put(CommandConstants.KEY_POINT_X, ex);
        endParams.put(CommandConstants.KEY_POINT_Y, ey);
        endParams.put(CommandConstants.KEY_POINT_X1, ex1);
        endParams.put(CommandConstants.KEY_POINT_Y1, ey1);
        endParams.put(CommandConstants.KEY_POINT_X2, ex2);
        endParams.put(CommandConstants.KEY_POINT_Y2, ey2);
        list.add(endParams);

        final ContentValues result = executeListCommandOne(CommandConstants.TYPE_SCREENSHRINK, list);
        return result.getAsBoolean(CommandConstants.KEY_METHOD_RESULT);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#findInScreen(int, int, int, int, java.lang.String, int,
     *      int, int, int, int)
     */
    public int[] findInScreen(int inputStartX, int inputStartY, int inputWidth, int inputHeight,
            String templateImagePath, int templateStartX, int templateStartY, int w, int h, int colorToleranceAsPercent)
            throws MException {
        if (colorToleranceAsPercent < 0 && colorToleranceAsPercent > 100) {
            throw new MException("参数'colorToleranceAsPercent'应在0到100间");
        }

        final ContentValues params = new ContentValues();
        params.put(CommandConstants.KEY_INPUTSTARTX, inputStartX);
        params.put(CommandConstants.KEY_INPUTSTARTY, inputStartY);
        params.put(CommandConstants.KEY_INPUTWIDTH, inputWidth);
        params.put(CommandConstants.KEY_INPUTHEIGHT, inputHeight);
        params.put(CommandConstants.KEY_TEMPLATEIMAGEPATH, templateImagePath);
        params.put(CommandConstants.KEY_TEMPLATESTARTX, templateStartX);
        params.put(CommandConstants.KEY_TEMPLATESTARTY, templateStartY);
        params.put(CommandConstants.KEY_W, w);
        params.put(CommandConstants.KEY_H, h);
        params.put(CommandConstants.KEY_COLORTOLERANCEASPERCENT, colorToleranceAsPercent);
        final ContentValues result = executeCommand(CommandConstants.TYPE_FINDINSCREEN, params);
        int[] reInt = null;
        final Integer x = result.getAsInteger(CommandConstants.KEY_METHOD_RESULT_X);
        final Integer y = result.getAsInteger(CommandConstants.KEY_METHOD_RESULT_Y);
        if (null != x && null != y) {
            reInt = new int[] { x, y };
        }
        return reInt;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#findInScreen(int, int, java.lang.String, int, int, int,
     *      int, int)
     */
    public int[] findInScreen(int inputStartX, int inputStartY, String templateImagePath, int templateStartX,
            int templateStartY, int w, int h, int colorToleranceAsPercent) throws MException {
        return findInScreen(inputStartX, inputStartY, -1, -1, templateImagePath, templateStartX, templateStartY, w, h,
                colorToleranceAsPercent);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#stopApp()
     */
    public boolean stopApp(String packageName) throws MException {
        final ContentValues params = new ContentValues(1);
        params.put(CommandConstants.KEY_PACKAGENAME, packageName);
        final ContentValues result = executeCommand(CommandConstants.TYPE_STOPAPP, params);
        return result.getAsBoolean(CommandConstants.KEY_METHOD_RESULT);
    }

    /**
     * (non-Javadoc)
     * 
     * @throws MException
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#getCaseIndex()
     */
    public List<String> getCaseIndex() throws MException {
        final String case_index = getParams() != null ? getParams().getAsString(CommandConstants.KEY_CASE_INDEX) : null;
        if (null != case_index) {
            // 解析
            String[] str = case_index.split(",");
            List<String> list = new ArrayList<String>();
            for (int i = 0; i < str.length; i++) {
                list.add(str[i]);
            }
            return list;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#findInScreenCom(int, int, int, int, java.lang.String, int,
     *      int, int, int, int, int, int)
     */
    public int[] findInScreenCom(int inputStartX, int inputStartY, int inputWidth, int inputHeight,
            String templateImagePath, int templateStartX, int templateStartY, int w, int h,
            int colorToleranceAsPercent, int xStep, int yStep) throws MException {
        if (colorToleranceAsPercent < 0 && colorToleranceAsPercent > 100) {
            throw new MException("参数'colorToleranceAsPercent'应在0到100间");
        }

        final ContentValues params = new ContentValues();
        params.put(CommandConstants.KEY_INPUTSTARTX, inputStartX);
        params.put(CommandConstants.KEY_INPUTSTARTY, inputStartY);
        params.put(CommandConstants.KEY_INPUTWIDTH, inputWidth);
        params.put(CommandConstants.KEY_INPUTHEIGHT, inputHeight);
        params.put(CommandConstants.KEY_TEMPLATEIMAGEPATH, templateImagePath);
        params.put(CommandConstants.KEY_TEMPLATESTARTX, templateStartX);
        params.put(CommandConstants.KEY_TEMPLATESTARTY, templateStartY);
        params.put(CommandConstants.KEY_W, w);
        params.put(CommandConstants.KEY_H, h);
        params.put(CommandConstants.KEY_FINDINSCREENCOM_XSTEP, xStep);
        params.put(CommandConstants.KEY_FINDINSCREENCOM_YSTEP, yStep);
        params.put(CommandConstants.KEY_COLORTOLERANCEASPERCENT, colorToleranceAsPercent);
        final ContentValues result = executeCommand(CommandConstants.TYPE_FINDINSCREENCOM, params);
        int[] reInt = null;
        final Integer x = result.getAsInteger(CommandConstants.KEY_METHOD_RESULT_X);
        final Integer y = result.getAsInteger(CommandConstants.KEY_METHOD_RESULT_Y);
        if (null != x && null != y) {
            reInt = new int[] { x, y };
        }
        return reInt;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#saveScreen(java.lang.String, java.lang.String)
     */
    public String saveScreen(String patch, String name) throws MException {
        if (null == name || "".equals(name))
            throw new MException("the param name not is null");
        final ContentValues params = new ContentValues(2);
        params.put(CommandConstants.KEY_PATH, patch);
        params.put(CommandConstants.KEY_FILENAME, name);
        return executeCommand(CommandConstants.TYPE_SAVESCREEN, params).getAsString(CommandConstants.KEY_METHOD_RESULT);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#findPicture(java.lang.String, int, int, int, int,
     *      java.lang.String, int, int, int, int, int)
     */
    public int[] findPicture(String inputImagePath, int inputStartX, int inputStartY, int inputWidth, int inputHeight,
            String templateImagePath, int templateStartX, int templateStartY, int w, int h, int colorToleranceAsPercent)
            throws MException {
        if (colorToleranceAsPercent < 0 && colorToleranceAsPercent > 100) {
            throw new MException("参数'colorToleranceAsPercent'应在0到100间");
        }

        final ContentValues params = new ContentValues();
        params.put(CommandConstants.KEY_INPUTIMAGEPATH, inputImagePath);
        params.put(CommandConstants.KEY_INPUTSTARTX, inputStartX);
        params.put(CommandConstants.KEY_INPUTSTARTY, inputStartY);
        params.put(CommandConstants.KEY_INPUTWIDTH, inputWidth);
        params.put(CommandConstants.KEY_INPUTHEIGHT, inputHeight);
        params.put(CommandConstants.KEY_TEMPLATEIMAGEPATH, templateImagePath);
        params.put(CommandConstants.KEY_TEMPLATESTARTX, templateStartX);
        params.put(CommandConstants.KEY_TEMPLATESTARTY, templateStartY);
        params.put(CommandConstants.KEY_W, w);
        params.put(CommandConstants.KEY_H, h);
        params.put(CommandConstants.KEY_COLORTOLERANCEASPERCENT, colorToleranceAsPercent);
        final ContentValues result = executeCommand(CommandConstants.TYPE_FINDPICTURE, params);
        int[] reInt = null;
        final Integer x = result.getAsInteger(CommandConstants.KEY_METHOD_RESULT_X);
        final Integer y = result.getAsInteger(CommandConstants.KEY_METHOD_RESULT_Y);
        if (null != x && null != y) {
            reInt = new int[] { x, y };
        }
        return reInt;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#saveImageToCertificate(java.lang.String)
     */
    public boolean saveImageToCertificate(String path) throws MException {
        if (null != path && !"".equals(path.trim())) {
            final ContentValues params = new ContentValues(1);
            params.put(CommandConstants.KEY_PATH, path);
            final ContentValues result = executeCommand(CommandConstants.TYPE_SAVEIMAGETOCERTIFICATE, params);
            return result.getAsBoolean(CommandConstants.KEY_METHOD_RESULT);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#deleteInput(int, int)
     */
    public void deleteInput(int left, int rigth) throws MException {
        final ContentValues params = new ContentValues(2);
        params.put(CommandConstants.KEY_LEFT, left);
        params.put(CommandConstants.KEY_RIGHT, rigth);
        executeCommand(CommandConstants.TYPE_DELETEINPUT, params);

    }

}
