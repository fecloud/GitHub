/**
 * @(#) DeviceEntity.java Created on 2012-5-10
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import android.view.ViewConfiguration;

import com.aspire.android.common.exception.ExceptionHandler;
import com.aspire.android.common.exception.MException;
import com.aspire.android.log.Logger;
import com.aspire.android.log.attachment.ImageAttachment;
import com.aspire.android.ocr.IOcrService;
import com.aspire.android.screen.check.CheckParam;
import com.aspire.android.screen.check.CheckResult;
import com.aspire.android.screen.check.certificate.CertificateFacotry;
import com.aspire.android.screen.check.find.FindInMatch;
import com.aspire.android.screen.check.find.FindInParam;
import com.aspire.android.screen.check.find.FindInResult;
import com.aspire.android.screen.check.find.com.FindInComMatch;
import com.aspire.android.screen.check.find.com.FindInComParam;
import com.aspire.android.screen.check.ocr.OCRMatch;
import com.aspire.android.screen.check.ocr.OCRParam;
import com.aspire.android.screen.check.ocr.OCRResult;
import com.aspire.android.screen.check.verify.VerifyMatch;
import com.aspire.android.screen.check.verify.VerifyParams;
import com.aspire.android.screen.check.verify.VerifyResult;
import com.aspire.android.test.execute.engine.EngineClient;
import com.aspire.android.test.execute.resource.OCRResource;
import com.aspire.android.util.FileUtil;
import com.aspire.mcts.agent.msg.APSMessage;
import com.aspire.mcts.agent.msg.CommonResponse;
import com.aspire.mcts.agent.msg.FileOperationRequest;
import com.aspire.mcts.agent.msg.FileOperationResponse;
import com.aspire.mcts.agent.msg.GrabImageRequest;
import com.aspire.mcts.agent.msg.GrabImageResponse;
import com.aspire.mcts.agent.msg.ImageMessage;
import com.aspire.mcts.agent.msg.KeyDownRequest;
import com.aspire.mcts.agent.msg.KeyDownResponse;
import com.aspire.mcts.agent.msg.KeyUpRequest;
import com.aspire.mcts.agent.msg.KeyUpResponse;
import com.aspire.mcts.agent.msg.MutilTouchDownRequest;
import com.aspire.mcts.agent.msg.MutilTouchDownResponse;
import com.aspire.mcts.agent.msg.MutilTouchMoveRequest;
import com.aspire.mcts.agent.msg.MutilTouchMoveResponse;
import com.aspire.mcts.agent.msg.MutilTouchUpRequest;
import com.aspire.mcts.agent.msg.MutilTouchUpResponse;
import com.aspire.mcts.agent.msg.TouchClickRequest;
import com.aspire.mcts.agent.msg.TouchClickResponse;
import com.aspire.mcts.agent.msg.TouchDownRequest;
import com.aspire.mcts.agent.msg.TouchDownResponse;
import com.aspire.mcts.agent.msg.TouchMoveRequest;
import com.aspire.mcts.agent.msg.TouchMoveResponse;
import com.aspire.mcts.agent.msg.TouchUpRequest;
import com.aspire.mcts.agent.msg.TouchUpResponse;
import com.aspire.mobile.MobileConstants;
import com.aspire.mobile.element.MobileMessageInfo;
import com.aspire.mobile.msg.AddMessageReq;
import com.aspire.mobile.msg.AddMessageResp;
import com.aspire.mobile.msg.ApplicationOperationReq;
import com.aspire.mobile.msg.ApplicationOperationResp;
import com.aspire.mobile.msg.GrabNetworkPackageReq;
import com.aspire.mobile.msg.GrabNetworkPackageResp;
import com.aspire.mobile.msg.InputOperationReq;
import com.aspire.mobile.msg.InputOperationResp;
import com.aspire.mobile.msg.MessageBoxQueryReq;
import com.aspire.mobile.msg.MessageBoxQueryResp;
import com.aspire.mobile.msg.MobileMsgBase;
import com.aspire.mobile.msg.SMSOperationReq;
import com.aspire.mobile.msg.SMSOperationResp;
import com.aspire.mobile.msg.WapOperationReq;
import com.aspire.mobile.msg.WapOperationResp;
import com.aspire.util.ToolException;

/**
 * The class <code>DeviceEntity</code>
 * 
 * @author linjunsui
 * @version 1.0
 */
public class DeviceEntity {

    private static final String TAG = "DeviceEntity";

    /**
     * engineClient
     */
    protected EngineClient engineClient;

    /**
     * testcase params
     */
    protected ContentValues testcaseParams;

    /**
     * aidl ocr service
     */
    private IOcrService ocrService;

    private FindInParam findInParam;
    private FindInResult findInResult;

    private VerifyParams verifyParams;
    private VerifyResult verifyResult;

    private OCRParam ocrScreenParam, ocrRectParam, ocrScreenMatchParam, ocrRectMatchParam, ocrResourceParam;
    private OCRResult ocrScreenResult, ocrRectResult, ocrScreenMatchResult, ocrRectMatchResult, ocrResourceResult;

    private CheckParam lastCheckParam;
    private CheckResult lastcCheckResult;

    /**
     * 凭证保存
     */
    private byte[] certificate;

    /**
     * @return the ocrService
     */
    public IOcrService getOcrService() {
        return ocrService;
    }

    /**
     * @param ocrService
     *            the ocrService to set
     */
    public void setOcrService(IOcrService ocrService) {
        this.ocrService = ocrService;
    }

    /**
     * Constructor
     * 
     * @param engineClient
     */
    public DeviceEntity(EngineClient engineClient) {
        super();
        this.engineClient = engineClient;
        engineClient.start();
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#saveResult(com.aspire.android.test.execute.ContentValues)
     * 
     *      public void saveResult(ContentValues resultMap) throws MException { }
     */
    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#getSetting()
     * 
     *      public ContentValues getSetting() throws MException { return null; }
     */
    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#getGlobalVariables()
     * 
     *      public ContentValues getGlobalVariables() throws MException { return null; }
     */

    /**
     * 锟斤拷锟斤拷凭证
     * 
     * @param params
     * @param startTime
     * @param endTime
     * @param name
     * @param value
     * @param isSucess
     * @param saveCertificate
     * @param attachmentPath
     *            凭证图片保存目录
     * @throws MException
     */

    public void saveTransaction(ContentValues params, long startTime, long endTime, String name, String value,
            String result, boolean saveCertificate, String attachmentPath) throws MException {
        // TODO 未完成
        // TransactionData data = null;
        // if (saveCertificate) {
        // // set attachment value
        // // save certificate to disk
        // if (null == certificate) {
        // FileUtil.writeFile(attachmentPath + File.separator + "cf" + System.currentTimeMillis() + ".jpg",
        // getScreen());
        // } else {
        // FileUtil.writeFile(attachmentPath + File.separator + "cf" + System.currentTimeMillis() + ".jpg",
        // certificate);
        // }
        // data = new TransactionData(name, value, attachmentPath, startTime, endTime, result);
        // } else {
        // data = new TransactionData(name, value, startTime, endTime, result);
        // }
        // params.getTransactionDatas().add(data);

        TransactionData data = null;
        if (saveCertificate) {
            if (certificate == null && null != lastCheckParam && null != lastcCheckResult) {
                certificate = CertificateFacotry.generateCertificate(lastCheckParam, lastcCheckResult);
            }
            if (null == certificate)
                certificate = getScreen();// no invoke verify findIn ocr
            // save certificate to disk
            final String attachmentFullPath = attachmentPath + File.separator + "cf" + System.currentTimeMillis()
                    + ".jpg";
            FileUtil.writeFile(attachmentFullPath, certificate);
            Log.d(TAG, "save certificate " + attachmentFullPath);
            data = new TransactionData(name, value, attachmentFullPath, startTime, endTime, result);
        } else {
            data = new TransactionData(name, value, startTime, endTime, result);
        }
        params.getTransactionDatas().add(data);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#log(int, java.lang.String)
     */
    public void log(int logLevel, String message, Logger log) throws MException {
        log.log(logLevel, message);

    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#logScreen(int, java.lang.String)
     */
    public void logScreen(int logLevel, String message, Logger log) throws MException {
        final byte[] bytes = getScreen();
        Log.d(DeviceEntity.class.getSimpleName(), "logScreen size:" + bytes.length);
        final String filePath = log.getEnvironment().get("KEY_CASE_LOGLOCATION") + File.separator + UUID.randomUUID()
                + ".jpg";
        log.log(logLevel, message, new ImageAttachment(bytes, filePath));
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#ocrScreen(int)
     */
    public String ocrScreen(int lang) throws MException {
        String result = null;
        try {
            int count = 0;
            while (count < 2000) {
                if (ocrService != null) {
                    break;
                }
                try {
                    Thread.sleep(200);
                    count += 200;
                } catch (InterruptedException e) {
                }
            }
            final byte[] screenBytes = getScreen();
            ocrScreenParam = new OCRParam(screenBytes, 0, 0, 0, 0, lang, null);
            result = ocrService.ocrAll(screenBytes, lang, 1, -1);
        } catch (RemoteException e) {
            throw new MException("Ocr screen error ", e);
        }
        return result;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#ocrRect(int, int, int, int, int)
     */
    public String ocrRect(int startX, int startY, int width, int height, int lang) throws MException {
        String result = null;
        try {
            int count = 0;
            while (count < 2000) {
                if (ocrService != null) {
                    break;
                }
                try {
                    Thread.sleep(200);
                    count += 200;
                } catch (InterruptedException e) {
                }
            }
            final byte[] screenBytes = getScreen();
            ocrRectParam = new OCRParam(screenBytes, startX, startY, width, height, lang, null);
            result = ocrService.ocrRect(screenBytes, lang, startX, startY, width, height, 1, -1);
        } catch (RemoteException e) {
            throw new MException("Ocr screen error ", e);
        }
        return result;
    }

    /**
     * 
     */
    public boolean ocrWithResource(OCRResource ocrResource) throws MException {

        // TODO 未完成
        // if (null != ocrParam) {
        // String ocrResultText = null;
        // try {
        // int count = 0;
        // while (count < 10000) {
        // if (ocrService != null) {
        // break;
        // }
        // try {
        // Thread.sleep(200);
        // count += 200;
        // } catch (InterruptedException e) {
        // }
        // }
        // final byte[] screenBytes = getScreen();
        // int lang = ocrParam.lang != -1 ? ocrParam.lang : OcrParam.OCR_SIMPLIPIED_CHINESE; // 如果没有设置ocr语言,默认简中文
        // if (ocrParam.x1 == 0 && ocrParam.x2 == 0 && ocrParam.y1 == 0 && ocrParam.y2 == 0) { // 是否是全屏查找
        // ocrResultText = ocrService.ocrAll(screenBytes, lang, 1, -1);
        // } else {
        // ocrResultText = ocrService.ocrRect(screenBytes, lang, ocrParam.x1, ocrParam.y1, ocrParam.x2
        // - ocrParam.x1, ocrParam.y2 - ocrParam.y1, 1, -1);
        // }
        // Log.d(TAG, "ocr find text:" + ocrResultText);
        // final OcrResult ocrResult = ScreenOCR.ocr(ocrResultText, ocrParam); // 文字对比
        // certificate = ImageUtil.createOcrWarrant(screenBytes, ocrParam.x1, ocrParam.y1, ocrParam.x2,
        // ocrParam.y2, ocrResult.getOcrWarrant(), ocrResultText);
        // return ocrResult.isMatched();
        // } catch (RemoteException e) {
        // throw new MException("Ocr screen error ", e);
        // } catch (ToolExcOeption e) {
        // MException mexception = ExceptionHandler.handle(e, "");
        // if (mexception != null) {
        // throw mexception;
        // }
        // } catch (IOException e) {
        // MException mexception = ExceptionHandler.handle(e, "");
        // if (mexception != null) {
        // throw mexception;
        // }
        // }
        // }
        if (null == ocrResource)
            return false;
        int count = 0;
        while (count < 2000) {
            if (ocrService != null) {
                break;
            }
            try {
                Thread.sleep(200);
                count += 200;
            } catch (InterruptedException e) {
            }
        }
        ocrResourceParam = ocrResource;
        ocrResourceParam.setScreenBytes(getScreen());
        ocrResourceResult = new OCRMatch(ocrService, ocrResourceParam).match();
        // 如果失败,自动生成凭证
        if (!ocrResourceResult.isMatched()) {
            certificate = CertificateFacotry.generateCertificate(ocrResourceParam, ocrResourceResult);
        }
        return ocrResourceResult.isMatched();
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#ocrScreenMatch(int, java.lang.String)
     */
    public boolean ocrScreenMatch(int lang, String expect) throws MException {
        int count = 0;
        while (count < 10000) {
            if (ocrService != null) {
                break;
            }
            try {
                Thread.sleep(200);
                count += 200;
            } catch (InterruptedException e) {
            }
        }
        final byte[] screenBytes = getScreen();
        lastCheckParam = ocrScreenMatchParam = new OCRParam(screenBytes, 0, 0, 0, 0, lang, expect);
        lastcCheckResult = ocrScreenMatchResult = new OCRMatch(ocrService, ocrScreenMatchParam).match();
        return ocrScreenMatchResult.isMatched();
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#ocrRectMatch(int, int, int, int, int, java.lang.String)
     */
    public boolean ocrRectMatch(int startX, int startY, int width, int height, int lang, String expect)
            throws MException {
        int count = 0;
        while (count < 10000) {
            if (ocrService != null) {
                break;
            }
            try {
                Thread.sleep(200);
                count += 200;
            } catch (InterruptedException e) {
            }
        }
        final byte[] screenBytes = getScreen();
        lastCheckParam = ocrRectMatchParam = new OCRParam(screenBytes, startX, startY, width, height, lang, expect);
        lastcCheckResult = ocrRectMatchResult = new OCRMatch(ocrService, ocrRectMatchParam).match();
        return ocrRectMatchResult.isMatched();
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#screenDown(int, int)
     */
    public void screenDown(int x, int y) throws MException {
        final TouchDownRequest request = new TouchDownRequest(new int[] { x, y });
        final TouchDownResponse response = (TouchDownResponse) engineClient.sendAndResponse(request);
        checkingAPSMessageResponse(response);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#screenMove(int, int)
     */
    public void screenMove(int x, int y) throws MException {
        final TouchMoveRequest request = new TouchMoveRequest(new int[] { x, y });
        final TouchMoveResponse response = (TouchMoveResponse) engineClient.sendAndResponse(request);
        checkingAPSMessageResponse(response);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#screenUp(int, int)
     */
    public void screenUp(int x, int y) throws MException {
        final TouchUpRequest request = new TouchUpRequest(new int[] { x, y });
        final TouchUpResponse response = (TouchUpResponse) engineClient.sendAndResponse(request);
        checkingAPSMessageResponse(response);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#screenClick(int, int)
     */
    public void screenClick(int x, int y) throws MException {
        final TouchClickRequest request = new TouchClickRequest(new int[] { x, y });
        final TouchClickResponse response = (TouchClickResponse) engineClient.sendAndResponse(request);
        checkingAPSMessageResponse(response);
    }

    /**
     * 
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#screenTouch(int[][])
     */
    public void screenTouch(int x, int y, int toX, int toY, Context mContext) throws MException {
        final ViewConfiguration configuration = ViewConfiguration.get(mContext);
        final int mTouchSlop = configuration.getScaledTouchSlop();

        final int xdiff = Math.abs(x - toX);
        final int ydiff = Math.abs(y - toY);
        int[] xpoints = null;
        int[] ypoints = null;
        if (xdiff > ydiff) {
            xpoints = calcPoint(x, toX, mTouchSlop);
            ypoints = calcPoint2(y, toY, xpoints.length);
        } else if (xdiff == ydiff) {
            xpoints = calcPoint(x, toX, mTouchSlop);
            ypoints = xpoints;
        } else {
            ypoints = calcPoint(y, toY, mTouchSlop);
            xpoints = calcPoint2(x, toX, ypoints.length);
        }

        // 点击
        screenDown(x, y);
        for (int i = 0; i < xpoints.length; i++) {
            // System.out.println("x:" + xpoints[i] + " y:" + ypoints[i]);

            screenMove(x + xpoints[i], y + ypoints[i]);
        }
        screenMove(toX, toY);
        screenUp(toX, toY);// 按上

    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#keyUp(int)
     */
    public void keyUp(int key) throws MException {
        final KeyUpRequest request = new KeyUpRequest(new short[] { (short) key });
        final KeyUpResponse response = (KeyUpResponse) engineClient.sendAndResponse(request);
        checkingAPSMessageResponse(response);

    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#keyDown(int)
     */
    public void keyDown(int key) throws MException {
        final KeyDownRequest request = new KeyDownRequest(new short[] { (short) key });
        final KeyDownResponse response = (KeyDownResponse) engineClient.sendAndResponse(request);
        checkingAPSMessageResponse(response);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#keyClick(int)
     */
    public void keyClick(int key) throws MException {
        keyDown(key);
        keyUp(key);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#keyLongClick(int)
     */
    public void keyLongClick(int key) throws MException {
        keyDown(key);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new MException(e);
        }
        keyUp(key);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#verifyScreen(int, int, java.lang.String, int, int, int,
     *      int, int, int)
     */
    public boolean verifyScreen(int inputStartX, int inputStartY, String templateImagePath, int templateStartX,
            int templateStartY, int w, int h, int colorToleranceAsPercent, int pixelTolerance) throws MException {
        try {
            final byte[] screenBytes = getScreen();
            // save method params
            // verifyScreen = new ContentValues();
            // verifyScreen.put(CommandConstants.KEY_VERIFYSCREEN_INPUTSTARTX, inputStartX);
            // verifyScreen.put(CommandConstants.KEY_VERIFYSCREEN_INPUTSTARTY, inputStartY);
            // verifyScreen.put(CommandConstants.KEY_VERIFYSCREEN_TEMPLATEIMAGEPATH, templateImagePath);
            // verifyScreen.put(CommandConstants.KEY_VERIFYSCREEN_TEMPLATESTARTX, templateStartX);
            // verifyScreen.put(CommandConstants.KEY_VERIFYSCREEN_TEMPLATESTARTY, templateStartY);
            // verifyScreen.put(CommandConstants.KEY_VERIFYSCREEN_W, w);
            // verifyScreen.put(CommandConstants.KEY_VERIFYSCREEN_H, h);
            // verifyScreen.put(CommandConstants.KEY_VERIFYSCREEN_COLORTOLERANCEASPERCENT, colorToleranceAsPercent);
            // verifyScreen.put(CommandConstants.KEY_VERIFYSCREEN_PIXELTOLERANCE, pixelTolerance);
            // verifyScreen.put(CommandConstants.KEY_SCREEN_BYTE, screenBytes);
            //
            // final Bitmap inputImage = BitmapFactory.decodeStream(new ByteArrayInputStream(screenBytes));
            // final Bitmap templateImage = FileUtil.decodeStreamOfBitmap(templateImagePath);
            //
            // int colorToleranceAsPixel = calcPixel(256, colorToleranceAsPercent);
            //
            // final boolean verify = ImageUtil.verify(inputImage, inputStartX, inputStartY, templateImage,
            // templateStartX, templateStartY, w, h, colorToleranceAsPixel, pixelTolerance);
            // return verify;
            final int colorToleranceAsPixel = calcPixel(256, colorToleranceAsPercent);
            lastCheckParam = verifyParams = new VerifyParams(screenBytes, inputStartX, inputStartY, -1, -1,
                    templateImagePath, templateStartX, templateStartY, w, h, colorToleranceAsPixel, pixelTolerance);
            lastcCheckResult = verifyResult = new VerifyMatch(verifyParams).match();
            return verifyResult.isMatched();
        } catch (Exception e) {
            throw ExceptionHandler.handle(e);
        }
    }

    /**
     * 跟据颜色百分比计算颜色数
     * 
     * @param totalColor
     *            总颜色数
     * @param colorToleranceAsPercent
     *            颜色百分比
     * @return pixel count
     */
    private int calcPixel(int totalColor, int colorToleranceAsPercent) {
        return colorToleranceAsPercent * 3 * totalColor / 100;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#findInScreen(java.lang.String, int, int, int, int, int)
     */
    public int[] findInScreen(int inputStartX, int inputStartY, int inputWidth, int inputHeight,
            String templateImagePath, int templateStartX, int templateStartY, int w, int h, int colorToleranceAsPercent)
            throws MException {
        try {
            final byte[] screenBytes = getScreen();
            final int colorToleranceAsPixel = calcPixel(256, colorToleranceAsPercent);
            lastCheckParam = findInParam = new FindInParam(screenBytes, inputStartX, inputStartY, inputWidth,
                    inputHeight, templateImagePath, templateStartX, templateStartY, w, h, colorToleranceAsPixel);
            lastcCheckResult = findInResult = new FindInMatch(findInParam).match();
            if (findInResult.isMatched()) {
                return findInResult.getFind();
            }
            return null;
        } catch (Exception e) {
            throw ExceptionHandler.handle(e);
        }
    }

    int[] findPicture(String inputImagePath, int inputStartX, int inputStartY, int inputWidth, int inputHeight,
            String templateImagePath, int templateStartX, int templateStartY, int w, int h, int colorToleranceAsPercent)
            throws MException {
        try {
            final byte[] screenBytes = FileUtil.readFile(inputImagePath);
            final int colorToleranceAsPixel = calcPixel(256, colorToleranceAsPercent);
            final FindInParam findInParam = new FindInParam(screenBytes, inputStartX, inputStartY, inputWidth,
                    inputHeight, templateImagePath, templateStartX, templateStartY, w, h, colorToleranceAsPixel);
            final FindInResult findInResult = new FindInMatch(findInParam).match();
            if (findInResult.isMatched()) {
                return findInResult.getFind();
            }
            return null;
        } catch (Exception e) {
            throw ExceptionHandler.handle(e);
        }
    }

    public int[] findInScreenCom(int inputStartX, int inputStartY, int inputWidth, int inputHeight,
            String templateImagePath, int templateStartX, int templateStartY, int w, int h,
            int colorToleranceAsPercent, int xStep, int yStep) throws MException {
        try {
            final byte[] screenBytes = getScreen();
            final int colorToleranceAsPixel = calcPixel(256, colorToleranceAsPercent);
            FindInComParam findInComParam = new FindInComParam(screenBytes, inputStartX, inputStartY, inputWidth,
                    inputHeight, templateImagePath, templateStartX, templateStartY, w, h, colorToleranceAsPixel, xStep,
                    yStep);
            FindInResult findInResult = new FindInComMatch(findInComParam).match();
            if (findInResult.isMatched()) {
                return findInResult.getFind();
            }
            return null;
        } catch (Exception e) {
            throw ExceptionHandler.handle(e);
        }
    }

    /**
     * install a apk
     * 
     * @param apkPath
     *            path of apk
     * @throws MException
     * @return true while install success
     */
    public boolean install(String apkPath) throws MException {
        ApplicationOperationReq operationReq = new ApplicationOperationReq();
        operationReq.setAction(MobileConstants.APP_OPERATION_ACTION_INSTALL);
        operationReq.setPath(apkPath);
        ApplicationOperationResp operationResp = (ApplicationOperationResp) engineClient
                .sendAndResponseOne(operationReq);
        if (operationResp.getStatusValue() != MobileConstants.STATUS_OK) {
            throw new MException("install app " + apkPath + " fail");
        }
        return true;
    }

    /**
     * uninstall a apk
     * 
     * @param packageName
     *            packagename of the apk
     * @throws MException
     * @return true while install success
     */
    public boolean uninstall(String packageName) throws MException {
        ApplicationOperationReq operationReq = new ApplicationOperationReq();
        operationReq.setAction(MobileConstants.APP_OPERATION_ACTION_UNINSTALL);
        operationReq.setPackageName(packageName);
        ApplicationOperationResp operationResp = (ApplicationOperationResp) engineClient
                .sendAndResponseOne(operationReq);
        if (operationResp.getStatusValue() != MobileConstants.STATUS_OK) {
            throw new MException("uninstall app " + packageName + " fail");
        }
        return true;
    }

    /**
     * run a app
     * 
     * @param packageName
     *            packagename of the app
     * @throws MException
     * @return true while install success
     */
    public boolean runApp(String packageName) throws MException {
        ApplicationOperationReq operationReq = new ApplicationOperationReq();
        operationReq.setAction(MobileConstants.APP_OPERATION_ACTION_START);
        operationReq.setPackageName(packageName);
        ApplicationOperationResp operationResp = (ApplicationOperationResp) engineClient
                .sendAndResponseOne(operationReq);
        if (operationResp.getStatusValue() != MobileConstants.STATUS_OK) {
            throw new MException("run app " + packageName + " fail");
        }
        return true;
    }

    /**
     * stop a app
     * 
     * @param packageName
     *            packagename of the app
     * @throws MException
     */
    public boolean stopApp(String packageName) throws MException {
        ApplicationOperationReq operationReq = new ApplicationOperationReq();
        operationReq.setAction(MobileConstants.APP_OPERATION_ACTION_STOP);
        operationReq.setPackageName(packageName);
        ApplicationOperationResp operationResp = (ApplicationOperationResp) engineClient
                .sendAndResponseOne(operationReq);
        if (operationResp.getStatusValue() != MobileConstants.STATUS_OK) {
            throw new MException("stop app " + packageName + " fail");
        }
        return true;
    }

    /**
     * input the text via AspInputService
     * 
     * @param text
     * @throws MException
     */
    public void input(String text) throws MException {
        InputOperationReq req = new InputOperationReq();
        req.setCommand(text);
        InputOperationResp operationResp = (InputOperationResp) engineClient.sendAndResponseOne(req);
        if (operationResp.getStatusValue() != MobileConstants.STATUS_OK) {
            throw new MException("run input fail");
        }
    }

    /**
     * send a message
     * 
     * @param receivers
     *            receivers split by ";"
     * @param content
     * @throws MException
     * @return true while install success
     */
    public boolean sendMessage(String receivers, String content) throws MException {
        AddMessageReq engineMsg = new AddMessageReq();
        ArrayList<MobileMessageInfo> mbmsginfolist = new ArrayList<MobileMessageInfo>();
        MobileMessageInfo mbmsginfo = new MobileMessageInfo();
        mbmsginfo.setBody(content);
        mbmsginfo.setDestAddress(receivers);
        mbmsginfo.setType(MobileConstants.MESSAGE_TYPE_SMS);
        mbmsginfolist.add(mbmsginfo);
        engineMsg.setMessageInfo(mbmsginfolist);

        AddMessageResp msgmobile = (AddMessageResp) engineClient.sendAndResponseOne(engineMsg);
        if (msgmobile.getStatusValue() <= MobileConstants.STATUS_OK) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#deleteAllMessage()
     */
    public void deleteAllMessage() throws MException {
        final SMSOperationReq req = new SMSOperationReq();
        req.setAction(MobileConstants.SMS_OPERATION_ACTION_DELETE);
        req.setFolderName(MobileConstants.FOLDNAME_ALL);
        SMSOperationResp msgmobile = (SMSOperationResp) engineClient.sendAndResponseOne(req);
        if (msgmobile.getStatusValue() != MobileConstants.STATUS_OK) {
            throw new MException("run deleteAllMessage fail");
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.environment.IDeviceEntity#setAllMessageReaded()
     */
    public void setAllMessageReaded() throws MException {
        final SMSOperationReq req = new SMSOperationReq();
        req.setAction(MobileConstants.SMS_OPERATION_ACTION_UPDATE);
        req.setIsRead(MobileConstants.FLAG_READED);

        SMSOperationResp resp = (SMSOperationResp) engineClient.sendAndResponseOne(req);
        if (resp.getStatusValue() != MobileConstants.STATUS_OK) {
            throw new MException("run allMessageReaded fail");
        }
    }

    /**
     * query sms
     * 
     * @param receivers
     *            receivers split by ";"
     * @param content
     * @throws MException
     * @return true while install success
     * @throws ToolException
     */
    public List<ContentValues> getMessage(ContentValues params) throws MException, ToolException {
        List<ContentValues> contentValues = new ArrayList<ContentValues>();
        MessageBoxQueryReq req = new MessageBoxQueryReq();
        if (params.containsKey(CommandConstants.KEY_GETMESSAGE_ID)) {
            req.setID(params.getAsString(CommandConstants.KEY_GETMESSAGE_ID));
        }
        if (params.containsKey(CommandConstants.KEY_GETMESSAGE_TYPE)) {
            req.setType(params.getAsByte(CommandConstants.KEY_GETMESSAGE_TYPE));
        }
        if (params.containsKey(CommandConstants.KEY_GETMESSAGE_ISREAD)) {
            req.setIsRead(params.getAsByte(CommandConstants.KEY_GETMESSAGE_ISREAD));
        }
        if (params.containsKey(CommandConstants.KEY_GETMESSAGE_PRIORITY)) {
            req.setPriority(params.getAsByte(CommandConstants.KEY_GETMESSAGE_PRIORITY));
        }
        if (params.containsKey(CommandConstants.KEY_GETMESSAGE_DESTADDRESSES)) {
            req.setDestAddress(params.getAsString(CommandConstants.KEY_GETMESSAGE_DESTADDRESSES));
        }
        if (params.containsKey(CommandConstants.KEY_GETMESSAGE_SUBJECT)) {
            req.setSubject(params.getAsString(CommandConstants.KEY_GETMESSAGE_SUBJECT));
        }
        if (params.containsKey(CommandConstants.KEY_GETMESSAGE_STARTTIME)) {
            req.setStartTime(params.getAsString(CommandConstants.KEY_GETMESSAGE_STARTTIME));
        }
        if (params.containsKey(CommandConstants.KEY_GETMESSAGE_ENDTIME)) {
            req.setEndTime(params.getAsString(CommandConstants.KEY_GETMESSAGE_ENDTIME));
        }
        if (params.containsKey(CommandConstants.KEY_GETMESSAGE_FOLDERNAME)) {
            req.setFolderName(params.getAsString(CommandConstants.KEY_GETMESSAGE_FOLDERNAME));
        }
        if (params.containsKey(CommandConstants.KEY_GETMESSAGE_STARTINDEX)) {
            req.setStartIndex(params.getAsInteger(CommandConstants.KEY_GETMESSAGE_STARTINDEX));
        }
        if (params.containsKey(CommandConstants.KEY_GETMESSAGE_COUNT)) {
            req.setCount(params.getAsInteger(CommandConstants.KEY_GETMESSAGE_COUNT));
        }
        List<MobileMsgBase> mobileMsgBases = engineClient.sendAndResponse(req);
        if (null != mobileMsgBases) {
            MessageBoxQueryResp resp = null;
            ContentValues values = null;
            for (MobileMsgBase msgBase : mobileMsgBases) {
                resp = (MessageBoxQueryResp) msgBase;
                for (MobileMessageInfo messageInfo : resp.getMessageInfo()) {
                    values = new ContentValues();
                    // TODO 有些属性未赋值
                    values.put(CommandConstants.KEY_GETMESSAGE_ID, messageInfo.getID());
                    values.put(CommandConstants.KEY_GETMESSAGE_TYPE, messageInfo.getType());
                    values.put(CommandConstants.KEY_GETMESSAGE_TIME, messageInfo.getTime());
                    values.put(CommandConstants.KEY_GETMESSAGE_ISREAD, messageInfo.getIsReadValue());
                    values.put(CommandConstants.KEY_GETMESSAGE_PRIORITY, messageInfo.getPriorityValue());
                    values.put(CommandConstants.KEY_GETMESSAGE_VALIDITYPERIOD, messageInfo.getValidityPeriodValue());
                    values.put(CommandConstants.KEY_GETMESSAGE_SOURCEADDRESS, messageInfo.getSourceAddress());
                    values.put(CommandConstants.KEY_GETMESSAGE_CALLBACKNUMBER, messageInfo.getCallbackNumber());
                    values.put(CommandConstants.KEY_GETMESSAGE_SUBJECT, messageInfo.getSubject());
                    values.put(CommandConstants.KEY_GETMESSAGE_BODY, messageInfo.getBody());
                    values.put(CommandConstants.KEY_GETMESSAGE_HASATTACHMENT, messageInfo.getHasAttachmentValue());
                    contentValues.add(values);
                }
            }
        }
        return contentValues;
    }

    /**
     * open url in browser
     * 
     * @param url
     * @return
     * @throws MException
     */
    public boolean wapOpen(String url) throws MException {
        return wapOpen(url, MobileConstants.WAP_OPERATION_ACTION_START);
    }

    /**
     * open url in browser
     * 
     * @param url
     * @return
     * @throws MException
     */
    public boolean wapOpen(String url, boolean widgetTestEnable) throws MException {
        if (widgetTestEnable) {
            return wapOpen(url, MobileConstants.WAP_OPERATION_ACTION_WAP_DRIVER);
        }
        return wapOpen(url);
    }

    /**
     * open url in browser
     * 
     * @param url
     * @param action
     * @return
     * @throws MException
     */
    public boolean wapOpen(String url, byte action) throws MException {
        WapOperationReq engineMsg = new WapOperationReq();
        engineMsg.setAction(action);
        engineMsg.setURL(url);
        WapOperationResp wapOperationResp = (WapOperationResp) engineClient.sendAndResponseOne(engineMsg);
        if (wapOperationResp.getStatusValue() == MobileConstants.STATUS_OK) {
            return true;
        }
        return false;
    }

    /**
     * getting the mobile screen image
     * 
     * @throws MException
     */
    public byte[] getScreen() throws MException {
        return getScreen(0, 0, 0, 0);
    }

    public void resetCertificateParams() {
        Log.d(TAG, "resetCertificateParams");
        this.findInParam = null;
        this.findInResult = null;

        this.verifyParams = null;
        this.verifyResult = null;

        this.ocrScreenParam = null;
        this.ocrRectParam = null;
        this.ocrScreenMatchParam = null;
        this.ocrRectMatchParam = null;
        this.ocrResourceParam = null;
        this.ocrScreenResult = null;
        this.ocrRectResult = null;
        this.ocrScreenMatchResult = null;
        this.ocrRectMatchResult = null;
        this.ocrResourceResult = null;

        this.lastCheckParam = null;
        this.lastcCheckResult = null;
        this.certificate = null;

    }

    /**
     * getting the mobile screen image
     * 
     * @param xStart
     * @param yStart
     * @param width
     * @param height
     * @return {@link ImageMessage}
     * @throws MException
     */
    public byte[] getScreen(int xStart, int yStart, int width, int height) throws MException {
        final GrabImageRequest request = new GrabImageRequest(xStart, yStart, width, height);
        final List<APSMessage> responses = engineClient.sendAndListResponse(request);
        if (responses.size() > 0) {
            final ByteArrayOutputStream out = new ByteArrayOutputStream();
            GrabImageResponse response = null;
            try {
                for (APSMessage message : responses) {
                    response = (GrabImageResponse) message;
                    if (response.getErrcode() != GrabImageResponse.STATUS_FAIL) {
                        out.write(response.getImageMessage().getImageData());
                    } else {
                        // if the responses message have one package error ,
                        // reutrn null;
                        return null;
                    }
                }
                out.flush();
                out.close();
                return out.toByteArray();
            } catch (IOException e) {
                ExceptionHandler.handle(e);
            }
        }
        return null;
    }

    /**
     * save verifyCertificate
     * 
     */
    public void saveVerifyCertificate() throws MException {
        // if (null != verifyScreen) {
        // try {
        // final int inputStartX = verifyScreen.getAsInteger(CommandConstants.KEY_VERIFYSCREEN_INPUTSTARTX);
        // final int inputStartY = verifyScreen.getAsInteger(CommandConstants.KEY_VERIFYSCREEN_INPUTSTARTY);
        // final String templateImagePath = verifyScreen
        // .getAsString(CommandConstants.KEY_VERIFYSCREEN_TEMPLATEIMAGEPATH);
        // // final int templateStartX =
        // // verifyScreen.getAsInteger(CommandConstants.KEY_VERIFYSCREEN_TEMPLATESTARTX);
        // // final int templateStartY =
        // // verifyScreen.getAsInteger(CommandConstants.KEY_VERIFYSCREEN_TEMPLATESTARTY);
        // final int w = verifyScreen.getAsInteger(CommandConstants.KEY_VERIFYSCREEN_W);
        // final int h = verifyScreen.getAsInteger(CommandConstants.KEY_VERIFYSCREEN_H);
        // final int colorToleranceAsPercent = verifyScreen
        // .getAsInteger(CommandConstants.KEY_VERIFYSCREEN_COLORTOLERANCEASPERCENT);
        // // final int pixelTolerance =
        // // verifyScreen.getAsInteger(CommandConstants.KEY_VERIFYSCREEN_PIXELTOLERANCE);
        // final byte[] screenBytes = verifyScreen.getAsByteArray(CommandConstants.KEY_SCREEN_BYTE);
        // final byte[] referentImage = FileUtil.readFile(templateImagePath);
        //
        // int colorToleranceAsPixel = calcPixel(256, colorToleranceAsPercent);
        //
        // certificate = ImageUtil.createVerifyWarrant(referentImage, screenBytes, inputStartX, inputStartY, w, h,
        // colorToleranceAsPixel);
        //
        // // delete verifyScreen
        // verifyScreen.clear();
        // verifyScreen = null;
        // } catch (Exception e) {
        // throw new MException("saveVerifyCertificate error", e);
        // }
        //
        // }
        certificate = CertificateFacotry.generateCertificate(verifyParams, verifyResult);
    }

    /**
     * save findinCertificate
     * 
     * @return
     */
    public void saveFindinCertificate() throws MException {
        // try {
        // if (null != findInScreen) {
        // final String templateImagePath = findInScreen
        // .getAsString(CommandConstants.KEY_FINDINSCREEN_TEMPLATEIMAGEPATH);
        // final int templateStartX = findInScreen.getAsInteger(CommandConstants.KEY_FINDINSCREEN_TEMPLATESTARTX);
        // final int templateStartY = findInScreen.getAsInteger(CommandConstants.KEY_FINDINSCREEN_TEMPLATESTARTY);
        // final int w = findInScreen.getAsInteger(CommandConstants.KEY_FINDINSCREEN_W);
        // final int h = findInScreen.getAsInteger(CommandConstants.KEY_FINDINSCREEN_H);
        // final int colorToleranceAsPercent = findInScreen
        // .getAsInteger(CommandConstants.KEY_FINDINSCREEN_COLORTOLERANCEASPERCENT);
        // final byte[] screenBytes = findInScreen.getAsByteArray(CommandConstants.KEY_SCREEN_BYTE);
        // final byte[] referentImage = FileUtil.readFile(templateImagePath);
        // certificate = ImageUtil.createFindWarrant(referentImage, screenBytes, templateStartX, templateStartY,
        // w, h, colorToleranceAsPercent);
        // findInScreen.clear();
        // findInScreen = null;
        // }
        // } catch (Exception e) {
        // throw new MException("saveVerifyCertificate error", e);
        // }
        certificate = CertificateFacotry.generateCertificate(findInParam, findInResult);
    }

    /**
     * save ocr certificate
     * 
     * @param commandType
     * @param expect
     * @param reality
     * @throws MException
     */
    public void saveOcrCertificate(int commandType, String expect, String reality) throws MException {
        // int startX = 0, startY = 0, width = 0, height = 0;
        // byte[] currentImage = null;
        // switch (commandType) {
        // case CommandConstants.TYPE_SAVEOCRSCREENCERTIFICATE:
        // currentImage = ocrScreen.getAsByteArray(CommandConstants.KEY_SCREEN_BYTE);
        // break;
        // case CommandConstants.TYPE_SAVEOCRRECTCERTIFICATE:
        // currentImage = ocrRectScreen.getAsByteArray(CommandConstants.KEY_SCREEN_BYTE);
        // startX = ocrRectScreen.getAsInteger(CommandConstants.KEY_OCRRECT_STARTX);
        // startY = ocrRectScreen.getAsInteger(CommandConstants.KEY_OCRRECT_STARTY);
        // width = ocrRectScreen.getAsInteger(CommandConstants.KEY_OCRRECT_WIDTH);
        // height = ocrRectScreen.getAsInteger(CommandConstants.KEY_OCRRECT_HEIGHT);
        // break;
        // }
        // try {
        // certificate = ImageUtil.createOcrWarrant(currentImage, startX, startY, width, height, expect, reality);
        // } catch (Exception e) {
        // throw new MException("saveOcrCertificate error", e);
        // }
        CheckParam param = null;
        CheckResult result = null;
        switch (commandType) {
        case CommandConstants.TYPE_SAVEOCRSCREENCERTIFICATE:
            param = ocrScreenParam;
            ocrScreenResult = new OCRResult(false, expect, reality);
            result = ocrScreenResult;
            break;
        case CommandConstants.TYPE_SAVEOCRRECTCERTIFICATE:
            param = ocrRectParam;
            ocrRectResult = new OCRResult(false, expect, reality);
            result = ocrRectResult;
            break;
        case CommandConstants.TYPE_SAVEOCRRECTMATCHCERTIFICATE:
            param = ocrRectMatchParam;
            result = ocrRectMatchResult;
            break;
        case CommandConstants.TYPE_SAVEOCRSCREENMATCHCERTIFICATE:
            param = ocrScreenMatchParam;
            result = ocrScreenMatchResult;
            break;
        }
        try {
            certificate = CertificateFacotry.generateCertificate(param, result);
        } catch (Exception e) {
            throw new MException("saveOcrCertificate error", e);
        }
    }

    /**
     * start grab network package
     * 
     * @return grab file path
     * @throws MException
     */
    public String startGrabTcpPackage(String path) throws MException {
        final GrabNetworkPackageReq req = new GrabNetworkPackageReq();
        req.setPath(path);

        final GrabNetworkPackageResp response = (GrabNetworkPackageResp) engineClient.sendAndResponseOne(req);
        if (response.getStatusValue() == MobileConstants.STATUS_OK) {
            return response.getFile();
        }
        return null;
    }

    /**
     * stop grab network package
     * 
     * @return grab file path
     * @throws MException
     */
    public String stopGrabTcpPackage() throws MException {
        final GrabNetworkPackageReq req = new GrabNetworkPackageReq();
        req.setAction(MobileConstants.GRAB_NETWORKPACKAGE_ACTION_STOP);

        final GrabNetworkPackageResp response = (GrabNetworkPackageResp) engineClient.sendAndResponseOne(req);
        if (response.getStatusValue() == MobileConstants.STATUS_OK) {
            return response.getFile();
        }
        return null;

    }

    public String saveScreen(String path, String name) throws MException {
        if (path == null || "".equals(path.trim()))
            throw new MException("the param path is not null!");
        if (name == null || "".equals(name.trim()))
            throw new MException("the param name is not null!");
        final String filepath = path + File.separator + name;
        FileUtil.writeFile(filepath, getScreen());
        return filepath;

    }

    public List<ContentValues> findWidget(String key, String value) throws MException {
        // TODO Auto-generated method stub
        return null;
    }

    public int countWidget(String key, String value) throws MException {
        // TODO Auto-generated method stub
        return 0;
    }

    public boolean clickWidget(String key, String value) throws MException {
        // TODO Auto-generated method stub
        return false;
    }

    public String getWidgetText(String key, String value) throws MException {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * find web widget
     * 
     * @param key
     * @param value
     * @return
     * @throws MException
     */
    public List<ContentValues> findWebWidget(String key, String value) throws MException {
        final List<ContentValues> contentValues = new ArrayList<ContentValues>();

        final WapOperationReq req = new WapOperationReq();
        req.setAction(MobileConstants.WAP_OPERATION_ACTION_FIND);
        req.setKey(key);
        req.setValue(value);
        List<MobileMsgBase> list = engineClient.sendAndResponse(req);

        // WapOperationResp resp = null;
        ContentValues values = null;
        for (MobileMsgBase base : list) {
            if (base instanceof WapOperationResp) {
                // resp = (WapOperationResp) base;
                values = new ContentValues();
                contentValues.add(values);
            }
        }
        return contentValues;
    }

    /**
     * find web widget count
     * 
     * @param key
     * @param value
     * @return
     * @throws MException
     */
    public int countWebWidget(String key, String value) throws MException {
        final List<ContentValues> list = findWebWidget(key, value);
        return list.size();
    }

    /**
     * click find widget
     * 
     * @param key
     * @param value
     * @return
     * @throws MException
     */
    public boolean clickWebWidget(String key, String value) throws MException {
        final WapOperationReq req = new WapOperationReq();
        req.setAction(MobileConstants.WAP_OPERATION_ACTION_CLICK);
        req.setKey(MobileConstants.WAP_NAME);
        req.setValue("q");
        final WapOperationResp resp = (WapOperationResp) engineClient.sendAndResponseOne(req);
        if (resp.getStatusValue() == MobileConstants.STATUS_OK)
            return true;
        return false;
    }

    public String getWebWidgetText(String key, String value) throws MException {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * closed browser
     */
    public void wapClose() throws MException {
        final WapOperationReq req = new WapOperationReq();
        req.setAction(MobileConstants.WAP_OPERATION_ACTION_CLOSE_DRIVER);
        final WapOperationResp resp = (WapOperationResp) engineClient.sendAndResponseOne(req);
        if (resp.getStatusValue() != MobileConstants.STATUS_OK) {
            throw new MException("close wap fail");
        }
    }

    /**
     * 获取文件大小
     * 
     * @param path
     *            文件所在的文件夹
     * @param fileName
     *            文件名
     * @return 文件大小
     * @throws MException
     */
    public long getFileSize(String path, String fileName) throws MException {
        final FileOperationRequest request = new FileOperationRequest();
        request.setAction(MobileConstants.FILE_OPERATION_ACTION_GETSIZE);
        request.setPath(path);
        request.setFilename(fileName);
        final FileOperationResponse response = (FileOperationResponse) engineClient.sendAndResponse(request);
        checkingAPSMessageResponse(response);
        return response.getFileSize();
    }

    /**
     * 删除一个文件
     * 
     * @param path
     *            文件所在的文件夹
     * @param fileName
     *            文件名
     * @return true 成功
     * @throws MException
     */
    public boolean deleteFile(String path, String fileName) throws MException {
        final FileOperationRequest request = new FileOperationRequest();
        request.setPath(path);
        request.setFilename(fileName);
        final FileOperationResponse response = (FileOperationResponse) engineClient.sendAndResponse(request);
        checkingAPSMessageResponse(response);
        return true;
    }

    public boolean screenMutilDown(int[]... xys) throws MException {
        if (null != xys && xys.length < 2)
            throw new MException("the points is null or point length < 2");
        final MutilTouchDownRequest request = new MutilTouchDownRequest();
        request.setPointOne(xys[0]);
        request.setPointTwo(xys[1]);
        if (xys.length >= 3)
            request.setPointThree(xys[2]);
        final MutilTouchDownResponse response = (MutilTouchDownResponse) engineClient.sendAndResponse(request);
        checkingAPSMessageResponse(response);
        return true;
    }

    public boolean screenMutilMove(int[]... xys) throws MException {
        if (null != xys && xys.length < 2)
            throw new MException("the points is null or point length < 2");
        final MutilTouchMoveRequest request = new MutilTouchMoveRequest();
        request.setPointOne(xys[0]);
        request.setPointTwo(xys[1]);
        if (xys.length >= 3)
            request.setPointThree(xys[2]);
        final MutilTouchMoveResponse response = (MutilTouchMoveResponse) engineClient.sendAndResponse(request);
        checkingAPSMessageResponse(response);
        return true;
    }

    public boolean screenMutilUp(int[]... xys) throws MException {
        if (null != xys && xys.length < 2)
            throw new MException("the points is null or point length < 2");
        final MutilTouchUpRequest request = new MutilTouchUpRequest();
        request.setPointOne(xys[0]);
        request.setPointTwo(xys[1]);
        if (xys.length >= 3)
            request.setPointThree(xys[2]);
        final MutilTouchUpResponse response = (MutilTouchUpResponse) engineClient.sendAndResponse(request);
        checkingAPSMessageResponse(response);
        return true;
    }

    public boolean screenZoom(int sx, int sy, int sx1, int sy1, int sx2, int sy2, int ex, int ey, int ex1, int ey1,
            int ex2, int ey2) throws MException {
        if (sx < 0 || sy < 0 || sx1 < 0 || sy1 < 0 || ex < 0 || ey < 0 || ex1 < 0 || ey1 < 0) {
            return false;
        }

        if (sx2 < 0 || sy2 < 0 || ex2 < 0 || ey2 < 0) {
            screenMutilDown(new int[] { sx, sy }, new int[] { sx1, sy1 });
            screenMutilMove(new int[] { ex, sy }, new int[] { ex1, ey1 });
            screenMutilUp(new int[] { ex, sy }, new int[] { ex1, ey1 });
        } else {
            screenMutilDown(new int[] { sx, sy }, new int[] { sx1, sy1 }, new int[] { sx2, sy2 });
            screenMutilMove(new int[] { ex, sy }, new int[] { ex1, ey1 }, new int[] { ex2, ey2 });
            screenMutilUp(new int[] { ex, sy }, new int[] { ex1, ey1 }, new int[] { ex2, ey2 });
        }
        return true;
    }

    public boolean screenShrink(int sx, int sy, int sx1, int sy1, int sx2, int sy2, int ex, int ey, int ex1, int ey1,
            int ex2, int ey2) throws MException {
        return screenZoom(sx, sy, sx1, sy1, sx2, sy2, ex, ey, ex1, ey1, ex2, ey2);
    }

    /**
     * check the response message is null or status is fail
     * 
     * @param response
     * @throws MException
     */
    private static void checkingAPSMessageResponse(CommonResponse response) throws MException {
        if (null == response) {
            throw new MException("Response message is null");
        } else if (response.getErrcode() != CommonResponse.STATUS_OK) {
            throw new MException("Response success but there is an error, an error message is "
                    + response.getErrormsg());
        }

    }

    public boolean saveImageToCertificate(String path) throws MException, IOException {
        this.certificate = FileUtil.readFile(path);
        return true;
    }

    /**
     * calc point step
     * 
     * @param start
     *            开始坐标
     * @param end
     *            结束坐标
     * @param step
     *            一步移动多少
     * @return
     */
    public static int[] calcPoint(int start, int end, int step) {
        final int diff = Math.abs(start - end);
        int sum = diff / step; // 计算要移动多少次
        int[] points = new int[sum];
        final boolean negative = (start - end) < 0 ? true : false;
        for (int i = 1; i <= sum; i++) {
            if (negative)
                points[i - 1] = step * i;
            else
                points[i - 1] = -(step * i);
        }
        return points;
    }

    /**
     * calc point step
     * 
     * @param start
     *            开始坐标
     * @param end
     *            结束坐标
     * @param sum
     *            多少步移动完成
     * @return
     */
    public static int[] calcPoint2(int start, int end, int sum) {
        final int diff = Math.abs(start - end);
        final int step = diff / sum;
        if (step == 0) {
            final int[] points = new int[sum];
            return points;
        }
        return calcPoint(start, end, step);
    }

    /**
     * @param direction
     * @param length
     * @throws MException
     */
    public void deleteInput(int left, int right) throws MException {
        InputOperationReq req = new InputOperationReq();
        req.setAction(MobileConstants.INPUT_TYPE_DEL);
        req.setCommand(left + "|" + right);
        InputOperationResp operationResp = (InputOperationResp) engineClient.sendAndResponseOne(req);
        if (operationResp.getStatusValue() != MobileConstants.STATUS_OK) {
            throw new MException("run deleteInput fail");
        }

    }
}
