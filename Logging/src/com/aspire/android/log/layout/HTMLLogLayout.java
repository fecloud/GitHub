///**
// * @(#) HTMLLogLayout.java Created on 2008-1-11
// *
// * Copyright (c) 2008 Aspire. All Rights Reserved
// */
//package com.aspire.android.log.layout;
//
//import com.aspire.android.log.LogEvent;
//
///**
// * The class <code>HTMLLogLayout</code>
// *
// * @author xuyong
// * @version 1.0
// */
//@Deprecated
//public class HTMLLogLayout implements LogLayout {
//
//	/*
//	 * The instance of the HTMLLogLayout
//	 */
//	private final static HTMLLogLayout instance = new HTMLLogLayout();
//	
//	/**
//	 * Constructs a new HTMLLogLayout
//	 */
//	private HTMLLogLayout() {
//	}
//	
//	/**
//	 * Gets the instance of the HTMLLogLayout
//	 * @return Returns the instance of HTMLLogLayout
//	 */
//	public static HTMLLogLayout getInstance() {
//		return instance;
//	}
//	
//	/**
//     * Formats the LogEvent data to a string that appenders can log.
//     * Implement this method to create your own layout format.
//	 * @param evt The log event which contains log information
//	 * @return Returns the formated log information
//	 */
//	public String format(LogEvent evt) {
//		
//		String logMsg = evt.message;
//		StringBuffer sbMsg = new StringBuffer();
//		
//		logMsg = convert2WebMsg(logMsg);
//        
////		switch (evt.getLevel())
////		{
////		case LogLevel.PRINT:
////			sbMsg.append(logMsg);
////			return sbMsg;
////		case LogLevel.PRINT_LN:
////			sbMsg.append(logMsg);
////			sbMsg.append("<br>");
////			return sbMsg;
////		case LogLevel.RESULT:
////			sbMsg.append("<div id='log-result'>");
////			sbMsg.append(LogLevel.getDescription(evt.getLevel()));
////			sbMsg.append(": ");
////			sbMsg.append(logMsg);
////			sbMsg.append("</div><br><br>");
////			return sbMsg;
////		case LogLevel.FATAL:
////			{
////				SimpleDateFormat sdf = new SimpleDateFormat(
////				        "yyyy-MM-dd HH:mm:ss.SS");
////				sbMsg.append("<div id='log-fatal-title'>");
////				sbMsg.append(LogLevel.getDescription(evt.getLevel()));
////				sbMsg.append(": ");
////				sbMsg.append(sdf.format(evt.getDate()));
////				sbMsg.append("</div><div id='log-fatal'><pre>");
////				sbMsg.append(logMsg);
////				sbMsg.append("</pre></div><br><br>");
////			}
////			break;
////		case LogLevel.ERROR:
////			{
////				SimpleDateFormat sdf = new SimpleDateFormat(
////				        "yyyy-MM-dd HH:mm:ss.SS");
////				sbMsg.append("<div id='log-error-title'>");
////				sbMsg.append(LogLevel.getDescription(evt.getLevel()));
////				sbMsg.append(": ");
////				sbMsg.append(sdf.format(evt.getDate()));
////				sbMsg.append("</div><div id='log-error'><pre>");
////				sbMsg.append(logMsg);
////				sbMsg.append("</pre></div><br><br>");
////			}
////			break;
////		case LogLevel.WARNING:
////			{
////				SimpleDateFormat sdf = new SimpleDateFormat(
////				        "yyyy-MM-dd HH:mm:ss.SS");
////				sbMsg.append("<div id='log-warn-title'>");
////				sbMsg.append(LogLevel.getDescription(evt.getLevel()));
////				sbMsg.append(": ");
////				sbMsg.append(sdf.format(evt.getDate()));
////				sbMsg.append("</div><div id='log-warn'><pre>");
////				sbMsg.append(logMsg);
////				sbMsg.append("</pre></div><br><br>");
////			}
////			break;
////		case LogLevel.PROTOCOL:
////			{
////				SimpleDateFormat sdf = new SimpleDateFormat(
////				        "yyyy-MM-dd HH:mm:ss.SS");
////		        //logMsg = logMsg.replaceAll("<", "&lt;");
////		        //logMsg = logMsg.replaceAll(">", "&gt;");
////				//logMsg = "<pre>" + logMsg + "</pre>";
////				sbMsg.append("<div id='log-protocol-title'>");
////				sbMsg.append(LogLevel.getDescription(evt.getLevel()));
////				sbMsg.append(": ");
////				sbMsg.append(sdf.format(evt.getDate()));
////				sbMsg.append("</div><div id='log-protocol'><pre>");
////				sbMsg.append(logMsg);
////				sbMsg.append("</pre></div><br><br>");
////			}
////			break;
////		case LogLevel.DEBUG:
////			{
////				SimpleDateFormat sdf = new SimpleDateFormat(
////				        "yyyy-MM-dd HH:mm:ss.SS");
////				sbMsg.append("<div id='log-debug-title'>");
////				sbMsg.append(LogLevel.getDescription(evt.getLevel()));
////				sbMsg.append(": ");
////				sbMsg.append(sdf.format(evt.getDate()));
////				sbMsg.append("</div><div id='log-debug'><pre>");
////				sbMsg.append(logMsg);
////				sbMsg.append("</pre></div><br><br>");
////			}
////			break;
////		case LogLevel.INFO:
////		default:
////			{
////				SimpleDateFormat sdf = new SimpleDateFormat(
////				        "yyyy-MM-dd HH:mm:ss.SS");
////				sbMsg.append("<div id='log-info-title'>");
////				sbMsg.append(LogLevel.getDescription(evt.getLevel()));
////				sbMsg.append(": ");
////				sbMsg.append(sdf.format(evt.getDate()));
////				sbMsg.append("</div><div id='log-info'><pre>");
////				sbMsg.append(logMsg);
////				sbMsg.append("</pre></div><br><br>");
////			}
////			break;
////		}
//		
//		return sbMsg.toString();
//	}
//	
//	/**
//	 * ����־��Ϣת���ɿ�����web����ʾ����Ϣ����Ҫ�ǽ�<ת����&lt;������'<a '֮��Ľ���ת��
//	 * 
//	 * @param logMsg Ҫת������־��Ϣ
//	 * @return ����ת�������־��Ϣ
//	 */
//	protected String convert2WebMsg(String logMsg) {
//	    
//	    StringBuilder sbMsg = new StringBuilder(logMsg.length());
//	    StringBuilder sbTmp = new StringBuilder();
//	    int state = 0;
//	    
//	    for (int i = 0; i < logMsg.length(); i++) {
//	        char curCh = logMsg.charAt(i);
//	        
//	        switch (state) {
//	        case 0:
//	            if (curCh == '<') {
//	                state = 1;
//	                break;
//	            }
//	            sbMsg.append(curCh);
//	            break;
//	        case 1:
//	            if (curCh == '/') {
//	                state = 2;
//	                sbTmp.append(curCh);
//	                break;
//	            } else {
//	                if ((curCh >= 'a' && curCh <= 'z') ||
//	                    (curCh >= 'A' && curCh <= 'Z')) {
//                        
//                        sbTmp.append(curCh);
//                        break;
//                    } else {
//                        String sTmp = sbTmp.toString().trim();
//                        
//                        if (sTmp.equalsIgnoreCase("a")) {
//                            sbMsg.append('<').append(sbTmp).append(curCh);
//                        } else {
//                            sbMsg.append("&lt;").append(sbTmp).append(curCh);
//                        }
//                        sbTmp.delete(0, sbTmp.length());
//                        state = 0;
//                    }
//                    break;
//	            }
//	        case 2:
//                if ((curCh >= 'a' && curCh <= 'z') ||
//                    (curCh >= 'A' && curCh <= 'Z')) {
//                    
//                    sbTmp.append(curCh);
//                    break;
//                } else {
//                    String sTmp = sbTmp.toString().trim();
//                    
//                    if (sTmp.equalsIgnoreCase("/a")) {
//                        sbMsg.append('<').append(sbTmp).append(curCh);
//                    } else {
//                        sbMsg.append("&lt;").append(sbTmp).append(curCh);
//                    }
//                    sbTmp.delete(0, sbTmp.length());
//                    state = 0;
//                }
//                break;
//	        }
//	    }
//	    
//	    if (sbTmp.length() > 0) {
//            sbMsg.append("&lt;").append(sbTmp);
//	    }
//	    
//	    return sbMsg.toString();
//	}
//	
////	/**
////	 * ��������Ҫ���ڲ���
////	 * @param args
////	 */
////	public static void main(String[] args) {
////	    System.out.println(HTMLLogLayout.getInstance().convert2WebMsg(
////	            "<a href='<>'><xml></a><A href></A"));
////	}
//}
