/**
 * @(#) ImageUtilJNI.java Created on 2012-5-10
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.image;

import android.util.Log;

/**
 * The class <code>ImageUtilJNI</code>
 *
 * @author linjunsui
 * @version 1.0
 */
public class ImageUtilJNI {

    static {
        try {
            System.loadLibrary("ImageUtil");
        } catch (Exception ex) {
            Log.e("ImageUtilJNI", "Error to load libImageUtil.so", ex);
        }
    }

    /**
     * template match.
     * @param inputImage 输入截图
     * @param inputStartX 输入截图查找区域????X位移
     * @param inputStartY 输入截图查找区域????Y位移
     * @param inputWidth 输入截图查找区域的宽
     * @param inputHeight 输入截图查找区域的高
     * @param templateImage 模板截图
     * @param templateStartX 模板截图校验区域????X位移
     * @param templateStartY 模板截图校验区域????Y位移
     * @param templateWidth 模板截图校验区域的宽
     * @param templateHeight 模板截图校验区域的高
     * @param algorithm 匹配算法
     * @param colorTolerance 颜色容忍度（暂无用）
     * @return 坐标
     */
    public native int[] tempaleMatch(byte[] inputImage, int inputStartX,
            int inputStartY, int inputWidth, int inputHeight,
            byte[] templateImage, int templateStartX, int templateStartY,
            int templateWidth, int templateHeight, int algorithm,
            int colorTolerance);

    /**
     * template match.
     * @param inputImage 输入截图
     * @param templateImage 模板截图
     * @param templateStartX 模板截图校验区域????X位移
     * @param templateStartY 模板截图校验区域????Y位移
     * @param templateWidth 模板截图校验区域的宽
     * @param templateHeight 模板截图校验区域的高
     * @param algorithm 匹配算法
     * @param colorTolerance 颜色容忍度（暂无用）
     * @return 坐标
     */
    public int[] tempaleMatch(byte[] inputImage, byte[] templateImage,
            int templateStartX, int templateStartY, int templateWidth,
            int templateHeight, int algorithm, int colorTolerance) {
        return tempaleMatch(inputImage, 0, 0, -1, -1, templateImage,
                templateStartX, templateStartY, templateWidth, templateHeight,
                algorithm, colorTolerance);
    }

    /**
     * template match
     * @param inputImage 输入截图
     * @param templateImage 模板截图
     * @param algorithm 匹配算法
     * @param colorTolerance 颜色容忍度（暂无用）
     * @return 坐标
     */
    public int[] tempaleMatch(byte[] inputImage, byte[] templateImage,
            int algorithm, int colorTolerance) {
        return tempaleMatch(inputImage, 0, 0, -1, -1, templateImage,
                0, 0, -1, -1,
                algorithm, colorTolerance);
    }
    
    /**
     * OCR the input image 
     * @param inputImage 输入截图
     * @param lang 识别语言 0:数字 ; 1:英文 +数字; 2:简体中文 +数字; 3:繁体中文 +数字
     * @param scale 放大倍数（<= 0 或 = 1 表示无需缩放）
     * @param threshold 二值化阀值（图像的灰度）0到255，255为白色，
     *                   0为黑色 （< 0 或 > 255 表示无需二值化）
     * @return 识别结果; NULL : 如果发生错误
     */
    public native String ocrAll(byte[] inputImage, int lang, double scale, 
            int threshold);
    
    /**
     * OCR the rect of the input image 
     * @param inputImage 输入截图
     * @param startX OCR区域????X位移
     * @param startY OCR区域????Y位移
     * @param width OCR区域的宽
     * @param height OCR区域的高
     * @param lang 识别语言 0:数字 ; 1:英文 +数字; 2:简体中文 +数字; 3:繁体中文 +数字
     * @param scale 放大倍数（<= 0 或 = 1 表示无需缩放）
     * @param threshold 二值化阀值（图像的灰度）0到255，255为白色，
     *                   0为黑色 （< 0 或 > 255 表示无需二值化）
     * @return 识别结果; NULL : 如果发生错误
     */
    public native String ocrRect(byte[] inputImage, int startX, int startY,
            int width, int height, int lang, double scale, int threshold);
}
