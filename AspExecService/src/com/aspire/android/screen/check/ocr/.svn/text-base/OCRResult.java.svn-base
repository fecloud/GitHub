/**
 * @(#) OCRResult.java Created on 2012-8-2
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.screen.check.ocr;

import com.aspire.android.screen.check.CheckResult;

/**
 * The class <code>OCRResult</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class OCRResult extends CheckResult {

    /**
     * 期待结果
     */
    private String expect;

    /**
     * 实际结果
     */
    private String reality;

    /**
     * Constructor
     */
    public OCRResult() {
    }

    /**
     * Constructor
     * 
     * @param isMatched
     * @param expect
     * @param reality
     */
    public OCRResult(boolean isMatched, String expect, String reality) {
        super(isMatched);
        this.expect = expect;
        this.reality = reality;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.test.screen.check.CheckResult#generateWarrantText()
     */
    @Override
    public String generateWarrantText() {
        // TODO Auto-generated method stub
        return null;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public String getReality() {
        return reality;
    }

    public void setReality(String reality) {
        this.reality = reality;
    }

}
