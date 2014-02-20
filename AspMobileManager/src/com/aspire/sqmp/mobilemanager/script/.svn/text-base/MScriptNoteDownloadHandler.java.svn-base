package com.aspire.sqmp.mobilemanager.script;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspire.common.exception.ExceptionHandler;
import com.aspire.common.exception.MException;
import com.aspire.mgt.ats.tm.sync.script.ScriptFileDownloadHandler;
import com.aspire.mgt.ats.tm.sync.script.ScriptNoteDownloadHandler;
import com.aspire.mgt.ats.tm.sync.script.entity.ScriptDetail;
import com.aspire.mgt.ats.tm.sync.script.entity.ScriptNote;
import com.aspire.sqmp.mobilemanager.entity.Device;
import com.aspire.sqmp.mobilemanager.service.DeviceManager;

public class MScriptNoteDownloadHandler extends ScriptNoteDownloadHandler {

    protected Logger logger = LoggerFactory.getLogger(MScriptNoteDownloadHandler.class);
	/**
	 * 脚本zip保存文件并解析的handler
	 */
	private ScriptFileDownloadHandler sfDownloadHandler;

	@Override
	public void handle(ScriptNote scriptNote) {
	    sfDownloadHandler = new ScriptFileDownloadHandler() {
            @Override
            public void OnDownloaded(ScriptDetail scriptDetail, SimpleDateFormat sdf, File file) throws ParseException {
                try {
                    setLastUpdateTime(scriptDetail.getUploaddate());
                    logger.debug("Saved update time after save case zip file ");
                } catch (MException e) {
                    ExceptionHandler.handle(e, "save update time after save case zip file ");
                }
            }
        };
		super.setSFDownloadHanlder(sfDownloadHandler);
        super.handle(scriptNote);
	}


    private void setLastUpdateTime(String lastUpdataTime) throws MException{
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DeviceManager deviceManager = DeviceManager.getInstance();
        List<Device> devices = deviceManager.getDeviceList();
        for(int i = 0; i < devices.size(); i++){
            Device dev = devices.get(i);
            if(dev.getModel() != null && dev.getModel().equals(scriptRequest.getDevType())){
                if(dev.getpCScriptLastUpdateTime() != null && dev.getpCScriptLastUpdateTime().compareTo(lastUpdataTime) > 0){
                    return;
                }
                dev.setpCScriptLastUpdateTime(lastUpdataTime);
                dev.setScriptHasUpdate(true);
                dev.setScriptLastUpdateTime(dateformat.format(new Date()));
                deviceManager.updateDevice(i, dev);
            }
        }
    }
}
