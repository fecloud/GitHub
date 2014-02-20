/*
 * Copyright (C) 2010 Aspire
 * 
 * ForceLogout.java Version 1.0
 *
 */
package com.aspire.eldmose.monitor;

/**
 * 强制登出通知
 * @author x_liyajun
 *
 * 2010-9-14 下午02:15:17
 *  
 * ForceLogout
 *
 */
public class ForceLogout extends PushObject {
    
    /**用户手机号码*/
    public String msisdn = "";
    
    /**强制登出时间*/
    public String logoutTime = "";

    public ForceLogout() {
        super();
        // TODO Auto-generated constructor stub
        this.smsType = PushType.FORCE_LOGOUT;
    }
    
    
}
