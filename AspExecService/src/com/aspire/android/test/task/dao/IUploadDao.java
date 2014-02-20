/**
 * @(#) IUploadDao.java Created on 2012-6-27
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.task.dao;

import java.util.List;

import com.aspire.android.common.db.IBaseDao;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.task.entity.Upload;

/**
 * The class <code>IUploadDao</code>
 * 
 * @author Administrator
 * @version 1.0
 */
public interface IUploadDao extends IBaseDao<Upload, Long> {

    /**
     * @param upstatus
     * @return
     */
    List<Upload> listUploads(String upstatus) throws MException;

    /**
     * @param upstatus
     * @return
     */
    List<Upload> listUploads(String upstatus,String resp) throws MException;
    
    public List<Upload> getUpload(int status)throws MException;

}
