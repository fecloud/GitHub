package com.aspire.mose.frame.net.channel.hschannel;

import java.io.IOException;

import android.util.Log;

import com.aspire.mose.business.module.device.M_Device;
import com.aspire.mose.frame.config.Config;
import com.aspire.mose.frame.config.ConfigModule;
import com.aspire.mose.frame.crypto.DES;
import com.aspire.mose.frame.crypto.RSA;
import com.aspire.mose.frame.crypto.StringUtil;
import com.aspire.mose.frame.net.channel.BaseValidate;
import com.aspire.mose.frame.net.channel.IChannel;
import com.aspire.mose.frame.net.protocol.handshake.IHandShakeProtocol;
import com.aspire.mose.frame.net.protocol.handshake.example.HSMsg;
import com.aspire.mose.frame.net.protocol.handshake.example.HSMsgHead;
import com.aspire.mose.frame.net.protocol.transport.example.TranMsgHead;
import com.aspire.mose.protocol.handshake.MsgAuthClientReq;
import com.aspire.mose.protocol.handshake.MsgAuthServerResp;
import com.aspire.mose.protocol.handshake.MsgAuthServerResp.AuthServerResp;
import com.aspire.mose.protocol.handshake.MsgCertServerResp;
import com.aspire.mose.protocol.handshake.MsgCertServerResp.CertServerResp;
import com.aspire.mose.protocol.handshake.MsgConnectClientReq;
import com.aspire.mose.protocol.handshake.MsgConnectServerResp;
import com.aspire.mose.protocol.handshake.MsgConnectServerResp.ConnectServerResp;
import com.aspire.mose.protocol.handshake.MsgHandShakeFinish;
import com.aspire.mose.protocol.handshake.MsgHandShakeFinish.HandShakeFinish;
import com.aspire.mose.protocol.handshake.MsgSecKeyClientReq;
import com.aspire.mose.protocol.handshake.MsgSeckeyServerResp;
import com.aspire.mose.protocol.handshake.MsgSeckeyServerResp.SeckeyServerResp;
import com.aspire.mose.protocol.handshake.MsgSecuCheckProgClientReq;
import com.aspire.mose.protocol.handshake.MsgSecuCheckProgServerResp;
import com.aspire.mose.protocol.handshake.MsgSecuCheckProgServerResp.SecuCheckProgServerResp;
import com.google.protobuf.ByteString;

public class HSValidate extends BaseValidate{
	
	
	private static final String TAG = HSValidate.class.getSimpleName();
	
	// ValidateState
	private static final int HSfail = 1;//握手失败
	private static final int HSprocess = 2;//握手中
	private static final int HSSuccess = 0;//握手成功
	
	
	//错误码
	private static final int SUCCESS = 0;// 成功
	private static final int SYSTEMERROR = 1;// 系统错误
	private static final int SESSIONIDEXPIRED = 11;// 会话ID过期
	private static final int CREDENTIALSEXPIRINGSOON = 21;// 身份凭证即将过期
	private static final int CREDENTIALSEXPIRED = 22;// 身份凭证过期
	private static final int CREDENTIALSILLEGAL = 23;// 身份凭证非法
	private static final int REQTOKENDUPLICATE = 31;// requestToken重复使用
	private static final int REQTOKENILLEGAL = 32;// requestToken非法
	

	
	// hand shake message type
	private static final int ClientReq = 1;
	private static final int ServerResp = 2;
	private static final int ServerCertResp = 3;
	private static final int ClientVerifyReq = 4;
	private static final int ClientVerifyResp = 5;
	private static final int ClientKeyReq = 6;
	private static final int ClientKeyResp = 7;
	private static final int ClientSecuritycheckdllReq = 8;
	private static final int ClientSecuritycheckdllResp = 9;
	private static final int ServerFinish = 10;
	
	//握手协议处理器
	IHandShakeProtocol handshakeProtocol;
	
	
	//走过的握手的步骤
	public static int step;
	//握手的状态
	//public static int ValidateState ;
	
	private static boolean isDownSecurity=false;
	
	private static String sessionID ;
    
//	public static HSTransProtocolParamet transProtocolParametchannel;
	
	private  String secretKey;//MOSE密钥
	
	private  String serverpublicKey;//服务器公钥
	
	private  byte[] sessionKey;//会话密钥
	
	
	byte CertificateIDType ;//客户端身份
	
	private  String getSecretKey() {
		secretKey = "11111111";
		return secretKey;
	}

	private  void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
		
	}


	private  String getServerpublicKey() {
		serverpublicKey= "30819D300D06092A864886F70D010101050003818B00308187028181009A05BF7854601DC5560EBE704DB52658BCECE3C0CB030A8808A24877AF563C5AEA70478556C6FEBD6144EB48A535610E78DC8F9174B145D16F063EB46A9461AB6C1378F19A1FDBA4E2F5F9D39239F2C9501981DF34015F6BCAC9DD99339DD3D8E5E02E9ECED2C6EF35617259E95CC078B9396148842FBCB6824707991B281A19020111";
		return serverpublicKey;
	}


	private  void setServerpublicKey(String serverpublicKey) {
		this.serverpublicKey = serverpublicKey;
	}


	/**
	 * 生成会话密钥
	 */	


	private  byte[] generalSessionKey() {
			
			//long b=(long)(Math.random()*100000000);//产生8位的整数随机数　
		//Math.random(System.currentTimeMillis());
		//Math a= new Math();
		    long b = System.currentTimeMillis();
			Log.d(TAG, "b="+b);
//			String x = ""+(""+b+Math.random()).hashCode();
			String x = ""+b;
			byte[] a =   x.substring(x.length()-9, x.length()-1).getBytes();
						
		
		return a;
	}

	private void setSessionKey(byte[] sessionKey){
    	this.sessionKey = sessionKey;
    }
    
	private byte[] getSessionKey(){
    	return this.sessionKey;
    }
	
	private byte[] enSessionKey() {
    	byte[] a = null;
		if (getSessionKey() != null){
			
			try {
				Log.d(TAG, "a="+StringUtil.byteArr2HexStr(getSessionKey()));
				a = RSA.encryptByPublicKey(StringUtil.byteArr2HexStr(getSessionKey()).getBytes(), getServerpublicKey());//服务器公钥加密
	//			a = RSA.encryptByPublicKey(a, getServerpublicKey());//服务器公钥加密
				//sessionKey = a;
				Log.d(TAG, StringUtil.byteArr2HexStr(a));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return a;
	}




	
	

	Object obj = new Object();
	int SCA ;	
	
	//asymmetric cryptographic algorithm		
	//服务器确认使用的非对称加密算法（1表示DSA，2表示RSA），取值为多种加密算法取值的或运算
	int ACA ;
	
	//Digital-Digest Algorithm		
	//服务器确认使用正文内容的数字摘要算法（1表示不支持，2表示MD5，3表示SHA-1），取值为多种加密算法取值的或运算
	int DDA ;
	
	//服务器返回随机码
	private static long serverRandomNum=0;
	
	


	public HSValidate(IChannel channel)
	{
		this.channel = channel;
	}

	
	public HSValidate()
	{
	}
	
	
	/**
	 * 处理收到的握手消息
	 */	
	public void receive(HSMsg msg)
	{
		HSMsgHead msgHead = msg.getHead();
		
		byte cmd = msgHead.getCommand();
		
		Log.d(TAG,"receive HSMsg cmd ="+ cmd);
		
		
		//连接响应
		if(cmd == HSMsgHead.CMD_SERVER_CONNECT_RESP)
		{
			MsgConnectServerResp.ConnectServerResp resp = MsgConnectServerResp.ConnectServerResp.getDefaultInstance();
			//解码
			resp = (ConnectServerResp)msg.getDeMsgBodyTemp().decode(resp);
  
			serverRandomNum = resp.getServerRandomNum();//服务器随机数
			Log.d(TAG, "CMD_SERVER_CONNECT_RESP3+"+serverRandomNum);
			SCA = resp.getUseSemCrypMeth();
			ACA = resp.getUseAsemCrypMeth() ;
			DDA = resp.getUseDigestMeth();
			
			//设置是否需要加密参数  这个加密只对数据协议有效 对握手无效
			HSTransProtocolParamet hpp = (HSTransProtocolParamet)channel.getSession().getTransProtocolParamet();
			hpp.setEncrypt(resp.getIsEncrypt());
	//		channel.getSession().setTransProtocolParameter(hpp);
			
			//收到的会话ID不变
			Log.d(TAG, "ProtocolRetCode"+resp.getRetResult().getNumber());
		    //这两个方法试验都试验下
			if(resp!=null  && resp.getRetResult().getNumber() == SUCCESS)
//			if(resp!=null  && resp.getRetResult().equals(ProtocolRetCode.SUCCESS))
			{
				serverRandomNum = resp.getServerRandomNum();//服务器随机数
				SCA = resp.getUseSemCrypMeth();//  .getSCA();
				ACA = resp.getUseAsemCrypMeth() ;// .getACA();
				DDA = resp.getUseDigestMeth();//
				
				resp.getRetResult().getNumber();
				//收到的会话ID不变
				if(resp.getSessionId().equals(sessionID)){
					//握手结束
					
					setValidateState(HSSuccess);
					
					Log.d(TAG, "HS step is ServerResp");
					step = ServerResp;
					//握手结束，等接收finish消息
//					synchronized (obj) {
//						obj.notifyAll();
//					}
					
				}else{
					//	是否验证客户端身份（0表示验证，1表示不验证）
					setValidateState(HSprocess);
					
					boolean isValidate = resp.getIsNeedVerify();
					if(isValidate){
						
						//发起验证客户端身份请求
						
						sessionID = resp.getSessionId();
						channel.getSession().setSessionID(sessionID);//设置新的sessionID
						if(getCertFromSecurityProgram()==null)
							CertificateIDType = 0x00;//requestToken
						else
							CertificateIDType = 0x01;//身份凭证
						
						hpp.setIdCertType(CertificateIDType);
	//					channel.getSession().setTransProtocolParameter(hpp);
						
						Log.d(TAG, "SendMsg_ClientVerifyReq and CertificateIDType is"+CertificateIDType);
						SendMsg_ClientVerifyReq(CertificateIDType);
						
					}
					
				}
						
			}
			else
			{
				//解码错误
				switch (resp.getRetResult().getNumber()){
					case 1:
						setValidateState(HSfail);
						break;
					
					default:
					    setValidateState(HSfail);
				}
				
			}
			
			
		}
		//返回服务器证书
		if(cmd == HSMsgHead.CMD_SERVER_CERT_RESP)
		{
			MsgCertServerResp.CertServerResp resp = MsgCertServerResp.CertServerResp.getDefaultInstance();
			//解码
			resp = (CertServerResp) msg.getDeMsgBodyTemp().decode(resp);
			
			Log.d(TAG, "HS step is ServerCertResp");	
			step = ServerCertResp;
			//返回结果retResult
			if(resp!=null  && resp.getRetResult().getNumber() == SUCCESS){
				//平台数字证书（x.509）
				ByteString cert= resp.getDigiCert();
				//格式类型（0表示DER二进制编码，1表示BASE64编码）
				int type = resp.getFormatType();
				saveCertficate (type,cert);
				setValidateState(HSprocess);
			}else{
				//解码错误
				switch (resp.getRetResult().getNumber()){
					case 1:
						setValidateState(HSfail);
						break;
					default:
					setValidateState(HSfail);
				}
				synchronized (obj) {
					obj.notifyAll();
				}
			}
			
		}
		//验证身份客户端响应
		if(cmd == HSMsgHead.CMD_CLIENT_VERIFY_RESP)
		{
			MsgAuthServerResp.AuthServerResp resp = MsgAuthServerResp.AuthServerResp.getDefaultInstance();
			
			//解码
			resp =  (AuthServerResp) msg.getDeMsgBodyTemp().decode(resp);
			setValidateState(HSprocess);
			
			if(resp!=null  && resp.getRetResult().getNumber() == SUCCESS){
				if(CertificateIDType==0x00){//身份验证为requestToken
					
					//发起下载安全体检程序的请求
					Log.d(TAG, "SendMsg_ClientSecurityProgramReq");
					SendMsg_ClientSecurityProgramReq();
				}else if(CertificateIDType==0x01){//身份验证为身份凭证
					//发起密钥协商请求
					Log.d(TAG, "SendMsg_ClientKeyReq");
					SendMsg_ClientKeyReq();
				}
				
			}else{
				//解码错误
				switch (resp.getRetResult().getNumber()){
				case 1:
					setValidateState(HSfail);
					break;
				case 21:
					//ValidateState = CREDENTIALSEXPIRINGSOON;
					setValidateState(CREDENTIALSEXPIRINGSOON);
					break;
				case 22:
					//ValidateState = CREDENTIALSEXPIRED;
					setValidateState(CREDENTIALSEXPIRED);
					break;
				case 23:
					//ValidateState = CREDENTIALSILLEGAL;
					setValidateState(HSfail);
					break;
				case 31:
					//ValidateState = REQTOKENDUPLICATE;
					setValidateState(REQTOKENDUPLICATE);
					break;
				case 32:
					//ValidateState = REQTOKENILLEGAL;
					setValidateState(REQTOKENILLEGAL);
					break;
				default:
					//ValidateState = SYSTEMERROR;
				setValidateState(HSfail);
			}
				Log.d(TAG, "HS step is ClientVerifyResp");
				step = ClientVerifyResp;
				synchronized (obj) {
					obj.notifyAll();
				}
			}
			
			
		}
		//密钥协商响应
		if(cmd == HSMsgHead.CMD_CLIENT_KEY_RESP)
		{
			MsgSeckeyServerResp.SeckeyServerResp resp = MsgSeckeyServerResp.SeckeyServerResp.getDefaultInstance();
			
			//解码
			resp = (SeckeyServerResp) msg.getDeMsgBodyTemp().decode(resp);
			
			//返回结果retResult
			if(resp!=null  && resp.getRetResult().getNumber() == SUCCESS){
				//ValidateState = Finish;//结束握手
				setValidateState(HSprocess);
				
			}else{
				//解码错误
				switch (resp.getRetResult().getNumber()){
				case 1:
					setValidateState(HSfail);
					break;
				default:
				setValidateState(HSfail);
			}
				Log.d(TAG, "HS step is ClientKeyResp");
				step = ClientKeyResp;
				synchronized (obj) {
					obj.notifyAll();
				}
			}

			
		}
		//返回安全体检程序
		if(cmd == HSMsgHead.CMD_SERVER_SECURITY_PROGRAM_RESP)
		{
			MsgSecuCheckProgServerResp.SecuCheckProgServerResp resp = MsgSecuCheckProgServerResp.SecuCheckProgServerResp.getDefaultInstance();
			//解码
			resp = (SecuCheckProgServerResp) msg.getDeMsgBodyTemp().decode(resp);
			
			//返回结果retResult
			if(resp!=null  && resp.getRetResult().getNumber() == SUCCESS){
			
			//安全体检程序（二进制流），使用会话密钥加密
			
			//保存安全体检程序
			saveSecurityProgram(resp.getSecuCheckProg());
			//ValidateState = Finish;//结束握手
			setValidateState(HSprocess);
			}else{
				//解码错误
				switch (resp.getRetResult().getNumber()){
				case 1:
					setValidateState(HSfail);
					break;
				default:
				setValidateState(HSfail);
			}
				Log.d(TAG, "HS step is ClientSecuritycheckdllResp");
				
				step = ClientSecuritycheckdllResp;
				synchronized (obj) {
					obj.notifyAll();
				}
			}
			
			
		}
		//握手结束响应
		if(cmd == HSMsgHead.CMD_SERVER_FINISH)
		{

			MsgHandShakeFinish.HandShakeFinish resp = MsgHandShakeFinish.HandShakeFinish.getDefaultInstance();
			//解码
			
			resp = (HandShakeFinish) msg.getDeMsgBodyTemp().decode(resp);
			
			//返回结果retResult
			if(resp!=null  && resp.getRetResult().getNumber() == SUCCESS){
				step = ServerFinish;
				if(!isDownSecurity){
					//ValidateState = Finish;;//结束握手
					setValidateState(HSSuccess);
					synchronized (obj) {
						obj.notifyAll();
					}
				}
				
				isDownSecurity=false;
				
			}
			
		}
	
	}
	

	@Override
	public int doValidate() {
		// 发送第一个 握手请求消息
		
		//需要判断本地是否有 签名文件等等
		isDownSecurity=false;
		HSTransProtocolParamet hpp = (HSTransProtocolParamet)channel.getSession().getTransProtocolParamet();
		hpp.setContentState(TranMsgHead.MSG_TYPE_HANDSHAKE);
	//	channel.getSession().setTransProtocolParameter(hpp);
		
		SendMsg_ClientConnectReq();
		
		if(getValidateState()!=HSSuccess){	//握手已经成功，不需要wait
			
			try {
				synchronized (obj) {
					obj.wait(30000);
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
			

		isDownSecurity=false;
		Log.d(TAG, "Validate is finish");
		
		if(getValidateState()==HSSuccess){
			
			hpp.setContentState(TranMsgHead.MSG_TYPE_DATA);
		}
		
		
		return getValidateState();
		
		
		
	}

		
	/**
	 * 保存服务器证书
	 */
	private void saveCertficate (int type,ByteString cert){
		
	}
	/**
	 * 保存安全体检程序
	 * @throws IOException 
	 */
	
	private void saveSecurityProgram (ByteString byteString){
		//contentByte = byteString.toByteArray();
		
		isDownSecurity=true;
		setDeviceID("88888888");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 

		Log.d("", "保存安全体检程序");
		
		SendMsg_ClientConnectReq();//重新发送连接请求
	}
	
	private void setDeviceID(String deviceId ){
		
		if(deviceId == null){
			
			deviceId = "";
		}
		ConfigModule configModule = Config.getInstance().getMoudle("Device");
		if(configModule!=null)
		{
			configModule.setStringItem("token",deviceId);
//			deviceId 
//			configModule.setStringItem("deviceId",deviceId);
		}
	}
	
	/**
	 *获取到安全体检接口的身份凭证
	 * @throws IOException 
	 */
	
	private byte[] getCertFromSecurityProgram (){
		
//		byte[] contentByte =null;
//		ConfigModule configModule = Config.getInstance().getMoudle("Device");
//		String token = null;	
//		if(configModule!=null)
//			{
//				token = configModule.getStringItem("token",null);
//
//			}
//		
//		if(token!=null){
//			contentByte = token.getBytes();
//		
//			
//		}
//		Log.d(TAG, "" + contentByte);
//		return contentByte;
		
		String strImisID =  M_Device.getImisID();
		if(strImisID==null)
		{
			strImisID = "";
		}
		
		return strImisID.getBytes();
		
		//return "88888888".toString();
		
	}
	
	/**
	 * long转byte
	 * @throws IOException 
	 */
	private  byte[] long2bytes(long num) {
		   byte[] b = new byte[8];
		   for (int i = 0; i < 8; i++) {
		    b[i] = (byte) (num >>> (56 - i * 8));
		   }
		   return b;
	}

	/**
	 * byte转long
	 * @throws IOException 
	 */
	private  long bytes2long(byte[] b) {

		   int mask = 0xff;
		   int temp = 0;
		   int res = 0;
		   for (int i = 0; i < 8; i++) {
		    res <<= 8;
		    temp = b[i] & mask;
		    res |= temp;
		   }
		   return res;
	}

	
	/**
	 * 建立连接请求
	 * @throws IOException 
	 */	
	private void SendMsg_ClientConnectReq(/*参数留待添加*/)
	{
        
		HSMsg msg = new HSMsg();
		
		HSTransProtocolParamet hpp = (HSTransProtocolParamet)channel.getSession().getTransProtocolParamet();

		ACA = 1;
		SCA = 1;
		DDA = 1;
		hpp.setACA(ACA);
		hpp.setSCA(SCA);
		hpp.setDDA(DDA);
		hpp.setEncrypt(false);
		hpp.setDownloadCredence(false);
		
		sessionID = channel.getSession().getSessionID();
//		if(sessionID==null)
//		{
			sessionID = "";
//		}
		
		MsgConnectClientReq.ConnectClientReq.Builder req = MsgConnectClientReq.ConnectClientReq.newBuilder();
		req.setSessionId(sessionID);
		req.setIsEncrypt(false);//不加密
		req.setIsNeedServCert(false);//不下载证书
		req.setSemCrypMethods(SCA);
		req.setAsemCrypMethods(ACA);
		req.setDigestMethods(DDA);
		req.setMoseVersion("实验123adcABC_ 版本");
		
		HSMsgHead head = new HSMsgHead();
		head.setCommand(HSMsgHead.CMD_CLIENT_CONNECT_REQ);
	
		
		msg.setHead(head);
		msg.setBody(req.build());
		
		int errorCode = channel.send(msg);// 发送错误码
		
		if(errorCode == 0){
			step = ClientReq;
			setValidateState(HSprocess);
			Log.d(TAG, "SendMsg_ClientConnectReq");
		}else{
			step = ClientReq;
			setValidateState(HSfail);
			Log.d(TAG, "SendMsg_ClientConnectReq failed");
			synchronized (obj) {
				obj.notifyAll();
			}
		}
		
	}

	
	
	
	/**
	 * 验证客户端身份请求
	 * @throws Exception 
	 * @throws IOException 
	 */		
	private void SendMsg_ClientVerifyReq(byte CertificateIDType) {
		
		HSMsg msg = new HSMsg();
		
		MsgAuthClientReq.AuthClientReq.Builder req = MsgAuthClientReq.AuthClientReq.newBuilder();
		String temp = channel.getSession().getSessionID();
		req.setSessionId(temp);
		if(CertificateIDType == 0x00){
			MsgAuthClientReq.AuthClientReq.IdCertType certType = MsgAuthClientReq.AuthClientReq.IdCertType.valueOf(0);
			req.setIdType(certType);//身份凭证类型（0表示requestToken，1表示身份凭证）

		}else if(CertificateIDType == 0x01){
			MsgAuthClientReq.AuthClientReq.IdCertType certType = MsgAuthClientReq.AuthClientReq.IdCertType.valueOf(1);
			req.setIdType(certType);//身份凭证类型（0表示requestToken，1表示身份凭证）
		}
		
//		req.setIdCertContent(value);
		//身份凭证，取值如下：
		//0：平台公钥加密（secretKey加密（终端设备MAC地址）|服务器随机数）
		//1：平台公钥加密（安全体检接口获取到的身份凭证|服务器随机数）
		//加密算法使用服务端上一步选择的加密算法
		
		HSTransProtocolParamet hpp = (HSTransProtocolParamet)channel.getSession().getTransProtocolParamet();
		String mac = hpp.getMac();
	//	channel.getSession().setTransProtocolParameter(hpp);
		
		com.google.protobuf.ByteString contengByteString = null;
		
		if(CertificateIDType==0x00){
			

//			StringBuffer contentbuffer = new StringBuffer().append(mac).append('|').append(serverRandomNum);

			String secret = getSecretKey();//MOSE密钥
			byte[] contentByte = null;
			try {
                    long encryptStart = System.currentTimeMillis();
			        DES des = new DES(secret);
                    byte[] macDESResult =des.encrypt(mac.getBytes());// MOSE密钥加密
                    Log.d(TAG, "macDESResult:"+StringUtil.byteArr2HexStr(macDESResult).toUpperCase());
                    String content = StringUtil.byteArr2HexStr(macDESResult).toUpperCase()+"|"+serverRandomNum;
                    contentByte = RSA.encryptByPublicKey(content.getBytes(), getServerpublicKey());//服务器公钥加密
                    long cost = System.currentTimeMillis() - encryptStart;
                    Log.d(TAG, "Encrypt cost time:"+cost);
                    Log.d(TAG, "Encrypt result:"+StringUtil.byteArr2HexStr(contentByte));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			contengByteString = com.google.protobuf.ByteString.copyFrom(contentByte);
			req.setIdCertContent(contengByteString);
		

			
		}else if(CertificateIDType==0x01){
			
			StringBuffer contentbuffer = new StringBuffer().append(new String(getCertFromSecurityProgram())).append('|').append(serverRandomNum);
			
			String contentString = contentbuffer.toString();
			
			byte[] contentByte = contentString.getBytes();
			 
			try {
				long encryptStart = System.currentTimeMillis();
				contentByte = RSA.encryptByPublicKey(contentByte, getServerpublicKey());//服务器公钥加密
				long cost = System.currentTimeMillis() - encryptStart;
				Log.d(TAG, "Encrypt cost time:"+cost);
                Log.d(TAG, "Encrypt result:"+StringUtil.byteArr2HexStr(contentByte));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			contengByteString = com.google.protobuf.ByteString.copyFrom(contentByte);
			req.setIdCertContent(contengByteString);
		}
	    	  
		//终端设备MAC地址
		req.setTermMacAddr(mac);
		
		HSMsgHead head = new HSMsgHead();
		head.setCommand(HSMsgHead.CMD_CLIENT_VERIFY_REQ);
		
		msg.setHead(head);
		msg.setBody(req.build());
		
		int errorCode = channel.send(msg);// 发送错误码
		
		if(errorCode == 0){
			step = ClientVerifyReq;
			setValidateState(HSprocess);
			Log.d(TAG, "SendMsg_ClientVerifyReq"+"CertificateIDType"+CertificateIDType);
		}else{
			step = ClientVerifyReq;
			setValidateState(HSfail);
			Log.d(TAG, "SendMsg_ClientVerifyReq failed");
			synchronized (obj) {
				obj.notifyAll();
			}
		}
	}
	
	
	
	/**
	 * 密钥协商请求
	 * @throws IOException 
	 */	
	private void SendMsg_ClientKeyReq(/*参数留待添加*/){
		
		HSMsg msg = new HSMsg();
		
		setSessionKey(generalSessionKey());
		MsgSecKeyClientReq.SecKeyClientReq.Builder req= MsgSecKeyClientReq.SecKeyClientReq.newBuilder();
		sessionID = channel.getSession().getSessionID();
		req.setSessionId(sessionID);
		
		
		com.google.protobuf.ByteString contengByteString = com.google.protobuf.ByteString.copyFrom(enSessionKey());
		
		HSTransProtocolParamet hpp = (HSTransProtocolParamet)channel.getSession().getTransProtocolParamet();
		hpp.setSessionKey(getSessionKey());
	//	channel.getSession().setTransProtocolParameter(hpp);
		req.setSecuKey(contengByteString);//会话密钥
		
		
		HSMsgHead head = new HSMsgHead();
		head.setCommand(HSMsgHead.CMD_CLIENT_KEY_REQ);
		
		msg.setHead(head);
		msg.setBody(req.build());
		
		
		int errorCode = channel.send(msg);// 发送错误码
		
		if(errorCode == 0){
			step = ClientKeyReq;
			setValidateState(HSprocess);
			Log.d(TAG, "SendMsg_ClientKeyReq");
		}else{
			step = ClientKeyReq;
			setValidateState(HSfail);
			Log.d(TAG, "SendMsg_ClientKeyReq failed");
			synchronized (obj) {
				obj.notifyAll();
			}
		}
		
	}
	
	
	
	/**
	 * 下载安全体检程序请求
	 * @throws IOException 
	 */	
	private void SendMsg_ClientSecurityProgramReq(/*参数留待添加*/){
		
		HSMsg msg = new HSMsg();
		
		MsgSecuCheckProgClientReq.SecuCheckProgClientReq.Builder req= MsgSecuCheckProgClientReq.SecuCheckProgClientReq.newBuilder();
		sessionID = channel.getSession().getSessionID();
		req.setSessionId(sessionID);
		
		
		HSMsgHead head = new HSMsgHead();
		head.setCommand(HSMsgHead.CMD_CLIENT_SECURITY_PROGRAM_REQ);
		
		msg.setHead(head);
		msg.setBody(req.build());
		
		
		int errorCode = channel.send(msg);// 发送错误码
		
		if(errorCode == 0){
			step = ClientSecuritycheckdllReq;
			setValidateState(HSprocess);
			Log.d(TAG, "SendMsg_ClientSecurityProgramReq");
		}else{
			step = ClientSecuritycheckdllReq;
			setValidateState(HSfail);
			Log.d(TAG, "SendMsg_ClientSecurityProgramReq failed");
			synchronized (obj) {
				obj.notifyAll();
			}
		}
		
	}



}
