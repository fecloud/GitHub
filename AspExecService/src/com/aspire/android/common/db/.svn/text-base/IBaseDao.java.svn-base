/**
 * @(#) IBaseDao.java Created on 2012-4-19
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.common.db;

import java.util.List;

import com.aspire.android.common.exception.MException;

/**
 * The class <code>IBaseDao</code>
 *
 * @author linjunsui
 * @version 1.0
 * @param <T> Template Type
 * @param <ID> ID Type
 */
public interface IBaseDao<T, ID> {

    /**
     * @return the tableName
     */
    public String getTableName();
    
    /**
     * @return the EntityClassName
     */
    public String getEntityClassName();
    
    /**
     * findById
     * @param id Id as ID Type
     * @return Entity as T
     * @throws SQLException 
     */
    public T findById(ID id) throws MException;
    
    /**
     * findAll
     * @return List of T
     * @throws SQLException 
     */
    public List<T> findAll() throws MException;
    
    /**
     * findAll with orderBy
     * @param orderBy Order by clause
     * @return List of T
     * @throws SQLException 
     */
    public List<T> findAll(String orderBy) throws MException;
    
    /**
     * delete 
     * @param entity Entity to be deleted
     * @throws SQLException 
     */
    public void detele(T entity) throws MException;
    
    /**
     * delete by id
     * @param id Id of entity to be deleted
     * @throws SQLException 
     */
    public void deteleById(ID id) throws MException;
    
    /**
     * insert
     * @param entity Entity to be inserted
     * @return Entity inserted
     * @throws SQLException 
     */
    public T insert(T entity) throws MException;
    
    /**
     * update 
     * @param entity Entity to be updated
     * @return Entity updated
     * @throws SQLException 
     */
    public T update(T entity) throws MException;
    

}
