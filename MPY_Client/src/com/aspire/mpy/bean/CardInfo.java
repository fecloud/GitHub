package com.aspire.mpy.bean;

import java.io.Serializable;

/**
 * 定义为用户名片信息
 * @author Administrator
 *
 */
public class CardInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name = "";//联系人名称
	
	private String duty = "";//联系人职务
	
	private String mobile = "";//联系人手机号码
	
	private String email = "";//联系人邮件地址
	
	private String officeTel = "";//联系人办公号码
	
	private String officeFax = "";//联系人单位传真
	
	private String compAddr = "";//联系人办公地址
	
	private String compName = "";//联系人单位名称
	
	private String compURL = "";//联系人公司主页
	
	private String groupIDList = ""; //联系人归属的群组列表，每个ID之间使用；隔开
	
	private Integer photoID ;//联系人图像编号
	
	private Integer template;//联系人名片模板
	
	private String extrInfo = "";//联系人扩展信息
	
	private String tradeAddr = "";//联系人交换地址
	
	private String tradeTime = ""; //联系人交换时间
	
	private String createTime = ""; //联系人创建时间

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOfficeTel() {
		return officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	public String getOfficeFax() {
		return officeFax;
	}

	public void setOfficeFax(String officeFax) {
		this.officeFax = officeFax;
	}

	public String getCompAddr() {
		return compAddr;
	}

	public void setCompAddr(String compAddr) {
		this.compAddr = compAddr;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getCompURL() {
		return compURL;
	}

	public void setCompURL(String compURL) {
		this.compURL = compURL;
	}

	public String getGroupIDList() {
		return groupIDList;
	}

	public void setGroupIDList(String groupIDList) {
		this.groupIDList = groupIDList;
	}

	public Integer getPhotoID() {
		return photoID;
	}

	public void setPhotoID(Integer photoID) {
		this.photoID = photoID;
	}

	public Integer getTemplate() {
		return template;
	}

	public void setTemplate(Integer template) {
		this.template = template;
	}

	public String getExtrInfo() {
		return extrInfo;
	}

	public void setExtrInfo(String extrInfo) {
		this.extrInfo = extrInfo;
	}

	public String getTradeAddr() {
		return tradeAddr;
	}

	public void setTradeAddr(String tradeAddr) {
		this.tradeAddr = tradeAddr;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	
}
