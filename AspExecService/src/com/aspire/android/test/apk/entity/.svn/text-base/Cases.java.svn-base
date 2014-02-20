/**
 * @(#) Cases.java Created on 2012-6-11
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.apk.entity;

import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * The class <code>Cases</code>
 * 
 * @author gouanming
 * @version 1.0
 */
@XStreamAlias("cases")
public class Cases {
    @XStreamAlias("packageName")
    @XStreamAsAttribute
    private String packageName;

    @XStreamImplicit(itemFieldName = "case")
    private List<Case> listCase;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public static void initToXStream(XStream xstream) {
        xstream.alias("cases", Cases.class);
        xstream.autodetectAnnotations(true);
    }

    public String toXml() {
        XStream xstream = new XStream();
        xstream.alias("cases", Cases.class);
        xstream.autodetectAnnotations(true);
        return xstream.toXML(this);
    }

    public List<Case> getListCase() {
        return listCase;
    }

    public void setListCase(List<Case> listCase) {
        this.listCase = listCase;
    }

}
