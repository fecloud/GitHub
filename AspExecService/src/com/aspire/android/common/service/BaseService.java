/**
 * @(#) BaseService.java Created on 2012-5-9
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.common.service;

import java.util.List;

import com.aspire.android.common.db.IBaseDao;
import com.aspire.android.common.exception.MException;

/**
 * The class <code>BaseService</code>
 *
 * @author linjunsui
 * @version 1.0
 */
public class BaseService<T, ID> implements IBaseService<T, ID> {
    
    /**
     * baseDao
     */
    protected IBaseDao<T, ID> baseDao;

    /**
     * Constructor
     */
    public BaseService() {
        super();
    }

    /**
     * Constructor
     * @param baseDao
     */
    public BaseService(IBaseDao<T, ID> baseDao) {
        super();
        this.baseDao = baseDao;
    }

    /**
     * 
     * (non-Javadoc)
     * @see com.aspire.android.common.service.IBaseService#findById(java.lang.Object)
     */
    public T findById(ID id) throws MException {
        return baseDao.findById(id);
    }

    /**
     * (non-Javadoc)
     * @see com.aspire.android.common.service.IBaseService#findAll()
     */
    public List<T> findAll() throws MException {
        return baseDao.findAll();
    }

    /**
     * (non-Javadoc)
     * @see com.aspire.android.common.service.IBaseService#findAll(java.lang.String)
     */
    public List<T> findAll(String orderBy) throws MException {
        return baseDao.findAll();
    }

    /**
     * (non-Javadoc)
     * @see com.aspire.android.common.service.IBaseService#detele(java.lang.Object)
     */
    public void detele(T entity) throws MException {
        baseDao.detele(entity);
        
    }

    /**
     * (non-Javadoc)
     * @see com.aspire.android.common.service.IBaseService#deteleById(java.lang.Object)
     */
    public void deteleById(ID id) throws MException {
        baseDao.deteleById(id);
    }

    /**
     * (non-Javadoc)
     * @see com.aspire.android.common.service.IBaseService#insert(java.lang.Object)
     */
    public T insert(T entity) throws MException {
        return baseDao.insert(entity);
    }

    /**
     * (non-Javadoc)
     * @see com.aspire.android.common.service.IBaseService#update(java.lang.Object)
     */
    public T update(T entity) throws MException {
        return baseDao.update(entity);
    }

}
