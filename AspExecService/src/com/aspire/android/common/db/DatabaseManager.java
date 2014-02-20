/**
 * @(#) DatabaseManager.java Created on 2012-4-12
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.common.db;

import java.util.HashMap;

/**
 * The class <code>DatabaseManager</code>
 *
 * @author linjunsui
 * @version 1.0
 */
public final class DatabaseManager {
    
    /**
     * MEMORY_DATABASE
     */
    public static final String MEMORY_DATABASE = DatabaseManager.class.getCanonicalName() + ".memorydatabase";
    
    /**
     * database Map
     */
    protected static HashMap<String, DatabaseHelper> databaseHelperMap = new HashMap<String, DatabaseHelper>();
    
    /**
     * default database
     */
    public static String defaultDatabase = MEMORY_DATABASE;

    /**
     * Hide Constructor
     */
    private DatabaseManager() {
    }
    
    /**
     * set default Database
     * @param databaseName
     */
    public static void setDefaultDatabase(String databaseName) {
        defaultDatabase = databaseName;
    }
    
    /**
     * get a database Helper
     * @param databaseHelper DatabaseHelper to be added
     */
    public static void addDatabaseHelper(DatabaseHelper databaseHelper) {
        if (databaseHelper != null) {
            if (databaseHelper.getDatabaseName() == null) {
                databaseHelperMap.put(MEMORY_DATABASE, databaseHelper);
            } else {
                databaseHelperMap.put(databaseHelper.getDatabaseName(), databaseHelper);
            }
        }
    }
    
    /**
     * get default database Helper
     * @return Default DatabaseHelper
     */
    public static DatabaseHelper getDBHelper() {
        return databaseHelperMap.get(defaultDatabase);
    }
    
    /**
     * get a database Helper
     * @param databaseName Name of database
     * @return DatabaseHelper
     */
    public static DatabaseHelper getDBHelper(String databaseName) {
        if (databaseName == null) {
            return databaseHelperMap.get(MEMORY_DATABASE);
        }
        return databaseHelperMap.get(databaseName);
    }
    
    /**
     * get a dao
     * @param tableName Tablename of DAO
     * @return IBaseDao
     */
    public static IBaseDao<?, ?> getDao(String tableName) {
        return getDao(defaultDatabase, tableName);
    }
    
    /**
     * get a dao
     * @param databaseName DatabaseName of DAO
     * @param tableName TableName of DAO
     * @return IBaseDao
     */
    public static IBaseDao<?, ?> getDao(String databaseName, String tableName) {
        if (!databaseHelperMap.containsKey(databaseName)) {
            return null;
        }
        return databaseHelperMap.get(databaseName).getDao(tableName);
    }
    
    /**
     * get a DAO
     * @param <T> Template type
     * @param clazz Entity class of DAO
     * @return IBaseDao
     */
    public static <T> IBaseDao<?, ?> getDao(Class<T> clazz) {
        return getDao(defaultDatabase, clazz);
    }
    
    /**
     * get a DAO
     * @param <T> Template type
     * @param databaseName DatabaseName of DAO
     * @param clazz Entity class of DAO
     * @return IBaseDao
     */
    public static <T> IBaseDao<?, ?> getDao(String databaseName, Class<T> clazz) {
        if (!databaseHelperMap.containsKey(databaseName)) {
            return null;
        }
        return databaseHelperMap.get(databaseName).getDaoByEntity(clazz);
    }
}
