package com.aspire.mpy.message.request;

import java.util.Hashtable;

import com.aspire.mpy.exception.MpyException;


public interface IRequestMsg {
	/**
	 * 获取数据，一次性获取全部数据
	 * 
	 * @return byte[]
	 * @throws Exception
	 */
	public byte[] getData() throws MpyException;

	/**
	 * 分批次获取数据接口，获取数据长度，最大为length，主要是为上传提供的接口
	 * 
	 * @param length
	 *            int
	 * @return byte[]
	 * @throws Exception
	 */
	public byte[] getData(int length) throws MpyException;

	/**
	 * 添加需要从http协议header中获取数据的属性
	 */
	public void addNeedRespHeaderProp(String key, String value);

	/**
	 * 需要从http协议header中获取数据的属性
	 */
	public Hashtable<String , String> getNeedRespHeaderProps();

	public String getContentType();

	public String getURL();

	public String getMsgType();

	/**
	 * 获取当前协议网络通道类型
	 * 
	 * @return 0: cmnet 或 cmwap、 1: cmnet、 2: cmwap、 3: cmlan
	 */
	public int getNetworkType();
}
