/**
 * @(#) OCRResource.java Created on 2012-8-7
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute.resource;

import com.aspire.android.screen.check.ocr.OCRParam;

/**
 * The class <code>OCRResource</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class OCRResource extends OCRParam {

    private String area;

    private String key;

    private String refId;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "{key:" + key + ", area:(" + getStartX() + ", " + getStartY() + ", " + (getStartX() + getWidth()) + ", "
                + (getStartY() + getHeight()) + "), scale: " + getScale() + ", threshold: " + getThreshold() + ", trim:"
                + isTrim() + ", join:" + isJoin() + ", expect:" + getExpect() + ", lang:" + getLang() + "}";
    }

    public String getArea() {
        return area;
    }

    /**
     * @param area
     *            the area to set
     */
    public void setArea(String area) {
        this.area = area;
        if (area != null && area.length() > 0) {
            final String[] vs = area.split(",");
            final int x1 = (vs.length > 0 && vs[0].length() > 0) ? Integer.parseInt(vs[0]) : 0;
            final int y1 = (vs.length > 1 && vs[1].length() > 0) ? Integer.parseInt(vs[1]) : 0;
            final int x2 = (vs.length > 2 && vs[2].length() > 0) ? Integer.parseInt(vs[2]) : 0;
            final int y2 = (vs.length > 3 && vs[3].length() > 0) ? Integer.parseInt(vs[3]) : 0;
            setStartX(x1);
            setStartY(y1);
            setWidth(x2 - x1);
            setHeight(y2 - y1);
        }
    }

    public void assignment(OCRResource resource) {
        setArea(resource.getArea());
        setLang(resource.getLang());
        setScale(resource.getScale());
        setThreshold(resource.getThreshold());
        setTrim(resource.isTrim());
        setJoin(resource.isJoin());
    }
}
