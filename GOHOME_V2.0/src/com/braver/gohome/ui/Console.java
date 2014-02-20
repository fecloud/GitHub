/**
 * @(#) Console.java Created on 2014-1-1
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.gohome.ui;

import java.util.Scanner;

import com.braver.gohome.GoHomeIO;
import com.braver.gohome.Message;

/**
 * The class <code>Console</code>
 * 
 * @author braver
 * @version 1.0
 */
public class Console implements GoHomeIO {

	protected Scanner scanner;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.GoHomeIO#write(java.lang.String)
	 */
	/**
	 * @param scanner
	 */
	public Console() {
		super();
		this.scanner = new Scanner(System.in);
	}

	@Override
	public void write(String str) {
		System.out.print(str);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.GoHomeIO#writeError(java.lang.String)
	 */
	@Override
	public void writeError(String str) {
		System.err.print(str);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.GoHomeIO#writeln(java.lang.String)
	 */
	@Override
	public void writeln(String str) {
		System.out.println(str);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.GoHomeIO#writeErrorLn(java.lang.String)
	 */
	@Override
	public void writeErrorLn(String str) {
		System.err.println(str);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.GoHomeIO#readLine()
	 */
	@Override
	public String readLine() {
		return scanner.nextLine();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.GoHomeIO#readInt()
	 */
	@Override
	public int readInt() {
		return Integer.valueOf(scanner.nextLine());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.GoHomeIO#writeMessage(com.braver.gohome.Message)
	 */
	@Override
	public void writeMessage(Message message) {
		writeln(String.format("收到一条消息 type:%1$s content:%2$s", message.getType(),
				message.getContent()));

		if (null != message && message.getType().equals(Message.TYPE_PASS_CODE)) {
			writeln("验证码保存在：" + message.getContent());
		}
	}

}
