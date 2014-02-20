/**
 * @(#) UploadDao.java Created on 2012-6-27
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.task.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.aspire.android.common.db.BaseDao;
import com.aspire.android.common.db.DatabaseHelper;
import com.aspire.android.common.db.DatabaseManager;
import com.aspire.android.common.exception.ExceptionHandler;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.Constants;
import com.aspire.android.test.task.entity.Upload;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * The class <code>UploadDao</code>
 *
 * @author Administrator
 * @version 1.0
 */
@Singleton
public class UploadDao extends BaseDao<Upload,Long> implements IUploadDao{
    /**
     * Constructor
     * 
     * @param databaseHelper
     * @throws MException
     */
    public UploadDao() throws MException {
        super(DatabaseManager.getDBHelper(), Constants.ATS_TASK_UPLOAD, Upload.class);
    }
    /**
     * Constructor
     * 
     * @param databaseHelper
     * @throws MException
     */
    @Inject
    public UploadDao(DatabaseHelper databaseHelper) throws MException {
        super(databaseHelper, Constants.ATS_TASK_UPLOAD, Upload.class);
    }
    /**
     * (non-Javadoc)
     * @see com.aspire.android.test.task.dao.IUploadDao#listUploads(java.lang.String)
     */
    public List<Upload> listUploads(String upstatus) throws MException {
        HashMap<String , Object> simpledataMap = new HashMap<String, Object>();
        simpledataMap.put("upstatus", upstatus);
        List<Upload> list=null;
        try {
            list = this.dao.queryForFieldValues(simpledataMap);
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error listUploads(String upstatus)");
            if (mexception != null) {
                throw mexception;
            }
        }
        return list;
    }
    /**
     * (non-Javadoc)
     * @see com.aspire.android.test.task.dao.IUploadDao#listUploads(java.lang.String)
     */
    public List<Upload> listUploads(String upstatus, String resp) throws MException {
        HashMap<String , Object> simpledataMap = new HashMap<String, Object>();
        simpledataMap.put("upstatus", upstatus);
        simpledataMap.put("respFile", resp);
        List<Upload> list=null;
        try {
            list = this.dao.queryForFieldValues(simpledataMap);
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error listUploads(String upstatus)");
            if (mexception != null) {
                throw mexception;
            }
        }
        return list;
    }
    
    public List<Upload> getUpload(int status)throws MException{
        HashMap<String , Object> simpledataMap = new HashMap<String, Object>();
        if(status==11){
            simpledataMap.put("status", status);
        }else{
            simpledataMap.put("status", status);
        }
        List<Upload> list=null;
        try {
            list = this.dao.queryForFieldValues(simpledataMap);
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error listUploads(String upstatus)");
            if (mexception != null) {
                throw mexception;
            }
        }
        return list;
    }
}
