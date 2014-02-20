/**
 * @(#) ScriptDao.java Created on 2012-6-12
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.script.dao;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import android.util.Log;

import com.aspire.android.common.db.BaseDao;
import com.aspire.android.common.db.DatabaseHelper;
import com.aspire.android.common.db.DatabaseManager;
import com.aspire.android.common.exception.ExceptionHandler;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.Constants;
import com.aspire.android.test.script.entity.AtsScript;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

/**
 * The class <code>ScriptDao</code>
 *
 * @author gouanming
 * @version 1.0
 */
@Singleton
public class ScriptDao extends BaseDao<AtsScript, Long> implements IScriptDao{
    /**
     * Constructor
     * 
     * @param databaseHelper
     * @throws MException
     */
    public ScriptDao() throws MException {
        super(DatabaseManager.getDBHelper(), Constants.ATS_script, AtsScript.class);
    }
    /**
     * Constructor
     * 
     * @param databaseHelper
     * @throws MException
     */
    @Inject
    public ScriptDao(DatabaseHelper databaseHelper) throws MException {
        super(databaseHelper, Constants.ATS_script, AtsScript.class);
    }

    /**
     * (non-Javadoc)
     * @see com.aspire.android.test.script.dao.IScriptDao#findMAXScriptID()
     */
    public Long findMAXScriptID() throws MException {
        try {
            String sql="select max(id) from ats_script";
            GenericRawResults<String[]> generic=this.dao.queryRaw(sql, new String[]{});
            List<String[]> allList=generic.getResults();
            for (String[] strings : allList) {
                for (int i = 0; i < strings.length;) {
                    Log.d(getClass().getSimpleName(),"-:-_"+strings[i]);
                    return Long.parseLong(strings[i]);
                }
            }
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error findMAXScriptID");
            if (mexception != null) {
                throw mexception;
            }
        }
        return null;
    }
    public List<AtsScript> findListAtsScript(String scriptName,String taskKeyName)throws MException{
        try {
            final QueryBuilder<AtsScript, Long> builder = this.dao.queryBuilder();
            final Where<AtsScript, Long> where = builder.where();
            where.like("SCRIPTNAME", "%"+scriptName.trim()+"%").and().like("TESTKEYCODE", "%"+taskKeyName.trim()+"%");
            return builder.query();
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error findListAtsScript(String scriptName,String taskKeyCode)");
            if (mexception != null) {
                throw mexception;
            }
        }
        return null;
    }
    
    public List<AtsScript> findListAtsScript(String scriptName)throws MException{
        try {
            final QueryBuilder<AtsScript, Long> builder = this.dao.queryBuilder();
            final Where<AtsScript, Long> where = builder.where();
            where.like("SCRIPTNAME", "%"+scriptName.trim()+"%");
            return builder.query();
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error findListAtsScript(String scriptName)");
            if (mexception != null) {
                throw mexception;
            }
        }
        return null;
    }
    public List<AtsScript> findListAtsScript()throws MException{
        List<AtsScript> mAtsScript =null;
        try {
            mAtsScript= dao.queryForAll();
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "err findAtsScript");
            if (mexception != null) {
                throw mexception;
            }
        }
        return mAtsScript;
    }
    /**
     * (non-Javadoc)
     * @see com.aspire.android.test.script.dao.IScriptDao#findAtsScript(java.lang.String, java.lang.String)
     */
    public AtsScript findAtsScript(String serviceCode, String testKeyCode) throws MException {
        HashMap<String, Object> simpledataMap = new HashMap<String, Object>();
        simpledataMap.put("SERVTYPE", serviceCode);
        simpledataMap.put("TESTKEYCODE", testKeyCode);
        List<AtsScript> list = null;
        AtsScript mAtsScript =null;
        try {
            list = this.dao.queryForFieldValues(simpledataMap);
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "err findAtsScript");
            if (mexception != null) {
                throw mexception;
            }
        }
        if (list != null && list.size() > 0) {
            mAtsScript = list.get(0);
        }
        return mAtsScript;
    }
}
