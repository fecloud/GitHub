/**
 * @(#) CertificateFacotry.java Created on 2012-8-2
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.screen.check.certificate;

import android.util.Log;

import com.aspire.android.common.exception.MException;
import com.aspire.android.image.ImageUtil;
import com.aspire.android.screen.check.CheckParam;
import com.aspire.android.screen.check.CheckResult;
import com.aspire.android.screen.check.find.FindInParam;
import com.aspire.android.screen.check.find.FindInResult;
import com.aspire.android.screen.check.ocr.OCRParam;
import com.aspire.android.screen.check.ocr.OCRResult;
import com.aspire.android.screen.check.verify.VerifyParams;
import com.aspire.android.screen.check.verify.VerifyResult;
import com.aspire.android.util.FileUtil;

/**
 * The class <code>CertificateFacotry</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public final class CertificateFacotry {

    private static final String TAG = "CertificateFacotry";

    /**
     * generate Certificate
     * 
     * @param param
     * @param result
     * @return
     */
    public static byte[] generateCertificate(CheckParam param, CheckResult result) throws MException {
        Log.d(TAG, "generateCertificate");
        if (param instanceof FindInParam && result instanceof FindInResult) {
            return generateFindIn((FindInParam) param, (FindInResult) result);
        } else if (param instanceof VerifyParams && result instanceof VerifyResult) {
            return generateVerify((VerifyParams) param, (VerifyResult) result);
        } else if (param instanceof OCRParam && result instanceof OCRResult) {
            return generateOCR((OCRParam) param, (OCRResult) result);
        }
        return null;
    }

    /**
     * generate findIn Certificate
     * 
     * @param param
     * @param result
     * @return
     */
    private static byte[] generateFindIn(FindInParam param, FindInResult result) throws MException {
        try {
            final byte[] referentImage = FileUtil.readFile(param.getTemplateImagePath());
            return ImageUtil.createFindWarrant(referentImage, param.getScreenBytes(), param.getStartX(),
                    param.getStartY(), param.getWidth() > 0 ? param.getWidth() : 0,
                    param.getHeight() > 0 ? param.getHeight() : 0, param.getColorToleranceAsPercent());
        } catch (Exception e) {
            Log.e(TAG, "generateFindIn error", e);
            throw new MException(e);
        }
    }

    /**
     * generate verify Certificate
     * 
     * @param param
     * @param result
     * @return
     */
    private static byte[] generateVerify(VerifyParams param, VerifyResult result) throws MException {

        try {
            final byte[] referentImage = FileUtil.readFile(param.getTemplateImagePath());
            return ImageUtil.createVerifyWarrant(referentImage, param.getScreenBytes(), param.getStartX(),
                    param.getStartY(), param.getTemplateWidth(), param.getTemplateHeight(),
                    param.getColorToleranceAsPercent());
        } catch (Exception e) {
            Log.e(TAG, "generateVerify error", e);
            throw new MException(e);
        }
    }

    /**
     * generate ocr Certificate
     * 
     * @param param
     * @param result
     * @return
     */
    private static byte[] generateOCR(OCRParam param, OCRResult result) throws MException {

        try {
            return ImageUtil.createOcrWarrant(param.getScreenBytes(), param.getStartX(), param.getStartY(),
                    param.getWidth(), param.getHeight(), result.getExpect(), result.getReality());
        } catch (Exception e) {
            Log.e(TAG, "generateVerify error", e);
            throw new MException(e);
        }
    }

}
