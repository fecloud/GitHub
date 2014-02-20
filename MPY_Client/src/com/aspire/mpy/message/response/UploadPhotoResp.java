package com.aspire.mpy.message.response;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import android.util.Log;

import com.aspire.mpy.exception.MpyException;

public class UploadPhotoResp extends FileRespMsg {

	protected final String TAG = "UploadPhotoResp";
	
	@Override
	public void parseInputStream(InputStream in, HttpURLConnection httpConn) throws MpyException,
			IOException {
		StringBuffer st = new StringBuffer();
		byte[] bs = new byte [1024];
		int length = -1;
		while( -1 != (length = in.read(bs ))){
			st.append(new String(bs , 0 , length ,"UTF-8"));
		}
		in.close();
		Log.i(TAG, "parseInputStream get input String :" + st.toString());
		super.parseInputStream(in, httpConn);
	}

	
	
}
