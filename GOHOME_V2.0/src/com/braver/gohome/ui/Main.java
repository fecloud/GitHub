/**
 * @(#) Main.java Created on 2014-1-1
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.gohome.ui;

import java.util.List;

import org.apache.log4j.Logger;

import com.braver.gohome.GoHome;
import com.braver.gohome.resouce.Station;
import com.braver.gohome.resouce.Station_Name;

/**
 * The class <code>Main</code>
 * 
 * @author braver
 * @version 1.0
 */
public class Main {

	protected GoHome goHome;

	public Main() {
		goHome = new GoHome(new Console());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Logger.getLogger(Main.class).error("");
		if (validateRequire(args)) {
			Main main = new Main();
			main.parserOption(args);
			main.loop();
			while (true)
				;
		} else {
			System.err.println("没有设置用户名和密码!");
			System.err.println("Option ");
			System.err.println("-U username");
			System.err.println("-P password");
			System.err.println("-S site");
			System.err.println("-M http or https");
			System.err.println("-T timeout ms");
			System.err.println("-R buy rent");
			System.err.println("-p proxy like 127.0.0.1:8888");
			System.err.println("-d buy time like 2014-01-12");
			System.err.println("-C passcode path");
			System.err.println("-Z zuowei");
			System.err.println("-c checi");
			System.err.println("-s checi start time");
			System.err.println("-e checi end time");
			System.err.println("-f 始发站");
			System.err.println("-e 目的地");
		}
	}

	public void loop() {
		goHome.startGoHome();
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

}
