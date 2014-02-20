/**
 * @(#) DeleteCodeStatistics.java Created on 2012-9-20
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.braver.code.statistics;

/**
 * The class <code>DeleteCodeStatistics</code>
 *
 * @author ouyangfeng
 * @version 1.0
 */
public class DeleteCodeStatistics extends AbsCodeStatistics {

    public static final String MATCH_STRING = "Deleted :";
    
    /**
     * {@inheritDoc}
     * @see com.braver.code.statistics.AbsCodeStatistics#statistics(java.lang.String)
     */
    @Override
    public boolean statistics(String line) {
        final boolean match = containerString(MATCH_STRING, line);
        if (match)
            count++;
        return match;
    }

}
