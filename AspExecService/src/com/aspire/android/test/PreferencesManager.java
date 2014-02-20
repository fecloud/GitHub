/**
 * @(#) PreferencesManager.java Created on 2012-9-13
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test;

import com.aspire.android.test.application.ExecApplication;
import com.aspire.android.test.execute.NameConstants;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * The class <code>PreferencesManager</code>
 *
 * @author likai
 * @version 1.0
 */
public class PreferencesManager {

    private static PreferencesManager instance;
    
    private SharedPreferences preferences;
    
    private PreferencesManager(){
        SharedPreferences preferencesType = ExecApplication.instance().getBaseContext().getSharedPreferences("type", Context.MODE_PRIVATE);
        int type = preferencesType.getInt(NameConstants.TYPE, NameConstants.TYPE_NORMAL);
        if(type == NameConstants.TYPE_NORMAL){
            preferences = ExecApplication.instance().getBaseContext().getSharedPreferences(ExecApplication.instance().getBaseContext().getPackageName() + "_preferences", Context.MODE_PRIVATE);
        }else if(type == NameConstants.TYPE_TEST){
            preferences = ExecApplication.instance().getBaseContext().getSharedPreferences(ExecApplication.instance().getBaseContext().getPackageName() + "_preferences_test", Context.MODE_PRIVATE);
        }
    }
    
    public static PreferencesManager getInstance(){
        if(instance == null){
            instance = new PreferencesManager();
        }
        return instance;
    }

    /**
     * Getter of preferences
     * @return the preferences
     */
    public SharedPreferences getPreferences() {
        return preferences;
    }
}
