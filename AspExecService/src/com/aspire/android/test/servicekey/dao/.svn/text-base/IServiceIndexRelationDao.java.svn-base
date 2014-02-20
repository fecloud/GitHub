/**
 * @(#) IServiceIndexRelationDao.java Created on 2012-6-12
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.servicekey.dao;

import java.util.List;

import com.aspire.android.common.db.IBaseDao;
import com.aspire.android.common.exception.MException;
import com.aspire.android.test.servicekey.entity.AtsServiceIndexRelation;

/**
 * The class <code>IServiceIndexRelationDao</code>
 *
 * @author gouanming
 * @version 1.0
 */
public interface IServiceIndexRelationDao  extends IBaseDao<AtsServiceIndexRelation, Long>{
    
    public AtsServiceIndexRelation findAtsServiceIndexRelation(String serviceCode,String testKeyCode) throws MException; 
    public List<AtsServiceIndexRelation> findAtsServiceIndexRelation(String testKeyName) throws MException;
    public List<AtsServiceIndexRelation> findLsitAtsServiceIndexRelation(String serviceName, String testKeyName) throws MException;
    public List<AtsServiceIndexRelation> findAllAtsServiceIndexRelation()throws MException;
}
