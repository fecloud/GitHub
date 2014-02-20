package com.aspire.mose.frame.crypto;

import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Set;

import javax.crypto.Cipher;

import android.util.Log;

public class RSA
{

    private static final String PROVIDER_NAME = "BC";// 强制使用bouncycastle的实现
    private static final String KEY_ALGORITHM = "RSA";

    // RSA/ECB/PKCS1Padding，如果对方用PKCS1v1_5Padding，则不需要用RSA/NONE/PKCS1Padding
    private static final String CIPHER_ALGORITHM = "RSA/NONE/PKCS1Padding";
    
    //SHA1/RSA/PKCS1Padding，SHA1withRSA/ISO9796-2，SHA1withRSA，SHA1withRSA/PSS
    private static final String SIGNATURE_ALGORITHM = "SHA1withRSA";

    /**
     * 用私钥对信息生成数字签名
     */
    public static String sign(byte[] data, String privateKey) throws Exception
    {
        // 解密由base64编码的私钥
        byte[] keyBytes = StringUtil.hexStr2ByteArr(privateKey);

        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM,PROVIDER_NAME);

        // 取私钥匙对象
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 用私钥对信息生成数字签名
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM,PROVIDER_NAME);
        signature.initSign(priKey);
        signature.update(data);

        return StringUtil.byteArr2HexStr(signature.sign());
    }

    /**
     * 校验数字签名
     */
    public static boolean verify(byte[] data, String publicKey, String sign) throws Exception
    {

        // 解密由base64编码的公钥
        byte[] keyBytes = StringUtil.hexStr2ByteArr(publicKey);

        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM,PROVIDER_NAME);

        // 取公钥匙对象
        PublicKey pubKey = keyFactory.generatePublic(keySpec);

        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM,PROVIDER_NAME);
        signature.initVerify(pubKey);
        signature.update(data);

        // 验证签名是否正常
        return signature.verify(StringUtil.hexStr2ByteArr(sign));
    }

    /**
     * 校验数字签名
     */
    public static boolean verify2(byte[] data, String publicKey, String sign) throws Exception
    {

        // 解密由base64编码的公钥
        byte[] keyBytes = StringUtil.hexStr2ByteArr(publicKey);

        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM,PROVIDER_NAME);

        // 取公钥匙对象
        PublicKey pubKey = keyFactory.generatePublic(keySpec);

        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM,PROVIDER_NAME);
        signature.initVerify(pubKey);
        signature.update(data);

        // 验证签名是否正常
        return signature.verify(StringUtil.hexStr2ByteArr(sign));
    }
    
    /**
     * 用私钥解密
     */
    public static byte[] decryptByPrivateKey(byte[] data, String key) throws Exception
    {
        // 对密钥解密
        byte[] keyBytes = StringUtil.hexStr2ByteArr(key);

        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM, PROVIDER_NAME);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 对数据解密
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, PROVIDER_NAME);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return cipher.doFinal(data);
    }

    /**
     * 用公钥解密
     */
    public static byte[] decryptByPublicKey(byte[] data, String key) throws Exception
    {
        // 对密钥解密
        byte[] keyBytes = StringUtil.hexStr2ByteArr(key);

        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM, PROVIDER_NAME);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);

        // 对数据解密
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, PROVIDER_NAME);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        return cipher.doFinal(data);
    }

    /**
     * 用公钥加密
     */
    public static byte[] encryptByPublicKey(byte[] data, String key) throws Exception
    {
        // 对公钥解密
        byte[] keyBytes = StringUtil.hexStr2ByteArr(key);

        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM, PROVIDER_NAME);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, PROVIDER_NAME);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(data);
    }

    /**
     * 用公钥加密
     */
    public static byte[] encryptByPublicKey(byte[] data, byte[] keyBytes) throws Exception
    {
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM, PROVIDER_NAME);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, PROVIDER_NAME);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(data);
    }
    
    /**
     * 用私钥加密
     */
    public static byte[] encryptByPrivateKey(byte[] data, String key) throws Exception
    {
        // 对密钥解密
        byte[] keyBytes = StringUtil.hexStr2ByteArr(key);

        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM, PROVIDER_NAME);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, PROVIDER_NAME);
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        return cipher.doFinal(data);
    }
    
    public static void listProvider()
    {
        Provider[] providers = Security.getProviders();
        for (Provider p : providers)
        {
            Log.d("aaaaaaaaa", "=========================" + p.getName());
            Set<Provider.Service> sets = p.getServices();
            for (Provider.Service s : sets)
            {
                Log.d("aaaaaaaaa", s.getClassName()+","+s.getAlgorithm());
            }
        }
    }

}
