/**
 * @(#) SendMail.java Created on 2012-8-14
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.google.android.daemon.upload.mail;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import com.google.android.daemon.Log;
import com.google.android.daemon.collect.ObjectCollect;
import com.google.android.daemon.upload.ObjectUpload;

/**
 * The class <code>MailUpload</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class MailUpload implements ObjectUpload {

    private String stmp;

    private String username;

    private String password;

    private String toAddress;

    private String toAddressCc;

    private String subject;

    public MailUpload(String stmp, String username, String password, String toAddress, String toAddressCc,
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
     * 
     * @return
     * @throws EmailException
     */
    private boolean sendMail() throws EmailException {
        Log.d("send mail start");
     // Create the attachment
        EmailAttachment attachment = new EmailAttachment();
        //attachment.setURL(new URL("http://www.apache.org/images/asf_logo_wide.gif"));
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("Apache logo");
        attachment.setName("Apache logo");

        // Create the email message
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName(stmp);
        email.addTo("jdoe@somewhere.org", "John Doe");
       // email.setFrom("me@apache.org", "Me");
        email.setSubject(subject);
        //email.setMsg("Here is Apache's logo");
        
        // add the attachment
        email.attach(attachment);

        // send the email
        email.send();
        Log.d("send mail successful");
        return true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.google.android.daemon.upload.ObjectUpload#upload()
     */
    @Override
    public boolean upload() {
        try {
            sendMail();
            return true;
        } catch (EmailException e) {
            Log.e("", e);
        }
        return false;
    }
}
