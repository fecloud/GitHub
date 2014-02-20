/**
 * @(#) Utils.java Created on 2013-9-11
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.android.wfupload.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * The class <code>Utils</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public final class Utils {

    /**
     * 判断wifi是否打开
     * 
     * @param mContext
     * @return
     */
    public static final boolean wirelessOpen(Context mContext) {
    	final WifiManager wifimanage = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);// 获取WifiManager
    	return wifimanage.isWifiEnabled();
    }

    public static String getWifiIP(Context mContext) {
        final WifiManager wifimanage = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);// 获取WifiManager
        if (wifimanage.isWifiEnabled()) {
            WifiInfo wifiinfo = wifimanage.getConnectionInfo();
            final String ip = intToIp(wifiinfo.getIpAddress());
            return ip;
        }
        return null;

    }

    // 将获取的int转为真正的ip地址,参考的网上的，修改了下
    private static String intToIp(int i)  {
        return (i & 0xFF)+ "." + ((i >> 8 ) & 0xFF) + "." + ((i >> 16 ) & 0xFF) +"."+((i >> 24 ) & 0xFF );
	}

    public static final String getIPAddress(NetworkInterface intf) {
        String result = "";
        if (null != intf) {
            for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                InetAddress inetAddress = enumIpAddr.nextElement();
                if (inetAddress instanceof Inet4Address) {
                    result = inetAddress.getHostAddress();
                }
            }
        }
        return result;
    }

    public static final NetworkInterface getInternetInterface() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                if (intf.getName().equals("wlan0")) {
                    return intf;
                }

            }
        } catch (SocketException ex) {
        }
        return null;
    }
}
