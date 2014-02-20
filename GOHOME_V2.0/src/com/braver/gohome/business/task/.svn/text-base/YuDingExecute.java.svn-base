/**
 * @(#) YuDingExecute.java Created on 2014-1-2
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.gohome.business.task;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.ScriptTag;
import org.htmlparser.util.NodeList;

import com.braver.gohome.GoHome;
import com.braver.gohome.Message;
import com.braver.gohome.Utils;
import com.braver.gohome.ZuoWei;
import com.braver.gohome.business.Business;
import com.braver.gohome.business.HttpBusiness;
import com.braver.gohome.business.HttpsBusiness;
import com.braver.gohome.concat.HuoChePiaoContcat;
import com.braver.gohome.exception.BusinessException;
import com.braver.gohome.task.BaseTask;
import com.braver.gohome.task.Task;
import com.braver.gohome.task.TaskStatus;
import com.braver.gohome.task.executeable.TaskExecuteable;

/**
 * The class <code>YuDingExecute</code>
 * 
 * @author braver
 * @version 1.0
 */
public class YuDingExecute implements TaskExecuteable {

	Logger logger = Logger.getLogger(YuDingExecute.class);

	protected JSONObject yuding;

	protected GoHome home;

	protected Business business;

	protected String html;

	/**
	 * 帐号用户信息json
	 */
	protected JSONObject concat;

	/**
	 * 火车票购买人
	 */
	protected HuoChePiaoContcat ren;

	/**
	 * 正验证过的验证码
	 */
	protected String passCode;

	/**
	 * 是否进入到了填写验证码的流程
	 */
	protected boolean intopassCode;

	/**
	 * 下单成功进入等待
	 */
	protected boolean intoWaitime;

	/**
	 * 填写验证码错误数,也就是刷新数
	 */
	protected int inputPassCodeError;

	/**
	 * 确认订单出错,进入重新查询
	 */
	protected String redirect;

	protected Properties pageInfo;

	/**
	 * 跟根据用户要订的位分析出用户下拉框值
	 */
	protected String xiebie;

	/**
	 * 座位下拉选择
	 */
	protected Hashtable<String, String> ticket_seat_codeMap = new Hashtable<String, String>();

	boolean todo = true;
	
	private Task task;

	/**
	 * @param yuding
	 * @param home
	 * @param business
	 */
	public YuDingExecute(JSONObject yuding, GoHome home) {
		super();
		this.yuding = yuding;
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

			home.getGoHomeIO().writeln("进入填写车票信息页面========================");
			while (todo) {
				try {
					if (!intopassCode) {
						if (task.getTaskStatus() == TaskStatus.DOING) {
							otnConfirmPassengerInitDc();
							analysisPage();
							analysisTicket_seat_codeMap();
						} else {
							todo = false;
						}

						if (task.getTaskStatus() == TaskStatus.DOING) {
							downLoadUserInfo();

						} else {
							todo = false;
						}
					}

					if (!intoWaitime) {

						// 没下单之前
						// 先填写验证码

						if (task.getTaskStatus() == TaskStatus.DOING) {
							intopassCode = true;
							inputPassCodeError++;
							validatePassCode();
							if (inputPassCodeError == 3) { 
								home.getGoHomeIO().writeErrorLn("验证码3次错误,进入重新查车次");
								home.getService().addTask(getQueryTask());
								todo = false;
								break;
							}
						} else {
							todo = false;
						}

						if (task.getTaskStatus() == TaskStatus.DOING) {

							// 进入
							analysXieOption();
							checkOrderInfo();

						} else {
							todo = false;
						}

						if (task.getTaskStatus() == TaskStatus.DOING) {
							getQueueCount();
						} else {
							todo = false;
						}
						if (task.getTaskStatus() == TaskStatus.DOING) {
							confirmSingleForQueue();
							intoWaitime = true;
						} else {
							todo = false;
						}
					}
					if (intoWaitime) {
						if (task.getTaskStatus() == TaskStatus.DOING) {
							queryOrderWaitTime();
						} else {
							todo = false;
							break;
						}
					}

				} catch (Exception e) {
					logger.error("", e);
					home.getGoHomeIO().writeErrorLn("出现错误暂停2s");
					Utils.sleep(1);
				}

			}
			home.getGoHomeIO().writeln("填写车票信息成功完成");
		}

	}

	/**
	 * 初始化选择订票人等信息页面
	 * 
	 * @throws BusinessException
	 */
	protected void otnConfirmPassengerInitDc() throws BusinessException {
		home.getGoHomeIO().writeln("初始化选择订票人等信息页面...");
		int count = 3;
		boolean sucess = false;
		while (count > 0) {
			String temp = business.otnConfirmPassengerInitDc();
			if (null != temp) {
				html = temp;
				sucess = true;
				home.getGoHomeIO().writeln("初始化选择订票人等信息页面成功");
				break;
			}
			count--;
		}
		if (!sucess) {
			throw new BusinessException("初始化选择订票人等信息页面错误");
		}
	}

	/**
	 * 下载验证码
	 */
	protected void downLoginPassCode() throws BusinessException {
		if (null != html && business.getPassCode(html, "img_rand_code") != null) {
			String passcodeUrl = business.getPassCode(html, "img_rand_code");
			int count = 3;
			boolean sucess = false;
			while (count > 0) {
				home.getGoHomeIO().writeln("开始下载填写车票信息验证码...");
				passcodeUrl = passcodeUrl.replace("amp;", ""); // 去除去路径中的错误/otn/passcodeNew/getPassCodeNew?module=login&amp;rand=sjrand"
				if (business.downloadPassCode(passcodeUrl, home.getPasscodeimage())) {
					home.getGoHomeIO().writeMessage(
							new Message(Message.TYPE_PASS_CODE, home.getPasscodeimage()));
					sucess = true;
					break;
				}
				count--;
			}
			if (sucess) {
				home.getGoHomeIO().writeln("下载填写车票信息验证码成功");
			} else {
				throw new BusinessException("下载填写车票信息验证码错误");
			}
		} else {
			throw new BusinessException("加载填写车票信息页面错误,无法取得填写车票信息验证码下载路径");
		}
	}

	/**
	 * Ajax请求帐号里的联系人
	 * 
	 * @throws BusinessException
	 */
	protected void downLoadUserInfo() throws BusinessException {
		home.getGoHomeIO().writeln("Ajax请求帐号里的用户信息...");
		int count = 3;
		boolean sucess = false;
		while (count > 0) {
			JSONObject temp = business.downLoadUserInfo(pageInfo
					.getProperty("globalRepeatSubmitToken"));
			if (null != temp) {

				// 成功后处理Ajax请求帐号里的联系人
				concat = temp;
				if (!temp.getBoolean("isExist")) {
					home.getGoHomeIO().writeErrorLn("您的帐号里没有用户信息");
					todo = false;
					home.stopGOHome();
				}

				JSONArray jsonArray = temp.getJSONArray("normal_passengers");
				home.getGoHomeIO().writeln("转换用户信息...");

				List<HuoChePiaoContcat> chePiaoContcats = new ArrayList<HuoChePiaoContcat>();
				for (int i = 0; i < jsonArray.size(); i++) {
					chePiaoContcats.add((HuoChePiaoContcat) JSONObject.toBean(
							jsonArray.getJSONObject(i), HuoChePiaoContcat.class));
				}

				home.getGoHomeIO().writeln("您的帐号中有" + chePiaoContcats.size() + "用户信息");

				home.getGoHomeIO().writeln("匹配火车票购买人....");
				for (HuoChePiaoContcat c : chePiaoContcats) {
					if (c.getPassenger_name().contains(home.getPiaoInfo().getRen())) {
						home.getGoHomeIO().writeErrorLn("匹配到火车票购买人：" + c.getPassenger_name());
						this.ren = c;
					}
				}

				if (null == this.ren) {
					home.getGoHomeIO().writeErrorLn("没有匹配到火车票购买人：" + home.getPiaoInfo().getRen());
					todo = false;
					home.stopGOHome();
				}

				sucess = true;
				home.getGoHomeIO().writeln("Ajax请求帐号里的用户信息成功");
				break;
			}
			count--;
		}
		if (!sucess) {
			throw new BusinessException("Ajax请求帐号里的联系人错误");
		}
	}

	/**
	 * 输入 验证码
	 * 
	 * @throws BusinessException
	 */
	protected void validatePassCode() throws BusinessException {
		int count = 4;
		boolean sucess = false;
		while (count > 0) {
			downLoginPassCode();
			home.getGoHomeIO().writeErrorLn("请输入填写车票信息验证码:");
			home.getGoHomeIO().writeMessage(new Message(Message.TYPE_INPUT_PASS_CODE, "2"));
			final String inputPassCode = home.getGoHomeIO().readLine();
			if (null != inputPassCode && !inputPassCode.trim().equals("")) {
				if (business.checkConfirmPassengerPassCode(inputPassCode,
						pageInfo.getProperty("globalRepeatSubmitToken"))) {
					this.passCode = inputPassCode;
					sucess = true;
					break;
				}
			}
			count--;
		}
		if (sucess) {
			home.getGoHomeIO().writeln("填写车票信息验证码验证成功");
		} else {
			throw new BusinessException("填写车票信息验证码验证错误");
		}
	}

	/**
	 * 检查订单
	 * 
	 * @throws BusinessException
	 */
	protected void checkOrderInfo() throws BusinessException {
		home.getGoHomeIO().writeln("检查订单...");
		int count = 3;
		boolean sucess = false;
		while (count > 0) {

			// =================构造请求参数====================
			try {
				String cancel_flag = "2";
				String bed_level_order_num = "000000000000000000000000000000";
				String passengerTicketStr = String.format("%1$s,0,1,%2$s,1,%3$s,%4$s,N", xiebie,
						ren.getPassenger_name(), ren.getPassenger_id_no(), ren.getMobile_no());
				passengerTicketStr = URLEncoder.encode(passengerTicketStr, "UTF-8");
				String oldPassengerStr = String.format("%1$s,%2$s,%3$s,%4$s_",
						ren.getPassenger_name(), ren.getPassenger_id_type_code(),
						ren.getPassenger_id_no(), ren.getPassenger_type());
				oldPassengerStr = URLEncoder.encode(oldPassengerStr, "UTF-8");
				//
				JSONObject object = business.checkOrderInfo(cancel_flag, bed_level_order_num,
						passengerTicketStr, oldPassengerStr, "dc", passCode,
						pageInfo.getProperty("globalRepeatSubmitToken"));

				// 处理结果
				if (null != object && object.getJSONObject("data") != null
						&& object.getJSONObject("data").getBoolean("submitStatus")) {

					sucess = true;
					home.getGoHomeIO().writeln("检查订单成功");
					break;
				} else if (null != object && object.getJSONObject("data") != null) {
					home.getGoHomeIO().writeErrorLn(
							"检查订单失败原因：" + object.getJSONObject("data").getString("errMsg"));
					home.getGoHomeIO().writeln("重新进入查询火车票页面");

					home.getService().addTask(getQueryTask());
					todo = false;
					break;
				}
				count--;
			} catch (Exception e) {
				logger.error("", e);
				throw new BusinessException("检查订单错误");
			}
		}
		if (!sucess) {
			throw new BusinessException("检查订单错误");
		}
	}

	/**
	 * 查询等待用
	 * 
	 * @throws BusinessException
	 */
	protected void getQueueCount() throws BusinessException {
		home.getGoHomeIO().writeln("开始检查排队信息... ");
		int count = 3;
		boolean sucess = false;
		while (count > 0) {

			// =================构造请求参数====================

			String train_date = train_date();
			String train_no = train_no();
			String stationTrainCode = stationTrainCode();
			String seatType = xiebie;
			String fromStationTelecode = fromStationTelecode();
			String toStationTelecode = toStationTelecode();
			String leftTicket = leftTicket();
			String purpose_codes = purpose_codes();
			JSONObject queue = business.getQueueCount(train_date, train_no, stationTrainCode,
					seatType, fromStationTelecode, toStationTelecode, leftTicket, purpose_codes,
					pageInfo.getProperty("globalRepeatSubmitToken"));

			if (null != queue && queue.containsKey("data")) {

				final JSONObject data = queue.getJSONObject("data");

				// 成功查到排除信息
				if (data.containsKey("isRelogin")
						&& data.getString("isRelogin").equalsIgnoreCase("Y")) {
					// 用户要重新登录
					this.todo = false;
					task.setTaskStatus(TaskStatus.CANCELED);
					home.getService().addTask(getLoginTask());
					return;
				}

				if (data.containsKey("op_2") && data.getBoolean("op_2")) {
					// 目前排队人数已经超过余票张数，请您选择其他席别或车次
					home.getGoHomeIO().writeErrorLn("目前排队人数已经超过余票张数，请您选择其他席别或车次");
					task.setTaskStatus(TaskStatus.CANCELED);
					home.getService().addTask(getQueryTask());
					this.todo = false;

					return;
				} else if (data.containsKey("countT") && data.getInt("countT") > 0) {
					home.getGoHomeIO().writeErrorLn("目前排队人数" + data.getInt("countT") + "人");
				} else {
					// 准备最后一次提单
					home.getGoHomeIO().writeln("检查排队信息成功,准备提交 ");
					sucess = true;
					break;
				}

			}

			count--;
		}
		if (!sucess) {
			throw new BusinessException("检查订单错误");
		}
	}

	/**
	 * 准备最后一次提单
	 * 
	 * @throws BusinessException
	 */
	protected void confirmSingleForQueue() throws BusinessException {
		home.getGoHomeIO().writeln("开始下单... ");
		int count = 3;
		boolean sucess = false;
		while (count > 0) {

			// =================构造请求参数====================

			try {
				String passengerTicketStr = String.format("%1$s,0,1,%2$s,1,%3$s,%4$s,N", xiebie,
						ren.getPassenger_name(), ren.getPassenger_id_no(), ren.getMobile_no());
				passengerTicketStr = URLEncoder.encode(passengerTicketStr, "UTF-8");
				String oldPassengerStr = String.format("%1$s,%2$s,%3$s,%4$s_",
						ren.getPassenger_name(), ren.getPassenger_id_type_code(),
						ren.getPassenger_id_no(), ren.getPassenger_type());
				oldPassengerStr = URLEncoder.encode(oldPassengerStr, "UTF-8");

				String purpose_codes = purpose_codes();
				String key_check_isChange = key_check_isChange();
				String train_location = train_location();
				JSONObject confirmSingleForQueue = business.confirmSingleForQueue(
						passengerTicketStr, oldPassengerStr, passCode, purpose_codes,
						key_check_isChange, leftTicket(), train_location,
						pageInfo.getProperty("globalRepeatSubmitToken"));
				if (null != confirmSingleForQueue && confirmSingleForQueue.containsKey("status")
						&& !confirmSingleForQueue.getBoolean("status")) {
					home.getGoHomeIO().writeErrorLn("订票失败!很抱歉！请重新预定车票");
					todo = false;
					home.getService().addTask(getLoginTask());
					break;
				}
				if (null != confirmSingleForQueue && confirmSingleForQueue.containsKey("data")) {
					// 成功返回
					JSONObject data = confirmSingleForQueue.getJSONObject("data");
					if (data.containsKey("submitStatus") && !data.getBoolean("submitStatus")) {
						// 出票失败
						home.getGoHomeIO().writeErrorLn(
								"出票失败 原因：" + data.getString("errMsg") + "进入重新查询");
						home.getService().addTask(getQueryTask());
						todo = false;
						sucess = true;
						return;
					} else {
						// 成功买到票,开始
						home.getGoHomeIO().writeErrorLn(
								"================恭喜成功买到票,请登录12306.cn支付================");
						intoWaitime = true;
						sucess = true;
						break;
					}

				}

			} catch (Exception e) {
				logger.error("", e);
				throw new BusinessException("下单错误");
			}

			count--;
		}
		if (!sucess) {
			throw new BusinessException("下单错误");
		}
	}

	/**
	 * 等待支付
	 */
	protected void queryOrderWaitTime() {
		home.getGoHomeIO().writeln("最后提交订票等待中... ");
		// =================构造请求参数====================
		String globalRepeatSubmitToken = pageInfo.getProperty("globalRepeatSubmitToken");
		String tourFlag = "dc";
		JSONObject object = business.queryOrderWaitTime("" + new Date().getTime(), tourFlag,
				globalRepeatSubmitToken);

		if (null != object && object.containsKey("data")) {
			JSONObject data = object.getJSONObject("data");
			if (data.containsKey("queryOrderWaitTimeStatus")
					&& !data.getBoolean("queryOrderWaitTimeStatus")) {
				// 用户要重新登录
				home.getGoHomeIO().writeErrorLn("=================请您重新登录=======================");
				home.getService().addTask(getLoginTask());
				this.todo = false;
				return;
			} else if (data.containsKey("waitTime") && data.getLong("waitTime") > 0) {
				home.getGoHomeIO().writeErrorLn("请等待" + data.getLong("waitTime"));
			} else {
				home.getGoHomeIO().writeErrorLn("抢成功,请在http://kyfw.12306.cn去支付!!");
				this.todo = false;
				return;
			}
		}
	}

	/**
	 * @return
	 */
	private String train_date() {

		JSONObject orderRequestDTO = orderRequestDTO();
		if (null != orderRequestDTO && orderRequestDTO.containsKey("train_date")) {
			final JSONObject train_date = orderRequestDTO.getJSONObject("train_date");
			if (null != train_date && train_date.containsKey("time")) {
				final String d = Utils.jsDateToString(train_date.getLong("time"));
				try {
					return URLEncoder.encode(d, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					logger.error("", e);
				}
			}
		}
		return "";
	}

	/**
	 * 分析页面内容
	 * 
	 * @throws BusinessException
	 */
	private void analysisPage() throws BusinessException {
		home.getGoHomeIO().writeln("开始分析页面内容 ");
		try {

			Parser parser = Parser.createParser(html.toString(), "UTF-8");
			/*
			 * 分析js里的var,提交用会到
			 */
			NodeFilter filter = new TagNameFilter("script");
			StringBuffer stringBuffer = new StringBuffer();
			NodeList nodeList = parser.extractAllNodesThatMatch(filter);
			for (int i = 0; i < nodeList.size(); i++) {
				final ScriptTag scriptTag = (ScriptTag) nodeList.elementAt(i);
				if (null != scriptTag.getScriptCode()) {
					stringBuffer.append(scriptTag.getScriptCode().trim());
				}

			}

			Pattern pattern = Pattern.compile("(\\s*var\\s*\\w+\\s*={1}.+\\s*;)");
			Matcher matcher = pattern.matcher(stringBuffer);

			StringBuilder find = new StringBuilder();
			String line = "";
			while (matcher.find()) {
				for (int i = 0; i < matcher.groupCount(); i++) {
					line = matcher.group(i).trim();
					line = line.replaceFirst("var", "").replaceFirst(";", "");
					line = line.replaceFirst("={1}\\s*[\'\\[\"]", "=");
					line = line.replaceFirst("[\'\\]\"]{1}$", "");
					find.append(line).append("\r\n");
				}
				Properties properties = new Properties();
				properties.load(new StringReader(find.toString()));
				this.pageInfo = properties;
			}

			/**
			 * 根据下拉框
			 */

			// select

		} catch (Exception e) {
			home.getGoHomeIO().writeErrorLn("分析页面内容 出错");
			logger.error("", e);
			todo = false;
			home.getService().addTask(getLoginTask());

			throw new BusinessException("分析页面内容 出错");
		}
		home.getGoHomeIO().writeln("分析页面内容 结束");
	}

	/**
	 * 分析座位信息
	 */
	private void analysisTicket_seat_codeMap() {
		home.getGoHomeIO().writeln("分析座位信息 ");

		if (pageInfo.containsKey("ticket_seat_codeMap")) {
			JSONObject jsonObject = null;
			JSONObject setObject = null;
			jsonObject = JSONObject.fromObject(pageInfo.get("ticket_seat_codeMap"));
			if (null != jsonObject.keySet() && !jsonObject.keySet().isEmpty()) {
				JSONArray jsonArray = null;
				for (Object s : jsonObject.keySet()) {
					jsonArray = jsonObject.getJSONArray(s.toString());
					if (null != jsonArray && !jsonArray.isEmpty()) {
						for (int i = 0; i < jsonArray.size(); i++) {
							setObject = jsonArray.getJSONObject(i);
							if (null != setObject) {
								ticket_seat_codeMap.put(setObject.getString("value"),
										setObject.getString("id"));
							}
						}
					}
				}
			}

		} else {
			home.getGoHomeIO().writeErrorLn("分析座位信息错误");
		}

		home.getGoHomeIO().writeln("分析座位信息结束 ");
	}

	private boolean analysXieOption() {
		for (ZuoWei zuoWei : home.getCheCiOption().getZuowei()) {
			if (ticket_seat_codeMap.containsKey(zuoWei.getZh_name())) {
				xiebie = ticket_seat_codeMap.get(zuoWei.getZh_name());
				home.getGoHomeIO().writeErrorLn("将为您订" + zuoWei.getZh_name() + " (注意硬座也可能是无座哦!)");
				return true;
			}
		}

		home.getGoHomeIO().writeErrorLn("没有找到匹配您要求的座位,可能已没有了!");

		return false;
	}

	private String key_check_isChange() {
		if (null != pageInfo && pageInfo.containsKey("ticketInfoForPassengerForm")) {
			JSONObject ticketInfoForPassengerForm = JSONObject.fromObject(pageInfo
					.get("ticketInfoForPassengerForm"));
			if (null != ticketInfoForPassengerForm
					&& ticketInfoForPassengerForm.containsKey("key_check_isChange")) {
				return ticketInfoForPassengerForm.getString("key_check_isChange");
			}

		}
		return "";
	}

	private String train_location() {
		if (null != pageInfo && pageInfo.containsKey("ticketInfoForPassengerForm")) {
			JSONObject ticketInfoForPassengerForm = JSONObject.fromObject(pageInfo
					.get("ticketInfoForPassengerForm"));
			if (null != ticketInfoForPassengerForm
					&& ticketInfoForPassengerForm.containsKey("train_location")) {
				return ticketInfoForPassengerForm.getString("train_location");
			}

		}
		return "";
	}

	private String leftTicket() {
		if (null != pageInfo && pageInfo.containsKey("ticketInfoForPassengerForm")) {
			JSONObject ticketInfoForPassengerForm = JSONObject.fromObject(pageInfo
					.get("ticketInfoForPassengerForm"));
			if (null != ticketInfoForPassengerForm
					&& ticketInfoForPassengerForm.containsKey("leftTicketStr")) {
				return ticketInfoForPassengerForm.getString("leftTicketStr");
			}

		}
		return "";
	}

	private String train_no() {
		JSONObject orderRequestDTO = orderRequestDTO();
		if (null != orderRequestDTO && orderRequestDTO.containsKey("train_no")) {
			return orderRequestDTO.getString("train_no");
		}
		return "";
	}

	private String stationTrainCode() {
		JSONObject orderRequestDTO = orderRequestDTO();
		if (null != orderRequestDTO && orderRequestDTO.containsKey("station_train_code")) {
			return orderRequestDTO.getString("station_train_code");
		}
		return "";
	}

	private String purpose_codes() {
		if (null != pageInfo && pageInfo.containsKey("ticketInfoForPassengerForm")) {
			JSONObject ticketInfoForPassengerForm = JSONObject.fromObject(pageInfo
					.get("ticketInfoForPassengerForm"));
			if (null != ticketInfoForPassengerForm
					&& ticketInfoForPassengerForm.containsKey("purpose_codes")) {
				return ticketInfoForPassengerForm.getString("purpose_codes");
			}

		}
		return "";
	}

	private JSONObject orderRequestDTO() {
		if (null != pageInfo && pageInfo.containsKey("ticketInfoForPassengerForm")) {
			JSONObject ticketInfoForPassengerForm = JSONObject.fromObject(pageInfo
					.get("ticketInfoForPassengerForm"));
			if (null != ticketInfoForPassengerForm
					&& ticketInfoForPassengerForm.containsKey("orderRequestDTO")) {
				JSONObject orderRequestDTO = ticketInfoForPassengerForm
						.getJSONObject("orderRequestDTO");
				return orderRequestDTO;
			}

		}
		return null;
	}

	private String fromStationTelecode() {
		JSONObject orderRequestDTO = orderRequestDTO();
		if (null != orderRequestDTO && orderRequestDTO.containsKey("from_station_telecode")) {
			return orderRequestDTO.getString("from_station_telecode");
		}
		return "";
	}

	private String toStationTelecode() {
		JSONObject orderRequestDTO = orderRequestDTO();
		if (null != orderRequestDTO && orderRequestDTO.containsKey("to_station_telecode")) {
			return orderRequestDTO.getString("to_station_telecode");
		}

		return "";
	}

	/**
	 * 解析票信息还剩多少
	 * 
	 * @param str
	 * @return
	 */
	protected String[] getTicketCountDesc(String str) {
		String[] rt = {};
		// int seat_1 = -1;
		// int seat_2 = -1;
		// int i = 0;
		// while (i < mark.length) {
		// s = mark.substr(i, 10);
		// c_seat = s.substr(0, 1);
		// if (c_seat == seat) {
		// count = s.substr(6, 4);
		// while (count.length > 1 && count.substr(0, 1) == "0") {
		// count = count.substr(1, count.length);
		// }
		// count = parseInt(count);
		// if (count < 3000) {
		// seat_1 = count;
		// } else {
		// seat_2 = (count - 3000);
		// }
		// }
		// i = i + 10;
		// }
		//
		// if (seat_1 > -1) {
		// rt += seat_1;
		// }
		// if (seat_2 > -1) {
		// rt += "," + seat_2;
		// }
		return rt;
	}

	private Task getLoginTask() {
		final Task task = new BaseTask(new LoginTaskExecute(home));
		return task;
	}

	private Task getQueryTask() {
		final Task t = new BaseTask(new QueryHuoCheExecute(home));
		return t;
	}

}
