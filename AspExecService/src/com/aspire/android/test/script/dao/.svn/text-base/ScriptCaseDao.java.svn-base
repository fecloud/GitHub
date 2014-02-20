/**
 * @(#) ScriptCaseDao.java Created on 2012-6-14
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
import com.aspire.android.test.script.entity.ScriptCase;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * The class <code>ScriptCaseDao</code>
 *
 * @author Administrator
 * @version 1.0
 */
@Singleton
public class ScriptCaseDao extends BaseDao<ScriptCase, Long> implements IScriptCaseDao {
    /**
     * Constructor
     * 
     * @param databaseHelper
     * @throws MException
     */
    public ScriptCaseDao() throws MException {
        super(DatabaseManager.getDBHelper(), Constants.ATS_script_CASE, ScriptCase.class);
    }
    /**
     * Constructor
     * 
     * @param databaseHelper
     * @throws MException
     */
    @Inject
    public ScriptCaseDao(DatabaseHelper databaseHelper) throws MException {
        super(databaseHelper, Constants.ATS_script_CASE, ScriptCase.class);
    }
    /**
     * (non-Javadoc)
     * @see com.aspire.android.test.script.dao.IScriptCaseDao#findScriptCases(java.lang.Long)
     */
    public List<ScriptCase> findScriptCases(Long scrpitID) throws MException {
        HashMap<String, Object> simpledataMap = new HashMap<String, Object>();
        simpledataMap.put("SCRIPT_ID", scrpitID);
        try {
            return this.dao.queryForFieldValues(simpledataMap);
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error findScriptCases");
            if (mexception != null) {
                throw mexception;
            }
        }
        return null;
    }
    
    public ScriptCase findScriptCase(String casePath) throws MException{
        HashMap<String, Object> simpledataMap = new HashMap<String, Object>();
        simpledataMap.put("CASEPATH", casePath);
        try {
            List<ScriptCase>  list = this.dao.queryForFieldValues(simpledataMap);
            for (ScriptCase scriptCase : list) {
                return scriptCase;
            }
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error findScriptCases");
            if (mexception != null) {
                throw mexception;
            }
        }
        return null;
    }
}
