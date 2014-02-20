/**
 * @(#) TestService.java Created on 2012-4-26
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.aspire.android.common.exception.ExceptionHandler;
import com.aspire.android.common.exception.MException;
import com.aspire.android.common.share.AspShareUtil;
import com.aspire.android.log.Logger;
import com.aspire.android.ocr.IOcrService;
import com.aspire.android.test.device.sync.DeviceSync;
import com.aspire.android.test.execute.engine.EngineClient;
import com.aspire.android.test.execute.resource.CustomizeResource;
import com.aspire.android.test.execute.resource.OCRResource;
import com.aspire.android.test.execute.runner.ICaseRunner;
import com.aspire.android.test.execute.runner.ShellCaseRunner;
import com.aspire.android.test.testcase.AbstractTestCase;

/**
 * The class <code>TestService</code>
 * 
 * @author linjunsui
 * @version 1.0
 */
public class TestService extends Service {

    private static final String TAG = "TestService";

    /**
     * param Stack
     */
    protected Stack<ContentValues> paramStack = new Stack<ContentValues>();

    /**
     * result Stack
     */
    protected Stack<ContentValues> resultStack = new Stack<ContentValues>();

    /*
     * transaction stack
     */
    protected Stack<ContentValues> transactionStack = new Stack<ContentValues>();

    /**
     * loggers stack
     */
    protected Stack<Logger> loggers = new Stack<Logger>();

    /**
     * environment setting
     */
    protected ContentValues setting = new ContentValues();

    /**
     * environment setting
     */
    protected ContentValues globalVariables = new ContentValues();

    /**
     * caseRunner
     */
    protected ICaseRunner caseRunner;

    /**
     * device Entity
     */
    protected DeviceEntity deviceEntity;

    /**
     * aidl ocr service
     */
    private IOcrService ocrService;

    private String testMode = CommandConstants.MODE_DEBUG;

    private CustomizeResource resource;

    /**
     * aidl binder
     */
    private final ITestService.Stub binder = new ITestService.Stub() {

        /**
         * (non-Javadoc)
         * 
         * @see com.aspire.android.test.execute.ITestService#execute(int, com.aspire.android.test.execute.ContentValues)
         */
        public ContentValues execute(int commandType, ContentValues params) throws RemoteException {
            Log.d(TAG, "execute commandType:" + commandType + " params:" + params);
            switch (commandType) {
            case CommandConstants.TYPE_RUNTEST:
                return runCase(params);
            case CommandConstants.TYPE_SETPARAMS:
                paramStack.peek().putAll(params);
                return params;
            case CommandConstants.TYPE_GETPARAMS:
                return paramStack.peek();
            case CommandConstants.TYPE_GETMODEL:
                return getModel();
            case CommandConstants.TYPE_SETSETTING:
                setting = params;
                return setting;
                // 设置调试模式参数
            case CommandConstants.TYPE_SETDEBUGPARAMS:
                return setDebugParams(params);
            case CommandConstants.TYPE_GETSETTING:
                return setting;
            case CommandConstants.TYPE_SETGLOBALVARIABLES:
                globalVariables = params;
                return globalVariables;
            case CommandConstants.TYPE_GETGLOBALVARIABLES:
                return globalVariables;
            case CommandConstants.TYPE_SAVERESULT:
                if (params == null) {
                    return null;
                }
                ContentValues result = resultStack.peek();
                result.putAll(params);
                return params;
                // 生成凭证
            case CommandConstants.TYPE_SAVEOCRSCREENCERTIFICATE:
            case CommandConstants.TYPE_SAVEOCRRECTCERTIFICATE:
            case CommandConstants.TYPE_SAVEOCRRECTMATCHCERTIFICATE:
            case CommandConstants.TYPE_SAVEOCRSCREENMATCHCERTIFICATE:
            case CommandConstants.TYPE_SAVEVERIFYCERTIFICATE:
            case CommandConstants.TYPE_SAVEFINDINCERTIFICATE:
                return saveCertificate(commandType, params);

            case CommandConstants.TYPE_SAVETRANSACTION:
                return invokeSaveTransaction(params);
            case CommandConstants.TYPE_LOG:
                return invokeLog(params);
            case CommandConstants.TYPE_GET_SAVERESULT:
                break;
            case CommandConstants.TYPE_LOGSCREEN:
                return invokeLogScreen(params);
            case CommandConstants.TYPE_OCRSCREEN:
                return invokeOcrScreen(params);
            case CommandConstants.TYPE_OCRRECT:
                return invokeOcrRect(params);
            case CommandConstants.TYPE_SCREENDOWN:
                return invokeScreenDown(params);
            case CommandConstants.TYPE_SCREENUP:
                return invokeScreenUp(params);
            case CommandConstants.TYPE_SCREENMOVE:
                return invokeScreenMove(params);
            case CommandConstants.TYPE_SCREENCLICK:
                return invokeScreenClick(params);
            case CommandConstants.TYPE_SCREENTOUCH:
                return invokeScreenTouch(params);
            case CommandConstants.TYPE_KEYDOWN:
                return invokeKeyDown(params);
            case CommandConstants.TYPE_KEYUP:
                return invokeKeyUp(params);
            case CommandConstants.TYPE_KEYCLICK:
                return invokeKeyClick(params);
            case CommandConstants.TYPE_KEYLONGCLICK:
                return invokeKeyLongClick(params);
            case CommandConstants.TYPE_VERIFYSCREEN:
                return invokeVerifyScreen(params);
            case CommandConstants.TYPE_FINDINSCREEN:
                return invokeFindInScreen(params);
            case CommandConstants.TYPE_FINDINSCREENCOM:
                return invokeFindInScreenCom(params);
            case CommandConstants.TYPE_RUNACIVITY:
                break;
            case CommandConstants.TYPE_INSTALL:
                return invokeInstall(params);
            case CommandConstants.TYPE_UNINSTALL:
                return invokeUninstall(params);
            case CommandConstants.TYPE_GETURL:
                break;
            case CommandConstants.TYPE_INPUT:
                return invokeInput(params);
            case CommandConstants.TYPE_RUNAPP:
                return invokeRunApp(params);
            case CommandConstants.TYPE_STOPAPP:
                return invokeStopApp(params);
            case CommandConstants.TYPE_SENDMESSAGE:
                return invokeSendMessage(params);
            case CommandConstants.TYPE_SETMESSAGE:
                break;
            case CommandConstants.TYPE_WAPOPEN:
                return invokeWAPOpe(params);
                // 取得调试模式的返回值
            case CommandConstants.TYPE_GETDEBUGRESULT:
                return getDebugResult();
                // 删除全部短信
            case CommandConstants.TYPE_DELETEALLMESSAGE:
                return invokeDeleteAllMessage(params);
                // 把所有短信设置成已读
            case CommandConstants.TYPE_SETALLMESSAGEREADED:
                return invokeSetAllMessageReaded(params);
                // 开始抓包
            case CommandConstants.TYPE_STARTGRABTCPPACKAGE:
                return invokeStartGrabTcpPackage(params);
                // 结束抓包
            case CommandConstants.TYPE_STOPGRABTCPPACKAGE:
                return invokeStopGrabTcpPackage(params);
            case CommandConstants.TYPE_COUNTWIDGET:
                return invokeCountWidget(params);
            case CommandConstants.TYPE_CLICKWIDGET:
                return invokeClickWidget(params);
            case CommandConstants.TYPE_GETWIDGETTEXT:
                return invokeGetWidgetText(params);
            case CommandConstants.TYPE_COUNTWEBWIDGET:
                return invokeCountWebWidget(params);
            case CommandConstants.TYPE_CLICKWEBWIDGET:
                return invokeClickWebWidget(params);
            case CommandConstants.TYPE_GETWEBWIDGETTEXT:
                return invokeGetWebWidgetText(params);
            case CommandConstants.TYPE_WAPCLOSE:
                return invokeWapClose(params);
            case CommandConstants.TYPE_GETRESOURCE:
                return getResource(params);
            case CommandConstants.TYPE_OCRWITHRESOURCE:
                return invokeOcrWithResource(params);
            case CommandConstants.TYPE_ALARM:
                return alarm(params);
                // OCR 区域匹配
            case CommandConstants.TYPE_OCRRECTMATCH:
                return invokeOcrRectMatch(params);
                // OCR 全屏匹配
            case CommandConstants.TYPE_OCRSCREENMATCH:
                return invokeOcrScreenMatch(params);
            case CommandConstants.TYPE_GETFILESIZE:
                return invokeGetFileSize(params);
            case CommandConstants.TYPE_DELETEFILE:
                return invokeDeleteFile(params);
                // 保存当前屏幕截图
            case CommandConstants.TYPE_SAVESCREEN:
                return invokeSaveScreen(params);
                // 指定的大图找小图
            case CommandConstants.TYPE_FINDPICTURE:
                return invokeFindPicture(params);
            case CommandConstants.TYPE_SAVEIMAGETOCERTIFICATE:
                return invokeSaveImageToCertificate(params);
            case CommandConstants.TYPE_DELETEINPUT:
                return inovkeDeleteInput(params);
            default:
                break;
            }
            return null;
        }

        /**
         * (non-Javadoc)
         * 
         * @see com.aspire.android.test.execute.ITestService#executeWithList(int, java.util.List)
         */
        public List<ContentValues> executeWithList(int commandType, List<ContentValues> paramsList)
                throws RemoteException {
            Log.d(TAG, "executeWithList commandType:" + commandType + " paramsList:" + paramsList);
            switch (commandType) {
            case CommandConstants.TYPE_GETMESSAGE:
                return invokeGetMessage(paramsList.get(0));
                // case CommandConstants.TYPE_SCREENTOUCH:
                // return invokeScreenTouch(paramsList);
            case CommandConstants.TYPE_FINDWIDGET:
                return invokeFindWidget(paramsList.get(0));
            case CommandConstants.TYPE_FINDWEBWIDGET:
                return invokeFindWebWidget(paramsList.get(0));
            case CommandConstants.TYPE_SCREENMUTILDOWN:
                return invoekScreenMutilDown(paramsList);
            case CommandConstants.TYPE_SCREENMUTILMOVE:
                return invokeScreenMutilMove(paramsList);
            case CommandConstants.TYPE_SCREENMUTILUP:
                return invokeScreenMutilUp(paramsList);
                // 多点触控放大
            case CommandConstants.TYPE_SCREENZOOM:
                return invokeScreenZoom(paramsList);
                // 多点触控缩小
            case CommandConstants.TYPE_SCREENSHRINK:
                return invokeScreenShrink(paramsList);
            default:
                break;
            }
            return null;
        }

    };

    /**
     * service connection
     */
    protected ServiceConnection serviceConnection = new ServiceConnection() {

        /*
         * (non-Javadoc)
         * 
         * @see android.content.ServiceConnection#onServiceConnected(android.content.ComponentName, android.os.IBinder)
         */
        public void onServiceConnected(ComponentName name, IBinder service) {
            ocrService = IOcrService.Stub.asInterface(service);
            deviceEntity.setOcrService(ocrService);
        }

        /*
         * (non-Javadoc)
         * 
         * @see android.content.ServiceConnection#onServiceDisconnected(android.content.ComponentName)
         */
        public void onServiceDisconnected(ComponentName name) {
            ocrService = null;
        }

    };

    /**
     * (non-Javadoc)
     * 
     * @see android.app.Service#onCreate()
     */
    @Override
    public void onCreate() {
        initDeviceEntity();
    }

    /**
     * @param params
     * @return
     */
    protected ContentValues inovkeDeleteInput(ContentValues params) {
        Log.d(TAG, "inovkeDeleteInput");
        final int left = params.getAsInteger(CommandConstants.KEY_LEFT);
        final int right = params.getAsInteger(CommandConstants.KEY_RIGHT);
        logAction(getString(R.string.ts_deleteInput) + " left:" + left + " right:" + right);
        try {
            deviceEntity.deleteInput(left, right);
        } catch (MException e) {
            return printErrorAndSave(e, "Run deleteInput error");

        }
        return newSucessContentValues();
    }

    /**
     * @param params
     * @return
     */
    protected ContentValues invokeFindPicture(ContentValues params) {
        Log.d(TAG, "invokeFindPicture");
        try {
            String inputImagePath = params.getAsString(CommandConstants.KEY_INPUTIMAGEPATH);
            String templateImagePath = params.getAsString(CommandConstants.KEY_TEMPLATEIMAGEPATH);
            templateImagePath = paramStack.peek().getAsString(CommandConstants.KEY_CASE_ATTACHMENT_LOCTION)
                    + File.separator + templateImagePath;
            int inputStartX = params.getAsInteger(CommandConstants.KEY_INPUTSTARTX);
            final int inputStartY = params.getAsInteger(CommandConstants.KEY_INPUTSTARTY);
            final int inputWidth = params.getAsInteger(CommandConstants.KEY_INPUTWIDTH);
            final int inputHeight = params.getAsInteger(CommandConstants.KEY_INPUTHEIGHT);
            final int templateStartX = params.getAsInteger(CommandConstants.KEY_TEMPLATESTARTX);
            final int templateStartY = params.getAsInteger(CommandConstants.KEY_TEMPLATESTARTY);
            final int w = params.getAsInteger(CommandConstants.KEY_W);
            final int h = params.getAsInteger(CommandConstants.KEY_H);
            final int colorToleranceAsPercent = params.getAsInteger(CommandConstants.KEY_COLORTOLERANCEASPERCENT);
            // 加入日志
            logAction(getString(R.string.ts_findpicture) + "inputImagePath:" + inputImagePath + " inputStartX:"
                    + inputStartX + " inputStartY:" + inputStartY + " inputWidth:" + inputWidth + " inputHeight:"
                    + inputHeight + " templateImagePath:" + templateImagePath + " templateStartX:" + templateStartX
                    + " templateStartY:" + templateStartY + " w:" + w + " h:" + h + " colorToleranceAsPercent:"
                    + colorToleranceAsPercent);

            int[] reArray = deviceEntity.findPicture(inputImagePath, inputStartX, inputStartY, inputWidth, inputHeight,
                    templateImagePath, templateStartX, templateStartY, w, h, colorToleranceAsPercent);
            Log.d("TestService", "reArray=" + reArray);
            if (null != reArray && reArray.length == 2) {
                Log.d("TestService", "reArray x:" + reArray[0] + " y:" + reArray[1]);
                final ContentValues result = newSucessContentValues();
                result.put(CommandConstants.KEY_METHOD_RESULT_X, reArray[0]);
                result.put(CommandConstants.KEY_METHOD_RESULT_Y, reArray[1]);
                return result; // 返回查找到的正确结果
            } else {
                return newSucessContentValues();// 没有找到
            }
        } catch (MException e) {
            return printErrorAndSave(e, "Run invokeFindPicture error");
        }
    }

    /**
     * @param params
     * @return
     */
    protected ContentValues invokeSaveScreen(ContentValues params) {
        Log.d(TAG, "invokeSaveScreen");
        String path = params.getAsString(CommandConstants.KEY_PATH);
        final String name = params.getAsString(CommandConstants.KEY_FILENAME);
        if (null == path || "".equals(path)) {
            path = paramStack.peek().getAsString(CommandConstants.KEY_TEMP_LOCATION);
        }
        logAction(getString(R.string.ts_savescreen) + "path:" + path + " name:" + name);
        try {
            final String filepath = deviceEntity.saveScreen(path, name);
            return saveReturnParam(CommandConstants.KEY_METHOD_RESULT, filepath);
        } catch (MException e) {
            return printErrorAndSave(e, "Run invokeSaveScreen error");
        }
    }

    /**
     * @param params
     * @return
     */
    protected ContentValues invokeFindInScreenCom(ContentValues params) {
        Log.d(TAG, "invokeFindInScreenCom");
        try {
            String templateImagePath = params.getAsString(CommandConstants.KEY_TEMPLATEIMAGEPATH);
            templateImagePath = paramStack.peek().getAsString(CommandConstants.KEY_CASE_ATTACHMENT_LOCTION)
                    + File.separator + templateImagePath;
            int inputStartX = params.getAsInteger(CommandConstants.KEY_INPUTSTARTX);
            final int inputStartY = params.getAsInteger(CommandConstants.KEY_INPUTSTARTY);
            final int inputWidth = params.getAsInteger(CommandConstants.KEY_INPUTWIDTH);
            final int inputHeight = params.getAsInteger(CommandConstants.KEY_INPUTHEIGHT);
            final int templateStartX = params.getAsInteger(CommandConstants.KEY_TEMPLATESTARTX);
            final int templateStartY = params.getAsInteger(CommandConstants.KEY_TEMPLATESTARTY);
            final int w = params.getAsInteger(CommandConstants.KEY_W);
            final int h = params.getAsInteger(CommandConstants.KEY_H);
            final int colorToleranceAsPercent = params.getAsInteger(CommandConstants.KEY_COLORTOLERANCEASPERCENT);
            final int xStep = params.getAsInteger(CommandConstants.KEY_FINDINSCREENCOM_XSTEP);
            final int yStep = params.getAsInteger(CommandConstants.KEY_FINDINSCREENCOM_YSTEP);
            // 加入日志
            logAction(getString(R.string.ts_findinscreen) + "inputStartX:" + inputStartX + " inputStartY:"
                    + inputStartY + " inputWidth:" + inputWidth + " inputHeight:" + inputHeight + " templateImagePath:"
                    + templateImagePath + " templateStartX:" + templateStartX + " templateStartY:" + templateStartY
                    + " w:" + w + " h:" + h + " colorToleranceAsPercent:" + colorToleranceAsPercent + " xStep:" + xStep
                    + " yStep:" + yStep);

            int[] reArray = deviceEntity.findInScreenCom(inputStartX, inputStartY, inputWidth, inputHeight,
                    templateImagePath, templateStartX, templateStartY, w, h, colorToleranceAsPercent, xStep, yStep);
            Log.d("TestService", "reArray=" + reArray);
            if (null != reArray && reArray.length == 2) {
                Log.d("TestService", "reArray x:" + reArray[0] + " y:" + reArray[1]);
                final ContentValues result = newSucessContentValues();
                result.put(CommandConstants.KEY_METHOD_RESULT_X, reArray[0]);
                result.put(CommandConstants.KEY_METHOD_RESULT_Y, reArray[1]);
                return result; // 返回查找到的正确结果
            } else {
                return newSucessContentValues();// 没有找到
            }
        } catch (MException e) {
            return printErrorAndSave(e, "Run invokeFindInScreenCom error");
        }
    }

    /**
     * @param params
     * @return
     */
    protected ContentValues invokeStopApp(ContentValues params) {
        Log.d(TAG, "invokeStopApp");
        try {
            final String packageName = params.getAsString(CommandConstants.KEY_PACKAGENAME);
            logAction(getString(R.string.ts_stopapp) + "packageName:" + packageName);
            final boolean con = deviceEntity.stopApp(packageName);
            return saveReturnParam(CommandConstants.KEY_METHOD_RESULT, con);
        } catch (MException e) {
            // Handle
            ExceptionHandler.handle(e, "Run stopApp error");
            return saveReturnParam(CommandConstants.KEY_METHOD_RESULT, false);
        }
    }

    /**
     * @param paramsList
     * @return
     */
    protected List<ContentValues> invokeScreenShrink(List<ContentValues> paramsList) {
        Log.d(TAG, "invokeScreenShrink");
        final ContentValues startParams = paramsList.get(0);
        final ContentValues endParams = paramsList.get(1);
        int sx = startParams.getAsInteger(CommandConstants.KEY_POINT_X);
        int sy = startParams.getAsInteger(CommandConstants.KEY_POINT_Y);
        int sx1 = startParams.getAsInteger(CommandConstants.KEY_POINT_X1);
        int sy1 = startParams.getAsInteger(CommandConstants.KEY_POINT_Y1);
        int sx2 = startParams.getAsInteger(CommandConstants.KEY_POINT_X2);
        int sy2 = startParams.getAsInteger(CommandConstants.KEY_POINT_Y2);

        int ex = endParams.getAsInteger(CommandConstants.KEY_POINT_X);
        int ey = endParams.getAsInteger(CommandConstants.KEY_POINT_Y);
        int ex1 = endParams.getAsInteger(CommandConstants.KEY_POINT_X1);
        int ey1 = endParams.getAsInteger(CommandConstants.KEY_POINT_Y1);
        int ex2 = endParams.getAsInteger(CommandConstants.KEY_POINT_X2);
        int ey2 = endParams.getAsInteger(CommandConstants.KEY_POINT_Y2);
        try {
            logAction(getString(R.string.ts_screenshrink) + "sx:" + sx + " sy:" + sy + " sx1:" + sx1 + " sy1:" + sy1
                    + " sx2:" + sx2 + " sy2:" + sy2 + " ex:" + ex + " ey:" + ey + " ex1:" + ex1 + " ey1:" + ey1
                    + " ex2:" + ex2 + " ey2:" + ey2);
            final boolean con = deviceEntity.screenShrink(sx, sy, sx1, sy1, sx2, sy2, ex, ey, ex1, ey1, ex2, ey2);
            return saveListReturnParam(CommandConstants.KEY_METHOD_RESULT, con);
        } catch (MException e) {
            return printListErrorAndSave(e, "Run screenShrink error");
        }
    }

    /**
     * @param paramsList
     * @return
     */
    protected List<ContentValues> invokeScreenZoom(List<ContentValues> paramsList) {
        Log.d(TAG, "invokeScreenShrink");
        final ContentValues startParams = paramsList.get(0);
        final ContentValues endParams = paramsList.get(1);
        int sx = startParams.getAsInteger(CommandConstants.KEY_POINT_X);
        int sy = startParams.getAsInteger(CommandConstants.KEY_POINT_Y);
        int sx1 = startParams.getAsInteger(CommandConstants.KEY_POINT_X1);
        int sy1 = startParams.getAsInteger(CommandConstants.KEY_POINT_Y1);
        int sx2 = startParams.getAsInteger(CommandConstants.KEY_POINT_X2);
        int sy2 = startParams.getAsInteger(CommandConstants.KEY_POINT_Y2);

        int ex = endParams.getAsInteger(CommandConstants.KEY_POINT_X);
        int ey = endParams.getAsInteger(CommandConstants.KEY_POINT_Y);
        int ex1 = endParams.getAsInteger(CommandConstants.KEY_POINT_X1);
        int ey1 = endParams.getAsInteger(CommandConstants.KEY_POINT_Y1);
        int ex2 = endParams.getAsInteger(CommandConstants.KEY_POINT_X2);
        int ey2 = endParams.getAsInteger(CommandConstants.KEY_POINT_Y2);
        try {
            logAction(getString(R.string.ts_screenshrink) + "sx:" + sx + " sy:" + sy + " sx1:" + sx1 + " sy1:" + sy1
                    + " sx2:" + sx2 + " sy2:" + sy2 + " ex:" + ex + " ey:" + ey + " ex1:" + ex1 + " ey1:" + ey1
                    + " ex2:" + ex2 + " ey2:" + ey2);
            final boolean con = deviceEntity.screenZoom(sx, sy, sx1, sy1, sx2, sy2, ex, ey, ex1, ey1, ex2, ey2);
            return saveListReturnParam(CommandConstants.KEY_METHOD_RESULT, con);
        } catch (MException e) {
            return printListErrorAndSave(e, "Run screenZoom error");
        }
    }

    /**
     * @param paramsList
     * @return
     */
    protected List<ContentValues> invokeScreenMutilUp(List<ContentValues> paramsList) {
        Log.d(TAG, "invokeScreenMutilUp");
        int x = 0, y = 0;
        final int[][] points = new int[paramsList.size()][2];
        ContentValues value = null;
        final StringBuffer log = new StringBuffer(getString(R.string.ts_screenmutilup));
        for (int i = 0; i < paramsList.size(); i++) {
            value = paramsList.get(i);
            x = value.getAsInteger(CommandConstants.KEY_POINT_X);
            y = value.getAsInteger(CommandConstants.KEY_POINT_Y);
            points[i] = new int[] { x, y };
            log.append("x:" + x + " y:" + y + " ");
        }
        try {
            logAction(log.toString());
            final boolean con = deviceEntity.screenMutilUp(points);
            return saveListReturnParam(CommandConstants.KEY_METHOD_RESULT, con);
        } catch (MException e) {
            return printListErrorAndSave(e, "Run screenMutilUp error");
        }
    }

    /**
     * @param paramsList
     * @return
     */
    protected List<ContentValues> invokeScreenMutilMove(List<ContentValues> paramsList) {
        Log.d(TAG, "invokeScreenMutilMove");
        int x = 0, y = 0;
        final int[][] points = new int[paramsList.size()][2];
        ContentValues value = null;
        final StringBuffer log = new StringBuffer(getString(R.string.ts_screenmutilmove));
        for (int i = 0; i < paramsList.size(); i++) {
            value = paramsList.get(i);
            x = value.getAsInteger(CommandConstants.KEY_POINT_X);
            y = value.getAsInteger(CommandConstants.KEY_POINT_Y);
            points[i] = new int[] { x, y };
            log.append("x:" + x + " y:" + y + " ");
        }
        try {
            logAction(log.toString());
            final boolean con = deviceEntity.screenMutilMove(points);
            return saveListReturnParam(CommandConstants.KEY_METHOD_RESULT, con);
        } catch (MException e) {
            return printListErrorAndSave(e, "Run screenMutilMove error");
        }
    }

    /**
     * @param paramsList
     * @return
     */
    protected List<ContentValues> invoekScreenMutilDown(List<ContentValues> paramsList) {
        Log.d(TAG, "invoekScreenMutilDown");
        int x = 0, y = 0;
        final int[][] points = new int[paramsList.size()][2];
        ContentValues value = null;
        final StringBuffer log = new StringBuffer(getString(R.string.ts_screenmutildown));
        for (int i = 0; i < paramsList.size(); i++) {
            value = paramsList.get(i);
            x = value.getAsInteger(CommandConstants.KEY_POINT_X);
            y = value.getAsInteger(CommandConstants.KEY_POINT_Y);
            points[i] = new int[] { x, y };
            log.append("x:" + x + " y:" + y + " ");
        }
        try {
            logAction(log.toString());
            final boolean con = deviceEntity.screenMutilDown(points);
            return saveListReturnParam(CommandConstants.KEY_METHOD_RESULT, con);
        } catch (MException e) {
            return printListErrorAndSave(e, "Run screenMutilDown error");
        }
    }

    /**
     * @param params
     * @return
     */
    protected ContentValues invokeDeleteFile(ContentValues params) {
        Log.d(TAG, "invokeDeleteFile");
        final String path = params.getAsString(CommandConstants.KEY_PATH);
        final String fileName = params.getAsString(CommandConstants.KEY_FILENAME);
        logAction(getString(R.string.ts_deletefile) + "path:" + path + " fileName:" + fileName);
        try {
            final boolean isDelete = deviceEntity.deleteFile(path, fileName);
            return saveReturnParam(CommandConstants.KEY_METHOD_RESULT, isDelete);
        } catch (MException e) {
            return printErrorAndSave(e, "Run deleteFile error");
        }
    }

    /**
     * @param params
     * @return
     */
    protected ContentValues invokeGetFileSize(ContentValues params) {
        Log.d(TAG, "invokeGetFileSize");
        final String path = params.getAsString(CommandConstants.KEY_PATH);
        final String fileName = params.getAsString(CommandConstants.KEY_FILENAME);
        logAction(getString(R.string.ts_getfilesize) + "path:" + path + " fileName:" + fileName);
        try {
            final long size = deviceEntity.getFileSize(path, fileName);
            return saveReturnParam(CommandConstants.KEY_METHOD_RESULT, size);
        } catch (MException e) {
            return printErrorAndSave(e, "Run getFileSize error");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Service#onBind(android.content.Intent)
     */
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "one client binder");
        if (deviceEntity == null) {
            if (initDeviceEntity()) {
                return binder;
            } else {
                return null;
            }
        } else {
            return binder;
        }

    }

    /**
     * (non-Javadoc)
     * 
     * @see android.app.Service#onDestroy()
     */
    @Override
    public void onDestroy() {
        unbindService(serviceConnection);
        super.onDestroy();
    }

    /**
     * init the deviceEntity
     * 
     * @return true is success
     */
    private boolean initDeviceEntity() {
        try {
            Log.d(TAG, "initDeviceEntity");
            int port = 54000;

            try {
                final String configPort = AspShareUtil.get(this, AspShareUtil.AGENT_PORT_KEY);
                if (null != configPort) {
                    port = Integer.parseInt(configPort);
                }
            } catch (Exception e) {
            }

            deviceEntity = new DeviceEntity(new EngineClient("127.0.0.1", port));
            Intent ocrIntent = new Intent("com.aspire.android.ocr.IOcrService");
            bindService(ocrIntent, serviceConnection, Context.BIND_AUTO_CREATE);
            return true;
        } catch (IOException e) {
            ExceptionHandler.handle(e, "Bind error, error create device entity");
            deviceEntity = null;
            return false;
        }
    }

    /**
     * run case
     * 
     * @return result
     */
    protected ContentValues runCase(ContentValues params) {
        Log.d(TAG, "runCase");
        testMode = CommandConstants.MODE_RUN;// 设置成运行模式
        if (caseRunner == null) {
            caseRunner = new ShellCaseRunner();
        }
        // get case name
        String caseName = getCaseName(params);
        if (caseName == null) {
            ContentValues testResult = new ContentValues();
            testResult.put(CommandConstants.KEY_CASE_TESTRESULT, -1);
            final String errorMsg = "key CommandConstants.KEY_CASE_CLASSNAME is not found";
            return printErrorAndSave(new MException("errorMsg"), errorMsg);
        }
        try {
            initRunCase(params);
            final boolean runResult = runingRunCase(params);
            return resultRunCase(runResult);
        } catch (Exception e) {
            return printErrorAndSave(e, "Run case error" + getCaseName(params));
        } finally {
            resetRunCase();
        }

    }

    /**
     * init runcase
     * 
     * @param params
     */
    private void initRunCase(ContentValues params) {
        Log.d(TAG, "initRunCase");
        HashMap<String, String> environment = new HashMap<String, String>();
        environment.put(CommandConstants.KEY_CASE_LOGLOCATION,
                params.getAsString(CommandConstants.KEY_CASE_LOGLOCATION));
        environment.put(CommandConstants.KEY_CASE_LOG_LEVEL, params.getAsString(CommandConstants.KEY_CASE_LOG_LEVEL));
        final Logger logger = Logger.getLogger(this, "" + System.currentTimeMillis(), environment);
        loggers.push(logger);
        Log.d(TAG + "stack", " loggers.push" + loggers.size());
        // 读取Resources文件
        resource = CustomizeResource.getInstance();
        paramStack.push(params == null ? new ContentValues() : params);
        Log.d(TAG + "stack", " paramStack.push" + paramStack.size());
        resultStack.push(new ContentValues());
        Log.d(TAG + "stack", " resultStack.push" + resultStack.size());
        transactionStack.push(new ContentValues());
        Log.d(TAG + "stack", " transactionStack.push" + transactionStack.size());
    }

    /**
     * runing RunCase
     * 
     * @param params
     * @return
     * @throws MException
     */
    private boolean runingRunCase(ContentValues params) throws MException {
        Log.d(TAG, "runingRunCase");
        return caseRunner.runCase(setting, globalVariables, params);
    }

    /**
     * resultRunCase
     * 
     * @param runResult
     * @return
     */
    private ContentValues resultRunCase(boolean runResult) {
        Log.d(TAG, "resultRunCase");
        ContentValues testResult = resultStack.peek();
        if (testResult != null) {
            testResult.put(CommandConstants.KEY_CASE_TESTRESULT, runResult ? 1 : 0);
        }
        testResult.getTransactionDatas().addAll(transactionStack.peek().getTransactionDatas());
        testResult.put(CommandConstants.KEY_ERROR_CODE, CommandConstants.KEY_ERROR_CODE_SUCESS);
        return testResult;
    }

    /**
     * reset runcase
     */
    private void resetRunCase() {
        Log.d(TAG, "resetRunCase");
        loggers.pop().dispose();
        Log.d(TAG + "stack", " loggers.pop" + loggers.size());
        paramStack.pop();
        Log.d(TAG + "stack", " paramStack.pop" + paramStack.size());
        resultStack.pop();
        Log.d(TAG + "stack", " resultStack.pop" + resultStack.size());
        transactionStack.pop();
        Log.d(TAG + "stack", " transactionStack.pop" + transactionStack.size());
        deviceEntity.resetCertificateParams();// 清除所有的
        testMode = CommandConstants.MODE_DEBUG;
    }

    /**
     * get the case name from params
     * 
     * @param params
     *            case params
     * @return casename
     */
    private String getCaseName(ContentValues params) {
        return params.getAsString(CommandConstants.KEY_CASE_CLASSNAME);
    }

    /**
     * get resource
     * 
     * @param params
     * @return
     */
    protected ContentValues getResource(ContentValues params) {
        Log.d(TAG, "getResource");
        if (null != resource) {
            final String key = params.getAsString(CommandConstants.KEY_KEY);
            final String str = resource.getString(key);
            return saveReturnParam(CommandConstants.KEY_METHOD_RESULT, str);
        } else {
            final MException e = new MException("parser resource.xml error");
            return printErrorAndSave(e, "parser resource.xml error");
        }

    }

    /**
     * 告警
     * 
     * @param params
     * @return
     */
    protected ContentValues alarm(ContentValues params) {
        Log.d(TAG, "alarm");
        final String code = params.getAsString(CommandConstants.KEY_ALARM_CODE);
        final String message = params.getAsString(CommandConstants.KEY_ALARM_MESSAGE);
        logAction(getString(R.string.ts_alarm) + "code:" + code + " message:" + message);
        try {
            new DeviceSync().deviceAlarm(code, message);
        } catch (Exception e) {
            return newErrorContentValues(new MException(e));
        }
        return newSucessContentValues();
    }

    /**
     * ocr with resource
     * 
     * @param params
     * @return
     */
    protected ContentValues invokeOcrWithResource(ContentValues params) {
        Log.d(TAG, "ocrWithResource");
        final String key = params.getAsString(CommandConstants.KEY_KEY);
        logAction(getString(R.string.ts_ocrwithresource) + "key:" + key);
        try {
            final SharedPreferences prefs = getSharedPreferences(getPackageName() + "_preferences",
                    Context.MODE_PRIVATE);
            final String model = prefs.getString("devic_name", null);
            // final OcrParam ocrParam = resource.getOcrParam(key, model);
            final OCRResource ocrResource = resource.getOcrParam(key, model);
            final boolean pass = deviceEntity.ocrWithResource(ocrResource);
            return saveReturnParam(CommandConstants.KEY_METHOD_RESULT, pass);
        } catch (MException e) {
            return printErrorAndSave(e, "Run ocrWithResource error");
        }
    }

    /**
     * @param params
     * @return
     */
    protected ContentValues invokeOcrScreenMatch(ContentValues params) {
        Log.d(TAG, "invokeOcrScreenMatch");
        final int lang = params.getAsInteger(CommandConstants.KEY_OCR_LANG);
        final String expect = params.getAsString(CommandConstants.KEY_OCR_EXPECT);
        logAction(getString(R.string.ts_ocrscreen_match) + "lang:" + lang + " expect:" + expect);
        try {
            final boolean isMatched = deviceEntity.ocrScreenMatch(lang, expect);
            return saveReturnParam(CommandConstants.KEY_METHOD_RESULT, isMatched);
        } catch (MException e) {
            return printErrorAndSave(e, "Run ocrScreenMatch error");
        }
    }

    /**
     * @param params
     * @return
     */
    protected ContentValues invokeOcrRectMatch(ContentValues params) {
        Log.d(TAG, "invokeOcrRectMatch");
        final int startX = params.getAsInteger(CommandConstants.KEY_OCRRECT_STARTX);
        final int startY = params.getAsInteger(CommandConstants.KEY_OCRRECT_STARTY);
        final int width = params.getAsInteger(CommandConstants.KEY_OCRRECT_WIDTH);
        final int height = params.getAsInteger(CommandConstants.KEY_OCRRECT_HEIGHT);
        final int lang = params.getAsInteger(CommandConstants.KEY_OCR_LANG);
        final String expect = params.getAsString(CommandConstants.KEY_OCR_EXPECT);
        logAction(getString(R.string.ts_ocrrect_match) + "startX:" + startX + " startY:" + startY + " width:" + width
                + " height:" + height + " lang:" + switchLang(lang) + " expect:" + expect);
        try {
            final boolean isMatched = deviceEntity.ocrRectMatch(startX, startY, width, height, lang, expect);
            return saveReturnParam(CommandConstants.KEY_METHOD_RESULT, isMatched);
        } catch (MException e) {
            return printErrorAndSave(e, "Run ocrRectMatch error");
        }
    }

    /**
     * 调用保存凭证
     * 
     * @param params
     * @return
     */
    protected ContentValues saveCertificate(int commandType, ContentValues params) {
        Log.d(TAG, "saveCertificate");
        try {
            switch (commandType) {
            case CommandConstants.TYPE_SAVEOCRSCREENCERTIFICATE:
            case CommandConstants.TYPE_SAVEOCRRECTCERTIFICATE:
                final String expect = params.getAsString(CommandConstants.KEY_SAVEOCRCERTIFICATE_EXPECT);
                final String reality = params.getAsString(CommandConstants.KEY_SAVEOCRCERTIFICATE_REALITY);
                logAction(switchCertificate(commandType) + "expect:" + expect + " reality:" + reality);// 记录日志
                deviceEntity.saveOcrCertificate(commandType, expect, reality);
                break;
            case CommandConstants.TYPE_SAVEOCRRECTMATCHCERTIFICATE:
            case CommandConstants.TYPE_SAVEOCRSCREENMATCHCERTIFICATE:
                logAction(switchCertificate(commandType));// 记录日志
                deviceEntity.saveOcrCertificate(commandType, null, null);
                break;
            case CommandConstants.TYPE_SAVEVERIFYCERTIFICATE:
                logAction(switchCertificate(commandType));// 记录日志
                deviceEntity.saveVerifyCertificate();
                break;
            case CommandConstants.TYPE_SAVEFINDINCERTIFICATE:
                logAction(switchCertificate(commandType));// 记录日志
                deviceEntity.saveFindinCertificate();
                break;
            }
        } catch (MException e) {
            return printErrorAndSave(e, "");
        }
        return saveReturnParam(CommandConstants.KEY_METHOD_RESULT, true);
    }

    /**
     * 调用DeviceEntity saveTransaction
     * 
     * @param params
     * @return
     */
    protected ContentValues invokeSaveTransaction(ContentValues params) {
        Log.d(TAG, "invokeSaveTransaction");
        final long begintime = params.getAsLong(CommandConstants.KEY_SAVETRANSACTION_BEGINTIME);
        final long endtime = params.getAsLong(CommandConstants.KEY_SAVETRANSACTION_ENDTIME);
        final String name = params.getAsString(CommandConstants.KEY_SAVETRANSACTION_NAME);
        final String value = params.getAsString(CommandConstants.KEY_SAVETRANSACTION_VALUE);
        final String result = params.getAsString(CommandConstants.KEY_SAVETRANSACTION_RESULT);
        final boolean saveCertificate = params.getAsBoolean(CommandConstants.KEY_SAVETRANSACTION_SAVECERTIFICATE);
        try {
            final String attachment = paramStack.peek().getAsString(CommandConstants.KEY_CASE_CERTIFICATE_LOCATION);
            logAction(getString(R.string.ts_savetransaction) + " begintime:" + begintime + " endtime:" + endtime
                    + " name:" + name + " value:" + value + " result:" + result + " saveCertificate:" + saveCertificate);
            deviceEntity.saveTransaction(transactionStack.peek(), begintime, endtime, name, value, result,
                    saveCertificate, attachment);
            return newSucessContentValues();
        } catch (Exception e) {
            Log.e(TAG, "invokeSaveTransaction Error: " + e.getMessage(), e);
            return printErrorAndSave(e, "Run SaveTransaction error");
        }
    }

    /**
     * 调用DeviceEntity log
     * 
     * @param params
     * @return
     */
    protected ContentValues invokeLog(ContentValues params) {
        try {
            Log.d(TAG, "invokeLog");
            deviceEntity.log(params.getAsInteger(CommandConstants.KEY_LOG_LOGLEVEL),
                    params.getAsString(CommandConstants.KEY_LOG_MESSAGE), loggers.peek());
        } catch (MException e) {
            return printErrorAndSave(e, "Run Log error");
        }
        return newSucessContentValues();
    }

    /**
     * 调用DeviceEntity logScreen
     * 
     * @param params
     * @return
     */
    protected ContentValues invokeLogScreen(ContentValues params) {
        try {
            Log.d(TAG, "invokeLogScreen");
            deviceEntity.logScreen(params.getAsInteger(CommandConstants.KEY_LOG_LOGLEVEL),
                    params.getAsString(CommandConstants.KEY_LOG_MESSAGE), loggers.peek());
        } catch (MException e) {
            return printErrorAndSave(e, "Run LogScreen error");
        }
        return newSucessContentValues();
    }

    /**
     * 调用DeviceEntity ocrScreen
     * 
     * @param params
     * @return
     */
    protected ContentValues invokeOcrScreen(ContentValues params) throws RemoteException {
        try {
            Log.d(TAG, "invokeOcrScreen");
            final int lang = params.getAsInteger(CommandConstants.KEY_OCR_LANG);
            logAction(getString(R.string.ts_ocrscreen) + "lang:" + switchLang(lang));
            final String resultStr = deviceEntity.ocrScreen(lang);
            return saveReturnParam(CommandConstants.KEY_METHOD_RESULT, resultStr);
        } catch (MException e) {
            return printErrorAndSave(e, "Run OcrScreen error");
        }
    }

    /**
     * 调用DeviceEntity ocrRect
     * 
     * @param params
     * @return
     */
    protected ContentValues invokeOcrRect(ContentValues params) {
        Log.d(TAG, "invokeOcrRect");
        final int startX = params.getAsInteger(CommandConstants.KEY_OCRRECT_STARTX);
        final int startY = params.getAsInteger(CommandConstants.KEY_OCRRECT_STARTY);
        final int width = params.getAsInteger(CommandConstants.KEY_OCRRECT_WIDTH);
        final int height = params.getAsInteger(CommandConstants.KEY_OCRRECT_HEIGHT);
        final int lang = params.getAsInteger(CommandConstants.KEY_OCR_LANG);
        logAction(getString(R.string.ts_ocrrect) + "startX:" + startX + " startY:" + startY + " width:" + width
                + " height:" + height + " lang:" + switchLang(lang));
        try {
            final String resultStr = deviceEntity.ocrRect(startX, startY, width, height, lang);
            return saveReturnParam(CommandConstants.KEY_METHOD_RESULT, resultStr);
        } catch (MException e) {
            return printErrorAndSave(e, "Run OcrRect error");
        }
    }

    /**
     * 调用DeviceEntity screenDown
     * 
     * @param params
     * @return
     */
    protected ContentValues invokeScreenDown(ContentValues params) {
        Log.d(TAG, "invokeScreenDown");
        try {
            final int x = params.getAsInteger(CommandConstants.KEY_POINT_X);
            final int y = params.getAsInteger(CommandConstants.KEY_POINT_Y);
            logAction(getString(R.string.ts_screendown) + "x:" + x + " y:" + y);// 记录日志
            deviceEntity.screenDown(x, y);
        } catch (MException e) {
            return printErrorAndSave(e, "Run ScreenDown error");
        }
        return newSucessContentValues();
    }

    /**
     * 调用DeviceEntity screenMove
     * 
     * @param params
     * @return
     */
    protected ContentValues invokeScreenMove(ContentValues params) {
        Log.d(TAG, "invokeScreenMove");
        try {
            final int x = params.getAsInteger(CommandConstants.KEY_POINT_X);
            final int y = params.getAsInteger(CommandConstants.KEY_POINT_Y);
            logAction(getString(R.string.ts_screenmove) + "x:" + x + " y:" + y);// 记录日志
            deviceEntity.screenMove(x, y);
        } catch (MException e) {
            return printErrorAndSave(e, "Run ScreenMove error");
        }
        return newSucessContentValues();
    }

    /**
     * 调用DeviceEntity screenUp
     * 
     * @param params
     * @return
     */
    protected ContentValues invokeScreenUp(ContentValues params) {
        Log.d(TAG, "invokeScreenUp");
        try {
            final int x = params.getAsInteger(CommandConstants.KEY_POINT_X);
            final int y = params.getAsInteger(CommandConstants.KEY_POINT_Y);
            logAction(getString(R.string.ts_screenup) + "x:" + x + " y:" + y);// 记录日志
            deviceEntity.screenUp(x, y);
        } catch (MException e) {
            return printErrorAndSave(e, "Run ScreenUp error");
        }
        return newSucessContentValues();
    }

    /**
     * 调用DeviceEntity screenClick
     * 
     * @param params
     * @return
     */
    protected ContentValues invokeScreenClick(ContentValues params) {
        Log.d(TAG, "invokeScreenClick");
        try {
            final int x = params.getAsInteger(CommandConstants.KEY_POINT_X);
            final int y = params.getAsInteger(CommandConstants.KEY_POINT_Y);
            logAction(getString(R.string.ts_screenclick) + "x:" + x + " y:" + y);// 记录日志
            deviceEntity.screenClick(x, y);
        } catch (MException e) {
            return printErrorAndSave(e, "Run screenClick error");
        }
        return newSucessContentValues();
    }

    /**
     * screent touch
     * 
     * @param paramsList
     * @return
     */
    protected ContentValues invokeScreenTouch(ContentValues params) {
        Log.d(TAG, "invokeScreenTouch");
        try {
            final int x = params.getAsInteger(CommandConstants.KEY_POINT_X);
            final int y = params.getAsInteger(CommandConstants.KEY_POINT_Y);
            final int toX = params.getAsInteger(CommandConstants.KEY_POINT_X1);
            final int toY = params.getAsInteger(CommandConstants.KEY_POINT_Y1);
            logAction(getString(R.string.ts_screentouch) + "x:" + x + " y:" + y + " toX:" + toX + " toY:" + toY);
            deviceEntity.screenTouch(x, y, toX, toY, this);
            return newSucessContentValues();
        } catch (MException e) {
            return printErrorAndSave(e, "Run screenTouch error");
        }
    }

    /**
     * 调用DeviceEntity keyUp
     * 
     * @param params
     * @return
     */
    protected ContentValues invokeKeyUp(ContentValues params) {
        Log.d(TAG, "invokeKeyUp");
        try {
            final int keyCode = params.getAsInteger(CommandConstants.KEY_KEYCODE);
            logAction(getString(R.string.ts_keyup) + "keycode:" + keyCode);
            deviceEntity.keyUp(keyCode);
        } catch (MException e) {
            return printErrorAndSave(e, "Run keyUp error");
        }
        return newSucessContentValues();
    }

    /**
     * 调用DeviceEntity keyDown
     * 
     * @param params
     * @return
     */
    protected ContentValues invokeKeyDown(ContentValues params) {
        Log.d(TAG, "invokeKeyDown");
        try {
            final int keyCode = params.getAsInteger(CommandConstants.KEY_KEYCODE);
            logAction(getString(R.string.ts_keydown) + "keycode:" + keyCode);
            deviceEntity.keyDown(keyCode);
        } catch (MException e) {
            return printErrorAndSave(e, "Run keyDown error");
        }
        return newSucessContentValues();
    }

    /**
     * 
     * @param params
     * @return
     * @throws MException
     */
    protected ContentValues invokeKeyClick(ContentValues params) {
        Log.d(TAG, "invokeKeyClick");
        final int keyCode = params.getAsInteger(CommandConstants.KEY_KEYCODE);
        logAction(getString(R.string.ts_keyclick) + "keycode:" + keyCode);
        try {
            deviceEntity.keyClick(keyCode);
        } catch (MException e) {
            return printErrorAndSave(e, "Run keyClick error");
        }
        return newSucessContentValues();
    }

    /**
     * 
     * @param params
     * @return
     * @throws MException
     */
    protected ContentValues invokeKeyLongClick(ContentValues params) {
        Log.d(TAG, "invokeKeyLongClick");
        final int keyCode = params.getAsInteger(CommandConstants.KEY_KEYCODE);
        logAction(getString(R.string.ts_keylongclick) + "keycode:" + keyCode);
        try {
            deviceEntity.keyLongClick(keyCode);
            return newSucessContentValues();
        } catch (MException e) {
            return printErrorAndSave(e, "Run keyLongClick error");
        }
    }

    /**
     * 调用DeviceEntity verifyScreen
     * 
     * @param params
     * @return
     */
    protected ContentValues invokeVerifyScreen(ContentValues params) {
        Log.d(TAG, "invokeVerifyScreen");
        final int inputStartX = params.getAsInteger(CommandConstants.KEY_INPUTSTARTX);
        final int inputStartY = params.getAsInteger(CommandConstants.KEY_INPUTSTARTY);
        String templateImagePath = params.getAsString(CommandConstants.KEY_TEMPLATEIMAGEPATH);
        templateImagePath = paramStack.peek().getAsString(CommandConstants.KEY_CASE_ATTACHMENT_LOCTION)
                + File.separator + templateImagePath;
        final int templateStartX = params.getAsInteger(CommandConstants.KEY_TEMPLATESTARTX);
        final int templateStartY = params.getAsInteger(CommandConstants.KEY_TEMPLATESTARTY);
        final int w = params.getAsInteger(CommandConstants.KEY_W);
        final int h = params.getAsInteger(CommandConstants.KEY_H);
        final int colorToleranceAsPercent = params.getAsInteger(CommandConstants.KEY_COLORTOLERANCEASPERCENT);
        final int pixelTolerance = params.getAsInteger(CommandConstants.KEY_PIXELTOLERANCE);
        logAction(getString(R.string.ts_verifyscreen) + "inputStartX:" + inputStartX + " inputStartY:" + inputStartY
                + " templateImagePath:" + templateImagePath + " templateStartX:" + templateStartX + " templateStartY:"
                + templateStartY + " w:" + w + " h:" + h);
        try {
            final boolean resultBoolen = deviceEntity.verifyScreen(inputStartX, inputStartY, templateImagePath,
                    templateStartX, templateStartY, w, h, colorToleranceAsPercent, pixelTolerance);
            return saveReturnParam(CommandConstants.KEY_METHOD_RESULT, resultBoolen);
        } catch (MException e) {
            return printErrorAndSave(e, "Run verifyScreen error");
        }
    }

    /**
     * 调用DeviceEntity findInScreen
     * 
     * @param params
     * @return
     */
    protected ContentValues invokeFindInScreen(ContentValues params) {
        Log.d(TAG, "invokeFindInScreen");
        try {
            String templateImagePath = params.getAsString(CommandConstants.KEY_TEMPLATEIMAGEPATH);
            templateImagePath = paramStack.peek().getAsString(CommandConstants.KEY_CASE_ATTACHMENT_LOCTION)
                    + File.separator + templateImagePath;
            int inputStartX = params.getAsInteger(CommandConstants.KEY_INPUTSTARTX);
            final int inputStartY = params.getAsInteger(CommandConstants.KEY_INPUTSTARTY);
            final int inputWidth = params.getAsInteger(CommandConstants.KEY_INPUTWIDTH);
            final int inputHeight = params.getAsInteger(CommandConstants.KEY_INPUTHEIGHT);
            final int templateStartX = params.getAsInteger(CommandConstants.KEY_TEMPLATESTARTX);
            final int templateStartY = params.getAsInteger(CommandConstants.KEY_TEMPLATESTARTY);
            final int w = params.getAsInteger(CommandConstants.KEY_W);
            final int h = params.getAsInteger(CommandConstants.KEY_H);
            final int colorToleranceAsPercent = params.getAsInteger(CommandConstants.KEY_COLORTOLERANCEASPERCENT);
            // 加入日志
            logAction(getString(R.string.ts_findinscreen) + "inputStartX:" + inputStartX + " inputStartY:"
                    + inputStartY + " inputWidth:" + inputWidth + " inputHeight:" + inputHeight + " templateImagePath:"
                    + templateImagePath + " templateStartX:" + templateStartX + " templateStartY:" + templateStartY
                    + " w:" + w + " h:" + h + " colorToleranceAsPercent:" + colorToleranceAsPercent);

            int[] reArray = deviceEntity.findInScreen(inputStartX, inputStartY, inputWidth, inputHeight,
                    templateImagePath, templateStartX, templateStartY, w, h, colorToleranceAsPercent);
            Log.d("TestService", "reArray=" + reArray);
            if (null != reArray && reArray.length == 2) {
                Log.d("TestService", "reArray x:" + reArray[0] + " y:" + reArray[1]);
                final ContentValues result = newSucessContentValues();
                result.put(CommandConstants.KEY_METHOD_RESULT_X, reArray[0]);
                result.put(CommandConstants.KEY_METHOD_RESULT_Y, reArray[1]);
                return result; // 返回查找到的正确结果
            } else {
                return newSucessContentValues();// 没有找到
            }
        } catch (MException e) {
            return printErrorAndSave(e, "Run findInScreen error");
        }
    }

    /**
     * 调用DeviceEntity install
     * 
     * @param params
     * @return
     */
    protected ContentValues invokeInstall(ContentValues params) {
        Log.d(TAG, "invokeInstall");
        try {
            final String apkPath = params.getAsString(CommandConstants.KEY_APKPATH);
            logAction(getString(R.string.ts_install) + "apkPath:" + apkPath);
            final boolean reBoolean = deviceEntity.install(apkPath);
            return saveReturnParam(CommandConstants.KEY_METHOD_RESULT, reBoolean);
        } catch (MException e) {
            return printErrorAndSave(e, "Run install error");
        }
    }

    /**
     * 调用DeviceEntity uninstall
     * 
     * @param params
     * @return
     */
    protected ContentValues invokeUninstall(ContentValues params) {
        Log.d(TAG, "invokeUninstall");
        try {
            final String packageName = params.getAsString(CommandConstants.KEY_PACKAGENAME);
            logAction(getString(R.string.ts_uninstall) + "packageName:" + packageName);
            final boolean reBoolean = deviceEntity.uninstall(packageName);
            return saveReturnParam(CommandConstants.KEY_METHOD_RESULT, reBoolean);
        } catch (MException e) {
            return printErrorAndSave(e, "Run install error");
        }
    }

    /**
     * 调用DeviceEntity input
     * 
     * @param params
     * @return
     */
    protected ContentValues invokeInput(ContentValues params) {
        Log.d(TAG, "invokeInput");
        try {
            final String text = params.getAsString(CommandConstants.KEY_INPUT_TEXT);
            logAction(getString(R.string.ts_input) + "text:" + text);
            deviceEntity.input(text);
        } catch (MException e) {
            return printErrorAndSave(e, "Run input error");
        }
        return newSucessContentValues();
    }

    /**
     * 调用DeviceEntity runApp
     * 
     * @param params
     * @return
     */
    protected ContentValues invokeRunApp(ContentValues params) {
        Log.d(TAG, "invokeRunApp");
        try {
            final String packageName = params.getAsString(CommandConstants.KEY_PACKAGENAME);
            logAction(getString(R.string.ts_runapp) + "packageName:" + packageName);
            final boolean con = deviceEntity.runApp(packageName);
            return saveReturnParam(CommandConstants.KEY_METHOD_RESULT, con);
        } catch (MException e) {
            ExceptionHandler.handle(e, "Run runApp error");
            return saveReturnParam(CommandConstants.KEY_METHOD_RESULT, false);
        }
    }

    /**
     * 调用DeviceEntity sendMessage
     * 
     * @param params
     * @return
     */
    protected ContentValues invokeSendMessage(ContentValues params) {
        Log.d(TAG, "invokeSendMessage");
        final String receivers = params.getAsString(CommandConstants.KEY_SENDMESSAGE_RECEIVERS);
        final String content = params.getAsString(CommandConstants.KEY_SENDMESSAGE_CONTENT);
        try {
            logAction(getString(R.string.ts_sendmessage) + "receivers:" + receivers + " content:" + content);
            final boolean reBoolean = deviceEntity.sendMessage(receivers, content);
            return saveReturnParam(CommandConstants.KEY_METHOD_RESULT, reBoolean);
        } catch (MException e) {
            return printErrorAndSave(e, "Run sendMessage error");
        }
    }

    /**
     * 调用DeviceEntity getMessage
     * 
     * @param params
     * @return
     */
    protected List<ContentValues> invokeGetMessage(ContentValues params) {
        Log.d(TAG, "invokeGetMessage");
        try {
            if (null == params)
                throw new MException("the params is null");
            logAction(getString(R.string.ts_getmessage) + params);
            return addListSucessContentValues(deviceEntity.getMessage(params));
        } catch (Exception e) {
            final MException exception = ExceptionHandler.handle(e, "Run getMessage error");
            return newListErrorContentValues(exception);
        }
    }

    /**
     * 调用DeviceEntity deleteAllMessage
     * 
     * @param params
     * @return
     */
    protected ContentValues invokeDeleteAllMessage(ContentValues params) {
        Log.d(TAG, "invokeDeleteAllMessage");
        logAction(getString(R.string.ts_deleteallmessage));
        try {
            deviceEntity.deleteAllMessage();
        } catch (MException e) {
            return printErrorAndSave(e, "Run deleteAllMessage error");
        }
        return newSucessContentValues();
    }

    /**
     * 调用DeviceEntity setAllMessageReaded
     * 
     * @param params
     * @return
     */
    protected ContentValues invokeSetAllMessageReaded(ContentValues params) {
        Log.d(TAG, "invokeSetAllMessageReaded");
        logAction(getString(R.string.ts_setallmessagereaded));
        try {
            deviceEntity.setAllMessageReaded();
        } catch (MException e) {
            return printErrorAndSave(e, "Run setAllMessageReaded error");
        }
        return newSucessContentValues();
    }

    /**
     * open url in browser
     * 
     * @param params
     * @return
     */
    protected ContentValues invokeWAPOpe(ContentValues params) {
        Log.d(TAG, "invokeWAPOpe");
        final String url = params.getAsString(CommandConstants.KEY_WAPOPE_URL);
        final boolean widgetTestEnable = params.getAsBoolean(CommandConstants.KEY_WAPOPE_WIDGETTESTENABLE);
        try {
            // 加入日志
            logAction(getString(R.string.ts_wapope) + "url:" + url + " widgetTestEnable" + widgetTestEnable);
            boolean reBoolean = false;
            if (widgetTestEnable) {
                // 支持控件化的
                reBoolean = deviceEntity.wapOpen(url, widgetTestEnable);
            } else {
                // 不支持控件化的
                reBoolean = deviceEntity.wapOpen(url);
            }
            return saveReturnParam(CommandConstants.KEY_METHOD_RESULT, reBoolean);
        } catch (MException e) {
            return printErrorAndSave(e, "Run invokeWAPOpe error");
        }

    }

    protected ContentValues invokeSaveImageToCertificate(ContentValues params) {
        Log.d(TAG, "invokeSaveImageToCertificate");
        final String path = params.getAsString(CommandConstants.KEY_PATH);
        logAction(getString(R.string.ts_saveimagetocertificate) + "path:" + path);
        try {
            final boolean isSuccess = deviceEntity.saveImageToCertificate(path);
            return saveReturnParam(CommandConstants.KEY_METHOD_RESULT, isSuccess);
        } catch (Exception e) {
            return printErrorAndSave(e, "Run saveImageToCertificate error");
        }
    }

    /**
     * start grab network package
     * 
     * @return
     */
    protected ContentValues invokeStartGrabTcpPackage(ContentValues params) {
        Log.d(TAG, "invokeStartGrabTcpPackage");
        final String isGrabTcp = paramStack.peek().getAsString(CommandConstants.KEY_CASE_GRABTCP);
        if (null == isGrabTcp || "1".equals(isGrabTcp.trim())) {
            try {
                final String path = paramStack.peek().getAsString(CommandConstants.KEY_CASE_TCPDUM_LOCATION);
                logAction(getString(R.string.ts_startgrabtcppackage) + " path:" + path);
                final String rePath = deviceEntity.startGrabTcpPackage(path);
                logAction("tcpdump save filepath:" + rePath);
                logAction(getString(R.string.ts_startgrabtcppackage));
            } catch (MException e) {
                return printErrorAndSave(e, "Run startGrabTcpPackage error");
            }
        }
        return newSucessContentValues();
    }

    /**
     * stop grab network package
     * 
     * @return
     */
    protected ContentValues invokeStopGrabTcpPackage(ContentValues params) {
        Log.d(TAG, "invokeStartGrabTcpPackage");
        // 加入日志
        final String isGrabTcp = paramStack.peek().getAsString(CommandConstants.KEY_CASE_GRABTCP);
        if (null == isGrabTcp || "1".equals(isGrabTcp.trim())) {
            try {
                logAction(getString(R.string.ts_stopgrabtcppackage) + " isGrabTcp" + isGrabTcp);
                final String path = deviceEntity.stopGrabTcpPackage();
                Log.d(TAG, "invokeStopGrabTcpPackage path:" + path);
            } catch (MException e) {
                return printErrorAndSave(e, "Run stopGrabTcpPackage error");
            }
        }

        return newSucessContentValues();
    }

    /**
     * find view controls
     * 
     * @param params
     * @return
     */
    public List<ContentValues> invokeFindWidget(ContentValues params) {
        Log.d(TAG, "invokeStartGrabTcpPackage");
        final String key = params.getAsString(CommandConstants.KEY_KEY);
        final String value = params.getAsString(CommandConstants.KEY_VALUE);
        logAction(getString(R.string.ts_findwidget) + "key:" + key + " value:" + value);
        try {
            deviceEntity.findWidget(key, value);
        } catch (MException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * find view controls count
     * 
     * @param params
     * @return
     */
    public ContentValues invokeCountWidget(ContentValues params) {
        Log.d(TAG, "invokeCountWidget");
        try {
            final String key = params.getAsString(CommandConstants.KEY_KEY);
            final String value = params.getAsString(CommandConstants.KEY_VALUE);
            logAction(getString(R.string.ts_countwidget) + "key:" + key + " value:" + value);
            final int count = deviceEntity.countWidget(key, value);
            return saveReturnParam(CommandConstants.KEY_METHOD_RESULT, count);
        } catch (MException e) {
            return printErrorAndSave(e, "Run countWidget error");
        }
    }

    /**
     * click the find view control
     * 
     * @param params
     * @return
     */
    public ContentValues invokeClickWidget(ContentValues params) {
        Log.d(TAG, "invokeClickWidget");
        try {
            final String key = params.getAsString(CommandConstants.KEY_KEY);
            final String value = params.getAsString(CommandConstants.KEY_VALUE);
            logAction(getString(R.string.ts_clickwidget) + "key:" + key + " value:" + value);
            final boolean con = deviceEntity.clickWidget(key, value);
            return saveReturnParam(CommandConstants.KEY_METHOD_RESULT, con);
        } catch (MException e) {
            return printErrorAndSave(e, "Run clickWidget error");
        }
    }

    /**
     * find the view control , get mText;
     * 
     * @param params
     * @return
     */
    public ContentValues invokeGetWidgetText(ContentValues params) {
        Log.d(TAG, "invokeGetWidgetText");
        try {
            final String key = params.getAsString(CommandConstants.KEY_KEY);
            final String value = params.getAsString(CommandConstants.KEY_VALUE);
            logAction(getString(R.string.ts_getwidgettext) + "key:" + key + " value:" + value);
            final String str = deviceEntity.getWidgetText(key, value);
            return saveReturnParam(CommandConstants.KEY_METHOD_RESULT, str);
        } catch (MException e) {
            return printErrorAndSave(e, "Run getWidgetText error");
        }
    }

    /**
     * find the web view controls
     * 
     * @param params
     * @return
     */
    public List<ContentValues> invokeFindWebWidget(ContentValues params) {
        Log.d(TAG, "invokeFindWebWidget");
        final String key = params.getAsString(CommandConstants.KEY_KEY);
        final String value = params.getAsString(CommandConstants.KEY_VALUE);
        logAction(getString(R.string.ts_findwebwidget) + "key:" + key + " value:" + value);
        try {
            final List<ContentValues> values = deviceEntity.findWebWidget(key, value);
            return addListSucessContentValues(values);
        } catch (MException e) {
            return printListErrorAndSave(e, "Run findWebWidget error");
        }
    }

    /**
     * find web view controls count
     * 
     * @param params
     * @return
     */
    public ContentValues invokeCountWebWidget(ContentValues params) {
        Log.d(TAG, "invokeCountWebWidget");
        try {
            final String key = params.getAsString(CommandConstants.KEY_KEY);
            final String value = params.getAsString(CommandConstants.KEY_VALUE);
            logAction(getString(R.string.ts_countwebwidget) + "key:" + key + " value:" + value);
            final int count = deviceEntity.countWebWidget(key, value);
            return saveReturnParam(CommandConstants.KEY_METHOD_RESULT, count);
        } catch (MException e) {
            return printErrorAndSave(e, "Run countWebWidget error");
        }
    }

    /**
     * click the web view controls
     * 
     * @param params
     * @return
     */
    public ContentValues invokeClickWebWidget(ContentValues params) {
        Log.d(TAG, "invokeClickWebWidget");
        try {
            final String key = params.getAsString(CommandConstants.KEY_KEY);
            final String value = params.getAsString(CommandConstants.KEY_VALUE);
            logAction(getString(R.string.ts_clickwebwidget) + "key:" + key + " value:" + value);
            final boolean con = deviceEntity.clickWebWidget(key, value);
            return saveReturnParam(CommandConstants.KEY_METHOD_RESULT, con);
        } catch (MException e) {
            return printErrorAndSave(e, "Run clickWebWidget error");
        }
    }

    /**
     * find web view controls ,get mText
     * 
     * @param params
     * @return
     */
    public ContentValues invokeGetWebWidgetText(ContentValues params) {
        Log.d(TAG, "invokeGetWebWidgetText");
        try {
            final String key = params.getAsString(CommandConstants.KEY_KEY);
            final String value = params.getAsString(CommandConstants.KEY_VALUE);
            logAction(getString(R.string.ts_getwebwidgettext) + "key:" + key + " value:" + value);
            final String str = deviceEntity.getWebWidgetText(key, value);
            return saveReturnParam(CommandConstants.KEY_METHOD_RESULT, str);
        } catch (MException e) {
            return printErrorAndSave(e, "Run getWebWidgetText error");
        }
    }

    /**
     * closed browser
     * 
     * @param params
     * @return
     */
    protected ContentValues invokeWapClose(ContentValues params) {
        Log.d(TAG, "invokeWapClose");
        try {
            logAction(getString(R.string.ts_wapclose));
            deviceEntity.wapClose();
            return newSucessContentValues();
        } catch (MException e) {
            return printErrorAndSave(e, "Run wapClose error");
        }
    }

    /**
     * get model
     * 
     * @return
     */
    protected ContentValues getModel() {
        Log.d(TAG, "getModel");
        final ContentValues values = new ContentValues();
        values.put(CommandConstants.KEY_METHOD_RESULT, testMode);
        return values;
    }

    protected ContentValues setDebugParams(ContentValues params) {
        Log.d(TAG, "setDebugParams");
        try {
            initRunCase(params);
        } catch (Exception e) {
            return printErrorAndSave(e, "Run setDebugParams error");
        }

        return newSucessContentValues();
    }

    /**
     * get debug result values
     * 
     * @return
     */
    protected ContentValues getDebugResult() {
        Log.d(TAG, "getDebugResult");
        final ContentValues debugResult = resultRunCase(true);
        resetRunCase();
        return debugResult;
    }

    /**
     * 包装结果集参数
     * 
     * @param key
     * @param value
     * @return
     */
    protected static ContentValues saveReturnParam(String key, Object value) {
        final ContentValues result = new ContentValues();
        // add the result ContentValues invoke sucess
        result.put(CommandConstants.KEY_ERROR_CODE, CommandConstants.KEY_ERROR_CODE_SUCESS);
        if (value instanceof Byte) {
            result.put(key, (Byte) value);
        } else if (value instanceof Short) {
            result.put(key, (Short) value);
        } else if (value instanceof Integer) {
            result.put(key, (Integer) value);
        } else if (value instanceof Long) {
            result.put(key, (Long) value);
        } else if (value instanceof Float) {
            result.put(key, (Float) value);
        } else if (value instanceof Double) {
            result.put(key, (Double) value);
        } else if (value instanceof Boolean) {
            result.put(key, (Boolean) value);
        } else if (value instanceof String) {
            result.put(key, (String) value);
        }
        return result;
    }

    /**
     * 包装结果集参数
     * 
     * @param key
     * @param value
     * @return
     */
    protected static List<ContentValues> saveListReturnParam(String key, Object value) {
        final ContentValues result = saveReturnParam(key, value);
        final List<ContentValues> list = new ArrayList<ContentValues>();
        list.add(result);
        return list;
    }

    /**
     * if invoke deviceEntity to agent sucess if not result parames
     * 
     * @param erroMessage
     * @return
     */
    protected static ContentValues newSucessContentValues() {
        final ContentValues result = new ContentValues();
        result.put(CommandConstants.KEY_ERROR_CODE, CommandConstants.KEY_ERROR_CODE_SUCESS);
        return result;
    }

    /**
     * if invoke deviceEntity to agent sucess if not result parames
     * 
     * @param erroMessage
     * @return
     */
    protected static List<ContentValues> newListSucessContentValues() {
        final List<ContentValues> list = new ArrayList<ContentValues>();
        final ContentValues result = new ContentValues();
        result.put(CommandConstants.KEY_ERROR_CODE, CommandConstants.KEY_ERROR_CODE_SUCESS);
        list.add(result);
        return list;
    }

    /**
     * if invoke deviceEntity to agent sucess if not result parames
     * 
     * @param erroMessage
     * @return
     */
    protected static List<ContentValues> addListSucessContentValues(List<ContentValues> values) {
        if (null != values && !values.isEmpty()) {
            final ContentValues result = values.get(0);
            result.put(CommandConstants.KEY_ERROR_CODE, CommandConstants.KEY_ERROR_CODE_SUCESS);
        }
        return values;
    }

    /**
     * if invoke deviceEntity to agent error result
     * 
     * @param erroMessage
     * @return
     */
    protected static ContentValues newErrorContentValues(MException mException) {
        final ContentValues result = new ContentValues();
        result.put(CommandConstants.KEY_ERROR_CODE, CommandConstants.KEY_ERROR_CODE_FAIL);
        result.put(CommandConstants.KEY_ERROR_MESSAGE, mException.getUiMessage());
        return result;
    }

    /**
     * if invoke deviceEntity to agent error result
     * 
     * @param mException
     * @return
     */
    protected static List<ContentValues> newListErrorContentValues(MException mException) {
        ArrayList<ContentValues> contentValues = new ArrayList<ContentValues>(1);
        contentValues.add(newErrorContentValues(mException));
        return contentValues;
    }

    /**
     * print error message and save error return to the client
     * 
     * @param e
     * @param message
     * @return
     */
    private static ContentValues printErrorAndSave(Exception e, String message) {
        final MException exception = ExceptionHandler.handle(e, message);
        return newErrorContentValues(exception);
    }

    /**
     * print error message and save error return to the client
     * 
     * @param e
     * @param message
     * @return
     */
    private static List<ContentValues> printListErrorAndSave(Exception e, String message) {
        final MException exception = ExceptionHandler.handle(e, message);
        return newListErrorContentValues(exception);
    }

    /**
     * log the action
     * 
     * @param msg
     */
    private void logAction(String msg) {
        final Logger logger = getCurrentLogger();
        if (null != logger)
            logger.log(CommandConstants.LOG_LEVEL_DEBUG, msg);
    }

    /**
     * get current logger
     * 
     * @return
     */
    private Logger getCurrentLogger() {
        if (!loggers.isEmpty()) {
            return loggers.peek();
        }
        return null;

    }

    /**
     * switch lang type
     * 
     * @param lang
     * @return
     */
    private static String switchLang(int lang) {
        switch (lang) {
        case AbstractTestCase.OCR_NUMBER:
            return "OCR_NUMBER";
        case AbstractTestCase.OCR_ENGLISH:
            return "OCR_ENGLISH";
        case AbstractTestCase.OCR_SIMPLIPIED_CHINESE:
            return "OCR_SIMPLIPIED_CHINESE";
        case AbstractTestCase.OCR_TRADITIONAL_CHINESE:
            return "OCR_TRADITIONAL_CHINESE";
        default:
            return "";
        }
    }

    /**
     * switch certificate type
     * 
     * @param certificate
     * @return
     */
    private String switchCertificate(int certificate) {
        switch (certificate) {
        case CommandConstants.TYPE_SAVEOCRSCREENCERTIFICATE:
        case CommandConstants.TYPE_SAVEOCRSCREENMATCHCERTIFICATE:
            return getString(R.string.ts_savecertificate_ocrscreen);
        case CommandConstants.TYPE_SAVEOCRRECTCERTIFICATE:
        case CommandConstants.TYPE_SAVEOCRRECTMATCHCERTIFICATE:
            return getString(R.string.ts_savecertificate_ocrrect);
        case CommandConstants.TYPE_SAVEVERIFYCERTIFICATE:
            return getString(R.string.ts_savecertificate_verify);
        case CommandConstants.TYPE_SAVEFINDINCERTIFICATE:
            return getString(R.string.ts_savecertificate_find);
        default:
            return null;
        }
    }
}
