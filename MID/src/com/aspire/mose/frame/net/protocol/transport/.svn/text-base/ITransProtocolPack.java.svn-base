package com.aspire.mose.frame.net.protocol.transport;

import java.io.IOException;

import com.aspire.mose.frame.net.protocol.IDataProtocolObject;

public interface ITransProtocolPack {
	/**
     * 编码 将上层的 对象协议对象 转换为传输协议对象
     * @param dataProtocolObject 上层对象
     * @param protocolStates 协议状态 这个有点郁闷 我们定义的传输协议是有状态的传输协议 不同状态下 传输协议除了体之外
     *                       其他内容也是变长的
     * @return 传输对象
     * @throws IOException
     */
	public ITransProtocolObject<?, ?, ?> pack(IDataProtocolObject dataProtocolObject) throws IOException;
}
