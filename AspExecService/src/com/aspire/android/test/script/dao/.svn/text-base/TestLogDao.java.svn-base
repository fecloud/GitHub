/**
 * @(#) TestLogDaoImpl.java Created on 2012-5-22
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.script.dao;


import java.sql.SQLException;
import java.util.List;

import com.aspire.android.common.db.BaseDao;
import com.aspire.android.common.db.DatabaseHelper;
import com.aspire.android.common.db.DatabaseManager;
import com.aspire.android.common.exception.ExceptionHandler;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.Constants;
import com.aspire.android.test.script.entity.TestLog;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.j256.ormlite.dao.GenericRawResults;

/**
 * The class <code>TestLogDaoImpl</code>
 *
 * @author gouanming
 * @version 1.0

 */
@Singleton
public class TestLogDao extends BaseDao<TestLog, Long> implements ITestLogDao {
    /**
     * Constructor
     * @param databaseHelper
     * @throws MException 
     */
    public TestLogDao() throws MException {
        super(DatabaseManager.getDBHelper(), Constants.ATS_TEST_LOG, TestLog.class);
    }
    /**
     * Constructor
     * @param databaseHelper
     * @throws MException 
     */
    @Inject
    public TestLogDao(DatabaseHelper databaseHelper) throws MException {
        super(databaseHelper, Constants.ATS_TEST_LOG, TestLog.class);
    }
    /**
     * (non-Javadoc)
     * @see com.aspire.android.test.script.dao.ITestLogDao#findTestLogMAXid()
     */
    public String findTestLogMAXid() throws MException {
        try {
            String sql="select max(id) from ats_test_log";
            GenericRawResults<String[]> generic=this.dao.queryRaw(sql, new String[]{});
            List<String[]> allList=generic.getResults();
            for (String[] strings : allList) {
                for (int i = 0; i < strings.length;) {
                    return strings[i];
                }
            }
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error findMAXID() ");
            if (mexception != null) {
                throw mexception;
            }
        }
        return null;
    }
    /**
     * (non-Javadoc)
     * @see com.aspire.android.test.script.dao.ITestLogDao#findScriptid(java.lang.Long)
     */
    public Long findScriptid(Long taskItemBatchId) throws MException {
        try {
            String sql="select SCRIPT_ID from ats_test_log where TESKITENBATCH_ID = ?";
            GenericRawResults<String[]> generic=this.dao.queryRaw(sql, new String[]{taskItemBatchId.toString()});
            List<String[]> allList=generic.getResults();
            for (String[] strings : allList) {
                for (int i = 0; i < strings.length;) {
                    return Long.valueOf(strings[i]);
                }
            }
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "findScriptid(Long taskItemBatchId)");
            if (mexception != null) {
                throw mexception;
            }
        }
        return null;
    }
  
}
