///**
// * @(#) ContentChangUpLoad.java Created on 2012-8-15
// *
// * Copyright (c) 2012 Aspire. All Rights Reserved
// */
//package com.braver.ip.upload.mail;
//
///**
// * The class <code>ContentChangUpLoad</code>
// * 
// * @author ouyangfeng
// * @version 1.0
// */
//public class ContentChangUpLoad extends UpLoadMail {
//
//    private String content;
//
//    private boolean sendingState;// 记录是否发送mail成功
//
//    /**
//     * Constructor
//     * 
//     * @param stmp
//     * @param username
//     * @param password
//     * @param toAddress
//     * @param subject
//     */
//    public ContentChangUpLoad(String stmp, String username, String password, String toAddress, String toAddressCc,
//            String subject) {
//        super(stmp, username, password, toAddress, toAddressCc, subject);
//    }
//
//    @Override
//    public boolean upLoadIP(String content) throws Exception {
//        logger.debug("pre send " + content);
//        if (this.content == null || !this.content.equals(content)) {
//            sendingState = super.upLoadIP(content);
//            if (sendingState) {
//                this.content = content;
//            } else {
//                this.content = null;
//            }
//            return sendingState;
//        } else {
//            logger.debug("content not chang not sending mail");
//            return true;
//        }
//    }
//
//}
