/**
 * @(#) BaseDao.java Created on 2012-4-19
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.common.db;

import java.sql.SQLException;
import java.util.List;

import com.aspire.android.common.exception.ExceptionHandler;
import com.aspire.android.common.exception.MException;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

/**
 * The class <code>BaseDao</code>
 *
 * @author linjunsui
 * @version 1.0
 */
public class BaseDao<T, ID> implements IBaseDao<T, ID>{
  
    /**
     * table name
     */
    protected String tableName;
    
    /**
     * table name
     */
    protected String entityClassName;
    
    /**
     * databaseHelper
     */
    protected DatabaseHelper databaseHelper;
    
    /**
     * dao
     */
    protected Dao<T, ID> dao;

    /**
     * Constructor
     * @param dao
     * @throws MException 
     */
    public BaseDao(DatabaseHelper databaseHelper, String tableName, Class<T> clazz) throws MException {
        this.databaseHelper = databaseHelper;
        this.tableName = tableName;
        try {
            this.dao = databaseHelper.getDao(clazz);
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error get Dao");
            if (mexception != null) {
                throw mexception;
            }
        }
        this.entityClassName = clazz.getName();
    }
    
    /**
     * @return the tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @param tableName the tableName to set
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    
    /**
     * findAll with orderBy
     * @param orderBy
     * @return
     * @throws MException 
     */
    public List<T> findAll(String orderBy) throws MException{
        QueryBuilder<T, ID> queryBuilder = dao.queryBuilder();
        queryBuilder.orderByRaw(orderBy);
        try {
            return queryBuilder.query();
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error fineAll");
            if (mexception != null) {
                throw mexception;
            }
        }
        return null;
    }

    /**
     * getter
     */
    public String getEntityClassName() {
        return entityClassName;
    }

    /**
     * delete
     */
    public void detele(T entity) throws MException {
        try {
            dao.delete(entity);
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error while delete entity");
            if (mexception != null) {
                throw mexception;
            }
        }
    }

    /**
     * find by id
     */
    public T findById(ID id) throws MException {
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error findById");
            if (mexception != null) {
                throw mexception;
            }
        }
        return null;
    }

    /**
     * findAll
     */
    public List<T> findAll() throws MException {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error while findAll");
            if (mexception != null) {
                throw mexception;
            }
        }
        return null;
    }

    /**
     * delete by id
     * @param id
     */
    public void deteleById(ID id) throws MException {
        try {
            dao.deleteById(id);
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error while deleteById");
            if (mexception != null) {
                throw mexception;
            }
        }
    }

    /**
     * insert
     */
    public T insert(T entity) throws MException {
        try {
            dao.create(entity);
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error while create entity");
            if (mexception != null) {
                throw mexception;
            }
        }
        return entity;
    }

    /**
     * update
     */
    public T update(T entity) throws MException {
        try {
            dao.update(entity);
        } catch (SQLException e) {
            MException mexception = ExceptionHandler.handle(e, "Error while update");
            if (mexception != null) {
                throw mexception;
            }
        }
        return entity;
    }

}
