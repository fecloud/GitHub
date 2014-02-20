/**
 * @(#) FindInMatch.java Created on 2012-8-2
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.screen.check.find;

import java.io.IOException;

import com.aspire.android.common.exception.MException;
import com.aspire.android.image.ImageUtil;
import com.aspire.android.screen.check.CheckMatch;
import com.aspire.android.util.FileUtil;

/**
 * The class <code>FindInMatch</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class FindInMatch extends CheckMatch<FindInParam, FindInResult> {

    public FindInMatch() {
        this(null);
    }

    public FindInMatch(FindInParam param) {
        super(param);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.screen.check.CheckMatch#match(java.lang.Object)
     */
    @Override
    public FindInResult match(FindInParam param) throws MException {
        if (null == param)
            throw new MException("the params not is null");

        this.param = param;

        // 解析模板文件
        final byte[] templateImageData = readTemplateData(this.param.getTemplateImagePath());
        // 调用查找
        int[] find = ImageUtil.find(this.param.getScreenBytes(), param.getStartX(), param.getStartY(),
                param.getWidth(), param.getHeight(), templateImageData, this.param.getTemplateStartX(),
                this.param.getTemplateStartY(), this.param.getTemplateWidth(), this.param.getTemplateHeight(),
                this.param.getColorToleranceAsPercent());
        final FindInResult result = new FindInResult();
        if (null != find) {
            result.setMatched(true);
            result.setFind(find);
        }
        return result;
    }

    /**
     * read templateImageData
     * 
     * @param path
     * @return
     * @throws MException
     */
    public static byte[] readTemplateData(String path) throws MException {
        try {
            final byte[] templateImageData = FileUtil.readFile(path);
            return templateImageData;
        } catch (IOException e) {
            throw new MException("read templateImageData fail,the path:" + path);
        }
    }
}
