package android.mid.config;


import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.XmlResourceParser;
import android.mid.R;
import android.util.Log;

public class Config 
{
	//整个模块的日志标记
	public static final String  MODULE_PRE_TAG = " MOSE ";
	 
    public static final String TAG = MODULE_PRE_TAG+Config.class.getSimpleName()/*"mid"*/;
    public static Context context;
    private static Config instance;
    
    /**运行模式 0:模拟器，1：手机*/
    public static int run_model = 0;
    /**模拟器*/
    public static final int RUN_MODEL_EMULATOR = 0;
    /**手机*/
    public static final int RUN_MODEL_PHONE = 1;
    
    /**短信拦截接入号*/
    public static String sms = "";
    
    // 临时环境
//    public static String URL_SERVICE="http://221.179.9.154:8085/mid/client";//业务支撑平台
//    public static String URL_NETBOOK="http://10.0.0.147/mid/client";;//上网本平台
//    public static String URL_DIRECTORIES = "221.179.9.154:8085";//通讯录服务器地址
//    public static String URL_RESOURCE = "http://221.179.9.154:8085/middls/dlegver";//下载资源服务务
    
    //现网环境
//    public static String URL_SERVICE="http://221.179.9.155:8085/mid/client";//业务支撑平台
    public static String URL_SERVICE="http://yx100.televehicle.com:8085/mid/client";//业务支撑平台
    public static String URL_NETBOOK="http://10.0.0.147/mid/client";;//上网本平台
    public static String URL_DIRECTORIES = "221.179.9.155:8085";//通讯录服务器地址
    public static String URL_RESOURCE = "http://221.179.9.155:8085/middls/dlegver";//下载资源服务务
    
    //演示环境
//    public static String URL_SERVICE="http://211.139.191.156:8085/mid/client";//业务支撑平台
//    public static String URL_NETBOOK="http://10.0.0.147/mid/client";;//上网本平台
//    public static String URL_DIRECTORIES = "211.139.191.156:8085";//通讯录服务器地址
//    public static String URL_RESOURCE = "http://211.139.191.156:8085/middls/dlegver";//下载资源服务务
    
    //测试环境
//    public static String URL_SERVICE="http://211.139.191.156:8090/mid/client";//业务支撑平台
//    public static String URL_NETBOOK="http://10.0.0.147/mid/client";;//上网本平台
//    public static String URL_DIRECTORIES = "211.139.191.156:8090";//通讯录服务器地址
//    public static String URL_RESOURCE = "http://211.139.191.156:8090/middls/dlegver";//下载资源服务务

    public static int logsize = 1000; //日志MidLog表最大存储量
    
    public static int HTTP_CONNECT_TIMEOUT = 1000 * 20; //网络连接超时时间
    public static int HTTP_READ_TIMEOUT = 1000 * 20; //读入超时时间
    public static int APN_SWITCH_TIMEOUT = 20 * 1000; //APN切换超时时间时间戳
    
    /**协议版本*/
    public static String version = "1.0";
    /**apk版本*/
    public static String apkversion="0";
    /**数据库版本号*/
	public static final int DATABASE_VERSION = 9;
    /**mOSE appid*/
    public static String appId = "000000";
    /**mOSE ServiceType*/
    public static String serviceType = "0";
    /**mOSE PlatformCode*/
    public static String platformCode = "0000000";
    /**mOSE ServiceCode*/
    public static String serviceCode = "000000000";
    /**是否入库*/
    public static String flag = "1";
   /**
    * 内容推送id
    */
    public static String msiadnId="";
    
    private SharedPreferences mCfgPrf;
    private static final String CONFIG_PERFER = "basesetting.mid";
    
    private final String CARD_INFO = "cardInfo";//刷卡用户信息
    private final String VERIFY_INFO = "verifyInfo";//签名信息
    private final String TIMESTAMP = "timestamp";//签名信息的时间戳
    
    public synchronized static Config getInstance(){
        if (null ==instance) {
            instance = new Config();
        }
        return instance;
    }
    
    private Config() 
    {
        mCfgPrf = context.getSharedPreferences(CONFIG_PERFER, Context.MODE_WORLD_READABLE|Context.MODE_WORLD_WRITEABLE);
    }
    
    public static void init(Context c){
        context = c;
//        apkversion=""+FileUtils.getVersion(context, "android.mid");
        Log.i(TAG, "mid---loading config.xml");
        XmlResourceParser xrp = context.getResources().getXml(R.xml.config);
        try {
            String nodeName = "";
            while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
                if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                    nodeName = xrp.getName();
                } else if (xrp.getEventType() == XmlResourceParser.TEXT) {
                    if (nodeName.equals("run_model")) {
                        run_model = Integer.valueOf(xrp.getText());
                    } else if (nodeName.equals("url_service")) {
                        URL_SERVICE = xrp.getText();
                    } else if (nodeName.equals("url_netbook")) {
                        URL_NETBOOK = xrp.getText();
                    }
                    else if (nodeName.equals("url_directories")) {
                    	URL_DIRECTORIES = xrp.getText();
                    }
                    else if (nodeName.equals("logsize")) {
                    } else if (nodeName.equals("url_resource")) {
//                        URL_RESOURCE = xrp.getText();
                    } else if (nodeName.equals("logsize")) {
                        logsize = Integer.valueOf(xrp.getText());
                    } else if (nodeName.equals("sms")) {
                        sms = xrp.getText();
                    } else if (nodeName.equals("apn_timeout")) {
                        APN_SWITCH_TIMEOUT = Integer.valueOf(xrp.getText());
                    } else if (nodeName.equals("connect")) {
                        HTTP_CONNECT_TIMEOUT =Integer.valueOf(xrp.getText());
                    } else if (nodeName.equals("read")) {
                        HTTP_READ_TIMEOUT = Integer.valueOf(xrp.getText());
                    }
//                    Log.i(TAG, "mid---nodeName=" + nodeName+"---value=" + xrp.getText());
                }
                xrp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        xrp.close();
    }
    
    
    /**
     * 设置刷卡用户信息
     * @param cardInfo
     */
    public void setCardInfo(String cardInfo)
    {
        mCfgPrf.edit().putString(CARD_INFO, cardInfo).commit();
    }
    
    /**
     * 获取刷卡用户信息
     * @return
     */
    public String getCardInfo()
    {
        return mCfgPrf.getString(CARD_INFO, "");
    }
    
    /**
     * 设置签名信息
     * @param verifyInfo
     */
    public void setVerifyInfo(String verifyInfo)
    {
        mCfgPrf.edit().putString(VERIFY_INFO, verifyInfo).commit();
    }
    
    /**
     * 获取签名信息
     * @return
     */
    public String getVerifyInfo()
    {
        return mCfgPrf.getString(VERIFY_INFO, "");
    }
    
    /**
     * 设置时间戳
     * @param timestamp
     */
    public void setTimestamp(String timestamp)
    {
        mCfgPrf.edit().putString(TIMESTAMP, timestamp).commit();
    }
    
    /**
     * 获取时间戳
     * @return
     */
    public String getTimestamp()
    {
        return mCfgPrf.getString(TIMESTAMP, "");
    }
    
}
