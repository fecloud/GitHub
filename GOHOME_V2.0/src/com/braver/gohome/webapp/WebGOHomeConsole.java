/**
 * @(#) WebGOHomeConsole.java Created on 2014-1-2
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.gohome.webapp;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;

import com.braver.gohome.GoHome;
import com.braver.gohome.GoHomeIO;
import com.braver.gohome.Message;
import com.braver.gohome.resouce.Station;
import com.braver.gohome.resouce.Station_Name;
import com.braver.gohome.webapp.WebGoHomeMessageInbound.InboundListener;

/**
 * The class <code>WebGOHomeConsole</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class WebGOHomeConsole implements InboundListener, GoHomeIO {

	Logger logger = Logger.getLogger(WebGOHomeConsole.class);

	protected String clientId;

	private String args;

	protected GoHome goHome;

	private Queue<String> queue = new ArrayBlockingQueue<String>(20);

	private Hashtable<WebGOHomeConsole, WebGoHomeMessageInbound> hashtable;

	private String webRoot;

	private boolean stop;

	public WebGOHomeConsole(String args,
			Hashtable<WebGOHomeConsole, WebGoHomeMessageInbound> hashtable) {
		super();
		this.args = args;
		this.hashtable = hashtable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.GoHomeIO#write(java.lang.String)
	 */
	@Override
	public void write(String str) {
		if (null != str) {
			final String send = new Message(Message.TYPE_GENERAL, String.format(
					"<span style=\"color:#FFFFFF\">%1$s</span", str)).toJSON();
			sendInbound(send);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.GoHomeIO#writeln(java.lang.String)
	 */
	@Override
	public void writeln(String str) {
		if (null != str) {
			final String send = new Message(Message.TYPE_GENERAL, String.format(
					"<span style=\"color:#FFFFFF\">%1$s<br /></span", str)).toJSON();
			sendInbound(send);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.GoHomeIO#writeError(java.lang.String)
	 */
	@Override
	public void writeError(String str) {
		if (null != str) {
			final String send = new Message(Message.TYPE_GENERAL, String.format(
					"<span style=\"color:red\">%1$s</span>", str)).toJSON();
			sendInbound(send);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.GoHomeIO#writeErrorLn(java.lang.String)
	 */
	@Override
	public void writeErrorLn(String str) {
		if (null != str) {
			final String send = new Message(Message.TYPE_GENERAL, String.format(
					"<span style=\"color:red\">%1$s<br/></span>", str)).toJSON();
			sendInbound(send);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.GoHomeIO#readLine()
	 */
	@Override
	public String readLine() {
		synchronized (queue) {
			if (queue.peek() == null) {
				try {
					if (!stop) {
						queue.wait();
					}
				} catch (InterruptedException e) {
					logger.error("readLine", e);
				}
				return queue.poll();
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.GoHomeIO#readInt()
	 */
	@Override
	public int readInt() {
		return Integer.valueOf(readLine());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.GoHomeIO#writeMessage(com.braver.gohome.Message)
	 */
	@Override
	public void writeMessage(Message message) {
		if (null != message) {
			if ( message.getType().equals(Message.TYPE_PASS_CODE)) {
				writeln(String.format("<img src=\"%1$s?randin=%2$s\" />", clientId + ".jpg",
						new Random().nextLong()));
			}else if(message.getType().equals(Message.TYPE_INPUT_PASS_CODE)){
				//下发自动输入4位回车
				final String send = new Message(Message.TYPE_INPUT_PASS_CODE, message.getContent()).toJSON();
				sendInbound(send);
			}
		}
	}

	protected void sendInbound(String str) {
		if (null != getInbound()) {
			getInbound().sendTextMessage(str);
		}
	}

	protected WebGoHomeMessageInbound getInbound() {
		if (hashtable.containsKey(this)) {
			return hashtable.get(this);
		}
		throw new RuntimeException("not found WebGoHomeMessageInbound...");
	}

	/**
	 * 设置参数
	 */
	public void parserOption(String[] args) {
		if (null != args) {
			for (String s : args) {
				if (s.startsWith("-S")) {
					// 服务器地址
					final String v = s.replace("-S", "");
					goHome.setSite(v);
				} else if (s.startsWith("-M")) {
					final String v = s.replace("-M", "");
					goHome.setModel(v);
					// 模式
				} else if (s.startsWith("-T")) {
					final String v = s.replace("-T", "");
					goHome.setTimeout(Integer.valueOf(v));
					// http超时时间
				} else if (s.startsWith("-p")) {
					final String v = s.replace("-p", "");
					goHome.setProxy(v);
					// 代理
				} else if (s.startsWith("-U")) {
					final String v = s.replace("-U", "");
					goHome.setUsername(v);
					// 用户名
				} else if (s.startsWith("-P")) {
					final String v = s.replace("-P", "");
					goHome.setPassword(v);
					// 密码
				} else if (s.startsWith("-C")) {
					final String v = s.replace("-C", "");
					goHome.setPasscodeimage(v);
					// 验证码路径
				} else if (s.startsWith("-d")) {
					final String v = s.replace("-d", "");
					goHome.getPiaoInfo().setDate(v);
					// 订票时间
				} else if (s.startsWith("-R")) {
					final String v = s.replace("-R", "");
					goHome.getPiaoInfo().setRen(v);
					// 订票人
				} else if (s.startsWith("-Z")) {
					final String v = s.replace("-Z", "");
					goHome.getCheCiOption().setZuoweiString(v);
					// 坐位
				} else if (s.startsWith("-c")) {
					final String v = s.replace("-c", "");
					goHome.getCheCiOption().setCheciString(v);
					// 车次
				} else if (s.startsWith("-s")) {
					final String v = s.replace("-s", "");
					goHome.getCheCiOption().setStart_time(v);
					// 开始时间
				} else if (s.startsWith("-e")) {
					final String v = s.replace("-e", "");
					goHome.getCheCiOption().setEnd_time(v);
					// 结束时间
				} else if (s.startsWith("-f")) {
					final String v = s.replace("-f", "");
					List<Station> result = Station_Name.getInstance().getStationZhCn(v);
					if (null != result && result.size() == 1) {
						// 始发站
						goHome.getPiaoInfo().setStart(result.get(0));
					} else {
						System.err.println("设置始发站参数错误");
					}

				} else if (s.startsWith("-t")) {
					final String v = s.replace("-t", "");
					List<Station> result = Station_Name.getInstance().getStationZhCn(v);
					if (null != result && result.size() == 1) {
						// 目的地
						goHome.getPiaoInfo().setEnd(result.get(0));
					} else {
						System.err.println("设置目的地站参数错误");
					}

				}
			}
		}
	}

	/**
	 * 检查用户名和密码是否已输入
	 * 
	 * @param args
	 * @return
	 */
	public static boolean validateRequire(String[] args) {
		if (null != args) {
			boolean inputU = false;
			boolean inputP = false;
			for (String s : args) {
				if (s.startsWith("-U")) {
					inputU = true;
				} else if (s.startsWith("-P")) {
					inputP = true;
				}
			}

			if (inputU && inputP) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.webapp.WebGoHomeMessageInbound.InboundListener#
	 * onTextMessage(java.lang.String)
	 */
	@Override
	public void onTextMessage(String str) {
		System.out.println("str:" + str);
		synchronized (queue) {
			final boolean isNotify = queue.peek() == null ? true : false;
			if (queue.offer(str)) {
				if (isNotify) {
					queue.notify();
				}
			}
		}
		writeErrorLn(str);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.gohome.webapp.WebGoHomeMessageInbound.InboundListener#onOpen()
	 */
	@Override
	public void onOpen() {
		goHome = new GoHome(this);
		clientId = UUID.randomUUID().toString();
		goHome.setPasscodeimage(webRoot + clientId + ".jpg");
		System.out.println(String.format("clientId %1$s: onOpen passcode:%2$s", clientId,
				goHome.getPasscodeimage()));
		writeln("clientId:" + clientId);

		if (null != args) {
			String string[] = parserToken(args);
			parserOption(string);
		}
		goHome.startGoHome();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.gohome.webapp.WebGoHomeMessageInbound.InboundListener#onClose
	 * ()
	 */
	@Override
	public void onClose() {
		goHome.stopGOHome();
		stop = true;
		hashtable.remove(this);
		synchronized (queue) {
			queue.notify();
		}
	}

	/**
	 * @return the webRoot
	 */
	public String getWebRoot() {
		return webRoot;
	}

	/**
	 * @param webRoot
	 *            the webRoot to set
	 */
	public void setWebRoot(String webRoot) {
		this.webRoot = webRoot;
	}

	public static String[] parserToken(String str) {
		ArrayList<String> strings = new ArrayList<String>();

		if (null != str) {
			StringTokenizer tokenizer = new StringTokenizer(str.trim());
			while (tokenizer.hasMoreTokens()) {
				strings.add(tokenizer.nextToken());
			}
			final String strs[] = new String[strings.size()];
			for (int i = 0; i < strings.size(); i++) {
				strs[i] = strings.get(i);
			}
			return strs;
		}
		return null;
	}
}
