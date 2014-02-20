package com.aspire.mpy.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;

import com.aspire.mpy.Const.TaskState;
import com.aspire.mpy.message.response.FileRespMsg;
import com.aspire.mpy.message.response.IResponseMsg;
import com.aspire.mpy.message.response.JsonResponseMsg;
import com.aspire.mpy.message.response.XmlResponseMsg;
import com.aspire.mpy.task.ITaskListener;

public abstract class HttpPostCallBack implements ITaskListener {
	
	public static final int RSPONSE_SUCESS = 0; //表示本次响应成功错误码为0
	
	public static final int FILE_RSPONSE_SUCESS = 200;
	
	private static final String TAG = "HttpPostCallBack";
	
	private Activity mActivity;
	
	public AlertDialog dialog;
	
	public int contentId = 0;
	
	public HttpPostCallBack(Activity mActivity) {
		super();
		this.mActivity = mActivity;
	}
	
	public HttpPostCallBack(Activity mActivity , int contentId) {
		super();
		this.mActivity = mActivity;
		this.contentId = contentId;
	}

	/**
	 * 任务开始前
	 * @return
	 */
	public  AlertDialog prepare(){
		 return ViewUtil.createLoadingDailg(mActivity ,contentId);
	}
	
	/**
	 * 任务完成后
	 */
	@Override
	public void notifyTask(IResponseMsg respMsg, int status) {
		Log.i(TAG, "notifyTask respMsg :" + respMsg + "status :" + status);
		XmlResponseMsg xmlRespMsg = null;
		FileRespMsg fileRespMsg = null;
		switch (status) {
		case TaskState.SUCCESS:
			if(respMsg instanceof XmlResponseMsg){
				xmlRespMsg = (XmlResponseMsg) respMsg;
				if(RSPONSE_SUCESS == xmlRespMsg.result){
					taskSucess(respMsg);
					release();
				}else{
					//弹出错误信息
					ViewUtil.showShortToast(mActivity, Tools.findALLError("" + xmlRespMsg.result));
					error();
				}
			}else if(respMsg instanceof FileRespMsg){
				fileRespMsg = (FileRespMsg) respMsg;
				if(FILE_RSPONSE_SUCESS == fileRespMsg.status){
					taskSucess(respMsg);
					release();
				}else{
					//弹出错误信息
					ViewUtil.showShortToast(mActivity, Tools.findALLError("" + fileRespMsg.status));
					error();
				}
			}else if(respMsg instanceof JsonResponseMsg){
				taskSucess(respMsg);
				release();
			}
			
			break;

		default:
			//弹出错误信息
			ViewUtil.showShortToast(mActivity, Tools.findALLError("" + status));
			error();
			break;
		}
		if(null != dialog){
			this.dialog.dismiss();
		}
	}
	
	/**
	 * 请求成功,响应无错时调用,调用者自己处理
	 * @param respMsg
	 */
	public void taskSucess(IResponseMsg respMsg){};
	

	public void release(){
		
	}
	
	public void excute(){
		dialog = prepare();
	}
	
	public void error(){}

}
