package com.aspire.android.test.script.sync;

import java.io.File;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.aspire.android.test.PreferencesManager;
import com.aspire.android.test.execute.NameConstants;
import com.aspire.android.test.execute.NameConstants.SharedPrefConstants;
import com.aspire.android.test.log.RunLogger;
import com.aspire.android.test.script.service.IScriptService;
import com.aspire.mgt.ats.tm.sync.script.ScriptNoteDownloadHandler;
import com.aspire.mgt.ats.tm.sync.script.entity.ScriptNote;

public class ESScriptNoteDownloadHandler extends ScriptNoteDownloadHandler {

    private RunLogger runLogger = RunLogger.getInstance();
    
	/**
	 * 脚本zip保存文件并解析的handler
	 */
	private ESScriptFileDownloadHandler tmSFDownloadHandler;
	
	private IScriptService service;
	
	public ESScriptNoteDownloadHandler(){
		super();
    }
	public void setService(IScriptService service){
		this.service = service;
	}
	
	@Override
	public void handle(ScriptNote scriptNote) {
		tmSFDownloadHandler = new ESScriptFileDownloadHandler();
		tmSFDownloadHandler.setService(service);
		tmSFDownloadHandler.setFtpLocalPath(ftpLocalPath);
		super.setSFDownloadHanlder(tmSFDownloadHandler);
        super.handle(scriptNote);
        //下载完所有的脚本后保存最后的时间搓
        if(lastUpdateTime != null){
            SharedPreferences preferences = PreferencesManager.getInstance().getPreferences();
            String updateTime = preferences.getString(SharedPrefConstants.SCRIPT_LAST_UPDATE_TIME, null);
            // 保证存储的是下载脚本中最新的日期
            if (updateTime == null
                    || (lastUpdateTime.compareTo(updateTime) > 0)) {
                Editor editor = preferences.edit();
                editor.putString(SharedPrefConstants.SCRIPT_LAST_UPDATE_TIME, lastUpdateTime);
                editor.commit();
            }
        }
        String filePath = ftpLocalPath + NameConstants.BACKUP_ADDRESS + "/" + NameConstants.SCRIPT_FILE_PATH;
        File parent = new File(filePath);
        if(!parent.exists())
            parent.mkdirs();
        File xmlFileDest = new File(parent, fileName);
        if(xmlFileDest.exists())
            xmlFileDest.delete();
        xmlFile.renameTo(xmlFileDest);
        if(xmlFile.exists())
            runLogger.debug(getClass(), "Failed while backup script xml file, destPath is " + xmlFileDest.getAbsolutePath());
	}
	
}
