package com.aspire.sqmp.mobilemanager.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.aspire.common.exception.ExceptionHandler;
import com.aspire.common.exception.MException;
import com.aspire.common.util.FileUtil;
import com.aspire.common.util.MobileManagerConstants;
import com.aspire.common.util.XmlUtil;
import com.aspire.sqmp.mobilemanager.entity.Device;

public class DeviceDao implements IDeviceDao {


    private static final String DEVICE_NAME = "device.xml";

    @Override
    public Device loadDevice(String path) throws MException {
        Device device = null;
        File xml = new File(path, DEVICE_NAME);
        if(!xml.exists())return null;
        FileInputStream inputStream = null;
        try {
            try{
                inputStream = new FileInputStream(xml);
                device = (Device)XmlUtil.deSerialize(Device.class, inputStream);
                inputStream.close();
                inputStream = null;
            }finally{
                if(inputStream != null){
                    inputStream.close();
                }
            }
        } catch (Exception e) {
            MException mexception = ExceptionHandler.handle(e, "while parse xml to device object");
            if (mexception != null) {
                throw mexception;
            }
        }
        return device;
    }
    @Override
    public List<Device> loadDevices(String path) throws MException {
        List<Device> deviceList = new ArrayList<Device>();
        File xmlParent = new File(path); 
        File[] fileList = xmlParent.listFiles();
        if(fileList != null && fileList.length > 0){
            for(File file : fileList){
                if(file.isDirectory()){
                    File xml = new File(file , MobileManagerConstants.DEVICE_FILE_PATH + "\\" + DEVICE_NAME);
                    if(xml.exists()){
                        FileInputStream inputStream = null;
                        try {
                            try{
                                inputStream = new FileInputStream(xml);
                                Device device = (Device)XmlUtil.deSerialize(Device.class, inputStream);
                                deviceList.add(device);
                                inputStream.close();
                                inputStream = null;
                            }finally{
                                if(inputStream != null){
                                    inputStream.close();
                                    inputStream = null;
                                }
                            }
                        } catch (Exception e) {
                            MException mexception = ExceptionHandler.handle(e, "while parse xml to device object");
                            if (mexception != null) {
                                throw mexception;
                            }
                        }
                    }
                }
            }
        }
        return deviceList == null ? null : deviceList;
    }

    @Override
    public void saveDevices(String path, List<Device> deviceList) throws MException {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveDevice(String path, Device device) throws MException {
        File xmlParent = new File(path + "\\" +device.getFolderName(), MobileManagerConstants.DEVICE_FILE_PATH);
        if(!xmlParent.exists()){
            xmlParent.mkdirs();
        }
        File xml = new File(xmlParent, DEVICE_NAME);
        try {
            FileOutputStream out = null;
            try{
                out = new FileOutputStream(xml);
                XmlUtil.serialize(device, out);
                out.flush();
                out.close();
                out = null;
            }finally{
                if(out != null){
                    out.flush();
                    out.close();
                    out = null;
                }
            }
        } catch (Exception e) {
            MException mexception = ExceptionHandler.handle(e, "save device to xml");
            if (mexception != null) {
                throw mexception;
            }
        }

    }

    @Override
    public void deleteDevice(String path, Device device) throws MException {
        String pathName = path + "\\" + device.getFolderName();
        File file = new File(pathName);
        if(file.exists()){
            FileUtil.deleteFile(file);
        }else{
            MException mexception = ExceptionHandler.handle(null, "Not find the device file");
            if (mexception != null) {
                throw mexception;
            }
        }
    }
    
}
