package com.aspire.mpy.contact;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ViewSwitcher.ViewFactory;

import com.aspire.mpy.LoginSignup;
import com.aspire.mpy.R;
import com.aspire.mpy.bean.CardID;
import com.aspire.mpy.bean.CardInfo;
import com.aspire.mpy.bean.TradeInfo;
import com.aspire.mpy.bean.UserInfo;
import com.aspire.mpy.config.Config;
import com.aspire.mpy.control.CoverFlow;
import com.aspire.mpy.message.response.DownloadPhotoResp;
import com.aspire.mpy.message.response.GetNameCardListResp;
import com.aspire.mpy.message.response.GetNameCardResp;
import com.aspire.mpy.message.response.IResponseMsg;
import com.aspire.mpy.message.response.TradeJsonResp;
import com.aspire.mpy.message.response.TradeNameCardResp;
import com.aspire.mpy.provider.ProviderUtil;
import com.aspire.mpy.util.HttpPostCallBack;
import com.aspire.mpy.util.HttpUtil;
import com.aspire.mpy.util.Tools;
import com.aspire.mpy.util.ViewUtil;

@SuppressWarnings("deprecation")
public class Contact extends Activity implements OnItemSelectedListener, ViewFactory,
		OnItemClickListener , SensorListener  {

	private static final String TAG = "Contact";
	
	//0activity关闭是是否杀进程,1,是否加载个人数据,2是否提示打开gps 3是否已加载交换名片数据 4,是否可以交换名片
	public boolean [] firsts = {true , true , true , true , true };
	
	private static List<Bitmap> adapterData = new ArrayList<Bitmap>();

	private int select = 0; // 哪一个tab被选中

	private TextView tabs[] = new TextView[2];

	private ViewGroup my_center, other_center;
	
	private CoverFlow cf; //交换名片滑动控件
	
	private LocationManager locationManager;
	private String provider;
	
	private SensorManager sensorManager;
	
	private long lastUpdate = -1;
	private float x, y, z;
	private float last_x, last_y, last_z;
	private static final int SHAKE_THRESHOLD = 800;
	
	private UserInfo userInfo;
	
	private void setTabOnClick() {
		tabs[0].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				select = 0;
				onselect();
			}
		});
		tabs[1].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
					select = 1;
					onselect();
			}
		});
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.contact, menu);
		return true;
	}

	/**
	 * 从网络上拉取个人的名片
	 */
	private void getUserInfo() {
		HttpUtil.getNameCard(Tools.get_mpy_id(this), Tools.get_mpy_id(this), new HttpPostCallBack(
				this, R.string.contact_getuser_loading) {

			@Override
			public void taskSucess(IResponseMsg respMsg) {
				super.taskSucess(respMsg);
				GetNameCardResp resp = (GetNameCardResp) respMsg;
				if (resp.number == 1) {
					userInfo = resp.infos.get(0);
					if (null != userInfo) {
						ProviderUtil.insertOrUpdateUserInfo(Contact.this, userInfo);
						Tools.saveOrUpdatempy_id(Contact.this, userInfo.getCardID()); //保存到xml
						setMy_CardInf(userInfo.getCardInfo());
					}
				}

			}

			@Override
			public void error() {
				firsts[1] = false;
				super.error();
			}

			@Override
			public void release() {
				firsts[1] = false;
				super.release();
				//拉取头像
				loadingUserInfoHead();
			}
			
			

		});
	}
	
	/**
	 * 加载用户头像
	 */
	private void loadingUserInfoHead(){
		if(null != userInfo.getCardInfo().getPhotoID() && 0 != userInfo.getCardInfo().getPhotoID()){
			HttpUtil.downloadPhoto(userInfo.getCardInfo().getPhotoID() , new HttpPostCallBack(this) {

				@Override
				public void taskSucess(IResponseMsg respMsg) {
					super.taskSucess(respMsg);
					DownloadPhotoResp msg = (DownloadPhotoResp) respMsg;
					ProviderUtil.saveOrUpdatePhoto(Contact.this, msg.photoID, msg.photo);//保存
					((ImageView)findViewById(R.id.head)).setImageBitmap(msg.photo);//显示
					((ImageView)findViewById(R.id.head)).setOnClickListener(null);
				}

				@Override
				public void error() {
					super.error();
					ViewUtil.showShortToast(Contact.this, R.string.contact_getuserhead_fail);
					((ImageView)findViewById(R.id.head)).setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							loadingUserInfoHead();
						}
					});
				}
				
				
			});
		}
	}
	
	public void loadingUserInfo(){
		if(firsts[1]){
			getUserInfo();
		}
	}

	/**
	 * 从网络上拉取交换的名片,只有cardID
	 */
	private void getTradeUserInfo() {
		HttpUtil.getNameCardList(Tools.get_mpy_id(this), new HttpPostCallBack(this,
				R.string.contact_tradeing_loading) {

			@Override
			public void taskSucess(IResponseMsg respMsg) {
				GetNameCardListResp resp = (GetNameCardListResp) respMsg;
				List<CardID> cardIDs = resp.cardIDs;
				getTradeUserInfoByCardID(cardIDs);//拉取交换名片,入库
				super.taskSucess(respMsg);
			}

		});
	}
	
	/**
	 * 从网络上拉取交换的名片
	 */
	
	protected void getTradeUserInfoByCardID(CardID cardID){
		List<CardID> cardIDs = new ArrayList<CardID>();
		cardIDs.add(cardID);
		getTradeUserInfoByCardID(cardIDs);
	}
	
	/**
	 * 从网络上拉取交换的名片
	 */
	
	protected void getTradeUserInfoByCardID(final List<CardID> cardIDs){
		HttpUtil.getNameCard(Tools.get_mpy_id(this), cardIDs, new  HttpPostCallBack(this , R.string.contact_tradeing_loading) {

			@Override
			public void taskSucess(IResponseMsg respMsg) {
				GetNameCardResp resp = (GetNameCardResp) respMsg;
				if(resp.number == cardIDs.size()){
					adapterData.clear();
					List<UserInfo> userInfos = resp.infos;
					for(UserInfo info : userInfos){
						ProviderUtil.insertOrUpdateUserInfo(Contact.this, info);//入库
						//生成缩略图
						Bitmap tempThum = ViewUtil.drawMPThum(Contact.this, info.getCardInfo(), R.drawable.card_01);
						adapterData.add(tempThum);
					}
					if(null != cf && null !=other_center){
						other_center.removeView(cf);
						createCoverFlow();
					}
					createCoverFlow();
				}else{
					ViewUtil.showShortToast(Contact.this, R.string.contact_requesttrade_fail);
				}
				super.taskSucess(respMsg);
			}
			
		});
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.contact_exit:
			firsts[0] = true;
			ViewUtil.createDailg(this, R.string.exit_title, R.string.exit_confm);
			break;
		case R.id.contact_unregisgter:
			Tools.user_UnRegister(this);
			Tools.user_UnRegisterDB(this);
			Intent newIntent = new Intent(this, LoginSignup.class);
			startActivity(newIntent);
			this.finish();
			break;

		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	public void onCenterRef(View view){
		switch (select) {
		case 0:
			getUserInfo();
			break;
		case 1:
			getTradeUserInfo();//拉取交换名片
			break;
		default:
			break;
		}
	}
	
	/**
	 * 两个tab的交换
	 */
	private void onselect() {
		switch (select) {
		case 0:
			tabs[0].setBackgroundResource(R.drawable.bottom_bar_bg_contact_left);
			tabs[0].setTextColor(Color.parseColor("#0d5ea9"));
			tabs[1].setBackgroundDrawable(null);// Resource(R.drawable.vertical);
			tabs[1].setTextColor(Color.WHITE);
			my_center.setVisibility(View.VISIBLE);
			other_center.setVisibility(View.GONE);
			break;

		default:
			tabs[1].setBackgroundResource(R.drawable.bottom_bar_bg_contact_right);
			tabs[1].setTextColor(Color.parseColor("#0d5ea9"));
			tabs[0].setBackgroundDrawable(null);// Resource(R.drawable.vertical);
			tabs[0].setTextColor(Color.WHITE);
			my_center.setVisibility(View.GONE);
			other_center.setVisibility(View.VISIBLE);
			if(firsts[3]){
				getTradeUserInfo();
				firsts[3] = false;
			}
			break;
		}
	}

	/**
	 * 进入编辑我的名片
	 * 
	 * @param view
	 */
	public void edit(View view) {
		int id = Tools.get_mpy_id(this).getId();
		Intent intent = new Intent(this, ContactEdit.class);
		intent.putExtra("id", id);
		startActivityForResult(intent, Config.ActivitCode.ContactEdit_RESULTCODE);
	}

	/**
	 * 进入编辑交换名片
	 * 
	 * @param view
	 */
	public void trade(View view) {
		try {
			if(Tools.getGpsState(this)){
				Log.i(TAG, "provider :" + provider);
				if(null != provider){
					Location location = locationManager.getLastKnownLocation(provider);
					Log.i(TAG, "Location :" + location);
					Log.i(TAG, "System.currentTimeMillis() :" + System.currentTimeMillis());
					Log.i(TAG, "Tools.mathTime(location.getTime(), System.currentTimeMillis()" + Tools.mathTime(location.getTime(), System.currentTimeMillis()));
					
					if(null != location && Tools.mathTime(location.getTime(), System.currentTimeMillis())){
						//
						TradeInfo tradeInfo = new TradeInfo(TradeInfo.TradeType.GPS, "" +
								location.getLatitude(), "" +location.getLongitude(), "" +location.getAltitude());
						sendTrade(tradeInfo);
					}else{
						//用基站定位
						getGsmLocation();
					}
				}else{
					firsts[4] = true;
				}
				
			}else{
				//如果没有打开 ,就弹出打开的界面,去监听
				ViewUtil.createDailgOpenGPS(this, R.string.exit_title, R.string.contact_opengps);
				//ViewUtil.showShortToast(this, R.string.contact_opengps);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			ViewUtil.showShortToast(this, R.string.contact_trade_fail);
			firsts[4] = true;
		}
		
	}
	
	
    
	/**
	 * 从网络上交换名片http
	 * @param tradeInfo
	 */
	private void sendTrade(TradeInfo tradeInfo){
		HttpUtil.tradeNameCard(Tools.get_mpy_id(this), tradeInfo, new HttpPostCallBack(this,
				R.string.contact_tradeing) {

			@Override
			public void taskSucess(IResponseMsg respMsg) {
				super.taskSucess(respMsg);
				//TODO交换返回成功
				ViewUtil.showShortToast(getBaseContext(), R.string.contact_trade_sucess);
				TradeNameCardResp resp = (TradeNameCardResp) respMsg;
				//取得交换名片的个人信息,入库,生成缩略图
				ProviderUtil.insertOrUpdateUserInfo(Contact.this, resp.userInfo);
				Bitmap bitmap = ViewUtil.drawMPThum(Contact.this, resp.userInfo.getCardInfo(), R.drawable.card_01);
				adapterData.add(bitmap);//加入
				//刷新滑动列表
				if(null != cf && null !=other_center){
					other_center.removeView(cf);
					createCoverFlow();
					
					//切换TAB
					if(null !=adapterData && adapterData.size() != 0){
						select = 1;
						onselect();
					}else{
						ViewUtil.showShortToast(Contact.this, R.string.contact_intoother);
					}
				}
			}
			
			@Override
			public void release() {
				firsts[4] = true;
				super.release();
			}
			
			@Override
			public void error() {
				firsts[4] = true;
				super.error();
			}

		});
	}
	
	private void getGsmLocation()throws Exception{
		TelephonyManager tm =(TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		GsmCellLocation gcl = (GsmCellLocation) tm.getCellLocation();
		int cid = gcl.getCid();
		int lac = gcl.getLac();

		int mcc = Integer.valueOf(tm.getNetworkOperator().substring(0, 3));
		int mnc = Integer.valueOf(tm.getNetworkOperator().substring(3, 5));
			// 组装JSON查询字符串
			JSONObject holder = new JSONObject();
			try {
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
				HttpUtil.getTradeFromGsm(holder, new HttpPostCallBack(this , R.string.contact_gegsm) {

					@Override
					public void taskSucess(IResponseMsg respMsg) {
						super.taskSucess(respMsg);
						TradeJsonResp resp = (TradeJsonResp) respMsg;
						if( null != resp.info){
							sendTrade(resp.info);
						}else{
							ViewUtil.showShortToast(Contact.this, R.string.contact_gegsm_fail);
						}
					}

					@Override
					public void error() {
						firsts[4] = true;
						super.error();
					}
					
				});
			} catch (JSONException e) {
				e.printStackTrace();
				firsts[4] = true;
				throw new Exception(e);
			}
	}

	
	
	
	private Gallery gallery;
	
	public class ImageAdapter extends BaseAdapter {
		
//		private List<Bitmap> datas = new ArrayList<Bitmap>();
		
		private Context mContext;

		public ImageAdapter(Context context) {
			mContext = context;
		}

		public int getCount() {
			return adapterData.size();
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// 此处加载图片
			ImageView imageView = new ImageView(mContext);
			BitmapDrawable mapdraw = new BitmapDrawable(adapterData.get(position));
			Bitmap bitmap = mapdraw.getBitmap();
			imageView.setImageBitmap(ViewUtil.createReflectedImage(bitmap));
			int with = ViewUtil.getSrceent(Contact.this).widthPixels;
			if(480 == with){
				imageView.setLayoutParams(new Gallery.LayoutParams(120, 120));
			}else if(320 == with){
				imageView.setLayoutParams(new Gallery.LayoutParams(70, 70));
			}
			
			return imageView;
		}

		public View composeItem(int position) {
			return gallery;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}

	@Override
	public View makeView() {
		ImageView imageView = new ImageView(this);
		return imageView;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.concat);
		tabs[0] = (TextView) findViewById(R.id.concat_mypy);
		tabs[1] = (TextView) findViewById(R.id.concat_jhpy);
		my_center = (RelativeLayout) findViewById(R.id.my_center);
		other_center = (LinearLayout) findViewById(R.id.other_center);
//		test();
		onselect();
		setTabOnClick();
		sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);  
		sensorManager.registerListener(this,SensorManager.SENSOR_ACCELEROMETER,SensorManager.SENSOR_DELAY_GAME);
		getLocation();//启动定位
	}
	
	public void test(){
		InputStream in = getResources().openRawResource(R.drawable.card_01);
		for(int i = 0 ;i< 6 ;i++){
			adapterData.add(new BitmapDrawable(in).getBitmap());
		}
		createCoverFlow();
	}
	
	/**
	 * 构建滑动列表
	 */
	private void createCoverFlow(){
		cf = new CoverFlow(this);
		ImageAdapter imageAdapter = new ImageAdapter(this);
		cf.setAdapter(imageAdapter);
		cf.setAnimationDuration(1500);
		cf.setOnItemClickListener(this);
		// cf.setOnItemLongClickListener(lonClick);
		LayoutParams layPara = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		cf.setLayoutParams(layPara);
		other_center.addView(cf);
	}

	@Override
	protected void onResume() {
		if (!Tools.getGpsState(this)) {
			if(firsts[2]){
				ViewUtil.createDailgOpenGPS(this, R.string.exit_title, R.string.contact_opengps);
			}
		} else {
			loadingUserInfo();
			getLocation();
		}
		super.onResume();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

	}

	@Override
	public void onBackPressed() {
		firsts[0] = true;
		ViewUtil.createDailg(this, R.string.exit_title, R.string.exit_confm);
	}

	@Override
	public void finish() {
		super.finish();
		if (firsts[0]) {
			Log.i(TAG, "Process.killProcess(Process.myPid());");
			Process.killProcess(Process.myPid());
			System.exit(1);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//界面requestCode
		switch (requestCode) {
		case Config.ActivitCode.ContactEdit_RESULTCODE:
			//重新设置个人信息
			UserInfo userInfo = ProviderUtil.getUserInfoByID(this, Tools.get_mpy_id(this).getId());
			setMy_CardInf(userInfo.getCardInfo());
			break;
		case Config.ActivitCode.OPENGPS_RESULTCODE:
			firsts[2] = false;
			loadingUserInfo();//去了打面gps界面,返回来时,加载数据
			getLocation();
			firsts[4] = true;
			break;
		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * 设置界面上的个人信息
	 * @param cardInfo
	 */
	public void setMy_CardInf(CardInfo cardInfo){
		((TextView)findViewById(R.id.my_cardinfo_name)).setText(cardInfo.getName());
		((TextView)findViewById(R.id.my_cardinfo_duty)).setText(cardInfo.getDuty());
		((TextView)findViewById(R.id.my_cardinfo_mobile)).setText(cardInfo.getMobile());
		((TextView)findViewById(R.id.my_cardinfo_email)).setText(cardInfo.getEmail());
		((TextView)findViewById(R.id.my_cardinfo_officeTel)).setText(cardInfo.getOfficeTel());
		((TextView)findViewById(R.id.my_cardinfo_officeFax)).setText(cardInfo.getOfficeFax());
		((TextView)findViewById(R.id.my_cardinfo_compAddr)).setText(cardInfo.getCompAddr());
	}
	
	private LocationListener locationListener = new LocationListener() {
		
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			Log.i(TAG, "locationListener onStatusChanged");
//			ViewUtil.setActivityTitle(Contact.this, "onStatusChanged");
		}
		
		@Override
		public void onProviderEnabled(String provider) {
			Log.i(TAG, "locationListener onProviderEnabled");
			ViewUtil.setActivityTitle(Contact.this, "onProviderEnabled");
//			locationManager.requestLocationUpdates(provider, 10 * 1000, 50,
//	                locationListener);
		}
		
		@Override
		public void onProviderDisabled(String provider) {
			Log.i(TAG, "locationListener onProviderDisabled");
//			ViewUtil.setActivityTitle(Contact.this, "onProviderDisabled");
		}
		
		@Override
		public void onLocationChanged(Location location) {
			Log.i(TAG, "locationListener onLocationChanged ");
		}
	};
	
	private void getLocation(){
        // 获取位置管理服务
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        // 查找到服务信息
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE); // 高精度
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW); // 低功耗
        provider = locationManager.getBestProvider(criteria, true); // 获取GPS信息
        // 设置监听器，自动更新的最小时间为间隔N秒(1秒为1*1000，这样写主要为了方便)或最小位移变化超过N米\
        if(null != provider){
        	 locationManager.requestLocationUpdates(provider, 10 * 1000, 50,
                     locationListener);
        }
    }
	
	@Override
	public void onAccuracyChanged(int sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(int sensor, float[] values) {
		if (sensor == SensorManager.SENSOR_ACCELEROMETER) {
			long curTime = System.currentTimeMillis();
			// 每100毫秒检测一次
			if ((curTime - lastUpdate) > 100) {
				long diffTime = (curTime - lastUpdate);
				lastUpdate = curTime;

				x = values[SensorManager.DATA_X];
				y = values[SensorManager.DATA_Y];
				z = values[SensorManager.DATA_Z];

				float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;

				if (speed > SHAKE_THRESHOLD) {
					Log.d(TAG, "trade ing firsts[4]" + firsts[4]);
					// Toast.makeText(this, “shake detected w/ speed: ” + speed,
					// Toast.LENGTH_SHORT).show();
					//重力感应交换名片
					if(firsts[4]&& !firsts[1]){
						firsts[4] = false;
						trade(null);
						Log.d(TAG, "trade ing trade(null)");
					}
				}
				last_x = x;
				last_y = y;
				last_z = z;
			}
		}

	}
}
