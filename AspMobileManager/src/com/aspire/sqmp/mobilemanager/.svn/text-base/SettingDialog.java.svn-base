/**
 * @(#) SettingDialog.java Created on 2012-7-12
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.aspire.common.config.Config;
import com.aspire.common.config.ConfigManager;
import com.aspire.common.util.VerifyTextUtil;
import com.aspire.common.util.MobileManagerConstants;
import com.aspire.common.util.StringConstants;
import com.aspire.common.util.VerifyTextUtil.VerifyLevel;

/**
 * The class <code>SettingDialog</code>
 * 
 * @author linjunsui
 * @version 1.0
 */
public class SettingDialog extends Dialog implements VerifyListener {

    private Config config;

    private Text company;
    private Text serviceKeyIp;
    private Text serviceKeyPort;
    private Text serviceKeyPath;
    private Text serviceKeyUserName;
    private Text serviceKeyPwd;
    private Text scriptIp;
    private Text scriptPort;
    private Text scriptPath;
    private Text scriptUserName;
    private Text scriptPwd;
    private Text resultIp;
    private Text resultPort;
    private Text resultPath;
    private Text resultUserName;
    private Text resultPwd;
    private Text versionIp;
    private Text versionPort;
    private Text versionPath;
    private Text versionUserName;
    private Text versionPwd;
    private Text tester;
    private Text pwdUpdateUrl;
    private Text taskDownloadUrl;
    private Text scriptQueryUrl;

    private Text deviceRegisterUrl;
    private Text deviceStatusUrl;
    private Text deviceAlarmUrl;
    private Text taskDownloadInterval;
    private Text deviceStatusInterval;
    private Text inquireUpdateEngineInterval;
    private Text mtExecServiceInterval;
    private Text serviceKeyInterval;
    private Text scriptInterval;
    private Text resultUploadInterval;
    private Text resultRespInterval;
    private Text taskUserName;
    private Text taskPwd;
    private Text scriptQueryUserName;
    private Text scriptQueryPwd;
    private Text deviceStatusUserName;
    private Text deviceStatusPwd;
    private Text deviceRegisterUserName;
    private Text deviceRegisterPwd;
    private Text deviceAlarmUserName;
    private Text deviceAlarmPwd;
    private Text pwdUpdateUserName;
    private Text pwdUpdatePwd;
    private ArrayList<CheckFormrEntity> checkArray = new ArrayList<SettingDialog.CheckFormrEntity>();

    /**
     * Create the dialog.
     * 
     * @param parentShell
     */
    public SettingDialog(Shell parentShell) {
        super(parentShell);
        setShellStyle(SWT.MAX | SWT.RESIZE | SWT.TITLE | SWT.PRIMARY_MODAL);
    }

    /**
     * 
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    @Override
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("参数设置");
    }

    /**
     * Create contents of the dialog.
     * 
     * @param parent
     */
    @Override
    protected Control createDialogArea(Composite parent) {

        Composite parentComposite = (Composite) super.createDialogArea(parent);
        parentComposite.setLayout(new FillLayout());
        ScrolledComposite scrolledComposite = new ScrolledComposite(parentComposite, SWT.H_SCROLL | SWT.V_SCROLL
                | SWT.BORDER);

        Composite container = new Composite(scrolledComposite, SWT.NONE);
        scrolledComposite.setContent(container);
        container.setFont(SWTResourceManager.getFont("微软雅黑", 6, SWT.ITALIC));
        container.setLayout(null);

        Label lbl_Company = new Label(container, SWT.NONE);
        lbl_Company.setBounds(307, 13, 60, 17);
        lbl_Company.setText("设置厂商：");

        company = new Text(container, SWT.BORDER);
        company.setTextLimit(10);
        company.setBounds(372, 10, 98, 23);

        Label lblTester = new Label(container, SWT.NONE);
        lblTester.setText("测试人员：");
        lblTester.setBounds(488, 13, 60, 17);

        tester = new Text(container, SWT.BORDER);
        tester.setBounds(554, 10, 98, 23);
        tester.setTextLimit(20);

        Label label_2 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
        label_2.setBounds(10, 39, 951, 2);

        Label lblServiceKeyFTP = new Label(container, SWT.NONE);
        lblServiceKeyFTP.setToolTipText("");
        lblServiceKeyFTP.setText("业务指标FTP接口");
        lblServiceKeyFTP.setFont(SWTResourceManager.getFont("微软雅黑", 8, SWT.NORMAL));
        lblServiceKeyFTP.setBounds(45, 53, 85, 16);

        Label lblServiceKeyIp = new Label(container, SWT.NONE);
        lblServiceKeyIp.setText("服务器IP：");
        lblServiceKeyIp.setBounds(55, 75, 61, 17);

        serviceKeyIp = new Text(container, SWT.BORDER);
        serviceKeyIp.setTextLimit(20);
        serviceKeyIp.setBounds(122, 75, 85, 17);

        Label lblServiceKeyPort = new Label(container, SWT.NONE);
        lblServiceKeyPort.setText("端口：");
        lblServiceKeyPort.setBounds(220, 75, 36, 17);

        serviceKeyPort = new Text(container, SWT.BORDER);
        serviceKeyPort.setTextLimit(20);
        serviceKeyPort.setBounds(262, 75, 73, 17);
        serviceKeyPort.addVerifyListener(this);

        Label lblServiceKeyPath = new Label(container, SWT.NONE);
        lblServiceKeyPath.setText("路径：");
        lblServiceKeyPath.setBounds(352, 75, 36, 17);

        serviceKeyPath = new Text(container, SWT.BORDER);
        serviceKeyPath.setTextLimit(200);
        serviceKeyPath.setBounds(394, 75, 73, 17);

        Label lblServiceKeyUserName = new Label(container, SWT.NONE);
        lblServiceKeyUserName.setText("用户名：");
        lblServiceKeyUserName.setBounds(484, 75, 48, 17);

        serviceKeyUserName = new Text(container, SWT.BORDER);
        serviceKeyUserName.setTextLimit(50);
        serviceKeyUserName.setBounds(534, 75, 85, 17);

        Label lblServiceKeyPwd = new Label(container, SWT.NONE);
        lblServiceKeyPwd.setText("密码：");
        lblServiceKeyPwd.setBounds(625, 75, 36, 17);

        serviceKeyPwd = new Text(container, SWT.BORDER | SWT.PASSWORD);
        serviceKeyPwd.setTextLimit(50);
        serviceKeyPwd.setBounds(667, 75, 73, 17);

        Label lblServiceKeyInterval = new Label(container, SWT.NONE);
        lblServiceKeyInterval.setText("指标下载时间间隔：");
        lblServiceKeyInterval.setBounds(746, 75, 108, 17);

        serviceKeyInterval = new Text(container, SWT.BORDER);
        serviceKeyInterval.setBounds(860, 75, 73, 17);
        serviceKeyInterval.addVerifyListener(this);

        Label label_3 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
        label_3.setBounds(10, 98, 951, 2);

        Label lblScriptFTP = new Label(container, SWT.NONE);
        lblScriptFTP.setToolTipText("");
        lblScriptFTP.setText("拨测脚本FTP接口");
        lblScriptFTP.setFont(SWTResourceManager.getFont("微软雅黑", 8, SWT.NORMAL));
        lblScriptFTP.setBounds(45, 110, 85, 16);

        Label lblScriptIp = new Label(container, SWT.NONE);
        lblScriptIp.setText("服务器IP：");
        lblScriptIp.setBounds(55, 132, 61, 17);

        scriptIp = new Text(container, SWT.BORDER);
        scriptIp.setTextLimit(20);
        scriptIp.setBounds(122, 132, 85, 17);

        Label lblScriptPort = new Label(container, SWT.NONE);
        lblScriptPort.setText("端口：");
        lblScriptPort.setBounds(220, 132, 36, 17);

        scriptPort = new Text(container, SWT.BORDER);
        scriptPort.setTextLimit(20);
        scriptPort.setBounds(262, 132, 73, 17);
        scriptPort.addVerifyListener(this);

        Label lblScriptPath = new Label(container, SWT.NONE);
        lblScriptPath.setText("路径：");
        lblScriptPath.setBounds(352, 132, 36, 17);

        scriptPath = new Text(container, SWT.BORDER);
        scriptPath.setTextLimit(200);
        scriptPath.setBounds(394, 132, 73, 17);

        Label lblScriptUserName = new Label(container, SWT.NONE);
        lblScriptUserName.setText("用户名：");
        lblScriptUserName.setBounds(484, 132, 48, 17);

        scriptUserName = new Text(container, SWT.BORDER);
        scriptUserName.setTextLimit(50);
        scriptUserName.setBounds(534, 132, 85, 17);

        Label lblScriptPwd = new Label(container, SWT.NONE);
        lblScriptPwd.setText("密码：");
        lblScriptPwd.setBounds(625, 132, 36, 17);

        scriptPwd = new Text(container, SWT.BORDER | SWT.PASSWORD);
        scriptPwd.setTextLimit(50);
        scriptPwd.setBounds(667, 132, 73, 17);

        Label lblScriptInterval = new Label(container, SWT.NONE);
        lblScriptInterval.setText("脚本更新时间间隔：");
        lblScriptInterval.setBounds(746, 132, 108, 17);

        scriptInterval = new Text(container, SWT.BORDER);
        scriptInterval.setBounds(860, 132, 73, 17);
        scriptInterval.addVerifyListener(this);

        Label label_4 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
        label_4.setBounds(10, 155, 951, 2);

        Label lblResultFTP = new Label(container, SWT.NONE);
        lblResultFTP.setToolTipText("");
        lblResultFTP.setText("拨测结果FTP接口");
        lblResultFTP.setFont(SWTResourceManager.getFont("微软雅黑", 8, SWT.NORMAL));
        lblResultFTP.setBounds(45, 167, 85, 16);

        Label lblResultIp = new Label(container, SWT.NONE);
        lblResultIp.setText("服务器IP：");
        lblResultIp.setBounds(55, 189, 61, 17);

        resultIp = new Text(container, SWT.BORDER);
        resultIp.setTextLimit(20);
        resultIp.setBounds(122, 189, 85, 17);

        Label lblResultPort = new Label(container, SWT.NONE);
        lblResultPort.setText("端口：");
        lblResultPort.setBounds(220, 189, 36, 17);

        resultPort = new Text(container, SWT.BORDER);
        resultPort.setTextLimit(20);
        resultPort.setBounds(262, 189, 73, 17);
        resultPort.addVerifyListener(this);

        Label lblResultPath = new Label(container, SWT.NONE);
        lblResultPath.setText("路径：");
        lblResultPath.setBounds(352, 189, 36, 17);

        resultPath = new Text(container, SWT.BORDER);
        resultPath.setTextLimit(200);
        resultPath.setBounds(394, 189, 73, 17);

        Label lblResultUserName = new Label(container, SWT.NONE);
        lblResultUserName.setText("用户名：");
        lblResultUserName.setBounds(481, 189, 48, 17);

        resultUserName = new Text(container, SWT.BORDER);
        resultUserName.setTextLimit(50);
        resultUserName.setBounds(534, 189, 85, 17);

        Label lblResultPwd = new Label(container, SWT.NONE);
        lblResultPwd.setText("密码：");
        lblResultPwd.setBounds(625, 189, 36, 17);

        resultPwd = new Text(container, SWT.BORDER | SWT.PASSWORD);
        resultPwd.setTextLimit(50);
        resultPwd.setBounds(667, 189, 73, 17);

        Label lblResultUploadInterval = new Label(container, SWT.NONE);
        lblResultUploadInterval.setText("结果上传时间间隔：");
        lblResultUploadInterval.setBounds(746, 163, 108, 17);

        resultUploadInterval = new Text(container, SWT.BORDER);
        resultUploadInterval.setBounds(860, 163, 73, 17);
        resultUploadInterval.addVerifyListener(this);

        Label lblResultRespInterval = new Label(container, SWT.NONE);
        lblResultRespInterval.setText("响应下载时间间隔：");
        lblResultRespInterval.setBounds(746, 189, 108, 17);

        resultRespInterval = new Text(container, SWT.BORDER);
        resultRespInterval.setBounds(860, 189, 73, 17);
        resultRespInterval.addVerifyListener(this);

        Label label_5 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
        label_5.setBounds(10, 212, 951, 2);

        Label lblVersionFTP = new Label(container, SWT.NONE);
        lblVersionFTP.setToolTipText("");
        lblVersionFTP.setText("版本升级FTP接口");
        lblVersionFTP.setFont(SWTResourceManager.getFont("微软雅黑", 8, SWT.NORMAL));
        lblVersionFTP.setBounds(45, 224, 85, 16);

        Label lblVersionIp = new Label(container, SWT.NONE);
        lblVersionIp.setText("服务器IP：");
        lblVersionIp.setBounds(55, 246, 61, 17);

        versionIp = new Text(container, SWT.BORDER);
        versionIp.setTextLimit(20);
        versionIp.setBounds(122, 246, 85, 17);

        Label lblVersionPort = new Label(container, SWT.NONE);
        lblVersionPort.setText("端口：");
        lblVersionPort.setBounds(220, 246, 36, 17);

        versionPort = new Text(container, SWT.BORDER);
        versionPort.setTextLimit(20);
        versionPort.addVerifyListener(this);
        versionPort.setBounds(262, 246, 73, 17);

        Label lblVersionPath = new Label(container, SWT.NONE);
        lblVersionPath.setText("路径：");
        lblVersionPath.setBounds(352, 246, 36, 17);

        versionPath = new Text(container, SWT.BORDER);
        versionPath.setTextLimit(200);
        versionPath.setBounds(394, 246, 73, 17);

        Label lblVersionUserName = new Label(container, SWT.NONE);
        lblVersionUserName.setText("用户名：");
        lblVersionUserName.setBounds(481, 246, 48, 17);

        versionUserName = new Text(container, SWT.BORDER);
        versionUserName.setTextLimit(50);
        versionUserName.setBounds(534, 246, 85, 17);

        Label lblVersionPwd = new Label(container, SWT.NONE);
        lblVersionPwd.setText("密码：");
        lblVersionPwd.setBounds(625, 246, 36, 17);

        versionPwd = new Text(container, SWT.BORDER | SWT.PASSWORD);
        versionPwd.setTextLimit(50);
        versionPwd.setBounds(667, 246, 73, 17);

        Label label_6 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
        label_6.setBounds(10, 269, 951, 2);

        Label lblEsInterface = new Label(container, SWT.NONE);
        lblEsInterface.setBounds(45, 283, 61, 17);
        lblEsInterface.setText("拨测接口");

        Label lblTaskDownload = new Label(container, SWT.NONE);
        lblTaskDownload.setText("任务下载：");
        lblTaskDownload.setBounds(55, 308, 61, 17);

        taskDownloadUrl = new Text(container, SWT.BORDER);
        taskDownloadUrl.setTextLimit(200);
        taskDownloadUrl.setBounds(126, 308, 304, 17);

        Label lblTaskUserName = new Label(container, SWT.NONE);
        lblTaskUserName.setText("用户名：");
        lblTaskUserName.setBounds(446, 308, 48, 17);

        taskUserName = new Text(container, SWT.BORDER);
        taskUserName.setTextLimit(50);
        taskUserName.setEnabled(false);
        taskUserName.setBounds(499, 308, 85, 17);

        Label lblTaskPwd = new Label(container, SWT.NONE);
        lblTaskPwd.setText("密码：");
        lblTaskPwd.setBounds(590, 308, 36, 17);

        taskPwd = new Text(container, SWT.BORDER | SWT.PASSWORD);
        taskPwd.setTextLimit(50);
        taskPwd.setEnabled(false);
        taskPwd.setBounds(632, 308, 73, 17);

        Label lblScriptSearch = new Label(container, SWT.NONE);
        lblScriptSearch.setText("脚本查询：");
        lblScriptSearch.setBounds(55, 331, 61, 17);

        scriptQueryUrl = new Text(container, SWT.BORDER);
        scriptQueryUrl.setTextLimit(200);
        scriptQueryUrl.setBounds(126, 331, 304, 17);

        Label lblScriptQueryUserName = new Label(container, SWT.NONE);
        lblScriptQueryUserName.setText("用户名：");
        lblScriptQueryUserName.setBounds(446, 331, 48, 17);

        scriptQueryUserName = new Text(container, SWT.BORDER);
        scriptQueryUserName.setTextLimit(50);
        scriptQueryUserName.setEnabled(false);
        scriptQueryUserName.setBounds(499, 331, 85, 17);

        Label lblScriptQueryPwd = new Label(container, SWT.NONE);
        lblScriptQueryPwd.setText("密码：");
        lblScriptQueryPwd.setBounds(590, 331, 36, 17);

        scriptQueryPwd = new Text(container, SWT.BORDER | SWT.PASSWORD);
        scriptQueryPwd.setTextLimit(50);
        scriptQueryPwd.setEnabled(false);
        scriptQueryPwd.setBounds(632, 331, 73, 17);

        Label lblDeviceStatus = new Label(container, SWT.NONE);
        lblDeviceStatus.setText("状态更新：");
        lblDeviceStatus.setBounds(55, 356, 61, 17);

        deviceStatusUrl = new Text(container, SWT.BORDER);
        deviceStatusUrl.setTextLimit(200);
        deviceStatusUrl.setBounds(126, 356, 304, 17);

        Label lblDeviceStatusUserName = new Label(container, SWT.NONE);
        lblDeviceStatusUserName.setText("用户名：");
        lblDeviceStatusUserName.setBounds(446, 356, 48, 17);

        deviceStatusUserName = new Text(container, SWT.BORDER);
        deviceStatusUserName.setTextLimit(50);
        deviceStatusUserName.setEnabled(false);
        deviceStatusUserName.setBounds(499, 356, 85, 17);

        Label lblDeviceStatusPwd = new Label(container, SWT.NONE);
        lblDeviceStatusPwd.setText("密码：");
        lblDeviceStatusPwd.setBounds(590, 356, 36, 17);

        deviceStatusPwd = new Text(container, SWT.BORDER | SWT.PASSWORD);
        deviceStatusPwd.setTextLimit(50);
        deviceStatusPwd.setEnabled(false);
        deviceStatusPwd.setBounds(632, 356, 73, 17);

        Label lblDeviceRegister = new Label(container, SWT.NONE);
        lblDeviceRegister.setText("设备注册：");
        lblDeviceRegister.setBounds(55, 381, 61, 17);

        deviceRegisterUrl = new Text(container, SWT.BORDER);
        deviceRegisterUrl.setTextLimit(200);
        deviceRegisterUrl.setBounds(126, 381, 304, 17);

        Label lblDeviceRegisterUserName = new Label(container, SWT.NONE);
        lblDeviceRegisterUserName.setText("用户名：");
        lblDeviceRegisterUserName.setBounds(446, 381, 48, 17);

        deviceRegisterUserName = new Text(container, SWT.BORDER);
        deviceRegisterUserName.setTextLimit(50);
        deviceRegisterUserName.setEnabled(false);
        deviceRegisterUserName.setBounds(499, 381, 85, 17);

        Label lblDeviceRegisterPwd = new Label(container, SWT.NONE);
        lblDeviceRegisterPwd.setText("密码：");
        lblDeviceRegisterPwd.setBounds(590, 381, 36, 17);

        deviceRegisterPwd = new Text(container, SWT.BORDER | SWT.PASSWORD);
        deviceRegisterPwd.setTextLimit(50);
        deviceRegisterPwd.setEnabled(false);
        deviceRegisterPwd.setBounds(632, 381, 73, 17);

        Label lblDeviceAlarm = new Label(container, SWT.NONE);
        lblDeviceAlarm.setText("设备告警：");
        lblDeviceAlarm.setBounds(55, 406, 61, 17);

        deviceAlarmUrl = new Text(container, SWT.BORDER);
        deviceAlarmUrl.setTextLimit(200);
        deviceAlarmUrl.setBounds(126, 406, 304, 17);

        Label lblDeviceAlarmUserName = new Label(container, SWT.NONE);
        lblDeviceAlarmUserName.setText("用户名：");
        lblDeviceAlarmUserName.setBounds(446, 406, 48, 17);

        deviceAlarmUserName = new Text(container, SWT.BORDER);
        deviceAlarmUserName.setTextLimit(50);
        deviceAlarmUserName.setEnabled(false);
        deviceAlarmUserName.setBounds(499, 406, 85, 17);

        Label lblDeviceAlarmPwd = new Label(container, SWT.NONE);
        lblDeviceAlarmPwd.setText("密码：");
        lblDeviceAlarmPwd.setBounds(590, 406, 36, 17);

        deviceAlarmPwd = new Text(container, SWT.BORDER | SWT.PASSWORD);
        deviceAlarmPwd.setTextLimit(50);
        deviceAlarmPwd.setEnabled(false);
        deviceAlarmPwd.setBounds(632, 406, 73, 17);

        Label lblPwdUpdata = new Label(container, SWT.NONE);
        lblPwdUpdata.setBounds(55, 431, 61, 17);
        lblPwdUpdata.setText("密钥更新：");

        pwdUpdateUrl = new Text(container, SWT.BORDER);
        pwdUpdateUrl.setTextLimit(200);
        pwdUpdateUrl.setBounds(127, 431, 304, 17);

        Label lblPwdUpdateUserName = new Label(container, SWT.NONE);
        lblPwdUpdateUserName.setText("用户名：");
        lblPwdUpdateUserName.setBounds(446, 431, 48, 17);

        pwdUpdateUserName = new Text(container, SWT.BORDER);
        pwdUpdateUserName.setTextLimit(50);
        pwdUpdateUserName.setBounds(499, 431, 85, 17);

        Label lblPwdUpdatePwd = new Label(container, SWT.NONE);
        lblPwdUpdatePwd.setText("密码：");
        lblPwdUpdatePwd.setBounds(591, 431, 36, 17);

        pwdUpdatePwd = new Text(container, SWT.BORDER | SWT.PASSWORD);
        pwdUpdatePwd.setTextLimit(50);
        pwdUpdatePwd.setBounds(632, 431, 73, 17);

        Label label_9 = new Label(container, SWT.SEPARATOR | SWT.VERTICAL);
        label_9.setBounds(719, 283, 2, 165);

        Label lblTaskDownloadInterval = new Label(container, SWT.NONE);
        lblTaskDownloadInterval.setText("任务下载时间间隔(s)：");
        lblTaskDownloadInterval.setBounds(727, 308, 127, 17);

        taskDownloadInterval = new Text(container, SWT.BORDER);
        taskDownloadInterval.setBounds(860, 308, 73, 17);
        taskDownloadInterval.addVerifyListener(this);

        Label lblDeviceStatusInterval = new Label(container, SWT.NONE);
        lblDeviceStatusInterval.setText("状态更新时间间隔(s)：");
        lblDeviceStatusInterval.setBounds(727, 331, 127, 17);

        deviceStatusInterval = new Text(container, SWT.BORDER);
        deviceStatusInterval.setBounds(860, 331, 73, 17);
        deviceStatusInterval.addVerifyListener(this);

        Label lblInquireUpdateEngineInterval = new Label(container, SWT.NONE);
        lblInquireUpdateEngineInterval.setText("检查更新引擎间隔(s)：");
        lblInquireUpdateEngineInterval.setBounds(727, 356, 127, 17);

        inquireUpdateEngineInterval = new Text(container, SWT.BORDER);
        inquireUpdateEngineInterval.setBounds(860, 356, 73, 17);
        inquireUpdateEngineInterval.addVerifyListener(this);

        Label lblMtExecServiceInterval = new Label(container, SWT.CENTER);
        lblMtExecServiceInterval.setText("维护运行服务间隔(s)：");
        lblMtExecServiceInterval.setAlignment(SWT.LEFT);
        lblMtExecServiceInterval.setBounds(727, 381, 127, 17);

        mtExecServiceInterval = new Text(container, SWT.BORDER);
        mtExecServiceInterval.setBounds(860, 381, 73, 17);
        mtExecServiceInterval.addVerifyListener(this);

        initialData();
        initCheckForm();
        scrolledComposite.setExpandHorizontal(true);
        scrolledComposite.setExpandVertical(true);
        scrolledComposite.setMinSize(container.computeSize(SWT.DEFAULT, SWT.DEFAULT));
        return parentComposite;
    }

    private void initialData() {
        config = ConfigManager.getConfig("global");
        String tmp = null;
        // 系统设置
        tmp = config.get(MobileManagerConstants.COMPANY_CODE, null);
        company.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.TESTER, null);
        tester.setText(tmp == null ? "" : tmp);
        // device的ftp设置
        tmp = config.get(MobileManagerConstants.DEVICE_TYPE_FTP_IP, null);
        tmp = config.get(MobileManagerConstants.DEVICE_TYPE_FTP_PORT, null);
        tmp = config.get(MobileManagerConstants.DEVICE_TYPE_FTP_PATH, null);
        tmp = config.get(MobileManagerConstants.DEVICE_TYPE_FTP_USER, null);
        tmp = config.get(MobileManagerConstants.DEVICE_TYPE_FTP_PWD, null);
        tmp = config.get(MobileManagerConstants.DEVICE_TYPE_INTERVAL, null);
        // servicekey的ftp设置
        tmp = config.get(MobileManagerConstants.SERVICE_KEY_FTP_IP, null);
        serviceKeyIp.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.SERVICE_KEY_FTP_PORT, null);
        serviceKeyPort.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.SERVICE_KEY_FTP_PATH, null);
        serviceKeyPath.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.SERVICE_KEY_FTP_USER, null);
        serviceKeyUserName.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.SERVICE_KEY_FTP_PWD, null);
        serviceKeyPwd.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.SERVICE_KEY_INTERVAL, null);
        serviceKeyInterval.setText(tmp == null ? "" : tmp);
        // test_script的ftp设置
        tmp = config.get(MobileManagerConstants.TEST_SCRIPT_FTP_IP, null);
        scriptIp.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.TEST_SCRIPT_FTP_PORT, null);
        scriptPort.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.TEST_SCRIPT_FTP_PATH, null);
        scriptPath.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.TEST_SCRIPT_FTP_USER, null);
        scriptUserName.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.TEST_SCRIPT_FTP_PWD, null);
        scriptPwd.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.TEST_SCRIPT_INTERVAL, null);
        scriptInterval.setText(tmp == null ? "" : tmp);
        // test_result的ftp设置
        tmp = config.get(MobileManagerConstants.TEST_RESULT_FTP_IP, null);
        resultIp.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.TEST_RESULT_FTP_PORT, null);
        resultPort.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.TEST_RESULT_FTP_PATH, null);
        resultPath.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.TEST_RESULT_FTP_USER, null);
        resultUserName.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.TEST_RESULT_FTP_PWD, null);
        resultPwd.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.TEST_RESULT_UPLOAD_INTERVAL, null);
        resultUploadInterval.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.TEST_RESULT_RESP_INTERVAL, null);
        resultRespInterval.setText(tmp == null ? "" : tmp);
        // version的ftp设置
        tmp = config.get(MobileManagerConstants.VERSION_FTP_IP, null);
        versionIp.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.VERSION_FTP_PORT, null);
        versionPort.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.VERSION_FTP_PATH, null);
        versionPath.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.VERSION_FTP_USER, null);
        versionUserName.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.VERSION_FTP_PWD, null);
        versionPwd.setText(tmp == null ? "" : tmp);
        // 拨测接口
        // 任务
        tmp = config.get(MobileManagerConstants.TASK_DOWNLOAD_URL, null);
        taskDownloadUrl.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.TASK_DOWNLOAD_USER, null);
        taskUserName.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.TASK_DOWNLOAD_PWD, null);
        taskPwd.setText(tmp == null ? "" : tmp);
        // 脚本查询
        tmp = config.get(MobileManagerConstants.TEST_SCRIPT_QUERY_URL, null);
        scriptQueryUrl.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.TEST_SCRIPT_QUERY_USER, null);
        scriptQueryUserName.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.TEST_SCRIPT_QUERY_PWD, null);
        scriptQueryPwd.setText(tmp == null ? "" : tmp);
        // 设备状态更新
        tmp = config.get(MobileManagerConstants.DEVICE_STATUS_URL, null);
        deviceStatusUrl.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.DEVICE_STATUS_USER, null);
        deviceStatusUserName.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.DEVICE_STATUS_PWD, null);
        deviceStatusPwd.setText(tmp == null ? "" : tmp);
        // 设备注册
        tmp = config.get(MobileManagerConstants.DEVICE_REGISTER_URL, null);
        deviceRegisterUrl.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.DEVICE_REGISTER_USER, null);
        deviceRegisterUserName.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.DEVICE_REGISTER_PWD, null);
        deviceRegisterPwd.setText(tmp == null ? "" : tmp);
        // 设备告警
        tmp = config.get(MobileManagerConstants.DEVICE_ALARM_URL, null);
        deviceAlarmUrl.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.DEVICE_ALARM_USER, null);
        deviceAlarmUserName.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.DEVICE_ALARM_PWD, null);
        deviceAlarmPwd.setText(tmp == null ? "" : tmp);
        // 密钥更新
        tmp = config.get(MobileManagerConstants.PASSWORD_UPDATE_URL, null);
        pwdUpdateUrl.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.PASSWORD_UPDATE_USER, null);
        pwdUpdateUserName.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.PASSWORD_UPDATE_PWD, null);
        pwdUpdatePwd.setText(tmp == null ? "" : tmp);
        // 部分时间间隔
        tmp = config.get(MobileManagerConstants.TASK_DOWNLOAD_INTERVAL, null);
        taskDownloadInterval.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.DEVICE_STATUS_INTERVAL, null);
        deviceStatusInterval.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.DEVICE_STATUS_INTERVAL, null);
        tmp = config.get(MobileManagerConstants.INQUIRE_UPDATE_ENGINE_INTERVAL, null);
        inquireUpdateEngineInterval.setText(tmp == null ? "" : tmp);
        tmp = config.get(MobileManagerConstants.MT_EXECSERVICE_INTERVAL, null);
        mtExecServiceInterval.setText(tmp == null ? "" : tmp);

    }

    /**
     * Create contents of the button bar.
     * 
     * @param parent
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, StringConstants.SAVE, true);
        createButton(parent, IDialogConstants.CANCEL_ID, StringConstants.CANCEL, false);
    }

    @Override
    protected void buttonPressed(int buttonId) {
        if (buttonId == IDialogConstants.OK_ID) {
            if (!checkForm())
                return;
            // 系统设置
            config.set(MobileManagerConstants.COMPANY_CODE, company.getText());
            config.set(MobileManagerConstants.TESTER, tester.getText());
            // servicekey的ftp设置
            config.set(MobileManagerConstants.SERVICE_KEY_FTP_IP, serviceKeyIp.getText());
            config.set(MobileManagerConstants.SERVICE_KEY_FTP_PORT, serviceKeyPort.getText());
            config.set(MobileManagerConstants.SERVICE_KEY_FTP_PATH, serviceKeyPath.getText());
            config.set(MobileManagerConstants.SERVICE_KEY_FTP_USER, serviceKeyUserName.getText());
            config.set(MobileManagerConstants.SERVICE_KEY_FTP_PWD, serviceKeyPwd.getText());
            config.set(MobileManagerConstants.SERVICE_KEY_INTERVAL, serviceKeyInterval.getText());
            // test_script的ftp设置
            config.set(MobileManagerConstants.TEST_SCRIPT_FTP_IP, scriptIp.getText());
            config.set(MobileManagerConstants.TEST_SCRIPT_FTP_PORT, scriptPort.getText());
            config.set(MobileManagerConstants.TEST_SCRIPT_FTP_PATH, scriptPath.getText());
            config.set(MobileManagerConstants.TEST_SCRIPT_FTP_USER, scriptUserName.getText());
            config.set(MobileManagerConstants.TEST_SCRIPT_FTP_PWD, scriptPwd.getText());
            config.set(MobileManagerConstants.TEST_SCRIPT_INTERVAL, scriptInterval.getText());
            // test_result的ftp设置
            config.set(MobileManagerConstants.TEST_RESULT_FTP_IP, resultIp.getText());
            config.set(MobileManagerConstants.TEST_RESULT_FTP_PORT, resultPort.getText());
            config.set(MobileManagerConstants.TEST_RESULT_FTP_PATH, resultPath.getText());
            config.set(MobileManagerConstants.TEST_RESULT_FTP_USER, resultUserName.getText());
            config.set(MobileManagerConstants.TEST_RESULT_FTP_PWD, resultPwd.getText());
            config.set(MobileManagerConstants.TEST_RESULT_UPLOAD_INTERVAL, resultUploadInterval.getText());
            config.set(MobileManagerConstants.TEST_RESULT_RESP_INTERVAL, resultRespInterval.getText());
            // version的ftp设置
            config.set(MobileManagerConstants.VERSION_FTP_IP, versionIp.getText());
            config.set(MobileManagerConstants.VERSION_FTP_PORT, versionPort.getText());
            config.set(MobileManagerConstants.VERSION_FTP_PATH, versionPath.getText());
            config.set(MobileManagerConstants.VERSION_FTP_USER, versionUserName.getText());
            config.set(MobileManagerConstants.VERSION_FTP_PWD, versionPwd.getText());
            // 拨测接口
            // 任务
            config.set(MobileManagerConstants.TASK_DOWNLOAD_URL, taskDownloadUrl.getText());
            config.set(MobileManagerConstants.TASK_DOWNLOAD_USER, taskUserName.getText());
            config.set(MobileManagerConstants.TASK_DOWNLOAD_PWD, taskPwd.getText());
            // 脚本查询
            config.set(MobileManagerConstants.TEST_SCRIPT_QUERY_URL, scriptQueryUrl.getText());
            config.set(MobileManagerConstants.TEST_SCRIPT_QUERY_USER, scriptQueryUserName.getText());
            config.set(MobileManagerConstants.TEST_SCRIPT_QUERY_PWD, scriptQueryPwd.getText());
            // 设备状态更新
            config.set(MobileManagerConstants.DEVICE_STATUS_URL, deviceStatusUrl.getText());
            config.set(MobileManagerConstants.DEVICE_STATUS_USER, deviceStatusUserName.getText());
            config.set(MobileManagerConstants.DEVICE_STATUS_PWD, deviceStatusPwd.getText());
            // 设备注册
            config.set(MobileManagerConstants.DEVICE_REGISTER_URL, deviceRegisterUrl.getText());
            config.set(MobileManagerConstants.DEVICE_REGISTER_USER, deviceRegisterUserName.getText());
            config.set(MobileManagerConstants.DEVICE_REGISTER_PWD, deviceRegisterPwd.getText());
            // 设备告警
            config.set(MobileManagerConstants.DEVICE_ALARM_URL, deviceAlarmUrl.getText());
            config.set(MobileManagerConstants.DEVICE_ALARM_USER, deviceAlarmUserName.getText());
            config.set(MobileManagerConstants.DEVICE_ALARM_PWD, deviceAlarmPwd.getText());
            // 密钥更新
            config.set(MobileManagerConstants.PASSWORD_UPDATE_URL, pwdUpdateUrl.getText());
            config.set(MobileManagerConstants.PASSWORD_UPDATE_USER, pwdUpdateUserName.getText());
            config.set(MobileManagerConstants.PASSWORD_UPDATE_PWD, pwdUpdatePwd.getText());
            // 部分间隔
            config.set(MobileManagerConstants.TASK_DOWNLOAD_INTERVAL, taskDownloadInterval.getText());
            config.set(MobileManagerConstants.DEVICE_STATUS_INTERVAL, deviceStatusInterval.getText());
            config.set(MobileManagerConstants.INQUIRE_UPDATE_ENGINE_INTERVAL, inquireUpdateEngineInterval.getText());
            config.set(MobileManagerConstants.MT_EXECSERVICE_INTERVAL, mtExecServiceInterval.getText());
            MessageDialog.openInformation(getShell(), "提示", "参数设置保存成功！");
            // 事后要记得调用父类的buttonPressed方法
        }
        super.buttonPressed(buttonId);
    }

    private void initCheckForm() {
        // 44个
        checkArray.clear();
        checkArray.add(new CheckFormrEntity(company, VerifyLevel.NOT_NULL, "厂商编码不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(tester, VerifyLevel.NOT_NULL, "测试人员不能为空，请输入！"));

        checkArray.add(new CheckFormrEntity(serviceKeyIp, VerifyLevel.NOT_NULL, "业务指标的ftp地址不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(serviceKeyPort, VerifyLevel.NOT_NULL, "业务指标的ftp端口不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(serviceKeyPath, VerifyLevel.NOT_NULL, "业务指标的ftp路径不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(serviceKeyUserName, VerifyLevel.NOT_NULL, "业务指标的ftp用户名不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(serviceKeyPwd, VerifyLevel.NOT_NULL, "业务指标的ftp密码不能为空，请输入！"));

        checkArray.add(new CheckFormrEntity(scriptIp, VerifyLevel.NOT_NULL, "拨测脚本的ftp地址不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(scriptPort, VerifyLevel.NOT_NULL, "拨测脚本的ftp端口不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(scriptPath, VerifyLevel.NOT_NULL, "拨测脚本的ftp路径不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(scriptUserName, VerifyLevel.NOT_NULL, "拨测脚本的ftp用户名不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(scriptPwd, VerifyLevel.NOT_NULL, "拨测脚本的ftp密码不能为空，请输入！"));

        checkArray.add(new CheckFormrEntity(resultIp, VerifyLevel.NOT_NULL, "拨测结果的ftp地址不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(resultPort, VerifyLevel.NOT_NULL, "拨测结果的ftp端口不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(resultPath, VerifyLevel.NOT_NULL, "拨测结果的ftp路径不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(resultUserName, VerifyLevel.NOT_NULL, "拨测结果的ftp用户名不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(resultPwd, VerifyLevel.NOT_NULL, "拨测结果的ftp密码不能为空，请输入！"));

        checkArray.add(new CheckFormrEntity(versionIp, VerifyLevel.NOT_NULL, "版本升级的ftp地址不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(versionPort, VerifyLevel.NOT_NULL, "版本升级的ftp端口不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(versionPath, VerifyLevel.NOT_NULL, "版本升级的ftp路径不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(versionUserName, VerifyLevel.NOT_NULL, "版本升级的ftp用户名不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(versionPwd, VerifyLevel.NOT_NULL, "版本升级的ftp密码不能为空，请输入！"));

        checkArray.add(new CheckFormrEntity(taskDownloadUrl, VerifyLevel.NOT_NULL, "获取任务的url不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(scriptQueryUrl, VerifyLevel.NOT_NULL, "脚本更新查询的url不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(deviceStatusUrl, VerifyLevel.NOT_NULL, "设备状态更新的url不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(deviceRegisterUrl, VerifyLevel.NOT_NULL, "设备注册的url不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(deviceAlarmUrl, VerifyLevel.NOT_NULL, "设备告警的url不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(pwdUpdateUrl, VerifyLevel.NOT_NULL, "更新密钥的url不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(pwdUpdateUserName, VerifyLevel.NOT_NULL, "更新密钥的用户名不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(pwdUpdatePwd, VerifyLevel.NOT_NULL, "更新密钥的密码不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(serviceKeyInterval, VerifyLevel.NOT_NULL, "指标下载时间间隔不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(scriptInterval, VerifyLevel.NOT_NULL, "脚本更新时间间隔不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(resultUploadInterval, VerifyLevel.NOT_NULL, "结果上传时间间隔不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(resultRespInterval, VerifyLevel.NOT_NULL, "响应下载时间间隔不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(taskDownloadInterval, VerifyLevel.NOT_NULL, "任务下载时间间隔不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(deviceStatusInterval, VerifyLevel.NOT_NULL, "状态更新时间间隔不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(inquireUpdateEngineInterval, VerifyLevel.NOT_NULL, "检查更新引擎间隔不能为空，请输入！"));
        checkArray.add(new CheckFormrEntity(mtExecServiceInterval, VerifyLevel.NOT_NULL, "维护运行服务间隔不能为空，请输入！"));
    }

    private boolean checkForm() {
        for (CheckFormrEntity c : checkArray) {
            if (!VerifyTextUtil.checkText(c.getText(), c.getChecklevel())) {
                MessageDialog.openWarning(getShell(), "警告", c.getMsg());
                c.getText().setFocus();
                return false;
            }
        }
        return true;
    }

    /**
     * Return the initial size of the dialog.
     */
    @Override
    protected Point getInitialSize() {
        return new Point(1000, 620);
    }

    /**
     * 
     * The class <code>CheckFormrEntity</code>
     * 
     * @author wuyanlong
     * @version 1.0
     */
    private class CheckFormrEntity {
        Text text;
        VerifyLevel checklevel;
        String msg;

        public CheckFormrEntity(Text text, VerifyLevel checklevel, String msg) {
            this.text = text;
            this.checklevel = checklevel;
            this.msg = msg;
        }

        /**
         * Getter of text
         * 
         * @return the text
         */
        public Text getText() {
            return text;
        }

        /**
         * Setter of text
         * 
         * @param text
         *            the text to set
         */
        public void setText(Text text) {
            this.text = text;
        }

        /**
         * Getter of checklevel
         * 
         * @return the checklevel
         */
        public VerifyLevel getChecklevel() {
            return checklevel;
        }

        /**
         * Setter of checklevel
         * 
         * @param checklevel
         *            the checklevel to set
         */
        public void setChecklevel(VerifyLevel checklevel) {
            this.checklevel = checklevel;
        }

        /**
         * Getter of msg
         * 
         * @return the msg
         */
        public String getMsg() {
            return msg;
        }

        /**
         * Setter of msg
         * 
         * @param msg
         *            the msg to set
         */
        public void setMsg(String msg) {
            this.msg = msg;
        }

    }

    @Override
    public void verifyText(VerifyEvent e) {
        Pattern pattern = Pattern.compile("[0-9]\\d*");
        Matcher matcher = pattern.matcher(e.text);
        if (matcher.matches()) // 处理数字
            e.doit = true;
        else if (e.text.length() > 0) // 有字符情况,包含中文、空格
            e.doit = false;
        else
            // 控制键
            e.doit = true;
    }
}
