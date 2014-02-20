/**
 * @(#) ServiceIndexRelationDao.java Created on 2012-6-12
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.servicekey.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.aspire.android.common.db.BaseDao;
import com.aspire.android.common.db.DatabaseHelper;
import com.aspire.android.common.db.DatabaseManager;
import com.aspire.android.common.exception.ExceptionHandler;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.Constants;
import com.aspire.android.test.servicekey.entity.AtsServiceIndexRelation;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

/**
 * The class <code>ServiceIndexRelationDao</code>
 * 
 * @author gouanming
 * @version 1.0
 */
@Singleton
public class ServiceIndexRelationDao extends BaseDao<AtsServiceIndexRelation, Long> implements IServiceIndexRelationDao {
    /**
     * Constructor
     * 
     * @param databaseHelper
     * @throws MException
     */
    public ServiceIndexRelationDao() throws MException {
        super(DatabaseManager.getDBHelper(), Constants.ATS_SERVICE_INDEX, AtsServiceIndexRelation.class);
    }

    /**
     * Constructor
     * 
     * @param databaseHelper
     * @throws MException
     */
    @Inject
    public ServiceIndexRelationDao(DatabaseHelper databaseHelper) throws MException {
        super(databaseHelper, Constants.ATS_SERVICE_INDEX, AtsServiceIndexRelation.class);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.servicekey.dao.IServiceIndexRelationDao#findAtsServiceIndexRelation(java.lang.String,
     *      java.lang.String)
     */
    public AtsServiceIndexRelation findAtsServiceIndexRelation(String serviceCode, String testKeyCode)
            throws MException {

        HashMap<String, Object> simpledataMap = new HashMap<String, Object>();
        simpledataMap.put("SERVICECODE", serviceCode);
        simpledataMap.put("TESTKEYCODE", testKeyCode);
        List<AtsServiceIndexRelation> list = null;
        AtsServiceIndexRelation mAtsServiceIndexRelation = null;
        try {
            list = this.dao.queryForFieldValues(simpledataMap);
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "findAtsServiceIndexRelation");
            if (mexception != null) {
                throw mexception;
            }
        }
        if (list != null && list.size() > 0) {
            mAtsServiceIndexRelation = list.get(0);
        }
        return mAtsServiceIndexRelation;
    }

    public List<AtsServiceIndexRelation> findAtsServiceIndexRelation(String testKeyName) throws MException {
        try {
            final QueryBuilder<AtsServiceIndexRelation, Long> builder = this.dao.queryBuilder();
            final Where<AtsServiceIndexRelation, Long> where = builder.where();
            where.like("TESTKEYNAME", "%" + testKeyName.trim() + "%");
            return builder.query();
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error findLsitAtsServiceIndexRelation");
            if (mexception != null) {
                throw mexception;
            }
        }

        return null;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.servicekey.dao.IServiceIndexRelationDao#findLsitAtsServiceIndexRelation(java.lang.String,
     *      java.lang.String)
     */
    public List<AtsServiceIndexRelation> findLsitAtsServiceIndexRelation(String serviceName, String testKeyName)
            throws MException {
        try {
            final QueryBuilder<AtsServiceIndexRelation, Long> builder = this.dao.queryBuilder();
            final Where<AtsServiceIndexRelation, Long> where = builder.where();
            where.like("SERVICNAME", "%" + serviceName.trim() + "%").and()
                    .like("TESTKEYNAME", "%" + testKeyName.trim() + "%");
            return builder.query();
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error findLsitAtsServiceIndexRelation");
            if (mexception != null) {
                throw mexception;
            }
        }

        return null;
    }

    public List<AtsServiceIndexRelation> findAllAtsServiceIndexRelation() throws MException {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error findAllAtsServiceIndexRelation");
            if (mexception != null) {
                throw mexception;
            }
        }
        return null;
    }
}
