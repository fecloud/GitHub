package com.aspire.mose.business.module.msgpush;

import java.util.ArrayList;
import java.util.List;


public class MsgPushDataUtil {
    
	private static MsgPushDataUtil iMsgPushDataUtil = null;
	private List<MsgPushData> PushDataArray = null;
	private static final int PUSH_DATA_COUNT_MAX = 500;
	private MsgPushData msgPushData = null;
	
	public static MsgPushDataUtil GetInstance() {
		if(iMsgPushDataUtil == null) {
			iMsgPushDataUtil = new MsgPushDataUtil();
		}
		return iMsgPushDataUtil;
	}
	
	private MsgPushDataUtil() {
		PushDataArray = new ArrayList<MsgPushData>();
	}
	
	public synchronized MsgPushData GetPushDataById(String msgId){

		for(int i=0 ; i < PushDataArray.size(); i++) {
			if(0 == PushDataArray.get(i).GetMsgID().compareTo(msgId)){
				msgPushData = PushDataArray.get(i);
				//删除数据
				PushDataArray.remove(i);
				return  msgPushData;
			}
		}
		return null;
	}
	
	public synchronized void SetPushData(MsgPushData msgPushData) {
		//如果存量超过500条，则删除时间最久的一条
		if(PUSH_DATA_COUNT_MAX < PushDataArray.size()){
			PushDataArray.remove(0);
		}
		PushDataArray.add(msgPushData);
	}

}
