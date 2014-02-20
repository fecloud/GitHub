/**
 * @(#) VerifyParams.java Created on 2012-8-2
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server.restore.matcher.verify;

import com.inspurworld.server.restore.matcher.CheckParam;

/**
 * The class <code>VerifyParams</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class VerifyParam extends CheckParam {

    /**
     * 模板
     */
    private String templatePath;

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

    private String verifyPropertiesKey;

    public VerifyParam(String inputPath, String templatePath) {
        setInputPath(inputPath);
        setTemplatePath(templatePath);
        this.verifyPropertiesKey = templatePath.replace(".jpg", "");
        final String str = VerifyParamLoader.getInstance().find(verifyPropertiesKey);
        if (null != str) {
            final String[] strings = str.split(",");
            if (null != strings && strings.length > 0) {
                if (strings.length == 1) {
                    final int colorToleranceAsPercent = Integer.parseInt(strings[0]);
                    setColorToleranceAsPercent(colorToleranceAsPercent);
                } else if (strings.length == 9) {
                    setInputPath(inputPath);
                    setTemplatePath(templatePath);
                    int startX = Integer.parseInt(strings[0]);
                    int startY = Integer.parseInt(strings[1]);
                    int width = Integer.parseInt(strings[2]);
                    int height = Integer.parseInt(strings[3]);
                    int templateStartX = Integer.parseInt(strings[4]);
                    int templateStartY = Integer.parseInt(strings[5]);
                    int templateWidth = Integer.parseInt(strings[6]);
                    int templateHeight = Integer.parseInt(strings[7]);
                    int colorToleranceAsPercent = Integer.parseInt(strings[8]);
                    setStartX(startX);
                    setStartY(startY);
                    setWidth(width);
                    setHeight(height);
                    setTemplateStartX(templateStartX);
                    setTemplateStartY(templateStartY);
                    setTemplateWidth(templateWidth);
                    setTemplateHeight(templateHeight);
                    setColorToleranceAsPercent(colorToleranceAsPercent);
                }
            }
        }

    }

    public String getVerifyPropertiesKey() {
        return verifyPropertiesKey;
    }

    public void setVerifyPropertiesKey(String verifyPropertiesKey) {
        this.verifyPropertiesKey = verifyPropertiesKey;
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
    public VerifyParam(String inputPath, int startX, int startY, int width, int height, String templatePath,
            int templateStartX, int templateStartY, int templateWidth, int templateHeight, int colorToleranceAsPercent) {
        super(inputPath, startX, startY, width, height);
        this.templatePath = templatePath;
        this.templateStartX = templateStartX;
        this.templateStartY = templateStartY;
        this.templateWidth = templateWidth;
        this.templateHeight = templateHeight;
        this.colorToleranceAsPercent = colorToleranceAsPercent;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
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

    /**
     * 像素容忍度，单位：像素个数
     */
    private int pixelTolerance;

    /**
     * Constructor
     * 
     * public VerifyParams() { }
     */

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
     * 
     * */
    public VerifyParam(String nativePath, int startX, int startY, int width, int height, String templatePath,
            int templateStartX, int templateStartY, int templateWidth, int templateHeight, int colorToleranceAsPercent,
            int pixelTolerance) {
        this(nativePath, startX, startY, width, height, templatePath, templateStartX, templateStartY, templateWidth,
                templateHeight, colorToleranceAsPercent);
        this.pixelTolerance = pixelTolerance;
    }

    public int getPixelTolerance() {
        return pixelTolerance;
    }

    public void setPixelTolerance(int pixelTolerance) {
        this.pixelTolerance = pixelTolerance;
    }

}
