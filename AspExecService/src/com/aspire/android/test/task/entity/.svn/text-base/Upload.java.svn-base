/**
 * @(#) Upload.java Created on 2012-6-27
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.task.entity;

import java.util.Date;

import com.aspire.android.test.Constants;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * The class <code>Upload</code>
 * 
 * @author gouanming
 * @version 1.0
 */
@DatabaseTable(tableName = Constants.ATS_TASK_UPLOAD)
public class Upload {

	/** ID **/
	@DatabaseField(columnName = "id", generatedId = true)
	private long id;

	/** uploadPath **/
	@DatabaseField(columnName = "uploadPath")
	private String uploadPath;

	/** 上传文件名称 **/
	@DatabaseField(columnName = "name")
	private String name;

	/** 上传上去的结果在ftp服务器上的名称 **/
	@DatabaseField(columnName = "resultFileName")
	private String resultFileName;

	/**
	 * 上传的文件的时间
	 */
	@DatabaseField(columnName = "uploadTime")
	private String uploadTime;

	/**
	 * 状态: 1-完成上传 /0没完成上传
	 */
	@DatabaseField(columnName = "upstatus")
	private String upstatus;

	/** 拨测平台的反馈文件 **/
	@DatabaseField(columnName = "respFile")
	private String respFile;

	/** 返回状态 0-待反馈，1-返回结果正确，2为不正确 **/
	@DatabaseField(columnName = "status")
	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	 

	/**
	 * 上传结果时间
	 */
	@DatabaseField(columnName = "upDate", dataType = DataType.DATE)
	private Date upDate;
	/**
	 * 返回结果时间
	 */
	@DatabaseField(columnName = "resultDate", dataType = DataType.DATE)
	private Date resultDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getName() {
		return name;
	}

	public void setResultFileName(String resultFileName) {
		this.resultFileName = resultFileName;
	}

	public String getResultFileName() {
		return resultFileName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getUpstatus() {
		return upstatus;
	}

	public void setUpstatus(String upstatus) {
		this.upstatus = upstatus;
	}

	public String getResponseFile() {
		return respFile;
	}

	public void setResponseFile(String respFile) {
		this.respFile = respFile;
	}

	public Date getUpDate() {
		return upDate;
	}

	public void setUpDate(Date upDate) {
		this.upDate = upDate;
	}

	public Date getResultDate() {
		return resultDate;
	}

	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
	}

}
