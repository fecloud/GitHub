/**
 * @(#) SettingActivity.java Created on 2012-10-20
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.uare.agent;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

/**
 * The class <code>SettingActivity</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class SettingActivity extends PreferenceActivity {
    public String defaultIp;
    public String defaultPort;

    /**
     * {@inheritDoc}
     * 
     * @see android.preference.PreferenceActivity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.addPreferencesFromResource(R.xml.setting);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        sp.registerOnSharedPreferenceChangeListener(new OnSharedPreferenceChangeListener() {

            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                /*
                 * // TODO Auto-generated method stub if (key.equals("server_ip")) { Log.d("Here", "Ip Item ");
                 * 
                 * if (defaultIp.equals(sharedPreferences.getString("service_ip", ""))) { Log.d("Here",
                 * "Server Ip is changed"); } else { Log.d("Here", "Server Ip is not changed"); }
                 * 
                 * } else if (key.equals("server_port")) { Log.d("Here", "Port Item ");
                 * 
                 * if (defaultIp.equals(sharedPreferences.getString("service_port", ""))) { Log.d("Here",
                 * "Server Port is changed"); } else { Log.d("Here", "Server Port is not changed"); } }
                 */

            }
        });
    }

}
