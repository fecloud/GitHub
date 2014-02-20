/**
 * @(#) CustomizeResource.java Created on 2011-5-12
 *
 * Copyright (c) 2011 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.xmlpull.v1.XmlPullParser;

import android.os.Environment;
import android.util.Log;
import android.util.Xml;

/**
 * The class <code>CustomizeResource</code>
 * 
 * @author Link Wang
 * @version 1.0
 */
public class CustomizeResource {

    private final static String TAG = "CustomizeResource";

    private final static CustomizeResource instance = new CustomizeResource();

    private Map<String, String> resources;
    private Map<String, Map<String, OcrParam>> globalOcrParams;
    private Map<String, Map<String, OcrParam>> deviceOcrParams;

    private CustomizeResource() {

        final String RESOURCE_LOCATION = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator
                + "Resource.xml";

        resources = new HashMap<String, String>();
        globalOcrParams = new HashMap<String, Map<String, OcrParam>>();
        deviceOcrParams = new HashMap<String, Map<String, OcrParam>>();
        /*
         * Thread loader = new Thread() {
         * 
         * (non-Javadoc)
         * 
         * @see java.lang.Thread#run()
         * 
         * @Override public void run() { File resource = null; long lastModTime = 0; while (true) { resource = new
         * File(RESOURCE_LOCATION); if (resource.lastModified() > lastModTime) { loadResource(resource); } try {
         * Thread.sleep(10000); } catch (InterruptedException ex) { logger.error("Sleep broken when load resource.",
         * ex); } } } }; loader.setDaemon(true); loader.start();
         */

        File resource = new File(RESOURCE_LOCATION);
        loadResource(resource);
    }

    public static CustomizeResource getInstance() {
        return instance;
    }

    public String getString(String resKey) {
        Log.d(TAG, "Get resource by key: " + resKey);
        return resources.get(resKey);
    }

    public int getInt(String resKey) {
        try {
            return Integer.parseInt(resources.get(resKey));
        } catch (NumberFormatException ex) {
            Log.e(TAG, ex.getMessage(), ex);
            return 0;
        }
    }

    public OcrParam getOcrParam(String ocrKey, String model) {
        OcrParam ocrParam = null;
        Map<String, OcrParam> ocrParams = deviceOcrParams.get(ocrKey);
        if (ocrParams != null) {
            ocrParam = ocrParams.get(model);
        }
        if (ocrParam != null && ocrParam.refId != null) {
            Map<String, OcrParam> gOcrParams = globalOcrParams.get(model);
            if (gOcrParams != null) {
                OcrParam globalOcrParam = gOcrParams.get(ocrParam.refId);
                if (globalOcrParam != null) {
                    if (ocrParam.area == null || "".equals(ocrParam.area.trim())) {
                        ocrParam.x1 = globalOcrParam.x1;
                        ocrParam.y1 = globalOcrParam.y1;
                        ocrParam.x2 = globalOcrParam.x2;
                        ocrParam.y2 = globalOcrParam.y2;
                    }
                    if (ocrParam.trim == null || "".equals(ocrParam.trim.trim())) {
                        ocrParam.doTrim = globalOcrParam.doTrim;
                    }
                    if (ocrParam.join == null || "".equals(ocrParam.join.trim())) {
                        ocrParam.doJoin = globalOcrParam.doJoin;
                    }
                    if (ocrParam.scale == 0) {
                        ocrParam.scale = globalOcrParam.scale > 0 ? globalOcrParam.scale : 2;
                    }
                    if (ocrParam.threshold == 0) {
                        ocrParam.threshold = globalOcrParam.threshold > 0 ? globalOcrParam.threshold : 150;
                    }
                    if(ocrParam.lang == -1){
                    	ocrParam.lang = globalOcrParam.lang > -1 ? globalOcrParam.lang : OcrParam.OCR_SIMPLIPIED_CHINESE;
                    }
                }
            }
        }
        return ocrParam;
    }

    private void loadResource(File resource) {
        InputStream ins = null;
        XmlPullParser parser = Xml.newPullParser();
        try {
            ins = new FileInputStream(resource);
            parser.setInput(ins, "UTF-8");
            Stack<String> nodeStack = new Stack<String>();
            String nodeName = null;
            int attrCount = 0;
            String attrName = null;
            String key = null;
            OcrParam ocrParam = null;
            Map<String, OcrParam> dOcrParams = null;
            Map<String, OcrParam> gOcrParams = null;
            int eventType = 0;
            while (XmlPullParser.END_DOCUMENT != (eventType = parser.next())) {
                switch (eventType) {
                case XmlPullParser.START_TAG:
                    nodeName = parser.getName();
                    if ("ocr".equalsIgnoreCase(nodeName)) {
                        ocrParam = new OcrParam();
                        if ("device".equalsIgnoreCase(nodeStack.peek())) {
                            attrCount = parser.getAttributeCount();
                            for (int i = 0; i < attrCount; i++) {
                                 attrName = parser.getAttributeName(i);
                                if ("id".equalsIgnoreCase(attrName)) {
                                    ocrParam.key = parser.getAttributeValue(i);
                                    gOcrParams.put(ocrParam.key, ocrParam);
                                } else if ("area".equalsIgnoreCase(attrName)) {
                                    ocrParam.setArea(parser.getAttributeValue(i));
                                } else if ("trim".equalsIgnoreCase(attrName)) {
                                    ocrParam.setTrim(parser.getAttributeValue(i));
                                } else if ("join".equalsIgnoreCase(attrName)) {
                                    ocrParam.setJoin(parser.getAttributeValue(i));
                                } else if ("scale".equalsIgnoreCase(attrName)) {
                                    ocrParam.scale = Integer.parseInt(parser.getAttributeValue(i));
                                } else if ("threshold".equalsIgnoreCase(attrName)) {
                                    ocrParam.threshold = Integer.parseInt(parser.getAttributeValue(i));
                                }else if("lang".equalsIgnoreCase(attrName)){
                                	ocrParam.lang = OcrParam.parseLang(parser.getAttributeValue(i));
                                }
                            }
                        } else if ("Category".equalsIgnoreCase(nodeStack.peek())) {
                            attrCount = parser.getAttributeCount();
                            for (int i = 0; i < attrCount; i++) {
                                 attrName = parser.getAttributeName(i);
                                if ("key".equalsIgnoreCase(attrName)) {
                                    key = parser.getAttributeValue(i);
                                }
                            }
                            dOcrParams = new HashMap<String, OcrParam>();
                        }
                    } else if ("res".equalsIgnoreCase(nodeName)) {
                        attrCount = parser.getAttributeCount();
                        for (int i = 0; i < attrCount; i++) {
                             attrName = parser.getAttributeName(i);
                            if ("key".equalsIgnoreCase(attrName)) {
                                key = parser.getAttributeValue(i);
                            }
                        }
                    } else if ("device".equalsIgnoreCase(nodeName)) {
                        if ("ocr".equalsIgnoreCase(nodeStack.peek())) {
                            ocrParam = new OcrParam();
                            ocrParam.key = key;
                            attrCount = parser.getAttributeCount();
                            String deviceModel = null;
                            for (int i = 0; i < attrCount; i++) {
                                 attrName = parser.getAttributeName(i);
                                if ("model".equalsIgnoreCase(attrName)) {
                                    deviceModel = parser.getAttributeValue(i);
                                } else if ("area".equalsIgnoreCase(attrName)) {
                                    ocrParam.setArea(parser.getAttributeValue(i));
                                } else if ("trim".equalsIgnoreCase(attrName)) {
                                    ocrParam.setTrim(parser.getAttributeValue(i));
                                } else if ("join".equalsIgnoreCase(attrName)) {
                                    ocrParam.setJoin(parser.getAttributeValue(i));
                                } else if ("ref".equalsIgnoreCase(attrName)) {
                                    ocrParam.setRefId(parser.getAttributeValue(i));
                                } else if ("scale".equalsIgnoreCase(attrName)) {
                                    ocrParam.scale = Integer.parseInt(parser.getAttributeValue(i));
                                } else if ("threshold".equalsIgnoreCase(attrName)) {
                                    ocrParam.threshold = Integer.parseInt(parser.getAttributeValue(i));
                                }else if("lang".equalsIgnoreCase(attrName)){
                                	ocrParam.lang = OcrParam.parseLang(parser.getAttributeValue(i));
                                }
                            }
                            dOcrParams.put(deviceModel, ocrParam);
                        } else if ("Global".equalsIgnoreCase(nodeStack.peek())) {
                            attrCount = parser.getAttributeCount();
                            for (int i = 0; i < attrCount; i++) {
                                 attrName = parser.getAttributeName(i);
                                if ("model".equalsIgnoreCase(attrName)) {
                                    gOcrParams = new HashMap<String, OcrParam>();
                                    globalOcrParams.put(parser.getAttributeValue(i), gOcrParams);
                                }
                            }
                        }
                    }
                    nodeStack.add(nodeName);
                    break;
                case XmlPullParser.TEXT:
                    if ("device".equalsIgnoreCase(nodeStack.peek())
                            && "ocr".equalsIgnoreCase(nodeStack.elementAt(nodeStack.size() - 2))) {
                        ocrParam.setMatcher(parser.getText());
                    } else if ("res".equalsIgnoreCase(nodeStack.peek())) {
                        resources.put(key, parser.getText());
                    }
                    break;
                case XmlPullParser.END_TAG:
                    nodeStack.pop();
                    if ("ocr".equalsIgnoreCase(parser.getName()) && "Category".equalsIgnoreCase(nodeStack.peek())) {
                        deviceOcrParams.put(key, dOcrParams);
                    }
                    break;
                // case XMLStreamConstants.START_DOCUMENT:
                // case XMLStreamConstants.PROCESSING_INSTRUCTION:
                // case XMLStreamConstants.COMMENT:
                // case XMLStreamConstants.END_DOCUMENT:
                }
            }
        } catch (Exception ex) {
            Log.e(TAG, "Fail to load resource from: " + resource, ex);
        } finally {
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException ex) {
                }
            }
        }
    }

}
