//package android.mid.util.update;
//
//import java.io.File;
//import java.io.IOException;
//
//import android.content.Context;
//import android.content.pm.PackageManager.NameNotFoundException;
//import android.util.Log;
//
///**
// * 文件操作类
// * @author x_liaolijun
// *
// */
//public class FileUtils {
//	private static String PATH = ""; //保存文件的路径
//	
//	/**
//	 * 创建文件夹
//	 */
//	public static void initDir(){
////	    common=new CommonUtil();
////        if(common.checkSDcard()){
//            File SDFile = android.os.Environment.getExternalStorageDirectory();   
//            PATH=SDFile.getAbsolutePath()+ File.separator+"mid";
//            Log.e("path","mid apk down path:"+PATH);
//   //     }
//////        else{
////            PATH=SERV_LOG_PATH;
////            Log.e("path","mid apk down path:"+PATH);
//////        }
//		File f = new File(PATH);
//		if(!f.exists()) 
//		    f.mkdir();
//	}
//	
//	/**
//	 * 得到保存文件的路径
//	 */
//	public static String getPath(){
//		return PATH;
//	}
//	
//	/**
//	 * 根据当前时间生成一个文件名
//	 * @param suufix 后缀名
//	 */
//	public static String newFileName(String suffix){
//		return PATH+"/"+System.currentTimeMillis()+"."+suffix;
//	}
//	
//	/**
//	 * 生成应用安装的名字
//	 * @param appInfo
//	 * @return
//	 
//	public static String newAppFileName(UdpInfo udpInfo){
//		if(udpInfo!=null) 
//		return PATH+"/"+"app"+udpInfo.appId+"_"+udpInfo.version+".apk";
//		return "";
//	}*/
//	
//	/**
//	 * 删除文件或文件夹
//	 * @return
//	 */
//	public static void deleteFile(String fileName){
//		File f = new File(fileName);
//		if(f.exists()){
//			if(f.isFile()){
//				f.delete();
//			}
//			else if(f.isDirectory()){
//				String[] files = f.list();
//				int len = files.length;
//				for(int i=0;i<len;i++){
//					deleteFile(files[i]);
//				}
//			}
//		}
//	}
//	
//	public static boolean newFile(String fileName){
//		File f = new File(fileName);
//		if(!f.exists()){
//			try {
//				return f.createNewFile();
//			} catch (IOException e) {
//			}
//		}
//		return false;
//	}
//	
//	/**
//	 * 判断文件是否存在
//	 * @param fileName
//	 * @return
//	 */
//	public static boolean isExists(String fileName){
//		File f = new File(fileName);
//		return f.exists();
//	}
//	/**
//	 * 获取服务中心当前版本号
//	 */
//	public static int getVersion(Context context,String pageName){
//		int nowVersion = 0;
//		try {
//			nowVersion = context.getPackageManager().getPackageInfo(
//					pageName, 0).versionCode;
//			Log.d("", "mid--------------versionName"+nowVersion);
//		} catch (NameNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		finally{
//			return nowVersion;
//		}
//	}
//}
