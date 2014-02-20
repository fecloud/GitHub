package com.aspire.mose.frame.net.channel.hschannel;

import com.aspire.mose.frame.net.protocol.transport.ITransProtocolParamet;
import com.aspire.mose.frame.net.protocol.transport.example.TranMsgHead;

public class HSTransProtocolParamet implements ITransProtocolParamet{
	
	public static final int SUMMARY_LENGTH_NONE= 0;//无 
	public static final int SUMMARY_LENGTH_SHA1= 20;//基于HMAC的SHA-1的长度为20 bytes
	public static final int SUMMARY_LENGTH_MD5= 16;//基于HMAC的MD5的长度为16 bytes
	
	
	//客户端支持的对称加密算法
	public static final int SCA_DES = 0x01;//先用这种
	public static final int SCA_3DES = 0x02;
	public static final int SCA_AES = 0x04;
	
	//客户端支持的非对称加密算法
	public static final int ACA_DSA = 0x01;
	public static final int ACA_RSA = 0x02;
	
	
	//客户端支持的非对称加密算法
	public static final int DDA_NONE = 0x01;
	public static final int DDA_MD5 = 0x02;
	public static final int DDA_SHA_1 = 0x04;
	
	
	// 身份凭证类型（0表示requestToken，1表示身份凭证）
	
	
	//	会话过程是否需要加密（0不需要，1需要）
	boolean encrypt= false;
	
	

	//需要下载服务器证书（0不需要，1需要）
	boolean  downloadCredence= false;		
	
	

	//symmetric cryptographic algorithm		用来加密传输协议正文的
	//客户端支持的对称加密算法（1表示DES，2表示3DES，4表示AES），取值为多种加密算法取值的或运算
	int SCA ;	
	
	//asymmetric cryptographic algorithm	用来加握手中使用的证书 密钥等的
	//客户端支持的非对称加密算法（1表示DSA，2表示RSA），取值为多种加密算法取值的或运算
	int ACA ;
	
	//Digital-Digest Algorithm		
	//正文内容的数字摘要算法（1表示不支持，2表示MD5，3表示SHA-1），取值为多种加密算法取值的或运算
	int DDA ;
	
	//数据包校验码的大小
	private int summmarySize;
	
	//内容状态  数据内容状态   标识传输协议中承载的数据协议类型
	private byte contentState = TranMsgHead.MSG_TYPE_HANDSHAKE;
	
	//身份凭证类型（0表示requestToken，1表示身份凭证）
	private byte IdCertType;
	

	//固定密钥 前后台协商 写死的
	private String pas ="aaaabbbb";
	
	
	//服务器公钥 第一次下载 后续文件读取
	private byte[] mospPublicKey=null;
	
	//本机的MAC地址
	private String mac ="00-0D-60-A4-B3-9E";
	
	
	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	//会话密钥 8位
	private byte[] sessionKey;
	
	
	public byte[] getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(byte[] sessionKey) {
		this.sessionKey = sessionKey;
	}

	public byte getContentState() {
		return contentState;
	}

	public void setContentState(byte contentState) {
		this.contentState = contentState;
	}

	public int getSummmarySize() {
		return summmarySize;
	}

	public void setSummmarySize(int summmarySize) {
		this.summmarySize = summmarySize;
	}
	
	public byte getIdCertType() {
		return IdCertType;
	}

	public void setIdCertType(byte idCertType) {
		IdCertType = idCertType;
	}
	
	public boolean isEncrypt() {
		return encrypt;
	}

	public boolean isDownloadCredence() {
		return downloadCredence;
	}

	public void setDownloadCredence(boolean downloadCredence) {
		this.downloadCredence = downloadCredence;
	}
	
	public void setEncrypt(boolean encrypt) {
		this.encrypt = encrypt;
	}

	public int getSCA() {
		return SCA;
	}

	public void setSCA(int sca) {
		SCA = sca;
	}

	public int getACA() {
		return ACA;
	}

	public void setACA(int aca) {
		ACA = aca;
	}

	public int getDDA() {
		return DDA;
	}

	public void setDDA(int dda) {
		DDA = dda;
	}

}
