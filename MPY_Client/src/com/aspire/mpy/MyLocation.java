package com.aspire.mpy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyLocation extends Activity {
	TextView mTextView;
	Button mButton;
	TelephonyManager tm;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mylocation);

		mTextView = (TextView) findViewById(R.id.TextView01);
		mButton = (Button) findViewById(R.id.Button01);
		tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);

		mButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GsmCellLocation gcl = (GsmCellLocation) tm.getCellLocation();
				int cid = gcl.getCid();
				int lac = gcl.getLac();

				int mcc = Integer.valueOf(tm.getNetworkOperator().substring(0, 3));
				int mnc = Integer.valueOf(tm.getNetworkOperator().substring(3, 5));
				try {
					// 组装JSON查询字符串
					JSONObject holder = new JSONObject();
					holder.put("version", "1.1.0");
					holder.put("host", "maps.google.com");
					// holder.put("address_language", "zh_CN");
					holder.put("request_address", true);

					JSONArray array = new JSONArray();
					JSONObject data = new JSONObject();
					data.put("cell_id", cid); // 25070
					data.put("location_area_code", lac);// 4474
					data.put("mobile_country_code", mcc);// 460
					data.put("mobile_network_code", mnc);// 0
					array.put(data);
					holder.put("cell_towers", array);

					// 创建连接，发送请求并接受回应
					DefaultHttpClient client = new DefaultHttpClient();

					HttpPost post = new HttpPost("http://www.google.com/loc/json");

					StringEntity se = new StringEntity(holder.toString());

					post.setEntity(se);
					HttpResponse resp = client.execute(post);

					HttpEntity entity = resp.getEntity();

					BufferedReader br = new BufferedReader(new InputStreamReader(entity
							.getContent()));
					StringBuffer sb = new StringBuffer();
					String result = br.readLine();

					while (result != null) {

						sb.append(result);
						result = br.readLine();
					}

					mTextView.setText(sb.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		});
	}
}
