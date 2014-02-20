/**
 * @(#) ReaderModule.java Created on 2012-8-22
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test;

import android.content.Context;
import android.content.SharedPreferences;

import com.aspire.android.common.db.DatabaseHelper;
import com.aspire.android.test.application.ExecApplication;
import com.aspire.android.test.execute.NameConstants;
import com.aspire.android.test.script.dao.CaseTestkeycodeDao;
import com.aspire.android.test.script.dao.ICaseTestkeycodeDao;
import com.aspire.android.test.script.dao.IScriptCaseDao;
import com.aspire.android.test.script.dao.IScriptDao;
import com.aspire.android.test.script.dao.ITestLogDao;
import com.aspire.android.test.script.dao.ITestLogOnTaskItemDao;
import com.aspire.android.test.script.dao.ScriptCaseDao;
import com.aspire.android.test.script.dao.ScriptDao;
import com.aspire.android.test.script.dao.TestLogDao;
import com.aspire.android.test.script.dao.TestLogOnTaskItemDao;
import com.aspire.android.test.script.service.IScriptService;
import com.aspire.android.test.script.service.ScriptService;
import com.aspire.android.test.servicekey.dao.IServiceIndexRelationDao;
import com.aspire.android.test.servicekey.dao.ServiceIndexRelationDao;
import com.aspire.android.test.servicekey.service.IServiceKeyService;
import com.aspire.android.test.servicekey.service.ServiceKeyService;
import com.aspire.android.test.sync.ISyncService;
import com.aspire.android.test.sync.SyncService;
import com.aspire.android.test.task.dao.ITaskBatchDao;
import com.aspire.android.test.task.dao.ITaskDao;
import com.aspire.android.test.task.dao.ITaskItemBatchDao;
import com.aspire.android.test.task.dao.ITaskItemDao;
import com.aspire.android.test.task.dao.IUploadDao;
import com.aspire.android.test.task.dao.TaskBatchDao;
import com.aspire.android.test.task.dao.TaskDao;
import com.aspire.android.test.task.dao.TaskItemBatchDao;
import com.aspire.android.test.task.dao.TaskItemDao;
import com.aspire.android.test.task.dao.UploadDao;
import com.aspire.android.test.task.service.ClearDbService;
import com.aspire.android.test.task.service.IClearDbService;
import com.aspire.android.test.task.service.ITaskService;
import com.aspire.android.test.task.service.TaskService;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

/**
 * The class <code>ExecuteServiceModule</code>
 *
 * @author linjunsui
 * @version 1.0
 */
public class ExecuteServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        //bind database helper
        SharedPreferences pref = ExecApplication.instance().getBaseContext().getSharedPreferences("type", Context.MODE_PRIVATE);
        int type = pref.getInt(NameConstants.TYPE, NameConstants.TYPE_NORMAL);
        if(type == NameConstants.TYPE_TEST){
            bind(String.class).annotatedWith(Names.named("DatabaseName")).toInstance(DBConstants.DATABASE_FILENAME_TEST);
        }else if(type == NameConstants.TYPE_NORMAL){
            bind(String.class).annotatedWith(Names.named("DatabaseName")).toInstance(DBConstants.DATABASE_FILENAME);
        }
        bind(Integer.class).annotatedWith(Names.named("DatabaseVersion")).toInstance(DBConstants.DATABASE_VERSION);
        
//        bind(Context.class).toInstance(ExecApplication.instance());
        
        bind(DatabaseHelper.class).to(TestDatabaseHelper.class).in(Singleton.class);
        
        //bind dao
        bind(ITaskDao.class).to(TaskDao.class).in(Singleton.class);
        bind(ITaskItemDao.class).to(TaskItemDao.class).in(Singleton.class);
        bind(ITaskItemBatchDao.class).to(TaskItemBatchDao.class).in(Singleton.class);
        bind(ITaskBatchDao.class).to(TaskBatchDao.class).in(Singleton.class);
        bind(IUploadDao.class).to(UploadDao.class).in(Singleton.class);
        bind(IServiceIndexRelationDao.class).to(ServiceIndexRelationDao.class).in(Singleton.class);
        bind(IScriptDao.class).to(ScriptDao.class).in(Singleton.class);
        bind(IScriptCaseDao.class).to(ScriptCaseDao.class).in(Singleton.class);
        bind(ITestLogDao.class).to(TestLogDao.class).in(Singleton.class);
        bind(ITestLogOnTaskItemDao.class).to(TestLogOnTaskItemDao.class).in(Singleton.class);
        bind(ICaseTestkeycodeDao.class).to(CaseTestkeycodeDao.class).in(Singleton.class);
        
        //bind service
        bind(ITaskService.class).to(TaskService.class).in(Singleton.class);
        bind(IServiceKeyService.class).to(ServiceKeyService.class).in(Singleton.class);
        bind(IScriptService.class).to(ScriptService.class).in(Singleton.class);
        bind(ISyncService.class).to(SyncService.class).in(Singleton.class);
        bind(IClearDbService.class).to(ClearDbService.class).in(Singleton.class);
    }
    
    
}
