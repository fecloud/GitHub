/*
 * Copyright (C) 2010 Aspire
 * 
 * MsisdnNotify.java Version 1.0
 *
 */
package com.aspire.eldmose.monitor;

/**
 * 手机号码下发短信
 * 
 * @author x_luoqingbo
 *
 * 2010-10-26 下午01:55:01
 *  
 * MsisdnNotify
 *
 */
public class MsisdnNotify extends PushObject {

    /**用户手机号码*/
    public String msisdn = "";
    
    /**IMSI卡串号*/
    public String imsi = "";
    /**随机号*/
    public String ssid = "";     //add by hjk 2011.09.23
    
    public MsisdnNotify() {
        super();
        this.smsType = PushType.MSISDN_NOTIFY;
    }

}
