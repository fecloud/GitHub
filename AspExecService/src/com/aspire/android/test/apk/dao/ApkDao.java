/**

 * @(#) ApkDaoImpl.java Created on 2012-6-5

 *

 * Copyright (c) 2012 Aspire. All Rights Reserved

 */
package com.aspire.android.test.apk.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.aspire.android.common.db.BaseDao;
import com.aspire.android.common.db.DatabaseHelper;
import com.aspire.android.common.db.DatabaseManager;
import com.aspire.android.common.exception.ExceptionHandler;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.Constants;
import com.aspire.android.test.apk.entity.Apk;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * 
 * The class <code>ApkDaoImpl</code>
 * 
 * 
 * 
 * @author gouanming
 * 
 * @version 1.0
 */
@Singleton
public class ApkDao extends BaseDao<Apk, Long> implements IApkDao {
    /**
     * Constructor
     * 
     * @param databaseHelper
     * @throws MException
     */
    public ApkDao() throws MException {
        super(DatabaseManager.getDBHelper(), Constants.ATS_APK, Apk.class);
    }
    /**
     * Constructor
     * 
     * @param databaseHelper
     * @throws MException
     */
    @Inject
    public ApkDao(DatabaseHelper databaseHelper) throws MException {
        super(databaseHelper, Constants.ATS_APK, Apk.class);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.apk.dao.IApkDao#getApk(java.lang.String)
     */
    public Apk getApk(String zipName) throws MException {
        HashMap<String, Object> simpledataMap = new HashMap<String, Object>();
        simpledataMap.put("dec", zipName);
        List<Apk> list = null;
        try {
            list = this.dao.queryForFieldValues(simpledataMap);
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error fineAll APK");
            if (mexception != null) {
                throw mexception;
            }
        }
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * (non-Javadoc)
     * @see com.aspire.android.test.apk.dao.IApkDao#findApk(java.lang.Long)
     */
    public Apk findApk(Long scriptId) throws MException {
        HashMap<String, Object> simpledataMap = new HashMap<String, Object>();
        simpledataMap.put("SCRIPT_ID", scriptId);
        List<Apk> list = null;
        try {
            list = this.dao.queryForFieldValues(simpledataMap);
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error findApk");
            if (mexception != null) {
                throw mexception;
            }
        }
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
