/**
 * @(#) AddCodeStatistics.java Created on 2012-9-20
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.braver.code.statistics;

/**
 * The class <code>AddCodeStatistics</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public abstract class AbsCodeStatistics implements CodeStatistics {

    /**
     * 计数器
     */
    protected int count;

    /**
     * {@inheritDoc}
     * 
     * @see com.braver.code.statistics.CodeStatistics#statistics(java.lang.String)
     */
    @Override
    public abstract boolean statistics(String line);
    
    /**
     * match string
     * @param match
     * @param line
     * @return
     */
    protected static boolean containerString(String match,String line) {
        if(null != line && !"".equals(line.trim())){
            return line.trim().contains(match);
        }
        return false;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
