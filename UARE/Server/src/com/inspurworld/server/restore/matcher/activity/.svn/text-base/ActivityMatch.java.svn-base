/**
 * @(#) ActivityMatch.java Created on 2012-10-26
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server.restore.matcher.activity;

import java.util.List;

import com.inspurworld.server.restore.matcher.CheckMatch;

/**
 * The class <code>ActivityMatch</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class ActivityMatch extends CheckMatch<ActivityParam, ActivityResult> {

    private ActivityMasterLoader activityMasterLoader;

    /**
     * Constructor
     * 
     * @param param
     */
    public ActivityMatch(ActivityParam param) {
        super(param);
        activityMasterLoader = ActivityMasterLoader.getInstance();
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.inspurworld.server.restore.matcher.CheckMatch#match(java.lang.Object)
     */
    @Override
    public ActivityResult match(ActivityParam param) throws Exception {
        this.param = param;
        final ActivityResult result = new ActivityResult();
        final List<String> strings = activityMasterLoader.find(param.getActivityName());
        result.setList(strings);
        if (null != strings && strings.size() == 1) {
            result.setMatched(true);
        }
        return result;
    }

}
