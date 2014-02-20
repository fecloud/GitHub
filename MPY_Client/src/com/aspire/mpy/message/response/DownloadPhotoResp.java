package com.aspire.mpy.message.response;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.aspire.mpy.exception.MpyException;

public class DownloadPhotoResp extends FileRespMsg {
	
	public Integer photoID;
	
	public Bitmap photo;

	@Override
	public void parseInputStream(InputStream in, HttpURLConnection httpConn) throws MpyException,
			IOException {
		super.parseInputStream(in, httpConn);
		httpConn.getHeaderField("PhotoID");
		photo = BitmapFactory.decodeStream(in);
	}


	
	
}
