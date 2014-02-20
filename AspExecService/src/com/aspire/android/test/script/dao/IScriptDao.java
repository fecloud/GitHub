/**
 * @(#) IScriptDao.java Created on 2012-6-12
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.script.dao;

import java.util.List;

import com.aspire.android.common.db.IBaseDao;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.script.entity.AtsScript;

/**
 * The class <code>IScriptDao</code>
 *
 * @author gouanming
 * @version 1.0
 */
public interface IScriptDao  extends IBaseDao<AtsScript, Long>{
    
    public Long findMAXScriptID() throws MException;
    public List<AtsScript> findListAtsScript(String scriptName)throws MException;
    public List<AtsScript> findListAtsScript()throws MException;
    public List<AtsScript> findListAtsScript(String scriptName,String taskKeyName)throws MException;
    public AtsScript findAtsScript(String serviceCode, String testKeyCode) throws MException;

}
