/**
 * @(#) TestLogDao.java Created on 2012-5-22
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.script.dao;

import com.aspire.android.common.db.IBaseDao;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.script.entity.TestLog;

/**
 * The class <code>TestLogDao</code>
 *
 * @author gouanmig
 * @version 1.0

 */
public interface ITestLogDao extends IBaseDao<TestLog, Long> {
    
    public String findTestLogMAXid() throws MException;
    
    public Long findScriptid(Long taskItemBatchId)throws MException;

}
