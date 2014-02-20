/**
 * @(#) ExecuteServiceState.java Created on 2012-8-16
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute;

import com.aspire.android.test.application.ExecApplication;
import com.aspire.android.test.execute.ui.MainActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * The class <code>ExecuteServiceState</code>
 * 
 * @author wuyanlong
 * @version 1.0
 */
public class EsStatusManager {
    public final static int NORMAL_ID = 0x0010;
    public final static String DEFAULT_TITLE = "ES提示:";
    private static EsStatusManager esStatusManager;
    private NotificationManager mNotificationManager;
    private ExecuteStatus state;

    private EsStatusManager() {
        mNotificationManager = (NotificationManager) ExecApplication.instance().getSystemService(
                Context.NOTIFICATION_SERVICE);
    }

    /**
     * @param state
     */
    public void setState(ExecuteStatus state) {
        this.state = state;
        onChangeListener(state);
    }

    public static synchronized EsStatusManager instance() {
        if (esStatusManager == null)
            esStatusManager = new EsStatusManager();
        return esStatusManager;
    }

    /**
     * @param state
     */
    private void onChangeListener(ExecuteStatus state) {
        int icon = 0;
        String title = DEFAULT_TITLE;
        String msg = state.getCompletionMsg();
        switch (state) {
        case ES_RUNNING:
            icon = R.drawable.motion_state;
            break;
        case ES_STOP_SERVICE:
            icon = R.drawable.motion_stop;
            break;
        case MOTION_STATE:
            icon = R.drawable.motion_state;
            break;
        case UNMOTION_STATE:
            icon = R.drawable.motion_un_state;
            break;
        default:
            break;
        }
        mNotificationManager.notify(NORMAL_ID, getNotification(icon, title, msg));
    }

    /**
     * 
     * @param icon
     * @param title
     * @param msg
     * @return
     */
    public Notification getNotification(int icon, String title, String msg) {
        return getNotification(icon, title, msg, ExecApplication.instance(), MainActivity.class);
    }

    /**
     * 
     * @param icon
     * @param title
     * @param msg
     * @param context
     * @param showActivityClass
     * @return
     */
    public Notification getNotification(int icon, String title, String msg, Context context, Class<?> showActivityClass) {
        long when = System.currentTimeMillis();
        CharSequence tickerText = msg;
        Notification notification = new Notification(icon, tickerText, when);
        notification.defaults = Notification.DEFAULT_LIGHTS;
        Intent mIntent = new Intent(context, showActivityClass);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent mContentIntent = PendingIntent.getActivity(context, 0, mIntent, 0);
        notification.setLatestEventInfo(context, title, msg, mContentIntent);
        return notification;
    }

    /**
     * @return
     */
    public ExecuteStatus getState() {
        return state;
    }

    /**
     * 
     * The class <code>ExecuteStatus</code>
     * 
     * @author wuyanlong
     * @version 1.0
     */
    public enum ExecuteStatus {
        ES_RUNNING("测试服务运行中", null), ES_STOP_SERVICE("测试服务已停止", null), MOTION_STATE("调度任务中", ES_RUNNING), UNMOTION_STATE(
                "不在调度任务", ES_RUNNING);
        private String msg;
        private ExecuteStatus parent;

        private ExecuteStatus(String msg, ExecuteStatus parent) {
            this.msg = msg;
            this.parent = parent;
        }

        public String getMsg() {
            return msg;
        }

        public String getCompletionMsg() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.insert(0, msg);
            ExecuteStatus parent = getParent();
            while (parent != null) {
                stringBuffer.insert(0, "-");
                stringBuffer.insert(0, parent.getMsg());
                parent = parent.getParent();
            }
            return stringBuffer.toString();
        }

        public ExecuteStatus getParent() {
            return parent;
        }
    }
}
