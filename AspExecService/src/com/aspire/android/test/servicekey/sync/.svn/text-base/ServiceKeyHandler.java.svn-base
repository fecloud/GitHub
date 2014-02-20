/**
 * @(#) ServiceKeyParser.java Created on 2012-6-12
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.servicekey.sync;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.aspire.android.common.exception.MException;
import com.aspire.android.test.log.RunLogger;
import com.aspire.android.test.servicekey.entity.AtsServiceIndexRelation;
import com.aspire.android.test.servicekey.service.IServiceKeyService;
import com.aspire.android.test.sync.pub.LocalFtpClient;
import com.aspire.mgt.ats.tm.sync.AbstractMessageHandler;
import com.aspire.mgt.ats.tm.sync.service.entity.ServiceKey;
import com.aspire.mgt.ats.tm.sync.service.entity.ServiceKeys;
import com.aspire.mgt.ats.tm.sync.service.entity.TestKeyInfo;

/**
 * The class <code>ServiceKeyHandler</code>
 * 
 * @author likai
 * @version 1.0
 */
public class ServiceKeyHandler extends AbstractMessageHandler<ServiceKeys> {

    private RunLogger runLogger = RunLogger.getInstance();
    
    private IServiceKeyService service;

    public ServiceKeyHandler(IServiceKeyService service) {
        super();
        this.service = service;
    }

    @Override
    public void handle(ServiceKeys serviceKeys) {
        List<ServiceKey> serviceKey = serviceKeys.getService();
        for (ServiceKey serviceKey2 : serviceKey) {
            String serviceName = serviceKey2.getServiceName();
            String serviceCode = serviceKey2.getServiceCode();

            List<TestKeyInfo> testKeyInfo = serviceKey2.getTestKeyInfo();
            for (TestKeyInfo testKeyInfo2 : testKeyInfo) {

                String testKeyName = testKeyInfo2.getTestKeyName();
                String testKeyCode = testKeyInfo2.getTestKeyCode();
                String status = testKeyInfo2.getStatus();

                AtsServiceIndexRelation ats = new AtsServiceIndexRelation();
                ats.setLastUpdateDate(new Date());
                ats.setServiceCode(serviceCode);
                ats.setServiceName(serviceName);
                ats.setTestKeyCode(testKeyCode);
                ats.setTestKeyName(testKeyName);
                ats.setStatus(status);

                AtsServiceIndexRelation mservice = null;
                try {
                    mservice = service.findAtsServiceIndexRelation(serviceCode, testKeyCode);
                } catch (MException e) {
                    runLogger.error(LocalFtpClient.class, "search servicekeys in db by serviceCode and testKeyCode, and errmessge is " + e.getMessage());
                }
                if (mservice == null) {
                    try {
                        service.addAtsServiceIndexRelation(ats);
                    } catch (MException e) {
                        runLogger.error(LocalFtpClient.class, "save servicekeys to db, and errmessge is " + e.getMessage());
                    }
                } else {
                    mservice.setLastUpdateDate(new Date());
                    mservice.setServiceCode(serviceCode);
                    mservice.setServiceName(serviceName);
                    mservice.setTestKeyCode(testKeyCode);
                    mservice.setTestKeyName(testKeyName);
                    mservice.setStatus(status);
                    try {
                        service.updateAtsServiceIndexRelation(mservice);
                    } catch (MException e) {
                        runLogger.error(LocalFtpClient.class, "update servicekeys to db, and errmessge is " + e.getMessage());
                    }
                }
            }
        }
    }

    @Override
    public void handle(ServiceKeys arg0, HashMap<String, Object> arg1) {
    }

}
