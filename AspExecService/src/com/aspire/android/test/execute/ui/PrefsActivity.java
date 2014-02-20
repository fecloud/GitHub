package com.aspire.android.test.execute.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.widget.Toast;

import com.aspire.android.common.exception.ExceptionHandler;
import com.aspire.android.test.PreferencesManager;
import com.aspire.android.test.execute.NameConstants;
import com.aspire.android.test.execute.NameConstants.SharedPrefConstants;
import com.aspire.android.test.execute.R;
import com.aspire.android.test.execute.entity.Province;
import com.aspire.android.test.execute.parse.ProvinceParse;
import com.aspire.android.test.log.RunLogger;

public class PrefsActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener,
        OnPreferenceChangeListener {

    public ProvinceParse provinceParse = null;
    public Context mContext;
    private RunLogger runLogger = RunLogger.getInstance();
    private EditTextPreference mEditTextUsername, mEditTextPassword, mEditTextScript, mEditTextHeartbeat,
            mEditTextDeviceTimeInterval, mEditTextTask, mEditTextTaskDownloadTime, mEditTextServiceIpThree,
            mEditTextService_port_three, mEditTextservice_username_three, mEditTextservice_password_three,
            mEditTextservice_ip_two, mEditTextservice_username_two, mEditTextscript_download_time,
            mEditTextservice_port_two, mEditTextservice_password_two, mEditTextservice_path_two,
            mEditTextservice_ip_four, mEditTextservice_username_four, mEditTextservice_password_four,
            mEditTextservice_port_four, mEditTextservice_path_four, mEditTexttest_result_upload_interval,
            mEditTexttest_result_resp_interval;
    public static final String DEVIC_NAME = "devic_name";
    public static final String SET_PHONE_NUM = "set_phone";
    public static final String TERMINAL = "set_terminal";
    public static final String IMEI = "set_IMEI";
    public static final String IP = "ip";
    public static final String PROVINCE = "set_province";
    public static final String CITY = "set_city";
    public static final String COMPANY = "set_company";
    public static final String TEST_PEOPLE = "set_test_people";
    public static final String DEVICE_TIME_INTERVAL = "device_time_interval";
    public static final String COLLECTION_FREQUENCY = "Performance_data_collection_frequency";
    public static final String SET_SAVE_ADDRESS = "set_save_address";
    public static final String SCRIPT_DOWNLOAD_URL = "script";
    public static final String EQUIPMENT = "equipment";
    public static final String HEARTBEAT = "heartbeat";
    public static final String ALARM = "alarm";
    public static final String TASK_DOWNLOAD_URL = "task";
    public static final String TASK_DOWNLOAD_TIME = "task_download_time";
    public static final String FILE_OPTION = "file_option";
    // 密钥更新的url及用户名密码
    public static final String PWD_UPDATE_URL = "password";
    public static final String PWD_UPDATE_USERNAME = "pwd_update_username";
    public static final String PWD_UPDATE_PWD = "pwd_update_pwd";
    // 业务指标FTP目录
    public static final String SERVICE_IP_TWO = "service_ip_two";
    public static final String SERVICE_USERNAME_TWO = "service_username_two";
    public static final String SERVICE_PASSWORD_TWO = "service_password_two";
    public static final String SERVICE_PORT_TWO = "service_port_two";
    public static final String SERVICE_PATH_TWO = "service_path_two";
    // 拨测脚本FTP目录
    public static final String SERVICE_IP_THREE = "service_ip_three";
    public static final String SERVICE_USERNAME_THREE = "service_username_three";
    public static final String SERVICE_PASSWORD_THREE = "service_password_three";
    public static final String SERVICE_PORT_THREE = "service_port_three";
    public static final String SERVICE_PATH_THREE = "service_path_three";
    public static final String SCRIPT_DOWNLOAD_TIME = "script_download_time";
    // 拨测结果FTP目录
    public static final String SERVICE_IP_FOUR = "service_ip_four";
    public static final String SERVICE_USERNAME_FOUR = "service_username_four";
    public static final String SERVICE_PASSWORD_FOUR = "service_password_four";
    public static final String SERVICE_PORT_FOUR = "service_port_four";
    public static final String SERVICE_PATH_FOUR = "service_path_four";
    public static final String TEST_RESULT_UPLOAD_INTERVAL = "test_result_upload_interval";
    public static final String TEST_RESULT_RESP_INTERVAL = "test_result_resp_interval";

    public static final String FTP_DATA_TIMEOUT = "ftp_data_timeout";
    /**
     * 设置上传开关
     */
    public static final String DATA_GET_SWITCH = "data_get_switch";
    /**
     * 短信控制的电话号码
     */
    public static final String SMS_CONTROAL_NUMBER = "sms_controal_number";
    /**
     * 拨测结果上传方式
     */
    public static final String UPLOADS_TYPE = "uploads_type";
    /**
     * 拨测结果上传方式：立即上传
     */
    public static final String UPLOADS_TYPE_NOW = "uploads_type_now";
    /**
     * 拨测结果上传方式：定时上传
     */
    public static final String UPLOADS_TYPE_QUICK = "uploads_type_quick";
    /**
     * 拨测结果上传方式：手动上传
     */
    public static final String UPLOADS_TYPE_MAN = "uploads_type_MAN";

    private SharedPreferences prefs;

    private PreferencesManager preferencesManager = PreferencesManager.getInstance();

    @Override
    protected void onResume() {
        super.onResume();
        prefs.registerOnSharedPreferenceChangeListener(this);
        getValue();
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onPause()
     */
    @Override
    protected void onPause() {
        super.onPause();
        prefs.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting_preferences);
        prefs = preferencesManager.getPreferences();
        SharedPreferences pref = getSharedPreferences("type", Context.MODE_PRIVATE);
        int type = pref.getInt(NameConstants.TYPE, NameConstants.TYPE_NORMAL);
        if (type == NameConstants.TYPE_TEST) {
            getPreferenceManager().setSharedPreferencesName(getPackageName() + "_preferences_test");
        } else if (type == NameConstants.TYPE_NORMAL) {
            getPreferenceManager().setSharedPreferencesName(getPackageName() + "_preferences");
        }
        // 加载省份,城市数据
        provinceParse = ProvinceParse.build(this, R.raw.province, R.raw.cities);
        getValue();
        init();
        mEditTextUsername = (EditTextPreference) findPreference(PWD_UPDATE_USERNAME);
        mEditTextPassword = (EditTextPreference) findPreference(PWD_UPDATE_PWD);
        mEditTextScript = (EditTextPreference) findPreference(SCRIPT_DOWNLOAD_URL);
        mEditTextHeartbeat = (EditTextPreference) findPreference(HEARTBEAT);
        mEditTextDeviceTimeInterval = (EditTextPreference) findPreference(DEVICE_TIME_INTERVAL);
        mEditTextTask = (EditTextPreference) findPreference(TASK_DOWNLOAD_URL);
        mEditTextTaskDownloadTime = (EditTextPreference) findPreference(TASK_DOWNLOAD_URL);
        mEditTextServiceIpThree = (EditTextPreference) findPreference(SERVICE_IP_THREE);
        mEditTextService_port_three = (EditTextPreference) findPreference(SERVICE_PORT_THREE);
        mEditTextservice_username_three = (EditTextPreference) findPreference(SERVICE_USERNAME_THREE);
        mEditTextservice_password_three = (EditTextPreference) findPreference(SERVICE_PASSWORD_THREE);
        mEditTextscript_download_time = (EditTextPreference) findPreference(SCRIPT_DOWNLOAD_TIME);
        mEditTextservice_ip_two = (EditTextPreference) findPreference(SERVICE_IP_TWO);
        mEditTextservice_username_two = (EditTextPreference) findPreference(SERVICE_USERNAME_TWO);
        mEditTextservice_port_two = (EditTextPreference) findPreference(SERVICE_PORT_TWO);
        mEditTextservice_password_two = (EditTextPreference) findPreference(SERVICE_PASSWORD_TWO);
        mEditTextservice_path_two = (EditTextPreference) findPreference(SERVICE_PATH_TWO);
        mEditTextservice_ip_four = (EditTextPreference) findPreference(SERVICE_IP_FOUR);
        mEditTextservice_username_four = (EditTextPreference) findPreference(SERVICE_USERNAME_FOUR);
        mEditTextservice_password_four = (EditTextPreference) findPreference(SERVICE_PASSWORD_FOUR);
        mEditTextservice_port_four = (EditTextPreference) findPreference(SERVICE_PORT_FOUR);
        mEditTextservice_path_four = (EditTextPreference) findPreference(SERVICE_PATH_FOUR);
        mEditTexttest_result_upload_interval = (EditTextPreference) findPreference(TEST_RESULT_UPLOAD_INTERVAL);
        mEditTexttest_result_resp_interval = (EditTextPreference) findPreference(TEST_RESULT_RESP_INTERVAL);
        mEditTextUsername.setOnPreferenceChangeListener(this);
        mEditTextPassword.setOnPreferenceChangeListener(this);
        mEditTextScript.setOnPreferenceChangeListener(this);
        mEditTextHeartbeat.setOnPreferenceChangeListener(this);
        mEditTextDeviceTimeInterval.setOnPreferenceChangeListener(this);
        mEditTextTask.setOnPreferenceChangeListener(this);
        mEditTextTaskDownloadTime.setOnPreferenceChangeListener(this);
        mEditTextServiceIpThree.setOnPreferenceChangeListener(this);
        mEditTextService_port_three.setOnPreferenceChangeListener(this);
        mEditTextservice_username_three.setOnPreferenceChangeListener(this);
        mEditTextservice_password_three.setOnPreferenceChangeListener(this);
        mEditTextscript_download_time.setOnPreferenceChangeListener(this);
        mEditTextservice_ip_two.setOnPreferenceChangeListener(this);
        mEditTextservice_username_two.setOnPreferenceChangeListener(this);
        mEditTextservice_port_two.setOnPreferenceChangeListener(this);
        mEditTextservice_password_two.setOnPreferenceChangeListener(this);
        mEditTextservice_path_two.setOnPreferenceChangeListener(this);
        mEditTextservice_ip_four.setOnPreferenceChangeListener(this);
        mEditTextservice_username_four.setOnPreferenceChangeListener(this);
        mEditTextservice_password_four.setOnPreferenceChangeListener(this);
        mEditTextservice_port_four.setOnPreferenceChangeListener(this);
        mEditTextservice_path_four.setOnPreferenceChangeListener(this);
        mEditTexttest_result_upload_interval.setOnPreferenceChangeListener(this);
        mEditTexttest_result_resp_interval.setOnPreferenceChangeListener(this);
    }

    public boolean onPreferenceChange(Preference preference, Object newValue) {
        // 当Preference改变时，这里会回调.
        if (preference.getKey().equals(PWD_UPDATE_USERNAME) || preference.getKey().equals(PWD_UPDATE_PWD)
                || preference.getKey().equals(SERVICE_USERNAME_THREE)
                || preference.getKey().equals(SERVICE_PASSWORD_THREE)
                || preference.getKey().equals(SERVICE_PASSWORD_TWO) || preference.getKey().equals(SERVICE_USERNAME_TWO)
                || preference.getKey().equals(SERVICE_USERNAME_FOUR)
                || preference.getKey().equals(SERVICE_PASSWORD_FOUR)) {
            if (newValue.toString().length() >= 50 || newValue.toString().length() <= 0) {
                Toast.makeText(PrefsActivity.this, "必填，不超过50字符", Toast.LENGTH_LONG).show();
                return false;
            }
        } else if (preference.getKey().equals(SERVICE_IP_THREE) || preference.getKey().equals(SERVICE_PORT_THREE)
                || preference.getKey().equals(SERVICE_IP_TWO) || preference.getKey().equals(SERVICE_PORT_TWO)
                || preference.getKey().equals(SERVICE_IP_FOUR) || preference.getKey().equals(SERVICE_PORT_FOUR)) {
            if (newValue.toString().length() > 20 || newValue.toString().length() <= 0) {
                Toast.makeText(PrefsActivity.this, "必填，不超过20字符", Toast.LENGTH_LONG).show();
                return false;
            }
        } else if (preference.getKey().equals(SCRIPT_DOWNLOAD_URL) || preference.getKey().equals(TASK_DOWNLOAD_URL)
                || preference.getKey().equals(SERVICE_PATH_TWO) || preference.getKey().equals(SERVICE_PATH_FOUR)

        ) {
            if (newValue.toString().length() > 200 || newValue.toString().length() <= 0) {
                Toast.makeText(PrefsActivity.this, "必填，不超过200字符", Toast.LENGTH_LONG).show();
                return false;
            }
        } else if (preference.getKey().equals(TASK_DOWNLOAD_TIME) || preference.getKey().equals(HEARTBEAT)
                || preference.getKey().equals(SCRIPT_DOWNLOAD_TIME)
                || preference.getKey().equals(TEST_RESULT_UPLOAD_INTERVAL)
                || preference.getKey().equals(TEST_RESULT_RESP_INTERVAL)) {
            if (newValue.toString().length() <= 0) {
                Toast.makeText(PrefsActivity.this, "必填，单位为分秒", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        // 返回true表示允许改变设置，返回false表示不允许改变当前设置
        return true;
    }

    public void getValue() {
        prefs.registerOnSharedPreferenceChangeListener(this);
        // 两个参数,一个是key，就是在PreferenceActivity的xml中设置的,另一个是取不到值时的默认值
        String switchStatus = null;
        String city_code = prefs.getString(CITY, null);
        String province_code = prefs.getString(PROVINCE, null);
        findPreference(SET_PHONE_NUM).setSummary(prefs.getString(SET_PHONE_NUM, null));
        findPreference(TERMINAL).setSummary(prefs.getString(TERMINAL, null));
        findPreference(COMPANY).setSummary(prefs.getString(COMPANY, null));
        findPreference(IMEI).setSummary(prefs.getString(IMEI, null));
        findPreference(IP).setSummary(prefs.getString(IP, null));
        findPreference(TEST_PEOPLE).setSummary(prefs.getString(TEST_PEOPLE, null));
        findPreference(PWD_UPDATE_URL).setSummary(prefs.getString(PWD_UPDATE_URL, null));
        findPreference(PWD_UPDATE_USERNAME).setSummary(prefs.getString(PWD_UPDATE_USERNAME, null));
        findPreference(PWD_UPDATE_PWD).setSummary(prefs.getString(PWD_UPDATE_PWD, null));
        findPreference(DEVICE_TIME_INTERVAL).setSummary(prefs.getString(DEVICE_TIME_INTERVAL, null));
        findPreference(COLLECTION_FREQUENCY).setSummary(prefs.getString(COLLECTION_FREQUENCY, null));
        findPreference(SET_SAVE_ADDRESS).setSummary(prefs.getString(SET_SAVE_ADDRESS, null));
        findPreference(SCRIPT_DOWNLOAD_URL).setSummary(prefs.getString(SCRIPT_DOWNLOAD_URL, null));
        findPreference(EQUIPMENT).setSummary(prefs.getString(EQUIPMENT, null));
        findPreference(HEARTBEAT).setSummary(prefs.getString(HEARTBEAT, null));
        findPreference(ALARM).setSummary(prefs.getString(ALARM, null));
        findPreference(TASK_DOWNLOAD_URL).setSummary(prefs.getString(TASK_DOWNLOAD_URL, null));
        findPreference(DEVIC_NAME).setSummary(prefs.getString(DEVIC_NAME, null));
        findPreference(TASK_DOWNLOAD_TIME).setSummary(prefs.getString(TASK_DOWNLOAD_TIME, null));
        findPreference(DATA_GET_SWITCH).setSummary(switchStatus);
        findPreference(SMS_CONTROAL_NUMBER).setSummary(prefs.getString(SMS_CONTROAL_NUMBER, null));
        // 业务指标FTP接口
        findPreference(SERVICE_IP_TWO).setSummary(prefs.getString(SERVICE_IP_TWO, null));
        findPreference(SERVICE_USERNAME_TWO).setSummary(prefs.getString(SERVICE_USERNAME_TWO, null));
        findPreference(SERVICE_PASSWORD_TWO).setSummary(prefs.getString(SERVICE_PASSWORD_TWO, null));
        findPreference(SERVICE_PORT_TWO).setSummary(prefs.getString(SERVICE_PORT_TWO, null));
        findPreference(SERVICE_PATH_TWO).setSummary(prefs.getString(SERVICE_PATH_TWO, null));
        // 拨测脚本FTP接口
        findPreference(SERVICE_IP_THREE).setSummary(prefs.getString(SERVICE_IP_THREE, null));
        findPreference(SERVICE_USERNAME_THREE).setSummary(prefs.getString(SERVICE_USERNAME_THREE, null));
        findPreference(SERVICE_PASSWORD_THREE).setSummary(prefs.getString(SERVICE_PASSWORD_THREE, null));
        findPreference(SERVICE_PORT_THREE).setSummary(prefs.getString(SERVICE_PORT_THREE, null));
        findPreference(SERVICE_PATH_THREE).setSummary(prefs.getString(SERVICE_PATH_THREE, null));
        findPreference(SCRIPT_DOWNLOAD_TIME).setSummary(prefs.getString(SCRIPT_DOWNLOAD_TIME, null));
        // 拨测结果FTP接口
        findPreference(SERVICE_IP_FOUR).setSummary(prefs.getString(SERVICE_IP_FOUR, null));
        findPreference(SERVICE_USERNAME_FOUR).setSummary(prefs.getString(SERVICE_USERNAME_FOUR, null));
        findPreference(SERVICE_PASSWORD_FOUR).setSummary(prefs.getString(SERVICE_PASSWORD_FOUR, null));
        findPreference(SERVICE_PORT_FOUR).setSummary(prefs.getString(SERVICE_PORT_FOUR, null));
        findPreference(SERVICE_PATH_FOUR).setSummary(prefs.getString(SERVICE_PATH_FOUR, null));
        findPreference(TEST_RESULT_UPLOAD_INTERVAL).setSummary(prefs.getString(TEST_RESULT_UPLOAD_INTERVAL, null));
        findPreference(TEST_RESULT_RESP_INTERVAL).setSummary(prefs.getString(TEST_RESULT_RESP_INTERVAL, null));
        
        findPreference(FTP_DATA_TIMEOUT).setSummary(prefs.getString(FTP_DATA_TIMEOUT, null));
        
        if (null != province_code && !province_code.equals("")) {
            String summary_provice = provinceParse.getProvinceByCode(Integer.parseInt(province_code)).getName();
            findPreference(PROVINCE).setSummary(summary_provice);
        }
        if (null != province_code && !province_code.equals("") && null != city_code && !city_code.equals("")) {
            String summary_city = provinceParse.getProvinceByCode(Integer.parseInt(province_code))
                    .getCityByCode(Integer.parseInt(city_code)).getName();
            findPreference(CITY).setSummary(summary_city);
        }
        if (prefs.getBoolean(DATA_GET_SWITCH, true)) {
            switchStatus = getResources().getString(R.string.switch_open);
        } else {
            switchStatus = getResources().getString(R.string.switch_close);
        }

        String uploadsType = prefs.getString(UPLOADS_TYPE, null);
        if (uploadsType != null && uploadsType.length() > 0) {
            final int position = Integer.parseInt(uploadsType);
            final String summary = getResources().getStringArray(R.array.results_uploads)[position - 1];
            findPreference(UPLOADS_TYPE).setSummary(summary);
        }
    }

    public void init() {
        final ListPreference province = (ListPreference) findPreference(PROVINCE);
        province.setEntries(provinceParse.getProvincesNames());
        province.setEntryValues(provinceParse.getProvincesCodes());
    }

    public void onProvinceChange(int code) {
        final ListPreference city = (ListPreference) findPreference(CITY);
        final ListPreference provinceList = (ListPreference) findPreference(PROVINCE);
        final String summary_provice = provinceParse.getProvinceByCode(code).getName();
        provinceList.setSummary(summary_provice);
        final Province province = provinceParse.getProvinceByCode(code);
        city.setEntries(province.getCitiesNameArray());
        city.setEntryValues(province.getCitiesUnique_codeArray());
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(SharedPrefConstants.TASK_LAST_INQUIRE_TIME)
                || key.equals(SharedPrefConstants.LAST_UPLOAD_RESULT_TIME)
                || key.equals(SharedPrefConstants.LAST_DOWNLOAD_RESP_TIME)
                || key.equals(SharedPrefConstants.LAST_DEVICE_STATUS_TIME)
                || key.equals(SharedPrefConstants.SCRIPT_LAST_INQUIRE_TIME)
                || key.equals(SharedPrefConstants.MAN_UPDATE_SCRIPT)
                || key.equals(SharedPrefConstants.MAN_UPDATE_TASK)
                || key.equals(SharedPrefConstants.MAN_UPLOAD_RESULT)
                        || key.equals(SharedPrefConstants.TASK_EXECUTE_OR_UPDATE_TIME)) {
            return;
        }
        if (null != key && key.equals(PROVINCE)) {
            final int code = Integer.parseInt(sharedPreferences.getString(key, ""));
            onProvinceChange(code);
        } else if (null != key && key.equals(CITY)) {
            final int unique_code = Integer.parseInt(sharedPreferences.getString(key, ""));
            final int province = Integer.parseInt(sharedPreferences.getString(PROVINCE, ""));// 取得选定的城市的id
            final String city_name = provinceParse.getProvinceByCode(province).getCityByCode(unique_code).getName();//
            findPreference(CITY).setSummary(city_name);
        } else if (key.equals(UPLOADS_TYPE)) {
            final int position = Integer.parseInt(sharedPreferences.getString(key, ""));
            final String summary = getResources().getStringArray(R.array.results_uploads)[position - 1];
            findPreference(key).setSummary(summary);
        } else if (key.equals(DATA_GET_SWITCH)) {
            String switchStatus = null;
            if (sharedPreferences.getBoolean(DATA_GET_SWITCH, true)) {
                switchStatus = getResources().getString(R.string.switch_open);
            } else {
                switchStatus = getResources().getString(R.string.switch_close);
            }
            findPreference(DATA_GET_SWITCH).setSummary(switchStatus);
        } else if (key.equals(SET_SAVE_ADDRESS)) {
            findPreference(key).setSummary(sharedPreferences.getString(key, ""));
            runLogger.onSaveParentPathChange();
        } else {
            try {
                findPreference(key).setSummary(sharedPreferences.getString(key, ""));
            } catch (RuntimeException e) {
                ExceptionHandler.handle(e, "while sharepreference.getString in method onSharedPreferenceChanged");
            }
        }
    }

}
