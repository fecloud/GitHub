/**
 * @(#) Testkeycodes.java Created on 2012-7-3
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.apk.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * The class <code>Testkeycodes</code>
 * 
 * @author Administrator
 * @version 1.0
 */
@XStreamAlias("testkeycodes")
public class TestKeyCodes {
	
    @XStreamAlias("servicecode")
    @XStreamAsAttribute
    private String serviceCode;
    
    @XStreamAlias("testkeycode")
    @XStreamAsAttribute
    private String testKeyCode;
    
    public String getTestServType() {
        return serviceCode;
    }
    public void setTestServType(String serviceCode) {
        this.serviceCode = serviceCode;
    }
    public String getTestkeycode() {
        return testKeyCode;
    }
    public void setTestkeycode(String testKeyCode) {
        this.testKeyCode = testKeyCode;
    }
}
