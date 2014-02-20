/**
 * @(#) ImageUtil.java Created on 2012-05-10
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.inspurworld.msg.exception.ToolException;

/**
 * The class <code>ImageUtil</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public final class ImageUtil {

    private static Logger logger = Logger.getLogger(ImageUtil.class.getClass());

    /**
     * Constructor
     */
    private ImageUtil() {
    }

    /**
     * 跟据颜色百分比计算颜色数
     * 
     * @param totalColor
     *            总颜色数
     * @param colorToleranceAsPercent
     *            颜色百分比
     * @return pixel count
     */
    private static int calcPixel(int totalColor, int colorToleranceAsPercent) {
        return colorToleranceAsPercent * 3 * totalColor / 100;
    }

    /**
     * 校验图片
     * 
     * @param inputImage
     *            当前手机截图
     * @param inputStartX
     *            当前手机截图X位移
     * @param inputStartY
     *            当前手机截图Y位移
     * @param templateImage
     *            模板匹配图片
     * @param templateStartX
     *            模板匹配图片X位移
     * @param templateStartY
     *            模板匹配图片Y位移
     * @param w
     *            校验区域的宽
     * @param h
     *            校验区域的高
     * @param colorTolerance
     *            颜色容忍度，单位：像素颜色(RGB相差的像素和)
     * @return 校验是否通过
     */
    public static final boolean verify(BufferedImage inputImage, int inputStartX, int inputStartY,
            BufferedImage templateImage, int templateStartX, int templateStartY, int w, int h, int colorTolerance) {

        return verify(inputImage, inputStartX, inputStartY, templateImage, templateStartX, templateStartY, w, h,
                calcPixel(256, colorTolerance), 0);
    }

    /**
     * 校验图片
     * 
     * @param inputImage
     *            当前手机截图
     * @param inputStartX
     *            当前手机截图X位移
     * @param inputStartY
     *            当前手机截图Y位移
     * @param templateImage
     *            模板匹配图片
     * @param templateStartX
     *            模板匹配图片X位移
     * @param templateStartY
     *            模板匹配图片Y位移
     * @param w
     *            校验区域的宽
     * @param h
     *            校验区域的高
     * @param colorTolerance
     *            颜色容忍度，单位：像素颜色(RGB相差的像素和)
     * @param pixelTolerance
     *            像素容忍度，单位：像素颜色(RGB相差的像素和)
     * @return true 校验通过
     */
    public static final boolean verify(BufferedImage inputImage, int inputStartX, int inputStartY,
            BufferedImage templateImage, int templateStartX, int templateStartY, int w, int h, int colorTolerance,
            int pixelTolerance) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        if (templateStartX < 0 || templateStartY < 0) {
            return false;
        }

        if (w <= 0) {
            w = templateImage.getWidth() - templateStartX;
        }

        if (h <= 0) {
            h = templateImage.getHeight() - templateStartY;
        }

        if (templateStartX + w > width) {
            w = width - templateStartX;
        }

        if (templateStartY + h > height) {
            h = height - templateStartY;
        }

        int upperTorlerance = Math.abs(colorTolerance);
        int lowerTorlarence = -1 * upperTorlerance;
        logger.debug("upperTorlerance:" + upperTorlerance);

        int inputRgb = 0;
        int templateRgb = 0;
        int colorDiff;

        int diffPixelLeft = pixelTolerance;
        // boolean result = true;
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                inputRgb = inputImage.getRGB(inputStartX + x, inputStartY + y);
                templateRgb = templateImage.getRGB(templateStartX + x, templateStartY + y);
                if (inputRgb != templateRgb) {
                    // 取像素差
                    colorDiff = diff(inputRgb, templateRgb);
                    if (colorDiff > upperTorlerance || colorDiff < lowerTorlarence) {

                        diffPixelLeft--;
                        logger.debug("diff found at x=" + (inputStartX + x) + ", y=" + (inputStartY + y) + ", diff="
                                + colorDiff + ", expect < " + upperTorlerance);
                        if (diffPixelLeft < 0) {
                            int inputX = inputStartX + x;
                            int inputY = inputStartY + y;
                            int tempX = templateStartX + x;
                            int tempY = templateStartY + y;
                            logger.debug("inputX:" + inputX + " inputY:" + inputY + " tempX:" + tempX + " tempY:"
                                    + tempY);
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * 校验图片
     * 
     * @param inputImageBase64String
     *            当前手机截图,Base64格式
     * @param inputStartX
     *            当前手机截图X位移
     * @param inputStartY
     *            当前手机截图Y位移
     * @param templateImageBase64String
     *            模板匹配图片,Base64格式
     * @param templateStartX
     *            模板匹配图片X位移
     * @param templateStartY
     *            模板匹配图片Y位移
     * @param w
     *            校验区域的宽
     * @param h
     *            校验区域的高
     * @param colorTolerance
     *            颜色容忍度，单位：像素颜色(RGB相差的像素和)
     * @return true 校验通过
     * @throws ToolException
     *             when error occur
     */
    public static boolean verify(byte[] inputImageData, int inputStartX, int inputStartY, byte[] templateImageData,
            int templateStartX, int templateStartY, int w, int h, int colorTolerance) throws Exception {

        BufferedImage inputImage = ImageIO.read(new ByteArrayInputStream(inputImageData));
        BufferedImage templateImage = ImageIO.read(new ByteArrayInputStream(templateImageData));

        if (inputImage == null || templateImage == null) {
            throw new Exception("图片解码失败");
        }

        return verify(inputImage, inputStartX, inputStartY, templateImage, templateStartX, templateStartY, w, h,
               colorTolerance);
    }

    /**
     * 求共相差的像素（R、G、B相差像素相加
     * 
     * @param inputRgb
     *            输入颜色
     * @param templateRgb
     *            模板颜色
     * @return 颜色
     */
    protected static final int diff(int inputRgb, int templateRgb) {
        return Math.abs((inputRgb & 0xFF0000 >> 16) - (templateRgb & 0xFF0000 >> 16))
                + Math.abs((inputRgb & 0xFF00 >> 8) - (templateRgb & 0xFF00 >> 8))
                + Math.abs((inputRgb & 0xFF) - (templateRgb & 0xFF));

        // Color inputColor = new Color(inputRgb);
        // Color templateColor = new Color(templateRgb);
        //
        // return Math.abs(inputColor.getRed() - templateColor.getRed())
        // + Math.abs(inputColor.getGreen() - templateColor.getGreen())
        // + Math.abs(inputColor.getBlue() - templateColor.getBlue());
    }

    /**
     * 在inputImage中查找templateImage
     * 
     * @param inputImageData
     *            输入图片的原始输
     * @param templateImageData
     *            模板图片的原始输
     * @param templateStartX
     *            模板匹配图片X位移
     * @param templateStartY
     *            模板匹配图片Y位移
     * @param templateWidth
     *            校验区域的宽
     * @param templateHeight
     *            校验区域的高
     * @param colorTolerance
     *            颜色容忍度，单位：像素颜色(RGB相差的像素和)
     * @return 坐标
     * @throws Exception
     *             when error occur
     */
    // public static int[] find(byte[] inputImageData, byte[] templateImageData, int templateStartX, int templateStartY,
    // int templateWidth, int templateHeight, int colorTolerance) throws Exception {
    // return find(inputImageData, 0, 0, -1, -1, templateImageData, templateStartX, templateStartY, templateWidth,
    // templateHeight, colorTolerance);
    // }

    /**
     * 在inputImage中查找templateImage
     * 
     * @param inputImageData
     *            输入图片的原始输
     * @param inputStartX
     *            输入图片查找区域X位移
     * @param inputStartY
     *            输入图片查找区域Y位移
     * @param inputWidth
     *            查找区域的宽
     * @param inputHeight
     *            查找区域的高
     * @param templateImageData
     *            模板图片的原始输
     * @param templateStartX
     *            模板匹配图片X位移
     * @param templateStartY
     *            模板匹配图片Y位移
     * @param templateWidth
     *            校验区域的宽
     * @param templateHeight
     *            校验区域的高
     * @param colorTolerance
     *            颜色容忍度，单位：像素颜色(RGB相差的像素和)
     * @return 坐标
     * @throws Exception
     *             when error occur
     */
    // public static int[] find(byte[] inputImageData, int inputStartX, int inputStartY, int inputWidth, int
    // inputHeight,
    // byte[] templateImageData, int templateStartX, int templateStartY, int templateWidth, int templateHeight,
    // int colorTolerance) throws Exception {
    //
    // if ((templateWidth > inputWidth && inputWidth > 0) && (templateHeight > inputHeight && inputHeight > 0)) {
    // throw new Exception("要查找的目标图片大于查找区域");
    // }
    //
    // int[] result = null;
    //
    // if (FIND_WITH_OPENCV) {
    // Log.d("ImageUtil", "imageUtilJNI.tempaleMatch return result start: ");
    //
    // result = imageUtilJNI
    // .tempaleMatch(inputImageData, inputStartX, inputStartY, inputWidth, inputHeight, templateImageData,
    // templateStartX, templateStartY, templateWidth, templateHeight, 0, colorTolerance);
    //
    // if (result == null) {
    // Log.d("ImageUtil", "imageUtilJNI.tempaleMatch return null");
    // return null;
    // }
    //
    // // 因为input偏移量设置后,返回以偏移量为起点,所以结果加上偏移量
    // result[0] += inputStartX;
    // result[1] += inputStartY;
    // Log.d("ImageUtil", "imageUtilJNI.tempaleMatch return result=" + result[0] + "-" + result[1]);
    // }
    //
    // Bitmap inputImage;
    // Bitmap templateImage;
    //
    // try {
    // InputStream inputStream = new ByteArrayInputStream(inputImageData);
    // inputImage = BitmapFactory.decodeStream(inputStream);
    //
    // inputStream.reset();
    // inputStream = new ByteArrayInputStream(templateImageData);
    // templateImage = BitmapFactory.decodeStream(inputStream);
    // } catch (IOException e) {
    // throw new Exception("读取图片失败", e);
    // }
    //
    // if (inputImage == null || templateImage == null) {
    // throw new Exception("图片解码失败");
    // }
    //
    // if (FIND_WITH_OPENCV) {
    // if (result != null
    // && verify(inputImage, result[0], result[1], templateImage, templateStartX, templateStartY,
    // templateWidth, templateHeight, colorTolerance)) {
    // return result;
    // }
    //
    // } else {
    // Log.d("ImageUtil", "find with verify start");
    // for (int x = 0; x < inputImage.getWidth() - templateImage.getWidth(); x++) {
    // for (int y = 0; y < inputImage.getHeight() - templateImage.getHeight(); y++) {
    // if (verify(inputImage, x, y, templateImage, templateStartX, templateStartY, templateWidth,
    // templateHeight, colorTolerance)) {
    // Log.d("ImageUtil", "find with verify result = " + x + "," + y);
    // return new int[] { x, y };
    // }
    // }
    // }
    // Log.d("ImageUtil", "find with verify not found");
    // }
    //
    // return null;
    // }

    /**
     * 在inputImage中查找templateImage
     * 
     * @param inputImageData
     * @param inputStartX
     * @param inputStartY
     * @param inputWidth
     * @param inputHeight
     * @param templateImageData
     * @param templateStartX
     * @param templateStartY
     * @param templateWidth
     * @param templateHeight
     * @param colorTolerance
     * @param xStep
     * @param yStep
     * @return
     * @throws Exception
     */
    // public static int[] find(byte[] inputImageData, int inputStartX, int inputStartY, int inputWidth, int
    // inputHeight,
    // byte[] templateImageData, int templateStartX, int templateStartY, int templateWidth, int templateHeight,
    // int colorTolerance, int xStep, int yStep) throws Exception {
    // if ((templateWidth > inputWidth && inputWidth > 0) && (templateHeight > inputHeight && inputHeight > 0)) {
    // throw new Exception("要查找的目标图片大于查找区域");
    // }
    //
    // Bitmap inputImage;
    // Bitmap templateImage;
    //
    // try {
    // InputStream inputStream = new ByteArrayInputStream(inputImageData);
    // inputImage = BitmapFactory.decodeStream(inputStream);
    //
    // inputStream.reset();
    // inputStream = new ByteArrayInputStream(templateImageData);
    // templateImage = BitmapFactory.decodeStream(inputStream);
    // } catch (IOException e) {
    // throw new Exception("读取图片失败", e);
    // }
    //
    // if (inputImage == null || templateImage == null) {
    // throw new Exception("图片解码失败");
    // }
    //
    // if (inputWidth <= 0)
    // inputWidth = inputImage.getWidth();
    // if (inputHeight <= 0)
    // inputHeight = inputImage.getHeight();
    // if (templateWidth <= 0)
    // templateWidth = templateImage.getWidth();
    // if (templateHeight <= 0)
    // templateHeight = templateImage.getHeight();
    //
    // // 取得模板所有二维数组相素
    // int[][] templateImageColor = getBitmapPixel(templateImage, templateStartX, templateStartY, templateWidth,
    // templateHeight);
    //
    // int[][] inputImageColor = null;
    // for (int i = 0; i < inputHeight; i += yStep) {
    // for (int j = 0; j < inputWidth; j += xStep) {
    // final int x = inputStartX + j;
    // final int y = inputStartY + i;
    // // 检查是不是到图片边缘了
    // if ((x + templateWidth) > inputWidth || (y + templateHeight) > inputHeight)
    // return null;
    // inputImageColor = getBitmapPixel(inputImage, x, y, templateWidth, templateHeight);
    // if (compartTowPixelArray(inputImageColor, templateImageColor, colorTolerance))
    // return new int[] { x, y };
    // }
    // }
    //
    // return null;
    // }

    /**
     * OCR the input image
     * 
     * @param inputImageData
     *            输入截图
     * @param lang
     *            识别语言 0:数字 ; 1:英文 +数字; 2:简体中文 +数字; 3:繁体中文 +数字
     * @param scale
     *            放大倍数（<= 0 或 = 1 表示无需缩放）
     * @param threshold
     *            二值化阀值（图像的灰度）0到255，255为白色， 0为黑色 （< 0 或 > 255 表示无需二值化）
     * @return 识别结果; NULL : 如果发生错误
     * @throws Exception
     *             when error occur
     */
    public static String ocrAll(byte[] inputImageData, int lang, double scale, int threshold) throws Exception {
        // TODO ocrAll
        return null;
    }

    /**
     * OCR the input image
     * 
     * @param inputImageData
     *            输入截图
     * @param lang
     *            识别语言 0:数字 ; 1:英文 +数字; 2:简体中文 +数字; 3:繁体中文 +数字
     * @return 识别结果; NULL : 如果发生错误
     * @throws Exception
     *             when error occur
     */
    public static String ocrAll(byte[] inputImageData, int lang) throws Exception {
        // TODO ocrAll
        /*
         * Bitmap bitmap = Bitmap.createBitmap(1000 , 10000, Config.ARGB_8888); Canvas canvas = new Canvas(bitmap);
         * canvas. bitmap.recycle(); return bitmap;
         */
        return null;
    }

    /**
     * OCR the rect of the input image
     * 
     * @param inputImage
     *            输入截图
     * @param startX
     *            OCR区域X位移
     * @param startY
     *            OCR区域Y位移
     * @param width
     *            OCR区域的宽
     * @param height
     *            OCR区域的高
     * @param lang
     *            识别语言 0:数字 ; 1:英文 +数字; 2:中文 +数字; 3:繁体中文 +数字
     * @param scale
     *            放大倍数（<= 0 或 = 1 表示无需缩放）
     * @param threshold
     *            二值化阀值（图像的灰度）0到255，255为白色， 0为黑色 （< 0 或 > 255 表示无需二值化）
     * @return 识别结果; NULL : 如果发生错误
     * @throws Exception
     *             when error occur
     */
    public static String ocrRect(byte[] inputImage, int startX, int startY, int width, int height, int lang,
            double scale, int threshold) throws Exception {

        // TODO ocrRect
        return null;
    }

    /**
     * OCR the rect of the input image
     * 
     * @param inputImage
     *            输入截图
     * @param startX
     *            OCR区域X位移
     * @param startY
     *            OCR区域Y位移
     * @param width
     *            OCR区域的宽
     * @param height
     *            OCR区域的高
     * @param lang
     *            识别语言 0:数字 ; 1:英文 +数字; 2:中文 +数字; 3:繁体中文 +数字
     * @return 识别结果; NULL : 如果发生错误
     * @throws Exception
     *             when error occur
     */
    public static String ocrRect(byte[] inputImage, int startX, int startY, int width, int height, int lang)
            throws Exception {
        // TODO ocrRect
        return null;
    }

    // private static byte[] bitMapToBytes(BufferedImage bitmap) throws IOException {
    // final ByteArrayOutputStream out = new ByteArrayOutputStream();
    // bitmap.compress(CompressFormat.JPEG, 100, out);
    // out.flush();
    // out.close();
    // return out.toByteArray();
    // }

    /**
     * 取得模板所有二维数组相素
     * 
     * @param bitmap
     * @param startX
     * @param startY
     * @param w
     * @param h
     * @return
     */
    public static int[][] getBitmapPixel(BufferedImage bitmap, int startX, int startY, int w, int h) {
        // 取得模板所有二维数组相素
        int[][] colorTable = new int[h][w];
        for (int i = 0; i < colorTable.length; i++) {
            for (int j = 0; j < colorTable[i].length; j++) {
                colorTable[i][j] = bitmap.getRGB(startX + j, startY + i);
            }
        }
        return colorTable;
    }

    /**
     * 比较两个二维数组相素差
     * 
     * @param input
     * @param template
     * @param colorTolerance
     * @return
     */
    // private static boolean compartTowPixelArray(int[][] input, int[][] template, int colorTolerance) {
    // if (null != input && null != template && input.length == template.length) {
    // Log.d(TAG, "colorTolerance:" + colorTolerance);
    // int upperTorlerance = Math.abs(colorTolerance);
    // int lowerTorlarence = -1 * upperTorlerance;
    // int colorDiff;
    // for (int i = 0; i < input.length; i++) {
    // for (int j = 0; j < input[i].length; j++) {
    // colorDiff = diff(input[i][j], template[i][j]);
    // if (colorDiff > upperTorlerance || colorDiff < lowerTorlarence) {
    // Log.d(TAG, "colorDiff:" + colorDiff);
    // return false;
    // }
    // }
    // }
    // return true;
    // }
    // return false;
    // }
}
