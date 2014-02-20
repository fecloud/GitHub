/**
 * @(#) IScriptCaseDao.java Created on 2012-6-14
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.script.dao;

import java.util.List;

import com.aspire.android.common.db.IBaseDao;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.script.entity.ScriptCase;

/**
 * The class <code>IScriptCaseDao</code>
 *
 * @author Administrator
 * @version 1.0
 */
public interface IScriptCaseDao extends IBaseDao<ScriptCase, Long>{
    
    public List<ScriptCase> findScriptCases(Long scrpitID)throws MException;
    public ScriptCase findScriptCase(String casePath) throws MException;

}
