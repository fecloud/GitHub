/**
 * @(#) SendMail.java Created on 2012-8-14
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.braver.ip.upload.mail;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.braver.ip.upload.AbstractUpLoadIP;

/**
 * The class <code>SendMail</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class UpLoadMail extends AbstractUpLoadIP {

    private String stmp;

    private String username;

    private String password;

    private String toAddress;

    private String toAddressCc;

    private String subject;

    private List<String> toAddressCcList;

    public UpLoadMail(String stmp, String username, String password, String toAddress, String toAddressCc,
            String subject) {
        super();
        this.stmp = stmp;
        this.username = username;
        this.password = password;
        this.toAddress = toAddress;
        this.toAddressCc = toAddressCc;
        this.subject = subject;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.braver.ip.upload.UpLoadIP#upLoadIP(java.lang.String)
     */
    @Override
    public boolean upLoadIP(String content) throws Exception {
        this.content = content;
        if (null != toAddress) {
            toAddressCcList = new ArrayList<String>();
            for (String str : toAddressCc.split(",")) {
                toAddressCcList.add(str);
            }
        }
        return sendMail();
    }

    private boolean sendMail() throws EmailException {
        logger.debug("send mail start");
        final SimpleEmail email = new SimpleEmail();
        email.setHostName(stmp);
        email.setAuthentication(username, password);
        email.addTo(toAddress);
        // 添加抄送
        for (String str : toAddressCcList) {
            email.addCc(str);
        }
        email.setFrom(username);
        email.setSubject(subject);
        email.setMsg(content);
        email.send();
        logger.debug("send mail successful");
        return true;
    }
}
