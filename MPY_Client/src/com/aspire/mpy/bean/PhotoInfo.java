package com.aspire.mpy.bean;

import java.io.Serializable;

import android.graphics.Bitmap;

public class PhotoInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer photoID;
	
	private Bitmap photo;
	
	private Bitmap PhotoThum;

	
	public PhotoInfo(){}
	
	public PhotoInfo(Integer photoID, Bitmap photo, Bitmap photoThum) {
		super();
		this.photoID = photoID;
		this.photo = photo;
		PhotoThum = photoThum;
	}

	public Integer getPhotoID() {
		return photoID;
	}

	public void setPhotoID(Integer photoID) {
		this.photoID = photoID;
	}

	public Bitmap getPhoto() {
		return photo;
	}

	public void setPhoto(Bitmap photo) {
		this.photo = photo;
	}

	public Bitmap getPhotoThum() {
		return PhotoThum;
	}

	public void setPhotoThum(Bitmap photoThum) {
		PhotoThum = photoThum;
	}
	
	
	
}
