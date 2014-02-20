/**
 * @(#) IServiceKeyService.java Created on 2012-6-12
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.servicekey.service;

import java.util.List;

import com.aspire.android.common.exception.MException;
import com.aspire.android.test.servicekey.entity.AtsServiceIndexRelation;


/**
 * The class <code>IServiceKeyService</code>
 *
 * @author likai
 * @version 1.0
 */
public interface IServiceKeyService {
    /**
     * 
     * @param serviceCode业务编号
     * @param testKeyCode指标编号
     * @return
     * @throws MException
     */
    public AtsServiceIndexRelation findAtsServiceIndexRelation(String serviceCode, String testKeyCode) throws MException;
    /**
     * 
     * @param testKeyName指标名称
     * @return
     * @throws MException
     */
    public List<AtsServiceIndexRelation> findAtsServiceIndexRelation(String testKeyName) throws MException;
    /**
     * 
     * @param serviceName业务类型名称
     * @param testKeyName指标名称
     * @return
     * @throws MException
     */
    public List<AtsServiceIndexRelation> findLsitAtsServiceIndexRelation(String serviceName, String testKeyName) throws MException;
    public List<AtsServiceIndexRelation> findAllAtsServiceIndexRelation()throws MException;
    public AtsServiceIndexRelation addAtsServiceIndexRelation(AtsServiceIndexRelation mAtsServiceIndexRelation)throws MException;
    public AtsServiceIndexRelation updateAtsServiceIndexRelation(AtsServiceIndexRelation mAtsServiceIndexRelation)throws MException;
}
