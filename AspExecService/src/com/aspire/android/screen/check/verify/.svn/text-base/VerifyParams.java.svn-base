/**
 * @(#) VerifyParams.java Created on 2012-8-2
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.screen.check.verify;

import com.aspire.android.screen.check.find.FindInParam;

/**
 * The class <code>VerifyParams</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class VerifyParams extends FindInParam {

    /**
     * 像素容忍度，单位：像素个数
     */
    private int pixelTolerance;

    /**
     * Constructor
     */
    public VerifyParams() {
    }

    /**
     * Constructor
     * 
     * @param screenBytes
     * @param startX
     * @param startY
     * @param width
     * @param height
     * @param templateImagePath
     * @param templateStartX
     * @param templateStartY
     * @param colorToleranceAsPercent
     * @param pixelTolerance
     */
    public VerifyParams(byte[] screenBytes, int startX, int startY, int width, int height, String templateImagePath,
            int templateStartX, int templateStartY, int templateWidth, int templateHeight, int colorToleranceAsPercent,
            int pixelTolerance) {
        super(screenBytes, startX, startY, width, height, templateImagePath, templateStartX, templateStartY,
                templateWidth, templateHeight, colorToleranceAsPercent);
        this.pixelTolerance = pixelTolerance;
    }

    public int getPixelTolerance() {
        return pixelTolerance;
    }

    public void setPixelTolerance(int pixelTolerance) {
        this.pixelTolerance = pixelTolerance;
    }

}
