/**
 * @(#) AlarmConvent.java Created on 2012-9-21
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.util;

import com.aspire.android.test.execute.CommandConstants;
import com.aspire.mcts.agent.msg.ErrorReportRequest.ErrorCode;

/**
 * The class <code>AlarmConvent</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public final class AlarmConverter {

    /**
     * 转换Agent发来的告警码
     * @param srcCode
     * @return
     */
    public static int converter(int srcCode) {
        switch (srcCode) {
        case ErrorCode.MN_REG_FAIL:
            return CommandConstants.ALARM_MN_REG_FAIL;
        case ErrorCode.N_DATA_DISCONN:
            return CommandConstants.ALARM_N_DATA_DISCONN;
        case ErrorCode.SCREEN_CLOSED:
            return CommandConstants.ALARM_SCREEN_CLOSED;
        case ErrorCode.SCREEN_LOCKED:
            return CommandConstants.ALARM_SCREEN_LOCKED;
        case ErrorCode.SDCARD_ABSENT:
            return CommandConstants.ALARM_SDCARD_ABSENT;
        case ErrorCode.CPU_OVERRUN:
            return CommandConstants.ALARM_CPU_OVERRUN;
        case ErrorCode.MEM_OVERRUN:
            return CommandConstants.ALARM_MEM_OVERRUN;
        case ErrorCode.STORAGE_OVERRUN:
            return CommandConstants.ALARM_STORAGE_OVERRUN;
        case ErrorCode.NW_OVERRUN:
            return CommandConstants.ALARM_NW_OVERRUN;
        case ErrorCode.LOWER_POWER:
            return CommandConstants.ALARM_LOWER_POWER;
        case ErrorCode.LOWER_SIGNAL:
            return CommandConstants.ALARM_LOWER_SIGNAL;
        case ErrorCode.HIGH_TEMP:
            return CommandConstants.ALARM_HIGH_TEMP;
        default:
            break;
        }
        return 0;
    }

}
