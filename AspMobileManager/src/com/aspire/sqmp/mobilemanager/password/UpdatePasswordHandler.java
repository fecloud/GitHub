/**
 * @(#) UpdatePasswordHandler.java Created on 2012-7-20
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager.password;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aspire.common.config.Config;
import com.aspire.common.config.ConfigManager;
import com.aspire.common.exception.ExceptionHandler;
import com.aspire.common.util.MobileManagerConstants;
import com.aspire.common.util.XmlUtil;
import com.aspire.mgt.ats.tm.sync.password.PasswordHandler;
import com.aspire.mgt.ats.tm.sync.password.entity.PwdResponse;

/**
 * The class <code>UpdatePasswordHandler</code>
 *
 * @author likai
 * @version 1.0
 */
public class UpdatePasswordHandler extends PasswordHandler {

    /**
     * {@inheritDoc}
     * @see com.aspire.mgt.ats.tm.sync.password.PasswordHandler#handle(com.aspire.mgt.ats.tm.sync.password.entity.PwdResponse)
     */
    @Override
    public void handle(PwdResponse pwdMsg) {
        super.handle(pwdMsg);
        if(pwdMsg.getKey() != null && !pwdMsg.getKey().equals("")){
            File parent = new File(MobileManagerConstants.PASSWORD_FILE_PATH);
            if(!parent.exists()){
                parent.mkdirs();
            }
            SimpleDateFormat simpleDataFormat = new SimpleDateFormat(
                    "yyyyMM");
            String dateString = simpleDataFormat.format(new Date());
            String fileName = "PASSWORD_" + dateString + ".xml";
            File pwdXml = new File(parent, fileName);
            try {
                FileOutputStream out = null;
                try{
                    out = new FileOutputStream(pwdXml);
                    XmlUtil.serialize(pwdMsg, out);
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
                Config config = ConfigManager.getConfig("global");
                config.set(MobileManagerConstants.UPDATE_RESULT_PWD_TIME, dateString);
            } catch (Exception e) {
                ExceptionHandler.handle(e, "while save password to file");
            }
        }
        
    }
    
}
