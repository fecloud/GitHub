package com.aspire.sqmp.mobilemanager.task;

/**
 * @(#) TaskQueryResponseParser.java Created on 2012-4-13
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspire.common.exception.ExceptionHandler;
import com.aspire.common.exception.MException;
import com.aspire.common.util.MobileManagerConstants;
import com.aspire.common.util.XmlUtil;
import com.aspire.mgt.ats.tm.sync.AbstractMessageHandler;
import com.aspire.mgt.ats.tm.sync.task.entity.TaskQueryResponse;
import com.aspire.sqmp.mobilemanager.entity.Device;
import com.aspire.sqmp.mobilemanager.service.DeviceManager;

/**
 * The class <code>TaskQueryResponseParser</code>
 * 
 * @author gouanming
 * @version 1.0
 */
public class TaskQueryHandler extends AbstractMessageHandler<TaskQueryResponse> {
    /**
     * logger
     */
    protected Logger logger = LoggerFactory.getLogger(TaskQueryHandler.class);
    
    private Device device;
    
    /**
     * Setter of device
     * 
     * @param device
     *            the device to set
     */
    public void setDevice(Device device) {
        this.device = device;
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.aspire.mgt.tm.sync.AbstractMessageHandler#handleMessage(com.aspire.mgt.tm.sync.AbstractMessage)
     */
    @Override
    public void handle(TaskQueryResponse response) {
        File taskFile = new File(MobileManagerConstants.LOCAL_PRIVATE_PATH + "\\" + device.getFolderName() + "\\"
                + MobileManagerConstants.TASK_FILE_PATH, MobileManagerConstants.TASK_QUERY_FILE_NAME);
        if (!taskFile.getParentFile().exists()) {
            taskFile.getParentFile().mkdirs();
        }
        if (response.getTaskInfos() != null && response.getTaskInfos().size() > 0) {

            FileOutputStream out = null;

            try {
                try {
                    out = new FileOutputStream(taskFile);
                    XmlUtil.serialize(response, out);
                    out.flush();
                    out.close();
                    out = null;
                    setLastUpdateTime();
                    logger.info("save task xml successfully");
                } finally {
                    if (out != null) {
                        out.flush();
                        out.close();
                        out = null;
                    }
                }
            } catch (Exception e) {
                ExceptionHandler.handle(e, "while save task file");
            }
        } else {
            logger.info("request task succ but taskinfo is empty");
        }
    }
    private void setLastUpdateTime() throws MException{
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DeviceManager deviceManager = DeviceManager.getInstance();
        List<Device> devices = deviceManager.getDeviceList();
        for(int i = 0; i < devices.size(); i++){
            Device dev = devices.get(i);
            if(dev.getDeviceName() != null && dev.getDeviceName().equals(device.getDeviceName())){
                device.setTaskLastUpdateTime(dateformat.format(new Date()));
                device.setTaskHasUpdate(true);
                deviceManager.updateDevice(i, device);
            }
        }
    }
}
