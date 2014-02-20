/**
 * @(#) GCaseRequest.java Created on 2012-8-10
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.service.request;

/**
 * The class <code>GCaseRequest</code>
 *
 * @author likai
 * @version 1.0
 */
public class GCaseRequest extends AspRequest {

    /**
     * 0:删除文件，1查询上次更新的时间戳
     */
    private int type;
    
    /**
     * Getter of type
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * Setter of type
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    public GCaseRequest() {
        super(G_CASE_REQ);
    }

}
