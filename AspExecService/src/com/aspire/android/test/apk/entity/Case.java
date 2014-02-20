/**
 * @(#) Case.java Created on 2012-6-11
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.apk.entity;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * The class <code>Case</code>
 *
 * @author gouanming
 * @version 1.0
 */
@XStreamAlias("case")
public class Case {
	
    @XStreamAlias("name")
    @XStreamAsAttribute
    private String name;
    
    @XStreamImplicit(itemFieldName = "testkeycodes")
    private List<TestKeyCodes> testkeycodes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TestKeyCodes> getTestkeycodes() {
        return testkeycodes;
    }

    public void setTestkeycodes(List<TestKeyCodes> testkeycodes) {
        this.testkeycodes = testkeycodes;
    }
}
