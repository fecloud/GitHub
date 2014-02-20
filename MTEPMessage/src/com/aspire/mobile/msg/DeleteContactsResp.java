/**
 * @(#) DeleteContactsResp.java Created on 2009-3-24
 *
 * Copyright (c) 2009 Aspire. All Rights Reserved
 */
package com.aspire.mobile.msg;

import com.aspire.mobile.MobileConstants;

/**
 * The class <code>DeleteContactsResp</code>
 *
 * @author Link Wang
 * @version 1.0
 */
public class DeleteContactsResp extends MobileRespBase {

    /**
     * The constructor.
     */
    public DeleteContactsResp() {
        super(MobileConstants.DELETE_CONTACTS_RESP, "DeleteContactsResp");
    }
}
