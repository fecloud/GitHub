/**
 * @(#) RestoreCenter.java Created on 2012-10-28
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server.restore;

import java.util.List;

import org.apache.log4j.Logger;

import com.inspurworld.server.restore.matcher.CheckMatch;
import com.inspurworld.server.restore.matcher.CheckParam;
import com.inspurworld.server.restore.matcher.CheckResult;
import com.inspurworld.server.restore.matcher.activity.ActivityMatch;
import com.inspurworld.server.restore.matcher.activity.ActivityParam;
import com.inspurworld.server.restore.matcher.activity.ActivityResult;
import com.inspurworld.server.restore.matcher.ocr.OCRMatch;
import com.inspurworld.server.restore.matcher.ocr.OCRParam;
import com.inspurworld.server.restore.matcher.verify.VerifyMatch;
import com.inspurworld.server.restore.matcher.verify.VerifyParam;

/**
 * The class <code>RestoreCenter</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public final class RestoreCenter {

    private Logger logger = Logger.getLogger(getClass());

    private CheckParam param;

    private CheckMatch<? extends CheckParam, ? extends CheckResult> match;

    private CheckResult result;

    private static RestoreLoader restoreLoader;

    private String inputFile;

    public RestoreCenter() {
        restoreLoader = RestoreLoader.getInstance();
    }

    public RestoreCenter(String inputFile) {
        this.inputFile = inputFile;
        restoreLoader = RestoreLoader.getInstance();
    }

    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    /**
     * 还原核心
     * 
     * @return
     */
    public boolean doRestore() {
        // TODO
         logger.info("start restore file " + inputFile);
        String restoreAction = null;
        try {
            restoreAction = restore();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (null != restoreAction) {
            logger.info(restoreAction);
        }
        return true;
    }

    private String restore() throws Exception {
        param = new ActivityParam(inputFile);
        match = new ActivityMatch((ActivityParam) param);
        result = match.match();
        final ActivityResult activityResult = (ActivityResult) result;
        if (activityResult.isMatched()) {
            logger.info("activity matcher sucess");
            return restoreLoader.find(((ActivityParam) param).getActivityName()); // 根据Activity查找到
        }

        // 如果匹配到母版有多张,则要用到图片比较
        final List<String> list = activityResult.getList();
        for (String templatePath : list) {
            param = new VerifyParam(inputFile, templatePath);
            match = new VerifyMatch((VerifyParam) param);
            result = match.match();
            if (result.isMatched()) {
                logger.info("verify matcher sucess");
                return restoreLoader.find(((VerifyParam) param).getVerifyPropertiesKey()); // 根据Verify查找到
            }
        }

        // 如果图片没有比对上
        param = new OCRParam(inputFile);
        match = new OCRMatch((OCRParam) param);
        result = match.match();
        if (result.isMatched()) {
            logger.info("ocr matcher sucess");
           return restoreLoader.find(((OCRParam) param).getActivityName());// 根据OCR查找到
        }
        return null;
    }

}
