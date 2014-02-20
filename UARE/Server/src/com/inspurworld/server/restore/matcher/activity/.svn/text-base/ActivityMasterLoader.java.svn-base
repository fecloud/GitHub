package com.inspurworld.server.restore.matcher.activity;

/**
 * @(#) ActivityMasterLoader.java Created on 2012-10-26
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.inspurworld.server.config.Config;
import com.inspurworld.server.util.FileUtil;

/**
 * The class <code>ActivityMasterLoader</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class ActivityMasterLoader {

    private Logger logger = Logger.getLogger(getClass());

    private List<String> list;

    private static ActivityMasterLoader instances;

    private ActivityMasterLoader() {
        logger.debug("ActivityMasterLoader");
        this.list = FileUtil.getDirFileName(Config.MASTER_DIR);
    }

    public static ActivityMasterLoader getInstance() {
        if (null == instances) {
            instances = new ActivityMasterLoader();
        }
        return instances;
    }

    public List<String> getActivityList() {
        return list;
    }

    /**
     * 查找包含
     * 
     * @param param
     * @return
     */
    public List<String> find(String param) {
        if (null == param || "".equals(param.trim()))
            return null;

        final List<String> strings = new ArrayList<String>();
        for (String str : this.list) {
            if (str.contains(param))
                strings.add(str);
        }
        return strings;
    }

}
