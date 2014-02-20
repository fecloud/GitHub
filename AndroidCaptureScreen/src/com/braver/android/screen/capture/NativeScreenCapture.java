/**
 * @(#) NativeGrabScreen.java Created on 2012-12-12
 *
 * Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.android.screen.capture;

/**
 * The class <code>NativeGrabScreen</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class NativeScreenCapture {

	static {
		System.loadLibrary("screencapture23");
	}

	public synchronized static native int screenCapture(byte[] arr,
			int startPos, int len);
}
