/**
 * @(#) ValueOf.java Created on 2012-12-12
 *
 * Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.android;

/**
 * The class <code>ValueOf</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public final class ValueOf {

    public static byte[] intToByte(int i) {

        byte[] abyte0 = new byte[4];

        abyte0[0] = (byte) ((0xff000000 & i) >> 24);

        abyte0[1] = (byte) ((0xff0000 & i) >> 16);

        abyte0[2] = (byte) ((0xff00 & i) >> 8);

        abyte0[3] = (byte) (0xff & i);

        return abyte0;

    }

    public static int bytesToInt(byte[] bytes) {

        int addr = bytes[3] & 0xFF;

        addr |= ((bytes[2] << 8) & 0xFF00);

        addr |= ((bytes[1] << 16) & 0xFF0000);

        addr |= ((bytes[0] << 24) & 0xFF000000);

        return addr;

    }
}
