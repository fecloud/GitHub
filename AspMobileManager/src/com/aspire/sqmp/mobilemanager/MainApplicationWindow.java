/**
 * @(#) MainApplicationWindow.java Created on 2012-7-12
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspire.common.config.Config;
import com.aspire.common.config.ConfigManager;
import com.aspire.common.config.PropertyConfigDao;
import com.aspire.common.exception.ExceptionHandler;
import com.aspire.common.exception.MException;
import com.aspire.common.util.MobileManagerConstants;
import com.aspire.common.util.StringConstants;
import com.aspire.sqmp.mobilemanager.entity.Device;
import com.aspire.sqmp.mobilemanager.entity.MobileSharedFile;
import com.aspire.sqmp.mobilemanager.listener.DialogClickListener;
import com.aspire.sqmp.mobilemanager.region.City;
import com.aspire.sqmp.mobilemanager.region.Province;
import com.aspire.sqmp.mobilemanager.region.RegionParse;
import com.aspire.sqmp.mobilemanager.service.DeviceManager;
import com.aspire.sqmp.mobilemanager.service.PropertyManager;
import com.aspire.sqmp.mobilemanager.service.RemoteDevice;
import com.aspire.sqmp.mobilemanager.service.RemoteDeviceManager;
import com.aspire.sqmp.mobilemanager.service.RemoteDeviceManager.IRemoteDevicesChangeListener;
import com.aspire.sqmp.mobilemanager.service.SyncService;
import com.aspire.sqmp.mobilemanager.service.adb.DeviceAdbState;
import com.aspire.sqmp.mobilemanager.service.request.GResultResponse;

/**
 * The class <code>MainApplicationWindow</code>
 * 
 * @author linjunsui
 * @version 1.0
 */
public class MainApplicationWindow extends ApplicationWindow implements ModifyListener, SelectionListener,
        VerifyListener, IRemoteDevicesChangeListener, DialogClickListener {

    /**
     * logger
     */
    protected Logger logger = LoggerFactory.getLogger(MainApplicationWindow.class);

    private Action actionAbout;
    private Action devicesInfoAct;
    private Action actionSetting;
    private Action actionAddDevice;

    /**
     * 全局设置
     */
    protected SettingDialog settingDialog;

    /**
     * 关于
     */
    protected AboutDialog aboutDialog;
    /**
     * 设备数据查询弹出框
     */
    protected DevicesInfoDialog devicesInfoDialog;
    private Action actionDeleteDevice;
    private Action actionSync;

    private DeviceManager deviceManager;

    /**
     * 左边显示设备名称的列表
     */
    private org.eclipse.swt.widgets.List list;
    /**
     * 设备名称
     */
    private Text deviceName;
    /**
     * 手机号码
     */
    private Text mobileNum;
    /**
     * 终端类型
     */
    private Text model;
    /**
     * imei
     */
    private Text imei;
    /**
     * 性能数据采集频率
     */
    private Text dataCollFreq;
    /**
     * 短信控制电话号码
     */
    private Text smsConNum;
    /**
     * 测试数据主目录
     */
    private Text dataParentPath;
    /**
     * 连接开关
     */
    private Button checkBtnSwitch;
    /**
     * 选择省份
     */
    private Combo comboProvince;
    /**
     * 选择城市
     */
    private Combo comboCity;
    /**
     * 拨测结果上传方式
     */
    private Combo uploadResultType;
    /**
     * 保存数据
     */
    private Button btnSave;
    /**
     * 下载任务
     */
    private Button btnDownTask;
    /**
     * 同步任务
     */
    private Button btnSyncTask;
    /**
     * 下载脚本
     */
    private Button btnDownScript;
    /**
     * 同步脚本
     */
    private Button btnSyncScript;
    /**
     * 上传结果
     */
    private Button btnUploadResult;
    /**
     * 同步结果
     */
    private Button btnSyncResult;
    /**
     * 标签栏-私有数据
     */
    private TabItem deviceSettingTab;
    /**
     * 标签栏-私有数据
     */
    private TabItem downloadTab;
    /**
     * 最后一次任务更新时间
     */
    private Label lblTaskLastUpdateTime;
    /**
     * 任务是否有更新文件
     */
    private Label lblTaskNewDataStatus;
    /**
     * 最后一次脚本更新时间
     */
    private Label lblScriptLastUpdateTime;
    /**
     * 脚本是否有可更新文件
     */
    private Label lblScriptNewDataStatus;
    /**
     * 最后一次结果更新时间
     */
    private Label lblResultLastUpdateTime;
    /**
     * 结果是否有可更新文件
     */
    private Label lblResultNewDataStatus;
    /**
     * 容器
     */
    private TabFolder tabFolder;
    /**
     * 存储设备私有数据
     */
    private Device device;
    private List<Device> devices;
    private String[] uploadTypes = { "立即上传", "定时上传", "手动上传" };

    /**
     * listview上次选中的位置
     */
    private int selectPosition = -1;

    /**
     * 判断表单是否修改过
     */
    private boolean changeStatus = false;

    /**
     * 用于和手机传输
     */
    private RemoteDeviceManager remoteDeviceManager;
    /**
     * 地区相关
     */
    private RegionParse regionParse;
    private Province province;
    private City city;
    private SyncService syncService;
    private Config config;
    private String adbDeviceName;
    private Label lblLastSyncTime;
    private Label lblTaskLastSyncTime;
    private Label lblScriptLastSyncTime;
    private Label lblResultLastSyncTime;
    private Label lblIp;
    private Text ip;
    private MenuItem menuItemDelete;

    /**
     * Create the application window.
     */
    public MainApplicationWindow() {
        super(null);
        deviceManager = DeviceManager.getInstance();
        device = new Device();
        initConfig();
        syncService = new SyncService();
        createActions();
        addToolBar(SWT.FLAT | SWT.WRAP);
        addMenuBar();
        addStatusLine();
        remoteDeviceManager = RemoteDeviceManager.getInstance();
        remoteDeviceManager.addDeviceChangeListener(this);

    }

    /**
     * init config
     */
    private void initConfig() {
        try {
            ConfigManager.add(new Config("global", new PropertyConfigDao("./data/config.ini")));
            config = ConfigManager.getConfig("global");
        } catch (IOException e) {
            ExceptionHandler.handle(e, "error of config.ini");
        }
    }

    /**
     * Create contents of the application window.
     * 
     * @param parent
     */
    @Override
    protected Control createContents(Composite parent) {
        
        Composite parentComposite = (Composite) super.createContents(parent);
        parentComposite.setLayout(new FillLayout()); 
        ScrolledComposite scrolledComposite = new ScrolledComposite(parentComposite, SWT.H_SCROLL|SWT.V_SCROLL);
         
        Composite container = new Composite(scrolledComposite, SWT.NONE);
        scrolledComposite.setContent(container); 
        container.setLayout(new FormLayout());

        ListViewer listViewer = new ListViewer(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        list = listViewer.getList();
        FormData fd_list = new FormData();
        fd_list.bottom = new FormAttachment(100, -1);
        fd_list.right = new FormAttachment(0, 200);
        fd_list.top = new FormAttachment(0);
        fd_list.left = new FormAttachment(0);
        list.setLayoutData(fd_list);
        // 初始化值
        list.setItems(new String[] {});
        try {
            deviceManager.loadDevice(MobileManagerConstants.LOCAL_PRIVATE_PATH);
        } catch (MException e) {
            ExceptionHandler.handle(e, "while loadDevice to listview");
        }
        devices = deviceManager.getDeviceList();
        for (int i = 0; i < devices.size(); i++) {
            if (devices.get(i).isTaskHasUpdate() || devices.get(i).isScriptHasUpdate()
                    || devices.get(i).isResultHasUpload())
                list.add(devices.get(i).getDeviceName() + StringConstants.SYN_NEW);
            else {
                list.add(devices.get(i).getDeviceName());
            }
        }

        Menu menu = new Menu(list);
        list.setMenu(menu);

        MenuItem menuItemAdd = new MenuItem(menu, SWT.NONE);
        menuItemAdd.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                try {
                    if (changeStatus)
                        changeDevice(selectPosition);
                    addDevice(null);
                } catch (MException e) {
                    ExceptionHandler.handle(e, "while actionAddDevice to add device");
                }
            }
        });
        menuItemAdd.setText("新增设备");

        menuItemDelete = new MenuItem(menu, SWT.NONE);
        menuItemDelete.setEnabled(false);
        menuItemDelete.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                try {
                    deleteDevice();
                } catch (MException e) {
                    ExceptionHandler.handle(e, "while delete device xml file");
                }
            }
        });
        menuItemDelete.setText("删除设备");
        tabFolder = new TabFolder(container, SWT.NONE);
        FormData fd_tabFolder = new FormData();
        fd_tabFolder.bottom = new FormAttachment(100, 0);
        fd_tabFolder.right = new FormAttachment(100, 0);
        fd_tabFolder.top = new FormAttachment(0);
        fd_tabFolder.left = new FormAttachment(0, 205);
        tabFolder.setLayoutData(fd_tabFolder);

        deviceSettingTab = new TabItem(tabFolder, SWT.NONE);
        deviceSettingTab.setText("终端信息");

        Composite composite = new Composite(tabFolder, SWT.NONE);
        deviceSettingTab.setControl(composite);

        Label lblDeviceName = new Label(composite, SWT.NONE);
        lblDeviceName.setBounds(34, 34, 127, 17);
        lblDeviceName.setText("设备名称：");

        deviceName = new Text(composite, SWT.BORDER);
        deviceName.setBounds(173, 34, 150, 17);
        deviceName.setTextLimit(20);
        deviceName.addModifyListener(this);

        Label lblMobileNum = new Label(composite, SWT.NONE);
        lblMobileNum.setText("手机号码：");
        lblMobileNum.setBounds(34, 80, 127, 17);

        mobileNum = new Text(composite, SWT.BORDER);
        mobileNum.setTextLimit(11);
        mobileNum.setBounds(173, 80, 150, 17);
        mobileNum.addModifyListener(this);
        mobileNum.addVerifyListener(this);

        Label lblModel = new Label(composite, SWT.NONE);
        lblModel.setText("设备类型：");
        lblModel.setBounds(34, 123, 127, 17);

        model = new Text(composite, SWT.BORDER);
        model.setTextLimit(20);
        model.setBounds(173, 123, 150, 17);
        model.addModifyListener(this);

        Label lblImei = new Label(composite, SWT.CENTER);
        lblImei.setAlignment(SWT.LEFT);
        lblImei.setText("设备编码：");
        lblImei.setBounds(34, 172, 127, 17);

        imei = new Text(composite, SWT.BORDER);
        imei.setTextLimit(20);
        imei.setBounds(173, 172, 150, 17);
        imei.addModifyListener(this);
        imei.addVerifyListener(this);

        Label lblDataCollFreq = new Label(composite, SWT.CENTER);
        lblDataCollFreq.setText("性能数据采集频率(s)：");
        lblDataCollFreq.setAlignment(SWT.LEFT);
        lblDataCollFreq.setBounds(34, 271, 138, 17);

        dataCollFreq = new Text(composite, SWT.BORDER);
        dataCollFreq.setTextLimit(10);
        dataCollFreq.setBounds(173, 271, 150, 17);
        dataCollFreq.addModifyListener(this);
        dataCollFreq.addVerifyListener(this);

        Label lblSmsConNum = new Label(composite, SWT.CENTER);
        lblSmsConNum.setText("短信控制电话号码：");
        lblSmsConNum.setAlignment(SWT.LEFT);
        lblSmsConNum.setBounds(393, 34, 138, 17);

        smsConNum = new Text(composite, SWT.BORDER);
        smsConNum.setBounds(532, 34, 150, 17);
        smsConNum.addModifyListener(this);
        smsConNum.addVerifyListener(this);

        Label lblDataParentPath = new Label(composite, SWT.CENTER);
        lblDataParentPath.setText("测试数据目录：");
        lblDataParentPath.setAlignment(SWT.LEFT);
        lblDataParentPath.setBounds(393, 80, 138, 17);

        dataParentPath = new Text(composite, SWT.BORDER);
        dataParentPath.setTextLimit(100);
        dataParentPath.setBounds(532, 80, 150, 17);
        dataParentPath.addModifyListener(this);

        regionParse = RegionParse.getInstance();

        Label lblProvinceCode = new Label(composite, SWT.NONE);
        lblProvinceCode.setText("接入省份：");
        lblProvinceCode.setBounds(393, 123, 127, 17);

        comboProvince = new Combo(composite, SWT.NONE);
        comboProvince.setBounds(532, 120, 110, 20);
        comboProvince.setItems(regionParse.getProvincesNames());
        comboProvince.addSelectionListener(this);

        Label lblCityCode = new Label(composite, SWT.NONE);
        lblCityCode.setText("接入城市：");
        lblCityCode.setBounds(393, 172, 127, 17);

        comboCity = new Combo(composite, SWT.NONE);
        comboCity.setBounds(532, 169, 110, 20);
        comboCity.addSelectionListener(this);
        comboProvince.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                if (comboProvince.getText() != null && !comboProvince.getText().equals("")) {
                    province = regionParse.getProvinceByName(comboProvince.getText());
                    comboCity.setItems(province.getCitiesNameArray());
                    comboCity.select(0);
                }
            }
        });
        initialRegion();

        Label lbluploadResultType = new Label(composite, SWT.NONE);
        lbluploadResultType.setBounds(393, 220, 127, 17);
        lbluploadResultType.setText("设置结果上传方式：");

        uploadResultType = new Combo(composite, SWT.NONE);
        uploadResultType.setBounds(532, 217, 110, 17);
        uploadResultType.setItems(uploadTypes);
        uploadResultType.select(2);
        uploadResultType.addSelectionListener(this);

        checkBtnSwitch = new Button(composite, SWT.CHECK);
        checkBtnSwitch.setBounds(393, 272, 110, 16);
        checkBtnSwitch.setText("设置连接开关");
        checkBtnSwitch.addSelectionListener(this);
        checkBtnSwitch.setSelection(true);

        btnSave = new Button(composite, SWT.NONE);
        btnSave.addSelectionListener(this);
        btnSave.setBounds(535, 307, 72, 29);
        btnSave.setText("保存");

        lblIp = new Label(composite, SWT.CENTER);
        lblIp.setText("IP：");
        lblIp.setAlignment(SWT.LEFT);
        lblIp.setBounds(34, 319, 138, 17);

        ip = new Text(composite, SWT.BORDER);
        ip.setTextLimit(15);
        ip.setBounds(173, 319, 150, 17);

        downloadTab = new TabItem(tabFolder, SWT.NONE);
        downloadTab.setText("数据同步");
        Composite composite_1 = new Composite(tabFolder, SWT.NONE);
        downloadTab.setControl(composite_1);

        Label label = new Label(composite_1, SWT.NONE);
        label.setBounds(70, 90, 54, 22);
        label.setText("名称");

        Label lblNewLabel = new Label(composite_1, SWT.NONE);
        lblNewLabel.setBounds(148, 90, 102, 22);
        lblNewLabel.setText("最后下载/上传时间");

        Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
        lblNewLabel_1.setBounds(435, 90, 77, 22);
        lblNewLabel_1.setText("是否有新数据");

        lblLastSyncTime = new Label(composite_1, SWT.NONE);
        lblLastSyncTime.setText("最后同步时间");
        lblLastSyncTime.setBounds(297, 90, 102, 22);

        Label lblTask = new Label(composite_1, SWT.NONE);
        lblTask.setFont(SWTResourceManager.getFont("宋体", 9, SWT.NORMAL));
        lblTask.setBounds(70, 134, 72, 22);
        lblTask.setText("拨测任务：");

        lblTaskLastUpdateTime = new Label(composite_1, SWT.NONE);
        lblTaskLastUpdateTime.setBounds(148, 134, 119, 22);

        lblTaskLastSyncTime = new Label(composite_1, SWT.NONE);
        lblTaskLastSyncTime.setBounds(297, 134, 119, 22);

        lblTaskNewDataStatus = new Label(composite_1, SWT.NONE);
        lblTaskNewDataStatus.setAlignment(SWT.CENTER);
        lblTaskNewDataStatus.setText("无");
        lblTaskNewDataStatus.setBounds(446, 134, 54, 22);

        btnDownTask = new Button(composite_1, SWT.NONE);
        btnDownTask.setEnabled(false);
        btnDownTask.setBounds(539, 129, 72, 22);
        btnDownTask.setText("从后端获取");
        btnDownTask.addSelectionListener(this);

        btnSyncTask = new Button(composite_1, SWT.NONE);
        btnSyncTask.setEnabled(false);
        btnSyncTask.setText("同步到手机");
        btnSyncTask.setBounds(617, 129, 72, 22);
        btnSyncTask.addSelectionListener(this);

        Label lblScript = new Label(composite_1, SWT.NONE);
        lblScript.setText("拨测脚本：");
        lblScript.setFont(SWTResourceManager.getFont("宋体", 9, SWT.NORMAL));
        lblScript.setBounds(70, 184, 72, 22);

        lblScriptLastUpdateTime = new Label(composite_1, SWT.NONE);
        lblScriptLastUpdateTime.setBounds(148, 184, 119, 22);

        lblScriptLastSyncTime = new Label(composite_1, SWT.NONE);
        lblScriptLastSyncTime.setBounds(297, 184, 119, 22);

        lblScriptNewDataStatus = new Label(composite_1, SWT.NONE);
        lblScriptNewDataStatus.setText("无");
        lblScriptNewDataStatus.setAlignment(SWT.CENTER);
        lblScriptNewDataStatus.setBounds(446, 184, 54, 22);

        btnDownScript = new Button(composite_1, SWT.NONE);
        btnDownScript.setEnabled(false);
        btnDownScript.setText("从后端获取");
        btnDownScript.setBounds(539, 179, 72, 22);
        btnDownScript.addSelectionListener(this);

        btnSyncScript = new Button(composite_1, SWT.NONE);
        btnSyncScript.setEnabled(false);
        btnSyncScript.setText("同步到手机");
        btnSyncScript.setBounds(617, 179, 72, 22);
        btnSyncScript.addSelectionListener(this);

        Label lblResult = new Label(composite_1, SWT.NONE);
        lblResult.setText("拨测结果：");
        lblResult.setFont(SWTResourceManager.getFont("宋体", 9, SWT.NORMAL));
        lblResult.setBounds(70, 237, 72, 22);

        lblResultLastUpdateTime = new Label(composite_1, SWT.NONE);
        lblResultLastUpdateTime.setBounds(148, 237, 119, 22);

        lblResultLastSyncTime = new Label(composite_1, SWT.NONE);
        lblResultLastSyncTime.setBounds(297, 237, 119, 22);

        lblResultNewDataStatus = new Label(composite_1, SWT.NONE);
        lblResultNewDataStatus.setText("无");
        lblResultNewDataStatus.setAlignment(SWT.CENTER);
        lblResultNewDataStatus.setBounds(446, 237, 54, 22);

        btnUploadResult = new Button(composite_1, SWT.NONE);
        btnUploadResult.setEnabled(false);
        btnUploadResult.setText("上传到后端");
        btnUploadResult.setBounds(539, 232, 72, 22);
        btnUploadResult.addSelectionListener(this);

        btnSyncResult = new Button(composite_1, SWT.NONE);
        btnSyncResult.setEnabled(false);
        btnSyncResult.setText("从手机获取");
        btnSyncResult.setBounds(617, 232, 72, 22);
        btnSyncResult.addSelectionListener(this);

        // changeDevice(0);
        listViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            public void selectionChanged(SelectionChangedEvent arg0) {
                if (selectPosition == list.getSelectionIndex() || list.getSelectionIndex() >= list.getItemCount())
                    return;

                changeDevice(list.getSelectionIndex());
            }
        });
        scrolledComposite.setExpandHorizontal(true); 
        scrolledComposite.setExpandVertical(true); 
        scrolledComposite.setMinSize(container.computeSize(SWT.DEFAULT, SWT.DEFAULT));
        return container;
    }

    /**
     * 初始化数据同步模块
     */
    protected void initialData() {
        if (list.getItemCount() < 0 || selectPosition >= list.getItemCount())
            return;
        if (device != null) {
            if (device.getTaskLastUpdateTime() != null) {
                lblTaskLastUpdateTime.setText(device.getTaskLastUpdateTime());
            } else {
                lblTaskLastUpdateTime.setText("");
            }
            if (device.getTaskLastSyncTime() != null) {
                lblTaskLastSyncTime.setText(device.getTaskLastSyncTime());
            } else {
                lblTaskLastSyncTime.setText("");
            }
            if (device.isTaskHasUpdate()) {
                lblTaskNewDataStatus.setText("有");
                if (list.getItem(selectPosition).contains(StringConstants.CONNECTION_OK)) {
                    btnSyncTask.setEnabled(true);
                } else {
                    btnSyncTask.setEnabled(false);
                }
            } else {
                lblTaskNewDataStatus.setText("无");
                btnSyncTask.setEnabled(false);
            }
            if (device.getScriptLastUpdateTime() != null) {
                lblScriptLastUpdateTime.setText(device.getScriptLastUpdateTime());
            } else {
                lblScriptLastUpdateTime.setText("");
            }
            if (device.getScriptLastSyncTime() != null) {
                lblScriptLastSyncTime.setText(device.getScriptLastSyncTime());
            } else {
                lblScriptLastSyncTime.setText("");
            }
            if (device.isScriptHasUpdate()) {
                lblScriptNewDataStatus.setText("有");
                if (list.getItem(selectPosition).contains(StringConstants.CONNECTION_OK)) {
                    btnSyncScript.setEnabled(true);
                } else {
                    btnSyncScript.setEnabled(false);
                }
            } else {
                lblScriptNewDataStatus.setText("无");
                btnSyncScript.setEnabled(false);
            }
            if (device.getResultLastUploadTime() != null) {
                lblResultLastUpdateTime.setText(device.getResultLastUploadTime());
            } else {
                lblResultLastUpdateTime.setText("");
            }
            if (device.getResultLastSyncTime() != null) {
                lblResultLastSyncTime.setText(device.getResultLastSyncTime());
            } else {
                lblResultLastSyncTime.setText("");
            }
            if (device.isResultHasUpload()) {
                lblResultNewDataStatus.setText("有");
                if (list.getItem(selectPosition).contains(StringConstants.CONNECTION_OK)) {
                    btnUploadResult.setEnabled(true);
                } else {
                    btnUploadResult.setEnabled(false);
                }
            } else {
                lblResultNewDataStatus.setText("无");
                btnUploadResult.setEnabled(false);
            }
            // 初始化下载任务的btn
            if (config.get(MobileManagerConstants.TASK_DOWNLOAD_URL, null) != null
                    && !config.get(MobileManagerConstants.TASK_DOWNLOAD_URL, null).equals("")) {
                btnDownTask.setEnabled(true);
            } else {
                logger.error("Task's properties have some empty! ");
            }
            // 初始化下载脚本的btn
            if (config.get(MobileManagerConstants.TEST_SCRIPT_QUERY_URL, null) != null
                    && !config.get(MobileManagerConstants.TEST_SCRIPT_QUERY_URL, null).equals("")
                    && config.get(MobileManagerConstants.TEST_SCRIPT_FTP_IP, null) != null
                    && !config.get(MobileManagerConstants.TEST_SCRIPT_FTP_IP, null).equals("")
                    && config.get(MobileManagerConstants.TEST_SCRIPT_FTP_PORT, null) != null
                    && !config.get(MobileManagerConstants.TEST_SCRIPT_FTP_PORT, null).equals("")
                    && config.get(MobileManagerConstants.TEST_SCRIPT_FTP_PATH, null) != null
                    && !config.get(MobileManagerConstants.TEST_SCRIPT_FTP_PATH, null).equals("")
                    && config.get(MobileManagerConstants.TEST_SCRIPT_FTP_USER, null) != null
                    && !config.get(MobileManagerConstants.TEST_SCRIPT_FTP_USER, null).equals("")
                    && config.get(MobileManagerConstants.TEST_SCRIPT_FTP_PWD, null) != null
                    && !config.get(MobileManagerConstants.TEST_SCRIPT_FTP_PWD, null).equals("")) {
                btnDownScript.setEnabled(true);
            } else {
                logger.error("Test_script's properties have some empty! ");
                btnDownScript.setEnabled(false);
            }
            // 初始化从手机端同步zip的btn
            if (list.getItem(selectPosition).contains(StringConstants.CONNECTION_OK)) {
                btnSyncResult.setEnabled(true);
            } else {
                btnSyncResult.setEnabled(false);
            }
            for (int i = 0; i < devices.size(); i++) {
                Device dev = devices.get(i);
                if (list.getItem(i).contains(StringConstants.CONNECTION_OK)) {
                    if (dev.isTaskHasUpdate() || dev.isScriptHasUpdate() || dev.isResultHasUpload())
                        list.setItem(i, dev.getDeviceName() + StringConstants.SYN_NEW + StringConstants.CONNECTION_OK);
                    else {
                        list.setItem(i, dev.getDeviceName() + StringConstants.CONNECTION_OK);
                    }
                } else {
                    if (dev.isTaskHasUpdate() || dev.isScriptHasUpdate() || dev.isResultHasUpload())
                        list.setItem(i, dev.getDeviceName() + StringConstants.SYN_NEW);
                    else {
                        list.setItem(i, dev.getDeviceName());
                    }
                }
            }
        } else {
            lblTaskLastSyncTime.setText("");
            lblTaskLastUpdateTime.setText("");
            lblTaskNewDataStatus.setText("");
            btnDownTask.setEnabled(false);
            btnSyncTask.setEnabled(false);
            lblScriptLastSyncTime.setText("");
            lblScriptLastUpdateTime.setText("");
            lblScriptNewDataStatus.setText("");
            btnDownScript.setEnabled(false);
            btnSyncScript.setEnabled(false);
            lblResultLastSyncTime.setText("");
            lblResultLastUpdateTime.setText("");
            lblResultNewDataStatus.setText("");
            btnUploadResult.setEnabled(false);
            btnSyncResult.setEnabled(false);
        }
    }

    /**
     * Create the actions.
     */
    private void createActions() {
        // Create the actions
        {
            actionAbout = new Action("关于") {

                /**
                 * (non-Javadoc)
                 * 
                 * @see org.eclipse.jface.action.Action#run()
                 */
                @Override
                public void run() {
                    if (aboutDialog == null) {
                        aboutDialog = new AboutDialog(MainApplicationWindow.this.getParentShell());
                    }
                    aboutDialog.open();
                }
            };
        }
        {
            // 设备信息查询
            devicesInfoAct = new Action("设备列表") {
                @Override
                public void run() {
                    if (devicesInfoDialog == null) {
                        devicesInfoDialog = new DevicesInfoDialog(MainApplicationWindow.this.getParentShell(),
                                MainApplicationWindow.this);
                    }

                    devicesInfoDialog.open();
                }
            };
        }

        {
            actionSetting = new Action("参数设置") {

                /**
                 * (non-Javadoc)
                 * 
                 * @see org.eclipse.jface.action.Action#run()
                 */
                @Override
                public void run() {
                    if (settingDialog == null) {
                        settingDialog = new SettingDialog(MainApplicationWindow.this.getParentShell());
                    }
                    settingDialog.open();
                }

            };
        }
        {
            actionAddDevice = new Action("新增设备") {

                /**
                 * {@inheritDoc}
                 * 
                 * @see org.eclipse.jface.action.Action#run()
                 */
                @Override
                public void run() {
                    super.run();
                    try {
                        if (changeStatus)
                            changeDevice(selectPosition);
                        addDevice(null);
                    } catch (MException e) {
                        ExceptionHandler.handle(e, "while actionAddDevice to add device");
                    }
                }

            };
        }
        {
            actionDeleteDevice = new Action("删除设备") {

                /**
                 * {@inheritDoc}
                 * 
                 * @see org.eclipse.jface.action.Action#run()
                 */
                @Override
                public void run() {
                    super.run();
                    try {
                        deleteDevice();
                    } catch (MException e) {
                        ExceptionHandler.handle(e, "while delete device xml file");
                    }

                }

            };
            actionDeleteDevice.setEnabled(false);
        }
        {
            actionSync = new Action("同步") {

                /**
                 * {@inheritDoc}
                 * 
                 * @see org.eclipse.jface.action.Action#run()
                 */
                @Override
                public void run() {
                    super.run();
                    try {
                        boolean confirmStatus = MessageDialog.openConfirm(getShell(), "确认信息",
                                "PC客户端当前的参数配置信将同步到手机，是否提交?");
                        if (confirmStatus) {
                            RemoteDevice remoteDevice = remoteDeviceManager.getRemoteDevice(device.getAdbDeviceName());
                            remoteDevice.syncSetSetting(new PropertyManager(device.getAdbDeviceName())
                                    .getMobileSharedFile());
                        }
                    } catch (Exception e) {
                        ExceptionHandler.handle(e, "sync data failed");
                    }
                }

            };
            actionSync.setEnabled(false);
        }
    }

    /**
     * Create the menu manager.
     * 
     * @return the menu manager
     */
    @Override
    protected MenuManager createMenuManager() {
        MenuManager menuManager = new MenuManager("menu");
        menuManager.setActionDefinitionId("");
        menuManager.add(actionSetting);
        menuManager.add(devicesInfoAct);
        menuManager.add(actionAbout);

        return menuManager;
    }

    /**
     * Create the toolbar manager.
     * 
     * @return the toolbar manager
     */
    @Override
    protected ToolBarManager createToolBarManager(int style) {
        ToolBarManager toolBarManager = new ToolBarManager(style);
        toolBarManager.add(actionAddDevice);
        toolBarManager.add(actionDeleteDevice);
        toolBarManager.add(actionSync);
        return toolBarManager;
    }

    /**
     * Create the status line manager.
     * 
     * @return the status line manager
     */
    @Override
    protected StatusLineManager createStatusLineManager() {
        StatusLineManager statusLineManager = new StatusLineManager();
        return statusLineManager;
    }

    /**
     * Configure the shell.
     * 
     * @param newShell
     */
    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText("New Application");
    }

    /**
     * Return the initial size of the window.
     */
    @Override
    protected Point getInitialSize() {
        return new Point(800, 554);
    }

    /**
     * 设置区域
     */
    private void initialRegion() {
        String provinceCode, cityCode;
        provinceCode = device.getProvinceCode();
        cityCode = device.getCityCode();
        if (provinceCode == null || provinceCode.equals("")) {
            comboProvince.setText("");
            comboCity.setText("");
            comboCity.removeAll();
            return;
        }
        province = regionParse.getProvinceByCode(Integer.parseInt(provinceCode));
        for (int i = 0; i < regionParse.getProvincesNames().length; i++) {
            if (regionParse.getProvincesNames()[i].equals(province.getName())) {
                comboProvince.select(i);
                comboCity.setItems(province.getCitiesNameArray());
                break;
            }
        }
        city = province.getCityByUniqueCode(Integer.parseInt(cityCode));
        if (city == null)
            return;
        for (int i = 0; i < province.getCitiesNameArray().length; i++) {
            if (province.getCitiesNameArray()[i].equals(city.getName())) {
                comboCity.select(i);
                break;
            }
        }
    }

    /**
     * 选中左边list，更新右边私有数据
     * 
     * @param position
     */
    public void changeDeviceByName(String name) {
        int changeIndex = -1;
        for (int i = 0; i < devices.size(); i++) {
            if (devices.get(i).getDeviceName().equals(name))
                changeIndex = i;
        }
        changeDevice(changeIndex);
    }

    /**
     * 选择右边数据同步Tab 页面
     */
    public void selectionDownladTab() {
        if (tabFolder != null && downloadTab != null)
            tabFolder.setSelection(downloadTab);
    }

    /**
     * 点击左边list，更新右边私有数据
     * 
     * @param position
     */
    public void changeDevice(int position) {
        if (list.getItemCount() <= 0 || position < 0 || position >= list.getItemCount())
            return;
        if (changeStatus) {
            boolean confirmChange = MessageDialog.openConfirm(getShell(), "确认信息", "是否对刚才的修改进行保存！");
            if (confirmChange) {
                try {
                    updateDevice(selectPosition);
                } catch (MException e) {
                    ExceptionHandler.handle(e, "select other listitem and update device");
                }
            }
            deviceSettingTab.setText("终端信息");
        }
        if (list.getItem(position).contains(StringConstants.CONNECTION_OK)) {
            actionSync.setEnabled(true);
        } else {
            actionSync.setEnabled(false);
        }
        actionDeleteDevice.setEnabled(true);
        menuItemDelete.setEnabled(true);
        changeStatus = false;
        selectPosition = position;
        if (list.getItemCount() > 0) {
            list.select(position);
            device = devices.get(position);
            initialDeviceInfo();
        }
    }

    private boolean checkForm() {
        if (deviceName.getText().equals("")) {
            MessageDialog.openWarning(getShell(), "警告", "设备名称不允许为空，请重新输入！");
            deviceName.setFocus();
            return false;
        } else if (model.getText().equals("")) {
            MessageDialog.openWarning(getShell(), "警告", "设备型号不允许为空，请重新输入！");
            model.setFocus();
            return false;
        } else if (imei.getText().equals("")) {
            MessageDialog.openWarning(getShell(), "警告", "手机IMEI不允许为空，请重新输入！");
            imei.setFocus();
            return false;
        } else if (comboProvince.getText().equals("")) {
            MessageDialog.openWarning(getShell(), "警告", "省份不允许为空，请重新选择！");
            comboProvince.setFocus();
            return false;
        } else if (comboCity.getText().equals("")) {
            MessageDialog.openWarning(getShell(), "警告", "城市不允许为空，请重新选择！");
            comboCity.setFocus();
            return false;
        } else if (dataParentPath.getText().equals("")) {
            MessageDialog.openWarning(getShell(), "警告", "测试数据目录不允许为空，请重新输入！");
            dataParentPath.setFocus();
            return false;
        } 
        return true;
    }

    /**
     * 添加设备
     * 
     * @param device
     *            设备
     * @throws MException
     */
    private boolean addDevice(String adbName) throws MException {
        UUID uuid = UUID.randomUUID();
        String folderName = String.valueOf(uuid);
        Device dev = new Device();
        dev.setFolderName(folderName);
        try {
            if (adbName != null) {
                dev.setAdbDeviceName(adbName);
                MobileSharedFile mobileSharedFile = null;
                try {
                    RemoteDevice remoteDevice = remoteDeviceManager.getRemoteDevice(adbName);
                    mobileSharedFile = remoteDevice.syncGetSetting();
                } catch (IOException e) {
                    logger.error("Get mobile setting failed, and tbe service maybe is not starting");
                } catch (MException e) {
                    logger.error("Get mobile setting failed, and tbe service maybe is not starting");
                }
                if (mobileSharedFile != null && mobileSharedFile.getDeviceName() != null
                        && !mobileSharedFile.getDeviceName().equals("")) {
                    if (checkDevice(mobileSharedFile.getDeviceName())) {
                        PropertyManager preferenceManager = new PropertyManager(null);
                        dev = preferenceManager.initialDevice(mobileSharedFile);
                        dev.setAdbDeviceName(adbName);
                        dev.setFolderName(folderName);
                    } else {
                        MessageDialog.openError(getShell(), "错误", "此设备名称已存在！");
                        return false;
                    }
                } else {
                    logger.debug("while sync settings from mobile to pc failed");
                    InputDialog input = new InputDialog(getShell(), "Input Dialog Title", "Please input a deviceName:",
                            "", null);
                    if (input.open() == Window.OK) {
                        if (input.getValue() == null || input.getValue().equals(""))
                            return false;
                        dev.setDeviceName(input.getValue());
                        dev.setResultUploadType("3");
                        dev.setConnSwitch(true);
                    } else {
                        return false;
                    }
                }
            } else {
                InputDialog input = new InputDialog(getShell(), "新增设备", "请输入设备名称:", "", null);
                if (input.open() == Window.OK) {
                    if (input.getValue() == null || input.getValue().equals(""))
                        return false;
                    dev.setDeviceName(input.getValue());
                    dev.setResultUploadType("3");
                    dev.setConnSwitch(true);
                } else {
                    return false;
                }
            }
            if (checkDevice(dev.getDeviceName())) {
            } else {
                MessageDialog.openError(getShell(), "错误", "此设备名称已存在！");
                return false;
            }
            deviceManager.saveDevice(dev);
            device = dev;
            list.add(device.getDeviceName(), devices.size() - 1);
            list.select(devices.size() - 1);
            selectPosition = devices.size() - 1;
            initialDeviceInfo();
            changeStatus = false;
            actionDeleteDevice.setEnabled(true);
            menuItemDelete.setEnabled(true);
            return true;
        } catch (MException e) {
            MException mexception = ExceptionHandler.handle(e, "while action add device ");
            if (mexception != null) {
                throw mexception;
            }
        }
        return false;
    }

    private boolean checkDevice(String deviceName) {
        for (Device device : devices) {
            if (device.getDeviceName().equals(deviceName.trim())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 更新设备
     * 
     * @throws MException
     */
    private void updateDevice(int position) throws MException {
        if (!checkForm())
            return;
        device.setDeviceName(deviceName.getText());
        device.setMobileNum(mobileNum.getText());
        device.setModel(model.getText());
        device.setImei(imei.getText());
        device.setIp(ip.getText());
        device.setDataCollFreq(dataCollFreq.getText());
        device.setSmsControlNum(smsConNum.getText());
        device.setDataParentPath(dataParentPath.getText());
        String provinceName = comboProvince.getText();
        if (provinceName != null && !provinceName.equals("")) {
            province = regionParse.getProvinceByName(provinceName);
            device.setProvinceCode(String.valueOf(province.getCode()));
            String cityName = comboCity.getText();
            if (cityName != null && !cityName.equals("")) {
                city = province.getCityByName(cityName);
                device.setCityCode(String.valueOf(city.getUnique_code()));
            }
        }
        device.setResultUploadType(String.valueOf(uploadResultType.getSelectionIndex() + 1));
        device.setConnSwitch(checkBtnSwitch.getSelection());
        deviceManager.updateDevice(position, device);
        String devName = device.getDeviceName();
        if (list.getItem(position).contains(StringConstants.CONNECTION_OK))
            devName = devName + StringConstants.CONNECTION_OK;
        if (list.getItem(position).contains(StringConstants.SYN_NEW))
            devName = devName + StringConstants.SYN_NEW;
        list.setItem(position, devName);
        MessageDialog.openInformation(getShell(), "提示", "终端信息保存成功！");
    }

    /**
     * 删除设备
     */
    private void deleteDevice() throws MException {

        int position = list.getFocusIndex();
        boolean delete = MessageDialog.openQuestion(getShell(), "提示", "确认要删除此设备信息！");
        if (delete) {
            changeStatus = false;
            deviceManager.deleteDevice(position, device);
            list.remove(position);
            if (list.getItemCount() > 0) {
                list.setSelection(0);
                changeDevice(0);
            } else {
                device = new Device();
                actionDeleteDevice.setEnabled(false);
                menuItemDelete.setEnabled(false);
                actionSync.setEnabled(false);
                initialDeviceInfo();
            }
        }
    }

    /**
     * 设置右侧设备私有数据
     */
    private void initialDeviceInfo() {
        deviceName.setText(device.getDeviceName() != null ? device.getDeviceName() : "");
        mobileNum.setText(device.getMobileNum() != null ? device.getMobileNum() : "");
        model.setText(device.getModel() != null ? device.getModel() : "");
        imei.setText(device.getImei() != null ? device.getImei() : "");
        ip.setText(device.getIp() != null ? device.getIp() : "");
        dataCollFreq.setText(device.getDataCollFreq() != null ? device.getDataCollFreq() : "");
        smsConNum.setText(device.getSmsControlNum() != null ? device.getSmsControlNum() : "");
        dataParentPath.setText(device.getDataParentPath() != null ? device.getDataParentPath() : "");
        uploadResultType
                .select(device.getResultUploadType() != null ? Integer.parseInt(device.getResultUploadType()) - 1 : 0);
        checkBtnSwitch.setSelection(device.isConnSwitch());
        initialRegion();
        initialData();
    }

    @Override
    public void modifyText(ModifyEvent me) {
        Text text = (Text) me.getSource();
        if (text.isFocusControl())
            isChanged();
    }

    @Override
    public void widgetDefaultSelected(SelectionEvent se) {
    }

    class ProcessThread implements IRunnableWithProgress {
        private final int DOWNLOAD_TASK = 0;
        private final int SYNC_TASK = 1;
        private final int DOWNLOAD_SCRIPT = 2;
        private final int SYNC_SCRIPT = 3;
        private final int UPLOAD_RESULT = 4;
        private final int SYNC_RESULT = 5;
        /**
         * 运行的种类
         */
        private int type;

        public ProcessThread(int type) {
            this.type = type;
        }

        @Override
        public void run(IProgressMonitor arg0) throws InvocationTargetException, InterruptedException {
            getShell().getDisplay().syncExec(new Runnable() {

                @Override
                public void run() {
                    syncService.setDevice(device);
                    syncService.setShell(MainApplicationWindow.this.getShell());
                    switch (type) {
                    case DOWNLOAD_TASK:
                        try {
                            syncService.downloadTask(selectPosition);
                        } catch (MException e) {
                            ExceptionHandler.handle(e, "click btn to down task");
                        }
                        break;
                    case SYNC_TASK:
                        try {
                            syncService.syncTask(selectPosition);
                        } catch (MException e) {
                            ExceptionHandler.handle(e, "click btn to sync task");
                        }
                        break;
                    case DOWNLOAD_SCRIPT:
                        try {
                            syncService.downloadCase(selectPosition);
                        } catch (MException e) {
                            ExceptionHandler.handle(e, "click btn to down script");
                        }
                        break;
                    case SYNC_SCRIPT:
                        try {
                            syncService.syncScript(selectPosition);
                        } catch (MException e) {
                            ExceptionHandler.handle(e, "click btn to sync script");
                        }
                        break;
                    case UPLOAD_RESULT:
                        try {
                            syncService.uploadResult(selectPosition);
                        } catch (MException e) {
                            ExceptionHandler.handle(e, "click btn to upload result");
                        }
                        break;
                    case SYNC_RESULT:
                        try {
                            RemoteDevice remoteDevice = remoteDeviceManager.getRemoteDevice(device.getAdbDeviceName());
                            GResultResponse resultResponse = remoteDevice.syncGetResult("1");
                            logger.debug("Inquire whether the mobile is having result file need to upload, the response is "
                                    + resultResponse.isHaveFile());
                            if (resultResponse.isHaveFile()) {
                                boolean syncResultStatus = syncService.syncResult(selectPosition);
                                if (syncResultStatus) {
                                    resultResponse = remoteDevice.syncGetResult("2");
                                    logger.debug("Delete result in mobile,the response is "
                                            + resultResponse.isDeleteResult());
                                }
                            } else {
                                MessageDialog.openInformation(getShell(), "提示", "终端指定目录下没有需要同步的文件，请查看后再操作！");
                            }
                        } catch (IOException e) {
                            ExceptionHandler.handle(e, "sync result file from mobile,while send order to delete file");
                        } catch (MException e) {
                            ExceptionHandler.handle(e, "sync result file from mobile,while click btn");
                        }
                        break;
                    }
                }
            });

        }

    }

    @Override
    public void widgetSelected(SelectionEvent se) {
        if (se.getSource().equals(btnSave)) {
            try {
                updateDevice(selectPosition);
                changeStatus = false;
                deviceSettingTab.setText("终端信息");
            } catch (MException e) {
                ExceptionHandler.handle(e, "save btn to save device info");
            }
            return;
        } else if (se.getSource().equals(btnDownTask)) {
            ProgressMonitorDialog progress = new ProgressMonitorDialog(getShell());
            try {
                progress.run(true, true, new ProcessThread(0));
                initialData();
            } catch (InvocationTargetException e) {
                ExceptionHandler.handle(e, "open ProgressMonitorDialog to download task");
            } catch (InterruptedException e) {
                ExceptionHandler.handle(e, "open ProgressMonitorDialog to download task");
            }
            return;
        } else if (se.getSource().equals(btnSyncTask)) {
            ProgressMonitorDialog progress = new ProgressMonitorDialog(getShell());
            try {
                progress.run(true, true, new ProcessThread(1));
                initialData();
            } catch (InvocationTargetException e) {
                ExceptionHandler.handle(e, "open ProgressMonitorDialog to sync task");
            } catch (InterruptedException e) {
                ExceptionHandler.handle(e, "open ProgressMonitorDialog to sync task");
            }
            return;
        } else if (se.getSource().equals(btnDownScript)) {
            ProgressMonitorDialog progress = new ProgressMonitorDialog(getShell());
            try {
                progress.run(true, true, new ProcessThread(2));
                initialData();
            } catch (InvocationTargetException e) {
                ExceptionHandler.handle(e, "open ProgressMonitorDialog to download script");
            } catch (InterruptedException e) {
                ExceptionHandler.handle(e, "open ProgressMonitorDialog to download script");
            }
            return;
        } else if (se.getSource().equals(btnSyncScript)) {
            ProgressMonitorDialog progress = new ProgressMonitorDialog(getShell());
            try {
                progress.run(true, true, new ProcessThread(3));
                initialData();
            } catch (InvocationTargetException e) {
                ExceptionHandler.handle(e, "open ProgressMonitorDialog to sync script");
            } catch (InterruptedException e) {
                ExceptionHandler.handle(e, "open ProgressMonitorDialog to sync script");
            }
            return;
        } else if (se.getSource().equals(btnUploadResult)) {
            ProgressMonitorDialog progress = new ProgressMonitorDialog(getShell());
            try {
                progress.run(true, true, new ProcessThread(4));
                initialData();
            } catch (InvocationTargetException e) {
                ExceptionHandler.handle(e, "open ProgressMonitorDialog to upload result");
            } catch (InterruptedException e) {
                ExceptionHandler.handle(e, "open ProgressMonitorDialog to upload result");
            }
            return;
        } else if (se.getSource().equals(btnSyncResult)) {
            ProgressMonitorDialog progress = new ProgressMonitorDialog(getShell());
            try {
                progress.run(true, true, new ProcessThread(5));
                initialData();
            } catch (InvocationTargetException e) {
                ExceptionHandler.handle(e, "open ProgressMonitorDialog to sync result");
            } catch (InterruptedException e) {
                ExceptionHandler.handle(e, "open ProgressMonitorDialog to sync result");
            }
            return;
        } else if (se.getSource().equals(checkBtnSwitch) && !checkBtnSwitch.isFocusControl()) {
            return;
        } else if (se.getSource().equals(uploadResultType) && !uploadResultType.isFocusControl()) {
            return;
        }
        isChanged();
    }

    /**
     * 判断表格内容是否有修改
     */
    private void isChanged() {
        if (device.getProvinceCode() != null && !device.getProvinceCode().equals("")) {
            province = regionParse.getProvinceByCode(Integer.parseInt(device.getProvinceCode()));
            if (province != null && device.getCityCode() != null && !device.getCityCode().equals("")) {
                city = province.getCityByUniqueCode(Integer.parseInt(device.getCityCode()));
            }
        }
        if (!deviceName.getText().equals(device.getDeviceName())) {
            changeStatus = true;
        } else if (!mobileNum.getText().equals(device.getMobileNum())) {
            changeStatus = true;
        } else if (!model.getText().equals(device.getModel())) {
            changeStatus = true;
        } else if (!imei.getText().equals(device.getImei())) {
            changeStatus = true;
        } else if (!dataCollFreq.getText().equals(device.getDataCollFreq())) {
            changeStatus = true;
        } else if (!smsConNum.getText().equals(device.getSmsControlNum())) {
            changeStatus = true;
        } else if (!dataParentPath.getText().equals(device.getDataParentPath())) {
            changeStatus = true;
        } else if ((province != null && !comboProvince.getText().equals(province.getName()))
                || (province == null && comboProvince.getText() != null && !comboProvince.getText().equals(""))) {
            changeStatus = true;
        } else if ((city != null && !comboCity.getText().equals(city.getName()))
                || (city == null && comboCity.getText() != null && !comboCity.getText().equals(""))) {
            changeStatus = true;
        } else if (!String.valueOf(uploadResultType.getSelectionIndex() + 1).equals(device.getResultUploadType())) {
            changeStatus = true;
        } else if (checkBtnSwitch.getSelection() != device.isConnSwitch()) {
            changeStatus = true;
        } else {
            changeStatus = false;
        }
        if (changeStatus) {
            deviceSettingTab.setText("终端信息*");
        } else {
            deviceSettingTab.setText("终端信息");
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

    private int getAdbDevice(String adbDeviceName) {
        for (int i = 0; i < list.getItemCount(); i++) {
            Device dev = devices.get(i);
            if (dev.getAdbDeviceName() != null && dev.getAdbDeviceName().equals(adbDeviceName)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onAddDevice(final RemoteDevice addRemoteDevice) {
        logger.info("addRemoteDevice.adbname = " + addRemoteDevice.getAdbDeviceName() + "； stateOnline is " + addRemoteDevice.isAdbStateOnline());
        adbDeviceName = addRemoteDevice.getAdbDeviceName();
        if (!addRemoteDevice.isAdbStateOnline()) {
            logger.debug("device's addDeviceName that is " + addRemoteDevice.getAdbDeviceName()
                    + ",but status is not online");
            return;
        }
        getShell().getDisplay().syncExec(new Runnable() {

            @Override
            public void run() {
                if (devices.size() == 0) {
                    boolean confirmStatus = MessageDialog.openConfirm(MainApplicationWindow.this.getShell(), "提示",
                            "是否需要创建新设备？");
                    logger.debug("mobile status is " + confirmStatus);
                    if (confirmStatus) {
                        try {
                            if (addDevice(addRemoteDevice.getAdbDeviceName())) {
                                list.setItem(0, devices.get(0).getDeviceName() + StringConstants.CONNECTION_OK);
                                actionSync.setEnabled(true);
                            }
                        } catch (MException e) {
                            ExceptionHandler.handle(e, "while onAdddevice to add device");
                        }
                    }
                } else {
                    int pos = getAdbDevice(addRemoteDevice.getAdbDeviceName());
                    if (pos >= 0) {
                        Device dev = devices.get(pos);
                        try {
                            RemoteDevice remoteDevice = remoteDeviceManager.getRemoteDevice(dev.getAdbDeviceName());
                            final String mobileScriptLastUpdateTime = remoteDevice.syncGetCase(1);
                            if (mobileScriptLastUpdateTime != null && !mobileScriptLastUpdateTime.equals("")) {
                                dev.setMobileScriptLastUpdateTime(mobileScriptLastUpdateTime);
                                if (dev.getpCScriptLastUpdateTime() != null
                                        && mobileScriptLastUpdateTime.compareTo(dev.getpCScriptLastUpdateTime()) >= 0) {
                                    dev.setScriptHasUpdate(false);
                                }
                                deviceManager.updateDevice(pos, dev);
                            }
                        } catch (IOException e) {
                            logger.error("Get mobile scriptLastUpdateTime failed, and tbe service maybe is not starting");
                        } catch (MException e) {
                            logger.error("Get mobile scriptLastUpdateTime failed, and tbe service maybe is not starting");
                        }
                        if (devices.get(pos).isTaskHasUpdate() || devices.get(pos).isScriptHasUpdate()
                                || devices.get(pos).isResultHasUpload())
                            list.setItem(pos, devices.get(pos).getDeviceName() + StringConstants.SYN_NEW
                                    + StringConstants.CONNECTION_OK);
                        else
                            list.setItem(pos, devices.get(pos).getDeviceName() + StringConstants.CONNECTION_OK);
                        if (selectPosition == pos) {
                            actionSync.setEnabled(true);
                        }
                    } else {
                        CreateOrSelectDialog createOrSelectDialog = new CreateOrSelectDialog(MainApplicationWindow.this
                                .getParentShell());
                        createOrSelectDialog.setClickListener(MainApplicationWindow.this);
                        createOrSelectDialog.open();
                    }
                }
            }
        });
    }

    @Override
    public void onRemoveDevice(final RemoteDevice addRemoteDevice) {
        logger.debug("remove:           addRemoteDevice.adbname = " + addRemoteDevice.getAdbDeviceName());
        getShell().getDisplay().asyncExec(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < devices.size(); i++) {
                    if (devices.get(i).getAdbDeviceName() != null && !devices.get(i).getAdbDeviceName().equals("")
                            && devices.get(i).getAdbDeviceName().equals(addRemoteDevice.getAdbDeviceName())) {
                        list.setItem(i, list.getItem(i).replace(StringConstants.CONNECTION_OK, ""));
                        if (selectPosition == i) {
                            actionSync.setEnabled(false);
                        }
                        initialData();
                    }
                }
            }
        });
    }

    @Override
    public void onDeviceAdbStateChange(RemoteDevice remoteDevice, DeviceAdbState oldState, DeviceAdbState state) {
        int status = 0;
        if (oldState.equals(DeviceAdbState.ONLINE) && !state.equals(DeviceAdbState.ONLINE)) {
            status = 1;
        } else if (!oldState.equals(DeviceAdbState.ONLINE) && state.equals(DeviceAdbState.ONLINE)) {
            status = 2;
        }
        if (status == 0)
            return;
        for (int i = 0; i < devices.size(); i++) {
            if (devices.get(i).getAdbDeviceName() != null
                    && devices.get(i).getAdbDeviceName().equals(remoteDevice.getAdbDeviceName())) {
                if (status == 1) {
                    list.setItem(i, list.getItem(i).replace(StringConstants.CONNECTION_OK, ""));
                } else if (status == 2) {
                    if (devices.get(i).isTaskHasUpdate() || devices.get(i).isScriptHasUpdate()
                            || devices.get(i).isResultHasUpload())
                        list.setItem(i, devices.get(i).getDeviceName() + StringConstants.SYN_NEW
                                + StringConstants.CONNECTION_OK);
                    else
                        list.setItem(i, devices.get(i).getDeviceName() + StringConstants.CONNECTION_OK);
                }

            }
        }
    }

    @Override
    public void onDeviceMapChange(Map<String, RemoteDevice> deviceMap) {
        // TODO
    }

    @Override
    public void onOkButtonClick(int position) {
        list.select(position);
        changeDevice(position);
    }

    @Override
    public void onDialogButtonClick(int buttonId) {
        switch (buttonId) {
        case CreateOrSelectDialog.CREATE_BUTTON:
            try {
                if (addDevice(adbDeviceName)) {
                    int pos = getAdbDevice(adbDeviceName);
                    list.setItem(pos, devices.get(pos).getDeviceName() + StringConstants.CONNECTION_OK);
                    actionSync.setEnabled(true);
                }
            } catch (MException e) {
                ExceptionHandler.handle(e, "onDialogButtonClick to add device");
            }
            break;
        case CreateOrSelectDialog.SELECT_BUTTON:
            ListDialog dialog = new ListDialog(MainApplicationWindow.this.getParentShell());
            dialog.setDevices(devices);
            dialog.setDeviceManager(deviceManager);
            dialog.setAdbDeviceName(adbDeviceName);
            dialog.setClickListener(MainApplicationWindow.this);
            dialog.open();
            break;
        }
        adbDeviceName = null;

    }

    @Override
    public boolean close() {
        RemoteDeviceManager.getInstance().destory();
        return super.close();
    }
}
