/**
 * @(#) ResultSearchAdapter.java Created on 2012-8-7
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute.adapter;

import java.util.List;
import com.aspire.android.test.execute.R;
import com.aspire.android.test.task.entity.QueryResults;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * The class <code>ResultSearchAdapter</code>
 * 
 * @author likai
 * @version 1.0
 */
public class ResultSearchAdapter extends BaseAdapter {
    private Context mContext;
    private List<ResultSearchItemContent> resultListData;
    /**
     * 删除所用的list
     */
    private List<ResultSearchItemContent> itemContents;

    /**
     * Setter of resultListData
     * 
     * @param resultListData
     *            the resultListData to set
     */
    public void setResultListData(List<ResultSearchItemContent> resultListData) {
        this.resultListData = resultListData;
    }

    /**
     * Setter of itemContents
     * @param itemContents the itemContents to set
     */
    public void setItemContents(List<ResultSearchItemContent> itemContents) {
        this.itemContents = itemContents;
    }

    public ResultSearchAdapter(Context context) {
        this.mContext = context;
    }

    /**
     * {@inheritDoc}
     * 
     * @see android.widget.Adapter#getCount()
     */
    public int getCount() {
        if (resultListData == null)
            return 0;
        return resultListData.size();
    }

    /**
     * {@inheritDoc}
     * 
     * @see android.widget.Adapter#getItem(int)
     */
    public Object getItem(int arg0) {
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see android.widget.Adapter#getItemId(int)
     */
    public long getItemId(int arg0) {
        return 0;
    }

    /**
     * {@inheritDoc}
     * 
     * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
     */
    public View getView(int position, View v, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.result_search_item, null);
        TextView taskName = (TextView) layout.findViewById(R.id.task_name);
        TextView serviceKeyName = (TextView) layout.findViewById(R.id.service_key_name);
        TextView resultId = (TextView) layout.findViewById(R.id.result_id);
        CheckBox checkBox = (CheckBox) layout.findViewById(R.id.checkbox);

        final ResultSearchItemContent resultData = resultListData.get(position);

        taskName.setText("任务名称:"+resultListData.get(position).getQueryResults().getTaskName());
        serviceKeyName.setText("指标名称:"+resultListData.get(position).getQueryResults().getTestKeyName());

        resultId.setText( "拨测序号:"+ resultListData.get(position).getQueryResults().getTaskItemBatchId());
        
       
        if (resultData.getQueryResults().getUpStatus().equals("0")
                || resultData.getQueryResults().getStatus().equals("0")) {
            checkBox.setVisibility(View.VISIBLE);
            checkBox.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    resultData.setNeedUploadStatus(((CheckBox) v).isChecked());
                    if(resultData.isNeedUploadStatus()){
                        itemContents.add(resultData);
                    }else{
                        itemContents.remove(resultData);
                    }
                    Log.v("ReesultSearchAdapter","size = " + itemContents.size());
                }
            });
        } else {
            checkBox.setVisibility(View.GONE);
        }
        resultListData.set(position, resultData);
        return layout;
    }

    public static class ResultSearchItemContent {
        private QueryResults queryResults;
        private boolean needUploadStatus;

        /**
         * Getter of queryResults
         * 
         * @return the queryResults
         */
        public QueryResults getQueryResults() {
            return queryResults;
        }

        /**
         * Getter of needUploadStatus
         * 
         * @return the needUploadStatus
         */
        public boolean isNeedUploadStatus() {
            return needUploadStatus;
        }

        /**
         * Setter of queryResults
         * 
         * @param queryResults
         *            the queryResults to set
         */
        public void setQueryResults(QueryResults queryResults) {
            this.queryResults = queryResults;
        }

        /**
         * Setter of needUploadStatus
         * 
         * @param needUploadStatus
         *            the needUploadStatus to set
         */
        public void setNeedUploadStatus(boolean needUploadStatus) {
            this.needUploadStatus = needUploadStatus;
        }

    }
}
