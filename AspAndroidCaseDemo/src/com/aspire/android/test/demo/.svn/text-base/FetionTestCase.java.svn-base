/**
 * @(#) FetionTestCase.java Created on 2012-6-20
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.test.demo;

import java.util.List;

import com.aspire.android.test.environment.IDeviceEntity;
import com.aspire.android.test.exception.MException;
import com.aspire.android.test.execute.CommandConstants;
import com.aspire.android.test.execute.ContentValues;
import com.aspire.android.test.testcase.AbstractTestCase;

/**
 * The class <code>FetionTestCase</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class FetionTestCase extends AbstractTestCase {

    private IDeviceEntity deviceEntity;

    private String noOrderMsg = "您将订购中国移动的飞信业务";

    private String yesOrderMsg = "您已经开通了飞信服务";

    private String opensuccess = "感谢您订制中国移动飞信";

    private String orderExitsuccess = "您已成功取消由中国移动提供的飞信业务";

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.android.test.testcase.AbstractTestCase#doExecute()
     */
    @Override
    protected void doExecute() throws Exception {
        deviceEntity = getDeviceEntity();
        logDebug("开始测试");
        sendOrderMsg();
        logDebug("开通业务短信已发出");
        logDebug("开始等待");

        int sumtime = 40000;
        String[] reviceMsg = null;
        String[] reviceMsgReprat = null;
        do {
            reviceMsg = getMsgRevice("1008619");
            reviceMsgReprat = getMsgRevice("12520034");
            if (null == reviceMsg || null == reviceMsgReprat) {
                deviceEntity.waitMillis(5000);
                sumtime -= 5000;
            } else {
                break;
            }
        } while (sumtime > 0);
        logDebug("等待40秒结束");
        if (null != reviceMsg && reviceMsg.length == 2 && reviceMsg[0].contains(noOrderMsg)) {
            // 二次订购确认
            logDebug("收到确认订购短信");
            logDebug("确认订购短信内容:" + reviceMsg[0]);
            deviceEntity.sendMessage(reviceMsg[1], "是");
            logDebug("确认订购短信,回复\"是\"");

            logDebug("等待开通成功短信");
            sumtime = 40000;
            do {
                reviceMsg = getMsgRevice("12520034");
                if (null == reviceMsg) {
                    deviceEntity.waitMillis(5000);
                    sumtime -= 5000;
                } else {
                    break;
                }
            } while (sumtime > 0);
            logDebug("等待开通成功短信结束");
            // 判断是否成功开通
            if (null != reviceMsg && reviceMsg.length == 2 && reviceMsg[0].contains(opensuccess)) {
                logDebug("开通短信收到,内容:" + reviceMsg[0]);
                orderExit();
            } else {
                logDebug("开通短信没有收到或者不是开通成功短信");
            }

        } else if (null != reviceMsgReprat && reviceMsgReprat.length == 2 && reviceMsgReprat[0].contains(yesOrderMsg)) {
            // 重复订购了
            logDebug("收到重复订购短信");
            logDebug("重复订购短信内容:" + reviceMsgReprat[0]);
            orderExit();
        } else {
            logDebug("没有收到确认订购短信");
        }
        logDebug("测试结束");
    }

    /**
     * 发送短信到12520
     * 
     * @return
     * @throws MException
     */
    protected boolean sendOrderMsg() throws MException {
        return deviceEntity.sendMessage("12520", "KTFX");
    }

    /**
     * 取短信
     * 
     * @return
     * @throws MException
     */
    protected boolean getOrderMsg() throws MException {
        final ContentValues params = new ContentValues(1);
        deviceEntity.getMessage(params);
        return true;
    }

    /**
     * 业务退订流程
     * 
     * @throws MException
     */
    protected void orderExit() throws MException {
        openWapExec();
        deviceEntity.waitMillis(10000);
       // logScreen("成功进入梦网页面");
        logDebug("点击营业厅");
        deviceEntity.screenClick(123, 250);
        deviceEntity.waitMillis(10000);
        //logScreen("成功进入营业厅页面");
        logDebug("点击输入框");
        deviceEntity.screenClick(38, 260);
        logDebug("输入飞信到输入框");
        deviceEntity.waitMillis(2000);
        deviceEntity.input("飞信");
        deviceEntity.waitMillis(2000);
        logDebug("点击搜业务");
        deviceEntity.screenClick(38, 300);
        deviceEntity.waitMillis(10000);
        //logScreen("转到搜索信息页面");
        logDebug("点击搜索到的飞信业务");
        deviceEntity.screenClick(43, 328);
        logDebug("点击飞信");
        deviceEntity.waitMillis(10000);
        //logScreen("转到取消飞信页面");
        deviceEntity.screenClick(43, 516);
        logDebug("点击取消飞信");
        deviceEntity.waitMillis(10000);
        //logScreen("转到确认取消飞信页面");
        deviceEntity.screenClick(47, 346);
        logDebug("点击取消飞信");
        deviceEntity.waitMillis(10000);
        //logScreen("转到取消飞信结果页面");

        // 校验是否收到取消成功短信
        deviceEntity.waitMillis(30000);
        logDebug("开始等待");
        int sumtime = 40000;
        String[] reviceMsg = null;
        do {
            reviceMsg = getMsgRevice("10086");
            if (null == reviceMsg) {
                deviceEntity.waitMillis(5000);
                sumtime -= 5000;
            } else {
                break;
            }
        } while (sumtime > 0);
        logDebug("等待40秒结束");
        if (null != reviceMsg && reviceMsg.length == 2 && reviceMsg[0].contains(orderExitsuccess)) {
            logDebug("取消飞信业务成功");
        } else {
            logDebug("没有收到取消飞信业务成功短信");
        }
    }

    /**
     * 打开wap.monternet.com
     * 
     * @return
     * @throws MException
     */
    protected void openWapExec() throws MException {
        logDebug("打开wap.monternet.com");
        deviceEntity.wapOpen("wap.monternet.com");
    }

    protected void click(int x, int y) throws MException {
        deviceEntity.screenClick(x, y);
    }

    /**
     * 取确认订购短信
     * 
     * @return
     * @throws MException
     */
    protected String[] getMsgRevice(String number) throws MException {
        final int reCount = 10;
        ContentValues params = new ContentValues();
        params.put(CommandConstants.KEY_GETMESSAGE_TYPE, 0x01);
        params.put(CommandConstants.KEY_GETMESSAGE_COUNT, reCount);
        params.put(CommandConstants.KEY_GETMESSAGE_DESTADDRESSES, number);
        params.put(CommandConstants.KEY_GETMESSAGE_PRIORITY, 0x02);
        final List<ContentValues> values = deviceEntity.getMessage(params);
        if (null != values && values.size() > 0) {
            final ContentValues contentValues = values.get(0);
            String[] res = new String[2];
            res[0] = contentValues.getAsString(CommandConstants.KEY_GETMESSAGE_BODY);
            res[1] = contentValues.getAsString(CommandConstants.KEY_GETMESSAGE_SOURCEADDRESS);
            return res;

        }
        return null;
    }

}
