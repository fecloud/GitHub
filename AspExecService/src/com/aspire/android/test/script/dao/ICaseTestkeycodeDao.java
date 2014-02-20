/**
 * @(#) ICaseTestkeycodeDao.java Created on 2012-7-4
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.script.dao;

import java.util.List;

import com.aspire.android.common.db.IBaseDao;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.script.entity.CaseTestkeycode;

/**
 * The class <code>ICaseTestkeycodeDao</code>
 *
 * @author Administrator
 * @version 1.0
 */
public interface ICaseTestkeycodeDao extends IBaseDao<CaseTestkeycode, Long>{
    
    public List<CaseTestkeycode> getCaseTestkeycode(String testServType,String testkeycode) throws MException;
    public List<CaseTestkeycode> findScriptServiceIndex(Long caseId)throws MException;

}
