/**
 * @(#) CaseTestkeycodeDao.java Created on 2012-7-4
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.script.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.aspire.android.common.db.BaseDao;
import com.aspire.android.common.db.DatabaseHelper;
import com.aspire.android.common.db.DatabaseManager;
import com.aspire.android.common.exception.ExceptionHandler;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.Constants;
import com.aspire.android.test.script.entity.CaseTestkeycode;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * The class <code>CaseTestkeycodeDao</code>
 * 
 * @author Administrator
 * @version 1.0
 */
@Singleton
public class CaseTestkeycodeDao extends BaseDao<CaseTestkeycode, Long> implements ICaseTestkeycodeDao {

    /**
     * Constructor
     * 
     * @param databaseHelper
     * @throws MException
     */
    public CaseTestkeycodeDao() throws MException {
        super(DatabaseManager.getDBHelper(), Constants.ATS__CASE_TESTKEYCODE, CaseTestkeycode.class);
    }
    /**
     * Constructor
     * 
     * @param databaseHelper
     * @throws MException
     */
    @Inject
    public CaseTestkeycodeDao(DatabaseHelper databaseHelper) throws MException {
        super(databaseHelper, Constants.ATS__CASE_TESTKEYCODE, CaseTestkeycode.class);
    }

    public List<CaseTestkeycode> getCaseTestkeycode(String testServType, String testkeycode) throws MException {
        HashMap<String, Object> simpledataMap = new HashMap<String, Object>();
        simpledataMap.put("testServType", testServType);
        simpledataMap.put("testkeycode", testkeycode);
        List<CaseTestkeycode> listTaskBatch = null;
        try {
            listTaskBatch = this.dao.queryForFieldValues(simpledataMap);
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e,
                    "Error getCaseTestkeycode(String testServType,String testkeycode)");
            if (mexception != null) {
                throw mexception;
            }
        }
        return listTaskBatch;
    }
    
    public List<CaseTestkeycode> findScriptServiceIndex(Long caseId)throws MException{
        HashMap<String, Object> simpledataMap = new HashMap<String, Object>();
        simpledataMap.put("CASE_ID", caseId);
        List<CaseTestkeycode> listTaskBatch = null;
        try {
            listTaskBatch = this.dao.queryForFieldValues(simpledataMap);
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e,
                    "Error findScriptServiceIndex(Long caseId))");
            if (mexception != null) {
                throw mexception;
            }
        }
        return listTaskBatch;
    }
}
