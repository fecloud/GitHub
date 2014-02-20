/**
 * @(#) VerifyMatch.java Created on 2012-8-2
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.screen.check.verify;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.aspire.android.common.exception.MException;
import com.aspire.android.image.ImageUtil;
import com.aspire.android.screen.check.CheckMatch;
import com.aspire.android.util.FileUtil;

/**
 * The class <code>VerifyMatch</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class VerifyMatch extends CheckMatch<VerifyParams, VerifyResult> {

    public VerifyMatch() {
        this(null);
    }

    /**
     * Constructor
     * 
     * @param param
     */
    public VerifyMatch(VerifyParams param) {
        super(param);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.screen.check.CheckMatch#match(java.lang.Object)
     */
    @Override
    public VerifyResult match(VerifyParams param) throws MException {
        if (null == param)
            throw new MException("the params not is null");
        if (!(param instanceof VerifyParams))
            throw new MException("the params type not is VerifyParams");

        this.param = param;
        // 解析模板文件
        final Bitmap inputImage = BitmapFactory.decodeByteArray(param.getScreenBytes(), 0,
                param.getScreenBytes().length);
        Bitmap templateImage = null;
        try {
            templateImage = FileUtil.decodeStreamOfBitmap(param.getTemplateImagePath());
        } catch (Exception e) {
            throw new MException("decode templateImage fail", e);
        }
        final boolean verify = ImageUtil.verify(inputImage, param.getStartX(), param.getStartY(), templateImage,
                param.getTemplateStartX(), param.getTemplateStartY(), param.getTemplateWidth(),
                param.getTemplateHeight(), param.getColorToleranceAsPercent(), param.getPixelTolerance());
        final VerifyResult result = new VerifyResult();
        result.setMatched(verify);
        return result;
    }

}
