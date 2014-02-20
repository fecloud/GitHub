/**
 * @(#) OCRTest.java Created on 2012-10-30
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server;

import com.inspurworld.server.ocr.OCRFind;
import com.inspurworld.server.restore.matcher.ocr.OCRMatch;
import com.inspurworld.server.restore.matcher.ocr.OCRParam;
import com.inspurworld.server.restore.matcher.ocr.OCRResult;

/**
 * The class <code>OCRTest</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class OCRTest {

    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        // final OCRFind find = new OCRFind("C:\\workspace\\Server\\tesseractocr\\2.jpg", OCRFind.CHI_SIM);
        // System.out.println(find.ocr());

        OCRParam ocrParam = new OCRParam("C:\\workspace\\Server\\tesseractocr\\2.jpg", OCRFind.CHI_SIM, "文档");
        OCRMatch match = new OCRMatch(ocrParam);
        OCRResult result = match.match();
        System.out.println(result.isMatched());

    }

}
