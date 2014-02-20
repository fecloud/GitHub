/**
 * @(#) ActivityParam.java Created on 2012-10-26
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server.restore.matcher.activity;

import java.io.File;

import com.inspurworld.server.restore.matcher.CheckParam;

/**
 * The class <code>ActivityParam</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class ActivityParam extends CheckParam {

    private String filename;

    /**
     * Constructor
     * 
     * @param filename
     */
    public ActivityParam(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFile() {
        return filename.substring(filename.lastIndexOf(File.separator) + 1);
    }

    /**
     * 取上传来的图片的Activity名,客户端上传来时,文件名为Activity名_时间戳
     * 
     * @return
     */
    public String getActivityName() {
        final String file = getFile();
        if (null != file && !"".equals(file)) {
            final String[] strings = file.split("_");
            if (null != strings && strings.length > 0) {
                return strings[0];
            }
        }
        return null;
    }

}
