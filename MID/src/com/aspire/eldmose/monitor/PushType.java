package com.aspire.eldmose.monitor;

public interface PushType {

	/**提醒消息*/
	public final static int SMS_WARN = 0;
	/**内容推送push*/
	public final static int CONTENT_PUSH = 1;
	/**绑定关系push*/
	public final static int BIND_PUSH = 2;
	/**订购关系变更push*/
	public final static int SUB_CHANGED_PUSH = 3;
	/**强制登出通知*/
	public final static int FORCE_LOGOUT = 4;
	/**手机号码下发*/
    public final static int MSISDN_NOTIFY = 5;
}
