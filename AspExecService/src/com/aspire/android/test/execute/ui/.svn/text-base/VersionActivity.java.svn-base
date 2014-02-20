package com.aspire.android.test.execute.ui;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.widget.TextView;

import com.aspire.android.test.execute.R;

public class VersionActivity extends Activity {
    // private Button get_message_button, version_button;
    private TextView version_text = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.version);
        version_text = (TextView) findViewById(R.id.version_id);

        // 获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
 
        try {
            PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
            String version = packInfo.versionName;
            version_text.setText(version);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        /*
         * get_message_button = (Button) findViewById(R.id.get_set_message); version_button = (Button)
         * findViewById(R.id.lookover_set_message); get_message_button.setOnClickListener(new OnClickListener() {
         * 
         * public void onClick(View v) { // 获取设置信息 dialog();
         * 
         * } }); version_button.setOnClickListener(new OnClickListener() {
         * 
         * public void onClick(View v) { // 查看版本信息 Toast.makeText(getApplicationContext(),
         * getResources().getString(R.string.version_message), 1).show(); } }); }
         * 
         * // 处理查看设备信息的点击按钮事件，获取设置的参数 public void dialog() { SharedPreferences prefs =
         * PreferenceManager.getDefaultSharedPreferences(this); //
         * 两个参数,一个是key，就是在PreferenceActivity的xml中设置的,另一个是取不到值时的默认值 AlertDialog.Builder builder = new Builder(this);
         * builder.setTitle("设置信息预览"); builder.setMessage(getResources().getString(R.string.set_terminal) + "：" +
         * prefs.getString("set_terminal", null) + "\n" + getResources().getString(R.string.set_phone_num) + "：" +
         * prefs.getString("set_phone", null) + "\n" + getResources().getString(R.string.set_IMEI) + "：" +
         * prefs.getString("set_IMEI", null) + "\n" + getResources().getString(R.string.set_company) + "：" +
         * prefs.getString("set_company", null) + "\n" + getResources().getString(R.string.set_test_people) + "：" +
         * prefs.getString("set_test_people", null));
         * 
         * builder.setPositiveButton(getResources().getString(R.string.OK), null); AlertDialog alertDialog =
         * builder.create(); alertDialog.show();
         */

    }
}
