/**
 * @(#) OCRFind.java Created on 2012-10-30
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.inspurworld.server.ocr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.inspurworld.server.config.Config;
import com.inspurworld.server.util.CmdExecute;
import com.inspurworld.server.util.CmdExecute.CmdResponse;

/**
 * The class <code>OCRFind</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class OCRFind {

    private Logger logger = Logger.getLogger(getClass());

    /**
     * 英语
     */
    public static final int ENG = 0;

    /**
     * 简体中文
     */
    public static final int CHI_SIM = 1;

    /**
     * 繁体中文
     */
    public static final int CHI_TRA = 2;

    private String inputImagePath;

    private int lang;

    public OCRFind() {
    }

    public OCRFind(String inputImagePath, int lang) {
        super();
        this.inputImagePath = inputImagePath;
        this.lang = lang;
    }

    /**
     * ocr find in the image
     * 
     * @return
     */
    public String ocr() {
        final String out = Config.TESSERACTOCR_DIR + "ocr";
        final StringBuilder cmd = new StringBuilder(Config.TESSERACTOCR_DIR);
        cmd.append("tesseract.exe ");
        cmd.append(inputImagePath).append(" ");
        cmd.append(out);
        cmd.append(" -l ");
        cmd.append(getLang(lang));
        logger.debug("invoke ocr cmd: " + cmd.toString());
        try {
            final CmdResponse response = CmdExecute.exec(cmd.toString());
            if (response.isRunSuccess()) {
                logger.debug("ocr run sucess!");
                return readOCRTXT(out + ".txt");
            }
        } catch (IOException e) {
            logger.error("ocr error", e);

        }
        return null;
    }

    public String getInputImagePath() {
        return inputImagePath;
    }

    public void setInputImagePath(String inputImagePath) {
        this.inputImagePath = inputImagePath;
    }

    public int getLang() {
        return lang;
    }

    public void setLang(int lang) {
        this.lang = lang;
    }

    /**
     * 获取语言包
     * 
     * @param lang
     * @return
     */
    private static String getLang(int lang) {
        switch (lang) {
        case ENG:
            return "eng";
        case CHI_SIM:
            return "chi_sim";
        case CHI_TRA:
            return "chi_tra";
        default:
            return null;
        }
    }

    /**
     * 读文件
     * 
     * @param path
     * @return
     * @throws IOException
     */
    private static String readOCRTXT(String path) throws IOException {
        final File file = new File(path);
        if (file.exists()) {
            final StringBuffer buffer = new StringBuffer();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = null;
            while (null != (line = reader.readLine())) {
                buffer.append(line + "\r\n");
            }
            reader.close();
            return buffer.toString();
        }

        return null;
    }
}
