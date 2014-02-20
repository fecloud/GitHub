/**
 * @(#) ServiceKeyService.java Created on 2012-6-18
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.servicekey.service;

import java.util.List;

import com.aspire.android.common.exception.MException;
import com.aspire.android.test.servicekey.dao.IServiceIndexRelationDao;
import com.aspire.android.test.servicekey.entity.AtsServiceIndexRelation;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * The class <code>ServiceKeyService</code>
 * 
 * @author likai
 * @version 1.0
 */
@Singleton
public class ServiceKeyService implements IServiceKeyService {
    @Inject
    public IServiceIndexRelationDao mIServiceIndexRelationDao;
    @Inject
    public ServiceKeyService() { }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.servicekey.service.IServiceKeyService#findAtsServiceIndexRelation(java.lang.String,
     *      java.lang.String)
     */
    public AtsServiceIndexRelation findAtsServiceIndexRelation(String serviceCode, String testKeyCode)
            throws MException {
        return mIServiceIndexRelationDao.findAtsServiceIndexRelation(serviceCode, testKeyCode);
    }
    public List<AtsServiceIndexRelation> findAtsServiceIndexRelation(String testKeyName) throws MException{
       return mIServiceIndexRelationDao.findAtsServiceIndexRelation(testKeyName);
    }
    public List<AtsServiceIndexRelation> findLsitAtsServiceIndexRelation(String serviceName, String testKeyName)
            throws MException {
        return mIServiceIndexRelationDao.findLsitAtsServiceIndexRelation(serviceName, testKeyName);
    }
    public List<AtsServiceIndexRelation> findAllAtsServiceIndexRelation()throws MException{
        return mIServiceIndexRelationDao.findAllAtsServiceIndexRelation();
    }
    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.servicekey.service.IServiceKeyService#addAtsServiceIndexRelation(com.aspire.android.test.servicekey.entity.AtsServiceIndexRelation)
     */
    public AtsServiceIndexRelation addAtsServiceIndexRelation(AtsServiceIndexRelation mAtsServiceIndexRelation)
            throws MException {
        return mIServiceIndexRelationDao.insert(mAtsServiceIndexRelation);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.servicekey.service.IServiceKeyService#updateAtsServiceIndexRelation(com.aspire.android.test.servicekey.entity.AtsServiceIndexRelation)
     */
    public AtsServiceIndexRelation updateAtsServiceIndexRelation(AtsServiceIndexRelation mAtsServiceIndexRelation)
            throws MException {
        return mIServiceIndexRelationDao.update(mAtsServiceIndexRelation);
    }

}
