/**
 * @(#) TestDatabaseHelper.java Created on 2012-5-9
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.aspire.android.common.db.DatabaseHelper;
import com.aspire.android.common.exception.ExceptionHandler;
import com.aspire.android.test.script.entity.AtsScript;
import com.aspire.android.test.script.entity.CaseTestkeycode;
import com.aspire.android.test.script.entity.ScriptCase;
import com.aspire.android.test.script.entity.TestLog;
import com.aspire.android.test.script.entity.TestLogOnTaskItem;
import com.aspire.android.test.servicekey.entity.AtsServiceIndexRelation;
import com.aspire.android.test.task.entity.Task;
import com.aspire.android.test.task.entity.TaskBatch;
import com.aspire.android.test.task.entity.TaskItem;
import com.aspire.android.test.task.entity.TaskItemBatch;
import com.aspire.android.test.task.entity.Upload;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * The class <code>TestDatabaseHelper</code>
 * 
 * @author linjunsui
 * @version 1.0
 */
@Singleton
public class TestDatabaseHelper extends DatabaseHelper {

    /**
     * Constructor
     * 
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public TestDatabaseHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    
    /**
     * Constructor
     * @param context context
     * @param name name
     * @param version version
     */
    @Inject
    public TestDatabaseHelper(Context context, @Named("DatabaseName") String name,
            @Named("DatabaseVersion") int version) {
        super(context, name, null, version);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.common.db.DatabaseHelper#onCreate(android.database.sqlite.SQLiteDatabase,
     *      com.j256.ormlite.support.ConnectionSource)
     */
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Task.class);
            TableUtils.createTableIfNotExists(connectionSource, TaskItem.class);
            TableUtils.createTableIfNotExists(connectionSource, TaskBatch.class);
            TableUtils.createTableIfNotExists(connectionSource, TaskItemBatch.class);
            TableUtils.createTableIfNotExists(connectionSource, TestLog.class);
            TableUtils.createTableIfNotExists(connectionSource, TestLogOnTaskItem.class);
            TableUtils.createTableIfNotExists(connectionSource, AtsScript.class);
            TableUtils.createTableIfNotExists(connectionSource, AtsServiceIndexRelation.class);
            TableUtils.createTableIfNotExists(connectionSource, ScriptCase.class);
            TableUtils.createTableIfNotExists(connectionSource, Upload.class);
            TableUtils.createTableIfNotExists(connectionSource, CaseTestkeycode.class);
        } catch (SQLException e) {
            ExceptionHandler.handle(e, "Error create Database");
        }
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.common.db.DatabaseHelper#onUpgrade(android.database.sqlite.SQLiteDatabase,
     *      com.j256.ormlite.support.ConnectionSource, int, int)
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        super.onUpgrade(database, connectionSource, oldVersion, newVersion);
    }

}
