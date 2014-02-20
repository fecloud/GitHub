/**
 * @(#) Business.java Created on 2014-1-1
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.braver.gohome.business;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;

import com.braver.gohome.exception.NetworkException;

/**
 * The class <code>Business</code>
 * 
 * @author braver
 * @version 1.0
 */
public interface Business {

	public static final String User_Agent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.69 Safari/537.36";

	/**
	 * 设置http参数
	 * 
	 * @param base
	 */
	void httpSetting(HttpRequestBase base);

	/**
	 * 判断模式是否是https
	 * 
	 * @return
	 */
	boolean https();

	/**
	 * 取网址
	 * 
	 * @return
	 */
	String getSite();

	boolean ajax200(JSONObject object);

	public JSONObject ajaxGet(String url, String referer);

	public JSONObject ajaxPost(String url, String origin, String referer, String data);

	HttpClient getHttpClient();

	/**
	 * 加载登录页面
	 * 
	 * @return
	 */
	String otnLoginInit();

	/**
	 * 从页面中取验证码
	 * 
	 * @param html
	 * @param imaId
	 * @return
	 */
	String getPassCode(String html, String imaId);

	/**
	 * 下载验证码
	 * 
	 * @param url
	 * @param file
	 * @return
	 */
	boolean downloadPassCode(String url, String file);

	/**
	 * ajax验证登录验证码
	 * 
	 * @param passcode
	 * @return
	 */
	boolean checkLoginPassCode(String passcode);

	/**
	 * ajax登录验证密码用户等
	 * 
	 * @param username
	 * @param password
	 * @param passcode
	 * @return
	 */
	String loginAysnSuggest(String username, String password, String passcode);

	/**
	 * POSR登录表单,返回跳转地址
	 * 
	 * @return
	 */
	String otnLoginUserLogin();

	/**
	 * 初始化成功登录后主页面
	 * 
	 * @param url
	 * @return
	 */
	String otnIndexInit(String url);

	/**
	 * 查询火车信息
	 * 
	 * @param date
	 *            2014-10-10
	 * @param start
	 *            GZQ
	 * @param end
	 *            SIN
	 * @param purpose_codes
	 *            defalut
	 * @return
	 */
	JSONArray otnLeftTicketQuery(String date, String start, String end, String purpose_codes);

	/**
	 * 点击预定按钮时,检查用户登录
	 * 
	 * @return
	 */
	boolean otnLoginCheckUser();

	/**
	 * ajax提交预定车次
	 * 
	 * @param secretStr
	 * @param train_date
	 * @param back_train_date
	 * @param tour_flag
	 * @param purpose_codes
	 * @param query_from_station_name
	 * @param query_to_station_name
	 * @return // {"validateMessagesShowId":"_validatorMessage","status":true,
	 *         "httpstatus":200,"messages":[],"validateMessages":{}}
	 * @throws NetworkException
	 */
	JSONObject otnLeftTicketSubmitOrderRequest(String secretStr, String train_date,
			String back_train_date, String tour_flag, String purpose_codes,
			String query_from_station_name, String query_to_station_name);

	/**
	 * 初始化选择定票人等信息页面
	 * 
	 * @return
	 * @throws NetworkException
	 */
	String otnConfirmPassengerInitDc();

	/**
	 * 选择定票人等信息页面Token
	 * 
	 * @return
	 */
	String getGlobalRepeatSubmitToken(String html);

	/**
	 * ajax请求帐号里的联系人
	 * 
	 * @param globalRepeatSubmitToken
	 * @return
	 * @throws NetworkException
	 */
	JSONObject downLoadUserInfo(String globalRepeatSubmitToken);

	/**
	 * ajax验证选择订票人等信息验证码
	 * 
	 * @param passcode
	 * @return
	 */
	boolean checkConfirmPassengerPassCode(String passcode, String globalRepeatSubmitToken);

	/**
	 * * 确认订单 cancel_flag=2&bed_level_order_num=000000000000000000000000000000&
	 * passengerTicketStr
	 * =1%2C0%2C1%2C%E4%BB%A3%E6%99%93%E5%A8%81%2C1%2C421087199005294754
	 * %2C15814024023%2CN&oldPassengerStr=%E4%BB%A3%E6%99%93%E5%A8%81%2C1%2
	 * C421087199005294754
	 * %2C1_&tour_flag=dc&randCode=bwlb&_json_att=&REPEAT_SUBMIT_TOKEN
	 * =8928adc4f40a789274b8816d83cfbcbf
	 * 
	 * @param cancel_flag
	 * @param bed_level_order_num
	 * @param passengerTicketStr
	 *            URLEncode路径编码
	 * @param oldPassengerStr
	 *            URLEncode 路径编码
	 * @param tour_flag
	 *            dc
	 * @param randCode
	 * @param globalRepeatSubmitToken
	 * @return
	 */

	// ////////////////分析结果///////////////////////////////////////
	// 1,0,1,代晓威,1,421087199005294754,,N
	// 席别,0,票种,中文名,证件类型,身份证号码,手机号,N应该是保存联系人资料的

	// 欧阳锋,1,421087199005215913,1_
	// 中文名,passenger_id_type_code,身份证号码,passenger_type
	//

	// cancel_flag:2 写死了
	// bed_level_order_num:000000000000000000000000000000 写死了
	// passengerTicketStr:1,0,1,欧阳锋,1,421087199005215913,18665934114,N
	// oldPassengerStr:欧阳锋,1,421087199005215913,1_
	// tour_flag:dc
	// randCode:xkq5
	// _json_att:
	// REPEAT_SUBMIT_TOKEN:6359974cd4bd138cfebf5b82c7a557f0
	JSONObject checkOrderInfo(String cancel_flag, String bed_level_order_num,
			String passengerTicketStr, String oldPassengerStr, String tour_flag, String randCode,
			String globalRepeatSubmitToken);

	/**
	 * 应该是查询等待
	 * 
	 * @return
	 */
	// train_date:Fri Jan 03 2014 00:00:00 GMT+0800 (中国标准时间)
	// train_no:650000K93203
	// stationTrainCode:K932
	// seatType:1
	// fromStationTelecode:GGQ
	// toStationTelecode:SIN
	// leftTicket:1014853265404735000010148500003026350000
	// purpose_codes:00
	// _json_att:
	// REPEAT_SUBMIT_TOKEN:8928adc4f40a789274b8816d83cfbcbf

	// {"validateMessagesShowId":"_validatorMessage","status":true,"httpstatus":200,"data":{"count":"40","ticket":"1014853265404735000010148500003026350000","op_2":"false","countT":"0","op_1":"true"},"messages":[],"validateMessages":{}}
	// ticket里面有还有多少个座位
	JSONObject getQueueCount(String train_date, String train_no, String stationTrainCode,
			String seatType, String fromStationTelecode, String toStationTelecode,
			String leftTicket, String purpose_codes, String globalRepeatSubmitToken);

	// passengerTicketStr:1,0,1,代晓威,1,421087199005294754,15814024023,N
	// oldPassengerStr:代晓威,1,421087199005294754,1_
	// randCode:bwlb
	// purpose_codes:00
	// key_check_isChange:96945DF6F3D7C0CABC15AA6347CB2D962939903E19C881368BFA22A7
	// leftTicketStr:1014853265404735000010148500003026350000
	// train_location:Q6
	// _json_att:
	// REPEAT_SUBMIT_TOKEN:8928adc4f40a789274b8816d83cfbcbf

	// {"validateMessagesShowId":"_validatorMessage","url":"login/init","status":true,"httpstatus":200,"messages":["用户未登录"],"validateMessages":{}}
	/**
	 * 用户确认
	 * 
	 * @param passengerTicketStr
	 * @param oldPassengerStr
	 * @param randCode
	 * @param key_check_isChange
	 * @param leftTicketStr
	 * @param train_location
	 * @param globalRepeatSubmitToken
	 * @return
	 */
	JSONObject confirmSingleForQueue(String passengerTicketStr, String oldPassengerStr,
			String randCode, String purpose_codes, String key_check_isChange, String leftTicketStr,
			String train_location, String globalRepeatSubmitToken);

	
	/**
	 * 查询等待的 tourFlag dc
	 * @return
	 */
	JSONObject queryOrderWaitTime(String time,String tourFlag, String globalRepeatSubmitToken);
	
	/**
	 * 准备去支付的
	 * 
	 * @param action_url
	 * @param orderSequence_no
	 * @returnconfirmPassenger/resultOrderForDcQueue
	 */
	JSONObject resultOrderForDcQueue(String orderSequence_no, String globalRepeatSubmitToken);
}
