/**
 * 
 */
package com.braver.bluetoothchat.util;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;

/**
 * @author braver
 * 
 */
public final class Utils {

	/**
	 * 取得默认的蓝牙适配器
	 * 
	 * @return
	 */
	@SuppressLint("NewApi")
	public static BluetoothAdapter getDefaultBluetoothAdapter() {
		return BluetoothAdapter.getDefaultAdapter();
	}

	/**
	 * 是否有蓝牙适配器
	 * 
	 * @return
	 */
	public static boolean haveBluetoothAdapter() {
		return getDefaultBluetoothAdapter() == null ? false : true;
	}

	/**
	 * 蓝牙是否打开
	 * 
	 * @return
	 */
	@SuppressLint("NewApi")
	public static boolean bluetoothIsEnable() {
		return getDefaultBluetoothAdapter().isEnabled();
	}

	/**
	 * 打开蓝牙
	 * 
	 * @return
	 */
	@SuppressLint("NewApi")
	public static boolean enableBluetoothAdapter() {
		if (haveBluetoothAdapter() && !bluetoothIsEnable()) {
			return getDefaultBluetoothAdapter().enable();
		}
		return false;
	}
}
