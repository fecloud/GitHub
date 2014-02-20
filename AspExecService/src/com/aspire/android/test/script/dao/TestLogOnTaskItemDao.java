/**
 * @(#) TestLogOnTaskItemDao.java Created on 2012-6-12
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
import com.aspire.android.test.script.entity.TestLogOnTaskItem;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.j256.ormlite.dao.GenericRawResults;

/**
 * The class <code>TestLogOnTaskItemDao</code>
 *
 * @author gouanming
 * @version 1.0
 */
@Singleton
public class TestLogOnTaskItemDao  extends BaseDao<TestLogOnTaskItem, Long> implements ITestLogOnTaskItemDao {


    /**
     * Constructor
     * 
     * @param databaseHelper
     * @throws MException
     */
    public TestLogOnTaskItemDao() throws MException {
        super(DatabaseManager.getDBHelper(), Constants.ATS_TASKITEM_TESTLOG, TestLogOnTaskItem.class);
    }

    /**
     * Constructor
     * 
     * @param databaseHelper
     * @throws MException
     */
    @Inject
    public TestLogOnTaskItemDao(DatabaseHelper databaseHelper) throws MException {
        super(databaseHelper, Constants.ATS_TASKITEM_TESTLOG, TestLogOnTaskItem.class);
    }

    /**
     * (non-Javadoc)
     * @see com.aspire.android.test.script.dao.ITestLogOnTaskItemDao#getTestLogID(long)
     */
    public String getTestLogID(Long itemBatchId) throws MException {
        try {
            String sql="select test_log_ID from ats_taskitem_testlog where item_batch_ID = ?";
            GenericRawResults<String[]> generic=this.dao.queryRaw(sql, new String[]{itemBatchId.toString()});
            List<String[]> allList=generic.getResults();
            for (String[] strings : allList) {
                for (int i = 0; i < strings.length;) {
                    return strings[i];
                }
            }
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error getTestLogID() ");
            if (mexception != null) {
                throw mexception;
            }
        }
        return null;
    }

   
}
