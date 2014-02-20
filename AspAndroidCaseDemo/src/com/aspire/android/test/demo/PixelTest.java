/**
 * @(#) PixelTest.java Created on 2012-8-9
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.demo;

import java.io.File;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.test.AndroidTestCase;

/**
 * The class <code>PixelTest</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class PixelTest extends AndroidTestCase {

    public void pixelTest() {
        final Bitmap bitmap = getBitmap();
        final int pixel = bitmap.getPixel(2, 2);
        int r = pixel & 0xFF0000 >> 16;
        int b = pixel & 0xFF00 >> 8;
        int g = pixel & 0xFF;
        System.out.println(r);
    }

    private Bitmap getBitmap() {
        final String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "red.jpg";
        return BitmapFactory.decodeFile(path);
    }

}
