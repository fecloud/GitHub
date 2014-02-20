/**
 * @(#) EatBread.java Created on 2013-3-18
 *
 * Copyright (c) 2013 com.braver All Rights Reserved
 */
package com.braver;

/**
 * The class <code>EatBread</code>
 * 
 * @author braver
 * @version 1.0
 */
public class EatBread extends Task {

	/**
	 * @param executeable
	 */
	public EatBread(CallBack back) {
		super(new EatBreadExecuteable());
		setBack(back);
	}

}
