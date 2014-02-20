///*
// * Copyright (C) 2010 Aspire
// * 
// * UpdList.java Version 1.0
// *
// */
//package android.mid.bean;
//
//import java.io.Serializable;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//
///**
// * 版本校验信息
// * 
// * @author x_luoqingbo
// *
// * 2010-10-25 上午09:48:19
// *  
// * UpdList
// *
// */
//public class UdpInfo implements Serializable,Parcelable{
//
//
//    /**
//     * 
//     */
//    private static final long serialVersionUID = -1622236967346947359L;
//
//    /**应用ID标识*/
//    public String appId;
//    
//    /**应用最新版本号*/
//    public String version;
//    
//    /**应用升级标识
//       0：不需要升级
//       1：可选升级
//       2：必需升级*/
//    public int flag;
//    
//    /**应用更新地址，仅当mOSE版本对比时返回*/
//    public String updUrl="";
//    
//    /**应用当前版本号*/
//    public String nowVersion="0";
//    
//    public String toString()
//    {
//    	StringBuffer sb = new StringBuffer();
//    	sb.append(" [ appId="+appId +" ] ");
//    	sb.append(" [ version="+version +" ] ");
//    	sb.append(" [ flag="+flag +" ] ");
//    	sb.append(" [ updUrl="+updUrl +" ] ");
//    	sb.append(" [ nowVersion="+nowVersion +" ] ");
//    	
//    	return sb.toString();
//    }
//
//   public static final Parcelable.Creator<UdpInfo> CREATOR = new Parcelable.Creator<UdpInfo>() {
//        public UdpInfo createFromParcel(Parcel in) {
//            return new UdpInfo(in);
//        }
//
//        public UdpInfo[] newArray(int size) {
//            return new UdpInfo[size];
//        }
//    };
//    private UdpInfo(Parcel in) {
//        readFromParcel(in);
//    }
//    public UdpInfo(){
//    	
//    }
//    public void readFromParcel(Parcel in) {    
//
//    flag = in.readInt();
//    version = in.readString();
//    updUrl = in.readString();
//    nowVersion = in.readString();
//    appId = in.readString();
//    
//    }
//@Override
//public int describeContents() {
//	// TODO Auto-generated method stub
//	return 0;
//}
//
//@Override
//public void writeToParcel(Parcel dest, int flags) {
//	// TODO Auto-generated method stub
//	dest.writeInt(flag);
//	dest.writeString(updUrl);
//	dest.writeString(nowVersion);
//	dest.writeString(appId);
//	dest.writeString(version);
//
//}
//
//
//}
