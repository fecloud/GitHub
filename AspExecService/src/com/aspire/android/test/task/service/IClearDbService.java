/**
 * @(#) IClearDbService.java Created on 2012-8-30
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.task.service;

import com.aspire.android.common.exception.MException;

/**
 * 定期清理数据库
 * The class <code>IClearDbService</code>
 *
 * @author gouanming
 * @version 1.0
 */
public interface IClearDbService {
    
    /**
     * 任务数据库数据进行清理。dayNum 天以前的数据清理
     * @param dayNum 天数
     * @throws MException
     */
    public void TaskCear(long dayNum)throws MException ;
    
    /**
     * 数据库拨测结果进行清理。dayNum 天以前的数据清理
     * @param dayNum  天数
     * @throws MException
     */
    public void TestResultCear(long dayNum)throws MException ;

    
    /**
     * 上报结果包 / 结果确认信息同步包进行清理。dayNum 天以前的数据清理
     * @param dayNum 天数
     * @throws MException
     */
    public void upResultCear(long dayNum)throws MException ;

}
