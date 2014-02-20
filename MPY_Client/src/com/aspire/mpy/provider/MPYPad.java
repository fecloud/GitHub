package com.aspire.mpy.provider ;

import android.net.Uri;
import android.provider.BaseColumns;

public class MPYPad {
	
	public static final String AUTHORITY = "com.aspire.mpy.provider.MPYPad";
	
	public static final String TB_NAME_USER_INFO = "UserInfo";// 名片表
	
	public static final String TB_NAME_PHOTO_INFO = "PhotoInfo";// 名片表
	
	public static final class PhotoInfo implements BaseColumns{
		/**
		 * The content:// style URL for table AppInfo.
		 */
		public static final Uri CONTENT_URI = Uri.parse("content://"+ AUTHORITY + "/" + TB_NAME_PHOTO_INFO);
		/**
		 * The MIME type
		 */
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/PhotoInfo";

		/**
		 * The MIME type of a {@link #CONTENT_URI} subdirectory of a single.
		 */
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/PhotoInfo";
		
		public static final String PHOTOID = "PhotoID"; // 联系人名片标识
		
		public static final String PHOTO = "Photo"; //联系人名片版本
		
		public static final String PHOTOTHUM = "PhotoThum";//名片缩略图
		
	}
	
	public static final class UserInfo implements BaseColumns{
		/**
		 * The content:// style URL for table AppInfo.
		 */
		public static final Uri CONTENT_URI = Uri.parse("content://"+ AUTHORITY + "/" + TB_NAME_USER_INFO);
		/**
		 * The MIME type
		 */
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/UserInfo";

		/**
		 * The MIME type of a {@link #CONTENT_URI} subdirectory of a single.
		 */
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/UserInfo";
		
		public static final String ID = "UserInfo_ID"; // 联系人名片标识
		
		public static final String VERSION = "Version"; //联系人名片版本
		
		public static final String NAME = "Name" ;//联系人名称
		
		public static final String DUTY ="Duty";//联系人职务
		
		public static final String MOBILE = "Mobile";//联系人手机号码
		
		public static final String EMAIL = "Email";//联系人邮件地址
		
		public static final String OFFICETEL = "OfficeTel";//联系人办公号码
		
		public static final String OFFICEFAX = "OfficeFax";//联系人单位传真
		
		public static final String COMPADDR = "CompAddr";//联系人办公地址
		
		public static final String COMPNAME = "CompName";//联系人单位名称
		
		public static final String COMPURL = "CompURL";//联系人公司主页
		
		public static final String GROUPIDLIST = "GroupIDList"; //联系人归属的群组列表，每个ID之间使用；隔开
		
		public static final String PHOTOID = "PhotoID";//联系人图像编号
		
		public static final String TEMPLATE = "Template";//联系人名片模板
		
		public static final String EXTRINFO = "ExtrInfo";//联系人扩展信息
		
		public static final String TRADEADDR = "TradeAddr";//联系人交换地址
		
		public static final String TRADETIME= "TradeTime"; //联系人交换时间
		
		public static final String CREATETIME  = "CreateTime"; //联系人创建时间
		
	}

}
