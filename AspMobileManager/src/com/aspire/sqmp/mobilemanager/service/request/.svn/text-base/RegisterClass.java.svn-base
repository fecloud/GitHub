/**
 * @(#) RegisterClass.java Created on 2012-7-25
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.service.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import com.aspire.sqmp.mobilemanager.entity.MobileSharedFile;

/**
 * The class <code>RegisterClass</code>
 * 
 * @author wuyanlong
 * @version 1.0
 */
public class RegisterClass {
    /**
     * The list of Register class
     */
    private static List<Class<?>> registerClassList = new ArrayList<Class<?>>();
    /**
     * Default register
     */
    static {

        registerClassList.add(ArrayList.class);
        registerClassList.add(Hashtable.class);
        registerClassList.add(HashMap.class);
        registerClassList.add(String.class);
        registerClassList.add(char[].class);
        // entity

        // request and respond
        registerClassList.add(AspMessage.class);
        registerClassList.add(AspRequest.class);
        registerClassList.add(AspResponse.class);
        registerClassList.add(ErrorRespond.class);
        registerClassList.add(SuccessRespond.class);

        // W_TASK
        registerClassList.add(STaskRequest.class);
        registerClassList.add(STaskResponse.class);

        // SETTING
        registerClassList.add(SSettingRequest.class);
        registerClassList.add(SSettingResponse.class);
        registerClassList.add(GSettingRequest.class);
        registerClassList.add(GSettingResponse.class);
        registerClassList.add(MobileSharedFile.class);

        // S_CASE
        registerClassList.add(SCaseRequest.class);
        registerClassList.add(SCaseResponse.class);
        registerClassList.add(GCaseRequest.class);
        registerClassList.add(GCaseResponse.class);

        // S_Password
        registerClassList.add(SPasswordRequest.class);
        registerClassList.add(SPasswordResponse.class);

        // S_Servicekey
        registerClassList.add(SServicekeyRequest.class);
        registerClassList.add(SServicekeyResponse.class);

        // G_RESULT
        registerClassList.add(GResultRequest.class);
        registerClassList.add(GResultResponse.class);

        // G_STORE_INFO
        registerClassList.add(GStoreInfoReqeust.class);
        registerClassList.add(GStoreInfoResponse.class);
    }

    /**
     * Add class of register
     * 
     * @param c
     */
    public static void addRegisterClass(Class<?> c) {
        if (!registerClassList.contains(c))
            registerClassList.add(c);
    }

    /**
     * Remove class of register
     * 
     * @param c
     */
    public static void removeRegisterClass(Class<?> c) {
        registerClassList.remove(c);
    }

    /**
     * Gettor of registerClassList
     * 
     * @return
     */
    public static List<Class<?>> getRegisterClassList() {
        return registerClassList;
    }
}
