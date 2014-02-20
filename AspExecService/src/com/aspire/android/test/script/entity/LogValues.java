/**
 * @(#) LogValues.java Created on 2012-7-10
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.script.entity;

import com.aspire.android.test.execute.ContentValues;

/**
 * The class <code>LogValues</code>
 *
 * @author Administrator
 * @version 1.0
 */
public class LogValues {
    
    private long logId;
    private ContentValues contentValues;
    
    
    public long getLogId() {
        return logId;
    }
    public void setLogId(long logId) {
        this.logId = logId;
    }
    public ContentValues getContentValues() {
        return contentValues;
    }
    public void setContentValues(ContentValues contentValues) {
        this.contentValues = contentValues;
    }
    
    

}
