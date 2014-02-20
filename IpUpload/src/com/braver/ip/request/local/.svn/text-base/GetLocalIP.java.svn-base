/**
 * @(#) GetLocalIP.java Created on 2012-8-14
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.braver.ip.request.local;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import com.braver.ip.request.AbstractGetIP;

/**
 * The class <code>GetLocalIP</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class GetLocalIP extends AbstractGetIP {

    /**
     * {@inheritDoc}
     * 
     * @see com.braver.ip.request.GetIP#getIp()
     */
    @Override
    public String getIP() {
        final StringBuffer buffer = new StringBuffer();
        Enumeration<NetworkInterface> netInterfaces = null;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress inetAddress = null;
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> ips = ni.getInetAddresses();
                while (ips.hasMoreElements()) {
                    inetAddress = ips.nextElement();
                    buffer.append("[");
                    buffer.append(inetAddress.getHostName());
                    buffer.append("   ");
                    buffer.append(numericToTextFormat(inetAddress.getAddress()));
                    buffer.append("]");
                    buffer.append("   ");
                }
            }
        } catch (Exception e) {
            logger.error("get local ip error", e);
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        new GetLocalIP().getIP();
    }
    
    static String numericToTextFormat(byte[] src)
    {
    return (src[0] & 0xff) + "." + (src[1] & 0xff) + "." + (src[2] & 0xff) + "." + (src[3] & 0xff);
    }

}
