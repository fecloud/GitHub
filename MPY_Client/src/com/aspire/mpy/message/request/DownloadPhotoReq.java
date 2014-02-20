package com.aspire.mpy.message.request;

import com.aspire.mpy.message.ReqMsgType;

public class DownloadPhotoReq extends FileRequestMsg {

	public Integer photoID;
	
	public DownloadPhotoReq(){
		super(ReqMsgType.DownloadPhotoReq);
		requestProperty.put("PhotoID", "" + photoID);
	}

	public DownloadPhotoReq(Integer photoID) {
		super(ReqMsgType.DownloadPhotoReq);
		this.photoID = photoID;
	}

}
