/**
 * @(#) IOcrService.java Created on 2012-5-28
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.ocr;

/**
 * The class <code>IOcrService</code>
 *
 * @author zhenghui
 * @version 1.0
 */
interface IOcrService {
 	/**
     * OCR the input image
     * 
     * @param inputImage
     *            输入截图
     * @param lang
     *            识别语言 0:数字 ; 1:英文 +数字; 2:简体中文 +数字; 3:繁体中文 +数字
     * @param scale
     *            放大倍数（<= 0 或 = 1 表示无需缩放）
     * @param threshold
     *            二值化阀值（图像的灰度）0到255，255为白色， 0为黑色 （< 0 或 > 255 表示无需二值化）
     * @return 识别结果（UTF8编码）; NULL : 如果发生错误
     */
    String ocrAll(in byte[] is, in int lang, in double scale, in int threshold);
    
    
    /**
     * OCR the rect of the input image
     * 
     * @param inputImage
     *            输入截图
     * @param startX
     *            OCR区域开始X位移
     * @param startY
     *            OCR区域开始Y位移
     * @param width
     *            OCR区域的宽
     * @param height
     *            OCR区域的高
     * @param lang
     *            识别语言 0:数字 ; 1:英文 +数字; 2:简体中文 +数字; 3:繁体中文 +数字
     * @param scale
     *            放大倍数（<= 0 或 = 1 表示无需缩放）
     * @param threshold
     *            二值化阀值（图像的灰度）0到255，255为白色， 0为黑色 （< 0 或 > 255 表示无需二值化）
     * @return 识别结果（utf8编码）; NULL : 如果发生错误
     */
    String ocrRect(in byte[] is, in int lang, in int startX, in int startY, in int width, in int height, in double scale, in int threshold);
}
