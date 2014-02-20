/**
 * @(#) OCRMatch.java Created on 2012-8-2
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server.restore.matcher.ocr;

import org.apache.log4j.Logger;

import com.inspurworld.server.ocr.OCRFind;
import com.inspurworld.server.restore.matcher.CheckMatch;

/**
 * The class <code>OCRMatch</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class OCRMatch extends CheckMatch<OCRParam, OCRResult> {

    private Logger logger = Logger.getLogger(getClass());

    private OCRFind find;

    private String[] ocrTokens; // 期待结果匹配集

    private boolean isAnd; // 是否是匹配期待结果中的一个

    /**
     * Constructor
     * 
     * @param param
     */
    public OCRMatch(OCRParam param) {
        super(param);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.screen.check.CheckMatch#match(java.lang.Object)
     */
    @Override
    public OCRResult match(OCRParam param) throws Exception {
        if (null == param)
            throw new Exception("the params not is null");

        // 如果没设期待结果,找了也白找
        if (param.getExpect() != null) {
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
        return new OCRResult();
    }

    /**
     * invoke ocr ,result find String
     * 
     * @return
     * @throws MException
     */
    private String ocrFind() throws Exception {
        if (null == find)
            find = new OCRFind();
        final OCRParam ocrParam = this.param;
        find.setInputImagePath(ocrParam.getInputPath());
        find.setLang(ocrParam.getLang());
        String ocrResult = find.ocr();
        logger.debug("ocrFind ocrResult:" + ocrResult);
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
