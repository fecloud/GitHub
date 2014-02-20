package com.aspire.mpy.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.aspire.mpy.bean.BindInfo;
import com.aspire.mpy.bean.CardID;
import com.aspire.mpy.bean.CardInfo;
import com.aspire.mpy.bean.TradeInfo;
import com.aspire.mpy.bean.UserInfo;
import com.aspire.mpy.message.request.BindNameCardReq;
import com.aspire.mpy.message.request.CreateNameCardReq;
import com.aspire.mpy.message.request.DownloadPhotoReq;
import com.aspire.mpy.message.request.GetNameCardListReq;
import com.aspire.mpy.message.request.GetNameCardReq;
import com.aspire.mpy.message.request.IRequestMsg;
import com.aspire.mpy.message.request.JsonRequestMsg;
import com.aspire.mpy.message.request.LoginNameCardReq;
import com.aspire.mpy.message.request.ModifyNameCardReq;
import com.aspire.mpy.message.request.TradeNameCardReq;
import com.aspire.mpy.message.request.UploadPhotoReq;
import com.aspire.mpy.message.response.BindNameCardResp;
import com.aspire.mpy.message.response.CreateNameCardResp;
import com.aspire.mpy.message.response.DownloadPhotoResp;
import com.aspire.mpy.message.response.GetNameCardListResp;
import com.aspire.mpy.message.response.GetNameCardResp;
import com.aspire.mpy.message.response.IResponseMsg;
import com.aspire.mpy.message.response.LoginNameCardResp;
import com.aspire.mpy.message.response.ModifyNameCardResp;
import com.aspire.mpy.message.response.TradeJsonResp;
import com.aspire.mpy.message.response.TradeNameCardResp;
import com.aspire.mpy.message.response.UploadPhotoResp;
import com.aspire.mpy.task.HttpNormalTask;
import com.aspire.mpy.task.TaskManager;

public class HttpUtil {

	/**
	 * 5.1 创建名片
	 * @param info
	 * @param callBack
	 */
	public static void createNameCard(CardInfo info , HttpPostCallBack callBack){
		IRequestMsg requestMsg = new CreateNameCardReq(info);
		IResponseMsg responseMsg = new CreateNameCardResp();
		HttpNormalTask task =  new HttpNormalTask(requestMsg, responseMsg, callBack);
		TaskManager.getInstance().addFgTask(task);
		callBack.excute();
	}
	/**
	 * 5.2 绑定名片
	 * @param cardID
	 * @param info
	 * @param callBack
	 */
	public static void bindNameCard(CardID cardID , BindInfo info , HttpPostCallBack callBack){
		IRequestMsg requestMsg = new BindNameCardReq(cardID,info);
		IResponseMsg responseMsg = new BindNameCardResp();
		HttpNormalTask task =  new HttpNormalTask(requestMsg, responseMsg, callBack);
		TaskManager.getInstance().addFgTask(task);
		callBack.excute();
	}
	
	/**
	 * 5.3 登录名片
	 * @param info
	 * @param callBack
	 */
	public static void loginNameCard(BindInfo info ,HttpPostCallBack callBack){
		IRequestMsg requestMsg = new LoginNameCardReq(info);
		IResponseMsg responseMsg = new LoginNameCardResp();
		HttpNormalTask task =  new HttpNormalTask(requestMsg, responseMsg, callBack);
		TaskManager.getInstance().addFgTask(task);
		callBack.excute();
	}
	
	/**
	 * 5.4 查询名片列表
	 * @param cardID
	 * @param callBack
	 */
	public static void getNameCardList(CardID cardID , HttpPostCallBack callBack){
		GetNameCardListReq req = new GetNameCardListReq();
		req.cardID = cardID;
		GetNameCardListResp resp = new GetNameCardListResp();
		HttpNormalTask task = new HttpNormalTask(req, resp, callBack);
		TaskManager.getInstance().addFgTask(task);
		callBack.excute();
	}
	
	/**
	 * 5.5 修改名片
	 * @param cardID
	 * @param cardInfo
	 * @param bindInfo
	 * @param callBack
	 */
	public static void modifyNameCard(UserInfo userInfo , BindInfo bindInfo , HttpPostCallBack callBack){
		ModifyNameCardReq req = new ModifyNameCardReq(userInfo , bindInfo);
		ModifyNameCardResp resp = new ModifyNameCardResp();
		HttpNormalTask task = new HttpNormalTask(req, resp, callBack);
		TaskManager.getInstance().addFgTask(task);
		callBack.excute();
	}
	
	/**
	 * 5.6 获取名片
	 */
	public static void getNameCard(CardID myCardID ,CardID cardID , HttpPostCallBack callBack){
		ArrayList<CardID> cardIDs = new ArrayList<CardID>(1);
		cardIDs.add(cardID);
		getNameCard(myCardID , cardIDs, callBack);
	}
	
	/**
	 * 5.6 获取名片
	 */
	public static void getNameCard(CardID myCardID ,List<CardID> cardIDs , HttpPostCallBack callBack){
		GetNameCardReq requestMsg = new GetNameCardReq(cardIDs.size(),myCardID ,cardIDs );
		IResponseMsg responseMsg = new GetNameCardResp();
		HttpNormalTask task =  new HttpNormalTask(requestMsg, responseMsg, callBack);
		TaskManager.getInstance().addFgTask(task);
		callBack.excute();
	}
	
	/**
	 * 5.7 交换名片
	 * @param cardID
	 * @param tradeInfo
	 * @param callBack
	 */
	public static void tradeNameCard(CardID cardID , TradeInfo tradeInfo ,  HttpPostCallBack callBack){
		TradeNameCardReq req = new TradeNameCardReq(cardID, tradeInfo);
		TradeNameCardResp resp = new TradeNameCardResp();
		HttpNormalTask task = new HttpNormalTask(req, resp, callBack);
		TaskManager.getInstance().addFgTask(task);
		callBack.excute();
	}
	
	/**
	 * 5.8 上传图像
	 */
	public static void uploadPhoto(String path , HttpPostCallBack callBack){
		UploadPhotoReq req = new UploadPhotoReq(path);
		UploadPhotoResp  resp = new UploadPhotoResp();
		HttpNormalTask task = new HttpNormalTask(req, resp, callBack);
		TaskManager.getInstance().addFgTask(task);
		//callBack.excute();
	}
	
	/**
	 * 5.9 下载图像
	 * @param cardID
	 * @param photoID
	 * @param callBack
	 */
	public static void downloadPhoto(Integer photoID, HttpPostCallBack callBack){
		DownloadPhotoReq req = new DownloadPhotoReq(photoID);
		DownloadPhotoResp resp = new DownloadPhotoResp();
		HttpNormalTask task = new HttpNormalTask(req, resp, callBack);
		TaskManager.getInstance().addFgTask(task);
		//callBack.excute();
	}
	
	public static void getTradeFromGsm(JSONObject object , HttpPostCallBack callBack){
		JsonRequestMsg requestMsg = new JsonRequestMsg();
		requestMsg.holder = object;
		TradeJsonResp responseMsg = new TradeJsonResp();
		HttpNormalTask task = new HttpNormalTask(requestMsg, responseMsg, callBack);
		TaskManager.getInstance().addFgTask(task);
		callBack.excute();
	}
}
