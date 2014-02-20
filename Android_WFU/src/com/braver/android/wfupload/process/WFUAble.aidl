/**
 * @(#) WFUAble.java Created on 2013-9-11
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.android.wfupload.process;

import com.braver.android.wfupload.process.WFUploadListener;

/**
 * The class <code>WFUAble</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
interface WFUAble {

	 void registerListener(WFUploadListener listener);
	 
	 void unRegisterListener();
	
}
