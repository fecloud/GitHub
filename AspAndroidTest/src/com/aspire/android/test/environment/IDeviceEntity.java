/**
 * @(#) ITestService.java Created on 2012-4-23
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */

package com.aspire.android.test.environment;

import java.util.List;

import com.aspire.android.test.exception.MException;
import com.aspire.android.test.execute.ContentValues;

/**
 * The interface <code>ITestService</code>
 * 
 * @author linjunsui
 * @version 1.0
 */
public interface IDeviceEntity {

    /**
     * <b style="color:red">取回设置参数(此API,不提供使用)</b>
     * 
     * @return 参数
     * @throws MException
     */
    ContentValues getParams() throws MException;

    /**
     * <b style="color:red">保存参数(此API,不提供使用)</b>
     * 
     * @param resultMap
     *            参数
     * @throws MException
     */
    void saveResult(ContentValues resultMap) throws MException;

    /**
     * 获取设置参数
     * 
     * @return 设置参数
     * @throws MException
     */
    ContentValues getSetting() throws MException;

    /**
     * <b style="color:red">取得全局设置参数(此API,不提供使用)</b>
     * 
     * @return 参数
     * @throws MException
     */
    ContentValues getGlobalVariables() throws MException;

    /**
     * 保存事务
     * 
     * @param begintime
     *            事务开始时间
     * @param endtime
     *            事务结束时间
     * @param name
     *            事务名称
     * @param value
     *            事务值
     * @param result
     *            事物结果
     * @param saveCertificate
     *            是否保存事务凭证
     * @throws MException
     */
    void saveTransaction(long begintime, long endtime, String name, String value, String result, boolean saveCertificate)
            throws MException;

    /**
     * 开始一个事务
     * 
     * @param name
     *            事务名称 transaction name
     */
    void beginTransaction(String name) throws MException;

    // /**
    // * 线束一个事务 <br/>
    // * <p>
    // * <b style="color:red">提示: 线束一个事务前,请先开始一个事务(调用beginTransaction函数),如果需要保存凭证,请调用对应的保存凭证函数</b>
    // * <p>
    // *
    // * @param name
    // * 事务名称
    // * @param value
    // * 事务值
    // * @param isSucess
    // * 事物是否成功
    // */
    // void endTransaction(String name, String value, boolean isSucess) throws MException;

    // /**
    // * 线束一个事务<br/>
    // * <p>
    // * <b style="color:red">提示: 线束一个事务前,请先开始一个事务(调用beginTransaction函数),如果需要保存凭证,请调用对应的保存凭证函数</b>
    // * <p>
    // *
    // * @param name
    // * 事务名称
    // * @param value
    // * 事务值
    // * @param isSucess
    // * 事务是否成功
    // * @param saveCertificate
    // * 是否保存事务凭证
    // *
    // */
    // void endTransaction(String name, String value, boolean isSucess, boolean saveCertificate) throws MException;

    /**
     * 线束一个事务<br/>
     * <p>
     * <b style="color:red">提示: 线束一个事务前,请先开始一个事务(调用beginTransaction函数),如果需要保存凭证,请调用对应的保存凭证函数</b>
     * <p>
     * 
     * @param name
     *            事务名称
     * @param value
     *            事务值
     * @param result
     *            事务返回值 <b>
     *            <p>
     *            result 取值如如下:
     *            <p>
     *            TRANSACTION_SUCCESS 成功
     *            <p>
     *            TRANSACTION_SCRIPT_ERROR 脚本错误引起的失败
     *            <p>
     *            TRANSACTION_BENIGN_INCIDENT 良性突发事件引起的失败
     *            <p>
     *            TRANSACTION_BUSINESS_FAIL 业务指标的失败
     *            <p>
     *            TRANSACTION_OTHER 其他失败原因
     *            <p>
     *            TRANSACTION_CANNOT_TESTING 无法拨测
     *            <p>
     *            TRANSACTION_IPNETWORK_FAIL IP网络失败 </b>
     * @param saveCertificate
     *            是否保存事务凭证
     */
    void endTransaction(String name, String value, String result, boolean saveCertificate) throws MException;

    /**
     * 线束一个事务<br/>
     * <p>
     * <b style="color:red">提示: 线束一个事务前,请先开始一个事务(调用beginTransaction函数),如果需要保存凭证,请调用对应的保存凭证函数</b>
     * <p>
     * 
     * @param name
     *            事务名称
     * @param result
     *            事务返回值 <b>
     *            <p>
     *            result 取值如如下:
     *            <p>
     *            TRANSACTION_SUCCESS 成功
     *            <p>
     *            TRANSACTION_SCRIPT_ERROR 脚本错误引起的失败
     *            <p>
     *            TRANSACTION_BENIGN_INCIDENT 良性突发事件引起的失败
     *            <p>
     *            TRANSACTION_BUSINESS_FAIL 业务指标的失败
     *            <p>
     *            TRANSACTION_OTHER 其他失败原因
     *            <p>
     *            TRANSACTION_CANNOT_TESTING 无法拨测
     *            <p>
     *            TRANSACTION_IPNETWORK_FAIL IP网络失败 </b>
     * @param saveCertificate
     *            是否保存事务凭证
     */
    void endTransaction(String name, String result, boolean saveCertificate) throws MException;

    /**
     * 输出日志到文件<br/>
     * <p>
     * <b style="color:red">(在eclipse中运行、调试下,运行完成日志文件在/sdcard/aspire/logs/run. log文件中)
     * 
     * @param logLevel
     *            日志级别
     *            <p>
     *            日志级别如下: AbstractTestCase.LOG_LEVEL_VERBOSE 详细
     *            <p>
     *            日志级别如下: AbstractTestCase.LOG_LEVEL_DEBUG 调试
     *            <p>
     *            日志级别如下: AbstractTestCase.LOG_LEVEL_INFO 消息
     *            <p>
     *            日志级别如下: AbstractTestCase.LOG_LEVEL_WARN 警告
     *            <p>
     *            日志级别如下: AbstractTestCase.LOG_LEVEL_ERROR 错误
     *            <p>
     *            日志级别如下: AbstractTestCase.LOG_LEVEL_FATAL 致命
     *            <p>
     *            日志级别从LOG_LEVEL_VERBOSE到LOG_LEVEL_FATAL越来越高
     * @param message
     *            日志信息 </b>
     */
    void log(int logLevel, String message) throws MException;

    /**
     * 输出日志到文件，包括当前的屏幕截图<br/>
     * <p>
     * <b style="color:red">(在eclipse中运行、调试下,运行完成日志文件在/sdcard/aspire/logs/run. log文件中，屏幕截图下/sdcard/aspire/logs/目录下)
     * 
     * @param logLevel
     *            日志级别
     *            <p>
     *            日志级别如下: LOG_LEVEL_VERBOSE 详细
     *            <p>
     *            日志级别如下: LOG_LEVEL_DEBUG 调试
     *            <p>
     *            日志级别如下: LOG_LEVEL_INFO 消息
     *            <p>
     *            日志级别如下: LOG_LEVEL_WARN 警告
     *            <p>
     *            日志级别如下: LOG_LEVEL_ERROR 错误
     *            <p>
     *            日志级别如下: LOG_LEVEL_FATAL 致命
     *            <p>
     *            日志级别从LOG_LEVEL_VERBOSE到LOG_LEVEL_FATAL越来越高
     * @param message
     *            日志信息 </b>
     */
    void logScreen(int logLevel, String message) throws MException;

    /**
     * 保存当前屏幕图像
     * 
     * @param patch
     *            指定的目录 <b style="color:red"> (在eclipse中运行、调试下,如果该参数为null,则默认为/sdcard/aspire/temp) </b>
     * @param name
     *            指定的文件名
     * @return 返回保存图像的绝对路径
     * @throws MException
     */
    String saveScreen(String patch, String name) throws MException;

    /**
     * 在当前屏幕中查找文字
     * 
     * @param lang
     *            所要找到的文字的类型
     *            <p>
     *            类型如下：
     *            <p>
     *            OCR_NUMBER 数字类型
     *            <p>
     *            OCR_ENGLISH 英文类型
     *            <p>
     *            OCR_SIMPLIPIED_CHINESE 简体中文类型
     *            <p>
     *            OCR_TRADITIONAL_CHINESE 繁体中文类型
     * 
     * @return 找到的文字结果
     */
    String ocrScreen(int lang) throws MException;

    /**
     * 在当前屏幕中查找文字,并且与期待结果进行匹配
     * 
     * @param lang
     *            所要找到的文字的类型
     *            <p>
     *            类型如下：
     *            <p>
     *            OCR_NUMBER 数字类型
     *            <p>
     *            OCR_ENGLISH 英文类型
     *            <p>
     *            OCR_SIMPLIPIED_CHINESE 简体中文类型
     *            <p>
     *            OCR_TRADITIONAL_CHINESE 繁体中文类型
     * @param expect
     *            期待结果
     * @return 成功true ,否则 false
     * @throws MException
     */
    boolean ocrScreenMatch(int lang, String expect) throws MException;

    /**
     * 在当前屏幕中的特定区域查找文字
     * 
     * @param startX
     *            OCR区域开始X位移
     * @param startY
     *            OCR区域开始Y位移
     * @param width
     *            OCR区域的宽
     * @param height
     *            OCR区域的高
     * @param lang
     *            所要找到的文字的类型
     *            <p>
     *            类型如下：
     *            <p>
     *            OCR_NUMBER 数字类型
     *            <p>
     *            OCR_ENGLISH 英文类型
     *            <p>
     *            OCR_SIMPLIPIED_CHINESE 简体中文类型
     *            <p>
     *            OCR_TRADITIONAL_CHINESE 繁体中文类型
     * @return 找到的文字结果
     */
    String ocrRect(int startX, int startY, int width, int height, int lang) throws MException;

    /**
     * 在当前屏幕中的特定区域查找文字,并且与期待结果进行匹配
     * 
     * @param startX
     *            OCR区域开始X位移
     * @param startY
     *            OCR区域开始Y位移
     * @param width
     *            OCR区域的宽
     * @param height
     *            OCR区域的高
     * @param lang
     *            所要找到的文字的类型
     *            <p>
     *            类型如下：
     *            <p>
     *            OCR_NUMBER 数字类型
     *            <p>
     *            OCR_ENGLISH 英文类型
     *            <p>
     *            OCR_SIMPLIPIED_CHINESE 简体中文类型
     *            <p>
     *            OCR_TRADITIONAL_CHINESE 繁体中文类型
     * @param expect
     *            期待结果
     * @return 成功true ,否则 false
     * @throws MException
     */
    boolean ocrRectMatch(int startX, int startY, int width, int height, int lang, String expect) throws MException;

    /**
     * 在屏幕中指定的坐标按下,保持不放开
     * 
     * @param x
     *            屏幕的x坐标
     * @param y
     *            屏幕的y坐标
     */
    void screenDown(int x, int y) throws MException;

    /**
     * 在屏幕中指定的坐标移动
     * 
     * @param x
     *            屏幕的x坐标
     * @param y
     *            屏幕的y坐标
     */
    void screenMove(int x, int y) throws MException;

    /**
     * 在屏幕中指定的坐标释放
     * 
     * @param x
     *            屏幕的x坐标
     * @param y
     *            屏幕的y坐标
     */
    void screenUp(int x, int y) throws MException;

    /**
     * 在屏幕中指定的坐标点击
     * 
     * @param x
     *            屏幕的x坐标
     * @param y
     *            屏幕的y坐标
     */
    void screenClick(int x, int y) throws MException;

    /**
     * 在屏幕上滑动,步骤是(按下-->移动-->释放)
     * 
     * @param x
     *            开始水平坐标
     * @param y
     *            开始垂直坐标
     * @param toX
     *            终点水平坐标
     * @param toY
     *            终点垂直坐标
     * @throws MException
     */
    void screenTouch(int x, int y, int toX, int toY) throws MException;

    /**
     * 释放按键
     * 
     * @param key
     *            按键码
     *            <p>
     *            <b style="color:red">关于按键码,请输入KeyEvent.然后在电脑上按Alt+/ 然后出现KEYCODE_的,根据需要选择</b>
     */
    void keyUp(int key) throws MException;

    /**
     * 按下按键,并保持不放
     * 
     * @param key
     *            按键码
     *            <p>
     *            <b style="color:red">关于按键码,请输入KeyEvent.然后在电脑上按Alt+/ 然后出现KEYCODE_的,根据需要选择</b>
     */
    void keyDown(int key) throws MException;

    /**
     * 按键按下并释放,普通按键事件
     * 
     * @param key按键码
     *            <p>
     *            <b style="color:red">关于按键码,请输入KeyEvent.然后在电脑上按Alt+/ 然后出现KEYCODE_的,根据需要选择</b>
     * @throws MException
     */
    void keyClick(int key) throws MException;

    /**
     * 按键长按下并释放,长按按键事件
     * 
     * @param key
     *            <p>
     *            <b style="color:red">关于按键码,请输入KeyEvent.然后在电脑上按Alt+/ 然后出现KEYCODE_的,根据需要选择</b>
     * @throws MException
     */

    void keyLongClick(int key) throws MException;

    /**
     * 校验图片(固定位置)<br/>
     * <p>
     * <b style="color:red">在eclipse中运行调试前,请运行cp_attachment.bat把模板图片放入手机中</b>
     * <p>
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
     * @return true 屏幕校验通过
     */
    boolean verifyScreen(int inputStartX, int inputStartY, String templateImagePath, int templateStartX,
            int templateStartY, int w, int h, int colorToleranceAsPercent, int pixelTolerance) throws MException;

    // /**
    // * 校验图片(固定位置)<br/>
    // * <p>
    // * <b style="color:red">在eclipse中运行调试前,请运行cp_attachment.bat把模板图片放入手机中</b>
    // * <p>
    // *
    // * @param inputStartX
    // * 当前截图开始X位移
    // * @param inputStartY
    // * 当前截图开始Y位移
    // * @param templateImagePath
    // * 原先保存的需要校验的截图路径
    // * @param templateStartX
    // * 原先保存的需要校验的截图开始X位移
    // * @param templateStartY
    // * 原先保存的需要校验的截图开始Y位移
    // * @param w
    // * 校验区域的宽
    // * @param h
    // * 校验区域的高
    // * @param colorToleranceAsPercent
    // * 颜色容忍度，百分比 ，取值：0到100
    // * @param pixelTolerance
    // * 像素容忍度，像素个数
    // * @return ContentValues with result pass or fail, and if pass, with the
    // result time as long
    // */
    // ContentValues verifyScreen(int inputStartX, int inputStartY, String
    // templateImagePath, int templateStartX,
    // int templateStartY, int w, int h, int colorToleranceAsPercent, int
    // pixelTolerance,
    // long times, int interval) throws MException;

    /**
     * 在大图片中查找小图片<br/>
     * <p>
     * <b style="color:red">在eclipse中运行调试前,请运行cp_attachment.bat把模板图片放入手机中</b>
     * <p>
     * 
     * @param templateImagePath
     *            Base64格式路径
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
     * @return 位置坐标 int[0] 为X坐标， int[1]为Y坐标
     */
    int[] findInScreen(String templateImagePath, int templateStartX, int templateStartY, int w, int h,
            int colorToleranceAsPercent) throws MException;

    /**
     * 按步进的方式查获找
     * <p>
     * <b style="color:red">在eclipse中运行调试前,请运行cp_attachment.bat把模板图片放入手机中</b>
     * <p>
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
     * @param xStep
     *            x步进
     * @param yStep
     *            y步进
     * @param colorToleranceAsPercent
     *            颜色容忍度百分比 ，取值：0到100
     * 
     * @return 位置坐标 int[0] 为X坐标， int[1]为Y坐标
     * @throws MException
     */
    int[] findInScreenCom(int inputStartX, int inputStartY, int inputWidth, int inputHeight, String templateImagePath,
            int templateStartX, int templateStartY, int w, int h, int colorToleranceAsPercent, int xStep, int yStep)
            throws MException;

    /**
     * 在大图片中查找小图片<br/>
     * *
     * <p>
     * <b style="color:red">在eclipse中运行调试前,请运行cp_attachment.bat把模板图片放入手机中</b>
     * <p>
     * 
     * @param inputStartX
     *            当前截图开始X位移
     * @param inputStartY
     *            当前截图开始Y位移
     * @param templateImagePath
     *            Base64格式路径
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
     * @return 位置坐标 int[0] 为X坐标， int[1]为Y坐标
     * @throws MException
     */
    int[] findInScreen(int inputStartX, int inputStartY, String templateImagePath, int templateStartX,
            int templateStartY, int w, int h, int colorToleranceAsPercent) throws MException;

    /**
     * 在大图片中查找小图片<br/>
     * <p>
     * <b style="color:red">在eclipse中运行调试前,请运行cp_attachment.bat把模板图片放入手机中</b>
     * <p>
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
     * @return 位置坐标 int[0] 为X坐标， int[1]为Y坐标
     * @throws MException
     */
    int[] findInScreen(int inputStartX, int inputStartY, int inputWidth, int inputHeight, String templateImagePath,
            int templateStartX, int templateStartY, int w, int h, int colorToleranceAsPercent) throws MException;

    /**
     * 在指定大图片中查找小图片<br/>
     * 
     * @param inputImagePath
     *            指定大图的路径
     * @param inputStartX
     *            大图的X位移
     * @param inputStartY
     *            大图的Y位移
     * @param inputWidth
     *            大图的校验区域宽
     * @param inputHeight
     *            大图的校验区域高
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
     * @return 位置坐标 int[0] 为X坐标， int[1]为Y坐标
     * @throws MException
     */
    int[] findPicture(String inputImagePath, int inputStartX, int inputStartY, int inputWidth, int inputHeight,
            String templateImagePath, int templateStartX, int templateStartY, int w, int h, int colorToleranceAsPercent)
            throws MException;

    /**
     * 安装android应用程序apk到手机
     * 
     * @param apkPath
     *            apk文件的路径,<b style="color:red">如果运行、调试,请把安装包放入手机内存卡里</b>
     * @throws MException
     * @return true 安装成功
     */
    boolean install(String apkPath) throws MException;

    /**
     * 卸载android应用程序
     * 
     * @param packageName
     *            android应用程序的包名
     * @throws MException
     * @return true 卸载成功
     */
    boolean uninstall(String packageName) throws MException;

    /**
     * 运行一个android应用程序
     * 
     * @param packageName
     *            android应用程序的包名
     * @throws MException
     * @return true 成功 false 失败
     */
    boolean runApp(String packageName) throws MException;

    /**
     * 停止运行一个android应用程序
     * 
     * @param packageName
     *            android应用程序的包名
     * @throws MException
     * @return true 成功 false 失败
     */
    boolean stopApp(String packageName) throws MException;

    /**
     * 调用手机的输入法,输入文字<br/>
     * <p>
     * <b style="color:red">(使用前,请到手机的设置-->语言和键盘--><br/>
     * 把AspireInputService勾上,然后把输入法切换成AspireInputService) </b>
     * 
     * @param text
     *            输入的文字
     * @throws MException
     */
    void input(String text) throws MException;

    /**
     * 删除输入的文字
     * 
     * @param left
     *            左边删除多少个
     * @param right
     *            右边删除多少个
     * @throws MException
     */
    void deleteInput(int left, int right) throws MException;

    /**
     * 发送一条短信
     * 
     * @param receivers
     *            发送到的指定的号码 ,多个号码以;隔开
     * 
     * @param content
     *            短信内容
     * @throws MException
     * @return true 发送成功
     */
    boolean sendMessage(String receivers, String content) throws MException;

    /**
     * 读取短信
     * 
     * @param params
     *            所设置的参数,请查看CommandConstants类以 KEY_GETMESSAGE_开头的常量定义
     * @return 读取到的短信
     * @throws MException
     */
    List<ContentValues> getMessage(ContentValues params) throws MException;

    /**
     * 读取短信
     * 
     * @param messageCount
     *            需要读取的信息数量， -1 为读入全部短信
     * @return 读取到的短信
     * @throws MException
     */
    List<ContentValues> getMessage(int messageCount) throws MException;

    /**
     * 读取短信
     * 
     * @param from
     *            发送人号码
     * @param messageCount
     *            需要读取的信息数量， -1 为读入全部短信
     * @param messageType
     *            -1:全部 ， 0 未读，1： 已读
     * @return 读取到的短信
     * @throws MException
     */
    List<ContentValues> getMessage(String from, int messageCount, int messageType) throws MException;

    /**
     * 读取短信
     * 
     * @param from
     *            发送人号码
     * @param messageCount
     *            需要读取的信息数量， -1 为读入全部短信
     * @return 读取到的短信
     * @throws MException
     */
    List<ContentValues> getMessage(String from, int messageCount) throws MException;

    /**
     * 将所有短信设为已读
     * 
     * @throws MException
     */
    void setAllMessageReaded() throws MException;

    /**
     * 删除所有短信
     * 
     * @throws MException
     */
    void deleteAllMessage() throws MException;

    /**
     * 在手机浏览器上打开网址
     * 
     * @param url
     *            指定的网址
     * @return true 打开成功
     * @throws MException
     */
    boolean wapOpen(String url) throws MException;

    /**
     * 保存文件到凭证
     * 
     * @param path
     *            文件路径
     * @return
     * @throws MException
     */
    boolean saveImageToCertificate(String path) throws MException;

    /**
     * 保存屏幕中查找文字凭证
     * 
     * @param expect
     *            期待结果
     * @param reality
     *            实际结果
     * @return true 保存成功
     */
    boolean saveOcrScreenCertificate(String expect, String reality) throws MException;

    /**
     * 保存在当前屏幕中的特定区域查找文字凭证
     * 
     * @param expect
     *            期待结果
     * @param reality
     *            实际结果
     * @return true 保存成功
     */
    boolean saveOcrRectCertificate(String expect, String reality) throws MException;

    /**
     * 保存屏幕中查找文字凭证
     * 
     * @return true 保存成功
     */
    boolean saveOcrScreenMatchCertificate() throws MException;

    /**
     * 保存在当前屏幕中的特定区域查找文字凭证
     * 
     * @return true 保存成功
     */
    boolean saveOcrRectMatchCertificate() throws MException;

    /**
     * 保存校验图片凭证
     * 
     * @return true 保存成功
     */
    boolean saveVerifyCertificate() throws MException;

    /**
     * 保存校验图片凭证
     * 
     * @return true 保存成功
     */
    boolean saveFindInCertificate() throws MException;

    /**
     * 让当前线程等待
     * 
     * @param millis
     *            等待时间,以毫秒为单位
     */
    void waitMillis(long millis);

    /**
     * <b style="color:red">获取当前的模式(此API,不提供使用)</b>
     * 
     * @return
     * @throws MException
     */
    String getModel() throws MException;

    /**
     * <b style="color:red">设置运行参数(此API,不提供使用)</b>
     * 
     * @param params
     *            设置参数
     * @throws MException
     */
    void setDebugParams(ContentValues params) throws MException;

    /**
     * <b style="color:red">取回运行结果(此API,不提供使用)</b>
     * 
     * @return 参数
     * @throws MException
     */
    ContentValues getDebugResult() throws MException;

    /**
     * 用浏览器上打开网址
     * 
     * @param url
     *            指定的网址
     * @param widgetTestEnable
     *            true： 使用自定浏览器打开网址，支持控件化测试； false： 使用系统缺省浏览器打开万只，不支持控件化测试。
     * @return true 打开成功
     * @throws MException
     */
    boolean wapOpen(String url, boolean widgetTestEnable) throws MException;

    /**
     * 关闭使用自定浏览器打开网址，支持控件化测试
     */
    void wapClose() throws MException;

    /**
     * 开始抓TCP包
     * 
     * @throws MException
     */
    public void startGrabTcpPackage() throws MException;

    /**
     * 结束抓TCP包
     * 
     * @throws MException
     */
    public void stopGrabTcpPackage() throws MException;

    /**
     * 查找控件
     * 
     * @param key
     *            控件属性名
     * @param value
     *            控件属性值
     * @return 返回符合条件的控件列表
     * @throws MException
     */
    public List<ContentValues> findWidget(String key, String value) throws MException;

    /**
     * 查找控件个数
     * 
     * @param key
     *            控件属性名
     * @param value
     *            控件属性值
     * @return 返回符合条件的控件列表
     * @throws MException
     */
    public int countWidget(String key, String value) throws MException;

    /**
     * 查找控件，点击控件
     * 
     * @param key
     *            控件属性名
     * @param value
     *            控件属性值
     * @return true 成功， false 失败
     * @throws MException
     */
    public boolean clickWidget(String key, String value) throws MException;

    /**
     * 查找控件，返回控件mText属性
     * 
     * @param key
     *            控件属性名
     * @param value
     *            控件属性值
     * @return 控件的mText属性, null 没有符合条件的控件
     * @throws MException
     */
    public String getWidgetText(String key, String value) throws MException;

    /**
     * 查web找控件
     * 
     * @param key
     *            控件属性名
     * @param value
     *            控件属性值
     * @return 返回符合条件的控件列表
     * @throws MException
     */
    public List<ContentValues> findWebWidget(String key, String value) throws MException;

    /**
     * 查找web控件个数
     * 
     * @param key
     *            控件属性名
     * @param value
     *            控件属性值
     * @return 返回符合条件的控件列表
     * @throws MException
     */
    public int countWebWidget(String key, String value) throws MException;

    /**
     * 查找web控件，点击控件
     * 
     * @param key
     *            控件属性名
     * @param value
     *            控件属性值
     * @return true 成功， false 失败
     * @throws MException
     */
    public boolean clickWebWidget(String key, String value) throws MException;

    /**
     * 查找web控件，返回控件mText属性
     * 
     * @param key
     *            控件属性名
     * @param value
     *            控件属性值
     * @return true 成功， false 失败
     * @throws MException
     *             , null 没有符合条件的控件
     */
    public String getWebWidgetText(String key, String value) throws MException;

    /**
     * 取resources.xml配置文件中的res值
     * 
     * @param key
     *            resource的Key
     * @return resource 的值
     * @throws MException
     */
    public String getResource(String key) throws MException;

    /**
     * 取resources.xml配置文件中的res值
     * 
     * @param key
     *            resource的Key
     * @return resource 的值
     * @throws MException
     */
    public int getResourceInt(String key) throws MException;

    /**
     * 取resources.xml配置文件中的ocr配置， 根据配置进行OCR，并根据OCR的配置进行校验, 如果失败生成凭证
     * 
     * @param key
     *            ocr配置的Key
     * @return true 成功， false 失败
     * @throws MException
     */
    public boolean ocrWithResource(String key) throws MException;

    /**
     * 告警
     * <p>
     * <b style="color:red">关于错误码的定义,请选择以 ALARM_开头的常量</b>
     * 
     * @param code
     *            告警代码
     * @return message 告警消息
     * @throws MException
     */
    public void alarm(String code, String message) throws MException;

    /**
     * 获取文件大小
     * 
     * @param path
     *            文件所在的文件夹
     * @param fileName
     *            文件名,可为null， 为null时获取最新文件的size
     * @return 文件大小
     * @throws MException
     */
    public long getFileSize(String path, String fileName) throws MException;

    /**
     * 删除一个文件
     * 
     * @param path
     *            文件所在的文件夹
     * @param fileName
     *            文件名，name可模糊匹配，contain关系
     * @return true 成功
     * @throws MException
     */
    public boolean deleteFile(String path, String fileName) throws MException;

    /**
     * 在屏幕中指定的坐标同时按下,最多支持3点
     * 
     * @param xys
     *            坐标数组,new int [] [] {new int [] {x,y},new int [] {x,y}}; 屏幕坐标
     * @return true
     * @throws MException
     */
    public boolean screenMutilDown(int[]... xys) throws MException;

    /**
     * 在屏幕中指定的坐标同时移动,最多支持3点
     * 
     * @param xys
     *            坐标数组,new int [] [] {new int [] {x,y},new int [] {x,y}}; 屏幕坐标
     * @return
     * @throws MException
     */
    public boolean screenMutilMove(int[]... xys) throws MException;

    /**
     * 在屏幕中指定的坐标同时释放,最多支持3点
     * 
     * @param xys
     *            坐标数组,new int [] [] {new int [] {x,y},new int [] {x,y}}; 屏幕坐标
     * @return
     * @throws MException
     */
    public boolean screenMutilUp(int[]... xys) throws MException;

    /**
     * 屏幕放大图片动作(两点放大)
     * 
     * @param sx
     *            第一个点起始的x坐标
     * @param sy
     *            第一个点起始的y坐标
     * @param sx1
     *            第二个点起始的x坐标
     * @param sy1
     *            第二个点起始的y坐标
     * @param ex
     *            第一个点结束的x坐标
     * @param ey
     *            第一个点结束的y坐标
     * @param ex1
     *            第二个点结束的x坐标
     * @param ey1
     *            第二个点结束的y坐标
     * @return true 成功
     * @throws MException
     */
    public boolean screenZoom(int sx, int sy, int sx1, int sy1, int ex, int ey, int ex1, int ey1) throws MException;

    /**
     * 屏幕放大图片动作(三点放大)
     * 
     * @param sx
     *            第一个点起始的x坐标
     * @param sy
     *            第一个点起始的y坐标
     * @param sx1
     *            第二个点起始的x坐标
     * @param sy1
     *            第二个点起始的y坐标
     * @param sx2
     *            第三个点起始的x坐标
     * @param sy2
     *            第三个点起始的y坐标
     * @param ex
     *            第一个点结束的x坐标
     * @param ey
     *            第一个点结束的y坐标
     * @param ex1
     *            第二个点结束的x坐标
     * @param ey1
     *            第二个点结束的y坐标
     * @param ex2
     *            第三个点结束的x坐标
     * @param ey2
     *            第三个点结束的y坐标
     * @return true 成功
     * @throws MException
     */
    public boolean screenZoom(int sx, int sy, int sx1, int sy1, int sx2, int sy2, int ex, int ey, int ex1, int ey1,
            int ex2, int ey2) throws MException;

    /**
     * 屏幕放大图片动作(两点放大)
     * 
     * @param sx
     *            第一个点起始的x坐标
     * @param sy
     *            第一个点起始的y坐标
     * @param sx1
     *            第二个点起始的x坐标
     * @param sy1
     *            第二个点起始的y坐标
     * @param ex
     *            第一个点结束的x坐标
     * @param ey
     *            第一个点结束的y坐标
     * @param ex1
     *            第二个点结束的x坐标
     * @param ey1
     *            第二个点结束的y坐标
     * @return true 成功
     * @throws MException
     */
    public boolean screenShrink(int sx, int sy, int sx1, int sy1, int ex, int ey, int ex1, int ey1) throws MException;

    /**
     * 屏幕放大图片动作(三点放大)
     * 
     * @param sx
     *            第一个点起始的x坐标
     * @param sy
     *            第一个点起始的y坐标
     * @param sx1
     *            第二个点起始的x坐标
     * @param sy1
     *            第二个点起始的y坐标
     * @param sx2
     *            第三个点起始的x坐标
     * @param sy2
     *            第三个点起始的y坐标
     * @param ex
     *            第一个点结束的x坐标
     * @param ey
     *            第一个点结束的y坐标
     * @param ex1
     *            第二个点结束的x坐标
     * @param ey1
     *            第二个点结束的y坐标
     * @param ex2
     *            第三个点结束的x坐标
     * @param ey2
     *            第三个点结束的y坐标
     * @return true 成功
     * @throws MException
     */
    public boolean screenShrink(int sx, int sy, int sx1, int sy1, int sx2, int sy2, int ex, int ey, int ex1, int ey1,
            int ex2, int ey2) throws MException;

    /**
     * 取得案例的指标编号
     * 
     * @return
     */
    public List<String> getCaseIndex() throws MException;
}
