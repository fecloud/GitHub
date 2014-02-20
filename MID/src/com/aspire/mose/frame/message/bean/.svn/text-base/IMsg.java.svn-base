package com.aspire.mose.frame.message.bean;

import com.aspire.mose.frame.net.protocol.IDataProtocolObject;

public interface IMsg<Head,Body> extends IDataProtocolObject{

	public Head getHead() ;
	public void setHead(Head head) ;
	public Body getBody() ;
	public void setBody(Body body) ;	
	public IBodyCatch getBodyCatch() ;
	public void setBodyCatch(IBodyCatch bodyCatch) ;
	

	

	

}

//public class IMsg implements IDataProtocolObject{
//
//	private MsgHead msgHead;
//
//	public IDataProtocolObject msgBody;
//
//	private DeMsgBodyTemp deMsgBodyTemp;
//
//	public DeMsgBodyTemp getDeMsgBodyTemp() {
//		return deMsgBodyTemp;
//	}
//
//	public void setMsgBody(IDataProtocolObject msgBody) {
//		this.msgBody = msgBody;
//	}
//
//	public MsgHead getMsgHead() {
//		return msgHead;
//	}
//
//	public void setMsgHead(MsgHead msgHead) {
//		this.msgHead = msgHead;
//	}
//
//	public final void decode(DataInputStream dataInputStream)
//			throws IOException {
//		msgHead = new MsgHead();
//		msgHead.decode(dataInputStream);
//		//dataInputStream.readUTF();
//
//		deMsgBodyTemp = new DeMsgBodyTemp(dataInputStream, msgHead
//				.getSizeOfMsgBody());
//	}
//
//	public final void encode(DataOutputStream dataOutputStream)
//			throws IOException {
//		ByteArrayOutputStream cache = new ByteArrayOutputStream();
//		DataOutputStream temp = new DataOutputStream(cache);
//		if (msgHead != null) {
//			{
//				if (msgBody != null) {
//					msgBody.encode(temp);
//					int size = cache.toByteArray().length;
//
//					msgHead.setSizeOfMsgBody(size);
//				} else {
//					msgHead.setSizeOfMsgBody(0);
//				}
//				msgHead.encode(dataOutputStream);
//			}
//			if (msgBody != null) {
//				dataOutputStream.write(cache.toByteArray());
//			}
//			temp.close();
//		}
//	}
//
//	public void setDeMsgBodyTemp(DeMsgBodyTemp deMsgBodyTemp) {
//		this.deMsgBodyTemp = deMsgBodyTemp;
//	}
//
//	public IDataProtocolObject getMsgBody() {
//		return msgBody;
//	}
//
//}

