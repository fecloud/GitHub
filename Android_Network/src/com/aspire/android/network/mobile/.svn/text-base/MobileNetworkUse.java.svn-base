/**
 * @(#) MobileNetworkUse.java Created on 2012-9-14
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.network.mobile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.util.Log;

import com.aspire.android.network.NetworkUse;

/**
 * The class <code>MobileNetworkUse</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class MobileNetworkUse implements NetworkUse {

    private static final String TAG = "MobileNetworkUse";

    /**
     * 记录网络流量的文件
     */
    private static final String NETWORK_USE_FILE = "/proc/net/dev";

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.network.NetworkUse#getTxBytes()
     */
    public long getTxBytes() {
        final long[] datas = getNetworkUseArray();
        if (null != datas && datas.length == 16) {
            return datas[9];
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.network.NetworkUse#getRxBytes()
     */
    public long getRxBytes() {
        final long[] datas = getNetworkUseArray();
        if (null != datas && datas.length == 16) {
            return datas[0];
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.aspire.android.network.NetworkUse#getTotalBytes()
     */
    public long getTotalBytes() {
        final long[] datas = getNetworkUseArray();
        if (null != datas && datas.length == 16) {
            return datas[0] + datas[9];
        }
        return 0;
    }

    /**
     * 根据机型配置,读取记录2g/3g流量一行
     * 
     * @return
     */
    private String readNetworkUse() {
        try {
            final String readField = new MobileFieldConfig().getReadField();
            final FileInputStream in = new FileInputStream(NETWORK_USE_FILE);
            final BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while (null != (line = reader.readLine())) {
                if (line.trim().contains(readField)) {
                    line = line.replaceFirst("^\\w+:", "");
                    break;
                }
            }
            reader.close();
            return line;
        } catch (IOException e) {
            Log.e(TAG, "read file error", e);
            return null;
        }
    }

    /**
     * 分析解析行数据
     * 
     * @return
     */
    private long[] getNetworkUseArray() {
        final String line = readNetworkUse();
        long[] datas = null;
        if (null != line) {
            String[] array = line.split(" ");
            array = clearEmptyArray(array);
            if (null != array) {
                datas = new long[array.length];
                for (int i = 0; i < datas.length; i++) {
                    datas[i] = Long.parseLong(array[i]);
                }
            }
        }
        return datas;

    }

    /**
     * 清除字符串的的空元素
     * 
     * @param array
     * @return
     */
    private String[] clearEmptyArray(String[] array) {
        if (null != array) {
            final ArrayList<String> strings = new ArrayList<String>();
            for (String str : array) {
                if (null != str && !str.trim().equals(""))
                    strings.add(str);
            }
            // copy array
            final String[] clsArray = new String[strings.size()];
            for (int i = 0; i < clsArray.length; i++) {
                clsArray[i] = strings.get(i);
            }
            return clsArray;
        }
        return array;
    }

}
