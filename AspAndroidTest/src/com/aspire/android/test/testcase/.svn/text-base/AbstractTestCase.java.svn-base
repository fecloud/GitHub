/**
 * @(#) AbstractTestCase.java Created on 2012-4-23
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.testcase;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.aspire.android.test.environment.Environment;
import com.aspire.android.test.environment.IDeviceEntity;
import com.aspire.android.test.environment.IEnvironment;
import com.aspire.android.test.exception.MException;
import com.aspire.android.test.execute.CommandConstants;
import com.aspire.android.test.execute.ContentValues;

/**
 * The class <code>AbstractTestCase</code>
 * 
 * @author linjunsui
 * @version 1.0
 */
public abstract class AbstractTestCase extends AndroidTestCase implements ITestCase {

    private static final String TAG = "AbstractTestCase";

    /**
     * 日志等级 最详细的
     */
    public static final int LOG_LEVEL_VERBOSE = CommandConstants.LOG_LEVEL_VERBOSE;
    /**
     * 日志等级 调试
     */
    public static final int LOG_LEVEL_DEBUG = CommandConstants.LOG_LEVEL_DEBUG;
    /**
     * 日志等级 详细
     */
    public static final int LOG_LEVEL_INFO = CommandConstants.LOG_LEVEL_INFO;
    /**
     * 日志等级 警告
     */
    public static final int LOG_LEVEL_WARN = CommandConstants.LOG_LEVEL_WARN;
    /**
     * 日志等级 错误
     */
    public static final int LOG_LEVEL_ERROR = CommandConstants.LOG_LEVEL_ERROR;
    /**
     * 日志等级 致命错误
     */
    public static final int LOG_LEVEL_FATAL = CommandConstants.LOG_LEVEL_FATAL;

    /**
     * OCR 数字类型
     */
    public static final int OCR_NUMBER = 0;
    /**
     * OCR 英文类型
     */
    public static final int OCR_ENGLISH = 1;
    /**
     * OCR 简体中文类型
     */
    public static final int OCR_SIMPLIPIED_CHINESE = 2;
    /**
     * OCR 繁体中文类型
     */
    public static final int OCR_TRADITIONAL_CHINESE = 3;

    /**
     * 成功
     */
    public static final String TRANSACTION_SUCCESS = "00";
    /**
     * 脚本错误引起的失败
     */
    public static final String TRANSACTION_SCRIPT_ERROR = "01";
    /**
     * 良性突发事件引起的失败
     */
    public static final String TRANSACTION_BENIGN_INCIDENT = "02";

    /**
     * 业务指标的失败
     */
    public static final String TRANSACTION_BUSINESS_FAIL = "03";

    /**
     * 其他失败原因
     */
    public static final String TRANSACTION_OTHER = "04";

    /**
     * 匹配不到脚本
     */
    public static final String TRANSACTION_NOT_MACTHER_SCRIPT = "05";

    /**
     * 网络异常
     */
    public static final String TRANSACTION_NETWORK_FAIL = "06";

    /**
     * 应用原因失败
     */
    public static final String TRANSACTION_APP_FAIL = "10";

    /**
     * WAP网站升级失败
     */
    public static final String TRANSACTION_WAP_UPDATE_FAIL = "11";

    /**
     * SIM原因失败
     */
    public static final String TRANSACTION_SIM_FAIL = "12";

    /**
     * 存储原因失败
     */
    public static final String TRANSACTION_STORAGE_FAIL = "13";

    /**
     * 连续失败异常
     */
    public static final String TRANSACTION_CONTINUOUS_FAIL = "14";

    /**
     * 重复订购、退订失败
     */
    public static final String TRANSACTION_REPEAT_ORDER = "15";
    
    /**
     * 移动网络注册失败
     */
    public static final int ALARM_MN_REG_FAIL = 102;

    /**
     * 数据网络连接断开
     */
    public static final int ALARM_N_DATA_DISCONN = 103;

    /**
     * 屏幕被关闭
     */
    public static final int ALARM_SCREEN_CLOSED = 104;

    /**
     * 屏幕被锁屏
     */
    public static final int ALARM_SCREEN_LOCKED = 105;

    /**
     * SD卡被移除
     */
    public static final int ALARM_SDCARD_ABSENT = 106;

    /**
     * CPU占用超限值告警
     */
    public static final int ALARM_CPU_OVERRUN = 107;
    /**
     * 内存使用超限值告警
     */
    public static final int ALARM_MEM_OVERRUN = 108;
    /**
     * 存储空间超限值告警
     */
    public static final int ALARM_STORAGE_OVERRUN = 109;

    /**
     * 网络流量超限值告警
     */
    public static final int ALARM_NW_OVERRUN = 114;

    /**
     * 低电量告警
     */
    public static final int ALARM_LOWER_POWER = 110;

    /**
     * 弱信号告警
     */
    public static final int ALARM_LOWER_SIGNAL = 111;

    /**
     * 电池过热
     */
    public static final int ALARM_HIGH_TEMP = 101;
    
    /**
     * 业务指标连续失败告警
     */
    public static final int ALARM_BS_FAIL = 119;

    /**
     * listener
     */
    protected ITestListener listener;

    /**
     * environment
     */
    protected IEnvironment environment;

    /**
     * paramMap
     */
    protected ContentValues paramMap;

    /**
     * result map
     */
    protected ContentValues resultMap = new ContentValues();

    /**
     * deviceEntity
     */
    protected IDeviceEntity deviceEntity;

    /**
     * @return the listener
     */
    public ITestListener getListener() {
        return listener;
    }

    /**
     * @param listener
     *            the listener to set
     */
    public void setListener(ITestListener listener) {
        this.listener = listener;
    }

    /**
     * Getter of resultMap
     * 
     * @return the resultMap
     */
    public ContentValues getResultMap() {
        return resultMap;
    }

    /**
     * Setter of resultMap
     * 
     * @param resultMap
     *            the resultMap to set
     */
    public void setResultMap(ContentValues resultMap) {
        this.resultMap = resultMap;
    }

    /**
     * caseName
     * 
     * @return
     */
    protected String caseName() {
        return this.getClass().getName();
    }

    /**
     * @return environment.getDeviceEntity()
     */
    public IDeviceEntity getDeviceEntity() {
        return environment.getDeviceEntity();
    }

    /**
     * 
     * @return environment.getSetting()
     */
    public ContentValues getSetting() {
        return environment.getSetting();
    }

    /**
     * @return environment.getGlobalVariables()
     */
    public ContentValues getGlobalVariables() {
        return environment.getGlobalVariables();
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.execute.testcase.ITestCase#execute()
     */
    public ContentValues execute(IEnvironment environment, ContentValues paramMap) {

        // this.environment = environment;
        this.paramMap = paramMap;

        if (listener != null) {
            listener.onStart();
        }

        try {
            doExecute();
        } catch (Exception e) {
            fail(e.getMessage());
            if (listener != null) {
                listener.onError(e, resultMap);
            }
            return null;
        }

        if (listener != null) {
            listener.onFinish(resultMap);
        }

        return resultMap;
    }

    /**
     * test start
     * 
     * @throws Exception
     */
    public void testStart() throws Exception {
        this.deviceEntity = getDeviceEntity();
        // create params, init TestService
        initDebugEnvironment();
        if (execute(environment, environment.getParams()) == null) {
            environment.saveResult(resultMap);
            // fail();
        } else {
            environment.saveResult(resultMap);
        }
        // get result from TestService
        displayDebugResult();
    }

    /**
     * display debug result
     */
    protected void displayDebugResult() {
        if (CommandConstants.MODE_RUN.equals(environment.getMode()))
            return;
        // read config
        try {
            final ContentValues result = deviceEntity.getDebugResult();
            System.out.println(result);

        } catch (MException e) {
            Log.e(TAG, "displayDebugResult error", e);
        }
    }

    /**
     * init debug environment
     */
    protected void initDebugEnvironment() throws Exception {
        if (CommandConstants.MODE_RUN.equals(environment.getMode()))
            return;
        final ContentValues params = environment.getDebugParams();
        try {
            deviceEntity.setDebugParams(params);
        } catch (MException e) {
            Log.e(TAG, "initDebugEnvironment error", e);
            throw e;
        }
    }

    /**
     * do execute the test case
     * 
     * @throws Exception
     */
    protected abstract void doExecute() throws Exception;

    /*
     * (non-Javadoc)
     * 
     * @see android.test.AndroidTestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.environment = Environment.getInstance(getContext());
        if (environment == null) {
            fail("Cant initial enviroment");
        }
    }

    /**
     * 打印LOG_LEVEL_DEBUG等级文本日志
     * 
     * @param str
     * @throws MException
     */
    public void logDebug(String str) throws MException {
        deviceEntity.log(LOG_LEVEL_DEBUG, str);
    }

    /**
     * 打印LOG_LEVEL_ERROR等级文本日志
     * 
     * @param str
     * @throws MException
     */
    public void logError(String str) throws MException {
        deviceEntity.log(LOG_LEVEL_ERROR, str);
    }

    /**
     * 在timeOut时间内循环调用verifyScreen,直到匹配或者超时
     * 
     * @param inputStartX
     *            当前截图开始X位移
     * @param inputStartY
     *            当前截图开始Y位移
     * @param templateImagePath
     *            原先保存的需要校验的截图路径
     * @param templateStartX
     *            原先保存的需要校验的截图开始X位移
     * @param templateStartY
     *            原先保存的需要校验的截图开始Y位移
     * @param w
     *            校验区域的宽
     * @param h
     *            校验区域的高
     * @param colorToleranceAsPercent
     *            颜色容忍度，百分比 ，取值：0到100
     * @param pixelTolerance
     *            像素容忍度，单位：像素个数
     * @param timeOut
     *            单位：ms
     * @return true 代表成功到匹配
     */
    public boolean verifyScreen(int inputStartX, int inputStartY, String templateImagePath, int templateStartX,
            int templateStartY, int w, int h, int colorToleranceAsPercent, int pixelTolerance, int timeOut) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < timeOut) {
            boolean result = false;
            try {
                result = deviceEntity.verifyScreen(inputStartX, inputStartY, templateImagePath, templateStartX,
                        templateStartY, w, h, colorToleranceAsPercent, pixelTolerance);
            } catch (Exception e1) {
                Log.e(TAG, "Error: " + e1.getMessage(), e1);
            }
            if (result)
                return true;

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {

            }
        }
        return false;
    }

    /**
     * 在timeOut时间内循环调用verifyScreen,直到匹配或者超时
     * 
     * @param inputStartX
     *            当前截图开始X位移
     * @param inputStartY
     *            当前截图开始Y位移
     * @param templateImagePath
     *            原先保存的需要校验的截图路径
     * @param templateStartX
     *            原先保存的需要校验的截图开始X位移
     * @param templateStartY
     *            原先保存的需要校验的截图开始Y位移
     * @param w
     *            校验区域的宽
     * @param h
     *            校验区域的高
     * @param colorToleranceAsPercent
     *            颜色容忍度，百分比 ，取值：0到100
     * @param pixelTolerance
     *            像素容忍度，单位：像素个数
     * @param timeOut
     *            单位：ms
     * @return true 代表成功到匹配
     */
    public boolean verifyScreen(int inputStartX, int inputStartY, String templateImagePath, int w, int h,
            int colorToleranceAsPercent, int timeOut) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < timeOut) {
            boolean result = false;
            try {
                result = deviceEntity.verifyScreen(inputStartX, inputStartY, templateImagePath, 0, 0, w, h,
                        colorToleranceAsPercent, 0);
            } catch (Exception e1) {
                Log.e(TAG, "Error: " + e1.getMessage(), e1);
            }
            if (result)
                return true;

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {

            }
        }
        return false;
    }

    /**
     * 在timeOut时间内循环调用verifyScreen,直到匹配或者超时
     * 
     * @param inputStartX
     *            当前截图开始X位移
     * @param inputStartY
     *            当前截图开始Y位移
     * @param templateImagePath
     *            原先保存的需要校验的截图路径
     * @param colorToleranceAsPercent
     *            颜色容忍度，百分比 ，取值：0到100
     * @param timeOut
     *            单位：ms
     * @return true 代表成功到匹配
     */
    public boolean verifyScreen(int inputStartX, int inputStartY, String templateImagePath,
            int colorToleranceAsPercent, int timeOut) {
        return verifyScreen(inputStartX, inputStartY, templateImagePath, -1, -1, colorToleranceAsPercent, timeOut);
    }

    /**
     * 在timeOut时间内循环调用verifyScreen,直到匹配或者超时
     * 
     * @param templateImagePath
     *            原先保存的需要校验的截图路径
     * @param colorToleranceAsPercent
     *            颜色容忍度，百分比 ，取值：0到100
     * @param timeOut
     *            单位：ms
     * @return true 代表成功到匹配
     */
    public boolean verifyScreen(String templateImagePath, int colorToleranceAsPercent, int timeOut) {
        return verifyScreen(0, 0, templateImagePath, colorToleranceAsPercent, timeOut);
    }

    /**
     * 在timeOut时间内循环调用verifyScreen,直到没匹配或者超时
     * 
     * @param inputStartX
     *            当前截图开始X位移
     * @param inputStartY
     *            当前截图开始Y位移
     * @param templateImagePath
     *            原先保存的需要校验的截图路径
     * @param w
     *            校验区域的宽
     * @param h
     *            校验区域的高
     * @param colorToleranceAsPercent
     *            颜色容忍度，百分比 ，取值：0到100
     * @param timeOut
     *            单位：ms
     * @return true 成功到没匹配
     */
    public boolean noVerifyScreen(int inputStartX, int inputStartY, String templateImagePath, int w, int h,
            int colorToleranceAsPercent, int timeOut) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < timeOut) {
            boolean result = true;
            try {
                result = deviceEntity.verifyScreen(inputStartX, inputStartY, templateImagePath, 0, 0, w, h,
                        colorToleranceAsPercent, 0);
            } catch (Exception e1) {
                Log.e(TAG, "Error: " + e1.getMessage(), e1);
            }
            if (!result)
                return true;

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {

            }
        }
        return false;
    }

    /**
     * 在timeOut时间内循环调用verifyScreen,直到没匹配或者超时
     * 
     * @param inputStartX
     *            当前截图开始X位移
     * @param inputStartY
     *            当前截图开始Y位移
     * @param templateImagePath
     *            原先保存的需要校验的截图路径
     * @param colorToleranceAsPercent
     *            颜色容忍度，百分比 ，取值：0到100
     * @param pixelTolerance
     *            像素容忍度，单位：像素个数
     * @param timeOut
     *            单位：ms
     * @return true 成功到没匹配
     */
    public boolean noVerifyScreen(int inputStartX, int inputStartY, String templateImagePath,
            int colorToleranceAsPercent, int pixelTolerance, int timeOut) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < timeOut) {
            boolean result = true;
            try {
                result = deviceEntity.verifyScreen(inputStartX, inputStartY, templateImagePath, 0, 0, -1, -1,
                        colorToleranceAsPercent, pixelTolerance);
            } catch (Exception e1) {
                Log.e(TAG, "Error: " + e1.getMessage(), e1);
            }
            if (!result)
                return true;

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {

            }
        }

        return false;
    }

    /**
     * 在大图片中查找小图片,找到后点击所在区域<br/>
     * <p>
     * <b style="color:red">在eclipse中运行调试前,请运行cp_attachment.bat把模板图片放入手机中</b>
     * <p>
     * 
     * @param templateImagePath
     *            Base64格式路径
     * @param templateStartX
     *            目标图开始X位移
     * @param templateStartY
     *            目标图开始Y位移
     * @param w
     *            目标图校验区域的宽
     * @param h
     *            目标图校验区域的高
     * @param colorToleranceAsPercent
     *            颜色容忍度百分比 ，取值：0到100
     * @return false 如果动作失败， true 如果成功
     */
    public boolean findAndClick(String templateImagePath, int templateStartX, int templateStartY, int w, int h,
            int colorToleranceAsPercent) throws MException {
        int[] location = null;
        location = deviceEntity.findInScreen(templateImagePath, 0, 0, w, h, colorToleranceAsPercent);
        if (location != null) {
            int x = location[0];
            int y = location[1];
            // 触屏点击
            deviceEntity.screenClick(x + w / 2, y + h / 2);
            return true;
        }
        return false;
    }

    /**
     * 按步进的方式查获找 图片
     * 
     * @param inputStartX
     *            当前截图开始X位移
     * @param inputStartY
     *            当前截图开始Y位移
     * @param inputWidth
     *            当前截图位移宽
     * @param inputHeight
     *            当前截图位移高
     * @param templateImagePath
     *            原先保存的需要校验的截图路径
     * @param templateStartX
     *            原先保存的需要校验的截图开始X位移
     * @param templateStartY
     *            原先保存的需要校验的截图开始Y位移
     * @param w
     *            校验区域的宽
     * @param h
     *            校验区域的高
     * @param colorToleranceAsPercent
     *            颜色容忍度百分比 ，取值：0到100
     * @param xStep
     *            x步进
     * @param yStep
     *            y步进
     * @param timeOut
     *            超时时间单位 ms
     * @return 位置坐标 int[0] 为X坐标， int[1]为Y坐标
     */
    public int[] findInScreenCom(int inputStartX, int inputStartY, int inputWidth, int inputHeight,
            String templateImagePath, int templateStartX, int templateStartY, int w, int h,
            int colorToleranceAsPercent, int xStep, int yStep, int timeOut) {
        int[] result = null;
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < timeOut) {

            try {
                result = deviceEntity.findInScreenCom(inputStartX, inputStartY, inputWidth, inputHeight,
                        templateImagePath, templateStartX, templateStartY, w, h, colorToleranceAsPercent, xStep, yStep);
            } catch (Exception e1) {
                Log.e(TAG, "Error: " + e1.getMessage(), e1);
            }
            if (null != result)
                break;

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {

            }
        }

        return result;
    }

    /**
     * 在大图片中查找小图片,找到后点击所在区域<br/>
     * 
     * @param templateImagePath
     *            Base64格式路径
     * @param w
     *            目标图校验区域的宽
     * @param h
     *            目标图校验区域的高
     * @param colorToleranceAsPercent
     * @param timeOut
     *            单位：ms
     * @return
     */
    public boolean findAndClick(String templateImagePath, int w, int h, int colorToleranceAsPercent, int timeOut) {
        long start = System.currentTimeMillis();
        boolean result = false;
        while (System.currentTimeMillis() - start < timeOut) {
            int[] location = null;
            try {
                location = deviceEntity.findInScreen(templateImagePath, 0, 0, w, h, colorToleranceAsPercent);
                if (location != null) {
                    int x = location[0] + w / 2;
                    int y = location[1] + h / 2;
                    // 触屏点击
                    deviceEntity.screenClick(x, y);
                    result = true;
                    break;
                }
            } catch (Exception e1) {
                Log.e(TAG, "Error: " + e1.getMessage(), e1);
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {

            }
        }

        return result;
    }

    /**
     * 循环查找图片，直到超时
     * 
     * @param templateImagePath
     *            *
     *            <p>
     *            <b style="color:red">该路径格式 为deviceName_serviceKey_width_heigh_xxx.jpg,注意格式</b>
     *            <p>
     * @param colorToleranceAsPercent
     *            颜色容忍度百分比 ，取值：0到100
     * @param timeOut
     *            单位 ms
     * @return 位置坐标 int[0] 为X坐标， int[1]为Y坐标
     */
    public int[] findInScreen(String templateImagePath, int colorToleranceAsPercent, int timeOut) {
        long start = System.currentTimeMillis();
        int[] location = null;
        final String[] params = parseTemplateImagePath(templateImagePath);
        if (null != params) {
            final int w = Integer.parseInt(params[2]);
            final int h = Integer.parseInt(params[3]);

            while (System.currentTimeMillis() - start < timeOut) {

                try {
                    location = deviceEntity.findInScreen(templateImagePath, 0, 0, w, h, colorToleranceAsPercent);
                    if (location != null) {
                        break;
                    }
                } catch (Exception e1) {
                    Log.e(TAG, "Error: " + e1.getMessage(), e1);
                }

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {

                }
            }

        }
        return location;
    }

    /**
     * 在大图片中查找小图片,找到后点击所在区域<br/>
     * 
     * @param templateImagePath
     *            Base64格式路径
     * @param w
     *            目标图校验区域的宽
     * @param h
     *            目标图校验区域的高
     * @param colorToleranceAsPercent
     * @param timeOut
     *            单位：ms
     * @return
     */
    public boolean findAndClick(int inputStartX, int inputStartY, String templateImagePath, int w, int h,
            int colorToleranceAsPercent, int timeOut) {
        long start = System.currentTimeMillis();
        boolean result = false;
        while (System.currentTimeMillis() - start < timeOut) {
            int[] location = null;
            try {
                location = deviceEntity.findInScreen(inputStartX, inputStartY, templateImagePath, 0, 0, w, h,
                        colorToleranceAsPercent);
                if (location != null) {
                    int x = location[0] + w / 2;
                    int y = location[1] + h / 2;
                    // 触屏点击
                    deviceEntity.screenClick(x, y);
                    result = true;
                    break;
                }
            } catch (Exception e1) {
                Log.e(TAG, "Error: " + e1.getMessage(), e1);
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {

            }
        }

        return result;
    }

    /**
     * 在大图片中查找小图片,找到后点击所在区域<br/>
     * 
     * @param inputStartX
     *            当前截图开始X位移
     * @param inputStartY
     *            当前截图开始Y位移
     * @param inputWidth
     *            当前截图位移宽
     * @param inputHeight
     *            当前截图位移高
     * @param templateImagePath
     *            模板路径
     * @param w
     *            校验区域的宽
     * @param h
     *            校验区域的高
     * @param colorToleranceAsPercent
     *            颜色容忍度百分比 ，取值：0到100
     * @param timeOut
     *            超时时间
     * @return 位置坐标 int[0] 为X坐标， int[1]为Y坐标
     */
    public boolean findAndClick(int inputStartX, int inputStartY, int inputWidth, int inputHeight,
            String templateImagePath, int w, int h, int colorToleranceAsPercent, int timeOut) {
        long start = System.currentTimeMillis();
        boolean result = false;
        while (System.currentTimeMillis() - start < timeOut) {
            int[] location = null;
            try {
                location = deviceEntity.findInScreen(inputStartX, inputStartY, inputWidth, inputHeight,
                        templateImagePath, 0, 0, w, h, colorToleranceAsPercent);
                if (location != null) {
                    int x = location[0] + w / 2;
                    int y = location[1] + h / 2;
                    // 触屏点击
                    deviceEntity.screenClick(x, y);
                    result = true;
                    break;
                }
            } catch (Exception e1) {
                Log.e(TAG, "Error: " + e1.getMessage(), e1);
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {

            }
        }

        return result;
    }

    // public boolean findInScreen(inputStartX, inputStartY, inputWidth, inputHeight,
    // templateImagePath，colorToleranceAsPercent, xStep, yStep, timeOut){
    //
    // }

    /**
     * 在大图片中查找小图片,找到后点击所在区域<br/>
     * 
     * @param templateImagePath
     *            *
     *            <p>
     *            <b style="color:red">该路径格式 为deviceName_serviceKey_width_heigh_xxx.jpg,注意格式</b>
     *            <p>
     * @param colorToleranceAsPercent
     *            颜色容忍度百分比 ，取值：0到100
     * @param timeOut
     *            单位：ms
     * @return
     */
    public boolean findAndClick(String templateImagePath, int colorToleranceAsPercent, int timeOut) {
        final String[] params = parseTemplateImagePath(templateImagePath);
        if (null != params) {
            final int w = Integer.parseInt(params[2]);
            final int h = Integer.parseInt(params[3]);
            return findAndClick(templateImagePath, w, h, colorToleranceAsPercent, timeOut);
        }
        return false;

    }

    protected void logScreen(String str) throws MException {
        deviceEntity.logScreen(CommandConstants.LOG_LEVEL_DEBUG, str);
    }

    /**
     * 在超时时间内查找未读匹配的短信
     * 
     * @param from
     *            发送方地址， 包含关系: 如from为10086，可匹配100860001
     * @param keys
     *            关键字
     * @param flag
     *            true为 关键字为and关系， false 关键字为或关系
     * @param timeOut
     *            超时时间
     * @return
     * @throws MException
     */
    protected boolean findSms(String from, String[] keys, boolean flag, int timeOut) throws MException {
        long start = System.currentTimeMillis();
        boolean result = false;
        while (System.currentTimeMillis() - start < timeOut) {
            try {
                List<ContentValues> smss = deviceEntity.getMessage(from, -1, 0);
                for (ContentValues sms : smss) {
                    String body = sms.getAsString(CommandConstants.KEY_GETMESSAGE_BODY);
                    boolean comFlag;
                    if (flag) {
                        comFlag = true;
                        for (String key : keys) {
                            if (!body.contains(key)) {
                                comFlag = false;
                                break;
                            }
                        }
                    } else {
                        comFlag = false;
                        for (String key : keys) {
                            if (body.contains(key)) {
                                comFlag = true;
                                break;
                            }
                        }
                    }
                    if (comFlag) {
                        result = true;
                        break;
                    }
                }
            } catch (Exception e1) {
                logError("Error: " + e1.getMessage());
            }

            try {
                if (result)
                    break;
                Thread.sleep(200);
            } catch (InterruptedException e) {

            }
        }

        return result;
    }

    /**
     * 在超时时间内查找未读匹配的短信，查找匹配成功回复replyBody
     * 
     * @param from
     *            发送方地址， 包含关系: 如from为10086，可匹配100860001
     * @param keys
     *            关键字
     * @param flag
     *            true为 关键字为and关系， false 关键字为或关系
     * @param timeOut
     *            超时时间
     * @return
     * @throws MException
     */
    public boolean findAndReplySms(String from, String[] keys, boolean flag, String replyBody, int timeOut)
            throws MException {
        long start = System.currentTimeMillis();
        boolean result = false;
        String sourAddress = null;
        while (System.currentTimeMillis() - start < timeOut) {
            try {
                List<ContentValues> smss = deviceEntity.getMessage(from, -1, 0);
                for (ContentValues sms : smss) {
                    String body = sms.getAsString(CommandConstants.KEY_GETMESSAGE_BODY);
                    boolean comFlag;
                    if (flag) {
                        comFlag = true;
                        for (String key : keys) {
                            if (!body.contains(key)) {
                                comFlag = false;
                                break;
                            }
                        }
                    } else {
                        comFlag = false;
                        for (String key : keys) {
                            if (body.contains(key)) {
                                comFlag = true;
                                break;
                            }
                        }
                    }
                    if (comFlag) {
                        result = true;
                        sourAddress = sms.getAsString(CommandConstants.KEY_GETMESSAGE_SOURCEADDRESS);
                        break;
                    }
                }
            } catch (Exception e1) {
                logError("Error: " + e1.getMessage());
            }

            try {
                if (result)
                    break;
                Thread.sleep(200);
            } catch (InterruptedException e) {

            }
        }

        if (sourAddress != null) {
            try {
                result = deviceEntity.sendMessage(sourAddress, replyBody);
            } catch (MException e) {
                logError("SendMsg to " + sourAddress + " Error: " + e.getMessage());
            }
        }

        return result;
    }

    /**
     * 在超时时间内查找from发送的未读短信
     * 
     * @param from
     *            发送方地址， 包含关系: 如from为10086，可匹配100860001
     * @param timeOut
     *            超时时间
     * @return
     * @throws MException
     */
    protected List<String> findSms(String from, int timeOut) throws MException {
        long start = System.currentTimeMillis();
        List<String> bodys = new ArrayList<String>();

        while (System.currentTimeMillis() - start < timeOut) {
            try {
                List<ContentValues> smss = deviceEntity.getMessage(from, -1, 0);
                if (smss.size() == 0) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {

                    }
                    continue;
                }
                for (ContentValues sms : smss) {
                    String body = sms.getAsString(CommandConstants.KEY_GETMESSAGE_BODY);
                    bodys.add(body);
                }
                break;

            } catch (Exception e1) {
                logError("Error: " + e1.getMessage());
            }

        }

        return bodys;
    }

    /**
     * 解析原先保存的需要校验的截图路径文件名格式,
     * <p>
     * <b style="color:red">该路径格式 为deviceName_serviceKey_width_heigh_xxx.jpg,注意格式</b>
     * 
     * @param templateImagePath
     * @return
     */
    public static String[] parseTemplateImagePath(String templateImagePath) {
        if (null != templateImagePath) {
            final String[] params = templateImagePath.split("_");
            if (null != params && params.length == 5) {
                return params;
            }
        }
        return null;
    }

    /**
     * 计算网络下载速度xxkb/s
     * 
     * @param size
     *            一定时间内已读取的长度, 单位： byte
     * @param ms
     *            时间， 单位：ms
     * @return xxkb/s
     */
    public static String clacNetworkSpeedKBS(long size, long ms) {
        return clacNetworkSpeed(size, ms) + "kb/s";
    }

    /**
     * 计算网络下载速度
     * 
     * @param size
     *            一定时间内已读取的长度, 单位： byte
     * @param ms
     *            时间， 单位：ms
     * @return xx
     */
    public static double clacNetworkSpeed(long size, long ms) {
        if (size <= 0 || ms <= 0)
            return 0;
        final double kb = (size / 1024);
        final double s = (ms / 1000);
        final double speed = kb / s;
        return formatDecimal2(speed);
    }

    /**
     * 把毫秒转换成秒,保留小数后两位
     * 
     * @param time
     *            时间以毫秒为单位
     * @return 0.00
     */
    public static double parseMs2M(double ms) {
        if (ms <= 0)
            return 0;
        return formatDecimal2(ms / 1000);
    }

    /**
     * 格式化double,保留小数后两位
     * 
     * @param d
     *            指定的double 数据
     * @return 0.00
     */
    public static double formatDecimal2(double d) {
        final DecimalFormat format = new DecimalFormat("0.00");
        return Double.parseDouble(format.format(d));
    }

    /**
     * 把毫秒转换成秒,保留小数后两位
     * 
     * @param time
     *            时间以毫秒为单位
     * @return 0.00s
     */
    public static String parseMs2MStr(long ms) {
        if (ms <= 0)
            return "-1";
        final StringBuffer buffer = new StringBuffer();
        buffer.append(parseMs2M(ms));
        buffer.append("s");
        return buffer.toString();
    }
}
