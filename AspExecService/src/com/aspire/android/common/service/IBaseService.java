/**
 * @(#) IBaseService.java Created on 2012-5-9
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.common.service;

import java.util.List;

import com.aspire.android.common.exception.MException;

/**
 * The class <code>IBaseService</code>
 *
 * @author linjunsui
 * @version 1.0
 */
public interface IBaseService<T, ID> {
    /**
     * findById
     * @param orderBy
     * @return
     * @throws MException 
     */
    public T findById(ID id) throws MException;
    
    /**
     * findAll
     * @return
     * @throws MException 
     */
    public List<T> findAll() throws MException;
    
    /**
     * findAll with orderBy
     * @param orderBy
     * @return
     * @throws MException 
     */
    public List<T> findAll(String orderBy) throws MException;
    
    /**
     * detele 
     * @param entity
     * @throws MException 
     */
    public void detele(T entity) throws MException;
    
    /**
     * delete by id
     * @param id
     * @throws MException 
     */
    public void deteleById(ID id) throws MException;
    
    /**
     * insert
     * @param entity
     * @return
     * @throws MException 
     */
    public T insert(T entity) throws MException;
    
    /**
     * update 
     * @param entity
     * @return
     * @throws MException 
     */
    public T update(T entity) throws MException;
}
