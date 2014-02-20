/**
 * @(#) CheckResult.java Created on 2012-8-2
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server.restore.matcher;

/**
 * The class <code>CheckResult</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public abstract class CheckResult {

    /**
     * 是否匹配
     */
    private boolean isMatched;

    private String ocrWarrant;

    /**
     * Constructor
     */
    public CheckResult() {
    }

    /**
     * Constructor
     * 
     * @param isMatched
     * @param ocrWarrant
     */
    public CheckResult(boolean isMatched) {
        super();
        this.isMatched = isMatched;
        // this.ocrWarrant = ocrWarrant;
    }

    public abstract String generateWarrantText();

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean isMatched) {
        this.isMatched = isMatched;
    }

    public String getOcrWarrant() {
        return ocrWarrant;
    }

    protected void setOcrWarrant(String ocrWarrant) {
        this.ocrWarrant = ocrWarrant;
    }

}
