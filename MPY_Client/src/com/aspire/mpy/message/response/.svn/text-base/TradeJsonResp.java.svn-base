package com.aspire.mpy.message.response;

import org.json.JSONException;
import org.json.JSONObject;

import com.aspire.mpy.bean.TradeInfo;

public class TradeJsonResp extends JsonResponseMsg {

	public TradeInfo info;
	
	@Override
	public void parserJson(JSONObject object) throws JSONException {
		JSONObject location = object.getJSONObject("location");
		if(null != location){
			info = new TradeInfo();
			info.setType(TradeInfo.TradeType.OTHER);
			info.setXinfo(location.getString("latitude"));
			info.setYinfo(location.getString("longitude"));
		}
		
		super.parserJson(object);
	}

}
