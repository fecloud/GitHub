/**
 * @(#) FindInComParam.java Created on 2012-8-28
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.screen.check.find.com;

import com.aspire.android.screen.check.find.FindInParam;

/**
 * The class <code>FindInComParam</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class FindInComParam extends FindInParam {

    /**
     * 匹配不到,每次x步进多少
     */
    protected int xStep;

    /**
     * 匹配不到,每次y步进多少
     */
    protected int yStep;

    public FindInComParam() {
    }

    public FindInComParam(byte[] screenBytes, int startX, int startY, int width, int height, String templateImagePath,
            int templateStartX, int templateStartY, int templateWidth, int templateHeight, int colorToleranceAsPercent,
            int xStep, int yStep) {
        super(screenBytes, startX, startY, width, height, templateImagePath, templateStartX, templateStartY,
                templateWidth, templateHeight, colorToleranceAsPercent);
        this.xStep = xStep;
        this.yStep = yStep;
    }

    public int getxStep() {
        return xStep;
    }

    public void setxStep(int xStep) {
        this.xStep = xStep;
    }

    public int getyStep() {
        return yStep;
    }

    public void setyStep(int yStep) {
        this.yStep = yStep;
    }

}
