/**
 * @(#) FindInResult.java Created on 2012-8-2
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.screen.check.find;

import com.aspire.android.screen.check.CheckResult;

/**
 * The class <code>FindInResult</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class FindInResult extends CheckResult {

    /**
     * 找到的位置
     */
    private int[] find;

    /**
     * Constructor
     */
    public FindInResult() {
    }

    /**
     * Constructor
     * 
     * @param isMatched
     * @param ocrWarrant
     * @param find
     */
    public FindInResult(boolean isMatched, int[] find) {
        super(isMatched);
        this.find = find;
    }

    public int[] getFind() {
        return find;
    }

    public void setFind(int[] find) {
        this.find = find;
    }

    /**
     * {@inheritDoc}
     * @see com.aspire.android.test.screen.check.CheckResult#generateWarrantText()
     */
    @Override
    public String generateWarrantText() {
        // TODO Auto-generated method stub
        return null;
    }

}
