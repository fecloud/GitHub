/**
 * @(#) ImageUtil.java Created on 2012-05-10
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.image;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.Log;

import com.aspire.android.common.exception.MException;
import com.aspire.util.ToolException;

/**
 * The class <code>ImageUtil</code>
 * 
 * @author linjunsui
 * @version 1.0
 */
public final class ImageUtil {

    /**
     * Constants
     */
    private static final String TAG = "ImageUtil";
    private static final boolean FIND_WITH_OPENCV = true;

    /**
     * JNI class
     */
    protected static ImageUtilJNI imageUtilJNI = new ImageUtilJNI();

    /**
     * Constructor
     */
    private ImageUtil() {
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
    public static final boolean verify(Bitmap inputImage, int inputStartX, int inputStartY, Bitmap templateImage,
            int templateStartX, int templateStartY, int w, int h, int colorTolerance) {

        return verify(inputImage, inputStartX, inputStartY, templateImage, templateStartX, templateStartY, w, h,
                colorTolerance, 0);
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
    public static final boolean verify(Bitmap inputImage, int inputStartX, int inputStartY, Bitmap templateImage,
            int templateStartX, int templateStartY, int w, int h, int colorTolerance, int pixelTolerance) {
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
        Log.d(TAG, "upperTorlerance:" + upperTorlerance);

        int inputRgb = 0;
        int templateRgb = 0;
        int colorDiff;

        int diffPixelLeft = pixelTolerance;
        // boolean result = true;
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                inputRgb = inputImage.getPixel(inputStartX + x, inputStartY + y);
                templateRgb = templateImage.getPixel(templateStartX + x, templateStartY + y);
                if (inputRgb != templateRgb) {
                    // 取像素差
                    colorDiff = diff(inputRgb, templateRgb);
                    if (colorDiff > upperTorlerance || colorDiff < lowerTorlarence) {

                        diffPixelLeft--;
                        Log.d("ImageUtil", "diff found at x=" + (inputStartX + x) + ", y=" + (inputStartY + y)
                                + ", diff=" + colorDiff + ", expect < " + upperTorlerance);
                        if (diffPixelLeft < 0) {
                            int inputX = inputStartX + x;
                            int inputY = inputStartY + y;
                            int tempX = templateStartX + x;
                            int tempY = templateStartY + y;
                            Log.d(TAG, "inputX:" + inputX + " inputY:" + inputY + " tempX:" + tempX + " tempY:" + tempY);
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
            int templateStartX, int templateStartY, int w, int h, int colorTolerance) throws MException {

        Bitmap inputImage = BitmapFactory.decodeStream(new ByteArrayInputStream(inputImageData));
        Bitmap templateImage = BitmapFactory.decodeStream(new ByteArrayInputStream(templateImageData));

        if (inputImage == null || templateImage == null) {
            throw new MException("图片解码失败");
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
     * @throws MException
     *             when error occur
     */
    public static int[] find(byte[] inputImageData, byte[] templateImageData, int templateStartX, int templateStartY,
            int templateWidth, int templateHeight, int colorTolerance) throws MException {
        return find(inputImageData, 0, 0, -1, -1, templateImageData, templateStartX, templateStartY, templateWidth,
                templateHeight, colorTolerance);
    }

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
     * @throws MException
     *             when error occur
     */
    public static int[] find(byte[] inputImageData, int inputStartX, int inputStartY, int inputWidth, int inputHeight,
            byte[] templateImageData, int templateStartX, int templateStartY, int templateWidth, int templateHeight,
            int colorTolerance) throws MException {

        if ((templateWidth > inputWidth && inputWidth > 0) && (templateHeight > inputHeight && inputHeight > 0)) {
            throw new MException("要查找的目标图片大于查找区域");
        }

        int[] result = null;

        if (FIND_WITH_OPENCV) {
            Log.d("ImageUtil", "imageUtilJNI.tempaleMatch return result start: ");

            result = imageUtilJNI
                    .tempaleMatch(inputImageData, inputStartX, inputStartY, inputWidth, inputHeight, templateImageData,
                            templateStartX, templateStartY, templateWidth, templateHeight, 0, colorTolerance);

            if (result == null) {
                Log.d("ImageUtil", "imageUtilJNI.tempaleMatch return null");
                return null;
            }

            // 因为input偏移量设置后,返回以偏移量为起点,所以结果加上偏移量
            result[0] += inputStartX;
            result[1] += inputStartY;
            Log.d("ImageUtil", "imageUtilJNI.tempaleMatch return result=" + result[0] + "-" + result[1]);
        }

        Bitmap inputImage;
        Bitmap templateImage;

        try {
            InputStream inputStream = new ByteArrayInputStream(inputImageData);
            inputImage = BitmapFactory.decodeStream(inputStream);

            inputStream.reset();
            inputStream = new ByteArrayInputStream(templateImageData);
            templateImage = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            throw new MException("读取图片失败", e);
        }

        if (inputImage == null || templateImage == null) {
            throw new MException("图片解码失败");
        }

        if (FIND_WITH_OPENCV) {
            if (result != null
                    && verify(inputImage, result[0], result[1], templateImage, templateStartX, templateStartY,
                            templateWidth, templateHeight, colorTolerance)) {
                return result;
            }

        } else {
            Log.d("ImageUtil", "find with verify start");
            for (int x = 0; x < inputImage.getWidth() - templateImage.getWidth(); x++) {
                for (int y = 0; y < inputImage.getHeight() - templateImage.getHeight(); y++) {
                    if (verify(inputImage, x, y, templateImage, templateStartX, templateStartY, templateWidth,
                            templateHeight, colorTolerance)) {
                        Log.d("ImageUtil", "find with verify result = " + x + "," + y);
                        return new int[] { x, y };
                    }
                }
            }
            Log.d("ImageUtil", "find with verify not found");
        }

        return null;
    }

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
     * @throws MException
     */
    public static int[] find(byte[] inputImageData, int inputStartX, int inputStartY, int inputWidth, int inputHeight,
            byte[] templateImageData, int templateStartX, int templateStartY, int templateWidth, int templateHeight,
            int colorTolerance, int xStep, int yStep) throws MException {
        if ((templateWidth > inputWidth && inputWidth > 0) && (templateHeight > inputHeight && inputHeight > 0)) {
            throw new MException("要查找的目标图片大于查找区域");
        }

        Bitmap inputImage;
        Bitmap templateImage;

        try {
            InputStream inputStream = new ByteArrayInputStream(inputImageData);
            inputImage = BitmapFactory.decodeStream(inputStream);

            inputStream.reset();
            inputStream = new ByteArrayInputStream(templateImageData);
            templateImage = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            throw new MException("读取图片失败", e);
        }

        if (inputImage == null || templateImage == null) {
            throw new MException("图片解码失败");
        }

        if (inputWidth <= 0)
            inputWidth = inputImage.getWidth();
        if (inputHeight <= 0)
            inputHeight = inputImage.getHeight();
        if (templateWidth <= 0)
            templateWidth = templateImage.getWidth();
        if (templateHeight <= 0)
            templateHeight = templateImage.getHeight();

        // 取得模板所有二维数组相素
        int[][] templateImageColor = getBitmapPixel(templateImage, templateStartX, templateStartY, templateWidth,
                templateHeight);

        int[][] inputImageColor = null;
        for (int i = 0; i < inputHeight; i += yStep) {
            for (int j = 0; j < inputWidth; j += xStep) {
                final int x = inputStartX + j;
                final int y = inputStartY + i;
                // 检查是不是到图片边缘了
                if ((x + templateWidth) > inputWidth || (y + templateHeight) > inputHeight)
                    return null;
                inputImageColor = getBitmapPixel(inputImage, x, y, templateWidth, templateHeight);
                if (compartTowPixelArray(inputImageColor, templateImageColor, colorTolerance))
                    return new int[] { x, y };
            }
        }

        return null;
    }

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
     * @throws MException
     *             when error occur
     */
    public static String ocrAll(byte[] inputImageData, int lang, double scale, int threshold) throws MException {
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
     * @throws MException
     *             when error occur
     */
    public static String ocrAll(byte[] inputImageData, int lang) throws MException {
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
     * @throws MException
     *             when error occur
     */
    public static String ocrRect(byte[] inputImage, int startX, int startY, int width, int height, int lang,
            double scale, int threshold) throws MException {

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
     * @throws MException
     *             when error occur
     */
    public static String ocrRect(byte[] inputImage, int startX, int startY, int width, int height, int lang)
            throws MException {
        // TODO ocrRect
        return null;
    }

    /**
     * 创建图片校验的凭证
     * 
     * @param referentImage
     *            参考图片
     * @param currentImage
     *            当前图片
     * @param startX
     *            校验区域 开始X坐标
     * @param startY
     *            校验区域 开始Y坐标
     * @param width
     *            校验区域宽
     * @param height
     *            校验区域高
     * @param colorTolerance
     *            颜色容忍度
     * @return 凭证图片
     * @throws ToolException
     *             发生异常
     * @throws IOException
     */
    public static byte[] createVerifyWarrant(byte[] referentImage, byte[] currentImage, int startX, int startY,
            int width, int height, int colorTolerance) throws ToolException, IOException {
        return createFindWarrant(referentImage, currentImage, startX, startY, width, height, colorTolerance);
    }

    /**
     * 创建图片模板匹配凭证
     * 
     * @param referentImage
     *            参考图片
     * @param currentImage
     *            当前图片
     * @param startX
     *            校验区域 开始X坐标
     * @param startY
     *            校验区域 开始Y坐标
     * @param width
     *            校验区域宽
     * @param height
     *            校验区域高
     * @param colorTolerance
     *            颜色容忍度
     * @return 凭证图片
     * @throws ToolException
     *             发生异常
     * @throws IOException
     */
    public static byte[] createFindWarrant(byte[] referentImage, byte[] currentImage, int startX, int startY,
            int width, int height, int colorTolerance) throws ToolException, IOException {
        final Bitmap referentBitmap = BitmapFactory.decodeByteArray(referentImage, 0, referentImage.length);
        final Bitmap currentBitmap = BitmapFactory.decodeByteArray(currentImage, 0, currentImage.length);
        int matchMode = 0;
        if (width > 0 && height > 0) {
            matchMode = 0;
        } else {
            matchMode = 1;
        }
        final String text = "匹配模式: " + ((matchMode == 0) ? "定位匹配" : "全图查找")

        + ";" + "\n" + "\n" + "校验区域: (" + startX + "," + startY + "," + (startX + width) + ","

        + (startY + height) + ");" + "\n" + "\n" + "颜色容忍度: " + colorTolerance;

        final Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
        paint.setTextSize(15.0f);
        paint.setColor(Color.RED);
        final float text_width = paint.measureText(text);

        int newBitmapHeight = 0;
        if (referentBitmap.getHeight() > currentBitmap.getHeight()) {
            newBitmapHeight = referentBitmap.getHeight();
        } else {
            newBitmapHeight = currentBitmap.getHeight();
        }
        final Bitmap newBitmap = Bitmap.createBitmap(
                referentBitmap.getWidth() + (int) text_width + currentBitmap.getWidth(), newBitmapHeight,
                Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(currentBitmap, 0, 0, null);
        canvas.drawText(text, currentBitmap.getWidth(), 17, paint);
        canvas.drawBitmap(referentBitmap, currentBitmap.getWidth() + text_width, 0, null);
        canvas.save();
        if (width != 0 && height != 0) {
            // true为去除锯齿效果
            paint.setAntiAlias(true);
            paint.setStyle(Style.STROKE);
            final RectF rect = new RectF(startX, startY, startX + width, startY + height);
            canvas.drawRect(rect, paint);
            canvas.save();
        }

        return bitMapToBytes(newBitmap);
    }

    /**
     * 创建OCR凭证
     * 
     * @param currentImage
     *            当前图片
     * @param startX
     *            校验区域 开始X坐标
     * @param startY
     *            校验区域 开始Y坐标
     * @param width
     *            校验区域宽
     * @param height
     *            校验区域高
     * @param expectedOcrResult
     *            期待OCR结果
     * @param ocrResult
     *            实际OCR结果
     * @return 凭证图片
     * @throws ToolException
     *             发生异常
     * @throws IOException
     */
    public static byte[] createOcrWarrant(byte[] currentImage, int startX, int startY, int width, int height,
            String expectedOcrResult, String ocrResult) throws ToolException, IOException {

        final Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
        // true为去除锯齿效果
        paint.setAntiAlias(true);
        paint.setTextSize(15.0f);
        paint.setColor(Color.RED);

        int matchMode = 0;
        if (width > 0 && height > 0) {
            matchMode = 0;
        } else {
            matchMode = 1;
        }
        final String text = "匹配模式: " + ((matchMode == 0) ? "定位匹配" : "全图查找")

        + ";" + "\n" + "\n" + "校验区域: (" + startX + "," + startY + "," + (startX + width) + ","

        + (startY + height) + ");" + "\n" + "\n" + "期待OCR结果: " + expectedOcrResult + ";" + "\n" + "\n" + "实际OCR结果: "
                + ocrResult;

        final float text_width = paint.measureText(text);
        final Bitmap bmp = BitmapFactory.decodeByteArray(currentImage, 0, currentImage.length);
        final Bitmap newBitmap = Bitmap.createBitmap(bmp.getWidth() + (int) text_width, bmp.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bmp, 0, 0, null);
        canvas.drawText(text, bmp.getWidth(), 17, paint);
        if (width > 0 && height > 0) {
            final RectF rect = new RectF(startX, startY, startX + width, startY + height);
            paint.setStyle(Style.STROKE);
            paint.setColor(Color.RED);
            canvas.drawRect(rect, paint);
        }
        canvas.save();

        return bitMapToBytes(newBitmap);
    }

    /**
     * convert bitmap to byte arrays
     * 
     * @param bitmap
     * @return
     * @throws IOException
     */
    private static byte[] bitMapToBytes(Bitmap bitmap) throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 100, out);
        out.flush();
        out.close();
        return out.toByteArray();
    }

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
    private static int[][] getBitmapPixel(Bitmap bitmap, int startX, int startY, int w, int h) {
        // 取得模板所有二维数组相素
        int[][] colorTable = new int[h][w];
        for (int i = 0; i < colorTable.length; i++) {
            for (int j = 0; j < colorTable[i].length; j++) {
                colorTable[i][j] = bitmap.getPixel(startX + j, startY + i);
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
    private static boolean compartTowPixelArray(int[][] input, int[][] template, int colorTolerance) {
        if (null != input && null != template && input.length == template.length) {
            Log.d(TAG, "colorTolerance:" + colorTolerance);
            int upperTorlerance = Math.abs(colorTolerance);
            int lowerTorlarence = -1 * upperTorlerance;
            int colorDiff;
            for (int i = 0; i < input.length; i++) {
                for (int j = 0; j < input[i].length; j++) {
                    colorDiff = diff(input[i][j], template[i][j]);
                    if (colorDiff > upperTorlerance || colorDiff < lowerTorlarence) {
                        Log.d(TAG, "colorDiff:" + colorDiff);
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
}
