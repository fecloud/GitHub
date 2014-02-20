package com.aspire.android.test;

import com.aspire.android.test.log.RunLogger;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

public class OphoneNetWork {
    private Context context;
    private RunLogger runLogger = RunLogger.getInstance();
    private final String[] model = { "OMS1_5" };

    public OphoneNetWork(Context context) {
        this.context = context;
    }

    public void ActiveNetWorkByMode(String networkmode) {

        if (compareModel()) {
            try {
                ConnectivityManager connectivity = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                connectivity.startUsingNetworkFeature(ConnectivityManager.TYPE_MOBILE, networkmode);
            } catch (Exception e) {
            }
        }
    }

    private boolean compareModel() {
        runLogger.debug(getClass(), "the phone's model is " + Build.MODEL);
        for (int i = 0; i < model.length; i++) {
            if (Build.MODEL.equalsIgnoreCase(model[i])) {
                return true;
            }
        }
        return false;
    }

    // 关闭数据连接
    public void DestroyNetWorkByMode(String networkmode) {
        if (compareModel()) {
            try {
                ConnectivityManager connectivity = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                connectivity.stopUsingNetworkFeature(ConnectivityManager.TYPE_MOBILE, networkmode);
            } catch (Exception e) {
            }
        }

    }

    // 判断数据连接是否激活，如果激活则进一步判断是不是CMWAP连接，我的联网只用CMWAP，你可以根据自己的需要具体去改写。

    public NetworkInfo[] getInfo() {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivity.getAllNetworkInfo();
    }

    public boolean isNetworkCMWAPAvailable() {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // System.out.println("NETWORK active connectivity     ");
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                // System.out.println("NETWORK active info is well     ");
                for (int i = 0; i < info.length; i++) {
//                    String typeName = info[i].getTypeName();
                    String extraInfo = info[i].getExtraInfo();
                    // System.out.println("NETWORK active info state      " + info.getState() + "   " + typeName + "   "
                    // + extraInfo);
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        // System.out.println("NETWORK CONNECTED     " + extraInfo);
//                        String subType = info[i].getSubtypeName();
                        if (extraInfo != null && extraInfo.contains("cmwap")) {
                            // System.out.println("NETWORK CONNECTED SHUTDOWN    " + extraInfo);
                            // connectivity.stopUsingNetworkFeature(ConnectivityManager.TYPE_MOBILE, "wap");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
