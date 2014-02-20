/**
 * @(#) HttpBusiness.java Created on 2014-1-2
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.gohome.business;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.braver.gohome.GoHome;
import com.braver.gohome.Utils;

/**
 * The class <code>HttpBusiness</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class HttpBusiness implements Business {

	Logger logger = Logger.getLogger(HttpBusiness.class);

	private GoHome home;

	/**
	 * @param home
	 */
	public HttpBusiness(GoHome home) {
		super();
		this.home = home;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.gohome.business.Business#httpSetting(org.apache.http.client
	 * .methods.HttpRequestBase)
	 */
	@Override
	public void httpSetting(HttpRequestBase base) {
		if (null != base) {
			Builder builder = RequestConfig.custom();
			if (home.getTimeout() != 0) {
				int timeout = home.getTimeout();
				builder.setConnectTimeout(timeout);
				builder.setSocketTimeout(timeout);
			}

			if (home.getProxy() != null) {

				final String proxys[] = home.getProxy().split(":");

				builder.setProxy(new HttpHost(proxys[0], Integer.valueOf(proxys[1])));
			}
			base.setConfig(builder.build());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.business.Business#https()
	 */
	@Override
	public boolean https() {
		if (null != home.getModel() && home.getModel().equalsIgnoreCase("https")) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.business.Business#getSite()
	 */
	@Override
	public String getSite() {
		return home.getSite();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.business.Business#ajax200(net.sf.json.JSONObject)
	 */
	@Override
	public boolean ajax200(JSONObject object) {
		if (null != object && object.getString("httpstatus").equals("200")) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.business.Business#ajaxGet(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public JSONObject ajaxGet(String url, String referer) {
		HttpClient httpClient = getHttpClient();
		HttpGet httpGet = new HttpGet(url);

		Utils.stringLineToHeader(httpGet, "Accept:*/*");
		Utils.stringLineToHeader(httpGet, "Accept-Encoding:gzip,deflate,sdch");
		Utils.stringLineToHeader(httpGet, "Accept-Language:zh-CN,zh;q=0.8,en;q=0.6");
		Utils.stringLineToHeader(httpGet, "Cache-Control:no-cache");
		Utils.stringLineToHeader(httpGet,
				"Content-Type:application/x-www-form-urlencoded; charset=UTF-8");
		Utils.stringLineToHeader(httpGet, "If-Modified-Since:0");
		if (null != referer) {
			httpGet.addHeader("Referer", referer);
		}
		httpGet.addHeader("User-Agent", User_Agent);
		Utils.stringLineToHeader(httpGet, "X-Requested-With:XMLHttpRequest");

		Utils.addCookie(httpGet, home.getCookie());
		httpSetting(httpGet);
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK
					&& httpResponse.getEntity().getContentType().getValue()
							.contains("application/json")) {
				final String string = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
				logger.debug(String.format("ajaxGet url:%1$s string:%2$s", url, string));
				JSONObject object = JSONObject.fromObject(string);
				return object;
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.business.Business#ajaxPost(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public JSONObject ajaxPost(String url, String origin, String referer, String data) {
		HttpClient httpClient = getHttpClient();
		HttpPost httpPost = new HttpPost(url);

		Utils.stringLineToHeader(httpPost, "Accept:*/*");
		Utils.stringLineToHeader(httpPost, "Accept-Encoding:gzip,deflate,sdch");
		Utils.stringLineToHeader(httpPost, "Accept-Language:zh-CN,zh;q=0.8,en;q=0.6");
		Utils.stringLineToHeader(httpPost,
				"Content-Type:application/x-www-form-urlencoded; charset=UTF-8");
		if (null != origin)
			httpPost.addHeader("Origin", origin);
		if (null != referer)
			httpPost.addHeader("Referer", referer);

		httpPost.addHeader("User-Agent", User_Agent);
		Utils.stringLineToHeader(httpPost, "X-Requested-With:XMLHttpRequest");

		Utils.addCookie(httpPost, home.getCookie());

		httpSetting(httpPost);
		try {
			StringEntity reqEntity = null;
			if (null != data) {
				reqEntity = new StringEntity(data,"UTF-8");
			} else {
				reqEntity = new StringEntity(String.format("_json_att="),"UTF-8");
			}
			httpPost.setEntity(reqEntity);

			HttpResponse httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK
					&& httpResponse.getEntity().getContentType().getValue()
							.contains("application/json")) {
				final String string = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
				logger.debug(String.format("ajaxPost url:%1$s string:%2$s", url, string));
				JSONObject object = JSONObject.fromObject(string);
				return object;
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.business.Business#getHttpClient()
	 */
	@Override
	public HttpClient getHttpClient() {
		return HttpClients.createDefault();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.business.Business#otnLoginInit()
	 */
	@Override
	public String otnLoginInit() {
		HttpClient httpClient = getHttpClient();
		HttpGet httpGet = new HttpGet(getSite() + "/otn/login/init");
		httpGet.addHeader("User-Agent", User_Agent);
		httpGet.addHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
		httpSetting(httpGet);
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			Utils.setCookie(httpResponse, home.getCookie());
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

				final String string = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
				logger.debug(String.format("otnLoginInit string:%1$s", string));
				return string;
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.business.Business#getPassCode(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public String getPassCode(String html, String imaId) {
		if (null != html) {
			Parser parser = Parser.createParser(html, "UTF-8");
			NodeFilter filter = new TagNameFilter("img");
			try {
				NodeList nodeList = parser.extractAllNodesThatMatch(filter);
				for (int i = 0; i < nodeList.size(); i++) {
					final ImageTag imageTag = (ImageTag) nodeList.elementAt(i);
					if (imageTag.getAttribute("id") != null
							&& imageTag.getAttribute("id").equals("img_rand_code")) {
						return imageTag.getImageURL();
					}
				}
			} catch (ParserException e) {
				logger.error("", e);
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.gohome.business.Business#downloadPassCode(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean downloadPassCode(String url, String file) {
		HttpClient httpClient = getHttpClient();
		HttpGet httpGet = new HttpGet(getSite() + url);// HTTP
		httpGet.addHeader("User-Agent", User_Agent); // Get请求(POST雷同)
		httpGet.addHeader("Accept", "image/webp,*/*;q=0.8"); // Get请求(POST雷同)
		httpGet.addHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
		httpGet.addHeader("Accept-Encoding", "gzip,deflate,sdch");
		// httpGet.addHeader("Referer", " http://kyfw.12306.cn/otn/login/init");
		Utils.addCookie(httpGet, home.getCookie());
		httpSetting(httpGet);
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK
					&& httpResponse.getEntity().getContentType().getValue().contains("image/jpeg")) {
				final FileOutputStream out = new FileOutputStream(file);
				logger.debug("passimage:" + file);
				final byte[] bytes = EntityUtils.toByteArray(httpResponse.getEntity());
				out.write(bytes);
				out.flush();
				out.close();
				return true;
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.gohome.business.Business#checkLoginPassCode(java.lang.String)
	 */
	@Override
	public boolean checkLoginPassCode(String passcode) {
		JSONObject object = ajaxPost(getSite() + "/otn/passcodeNew/checkRandCodeAnsyn", getSite(),
				getSite() + "/otn/login/init", "randCode=" + passcode + "&rand=sjrand");
		if (null != object && object.getString("data") != null) {
			final String data = object.getString("data");
			if (data != null && data.equalsIgnoreCase("y")) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.gohome.business.Business#loginAysnSuggest(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public String loginAysnSuggest(String username, String password, String passcode) {
		String postData = String.format(
				"loginUserDTO.user_name=%1$s&userDTO.password=%2$s&randCode=%3$s", username,
				password, passcode);

		JSONObject object = ajaxPost(getSite() + "/otn/login/loginAysnSuggest", getSite(),
				getSite() + "/otn/login/init", postData);

		if (null != object && object.containsKey("data")) {
			final JSONObject data = object.getJSONObject("data");
			if (data != null && data.containsKey("loginCheck")
					&& null != data.getString("loginCheck")) {
				if (data.getString("loginCheck").equalsIgnoreCase("y")) {
					return null;
				}
			} else {
				return object.getString("messages");
			}

		} else if (null != object && object.containsKey("url")) {
			return object.getString("messages");
		}
		return "loginAysnSuggest fail";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.business.Business#otnLoginUserLogin()
	 */
	@Override
	public String otnLoginUserLogin() {
		HttpClient httpClient = getHttpClient();
		HttpPost httpPost = new HttpPost(getSite() + "/otn/login/userLogin");

		Utils.stringLineToHeader(httpPost,
				"Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		Utils.stringLineToHeader(httpPost, "Accept-Encoding:gzip,deflate,sdch");
		Utils.stringLineToHeader(httpPost, "Accept-Language:zh-CN,zh;q=0.8,en;q=0.6");
		Utils.stringLineToHeader(httpPost,
				"Content-Type:application/x-www-form-urlencoded; charset=UTF-8");
		httpPost.addHeader("Origin", getSite());
		httpPost.addHeader("Referer", getSite() + "/otn/login/init");
		httpPost.addHeader("User-Agent", User_Agent);

		Utils.addCookie(httpPost, home.getCookie());

		httpSetting(httpPost);
		try {
			StringEntity reqEntity = new StringEntity(String.format("_json_att="));
			httpPost.setEntity(reqEntity);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_MOVED_TEMPORARILY) {
				final String location = httpResponse.getFirstHeader("Location").getValue();
				return location;
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.business.Business#otnIndexInit(java.lang.String)
	 */
	@Override
	public String otnIndexInit(String url) {
		String tempUrl = url;
		if (null != url) {
			if (!https())
				tempUrl = url.replace("https", "http");

			HttpClient httpClient = getHttpClient();
			HttpGet httpGet = null;
			httpGet = new HttpGet(tempUrl);// HTTP

			Utils.stringLineToHeader(httpGet,
					"Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			Utils.stringLineToHeader(httpGet, "Accept-Encoding:gzip,deflate,sdch");
			Utils.stringLineToHeader(httpGet, "Accept-Language:zh-CN,zh;q=0.8,en;q=0.6");
			httpGet.addHeader("Referer", getSite() + "/otn/login/init");
			httpGet.addHeader("User-Agent", User_Agent); // Get请求(POST雷同)

			Utils.addCookie(httpGet, home.getCookie());

			httpSetting(httpGet);
			try {
				HttpResponse httpResponse = httpClient.execute(httpGet);
				Utils.setCookie(httpResponse, home.getCookie());
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					final String string = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
					logger.debug(String.format("otnIndexInit(String) string:%1$s", string));
					return string;
				}
			} catch (Exception e) {
				logger.error("", e);
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.gohome.business.Business#otnLeftTicketQuery(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public JSONArray otnLeftTicketQuery(String date, String start, String end, String purpose_codes) {
		final String url = String
				.format(getSite()
						+ "/otn/leftTicket/query?leftTicketDTO.train_date=%1$s&leftTicketDTO.from_station=%2$s&leftTicketDTO.to_station=%3$s&purpose_codes=%4$s",
						date, start, end, purpose_codes);

		JSONObject object = ajaxGet(url, getSite() + "/otn/leftTicket/init");
		if (null != object && object.getString("httpstatus").equals("200")) {
			return object.getJSONArray("data");
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.business.Business#otnLoginCheckUser()
	 */
	@Override
	public boolean otnLoginCheckUser() {
		// {"validateMessagesShowId":"_validatorMessage","status":true,"httpstatus":200,"data":{"flag":true},"messages":[],"validateMessages":{}}
		JSONObject object = ajaxPost(getSite() + "/otn/login/checkUser", getSite(), getSite()
				+ "/otn/leftTicket/init", null);
		if (ajax200(object)) {
			final JSONObject data = object.getJSONObject("data");
			if (null != data && data.getBoolean("flag")) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.gohome.business.Business#otnLeftTicketSubmitOrderRequest(java
	 * .lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public JSONObject otnLeftTicketSubmitOrderRequest(String secretStr, String train_date,
			String back_train_date, String tour_flag, String purpose_codes,
			String query_from_station_name, String query_to_station_name) {
		String postData = String
				.format("secretStr=%1$s&train_date=%2$s&back_train_date=%3$s&tour_flag=%4$s&purpose_codes=%5$s&query_from_station_name=%6$s&query_to_station_name=%7$s&undefined",
						secretStr, train_date, back_train_date, tour_flag, purpose_codes,
						query_from_station_name, query_to_station_name);
		JSONObject object = ajaxPost(getSite() + "/otn/leftTicket/submitOrderRequest", getSite(),
				getSite() + "/otn/leftTicket/init", postData);
		if (ajax200(object)) {
			// {"validateMessagesShowId":"_validatorMessage","status":true,"httpstatus":200,"messages":[],"validateMessages":{}}
			return object;
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.business.Business#otnConfirmPassengerInitDc()
	 */
	@Override
	public String otnConfirmPassengerInitDc() {
		HttpClient httpClient = getHttpClient();
		HttpPost httpPost = new HttpPost(getSite() + "/otn/confirmPassenger/initDc");

		Utils.stringLineToHeader(httpPost,
				"Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		Utils.stringLineToHeader(httpPost, "Accept-Encoding:gzip,deflate,sdch");
		Utils.stringLineToHeader(httpPost, "Accept-Language:zh-CN,zh;q=0.8,en;q=0.6");
		Utils.stringLineToHeader(httpPost, "Content-Type:application/x-www-form-urlencoded");
		httpPost.addHeader("Origin", getSite());
		httpPost.addHeader("Referer", getSite() + "/otn/leftTicket/init");
		httpPost.addHeader("User-Agent", User_Agent); // Get请求(POST雷同)

		Utils.addCookie(httpPost, home.getCookie());

		httpSetting(httpPost);
		try {
			StringEntity entity = new StringEntity("_json_att=");
			httpPost.setEntity(entity);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			Utils.setCookie(httpResponse, home.getCookie());
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				final String string = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
				logger.debug(String.format("otnConfirmPassengerInitDc string:%1$s", string));
				return string;
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.gohome.business.Business#downLoadUserInfo(java.lang.String)
	 */
	@Override
	public JSONObject downLoadUserInfo(String globalRepeatSubmitToken) {
		JSONObject object = ajaxPost(getSite() + "/otn/confirmPassenger/getPassengerDTOs",
				getSite(), getSite() + "/otn/confirmPassenger/initDc",
				String.format("_json_att=&REPEAT_SUBMIT_TOKEN=%1$s", globalRepeatSubmitToken));
		if (null != object && object.getString("data") != null) {
			final String data = object.getString("data");
			if (data != null) {
				return object.getJSONObject("data");
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.gohome.business.Business#getGlobalRepeatSubmitToken(java.lang
	 * .String)
	 */
	@Override
	public String getGlobalRepeatSubmitToken(String html) {
		if (null != html) {
			String line = null;
			BufferedReader reader = new BufferedReader(new StringReader(html));
			try {
				while (null != (line = reader.readLine())) {
					if (line.contains("globalRepeatSubmitToken")) {
						line = line.trim();
						line = line.replace("var", "");
						line = line.replace("\'", "");
						line = line.replace("=", "");
						line = line.replace(";", "");
						line = line.replace("globalRepeatSubmitToken", "");
						return line.trim();
					}
				}
			} catch (IOException e) {
				logger.error("", e);
			}

		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.gohome.business.Business#checkConfirmPassengerPassCode(java
	 * .lang.String)
	 */
	@Override
	public boolean checkConfirmPassengerPassCode(String passcode, String globalRepeatSubmitToken) {
		JSONObject object = ajaxPost(getSite() + "/otn/passcodeNew/checkRandCodeAnsyn", getSite(),
				getSite() + "/otn/confirmPassenger/initDc", String.format(
						"randCode=%1$s&rand=randp&_json_att=&REPEAT_SUBMIT_TOKEN=%2$s", passcode,
						globalRepeatSubmitToken));
		if (null != object && object.getString("data") != null) {
			final String data = object.getString("data");
			if (data != null && data.equalsIgnoreCase("y")) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.business.Business#checkOrderInfo(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public JSONObject checkOrderInfo(String cancel_flag, String bed_level_order_num,
			String passengerTicketStr, String oldPassengerStr, String tour_flag, String randCode,
			String globalRepeatSubmitToken) {
		// cancel_flag=2&bed_level_order_num=000000000000000000000000000000&passengerTicketStr=1%2C0%2C1%2C%E4%BB%A3%E6%99%93%E5%A8%81%2C1%2C421087199005294754%2C15814024023%2CN&oldPassengerStr=%E4%BB%A3%E6%99%93%E5%A8%81%2C1%2C421087199005294754%2C1_&tour_flag=dc&randCode=bwlb&_json_att=&REPEAT_SUBMIT_TOKEN=8928adc4f40a789274b8816d83cfbcbf
		final String postData = String
				.format("cancel_flag=%1$s&bed_level_order_num=%2$s&passengerTicketStr=%3$s&oldPassengerStr=%4$s&tour_flag=%5$s&randCode=%6$s&_json_att=&REPEAT_SUBMIT_TOKEN=%7$s",
						cancel_flag, bed_level_order_num, passengerTicketStr, oldPassengerStr,
						tour_flag, randCode, globalRepeatSubmitToken);

		JSONObject object = ajaxPost(getSite() + "/otn/confirmPassenger/checkOrderInfo", getSite(),
				getSite() + "/otn/confirmPassenger/initDc", postData);
		if (ajax200(object)) {
			// if(G.status){
			// if(G.data.submitStatus){
			// otsRedirect("post",ctx+"payOrder/init?random="+new
			// Date().getTime(),{})
			// }else{
			// c("出票失败!",false,"原因： "+G.data.errMsg+'<a
			// id="xg_close_win_id">点击修改</a>',false,"lose");
			// $("#xg_close_win_id").click(function(){
			// closeWin("transforNotice_id",true);
			// $("#i-ok").css("display","none")})}
			// }else{c("订票失败!",true,"很抱歉！请关闭窗口重新预定车票",true,"lose")}},error:function(G,I,H){c("订票失败!",true,"很抱歉！网络忙，请关闭窗口稍后再试。",true,"lose");return}})}

			// {"validateMessagesShowId":"_validatorMessage","status":true,"httpstatus":200,"data":{"submitStatus":true},"messages":[],"validateMessages":{}}
			// final JSONObject data = object.getJSONObject("data");
			// if (null != data && data.getBoolean("submitStatus")) {
			// return true;
			// }
			return object;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.braver.gohome.business.Business#getQueueCount(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public JSONObject getQueueCount(String train_date, String train_no, String stationTrainCode,
			String seatType, String fromStationTelecode, String toStationTelecode,
			String leftTicket, String purpose_codes, String globalRepeatSubmitToken) {
		String postData = String
				.format("train_date=%1$s&train_no=%2$s&stationTrainCode=%3$s&seatType=%4$s&fromStationTelecode=%5$s&toStationTelecode=%6$s&leftTicket=%7$s&purpose_codes=%8$s&_json_att=&REPEAT_SUBMIT_TOKEN=%9$s",
						train_date, train_no, stationTrainCode, seatType, fromStationTelecode,
						toStationTelecode, leftTicket, purpose_codes, globalRepeatSubmitToken);
		JSONObject object = ajaxPost(getSite() + "/otn/confirmPassenger/getQueueCount", getSite(),
				getSite() + "/otn/confirmPassenger/initDc", postData);
		if (ajax200(object)) {
			return object;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.gohome.business.Business#confirmSingleForQueue(java.lang.String
	 * , java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public JSONObject confirmSingleForQueue(String passengerTicketStr, String oldPassengerStr,
			String randCode, String purpose_codes, String key_check_isChange, String leftTicketStr,
			String train_location, String globalRepeatSubmitToken) {
		//
		String postData = String
				.format("passengerTicketStr=%1$s&oldPassengerStr=%2$s&randCode=%3$s&purpose_codes=%4$s&key_check_isChange=%5$s&leftTicketStr=%6$s&train_location=%7$s&_json_att=&REPEAT_SUBMIT_TOKEN=%8$s",
						passengerTicketStr, oldPassengerStr, randCode, purpose_codes,
						key_check_isChange, leftTicketStr, train_location, globalRepeatSubmitToken);
		JSONObject object = ajaxPost(getSite() + "/otn/confirmPassenger/confirmSingleForQueue",
				getSite(), getSite() + "/otn/confirmPassenger/initDc", postData);
		if (ajax200(object)) {
			return object;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.gohome.business.Business#resultOrderForDcQueue(java.lang.String
	 * , java.lang.String)confirmPassenger/resultOrderForDcQueue
	 */
	@Override
	public JSONObject resultOrderForDcQueue(String orderSequence_no, String globalRepeatSubmitToken) {
		//
		String postData = String.format("orderSequence_no=%1$s", orderSequence_no);
		JSONObject object = ajaxPost(getSite() + "/otn/confirmPassenger/resultOrderForDcQueue",
				getSite(), getSite() + "/otn/confirmPassenger/initDc", postData);
		if (ajax200(object)) {
			return object;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.braver.gohome.business.Business#queryOrderWaitTime(java.lang.String)
	 */
	@Override
	public JSONObject queryOrderWaitTime(String time, String tourFlag,
			String globalRepeatSubmitToken) {
		String url = String
				.format("%1$s/otn/confirmPassenger/queryOrderWaitTime?random=%2$s&tourFlag=%3$s&_json_att=&REPEAT_SUBMIT_TOKEN=%4$s",
						getSite(), time, tourFlag, globalRepeatSubmitToken);
		JSONObject object = ajaxGet(url, getSite() + "/otn/confirmPassenger/initDc");
		if (ajax200(object)) {
			return object;
		}
		return null;
	}

}
