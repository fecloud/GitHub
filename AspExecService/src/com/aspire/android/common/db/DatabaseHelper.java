/**
 * @(#) DatabaseHelper.java Created on 2012-4-11
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.common.db;

import java.util.HashMap;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * The class <code>DatabaseHelper</code>
 *
 * @author linjunsui
 * @version 1.0
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    
    /**
     * database name
     */
    protected String databaseName;
    
    /**
     * daos
     */
    protected HashMap<String, IBaseDao<?, ?>> tableNameDaoMap = new HashMap<String, IBaseDao<?, ?>>();
    
    /**
     * class name 
     */
    protected HashMap<String, String> entityToTableNameMap = new HashMap<String, String>();

    /**
     * @param context
     * @param name
     * @param factory
     * @param version
     * @param databaseName
     * @param tableName
     * @param columns
     */
    public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.databaseName = name;
    }

    /**
     * @return the databaseName
     */
    public String getDatabaseName() {
        return databaseName;
    }
    
    /**
     * get Dao by Entity
     * @param clazz entity class
     * @return
     */
    public <T> IBaseDao<?, ?> getDaoByEntity(Class<T> clazz) {
        if (entityToTableNameMap.containsKey(clazz.getName())) {
            return tableNameDaoMap.get(entityToTableNameMap.get(clazz.getName()));
        }

        return null;
    }

    /**
     * get a dao
     * @param tableName
     * @return BaseDao
     */
    public IBaseDao<?, ?> getDao(String tableName) {
        if (tableNameDaoMap.containsKey(tableName)) {
            return tableNameDaoMap.get(tableName);
        }
        return null;
    }

    /**
     * add a dao
     * @param dao
     */
    public void addDao(IBaseDao<?, ?> dao) {
        if (dao != null) {
            tableNameDaoMap.put(dao.getTableName(), dao);
            entityToTableNameMap.put(dao.getEntityClassName(), dao.getTableName());
        }
    }
    
    /**
     * remove dao
     * @param tableName
     * @param dao
     */
    public void removeDao(IBaseDao<?, ?> dao) {
        tableNameDaoMap.remove(dao.getTableName());
        entityToTableNameMap.remove(dao.getTableName());
    }
    
    /**
     * clear Daos
     */
    public void clearDaos() {
        tableNameDaoMap.clear();
        entityToTableNameMap.clear();
    }

    /**
     * executeSQL
     * @param sql
     */
    public void execSQL(String sql) {
        getWritableDatabase().execSQL(sql);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        
    }

}
