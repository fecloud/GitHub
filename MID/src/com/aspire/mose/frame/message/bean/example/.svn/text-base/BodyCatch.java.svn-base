package com.aspire.mose.frame.message.bean.example;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import android.util.Log;

import com.aspire.mose.frame.message.bean.IBodyCatch;
import com.aspire.mose.frame.net.protocol.IDataProtocolObject;

public class BodyCatch implements IBodyCatch {

	private static final String TAG = BodyCatch.class.getSimpleName();

	byte[] a;

	@Override
	public void decode(IDataProtocolObject msgBody) throws IOException {

		// 如果有传递过来的编码对象和 缓存的数据对象
		if (msgBody != null && a != null) {
			// 如果这是一个对象协议代理 则走代理的流程
//			if (msgBody instanceof IDataProtocolProxy) {
//
//			} else {
				// 如果不是代理 则直接使用数据对象来解码
				ByteArrayInputStream cache = new ByteArrayInputStream(a);
				DataInputStream temp = new DataInputStream(cache);
				try {
					//调用传入对象方法编解码
					msgBody.decode(temp);
				
				} catch (IOException e) {
					//还是需要将异常抛出
					throw e;
				} finally {
					//无论出什么错误 都要关闭掉流
					try {
						temp.close();
					} catch (Exception e) {						
						e.printStackTrace();
					}
					//清除流 清除缓冲
					temp = null;
					a = null;
				}
//			}

		} else {
			Log.d(TAG, "decode() msgBody==null or  a[] = null");
			throw new IOException("msgBody==null or  a[] = null");
		}

//		// google buffer 暂时没有找到填充定义空对象的方法
//		// java又不能修改传入对象指针，只能用一个容器类包装传递过来，然后修改容器里面的东西，最后再返回出去
//		if (msgBody != null && msgBody.getMessageLite() != null && a != null) {
//			// ByteArrayInputStream cache;
//			//
//			// DataInputStream temp;
//			// cache = new ByteArrayInputStream(a);
//			// temp = new DataInputStream(cache);
//
//			GeneratedMessageLite resp = (GeneratedMessageLite) msgBody
//					.getMessageLite().getDefaultInstanceForType().toBuilder()
//					.mergeFrom(a).buildPartial();
//
//			if (resp != null) {
//				msgBody.setMessageLite(resp);
//			}
//		}
//		a = null;

	}

	@Override
	public void doCatch(DataInputStream dataInputStream, int size) {
		a = new byte[size];
		try {
			dataInputStream.read(a, 0, size);

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
