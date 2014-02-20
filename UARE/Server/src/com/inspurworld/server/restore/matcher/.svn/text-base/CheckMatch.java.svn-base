/**
 * @(#) CheckMatch.java Created on 2012-8-2
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server.restore.matcher;


/**
 * The class <code>CheckMatch</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public abstract class CheckMatch<P, R> {

    protected P param;

    public CheckMatch(P param) {
        super();
        this.param = param;
    }

    /**
     * 根据参数进行匹配操作,返回结果
     * 
     * @param param
     * @return
     * @return
     * @throws MException
     */
    public abstract R match(P param) throws Exception;

    /**
     * 根据参数进行匹配操作,返回结果
     * 
     * @return
     * @throws MException
     */
    public R match() throws Exception {
        if (null != getParam())
            return match(getParam());
        throw new Exception("the findInParam is null");
    }

    public P getParam() {
        return param;
    }

    public void setParam(P param) {
        this.param = param;
    }

}
