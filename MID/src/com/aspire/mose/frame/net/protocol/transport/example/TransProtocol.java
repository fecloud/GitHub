package com.aspire.mose.frame.net.protocol.transport.example;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import android.util.Log;

import com.aspire.mose.frame.crypto.DES;
import com.aspire.mose.frame.net.channel.IChannel;
import com.aspire.mose.frame.net.channel.hschannel.HSTransProtocolParamet;
import com.aspire.mose.frame.net.protocol.IDataProtocolObject;
import com.aspire.mose.frame.net.protocol.transport.ITransInputListener;
import com.aspire.mose.frame.net.protocol.transport.ITransProtocol;
import com.aspire.mose.frame.net.protocol.transport.ITransProtocolObject;


/**
 * 实例化的一个连接信息输入处理器
 * 仅仅负责将连接的输入byte[] 转换成传输对象，保证传输消息完成
 * 完整的消息 将丢给握手器处理 握手器鉴别出是 自己处理的握手消息 还是业务承载消息
 * @author liangbo
 *
 * 2011-7-14 下午01:28:47
 *  
 * TransProtocol
 *
 */
public class TransProtocol implements ITransProtocol{
	
	
	private static final String TAG = TransProtocol.class.getSimpleName();
	
	private static final int DECODE_TYPE_HEAD = 1;//正在解析头	
	private static final int DECODE_TYPE_BODY = 2;//正在解析体
	private static final int DECODE_TYPE_SUMMARY = 3;//正在解析摘要或校验数据
	
	//通道对象
	IChannel channel;
	



	//目前解析状态
	int decodeType = DECODE_TYPE_HEAD; 
	
	//包头大小
	int headSize = TranMsgHead.HEAD_LENGTH;
	//包体得大小
	int bodySize = 0;//需要从包头解析后赋值
//	//数据包校验码的大小
//	int summmarySize = TranMsg.SUMMARY_LENGTH_NONE;
	
	//当前读到的数据的位置
	int readFlag = 0;
	
	
	
	//不足够的数据  可能是一个包头或包体或校验 不足，无法解析  需要下个网络传输的byte[]来完整
	byte[] deficitData=null;

	//这是一个丢给上层协议的完整的传输消息包
	ITransProtocolObject<TranMsgHead,byte[],byte[]> transMsg=null; 
	
	
    //处理传输消息的处理器
	//ITransInputListener listener;
	Map<String,ITransInputListener> mapTransInputListener = new HashMap<String,ITransInputListener>();
	
	
	public TransProtocol()
	{
	}

	public TransProtocol(IChannel channel)
	{
		this.channel = channel;
	}
	
	public void setChannel(IChannel channel) {
		this.channel = channel;
	}
	
//	public ITransInputListener getHkTransInputListener() {
//		return hkTransInputListener;
//	}
//
//	public void setHkTransInputListener(ITransInputListener hkTransInputListener) {
//		this.hkTransInputListener = hkTransInputListener;
//	}
//
//	public ITransInputListener getProtoTransInputListener() {
//		return protoTransInputListener;
//	}
//
//	public void setProtoTransInputListener(
//			ITransInputListener protoTransInputListener) {
//		this.protoTransInputListener = protoTransInputListener;
//	}


	public void addTransInputListener(String key ,ITransInputListener listener)
	{
		mapTransInputListener.put(key, listener);
	}
	
	

	

	@Override
	public void receive(byte[] b)  throws IOException{
		
		Log.d(TAG, "receive() receive connect:  b.length="+b.length);
		Log.d(TAG, "receive() DATA= |"+new String(b,"UTF-8")+"|");
		
		//检查输入的byte是否有效
		if(b==null || b.length<=0)
		{
			throw new IOException("byte[] b is null");
		}
		
		//将数据装入流
		DataInputStream dis =new DataInputStream(new ByteArrayInputStream(b));
		
			while(dis.available()>0)//循环处理传入消息一直到无数据处理为止
			{
				//如果状态是正在解析头 第一次new的时候 就是解析头
				if(decodeType==DECODE_TYPE_HEAD)
				{
					//获取一个消息头
					TranMsgHead tmh = procHead(dis);
					if(tmh!=null)
					{						
						//说明消息头解释正常
						
						//实例化一个传输消息
						transMsg = new TranMsg();	
						//填充消息头
						transMsg.setHead(tmh);
						//清除 残留数据
						deficitData = null;
						//将状态设置到开始解析报文体
						decodeType = DECODE_TYPE_BODY;
						//设置报文体的大小
						bodySize = tmh.getLength();
					}
					else
					{
						//取得空又没有报错 说明本次的消息不够生成一个消息体
						//进入下个消息循环或等待下次网络数据推送
					}
				}
				else if(decodeType==DECODE_TYPE_BODY)
				{
					//如果状态是正在解析体
					//获取一个消息头
					byte[] tmb = procBody(dis);
					if(tmb!=null)
					{
						//说明消息体解释正常
						//填充消息体
						transMsg.setBody(tmb);
						
						//清除 残留数据
						deficitData = null;
						
						//从通道的session中获取校验包大小 根据握手的协商结果 会有不同的summmarySize
						int summmarySize = ((HSTransProtocolParamet)channel.getSession().getTransProtocolParamet()).getSummmarySize();
						
						//判断如果校验包大小不是0 则进入校验包获取处理
						if(summmarySize!=HSTransProtocolParamet.SUMMARY_LENGTH_NONE)
						{
							//将状态设置到开始解析校验包
							decodeType = DECODE_TYPE_SUMMARY;
						}
						else
						{
							//说明不需要处理校验包 直接回调上层协议
							//这是一个完整的消息 调用上层的传输消息处理器 回调给传输消息处理
							
							pushUpLayerInputListener(transMsg);
							//将状态设置到开始解析报文头
							decodeType = DECODE_TYPE_HEAD;
						}
						
					}
					else
					{
						//取得空又没有报错 说明本次的消息不够生成一个消息校验
						//进入下个消息循环或等待下次网络数据推送
					}
				}
				else if(decodeType==DECODE_TYPE_SUMMARY)
				{
					//如果状态是正在解析摘要
					//获取一个消息头
					byte[] tms = procSummmary(dis);
					if(tms!=null)
					{
						//说明消息摘要解释正常
						//填充消息体
						transMsg.setSummary(tms);
						
						//清除 残留数据
						deficitData = null;						
						
						//说明不需要处理校验包 直接回调上层协议
						//这是一个完整的消息 调用上层的传输消息处理器 回调给传输消息处理
						pushUpLayerInputListener(transMsg);
						//将状态设置到开始解析报文头
						decodeType = DECODE_TYPE_HEAD;						
					}
					else
					{
						//取得空又没有报错 说明本次的消息不够生成一个消息校验
						//进入下个消息循环或等待下次网络数据推送
					}
				}
			}
			//关闭流
			dis.close();
			dis = null;

	}
	
	
	private void pushUpLayerInputListener(ITransProtocolObject<?, ?, ?>transMsg)
	{
		
		//从通道的session中获取握手协议参数
		HSTransProtocolParamet transProtocolParamet = (HSTransProtocolParamet)channel.getSession().getTransProtocolParamet();
		
		//会话过程是否需要加密（0不需要，1需要）这个只对应用数据有效  对握手无效
		boolean encrypt = transProtocolParamet.isEncrypt();
		//encrypt = false;
		//asymmetric cryptographic algorithm	用来加握手中使用的证书 密钥等的
		//非对称加密算法（1表示DSA，2表示RSA），取值为多种加密算法取值的或运算,注意服务器返回的只有一个
		//int ACA = transProtocolParamet.getACA() ;
		
		//Digital-Digest Algorithm		
		//正文内容的数字摘要算法（1表示不支持，2表示MD5，3表示SHA-1），取值为多种加密算法取值的或运算,注意服务器返回的只有一个
		//int DDA = transProtocolParamet.getDDA();
		
		
		//传输协议承载的内容数据  是握手协议 还是 数据协议
		int msgType = ((TranMsg)transMsg).getHead().msgType;
		
		//如果传输中设置了需要加密，则此处需要将传输正文解密  这个只对应用数据有效  对握手无效
		if(encrypt  &&  msgType==TranMsgHead.MSG_TYPE_DATA)
		{
			//symmetric cryptographic algorithm		用来加密传输协议正文的
			//对称加密算法（1表示DES，2表示3DES，4表示AES），取值为多种加密算法取值的或运算,注意服务器返回的只有一个
			int SCA = transProtocolParamet.getSCA();	
			if(SCA==HSTransProtocolParamet.SCA_DES)
			{
				
				try {
					DES tDES = new DES( transProtocolParamet.getSessionKey() );
					//解密
					((TranMsg)transMsg).setBody(tDES.decrypt(((TranMsg)transMsg).getBody()) );
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
			else if(SCA==HSTransProtocolParamet.SCA_AES)
			{
				((TranMsg)transMsg).setBody( /* AES解码函数 */(((TranMsg)transMsg).getBody()) );
			}
			else if(SCA==HSTransProtocolParamet.SCA_3DES)
			{
				((TranMsg)transMsg).setBody( /* 3DES解码函数 */(((TranMsg)transMsg).getBody()) );
			}
			else
			{
				Log.e(TAG, "pushUpLayerInputListener() error SCA");
			}
		}
		
		//将解码好的传输消息，交给传输协议处理器来处理
		if(mapTransInputListener!=null && mapTransInputListener.size()>0)
		{
			Log.d(TAG, "msgType = "+ msgType);
			if(msgType==TranMsgHead.MSG_TYPE_DATA)
			{
				ITransInputListener ls= mapTransInputListener.get(IChannel.KEY_MessageCenter);
				if(ls!=null)
				{
					Log.d(TAG, "msgType = MSG_TYPE_DATA");
					ls.receive(transMsg);
				}
				else
				{
					Log.e(TAG, "receive() ITransInputListener is null,head.msgType= "+msgType +" is error");
				}
			}
			else if(msgType==TranMsgHead.MSG_TYPE_HANDSHAKE)
			{
				Log.d(TAG, "msgType = MSG_TYPE_HANDSHAKE");
				ITransInputListener ls= mapTransInputListener.get(IChannel.KEY_Validate);
				if(ls!=null)
				{
					ls.receive(transMsg);
				}
				else
				{
					Log.e(TAG, "receive() ITransInputListener is null,head.msgType= "+msgType +" is error");
				}
			}
			else
			{
				Log.e(TAG, "receive() head.msgType= "+msgType +" is error");
			}
		}		
		else
		{
			Log.e(TAG, "pushUpLayerInputListener() mapTransInputListener is null");
		}
	}
	
	/**
	 * 处理消息头
	 * @param dis
	 * @throws IOException 
	 */
	private TranMsgHead procHead(DataInputStream dis) throws IOException
	{
		
		//构建一个完整的报文头
		TranMsgHead tranMsgHead = null;
		
		//判断是否还有残留数据
		if(deficitData!=null && deficitData.length>0 )
		{
			//判断本次需要的报文头得数据长度 全长度-上次残留长度
			int  needHeadLength =  headSize -deficitData.length;
			
			//说明本次的长度还是不足一个报文头 需要等待下次解析
			int dataLength = dis.available();
			if(dataLength <needHeadLength )
			{
				//将本次数据添加到残留数据中  继续等待下次处理
				byte[] data = new byte[dataLength];
				//从流里读出全部的数据
				dis.read(data);
				//将数据装入残留数据中
				addDeficitData(data);
				//返回空 表示本次读取装载不足以获取到一个有效的消息头  
				//进入等待下个tcp通知包 如果出错则通过异常抛出
				return null;
			}
			else
			{
				//说明数据包头足够解析报文头 提取数据解析报文头
				byte[] data = new byte[headSize -deficitData.length];
				//从流里读出足够和残留数据拼装报文头得数据
				dis.read(data);
				//将残留数据和刚才的读取数据合并成报文头数据
				byte[] hdata = addArray(deficitData,data);
				
				tranMsgHead = new TranMsgHead();
				DataInputStream hdis =new DataInputStream(new ByteArrayInputStream(hdata));
				tranMsgHead.decode(hdis);
				//关闭流
				hdis.close();
				hdis = null;
				//解析出来  返回消息头
				return tranMsgHead;
				
				
			}
		}
		else //没有残留数据  表示是一个新的
		{
			//判断本次需要的报文头得数据长度 全长度
			int  needHeadLength =  headSize;
			
			//说明本次的长度还是不足一个报文头 需要等待下次解析
			int dataLength = dis.available();
			if(dataLength <needHeadLength )
			{
				//将本次数据添加到残留数据中  继续等待下次处理
				byte[] data = new byte[dataLength];
				//从流里读出全部的数据
				dis.read(data);
				//将数据装入残留数据中
				addDeficitData(data);
				//返回空 表示本次读取装载不足以获取到一个有效的消息头  
				//进入等待下个tcp通知包 如果出错则通过异常抛出
				return null;
			}
			else
			{
				//说明数据包头足够解析报文头 提取数据解析报文头
				byte[] hdata = new byte[headSize];
				//从流里读出足够和残留数据拼装报文头得数据
				dis.read(hdata);			
				
				tranMsgHead = new TranMsgHead();
				DataInputStream hdis =new DataInputStream(new ByteArrayInputStream(hdata));
				tranMsgHead.decode(hdis);
				//关闭流
				hdis.close();
				hdis = null;
				
				//解析出来  返回消息头
				return tranMsgHead;
				
			}
		}		
	}
	
	/**
	 * 处理消息体
	 * @param dis
	 * @throws IOException 
	 */
	private byte[] procBody(DataInputStream dis) throws IOException
	{
		//构建一个完整的消息体
		byte[] tranMsgBody = null;
		
		//判断是否还有残留数据
		if(deficitData!=null && deficitData.length>0 )
		{
			//判断本次需要的报文体得数据长度 全长度-上次残留长度
			int  needBodyLength =  bodySize -deficitData.length;
			
			//说明本次的长度还是不足一个报文体 需要等待下次解析
			int dataLength = dis.available();
			if(dataLength <needBodyLength )
			{
				//将本次数据添加到残留数据中  继续等待下次处理
				byte[] data = new byte[dataLength];
				//从流里读出全部的数据
				dis.read(data);
				//将数据装入残留数据中
				addDeficitData(data);
				//返回空 表示本次读取装载不足以获取到一个有效的消息体  
				//进入等待下个tcp通知包 如果出错则通过异常抛出
				return null;
			}
			else
			{
				//说明数据包头足够解析报文头 提取数据解析报文头
				byte[] data = new byte[bodySize -deficitData.length];
				//从流里读出足够和残留数据拼装报文头得数据
				dis.read(data);
				//将残留数据和刚才的读取数据合并成报文体数据
				tranMsgBody = addArray(deficitData,data);
				data = null;

				//解析出来  返回消息头
				return tranMsgBody;
				
			}
		}
		else //没有残留数据  表示是一个新的
		{
			//判断本次需要的报文体得数据长度 全长度
			int  needHeadLength =  bodySize;
			
			//说明本次的长度还是不足一个报文头 需要等待下次解析
			int dataLength = dis.available();
			if(dataLength <needHeadLength )
			{
				//将本次数据添加到残留数据中  继续等待下次处理
				byte[] data = new byte[dataLength];
				//从流里读出全部的数据
				dis.read(data);
				//将数据装入残留数据中
				addDeficitData(data);
				//返回空 表示本次读取装载不足以获取到一个有效的消息头  
				//进入等待下个tcp通知包 如果出错则通过异常抛出
				return null;
			}
			else
			{
				//说明数据包头足够解析报文头 提取数据解析报文头
				tranMsgBody = new byte[bodySize];
				//从流里读出足够和残留数据拼装报文头得数据
				dis.read(tranMsgBody);

				return tranMsgBody;
				
			}
		}		
	}
	
	/**
	 * 处理消息摘要
	 * @param dis
	 * @throws IOException 
	 */
	private byte[] procSummmary(DataInputStream dis) throws IOException
	{
		//从通道的session中获取校验包大小 根据握手的协商结果 会有不同的summmarySize
		int summmarySize = ((HSTransProtocolParamet)channel.getSession()).getSummmarySize();
		
		//构建一个完整的消息摘要
		byte[] tranMsgSummmary = null;
		
		//判断是否还有残留数据
		if(deficitData!=null && deficitData.length>0 )
		{
			
			//判断本次需要的报文体得数据长度 全长度-上次残留长度
			int  needBodyLength =  summmarySize -deficitData.length;
			
			//说明本次的长度还是不足一个报文体 需要等待下次解析
			int dataLength = dis.available();
			if(dataLength <needBodyLength )
			{
				//将本次数据添加到残留数据中  继续等待下次处理
				byte[] data = new byte[dataLength];
				//从流里读出全部的数据
				dis.read(data);
				//将数据装入残留数据中
				addDeficitData(data);
				//返回空 表示本次读取装载不足以获取到一个有效的消息体  
				//进入等待下个tcp通知包 如果出错则通过异常抛出
				return null;
			}
			else
			{
				//说明数据包头足够解析报文头 提取数据解析报文头
				byte[] data = new byte[summmarySize -deficitData.length];
				//从流里读出足够和残留数据拼装报文头得数据
				dis.read(data);
				//将残留数据和刚才的读取数据合并成报文体数据
				tranMsgSummmary = addArray(deficitData,data);
				data = null;

				//解析出来  返回消息头
				return tranMsgSummmary;
				
			}
		}
		else //没有残留数据  表示是一个新的
		{
			//判断本次需要的报文体得数据长度 全长度
			int  needHeadLength =  summmarySize;
			
			//说明本次的长度还是不足一个报文头 需要等待下次解析
			int dataLength = dis.available();
			if(dataLength <needHeadLength )
			{
				//将本次数据添加到残留数据中  继续等待下次处理
				byte[] data = new byte[dataLength];
				//从流里读出全部的数据
				dis.read(data);
				//将数据装入残留数据中
				addDeficitData(data);
				//返回空 表示本次读取装载不足以获取到一个有效的消息头  
				//进入等待下个tcp通知包 如果出错则通过异常抛出
				return null;
			}
			else
			{
				//说明数据包头足够解析报文头 提取数据解析报文头
				tranMsgSummmary = new byte[summmarySize];
				//从流里读出足够和残留数据拼装报文头得数据
				dis.read(tranMsgSummmary);

				return tranMsgSummmary;
				
			}
		}		
	}
	
	
//	private byte[] subArray(byte[] a,int pos,int length)
//	{
//		a.
//	}
	
	private byte[] addArray(byte[] a,byte[] b)
	{
		int la = 0;
		if(a!=null && a.length>0)
		{
			la = a.length;
		}
		
		int lb = 0;
		if(b!=null && b.length>0)
		{
			lb = b.length;
		}
		
		byte[] tempB=new byte[la+lb];
		if(la>0)
		{
			System.arraycopy(a,0,tempB,0,la);//拷贝第一个数组 
		}
		
		if(lb>0)
		{
			System.arraycopy(b,0,tempB,la,lb);//拷贝第二个数组 
		}
		
		return tempB;
	}

	
	/**
	 * 将新的数据添加到残存数据中
	 * @param newb
	 */
	private void addDeficitData(byte[] newb){
		
		byte[] tempB = addArray(deficitData,newb);
		deficitData = tempB;
		tempB = null;
	}
	
	/**
	 * 利用残留数据和 新数据拼装一个新的报文头出来
	 * @param newb
	 * @return
	 */
//	private byte[] getHeadFromDeficitData(byte[] newb){
//		
//		byte[] tempB = addArray(deficitData,newb);
//		
//	}
	
	@Override
	public ITransProtocolObject<?, ?, ?> pack(IDataProtocolObject dataProtocolObject)
			throws IOException {
		
		//设置默认的内容协议状态是握手协议
		HSTransProtocolParamet hpp = (HSTransProtocolParamet)channel.getSession().getTransProtocolParamet();
		
		byte contentState =  hpp.getContentState()  /*TranMsgHead.MSG_TYPE_HANDSHAKE*/;
		
		//获取设置传输协议所需要的一些参数
//		if(protocolStates!=null && (protocolStates instanceof HSTransProtocolParamet ))
//		{
//			contentState = ((HSTransProtocolParamet)protocolStates).getContentState();
// 
//			//....其他参数
//		}
		
		
		if(dataProtocolObject!=null)
		{
			ByteArrayOutputStream sbos = new ByteArrayOutputStream();
			DataOutputStream sdos = new DataOutputStream(sbos);
			dataProtocolObject.encode(sdos);
			

			
			byte[] body = sbos.toByteArray();
			
			//从通道的session中获取握手协议参数
			HSTransProtocolParamet transProtocolParamet = (HSTransProtocolParamet)channel.getSession().getTransProtocolParamet();
			
			//会话过程是否需要加密（0不需要，1需要） 这个只对数据协议有效 对握手无效
			boolean encrypt = transProtocolParamet.isEncrypt();
			//encrypt= false;
			//asymmetric cryptographic algorithm	用来加握手中使用的证书 密钥等的
			//非对称加密算法（1表示DSA，2表示RSA），取值为多种加密算法取值的或运算,注意服务器返回的只有一个
			//int ACA = transProtocolParamet.getACA() ;
			
			//Digital-Digest Algorithm		
			//正文内容的数字摘要算法（1表示不支持，2表示MD5，3表示SHA-1），取值为多种加密算法取值的或运算,注意服务器返回的只有一个
			//int DDA = transProtocolParamet.getDDA();
			
						
			//如果传输中设置了需要加密，则此处需要将传输正文加密 这个只对应用数据有效  对握手无效
			if(encrypt  &&  contentState==TranMsgHead.MSG_TYPE_DATA)
			{
				//symmetric cryptographic algorithm		用来加密传输协议正文的
				//对称加密算法（1表示DES，2表示3DES，4表示AES），取值为多种加密算法取值的或运算,注意服务器返回的只有一个
				int SCA = transProtocolParamet.getSCA();	
				if(SCA==HSTransProtocolParamet.SCA_DES)
				{
					try {
						byte[] sessionKey = transProtocolParamet.getSessionKey() ;
						DES tDES = new DES(sessionKey );
						
						body = tDES.encrypt(body);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				else if(SCA==HSTransProtocolParamet.SCA_AES)
				{
					body  = /* AES编码函数 */(body);
				}
				else if(SCA==HSTransProtocolParamet.SCA_3DES)
				{
					body  = /* 3DES编码函数 */(body);
				}
				else
				{
					Log.e(TAG, "pack() error SCA");
				}
			}
			
			
			
			
			
			TranMsg tranMsg = new TranMsg();
			
			
			
			TranMsgHead tranMsgHead = new TranMsgHead();
			//设置为内容使用什么协议 协议
			tranMsgHead.setMsgType(contentState);
			//设置包体长度
			tranMsgHead.setLength(body.length);
			
			tranMsg.setHead(tranMsgHead);
			tranMsg.setBody(body);
			
			
			int summmarySize = transProtocolParamet.getSummmarySize();
			//判断类型  对数据进行加密 或摘要处理
			//这个暂时还没有得到
//			byte[] summary = new byte[summmarySize];
//			tranMsg.setSummary(summary);
			
			return tranMsg;
		}
		
		
		
		
		return null;
	}



	
}
