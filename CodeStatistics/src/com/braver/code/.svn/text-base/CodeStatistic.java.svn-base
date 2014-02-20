/**
 * @(#) CodeStatistic.java Created on 2012-9-20
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.braver.code;

import com.braver.code.parse.LineReader;
import com.braver.code.parse.LineReaderListeners;
import com.braver.code.statistics.CodeStatisticsCenter;

/**
 * The class <code>CodeStatistic</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class CodeStatistic implements LineReaderListeners {

    private LineReader reader;

    private CodeStatisticsCenter center;

    private String path;

    public CodeStatistic(String path) {
        this.path = path;
        reader = new LineReader(path);
        reader.setListeners(this);
        center = new CodeStatisticsCenter();
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.braver.code.parse.LineReaderListeners#readingLine(java.lang.String)
     */
    @Override
    public void readingLine(String line) {
        if (null != center)
            center.statistics(line);
    }
    
    public void work(){
        reader.read();
    }
    
    public String getStatistics(){
        if(null != center){
            return center.getStatisticReport();
        }
        return null;
    }

    public LineReader getReader() {
        return reader;
    }

    public void setReader(LineReader reader) {
        this.reader = reader;
    }

    public CodeStatisticsCenter getCenter() {
        return center;
    }

    public void setCenter(CodeStatisticsCenter center) {
        this.center = center;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
