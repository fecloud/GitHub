/**
 * @(#) CodeStatisticsCenter.java Created on 2012-9-20
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.braver.code.statistics;

import java.util.ArrayList;
import java.util.List;

/**
 * The class <code>CodeStatisticsCenter</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class CodeStatisticsCenter implements CodeStatistics {

    private List<AbsCodeStatistics> statistics = new ArrayList<AbsCodeStatistics>();

    private AddCodeStatistics addCodeStatistics;

    private DeleteCodeStatistics deleteCodeStatistics;

    private ModifiyCodeStatistics modifiyCodeStatistics;

    public CodeStatisticsCenter() {
        addCodeStatistics = new AddCodeStatistics();
        deleteCodeStatistics = new DeleteCodeStatistics();
        modifiyCodeStatistics = new ModifiyCodeStatistics();
        // add in statistics
        statistics.add(addCodeStatistics);
        statistics.add(deleteCodeStatistics);
        statistics.add(modifiyCodeStatistics);

    }

    /**
     * {@inheritDoc}
     * 
     * @see com.braver.code.statistics.CodeStatistics#statistics(java.lang.String)
     */
    @Override
    public boolean statistics(String line) {
        for (AbsCodeStatistics codeStatistics : statistics) {
            if (codeStatistics.statistics(line))
                return true;
        }
        return false;
    }

    public String getStatisticReport(){
        final StringBuffer buffer = new StringBuffer();
        if(null != addCodeStatistics){
            buffer.append(AddCodeStatistics.MATCH_STRING);
            buffer.append(addCodeStatistics.getCount());
            buffer.append("\r\n");
        }
        
        if(null != deleteCodeStatistics){
            buffer.append(DeleteCodeStatistics.MATCH_STRING);
            buffer.append(deleteCodeStatistics.getCount());
            buffer.append("\r\n");
        }
        
        if(null != modifiyCodeStatistics){
            buffer.append(ModifiyCodeStatistics.MATCH_STRING);
            buffer.append(modifiyCodeStatistics.getCount());
            buffer.append("\r\n");
        }
        return buffer.toString();
    }
    
    
    public AddCodeStatistics getAddCodeStatistics() {
        return addCodeStatistics;
    }

    public void setAddCodeStatistics(AddCodeStatistics addCodeStatistics) {
        this.addCodeStatistics = addCodeStatistics;
    }

    public DeleteCodeStatistics getDeleteCodeStatistics() {
        return deleteCodeStatistics;
    }

    public void setDeleteCodeStatistics(DeleteCodeStatistics deleteCodeStatistics) {
        this.deleteCodeStatistics = deleteCodeStatistics;
    }

    public ModifiyCodeStatistics getModifiyCodeStatistics() {
        return modifiyCodeStatistics;
    }

    public void setModifiyCodeStatistics(ModifiyCodeStatistics modifiyCodeStatistics) {
        this.modifiyCodeStatistics = modifiyCodeStatistics;
    }
    

}
