/**
 * @(#) InitTaskExecute.java Created on 2014-1-4
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.gohome.business.task;

import java.util.List;

import com.braver.gohome.GoHome;
import com.braver.gohome.GoHomeIO;
import com.braver.gohome.Utils;
import com.braver.gohome.resouce.Station;
import com.braver.gohome.resouce.Station_Name;
import com.braver.gohome.task.BaseTask;
import com.braver.gohome.task.Task;
import com.braver.gohome.task.TaskStatus;
import com.braver.gohome.task.executeable.TaskExecuteable;

/**
 * The class <code>InitTaskExecute</code>
 * 
 * @author braver
 * @version 1.0
 */
public class InitTaskExecute implements TaskExecuteable {

	private GoHome goHome;

	private GoHomeIO goHomeIO;

	/**
	 * @param goHome
	 */
	public InitTaskExecute(GoHome goHome) {
		super();
		this.goHome = goHome;
		goHomeIO = goHome.getGoHomeIO();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.gohome.task.executeable.TaskExecuteable#executeTask(com.braver
	 * .gohome.task.Task)
	 */
	@Override
	public void executeTask(Task task) {
		if (task.getTaskStatus() == TaskStatus.SUBMIT) {
			prinSystem();
			init();
		}
	}

	public void prinSystem() {
		goHome.getGoHomeIO().writeErrorLn("12306地址:" + goHome.getSite());
		goHome.getGoHomeIO().writeErrorLn("12306刷票模式:" + goHome.getModel());
		goHome.getGoHomeIO().writeErrorLn("请求超时时间:" + goHome.getTimeout() + "ms");
		String proxy = goHome.getProxy() == null ? "无" : goHome.getProxy();
		goHome.getGoHomeIO().writeErrorLn("代理服务器:" + proxy);
	}

	public void init() {

		if (null == goHome.getUsername()) {
			goHomeIO.writeln("请输入用户名  :");
			goHome.setUsername(goHomeIO.readLine());
		}
		goHome.getGoHomeIO().writeErrorLn("您的用户名:" + goHome.getUsername());

		if (null == goHome.getPassword()) {
			goHomeIO.writeln("请输入密码  :");
			goHome.setPassword(goHomeIO.readLine());
		}
		goHome.getGoHomeIO().writeErrorLn("您的密码:" + goHome.getPassword());

		if (goHome.getPiaoInfo().getRen() == null) {
			goHomeIO.writeln("请输入订票人 :");
			goHome.getPiaoInfo().setRen(goHomeIO.readLine());
		}
		goHome.getGoHomeIO().writeErrorLn("订票人:" + goHome.getPiaoInfo().getRen());

		if (goHome.getPiaoInfo().getDate() == null) {
			goHomeIO.writeln("请输入乘车时间  ex 2014-12-31 不输入为 " + Utils.dataStringAfter(19) + " :");
			goHome.getPiaoInfo().setDate(goHomeIO.readLine());
		}
		goHome.getGoHomeIO().writeErrorLn("乘车时间:" + goHome.getPiaoInfo().getDate());

		if (goHome.getPiaoInfo().getStart() == null) {
			goHomeIO.writeln("请输入出发地 :");
			goHome.getPiaoInfo().setStart(getStation(goHomeIO));
		}
		goHome.getGoHomeIO().writeErrorLn("出发地:" + goHome.getPiaoInfo().getStart().getZh_name());

		if (goHome.getPiaoInfo().getEnd() == null) {
			goHomeIO.writeln("请输入目的地 :");
			goHome.getPiaoInfo().setEnd(getStation(goHomeIO));
		}
		goHome.getGoHomeIO().writeErrorLn("目的地:" + goHome.getPiaoInfo().getEnd().getZh_name());

		/* ======================车次输入=============================== */
		if (goHome.getCheCiOption().getCheci_String() == null) {
			goHomeIO.writeln("请输入车次选择 如  K932-K1096 :");
			final String checi = goHomeIO.readLine();
			goHome.getCheCiOption().parserChici(checi);
		}
		goHomeIO.writeErrorLn("您选择的车次 :" + goHome.getCheCiOption().checiToString());

		if (goHome.getCheCiOption().getZuowei().isEmpty()) {
			goHomeIO.writeln("请输入坐座选择  1.商务座 2.特等座 3.一等座 4.二等 座 5.软卧 6.硬卧 7.软座 8.硬座  9.无座  :");
			final String zuowei = goHomeIO.readLine();
			goHome.getCheCiOption().parserZuowei(zuowei);
		}
		goHomeIO.writeErrorLn("您选择的坐座：" + goHome.getCheCiOption().zuoweiToString());

		if (goHome.getCheCiOption().getStart_time() == null) {
			goHomeIO.writeln("请输入发车区间起始时间 ex 21:00 :");
			goHome.getCheCiOption().setStart_time(goHomeIO.readLine());
		}
		String start_time = goHome.getCheCiOption().getStart_time() != null ? "未设置"
				: goHome.getCheCiOption().getStart_time();
		goHomeIO.writeErrorLn("发车区间起始时间: " + start_time);

		if (goHome.getCheCiOption().getEnd_time() == null) {
			goHomeIO.writeln("请输入发车区间结束时间 ex 22:00 :");
			goHome.getCheCiOption().setEnd_time(goHomeIO.readLine());
		}
		String end_time = goHome.getCheCiOption().getEnd_time() != null ? "未设置" : goHome
				.getCheCiOption().getEnd_time();
		goHomeIO.writeErrorLn("发车区间结束时间 :" + end_time);

		final Task task = new BaseTask(new LoginTaskExecute(goHome));
		goHome.getService().addTask(task);
	}

	/**
	 * 选择车站
	 * 
	 * @param scanner
	 * @return
	 */
	public Station getStation(GoHomeIO io) {
		while (true) {
			String s = goHomeIO.readLine();
			if (null != s && !"".equals(s.trim())) {
				List<Station> result = Station_Name.getInstance().getStationZhCn(s);
				if (result.isEmpty()) {
					io.writeln("请重新输入:");
				} else if (result.size() > 1) {
					io.writeln("查询到：");
					for (int i = 0; i < result.size(); i++) {

						io.write(i + "." + result.get(i).getZh_name() + "  ");
						if ((i + 1) % 8 == 0) {
							io.writeln("");
						}
					}
					io.writeln("");
					io.writeln("请选择:-1重新输入站点");
					int i = io.readInt();
					if (i < 0) {
						io.writeln("请重新输入:");
						continue;
					} else {
						io.writeln("您选择了" + result.get(i).getZh_name());
						return result.get(i);
					}
				} else {
					io.writeln("您选择了" + result.get(0).getZh_name());
					return result.get(0);
				}
			} else {
				io.writeln("请重新输入:");
			}
		}
	}

}
