/**
 * @(#) OperateResultFromPc.java Created on 2012-10-12
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.task.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.aspire.android.common.exception.MException;
import com.aspire.android.test.log.RunLogger;
import com.aspire.android.test.result.sync.DoUpload;
import com.aspire.android.test.task.entity.QueryResults;
import com.aspire.sqmp.mobilemanager.entity.CPage;
import com.aspire.sqmp.mobilemanager.entity.ResultQueryBuilder;
import com.aspire.sqmp.mobilemanager.entity.TestResults;
import com.google.inject.Inject;

/**
 * The class <code>OperateResultFromPc</code>
 * 
 * @author likai
 * @version 1.0
 */
public class OperateResultFromPc {
    /**
     * 与任务相关操作
     */
    @Inject
    private ITaskService taskService;

    private CPage cPage;
    private RunLogger runLogger = RunLogger.getInstance();

    @Inject
    protected DoUpload mDoUpload;

    @Inject
    public OperateResultFromPc() {
    }

    public List<TestResults> getTestResults(ResultQueryBuilder queryBuilder) {
        if (queryBuilder == null)
            return null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        List<TestResults> testResults = new ArrayList<TestResults>();
        try {
            cPage = queryBuilder.page;
            long offset = 0;
            if (cPage.curPage > 1)
                offset =( cPage.curPage - 1) * cPage.eachPageCount;
            long num = cPage.eachPageCount;

            Integer count = 0;
            List<QueryResults> queryResults = new ArrayList<QueryResults>();
            count = taskService.getQueryResults(queryBuilder.getTaskName(), queryBuilder.getTestKeyName(),
                    queryBuilder.getTestStatus(), queryBuilder.getUpStatus(), queryBuilder.getFailedReason(),
                    queryBuilder.getStartTime(), queryBuilder.getEndTime(), offset, num, queryResults);
            int pageCount = count / cPage.eachPageCount;
            if (count % cPage.eachPageCount > 0) {
                pageCount++;
            }
            cPage.allCount = count;
            cPage.allPageCount = pageCount;
            for (QueryResults queryResult : queryResults) {
                TestResults testResult = new TestResults();
                testResult.setTaskItemBatchId(queryResult.getTaskItemBatchId());
                testResult.setTaskName(queryResult.getTaskName());
                testResult.setTestKeyName(queryResult.getTestKeyName());
                testResult.setStatus(queryResult.getStatus());
                testResult.setTestValue(queryResult.getTestValue());
                testResult.setUpStatus(queryResult.getUpStatus());
                testResult.setStartTime(queryResult.getStartTime() != null ? dateFormat.format(queryResult
                        .getStartTime()) : "");
                testResult.setEndTime(queryResult.getEndTime() != null ? dateFormat.format(queryResult.getEndTime())
                        : "");
                testResults.add(testResult);
            }
        } catch (MException e) {
            runLogger.error(getClass(), "query result failed, errmessage is " + e.getMessage());
        }
        return testResults;
    }

    public int deleteTaskItemBatchs(List<Long> taskItemBatchIds) {

        if (taskItemBatchIds != null && taskItemBatchIds.size() != 0) {
            try {
                return taskService.deleteTaskCase(taskItemBatchIds);
            } catch (MException e) {
                runLogger.error(getClass(), "delete taskItemBatchs failed, errmessage is " + e.getMessage());
            }
        } else {
            runLogger.error(getClass(), "taskItemBatchIds is null ,or taskItemBatchIds' size is 0");
        }
        return 0;
    }

    public String zipResultFile(List<Long> taskItemBatchIds) throws MException {
        mDoUpload.initialUpload();
        return mDoUpload.zipResultForPc(taskItemBatchIds);
    }

    public void setDBtoStart(List<TestResults> testResults) {
        if (testResults != null && testResults.size() > 0) {
            String[] columns = new String[1];
            String[] values = new String[1];
            for (TestResults testResult : testResults) {
                columns[0] = "UPSTATUS";
                values[0] = testResult.getUpStatus();
                try {
                    taskService.updateTaskItemBatchById(testResult.getTaskItemBatchId(), columns, values);
                } catch (MException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Getter of cPage
     * 
     * @return the cPage
     */
    public CPage getcPage() {
        return cPage;
    }

    /**
     * Setter of cPage
     * 
     * @param cPage
     *            the cPage to set
     */
    public void setcPage(CPage cPage) {
        this.cPage = cPage;
    }

}
