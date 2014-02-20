/**
 * @(#) QueryHuoCheExecute.java Created on 2014-1-2
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.gohome.business.task;

import java.util.Hashtable;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.braver.gohome.CheCiOption;
import com.braver.gohome.GoHome;
import com.braver.gohome.Utils;
import com.braver.gohome.ZuoWei;
import com.braver.gohome.business.Business;
import com.braver.gohome.business.HttpBusiness;
import com.braver.gohome.business.HttpsBusiness;
import com.braver.gohome.exception.BusinessException;
import com.braver.gohome.task.BaseTask;
import com.braver.gohome.task.Task;
import com.braver.gohome.task.TaskStatus;
import com.braver.gohome.task.executeable.TaskExecuteable;

/**
 * The class <code>QueryHuoCheExecute</code> 查询火车票
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class QueryHuoCheExecute implements TaskExecuteable {

	Logger logger = Logger.getLogger(QueryHuoCheExecute.class);

	protected GoHome home;

	protected Business business;

	protected String html;

	protected JSONArray query;

	/**
	 * 预定的
	 */
	protected JSONObject yuding;

	protected boolean start_init = true;

	boolean todo = true;

	private Task task;

	/**
	 * @param executeable
	 */
	public QueryHuoCheExecute(GoHome home) {
		super();
		this.home = home;
		init();
	}

	protected void init() {
		if (home.getModel().equalsIgnoreCase("https")) {
			// https
			business = new HttpsBusiness(home);
		} else {
			// http
			business = new HttpBusiness(home);
		}
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
		this.task = task;
		if (task.getTaskStatus() == TaskStatus.SUBMIT) {
			task.setTaskStatus(TaskStatus.DOING);

			home.getGoHomeIO().writeln("进入查询火车票页面========================");
			while (todo) {
				try {
					if (task.getTaskStatus() == TaskStatus.DOING) {
						if (start_init)
							otnIndexInit();
					} else {
						todo = false;
					}
					if (task.getTaskStatus() == TaskStatus.DOING) {
						otnLeftTicketQuery();
						Utils.sleep(1);
					} else {
						todo = false;
					}
					if (task.getTaskStatus() == TaskStatus.DOING) {
						macther();
					} else {
						todo = false;
					}

					if (null == yuding)
						continue;

					if (task.getTaskStatus() == TaskStatus.DOING) {
						otnLoginCheckUser(); // 一般会过的
					} else {
						todo = false;
					}
					if (task.getTaskStatus() == TaskStatus.DOING) {
						otnLeftTicketSubmitOrderRequest();

					} else {
						todo = false;
					}

				} catch (Exception e) {
					logger.error("", e);
					home.getGoHomeIO().writeErrorLn("出现错误暂停2s");
					Utils.sleep(1);
				}

			}
			home.getGoHomeIO().writeln("查询预定信息任务成功完成");
		}

	}

	/**
	 * 下载登录页面
	 * 
	 * @throws BusinessException
	 */
	protected void otnIndexInit() throws BusinessException {
		home.getGoHomeIO().writeln("加载查询预定信息页面...");
		int count = 3;
		boolean sucess = false;
		while (count > 0) {
			html = business.otnIndexInit(home.getRedirect());
			if (null != html) {
				sucess = true;
				home.getGoHomeIO().writeln("加载查询预定信息页面成功");
				start_init = false;
				break;
			}
			count--;
		}
		if (!sucess) {
			throw new BusinessException("加载登录查询预定信息错误");
		}

	}

	protected void otnLeftTicketQuery() throws BusinessException {
		home.getGoHomeIO().writeln("开始查询预定车次...");
		int count = 3;
		boolean sucess = false;
		while (count > 0) {
			query = business.otnLeftTicketQuery(home.getPiaoInfo().getDate(), home.getPiaoInfo()
					.getStart().getCz(), home.getPiaoInfo().getEnd().getCz(), "ADULT");
			if (null != query && query.size() > 0) {
				sucess = true;
				home.getGoHomeIO().writeln("查询预定车次成功");
				break;
			} else if (null != query && query.size() == 0) {
				home.getGoHomeIO().writeErrorLn("没有查询到相关车次");
				home.getPiaoInfo().setStart(null);
				home.getPiaoInfo().setEnd(null);
				todo = false;
				task.setTaskStatus(TaskStatus.CANCELED);
				home.getService().addTask(initTaskExecute());
				return;
			}
			count--;
		}
		if (!sucess) {
			throw new BusinessException("查询预定车次错误");
		}
	}

	/**
	 * 点击预定按钮时,检查用户登录
	 * 
	 * @throws BusinessException
	 */
	protected void otnLoginCheckUser() throws BusinessException {

		home.getGoHomeIO().writeln("点击预定按钮时,检查用户登录...");
		int count = 3;
		boolean sucess = false;
		while (count > 0) {
			if (business.otnLoginCheckUser()) {
				sucess = true;
				home.getGoHomeIO().writeln("点击预定按钮时,检查用户登录成功");
				break;
			}
			count--;
		}
		if (!sucess) {
			throw new BusinessException("点击预定按钮时,检查用户登录错误");
		}
	}

	/**
	 * Ajax提交预定车次
	 */
	protected void otnLeftTicketSubmitOrderRequest() throws BusinessException {

		home.getGoHomeIO().writeln("Ajax提交预定车次...");
		int count = 3;
		boolean sucess = false;
		while (count > 0) {
			String tour_flag = "dc";
			String purpose_codes = "ADULT";

			JSONObject object = business.otnLeftTicketSubmitOrderRequest(
					yuding.getString("secretStr"), home.getPiaoInfo().getDate(),
					Utils.dataStringAfter(10), tour_flag, purpose_codes, home.getPiaoInfo()
							.getStart().getZh_name(), home.getPiaoInfo().getEnd().getZh_name());

			if (null != object && object.containsKey("status")) {
				if (object.getBoolean("status")) {
					sucess = true;
					home.getGoHomeIO().writeln("Ajax提交预定车次成功");
					final Task task2 = new BaseTask(new YuDingExecute(yuding, home));
					home.getService().addTask(task2);
					todo = false;
					break;
				} else if (!object.getBoolean("status")) {
					todo = false;
					home.getGoHomeIO().writeErrorLn(object.getString("messages"));
					return;
				}
			}
			count--;
		}
		if (!sucess) {
			throw new BusinessException("Ajax提交预定车次错误");
		}
	}

	/**
	 * 匹配到了返回true
	 * 
	 * @return
	 */
	protected void macther() {

		printlnQuery(query);
		query = canBuy(query);
		query = canTime(query);
		query = canCheCi(query);
		yuding = canZuoWei(query);

	}

	/**
	 * 是否在用户指定的车次
	 * 
	 * @param array
	 * @return
	 */
	public JSONArray canCheCi(JSONArray array) {
		home.getGoHomeIO().writeln("过滤指定的车次");
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		for (int i = 0; i < array.size(); i++) {
			jsonObject = array.getJSONObject(i);
			if (jsonObject.getJSONObject("queryLeftNewDTO") != null) {
				final CheCiOption cheCiOption = home.getCheCiOption();
				if (cheCiOption.getCheci().isEmpty()) {
					printCheCiInfo(jsonObject);
					jsonArray.add(jsonObject);
				}
				for (String che : cheCiOption.getCheci()) {
					if (che.equalsIgnoreCase(jsonObject.getJSONObject("queryLeftNewDTO").getString(
							"station_train_code"))) {
						printCheCiInfo(jsonObject);
						jsonArray.add(jsonObject);
					}
				}
			}
		}

		return jsonArray;
	}

	/**
	 * 过滤指定的时间
	 * 
	 * @return
	 */
	public JSONArray canTime(JSONArray array) {
		home.getGoHomeIO().writeln("过滤指定的时间的车次");
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		String cheStartTime = null;

		final CheCiOption cheCiOption = home.getCheCiOption();
		int user_start = 0;
		int user_end = 2400;
		if (cheCiOption.getStart_time() != null && !cheCiOption.getStart_time().trim().equals("")
				&& cheCiOption.getStart_time().contains(":")) {
			user_start = Integer.valueOf(cheCiOption.getStart_time().replace(":", ""));
		}
		if (cheCiOption.getEnd_time() != null && !cheCiOption.getEnd_time().trim().equals("")
				&& cheCiOption.getEnd_time().contains(":")) {
			user_end = Integer.valueOf(cheCiOption.getEnd_time().replace(":", ""));
		}

		for (int i = 0; i < array.size(); i++) {
			jsonObject = array.getJSONObject(i);
			if (jsonObject.getJSONObject("queryLeftNewDTO") != null) {
				cheStartTime = jsonObject.getJSONObject("queryLeftNewDTO").getString("start_time");

				final int start = Integer.valueOf(cheStartTime.replace(":", ""));

				if (start >= user_start && start <= user_end) {
					printCheCiInfo(jsonObject);
					jsonArray.add(jsonObject);
				}

			}
		}
		return jsonArray;
	}

	/**
	 * 是否可预定
	 * 
	 * @return
	 */
	public JSONArray canBuy(JSONArray array) {
		home.getGoHomeIO().writeln("过滤可以预定的车次");
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		for (int i = 0; i < array.size(); i++) {
			jsonObject = array.getJSONObject(i);
			if (jsonObject.getJSONObject("queryLeftNewDTO") != null) {
				final String can = jsonObject.getJSONObject("queryLeftNewDTO").getString(
						"canWebBuy");
				if (null != can && "Y".equalsIgnoreCase(can)) {
					jsonArray.add(jsonObject);
					printCheCiInfo(jsonObject);
				}
			}
		}
		return jsonArray;
	}

	/**
	 * 打印查询到的车次信息
	 * 
	 * @param array
	 */
	public void printlnQuery(JSONArray array) {
		home.getGoHomeIO().writeln("查询到" + array.size() + "个结果 匹配中...");
		JSONObject jsonObject = null;
		for (int i = 0; i < array.size(); i++) {
			jsonObject = array.getJSONObject(i);
			if (jsonObject.getJSONObject("queryLeftNewDTO") != null) {
				home.getGoHomeIO().write((i + 1) + ".");
				printCheCiInfo(jsonObject);
			}
		}
	}

	/**
	 * 打印车次信息
	 * 
	 * @param jsonObject
	 */
	public void printCheCiInfo(JSONObject jsonObject) {
		if (null != jsonObject && jsonObject.getJSONObject("queryLeftNewDTO") != null) {
			final JSONObject queryLeftNewDTO = jsonObject.getJSONObject("queryLeftNewDTO");
			final StringBuilder builder = new StringBuilder();
			builder.append(queryLeftNewDTO.get("station_train_code")).append("  ");
			builder.append(queryLeftNewDTO.get("start_station_name")).append("  ");
			builder.append(queryLeftNewDTO.get("to_station_name")).append("  ");
			builder.append(queryLeftNewDTO.get("end_station_name")).append("  ");
			builder.append(queryLeftNewDTO.get("start_time")).append("  ");
			builder.append(queryLeftNewDTO.get("arrive_time")).append("  ");
			builder.append(queryLeftNewDTO.get("canWebBuy")).append("  ");

			String swz_num = queryLeftNewDTO.getString("swz_num");
			if (!swz_num.equals("") && !"--".equals(swz_num)) {
				builder.append("商务座:").append(swz_num).append("  ");
			}
			String tz_num = queryLeftNewDTO.getString("tz_num");
			if (!tz_num.equals("") && !"--".equals(tz_num)) {
				builder.append("特等座:").append(tz_num).append("  ");
			}
			String zy_num = queryLeftNewDTO.getString("zy_num");
			if (!zy_num.equals("") && !"--".equals(zy_num)) {
				builder.append("一等座:").append(zy_num).append("  ");
			}
			String ze_num = queryLeftNewDTO.getString("ze_num");
			if (!ze_num.equals("") && !"--".equals(ze_num)) {
				builder.append("二等座:").append(ze_num).append("  ");
			}
			String rw_num = queryLeftNewDTO.getString("rw_num");
			if (!rw_num.equals("") && !"--".equals(rw_num)) {
				builder.append("软卧:").append(rw_num).append("  ");
			}
			String yw_num = queryLeftNewDTO.getString("yw_num");
			if (!yw_num.equals("") && !"--".equals(yw_num)) {
				builder.append("硬卧:").append(yw_num).append("  ");
			}
			String rz_num = queryLeftNewDTO.getString("rz_num");
			if (!rz_num.equals("") && !"--".equals(rz_num)) {
				builder.append("软座:").append(rz_num).append("  ");
			}
			String yz_num = queryLeftNewDTO.getString("yz_num");
			if (!yz_num.equals("") && !"--".equals(yz_num)) {
				builder.append("硬座:").append(yz_num).append("  ");
			}
			String wz_num = queryLeftNewDTO.getString("wz_num");
			if (!wz_num.equals("") && !"--".equals(wz_num)) {
				builder.append("无座:").append(wz_num).append("  ");
			}
			home.getGoHomeIO().writeln(builder.toString());
		}
	}

	/**
	 * 匹配座位信息得出一个车次
	 * 
	 * @param array
	 * @return
	 */
	public JSONObject canZuoWei(JSONArray array) {
		JSONObject returnjsonObject = null;
		JSONObject jsonObject = null;
		home.getGoHomeIO().writeln("座位信息匹配中...");

		for (ZuoWei zuoWei : home.getCheCiOption().getZuowei()) {
			for (int i = 0; i < array.size(); i++) {
				jsonObject = array.getJSONObject(i);
				if (cheCiMactherZuoWei(jsonObject, zuoWei)) {
					returnjsonObject = jsonObject;
					// 查询到了
					break;
				}
			}
		}
		return returnjsonObject;
	}

	public boolean cheCiMactherZuoWei(JSONObject checi, ZuoWei wei) {
		if (null != checi && null != wei) {
			if (getCheCiNum(checi.getJSONObject("queryLeftNewDTO")).containsKey(wei.getName())) {
				home.getGoHomeIO().writeErrorLn(
						"匹配到"
								+ checi.getJSONObject("queryLeftNewDTO").getString(
										"station_train_code") + "-" + wei.getZh_name());
				return true;
			}

		}
		return false;
	}

	/**
	 * 取车次所有的Num
	 * 
	 * @param queryLeftNewDTO
	 * @return
	 */
	public Hashtable<String, Boolean> getCheCiNum(JSONObject queryLeftNewDTO) {
		Hashtable<String, Boolean> hashtable = new Hashtable<String, Boolean>();
		if (null != queryLeftNewDTO) {
			if (isYou(queryLeftNewDTO.getString("gg_num")))
				hashtable.put("gg_num", true);
			if (isYou(queryLeftNewDTO.getString("gr_num")))
				hashtable.put("gr_num", true);
			if (isYou(queryLeftNewDTO.getString("qt_num")))
				hashtable.put("qt_num", true);
			if (isYou(queryLeftNewDTO.getString("rw_num")))
				hashtable.put("rw_num", true);
			if (isYou(queryLeftNewDTO.getString("rz_num")))
				hashtable.put("rz_num", true);
			if (isYou(queryLeftNewDTO.getString("tz_num")))
				hashtable.put("tz_num", true);
			if (isYou(queryLeftNewDTO.getString("wz_num")))
				hashtable.put("wz_num", true);
			if (isYou(queryLeftNewDTO.getString("yb_num")))
				hashtable.put("yb_num", true);
			if (isYou(queryLeftNewDTO.getString("yw_num")))
				hashtable.put("yw_num", true);
			if (isYou(queryLeftNewDTO.getString("yz_num")))
				hashtable.put("yz_num", true);
			if (isYou(queryLeftNewDTO.getString("ze_num")))
				hashtable.put("ze_num", true);
			if (isYou(queryLeftNewDTO.getString("zy_num")))
				hashtable.put("zy_num", true);
			if (isYou(queryLeftNewDTO.getString("swz_num")))
				hashtable.put("swz_num", true);
		}
		return hashtable;
	}

	public Task initTaskExecute() {
		InitTaskExecute executeable = new InitTaskExecute(home);
		final Task task = new BaseTask(executeable);
		return task;
	}

	public static boolean isYou(String string) {
		if (string.equals("有") || string.matches("\\d+")) {
			return true;
		}
		return false;
	}

}
