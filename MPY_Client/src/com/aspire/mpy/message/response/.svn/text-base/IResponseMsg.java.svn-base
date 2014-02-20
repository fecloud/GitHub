package com.aspire.mpy.message.response;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import com.aspire.mpy.exception.MpyException;

public interface IResponseMsg {
	/**
	 * 响应消息接口
	 * 
	 * @author x_liyajun
	 * 
	 *         2010-8-20 下午08:19:52
	 * 
	 *         IResponseMsg
	 * 
	 */
	/** 回调该接口，将从网络侧收到的数据流交给响应消息进行解析，具体的解析工作由该ResponseMessage的实现类完成，解析xml */
	public void parseInputStream(InputStream in) throws MpyException, IOException;

	/** 回调该接口，将从网络侧收到的数据流交给响应消息进行解析，具体的解析工作由该ResponseMessage的实现类完成,解析字节流 */
	public void parseInputStream(InputStream in, HttpURLConnection httpConn)
			throws MpyException, IOException;
}
