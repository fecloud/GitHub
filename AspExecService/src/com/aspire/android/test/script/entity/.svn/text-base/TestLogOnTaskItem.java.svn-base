package com.aspire.android.test.script.entity;

import com.aspire.android.common.entity.BaseEntity;
import com.aspire.android.test.Constants;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * The class <code>TestLogOnTaskItemId</code>
 * 
 * @author gouanming
 * @version 1.0
 */
@DatabaseTable(tableName = Constants.ATS_TASKITEM_TESTLOG)
public class TestLogOnTaskItem extends BaseEntity {
    
    @DatabaseField(columnName = "ID", generatedId = true)
    private long id;

    /** 明细执行批次ID **/
    @DatabaseField(columnName = "item_batch_ID")
    private long itemBatchId;

    /** 过程日志ID **/
    @DatabaseField(columnName = "test_log_ID")
    private long testLogId;

   

    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getItemBatchId() {
        return itemBatchId;
    }

    public void setItemBatchId(long itemBatchId) {
        this.itemBatchId = itemBatchId;
    }

    public long getTestLogId() {
        return testLogId;
    }

    public void setTestLogId(long testLogId) {
        this.testLogId = testLogId;
    }

}
