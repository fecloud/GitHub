/**
 * @(#) TestAPI.java Created on 2012-7-13
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.demo;

import java.util.List;

import android.os.Environment;
import android.util.Log;

import com.aspire.android.test.exception.MException;
import com.aspire.android.test.execute.ContentValues;
import com.aspire.android.test.testcase.AbstractTestCase;

/**
 * The class <code>TestAPI</code>
 * 
 * @author Administrator
 * @version 1.0
 */
public class TestAPI extends AbstractTestCase {

    public static final String TAG = "TestAPI";

    /**
     * verifyScreen (non-Javadoc)
     * 
     * @see com.aspire.android.test.testcase.AbstractTestCase#doExecute()
     */
    @Override
    protected void doExecute() throws Exception {
        // stopApp();
//        for (int i = 0; i < 3; i++) {
//            logScreen("str");
//        }
        
//        final String tag = deviceEntity.saveScreen(null, "123");
//        System.out.println(tag);
//        deviceEntity.saveImageToCertificate("/sdcard/38.jpg");

        // deviceEntity.screenClick(60, 453);

        // deviceEntity.screenDown(10, 20);
        // final String reStr = deviceEntity.ocrScreen(OCR_SIMPLIPIED_CHINESE);
        // deviceEntity.log(LOG_LEVEL_DEBUG, reStr);
        // final String reStr = deviceEntity.ocrScreen(OCR_ENGLISH);
        // deviceEntity.log(LOG_LEVEL_DEBUG, reStr);
        // deviceEntity.beginTransaction("7001");
        // int[] xys = deviceEntity.findInScreen("device-2012-07-13-164430.jpg",
        // 0, 0, 480, 401, 100);
        // deviceEntity.log(LOG_LEVEL_DEBUG, "xys:" + xys);
        // if (null != xys) {
        // deviceEntity.log(LOG_LEVEL_DEBUG, "xys:" + xys[0] + "-" + xys[1]);
        // deviceEntity.endTransaction("7001", "", true);
        // }else{
        // deviceEntity.saveFindInCertificate();
        // deviceEntity.endTransaction("7001", "", false , true);
        // }

        // deviceEntity.beginTransaction("7001");
        // String ocr = deviceEntity.ocrScreen(OCR_SIMPLIPIED_CHINESE);
        // if (null != ocr && ocr.contains("设置")) {
        // deviceEntity.endTransaction("7001", "", true);
        // } else {
        // deviceEntity.saveOcrScreenCertificate("设置", ocr);
        // deviceEntity.endTransaction("7001", "", false, true);
        // }
        // deviceEntity.set

        // deviceEntity.beginTransaction("7001");
        // String ocr = deviceEntity.ocrRect(0, 0, 480, 100, OCR_NUMBER);
        // if (null != ocr && ocr.contains("14")) {
        // deviceEntity.log(LOG_LEVEL_DEBUG, "ocr:" + ocr);
        // deviceEntity.endTransaction("7001", "", true);
        // } else {
        // deviceEntity.saveOcrRectCertificate("14", ocr);
        // deviceEntity.endTransaction("7001", "", false, true);
        // }

        // int[] point = deviceEntity.findInScreen("pic.jpg", 0, 0, 480, 56,
        // 100);
        // Toast.makeText(getContext(), "" + point, Toast.LENGTH_SHORT).show();
        // boolean con = deviceEntity.verifyScreen(0, 240, "pic.jpg", 0, 0, 480,
        // 56, 100, 100);
        // deviceEntity.log(LOG_LEVEL_DEBUG, "" + con);
        // /13715235421;

        /*
         * boolean con = deviceEntity.sendMessage("18665934114", "" + System.currentTimeMillis()); assertTrue(con);
         */

        // System.out.println(con);

        // deviceEntity.keyDown(KeyEvent.KEYCODE_HOME);
        // deviceEntity.waitMillis(2000);
        // deviceEntity.keyUp(KeyEvent.KEYCODE_HOME);

        // deviceEntity.screenTouch(new int[][] { { 0, 200 }, { 200, 200 }, {
        // 480, 200 } });
        // int num = 5;
        // int step = 480 / num;
        // int[][] xys = new int[num][2];
        // for (int i = 0; i <num; i++) {
        // xys[i][0] = 200 * i;
        // xys[i][1] = 200;
        // }
        // deviceEntity.screenTouch(xys);
        // deviceEntity.sendMessage("18665934114", "" +
        // System.currentTimeMillis());
        // deviceEntity.keyClick(KeyEvent.KEYCODE_HOME);

        // deviceEntity.keyLongClick(KeyEvent.KEYCODE_HOME);
        // deviceEntity.deleteAllMessage();
        // deviceEntity.setAllMessageReaded();

        /*
         * 抓包的 deviceEntity.startGrabTcpPackage(); deviceEntity.waitMillis(50000); deviceEntity.stopGrabTcpPackage();
         */

        /*
         * deviceEntity.wapOpen("http://www.baidu.com", true);
         * 
         * List<ContentValues> list = deviceEntity.findWebWidget(CommandConstants.WAP_NAME, "q");
         * deviceEntity.log(LOG_LEVEL_DEBUG, "find elementbyname q count:"+ list.size()); int count =
         * deviceEntity.countWebWidget(CommandConstants.WAP_NAME, "q"); deviceEntity.log(LOG_LEVEL_DEBUG,
         * "find elementbyname q count:"+ count); deviceEntity.clickWebWidget(CommandConstants.WAP_LINKTEXT, "新闻");
         * //deviceEntity.wapClose(); // deviceEntity.getMessage(10);
         */

        // ----------------------
        // final boolean isOk = deviceEntity.ocrWithResource("DXHZ_SMSSub_MT_DATA");
        // assertTrue(isOk);
        // -----------------------
        // deviceEntity.log(LOG_LEVEL_DEBUG , "message");
        // deviceEntity.logScreen(LOG_LEVEL_ERROR , "message");
        // deviceEntity.screenTouch(120, 400, 120, 130);

        /**/
        // deviceEntity.beginTransaction("123");
        // final boolean con = deviceEntity.ocrWithResource("DCD_AddChannel_MT_DATA");
        // Log.d(TAG, "con:" + con);
        // deviceEntity.endTransaction("123", TRANSACTION_SUCCESS, true);

        // ---------------------------------------
        // logError("123");
        // --------------------------------------
        // ocrWithResource();
        // ocrScreenMatch();
        // ocrRectMatch();
        // ocrScreen();
        // final String filepath = deviceEntity.saveScreen("/mnt/sdcard/aspire/attachments", "name.jpg");
        // int[] point = deviceEntity.findPicture(filepath, 0, 0, 0, 0, "name.jpg", 30, 30, 0, 0, 10);
        // assertNotNull(point);
        // getFileSize();
        // findAndClick1();
        // screenMutilDown();
        // screenMutilMove();
        // screenMutilUp();
        // findSMS();

        // screenZoom();
        // deleteFile();
        // findInScreen();
        // verifyScreen();
        // input();
        // stopApp();
        // assertTrue(findAndClick("SamSungI9108_999010_50_29_click.bmp", 20, 6000));

        // Log.d(TAG, "start:");
        // deviceEntity.findInScreen("SamSungI9108_999010_50_29_click.bmp", 0, 0, 1, 1, 20);
        // deviceEntity.findInScreen(0, 0, 480, 200, "SamSungI9108_999010_50_29_click.bmp", 0, 0, 1, 1, 20);
        // deviceEntity.findInScreen(202, 229, 200, 200, "SamSungI9108_999010_50_29_click.bmp", 0, 0, 50, 29, 20);
        // deviceEntity.findInScreenCom(0, 0, -1, -1, "SamSungI9108_999010_50_29_click.bmp", 0, 0, 1, 1, 20, 20, 20);
        // logScreen("str")
        // int [] result = deviceEntity.findInScreenCom(0 , 0, -1, -1, "SamSungI9108_999010_50_29_click.bmp", 0, 0, 50,
        // 39, 20, 20, 10);
        // int [] result = findInScreenCom(1, 1, 480, 800, "SamSungI9108_999010_50_29_click.bmp", 0, 0, 11, 11, 10, 21,
        // 21, 6000);
        // Log.d(TAG, "end");
        // assertNotNull(result);
        //input();
        deviceEntity.deleteInput(4, 0);
    }

    public void stopApp() {
        try {
            final boolean con = super.deviceEntity.stopApp("评估程序");
            assertTrue(con);
        } catch (MException e) {
            e.printStackTrace();
        }
    }

    public void findSMS() throws MException {

        List<ContentValues> list = deviceEntity.getMessage("10086", -1, -1);
        System.out.println(list.size());
        // final boolean con = findSms("10086", new String [] {"中国"}, true, 10000);
        // assertTrue(con);
    }

    public void screenZoom() throws MException {
        int sx = 129;
        int sy = 471;
        int sx1 = 290;
        int sy1 = 367;
        int ex = 100;
        int ey = 501;
        int ex1 = 340;
        int ey1 = 307;
        final boolean con = deviceEntity.screenZoom(sx, sy, sx1, sy1, ex, ey, ex1, ey1);
        assertTrue(con);
    }

    public void screenMutilDown() throws MException {
        final int[] one = new int[] { 129, 471 };
        final int[] two = new int[] { 290, 367 };
        final boolean con = deviceEntity.screenMutilDown(one, two);
        assertTrue(con);
    }

    public void screenMutilMove() throws MException {
        final int[] one = new int[] { 100, 501 };
        final int[] two = new int[] { 340, 307 };
        final boolean con = deviceEntity.screenMutilMove(one, two);
        assertTrue(con);
    }

    public void screenMutilUp() throws MException {
        final int[] one = new int[] { 100, 501 };
        final int[] two = new int[] { 340, 307 };
        final boolean con = deviceEntity.screenMutilUp(one, two);
        assertTrue(con);
    }

    public void deleteFile() throws MException {
        final boolean con = deviceEntity.deleteFile(Environment.getExternalStorageDirectory().getAbsolutePath(), "bg");
        assertTrue(con);
    }

    public void getFileSize() throws MException {
        long size = deviceEntity.getFileSize(Environment.getExternalStorageDirectory().getAbsolutePath(), null);
        assertTrue(size > 0);
    }

    protected void input() throws MException {
        deviceEntity.input("HuaweiT2211");
    }

    protected void getResource() throws MException {
        final String str = deviceEntity.getResource("SendSMS_DATA");
        assertEquals("hello------", str);
    }

    protected void ocrWithResource() throws MException {
        final String tsName = "123456";
        deviceEntity.beginTransaction(tsName);
        final boolean con = deviceEntity.ocrWithResource("DCD_AddChannel_MT_DATA");
        if (!con) {
            deviceEntity.saveVerifyCertificate();
            deviceEntity.endTransaction(tsName, TRANSACTION_BUSINESS_FAIL, true);
        }
        // assertTrue(con);
    }

    protected void getResourceInt() throws MException {
        final int i = deviceEntity.getResourceInt("COMMON_WAIT_AFTER_CAPTURE");
        assertTrue(i == 5);
    }

    protected void findAndClick() {
        final boolean con = findAndClick("360.jpg", 87, 83, 100, 2000);
        assertTrue(con);
    }

    protected void findInScreen() throws MException {
        final String tsName = "123456";
        deviceEntity.beginTransaction(tsName);

        // final int[] point = deviceEntity.findInScreen("call.jpg", 0, 0, 253, 77, 80);
        final int[] point = deviceEntity.findInScreen(113, 757, 253, 76, "call.jpg", 0, 0, 253, 77, 90);
        if (null == point) {
            // deviceEntity.saveFindInCertificate();
            deviceEntity.endTransaction(tsName, TRANSACTION_BUSINESS_FAIL, true);
        }
        // assertNotNull(point);
    }

    protected void verifyScreen() throws MException {
        // -------------------[114, 758]-------------------
        final String tsName = "123456";
        deviceEntity.beginTransaction(tsName);
        final boolean verify = deviceEntity.verifyScreen(113, 757, "call.jpg", 0, 0, 253, 77, 90, 80);
        if (!verify) {
            deviceEntity.endTransaction(tsName, TRANSACTION_BUSINESS_FAIL, true);
        }
        // assertTrue(verify);
    }

    protected void ocrScreen() throws MException {
        final String tsName = "123456";
        deviceEntity.beginTransaction(tsName);
        final String str = deviceEntity.ocrScreen(OCR_SIMPLIPIED_CHINESE);
        deviceEntity.saveOcrScreenCertificate("arg0", str);
        deviceEntity.endTransaction(tsName, TRANSACTION_BUSINESS_FAIL, true);
        // assertNotNull(str);
    }

    protected void ocrScreenMatch() throws MException {
        final String tsName = "123456";
        deviceEntity.beginTransaction(tsName);
        final boolean isMatched = deviceEntity.ocrScreenMatch(OCR_SIMPLIPIED_CHINESE, "启动测试服务");
        if (!isMatched) {
            deviceEntity.endTransaction(tsName, TRANSACTION_BUSINESS_FAIL, true);
        }
        // assertTrue(isMatched);
    }

    protected void ocrScreenMatch1() throws MException {
        final boolean isMatched = deviceEntity.ocrScreenMatch(OCR_SIMPLIPIED_CHINESE, "AND{启,动,测,试,服,务}");
        assertTrue(isMatched);
    }

    protected void ocrScreenMatch2() throws MException {
        final boolean isMatched = deviceEntity.ocrScreenMatch(OCR_SIMPLIPIED_CHINESE, "OR{启,动,测,试,服,务}");
        assertTrue(isMatched);
    }

    protected void ocrRectMatch() throws MException {
        final String tsName = "123456";
        deviceEntity.beginTransaction(tsName);
        final boolean isMatched = deviceEntity.ocrRectMatch(0, 108, 480, 50, OCR_SIMPLIPIED_CHINESE, "OR{启动测试服务,123}");
        if (!isMatched) {
            deviceEntity.endTransaction(tsName, TRANSACTION_BUSINESS_FAIL, true);
        }
        // assertTrue(isMatched);
    }

    protected void ocrRectMatch1() throws MException {
        final boolean isMatched = deviceEntity.ocrRectMatch(0, 108, 480, 50, OCR_SIMPLIPIED_CHINESE, "AND{启动测试服务,123}");
        assertTrue(isMatched);
    }

    protected void findAndClick1() {
        final boolean con = findAndClick("SamSungI9100_0745_251_76_call.jpg", 80, 10000);
        assertTrue(con);
    }

}
