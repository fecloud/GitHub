/**
 * @(#) DesUtil.java Created on 2010-12-22
 *
 * Copyright (c) 2010 Aspire. All Rights Reserved
 */
package com.aspire.android.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;



/**
 * The class <code>DesUtil</code>
 * 
 * @author lixin_b
 * @version 1.0
 */
public class DesUtil {

    private static final String TRANSFORMATION = "DES/CBC/PKCS5Padding";

    public static final String ALGORITHM = "DES";


    /**
     * 加密
     * 
     * @param b 明文
     * @param keyBytes 密钥
     * @param ivBytes 加密偏移量
     * @return 密文
     * @throws Exception 异常
     */
    public static byte[] encryptDES(byte[] b, byte[] keyBytes, byte[] ivBytes)
            throws Exception {
        IvParameterSpec zeroIv = new IvParameterSpec(ivBytes);
        SecretKeySpec key = new SecretKeySpec(keyBytes, ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        return cipher.doFinal(b);
    }

	public static byte[] encrypt(byte[] bs ,byte[] password) throws IOException,
    Exception {
		if (password != null) {
		    ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
		    byte[] iv = new byte[8];
		    try {
		        for (byte b : bs) {
		            out.write(encryptDES(new byte[] { b }, password, iv));
		        }
	            return out.toByteArray();
		    } finally {
		        out.close();
		    }
		}
		return bs;
	}

}
