package com.aspire.mpy.task;

import com.aspire.mpy.config.Config;
import com.aspire.mpy.message.request.IRequestMsg;
import com.aspire.mpy.message.response.IResponseMsg;

public class HttpNormalTask extends Task 
{

	private int read_time = Config.HTTP_READ_TIMEOUT; 
	
    public HttpNormalTask(IRequestMsg requestMsg, IResponseMsg responseMsg, ITaskListener listener)
    {
        super(requestMsg, responseMsg, listener);
    }
    
    public HttpNormalTask(IRequestMsg requestMsg, IResponseMsg responseMsg, ITaskListener listener , int read_time)
    {
        super(requestMsg, responseMsg, listener);
        this.read_time = read_time;
    }

	@Override
	public int getRead_time() {
		return read_time;
	}

}
