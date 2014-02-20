/**
 * @(#) LogConfigure.java Created on 2012-6-4
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.android.log.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aspire.android.log.Logger;
import com.aspire.android.log.appender.FileLogAppender;
import com.aspire.android.log.appender.LogAppender;
import com.aspire.android.log.layout.LogLayout;

/**
 * The class <code>LogConfigure</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public final class LogConfigure {

    private static final String ROOTLOOGER = "android.log.rootLogger";

    private static final String APPENDER_TAG = "android.log.appender.";

    private static final String FILE_APPENDER_FILE = ".File";

    private static final String FILE_APPENDER_APPEND = ".Append";

    private static final String FILE_APPENDER_MAXSIZE = ".Maxsize";

    private static final String APPENDER_LAYOUT = ".layout";

    private Properties properties;

    protected HashMap<String, String> environment = new HashMap<String, String>();

    private InputStream inconfig;

    private Logger logger;

    public void setInconfig(InputStream inconfig) {
        this.inconfig = inconfig;
    }

    public LogConfigure() {
    }

    public LogConfigure(InputStream in) {
        this.inconfig = in;
    }

    /**
     * parse config to logger
     * 
     * @return
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public Logger getConfigure() throws IOException, IllegalAccessException, InstantiationException,
            ClassNotFoundException {
        load();
        final String rootlogger = getRootLogger();
        if (null != rootlogger) {
            final String[] rootconfig = rootlogger.split(",");
            if (rootconfig.length > 1) {
                logger = new Logger();
                // add environment to logger
                logger.setEnvironment(environment);
                // add appender
                for (int i = 1; i < rootconfig.length; i++) {
                    final String appender = rootconfig[i].trim();
                    logger.addAppender(configLogAppender(appender));
                }
                // set log level
                logger.setLogLevel(rootconfig[0].trim());
            }
        }
        return this.logger;
    }

    /**
     * added var to the environment
     * 
     * @param key
     * @param value
     */
    public void addEnvironment(String key, String value) {
        this.environment.put(key, value);
    }

    /**
     * added all var to the environment
     * 
     * @param all
     */
    public void addAllEnvironment(HashMap<String, String> all) {
        this.environment.putAll(all);
    }

    private static LogLayout getLayout(String className) throws IllegalAccessException, InstantiationException,
            ClassNotFoundException {
        if (null != className) {
            return (LogLayout) Class.forName(className).newInstance();
        }
        return null;
    }

    private static LogAppender getAppender(String className) throws IllegalAccessException, InstantiationException,
            ClassNotFoundException {
        if (null != className)
            return (LogAppender) Class.forName(className).newInstance();
        return null;
    }

    /**
     * parse Configure
     * 
     * @param filename
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */

    private LogAppender configLogAppender(String appender) throws IllegalAccessException, InstantiationException,
            ClassNotFoundException {
        final String appendtype = getProperty(APPENDER_TAG + appender);
        if (null == appendtype)
            throw new NullPointerException("not found the appender:" + appender + " appender config");
        final LogAppender logAppender = getAppender(appendtype);
        // if the LogAppender is FileLogAppender
        if (logAppender instanceof FileLogAppender) {
            // set fileAppender file path
            final FileLogAppender fileLogAppender = (FileLogAppender) logAppender;

            final String filepath = getProperty(APPENDER_TAG + appender + FILE_APPENDER_FILE);
            final String fileAppend = getProperty(APPENDER_TAG + appender + FILE_APPENDER_APPEND);
            if (null != filepath && null != fileAppend) {
                fileLogAppender.setFileProperties(filepath, Boolean.parseBoolean(fileAppend));
            } else if (null != filepath) {
                fileLogAppender.setFileProperties(filepath, false);
            } else {
                throw new RuntimeException("please config File properties with :" + appendtype);
            }
            final String fileMaxSize = getProperty(APPENDER_TAG + appender + FILE_APPENDER_MAXSIZE);
            if (null != fileMaxSize){
                fileLogAppender.setFileMaxLogSize(Integer.parseInt(fileMaxSize));
            }
                

        }

        // config loglayout
        final String layout = getProperty(APPENDER_TAG + appender + APPENDER_LAYOUT);
        if (null == layout)
            throw new NullPointerException("not found the " + appender + " layout config");
        LogLayout logLayout = getLayout(layout);
        logAppender.setLogLayout(logLayout);

        return logAppender;
    }

    private Properties load() throws IOException {
        this.properties = new Properties();
        properties.load(inconfig);
        return properties;
    }

    /**
     * get property
     * 
     * @param string
     * @return
     */
    private String getProperty(String string) {
        final String str = properties.getProperty(string);
        return replaceVar(str);
    }

    private String getRootLogger() {
        if (null != properties) {
            return getProperty(ROOTLOOGER);
        }
        return null;
    }

    /**
     * repalce regex to environment param
     * 
     * @param src
     * @return
     */
    private String replaceVar(String src) {
        if (null != src) {
            final ArrayList<String> strings = new ArrayList<String>();
            final String findVar = "\\$\\{\\w+\\}";
            final Matcher matcher = Pattern.compile(findVar).matcher(src);
            String find = null;
            while (matcher.find()) {
                find = matcher.group(0);
                strings.add(find);
            }
            String arribeter = null;
            String arribeterValue = null;
            for (String string : strings) {
                arribeter = string.substring(2, string.length() - 1);
                arribeterValue = this.environment.get(arribeter);
                if (null != arribeterValue) {
                    src = src.replace(string, arribeterValue);
                } else {
                    throw new NullPointerException("please add param:" + arribeter + " on the environment filed");
                }
            }
        }
        return src;
    }
}
