/**
 * @(#) OCRParam.java Created on 2012-8-2
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.screen.check.ocr;

import com.aspire.android.screen.check.CheckParam;
import com.aspire.android.test.testcase.AbstractTestCase;

/**
 * The class <code>OCRParam</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class OCRParam extends CheckParam {

    /**
     * 识别语言 0:数字 ; 1:英文 +数字; 2:简体中文 +数字; 3:繁体中文 +数字
     */
    private int lang;

    /**
     * 放大倍数（<= 0 或 = 1 表示无需缩放）
     */
    private double scale = 1;

    /**
     * 二值化阀值（图像的灰度）0到255，255为白色， 0为黑色 （< 0 或 > 255 表示无需二值化）
     */
    private int threshold = -1;

    /**
     * 期待结果
     */
    private String expect;

    /**
     * 是否去除结果中的空格
     */
    private boolean trim;

    /**
     * 是否去除结果中的换行符
     */
    private boolean join;

    /**
     * Constructor
     */
    public OCRParam() {
    }

    /**
     * Constructor
     * 
     * @param screenBytes
     * @param startX
     * @param startY
     * @param width
     * @param height
     * @param lang
     */
    public OCRParam(byte[] screenBytes, int startX, int startY, int width, int height, int lang, String expect) {
        super(screenBytes, startX, startY, width, height);
        this.lang = lang;
        this.expect = expect;
    }

    /**
     * Constructor
     * 
     * @param screenBytes
     * @param startX
     * @param startY
     * @param width
     * @param height
     * @param lang
     * @param scale
     * @param threshold
     */
    public OCRParam(byte[] screenBytes, int startX, int startY, int width, int height, int lang, double scale,
            int threshold, String expect) {
        super(screenBytes, startX, startY, width, height);
        this.lang = lang;
        this.scale = scale;
        this.threshold = threshold;
        this.expect = expect;
    }

    /**
     * Constructor
     * 
     * @param screenBytes
     * @param startX
     * @param startY
     * @param width
     * @param height
     * @param lang
     * @param scale
     * @param threshold
     * @param expect
     * @param trim
     * @param join
     */
    public OCRParam(byte[] screenBytes, int startX, int startY, int width, int height, int lang, double scale,
            int threshold, String expect, boolean trim, boolean join) {
        super(screenBytes, startX, startY, width, height);
        this.lang = lang;
        this.scale = scale;
        this.threshold = threshold;
        this.expect = expect;
        this.trim = trim;
        this.join = join;
    }

    public int getLang() {
        return lang;
    }

    public void setLang(int lang) {
        this.lang = lang;
    }

    public void setLang(String lang) {
        this.lang = parseLang(lang);
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect == null ? null : expect.trim();
    }

    public boolean isTrim() {
        return trim;
    }

    public void setTrim(boolean trim) {
        this.trim = trim;
    }

    public boolean isJoin() {
        return join;
    }

    public void setJoin(boolean join) {
        this.join = join;
    }

    public void setTrim(String doTrim) {
        this.trim = (doTrim != null && doTrim.length() > 0) ? Boolean.parseBoolean(doTrim) : false;
    }

    public void setJoin(String doJoin) {
        this.join = (doJoin != null && doJoin.length() > 0) ? Boolean.parseBoolean(doJoin) : false;
    }

    public static int parseLang(String lang) {
        if ("number".equalsIgnoreCase(lang)) {
            return AbstractTestCase.OCR_NUMBER;
        } else if ("english".equalsIgnoreCase(lang)) {
            return AbstractTestCase.OCR_ENGLISH;
        } else if ("simplipied_chinese".equalsIgnoreCase(lang)) {
            return AbstractTestCase.OCR_SIMPLIPIED_CHINESE;
        } else if ("traditional_chinese".equalsIgnoreCase(lang)) {
            return AbstractTestCase.OCR_TRADITIONAL_CHINESE;
        } else {
            throw new IllegalArgumentException("no found type:" + lang);
        }
    }
}
