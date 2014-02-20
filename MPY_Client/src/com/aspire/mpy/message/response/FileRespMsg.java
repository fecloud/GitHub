package com.aspire.mpy.message.response;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import com.aspire.mpy.exception.MpyException;

public class FileRespMsg implements IResponseMsg {
	
	public int status;
	
	@Override
	public void parseInputStream(InputStream in) throws MpyException, IOException {
		
	}

	@Override
	public void parseInputStream(InputStream in, HttpURLConnection httpConn) throws MpyException,
			IOException {
		String status = httpConn.getHeaderField("Status");
		if(null != status){
			this.status = Integer.parseInt(status);
		}
		parseInputStream(in);
	}


}
