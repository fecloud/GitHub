/**
 * @(#) ListDialog.java Created on 2012-8-1
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.aspire.common.exception.ExceptionHandler;
import com.aspire.common.exception.MException;
import com.aspire.common.util.StringConstants;
import com.aspire.sqmp.mobilemanager.entity.Device;
import com.aspire.sqmp.mobilemanager.listener.DialogClickListener;
import com.aspire.sqmp.mobilemanager.service.DeviceManager;

import java.util.List;

/**
 * The class <code>ListDialog</code>
 * 
 * @author likai
 * @version 1.0
 */
public class ListDialog extends Dialog{
    
    /**
     * 对话框中间区域
     */
    private Composite dialogContainer;

    /**
     * 存储设备信息
     */
    private List<Device> devices;
    
    /**
     * 保存用的空间
     */
    private DeviceManager deviceManager;
    
    /**
     * 设备的adbname
     */
    private String adbDeviceName;
    
    /**
     * 显示列表控件
     */
    private org.eclipse.swt.widgets.List list;
    
    private DialogClickListener clickListener;
    
    protected ListDialog(Shell parentShell) {
        super(parentShell);
    }
    /**
     * 
     * (non-Javadoc)
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    @Override
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("请关联本地设备");
    }

    /**
     * Getter of deviceMap
     * @return the deviceMap
     */
    public List<Device> getDevices() {
        return devices;
    }

    /**
     * Setter of deviceMap
     * @param deviceMap the deviceMap to set
     */
    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    /**
     * Getter of deviceManager
     * @return the deviceManager
     */
    public DeviceManager getDeviceManager() {
        return deviceManager;
    }

    /**
     * Setter of deviceManager
     * @param deviceManager the deviceManager to set
     */
    public void setDeviceManager(DeviceManager deviceManager) {
        this.deviceManager = deviceManager;
    }

    /**
     * Getter of adbDeviceName
     * @return the adbDeviceName
     */
    public String getAdbDeviceName() {
        return adbDeviceName;
    }

    /**
     * Setter of adbDeviceName
     * @param adbDeviceName the adbDeviceName to set
     */
    public void setAdbDeviceName(String adbDeviceName) {
        this.adbDeviceName = adbDeviceName;
    }

    /**
     * Getter of clickListener
     * @return the clickListener
     */
    public DialogClickListener getClickListener() {
        return clickListener;
    }

    /**
     * Setter of clickListener
     * @param clickListener the clickListener to set
     */
    public void setClickListener(DialogClickListener clickListener) {
        this.clickListener = clickListener;
    }

    /**
     * {@inheritDoc}
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        dialogContainer = (Composite) super.createDialogArea(parent);
        dialogContainer.setLayout(null);
        
        ListViewer listViewer = new ListViewer(dialogContainer, SWT.BORDER | SWT.V_SCROLL);
        list = listViewer.getList();
        list.setBounds(10, 10, 232, 204);
        FormData fd_list = new FormData();
        fd_list.bottom = new FormAttachment(100, -1);
        fd_list.right = new FormAttachment(0, 200);
        fd_list.top = new FormAttachment(0);
        fd_list.left = new FormAttachment(0);
        for(Device dev : devices){
            if(dev.getAdbDeviceName() == null || dev.getAdbDeviceName().equals("")){
                list.add(dev.getDeviceName());
            }
        }
        return dialogContainer;
    }
    
    /**
     * Create contents of the button bar.
     * 
     * @param parent
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, StringConstants.OK, true);
        createButton(parent, IDialogConstants.CANCEL_ID, StringConstants.CANCEL, false);
    }
    
    /**
     * {@inheritDoc}
     * @see org.eclipse.jface.dialogs.Dialog#buttonPressed(int)
     */
    @Override
    protected void buttonPressed(int buttonId) {
        try {
            if (buttonId == IDialogConstants.OK_ID) { 
                if(list.getSelectionIndex() >= 0 && list.getSelectionIndex() < devices.size()){
                    int index = getDeviceIndex(list.getItem(list.getSelectionIndex()));
                    if(index != -1){
                        Device device = devices.get(index);
                        device.setAdbDeviceName(adbDeviceName);
                        deviceManager.updateDevice(index, device);
                        clickListener.onOkButtonClick(index);
                    }
                    close();
                }
            }else{
                close();
            }
        } catch (MException e) {
            ExceptionHandler.handle(e, "select device to save adbname and save device");
        }
    }

    private int getDeviceIndex(String deviceName){
        for(int i = 0; i < devices.size(); i++){
            if(deviceName.equals(devices.get(i).getDeviceName()))
                return i;
        }
        return -1;
    }
    /**
     * Return the initial size of the dialog.
     */
    @Override
    protected Point getInitialSize() {
        return new Point(257, 303);
    }
}
