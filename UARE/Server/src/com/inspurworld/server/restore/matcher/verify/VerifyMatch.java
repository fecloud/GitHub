/**
 * @(#) VerifyMatch.java Created on 2012-8-2
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server.restore.matcher.verify;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.inspurworld.server.config.Config;
import com.inspurworld.server.image.ImageUtil;
import com.inspurworld.server.restore.matcher.CheckMatch;

/**
 * The class <code>VerifyMatch</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class VerifyMatch extends CheckMatch<VerifyParam, VerifyResult> {

    public VerifyMatch() {
        this(null);
    }

    /**
     * Constructor
     * 
     * @param param
     */
    public VerifyMatch(VerifyParam param) {
        super(param);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.screen.check.CheckMatch#match(java.lang.Object)
     */
    @Override
    public VerifyResult match(VerifyParam param) throws Exception {
        if (null == param)
            throw new Exception("the params not is null");
        if (!(param instanceof VerifyParam))
            throw new Exception("the params type not is VerifyParams");
        this.param = param; // 解析模板文件
        final BufferedImage inputImage = ImageIO.read(new File(param.getInputPath()));
        final BufferedImage templateImage = ImageIO.read(new File(Config.MASTER_DIR + param.getTemplatePath()));

        final boolean verify = ImageUtil.verify(inputImage, param.getStartX(), param.getStartY(), templateImage,
                param.getTemplateStartX(), param.getTemplateStartY(), param.getTemplateWidth(),
                param.getTemplateHeight(), param.getColorToleranceAsPercent());
        
        final VerifyResult result = new VerifyResult();
        result.setMatched(verify);
        return result;
    }
}
