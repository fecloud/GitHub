package com.aspire.mose.business.module.msgpush;

import java.io.IOException;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.aspire.eldmose.database.DBManager;
import com.aspire.mose.frame.message.IMessageCenter;
import com.aspire.mose.frame.message.IMsgListener;
import com.aspire.mose.frame.message.bean.IMsg;
import com.aspire.mose.frame.message.bean.example.CmdID;
import com.aspire.mose.frame.message.bean.example.DataProtocolProxy;
import com.aspire.mose.frame.message.bean.example.Msg;
import com.aspire.mose.protocol.proto.MsgTransHeader.TransHeader;
import com.aspire.mose.protocol.proto.msgpush.MsgMessagePushRequest.MessagePushRequest;
import com.aspire.mose.protocol.proto.msgpush.MsgMessagePushResponse.MessagePushResponse;
import com.aspire.mose.protocol.proto.msgpush.MsgMessagePushResponse.MessagePushResponse.ResponseRetCode;
import com.aspire.mose.protocol.proto.msgpush.MsgMessageStateRequest.MessageStateRequest;
import com.aspire.mose.shore.broadcast.BroadcastManager;

public class MsgPushListener implements IMsgListener {
	
	private static final String TAG = MsgPushListener.class.getSimpleName();
	
	private NavSetHandler handler;
	
	private IMessageCenter mc;

	MsgPushListener(IMessageCenter mc) {
		handler = new NavSetHandler();
		this.mc = mc;
	}

	// 通过继承，接收网络， 回调
	@Override
	public void msgProcess(IMsg<?, ?> msg) {
		MsgPushData msgData = null;

		Msg RespMsg = (Msg) msg;
		// 打log
		Log.d(TAG, "tMsg.getHead().getMsgType()="
				+ RespMsg.getHead().getMsgType());
		Log.d(TAG, "tMsg.getHead().getCmdID()=" + RespMsg.getHead().getCmdID());
		Log.d(TAG, "tMsg.getHead().getTransactionID()="
				+ RespMsg.getHead().getTransactionID());

		// 构建一个响应消息代理
		DataProtocolProxy dPProxy = new DataProtocolProxy(
				MessagePushRequest.getDefaultInstance());
		// 解码响应消息
		try {
			RespMsg.getBodyCatch().decode(dPProxy);
			// 从响应代理中获取返回消息
			MessagePushRequest msgPushReq = (MessagePushRequest) dPProxy
					.getMessageLite();
			msgData = setReqDataToLocal(msgPushReq);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Log.d(TAG, "msgData.GetContentStr()=" + msgData.GetContentStr());
		Log.d(TAG, "msgData.GetAppID()=" + msgData.GetAppID());
		Log.d(TAG, "msgData.GetStatusReport()=" + msgData.GetStateReport());

		// 如果回复给服务器的消息成功了，才能处理后面的消息

		// 添加进数据库
		if (HandlerMsgPushUtil.PUSH_DEVICENOTE_HANDLER == msgData.GetMode()
				| HandlerMsgPushUtil.PUSH_INBOX_HANDLER == msgData.GetMode()
				| HandlerMsgPushUtil.PUSH_TASK_ICON_HANDLER == msgData
						.GetMode()) {
		} else {
			MsgPushDataUtil.GetInstance().SetPushData(msgData);
		}
		// end 添加进数据库

		int PushType = msgData.GetMode();
		Log.d(TAG, "PushType=" + PushType);
		// 发送handler
		Message myMsg = handler.obtainMessage(PushType, msgData);
		myMsg.sendToTarget();
		Log.d(TAG, "getTransactionID=" + RespMsg.getHead().getTransactionID());

		// 发送响应消息给服务器
		sendPushMessageResponse(0, RespMsg.getHead().getTransactionID());

		if (msgData.GetStateReport()) {
			sendPushStatusReport(msgData, RespMsg.getHead().getTransactionID());
		}

	}

	private void sendPushStatusReport(MsgPushData msgData, String transactionID) {
		// TODO Auto-generated method stub
		Log.d(TAG, "Send Status Report");

		Msg retMsg = new Msg();
		// 构建消息头
		TransHeader header = TransHeader.newBuilder()
				.setCmdID(CmdID.CMD_MSG_STATE_REPORT)
				.setMsgType(Msg.MSG_TYPE_REQ).setTransactionID(transactionID)
				.build();
		retMsg.setHead(header);

		MessageStateRequest mr = MessageStateRequest.newBuilder()
				.setAppId(msgData.GetAppID())
				.setDeviceId(msgData.GetDeviceID())
				.setMsgId(msgData.GetMsgID()).setUserId(msgData.GetUserID())
				.setUserToken(msgData.GetUserToken())
				.setMsgState(MessageStateRequest.MessageState.ACCEPTD).build();
		retMsg.setBody(mr);
		// 发送消息
		int error = mc.sendMessage(retMsg);
		Log.d(TAG, "PushStatusReport resp:" + error);

		// DataProtocolProxy resp = new DataProtocolProxy(null);
		// int error = mc.sendMessage(retMsg, resp, 6000);

	}

	private MsgPushData setReqDataToLocal(MessagePushRequest msgPushReq) {
		MsgPushData resp = new MsgPushData();
		if (null == msgPushReq) {
			return null;
		}
		resp.SetDeviceID(msgPushReq.getDeviceId());
		resp.SetUserID(msgPushReq.getUserId());
		resp.SetUserToken(msgPushReq.getUserToken());
		resp.SetAppID(msgPushReq.getAppId());
		resp.SetMsgID(msgPushReq.getMsgId());
		resp.SetStateReport(msgPushReq.getStateReport());
		resp.SetFormat(msgPushReq.getMessage().getMsgFormat().getNumber());
		resp.SetMode(msgPushReq.getMessage().getMsgMode().getNumber());
		resp.SetContent(msgPushReq.getMessage().toByteArray());
		resp.setTime(System.currentTimeMillis()); // 获取当期那时间需要自己解析。
		return resp;
	}

	/**
	 * 发送消息推送的应答消息给MOSP
	 */
	private void sendPushMessageResponse(int retcode, String transactionID) {
		Msg retMsg = new Msg();
		// 构建消息头
		TransHeader header = TransHeader.newBuilder()
				.setCmdID(CmdID.CMD_MSG_PUSH).setMsgType(Msg.MSG_TYPE_RESP)
				.setTransactionID(transactionID).build();
		retMsg.setHead(header);
		// 构建消息体
		MessagePushResponse resp = null;
		if (0 == retcode) {
			resp = MessagePushResponse.newBuilder()
					.setRetCode(ResponseRetCode.SUCCESS).build();
		} else {
			resp = MessagePushResponse.newBuilder()
					.setRetCode(ResponseRetCode.OTHERFAIL).build();
		}
		retMsg.setBody(resp);
		// 发送消息
		int error = mc.sendMessage(retMsg);
		// 发送不成功
		if (error != 0) {
			// 发送不成功，需要怎么处理呢？重试？
		}
	}

	class NavSetHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case HandlerMsgPushUtil.PUSH_DEVICENOTE_HANDLER:
			case HandlerMsgPushUtil.PUSH_INBOX_HANDLER:
			case HandlerMsgPushUtil.PUSH_TASK_ICON_HANDLER: {
				// 发送消息到设备通知栏，没有这种消息
				break;
			}
			case HandlerMsgPushUtil.PUSH_TASK_HANDLER:
			case HandlerMsgPushUtil.PUSH_TASK_CONFIRM_HANDLER: {
				MsgPushData iMsgData = (MsgPushData) msg.obj;
				// final String msgid = iMsgData.GetMsgID();
				final String iAppID = iMsgData.GetAppID();
				final String content = iMsgData.GetContentStr();
				final String msgID = iMsgData.GetMsgID();
				Boolean iStateReport = iMsgData.GetStateReport();
				Log.d(TAG, "content:" + content);
				Log.d(TAG, "iAppID:" + iAppID);
				Log.d(TAG, "msgID:" + msgID);
				Log.d(TAG, "StateReport:" + iStateReport);

				if (DBManager.getInstance().vetifyRepeateMsg(msgID, 0)) {
					// abandom message.
					Log.d(TAG, "Abandom message");
				} else {
					DBManager.getInstance().insertMsg(msgID, content,
							System.currentTimeMillis());
					// 发送广播，通知应用程序来取数据
					BroadcastManager.sendPushMessageNotifyBroadcast(null,
							iAppID, content);
				}
				break;
			}
			}
		}
	}

}
