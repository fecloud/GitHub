/**
 * @(#) CheckParam.java Created on 2012-8-2
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server.restore.matcher;

/**
 * The class <code>CheckParam</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public abstract class CheckParam {

    /**
     * 原图二进制数组
     */
    private String inputPath;

    /**
     * x偏移量
     */
    private int startX;

    /**
     * y偏移量
     */
    private int startY;

    /**
     * 宽
     */
    private int width;

    /**
     * 高
     */
    private int height;

    /**
     * Constructor
     */
    public CheckParam() {
    }

    /**
     * Constructor
     * 
     * @param screenBytes
     * @param startX
     * @param startY
     * @param width
     * @param height
     */
    public CheckParam(String inputPath, int startX, int startY, int width, int height) {
        super();
        this.inputPath = inputPath;
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;
    }

    public String getInputPath() {
        return inputPath;
    }

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
