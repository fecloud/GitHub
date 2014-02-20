/**
 * @(#) DevicesInfoDialog.java Created on 2012-8-9
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;
import com.aspire.common.util.CastUtil;
import com.aspire.sqmp.mobilemanager.entity.Device;
import com.aspire.sqmp.mobilemanager.region.City;
import com.aspire.sqmp.mobilemanager.region.Province;
import com.aspire.sqmp.mobilemanager.region.RegionParse;
import com.aspire.sqmp.mobilemanager.service.DeviceManager;

/**
 * The class <code>DevicesInfoDialog</code>
 * 
 * @author wuyanlong
 * @version 1.0
 */
public class DevicesInfoDialog extends Dialog {
    /**
     * 对话框中间区域
     */
    private Composite dialogContainer;
    /**
     * MainApplicationWindow
     */
    private MainApplicationWindow mainApplicationWindow;
    /**
     * Table
     */
    private Table table;
    /**
     * The manager of device
     */
    private DeviceManager deviceManager;
    /**
     * List of device
     */
    private List<Device> deviceList;

    /**
     * Constructor
     * 
     * @param parentShell
     */
    protected DevicesInfoDialog(Shell parentShell, MainApplicationWindow mainApplicationWindow) {
        super(parentShell);
        setShellStyle(SWT.MAX | SWT.RESIZE | SWT.TITLE | SWT.PRIMARY_MODAL);
        deviceManager = DeviceManager.getInstance();
        this.mainApplicationWindow = mainApplicationWindow;
    }

 
    /**
     * 
     * (non-Javadoc)
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    @Override
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("设备列表");
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        dialogContainer = (Composite) super.createDialogArea(parent);
        dialogContainer.setLayout(new FillLayout(SWT.HORIZONTAL));

        table = new Table(dialogContainer, SWT.BORDER | SWT.FULL_SELECTION);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        table.setFont(SWTResourceManager.getFont("宋体", 9, SWT.NORMAL));
        table.addListener(SWT.MeasureItem, new Listener() {
            public void handleEvent(Event event) {
                event.height = 18;
            }
        });
        table.addListener(SWT.MouseDoubleClick, new Listener() {
            @Override
            public void handleEvent(Event event) {
                TableItem[] itemList = table.getItems();
                int listHaveChouse = table.getSelectionIndex();
                String deviceName = itemList[listHaveChouse].getText(0);
                choseDevice(deviceName);
            }
        });
        TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn.setAlignment(SWT.CANCEL);
        tblclmnNewColumn.setText("设备名称");
        tblclmnNewColumn.setWidth(100);

        TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn_1.setWidth(85);
        tblclmnNewColumn_1.setText("IMEI");

        TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn_2.setWidth(55);
        tblclmnNewColumn_2.setText("省份");

        TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn_3.setWidth(60);
        tblclmnNewColumn_3.setText("城市");

        TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn_4.setWidth(60);
        tblclmnNewColumn_4.setText("新脚本");

        TableColumn tblclmnNewColumn_5 = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn_5.setWidth(130);
        tblclmnNewColumn_5.setText("脚本最后更新时间");

        TableColumn tblclmnNewColumn_6 = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn_6.setWidth(60);
        tblclmnNewColumn_6.setText("新任务");

        TableColumn tblclmnNewColumn_7 = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn_7.setWidth(130);
        tblclmnNewColumn_7.setText("任务最后更新时间");

        TableColumn tblclmnNewColumn_8 = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn_8.setWidth(60);
        tblclmnNewColumn_8.setText("新结果");

        TableColumn tblclmnNewColumn_9 = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn_9.setWidth(130);
        tblclmnNewColumn_9.setText("结果最后上传时间");

        tblclmnNewColumn_1.setAlignment(SWT.CENTER);
        tblclmnNewColumn_2.setAlignment(SWT.CENTER);
        tblclmnNewColumn_3.setAlignment(SWT.CENTER);
        tblclmnNewColumn_4.setAlignment(SWT.CENTER);
        tblclmnNewColumn_5.setAlignment(SWT.CENTER);
        tblclmnNewColumn_6.setAlignment(SWT.CENTER);
        tblclmnNewColumn_7.setAlignment(SWT.CENTER);
        tblclmnNewColumn_8.setAlignment(SWT.CENTER);
        tblclmnNewColumn_9.setAlignment(SWT.CENTER);

        refreshData();
        return dialogContainer;
    }

    /**
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createButton(org.eclipse.swt.widgets.Composite, int, java.lang.String,
     *      boolean)
     */
    @Override
    protected Button createButton(Composite parent, int id, String label, boolean defaultButton) {
        return null;
    }

    /**
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createButtonBar(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createButtonBar(Composite parent) {
        return null;
    }

    /**
     * Return the initial size of the dialog.
     */
    @Override
    protected Point getInitialSize() {
        return new Point(900, 300);
    }

    /** -------------------------view end ------------------------ */

    /**
     * Refresh data
     */
    private void refreshData() {
        // System.out.println("DevicesInfoDialog refresh data.");
        if (table == null)
            return;
        table.removeAll();
        deviceList = deviceManager.getDeviceList();
        for (Device device : deviceList) {
            addDeviceInfo(device);
        }
    }

    /**
     * Add a device
     * 
     * @param device
     */
    public void addDeviceInfo(Device device) {
        TableItem deviceItem = new TableItem(table, SWT.NONE | SWT.CENTER);
        String[] textItem = new String[table.getColumnCount()];
        // 0:设备名称
        // 1:IMEI
        // 2: 省份
        // 3:地市
        // 4: 脚本信息 新脚本
        // 5:最后更新时间
        // 6:任务信息 新任务
        // 7:最后更新时间
        // 8:拨测结果 新结果
        // 9:最后更新时间
        textItem[0] = device.getDeviceName();// 设备名称
        textItem[1] = device.getImei();// IMEI
        final RegionParse regionParse = RegionParse.getInstance();
        Province province = null;
        City city = null;
        try {
            province = regionParse.getProvinceByCode(CastUtil.toInteger(device.getProvinceCode(), 0));
            city = province.getCityByUniqueCode(CastUtil.toInteger(device.getCityCode(), 0));
        } catch (Exception e) {
        }
        textItem[2] = province == null ? "" : province.getName();// 省份
        textItem[3] = city == null ? "" : city.getName();// 地市
        textItem[4] = device.isScriptHasUpdate() ? "有" : "-"; // 脚本信息 新脚本
        textItem[5] = device.getScriptLastUpdateTime();// 脚本最后更新时间
        textItem[6] = device.isTaskHasUpdate() ? "有" : "-";// 任务信息 新任务
        textItem[7] = device.getTaskLastUpdateTime();// 任务最后更新时间
        textItem[8] = device.isResultHasUpload() ? "有" : "-";// 拨测结果 新结果
        textItem[9] = device.getResultLastUploadTime();// 拨测结果最后更新时间
        // textItem[9] = "2008-08-01 23:12:11";// 拨测结果最后更新时间
        deviceItem.setText(textItem);
    }

    /**
     * @param deviceName
     */
    protected void choseDevice(String deviceName) {
        mainApplicationWindow.changeDeviceByName(deviceName);
        mainApplicationWindow.selectionDownladTab();
        close();
    }

}
