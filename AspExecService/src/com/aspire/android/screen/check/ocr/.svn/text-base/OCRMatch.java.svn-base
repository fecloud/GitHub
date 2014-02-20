/**
 * @(#) OCRMatch.java Created on 2012-8-2
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.screen.check.ocr;

import android.os.RemoteException;
import android.util.Log;

import com.aspire.android.common.exception.MException;
import com.aspire.android.ocr.IOcrService;
import com.aspire.android.screen.check.CheckMatch;

/**
 * The class <code>OCRMatch</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class OCRMatch extends CheckMatch<OCRParam, OCRResult> {

    private static final String TAG = "OCRMatch";

    private IOcrService ocrService;

    private String[] ocrTokens; // 期待结果匹配集

    private boolean isAnd; // 是否是匹配期待结果中的一个

    /**
     * Constructor
     * 
     * @param param
     */
    public OCRMatch(IOcrService ocrService, OCRParam param) {
        super(param);
        this.ocrService = ocrService;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.screen.check.CheckMatch#match(java.lang.Object)
     */
    @Override
    public OCRResult match(OCRParam param) throws MException {
        if (null == param)
            throw new MException("the params not is null");

        String ocrStr = ocrFind(); // 查到的结果

        if (param.isTrim()) // 去除 查找结果的空格
            ocrStr = ocrStr.replaceAll(" ", "");

        if (param.isJoin()) // 去除 查找结果的换行符
            ocrStr = ocrStr.replaceAll("\r", "").replaceAll("\n", "");

        final String expect = param.getExpect(); // 期待结果
        isAnd = paserExpect(expect);
        
        final boolean isMatched = doMatch(ocrStr, ocrTokens, isAnd);
        final OCRResult result = new OCRResult(isMatched, expect, ocrStr);
        return result;
    }

    /**
     * invoke ocr ,result find String
     * 
     * @return
     * @throws MException
     */
    private String ocrFind() throws MException {
        if (null == ocrService)
            throw new MException("the ocrService not is null");
        String ocrResult = null;
        try {
            // 全屏查找x = 0 , y = 0 , width = 0 , -1 , height =0 , -1
            if (param.getStartX() == 0 && param.getStartY() == 0 && param.getWidth() <= 0 && param.getHeight() <= 0) {
                ocrResult = ocrService.ocrAll(param.getScreenBytes(), param.getLang(), param.getScale(),
                        param.getThreshold());
            } else {
                ocrResult = ocrService.ocrRect(param.getScreenBytes(), param.getLang(), param.getStartX(),
                        param.getStartY(), param.getWidth(), param.getHeight(), param.getScale(), param.getThreshold());
            }

        } catch (RemoteException e) {
            throw new MException("Ocr Service error ", e);
        }
        Log.d(TAG, "ocrFind ocrResult:" + ocrResult);
        return ocrResult;
    }

    /**
     * 匹配
     * 
     * @param ocrText
     * @param tokens
     * @param isAnd
     * @return
     */
    private static boolean doMatch(String ocrText, String[] tokens, boolean isAnd) {
        if (isAnd) {
            for (String token : tokens) {
                if (ocrText.contains(token)) {
                    continue;
                } else {
                    return false;
                }
            }
            return true;
        } else {

            for (String token : tokens) {
                if (ocrText.contains(token)) {
                    return true;
                } else {
                    continue;
                }
            }
            return false;
        }
    }

    /**
     * 解析期待结果字符串
     * 
     * @param expect
     *            期待结果字符串
     * @param ocrTokens
     * @return
     */
    private boolean paserExpect(String expect) {
        // 解析期待结果字符串
        if (expect.startsWith("AND{")) { // and
            ocrTokens = expect.substring(4, expect.length() - 1).split(",");
        } else if (expect.startsWith("OR{")) { // or
            ocrTokens = expect.substring(3, expect.length() - 1).split(",");
            return false;
        } else { // nothing
            ocrTokens = new String[] { expect };
        }
        return true;
    }
}
