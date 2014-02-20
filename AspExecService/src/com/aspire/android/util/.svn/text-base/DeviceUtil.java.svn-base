/**
 * @(#) CommandUtil.java Created on 2012-3-30
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The class <code>CommandUtil</code>
 * 
 * @author linjunsui
 * @version 1.0
 */
public class DeviceUtil {

	/**
	 * execute a command
	 * 
	 * @param command
	 *            command to be execute
	 * @return command output
	 * @throws IOException
	 *             ioException
	 * @throws InterruptedException
	 *             ioExcetpion
	 */
	static public String execute(String command) throws IOException,
			InterruptedException {
		if (command == null) {
			return null;
		}

		String commands[] = command.split(" ");

		Process execDf = Runtime.getRuntime().exec(commands);
		execDf.waitFor();
		BufferedReader bufferdReader = new BufferedReader(
				new InputStreamReader(execDf.getInputStream()));
		StringBuilder stringBuilder = new StringBuilder(1024);
		char[] buffer = new char[1024];
		int readLength;
		while ((readLength = bufferdReader.read(buffer)) > 0) {
			stringBuilder.append(buffer, 0, readLength);
		}
		return stringBuilder.toString();
	}

}
