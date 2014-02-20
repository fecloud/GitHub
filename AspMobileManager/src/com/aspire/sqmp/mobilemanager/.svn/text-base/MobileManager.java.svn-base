/**
 * @(#) MobileManager.java Created on 2012-7-12
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.sqmp.mobilemanager;

import org.eclipse.swt.widgets.Display;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <code>MobileManager</code>
 * 
 * @author linjunsui
 * @version 1.0
 */
public class MobileManager {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(MobileManager.class);
    	try {
            MainApplicationWindow mainApplicationWindow = new MainApplicationWindow();
            mainApplicationWindow.setBlockOnOpen(true);
            mainApplicationWindow.open();  
            Display.getCurrent().dispose();
        } catch (Exception e) {
           logger.error("Error while launch MainApplicationWindow", e);
        }
    }
}
