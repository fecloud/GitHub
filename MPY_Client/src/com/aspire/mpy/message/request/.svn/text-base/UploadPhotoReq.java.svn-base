package com.aspire.mpy.message.request;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.aspire.mpy.exception.MpyException;
import com.aspire.mpy.message.ReqMsgType;

public class UploadPhotoReq extends FileRequestMsg {

	public String cardID ;
	
	public String photoID;
	
	public String photoPath ;
	
	public byte [] data;
	
	public UploadPhotoReq(String photoPath) {
		super(ReqMsgType.DownloadPhotoReq);
	}

	@Override
	public byte[] getData() throws MpyException {
		if(null != photoPath){
			File file = new File(photoPath);
			if(file.exists()){
				long length = file.length();
				byte [ ]bs = new byte[(int) length];
				
				try {
					FileInputStream in = new FileInputStream(file);
					in.read(bs);
					in.close();
					return bs;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				throw new MpyException("get data null");
			}
		}
		return null;
	}
	
	

}
