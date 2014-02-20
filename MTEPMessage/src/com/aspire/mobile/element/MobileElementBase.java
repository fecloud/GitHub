/**
 * @(#) MobileElementBase.java Created on 2009-2-16
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.element;

import com.aspire.msg.MsgField;

/**
 * The class <code>MobileElementBase</code>
 *
 * @author xuyong
 * @version 1.0
 */
public class MobileElementBase extends MsgField {

    /*
     * (non-Javadoc)
     * @see com.aspire.msg.MsgField#isPresent(java.lang.String)
     */
    public boolean isPresent(String field) {
        return true;
    }
}
