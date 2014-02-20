/**
 * @(#) MobileMsgCreator.java Created on 2008-12-30
 * 
 * Copyright (c) 2008 Aspire. All Rights Reserved
 */
package com.aspire.mobile.codec;

import com.aspire.mobile.MobileConstants;
import com.aspire.mobile.msg.AddCalendarItemReq;
import com.aspire.mobile.msg.AddCalendarItemResp;
import com.aspire.mobile.msg.AddCallRecordReq;
import com.aspire.mobile.msg.AddCallRecordResp;
import com.aspire.mobile.msg.AddContactsReq;
import com.aspire.mobile.msg.AddContactsResp;
import com.aspire.mobile.msg.AddMessageReq;
import com.aspire.mobile.msg.AddMessageResp;
import com.aspire.mobile.msg.ApplicationOperationReq;
import com.aspire.mobile.msg.ApplicationOperationResp;
import com.aspire.mobile.msg.CalendarQueryReq;
import com.aspire.mobile.msg.CalendarQueryResp;
import com.aspire.mobile.msg.CallOperationReq;
import com.aspire.mobile.msg.CallOperationResp;
import com.aspire.mobile.msg.CallRecordQueryReq;
import com.aspire.mobile.msg.CallRecordQueryResp;
import com.aspire.mobile.msg.ConfigModifyReq;
import com.aspire.mobile.msg.ConfigModifyResp;
import com.aspire.mobile.msg.ContactGroupQueryReq;
import com.aspire.mobile.msg.ContactGroupQueryResp;
import com.aspire.mobile.msg.ContactsQueryReq;
import com.aspire.mobile.msg.ContactsQueryResp;
import com.aspire.mobile.msg.DeleteCalendarItemReq;
import com.aspire.mobile.msg.DeleteCalendarItemResp;
import com.aspire.mobile.msg.DeleteCallRecordReq;
import com.aspire.mobile.msg.DeleteCallRecordResp;
import com.aspire.mobile.msg.DeleteContactsReq;
import com.aspire.mobile.msg.DeleteContactsResp;
import com.aspire.mobile.msg.DeleteMessageReq;
import com.aspire.mobile.msg.DeleteMessageResp;
import com.aspire.mobile.msg.DirInfoQueryReq;
import com.aspire.mobile.msg.DirInfoQueryResp;
import com.aspire.mobile.msg.EmulatorRawCmdReq;
import com.aspire.mobile.msg.EmulatorRawCmdResp;
import com.aspire.mobile.msg.EmulatorTelnetCmdReq;
import com.aspire.mobile.msg.EmulatorTelnetCmdResp;
import com.aspire.mobile.msg.EventReportResp;
import com.aspire.mobile.msg.FileInfoQueryReq;
import com.aspire.mobile.msg.FileInfoQueryResp;
import com.aspire.mobile.msg.FileOperateReq;
import com.aspire.mobile.msg.FileOperateResp;
import com.aspire.mobile.msg.GrabNetworkPackageReq;
import com.aspire.mobile.msg.GrabNetworkPackageResp;
import com.aspire.mobile.msg.InputOperationReq;
import com.aspire.mobile.msg.InputOperationResp;
import com.aspire.mobile.msg.MessageBoxQueryReq;
import com.aspire.mobile.msg.MessageBoxQueryResp;
import com.aspire.mobile.msg.MessageStatisticQueryReq;
import com.aspire.mobile.msg.MessageStatisticQueryResp;
import com.aspire.mobile.msg.MmsEventReport;
import com.aspire.mobile.msg.MobileConnectReq;
import com.aspire.mobile.msg.MobileConnectResp;
import com.aspire.mobile.msg.MonitorControlReq;
import com.aspire.mobile.msg.MonitorControlResp;
import com.aspire.mobile.msg.PerfMonitorControlReq;
import com.aspire.mobile.msg.PerfMonitorControlResp;
import com.aspire.mobile.msg.PerformanceDataReportReq;
import com.aspire.mobile.msg.PerformanceDataReportResp;
import com.aspire.mobile.msg.PhoneEventReport;
import com.aspire.mobile.msg.ProcessQueryReq;
import com.aspire.mobile.msg.ProcessQueryResp;
import com.aspire.mobile.msg.SMSOperationReq;
import com.aspire.mobile.msg.SMSOperationResp;
import com.aspire.mobile.msg.SmsEventReport;
import com.aspire.mobile.msg.ViewerOperationReq;
import com.aspire.mobile.msg.ViewerOperationResp;
import com.aspire.mobile.msg.WapOperationReq;
import com.aspire.mobile.msg.WapOperationResp;
import com.aspire.msg.MsgBase;
import com.aspire.msg.MsgCreator;
import com.aspire.util.ByteArray;
import com.aspire.util.ToolException;

/**
 * The class <code>MobileMsgCreator</code>
 * 
 * @author xuyong
 * @version 1.0
 */
public class MobileMsgCreator implements MsgCreator {

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.msg.MsgCreator#create(byte[], int, int)
     */
    public MsgBase create(byte[] byMsg, int start, int length) {

        int offset = start + 4;

        if (offset > length)
            return null;

        try {
            int nMsgType = ByteArray.bytesToInt(byMsg, offset);

            switch (nMsgType) {
            // Engine control message
            case MobileConstants.CONFIG_MODIFY_REQ:
                return new ConfigModifyReq();
            case MobileConstants.CONFIG_MODIFY_RESP:
                return new ConfigModifyResp();
            case MobileConstants.MONITOR_CONTROL_REQ:
                return new MonitorControlReq();
            case MobileConstants.MONITOR_CONTROL_RESP:
                return new MonitorControlResp();
            case MobileConstants.MOBILE_CONNECT_REQ:
                return new MobileConnectReq();
            case MobileConstants.MOBILE_CONNECT_RESP:
                return new MobileConnectResp();
            case MobileConstants.PERF_MONITOR_CONTROL_REQ:
                return new PerfMonitorControlReq();
            case MobileConstants.PERF_MONITOR_CONTROL_RESP:
                return new PerfMonitorControlResp();

                // Monitor data message
            case MobileConstants.PERF_DATA_REPORT_REQ:
                return new PerformanceDataReportReq();
            case MobileConstants.PERF_DATA_REPORT_RESP:
                return new PerformanceDataReportResp();
            case MobileConstants.EVENT_REPORT_REQ:
                return createEventRequest(byMsg, offset, length);
            case MobileConstants.EVENT_REPORT_RESP:
                return new EventReportResp();

                // Data query message
            case MobileConstants.PROCESS_QUERY_REQ:
                return new ProcessQueryReq();
            case MobileConstants.PROCESS_QUERY_RESP:
                return new ProcessQueryResp();
            case MobileConstants.FILE_INFO_QUERY_REQ:
                return new FileInfoQueryReq();
            case MobileConstants.FILE_INFO_QUERY_RESP:
                return new FileInfoQueryResp();
            case MobileConstants.DIR_INFO_QUERY_REQ:
                return new DirInfoQueryReq();
            case MobileConstants.DIR_INFO_QUERY_RESP:
                return new DirInfoQueryResp();
            case MobileConstants.CONTACTS_QUERY_REQ:
                return new ContactsQueryReq();
            case MobileConstants.CONTACTS_QUERY_RESP:
                return new ContactsQueryResp();
            case MobileConstants.CONTACT_GROUP_QUERY_REQ:
                return new ContactGroupQueryReq();
            case MobileConstants.CONTACT_GROUP_QUERY_RESP:
                return new ContactGroupQueryResp();
            case MobileConstants.CALENDAR_QUERY_REQ:
                return new CalendarQueryReq();
            case MobileConstants.CALENDAR_QUERY_RESP:
                return new CalendarQueryResp();
            case MobileConstants.CALL_RECORD_QUERY_REQ:
                return new CallRecordQueryReq();
            case MobileConstants.CALL_RECORD_QUERY_RESP:
                return new CallRecordQueryResp();
            case MobileConstants.MESSAGE_BOX_QUERY_REQ:
                return new MessageBoxQueryReq();
            case MobileConstants.MESSAGE_BOX_QUERY_RESP:
                return new MessageBoxQueryResp();
            case MobileConstants.MESSAGE_STATISTIC_QUERY_REQ:
                return new MessageStatisticQueryReq();
            case MobileConstants.MESSAGE_STATISTIC_QUERY_RESP:
                return new MessageStatisticQueryResp();

                // Data update message
            case MobileConstants.FILE_OPERATE_REQ:
                return new FileOperateReq();
            case MobileConstants.FILE_OPERATE_RESP:
                return new FileOperateResp();
            case MobileConstants.DELETE_CONTACTS_REQ:
                return new DeleteContactsReq();
            case MobileConstants.DELETE_CONTACTS_RESP:
                return new DeleteContactsResp();
            case MobileConstants.DELETE_CALENDAR_ITEM_REQ:
                return new DeleteCalendarItemReq();
            case MobileConstants.DELETE_CALENDAR_ITEM_RESP:
                return new DeleteCalendarItemResp();
            case MobileConstants.DELETE_CALL_RECORD_REQ:
                return new DeleteCallRecordReq();
            case MobileConstants.DELETE_CALL_RECORD_RESP:
                return new DeleteCallRecordResp();
            case MobileConstants.DELETE_MESSAGE_REQ:
                return new DeleteMessageReq();
            case MobileConstants.DELETE_MESSAGE_RESP:
                return new DeleteMessageResp();
            case MobileConstants.ADD_MESSAGE_REQ:
                return new AddMessageReq();
            case MobileConstants.ADD_MESSAGE_RESP:
                return new AddMessageResp();
            case MobileConstants.ADD_CALL_RECORD_REQ:
                return new AddCallRecordReq();
            case MobileConstants.ADD_CALL_RECORD_RESP:
                return new AddCallRecordResp();
            case MobileConstants.ADD_CALENDAR_ITEM_REQ:
                return new AddCalendarItemReq();
            case MobileConstants.ADD_CALENDAR_ITEM_RESP:
                return new AddCalendarItemResp();
            case MobileConstants.ADD_CONTACTS_REQ:
                return new AddContactsReq();
            case MobileConstants.ADD_CONTACTS_RESP:
                return new AddContactsResp();

                // Emulator command message
            case MobileConstants.CALL_OPERATION_REQ:
                return new CallOperationReq();
            case MobileConstants.CALL_OPERATION_RESP:
                return new CallOperationResp();
            case MobileConstants.WAP_OPERATION_REQ:
                return new WapOperationReq();
            case MobileConstants.WAP_OPERATION_RESP:
                return new WapOperationResp();
            case MobileConstants.APP_OPERATION_REQ:
                return new ApplicationOperationReq();
            case MobileConstants.APP_OPERATION_RESP:
                return new ApplicationOperationResp();
            case MobileConstants.SMS_OPERATION_REQ:
                return new SMSOperationReq();
            case MobileConstants.SMS_OPERATION_RESP:
                return new SMSOperationResp();
            case MobileConstants.INPUT_OPERATION_REQ:
                return new InputOperationReq();
            case MobileConstants.INPUT_OPERATION_RESP:
                return new InputOperationResp();
            case MobileConstants.VIEWER_OPERATION_REQ:
                return new ViewerOperationReq();
            case MobileConstants.VIEWER_OPERATION_RESP:
                return new ViewerOperationResp();

                // Emulator command message
            case MobileConstants.EMULATOR_TELNET_CMD_REQ:
                return new EmulatorTelnetCmdReq();
            case MobileConstants.EMULATOR_TELNET_CMD_RESP:
                return new EmulatorTelnetCmdResp();
            case MobileConstants.EMULATOR_RAW_CMD_REQ:
                return new EmulatorRawCmdReq();
            case MobileConstants.EMULATOR_RAW_CMD_RESP:
                return new EmulatorRawCmdResp();
                
            case MobileConstants.GRAB_NETWORKPACKAGE_REQ:
                return new GrabNetworkPackageReq();
            case MobileConstants.GRAB_NETWORKPACKAGE_RESP:
                return new GrabNetworkPackageResp();

            default:

                return null;
            }
        } catch (ToolException ex) {
            return null;
        }
    }

    /**
     * Creates a MsgBase object from message information contained in byte array
     * 
     * @param byMsg
     *            The byte array which contains message information
     * @param start
     *            The begin index of the message in the ByteArray
     * @param length
     *            The total length of the byMsg
     * @return Returns the created MsgBase object
     */
    protected MsgBase createEventRequest(byte[] byMsg, int start, int length) {
        int offset = start + 4 + MobileConstants.MAX_TIME_LEN; // Message type and time-stamp

        if (offset >= length)
            return null;

        switch (byMsg[offset]) {
        case MobileConstants.EVENT_TYPE_SMS:
            return new SmsEventReport();
        case MobileConstants.EVENT_TYPE_MMS:
            return new MmsEventReport();
        case MobileConstants.EVENT_TYPE_PHONE:
            return new PhoneEventReport();
        }
        return null;
    }

}
