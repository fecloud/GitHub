/**
 * @(#) FindInComMatch.java Created on 2012-8-28
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.screen.check.find.com;

import com.aspire.android.common.exception.MException;
import com.aspire.android.image.ImageUtil;
import com.aspire.android.screen.check.CheckMatch;
import com.aspire.android.screen.check.find.FindInMatch;
import com.aspire.android.screen.check.find.FindInResult;

/**
 * The class <code>FindInComMatch</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class FindInComMatch extends CheckMatch<FindInComParam, FindInResult> {

    public FindInComMatch() {
        super(null);
    }

    public FindInComMatch(FindInComParam param) {
        super(param);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.screen.check.CheckMatch#match(java.lang.Object)
     */
    @Override
    public FindInResult match(FindInComParam param) throws MException {
        if (null == param)
            throw new MException("the params not is null");

        this.param = param;

        // 解析模板文件
        final byte[] templateImageData = FindInMatch.readTemplateData(this.param.getTemplateImagePath());
        // 调用查找
        int[] find = ImageUtil.find(this.param.getScreenBytes(), param.getStartX(), param.getStartY(),
                param.getWidth(), param.getHeight(), templateImageData, this.param.getTemplateStartX(),
                this.param.getTemplateStartY(), this.param.getTemplateWidth(), this.param.getTemplateHeight(),
                this.param.getColorToleranceAsPercent(), this.param.getxStep(), this.param.getyStep());
        
        final FindInResult result = new FindInResult();
        if (null != find) {
            result.setMatched(true);
            result.setFind(find);
        }
        return result;
    }

}
