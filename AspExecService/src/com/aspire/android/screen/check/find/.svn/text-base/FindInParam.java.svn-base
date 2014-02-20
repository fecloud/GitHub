/**
 * @(#) FindParam.java Created on 2012-8-2
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.screen.check.find;

import com.aspire.android.screen.check.CheckParam;

/**
 * The class <code>FindParam</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class FindInParam extends CheckParam {

    /**
     * 模板路径
     */
    private String templateImagePath;

    /**
     * 模板x偏移
     */
    private int templateStartX;
    /**
     * 模板y偏移
     */
    private int templateStartY;

    /**
     * 模板宽
     */
    private int templateWidth;

    /**
     * 模板高
     */
    private int templateHeight;

    /**
     * 颜色容忍度百分比 ，取值：0到100
     */
    private int colorToleranceAsPercent;

    /**
     * Constructor
     */
    public FindInParam() {
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
     * @param templateWidth
     * @param templateHeight
     * @param colorToleranceAsPercent
     */
    public FindInParam(byte[] screenBytes, int startX, int startY, int width, int height, String templateImagePath,
            int templateStartX, int templateStartY, int templateWidth, int templateHeight, int colorToleranceAsPercent) {
        super(screenBytes, startX, startY, width, height);
        this.templateImagePath = templateImagePath;
        this.templateStartX = templateStartX;
        this.templateStartY = templateStartY;
        this.templateWidth = templateWidth;
        this.templateHeight = templateHeight;
        this.colorToleranceAsPercent = colorToleranceAsPercent;
    }

    public String getTemplateImagePath() {
        return templateImagePath;
    }

    public void setTemplateImagePath(String templateImagePath) {
        this.templateImagePath = templateImagePath;
    }

    public int getTemplateStartX() {
        return templateStartX;
    }

    public void setTemplateStartX(int templateStartX) {
        this.templateStartX = templateStartX;
    }

    public int getTemplateStartY() {
        return templateStartY;
    }

    public void setTemplateStartY(int templateStartY) {
        this.templateStartY = templateStartY;
    }

    public int getColorToleranceAsPercent() {
        return colorToleranceAsPercent;
    }

    public void setColorToleranceAsPercent(int colorToleranceAsPercent) {
        this.colorToleranceAsPercent = colorToleranceAsPercent;
    }

    public int getTemplateWidth() {
        return templateWidth;
    }

    public void setTemplateWidth(int templateWidth) {
        this.templateWidth = templateWidth;
    }

    public int getTemplateHeight() {
        return templateHeight;
    }

    public void setTemplateHeight(int templateHeight) {
        this.templateHeight = templateHeight;
    }

}
