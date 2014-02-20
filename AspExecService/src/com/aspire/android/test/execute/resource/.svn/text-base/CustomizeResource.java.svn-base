/**
 * @(#) CustomizeResource.java Created on 2011-5-12
 *
 * Copyright (c) 2011 Aspire. All Rights Reserved
 */
package com.aspire.android.test.execute.resource;

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

    private static CustomizeResource instance;

    private Map<String, String> resources;
    private Map<String, Map<String, OCRResource>> globalOcrParams;
    private Map<String, Map<String, OCRResource>> deviceOcrParams;

    final String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "aspire"
            + File.separator + "es" + File.separator + "resource" + File.separator + "Resource.xml";

    private long lastModfiy;

    private CustomizeResource() {
        Log.d(TAG, "the resource.xml path:" + path);
        resources = new HashMap<String, String>();
        globalOcrParams = new HashMap<String, Map<String, OCRResource>>();
        deviceOcrParams = new HashMap<String, Map<String, OCRResource>>();
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
        File resource = new File(path);
        loadResource(resource);
    }

    public static CustomizeResource getInstance() {
        if (null == instance) {
            instance = new CustomizeResource();
        } else if (instance.isModfiy()) {
            Log.d(TAG, "reloading......");
            instance = new CustomizeResource();
        }
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

    public OCRResource getOcrParam(String ocrKey, String model) {
        OCRResource ocrParam = null;
        Map<String, OCRResource> ocrParams = deviceOcrParams.get(ocrKey);
        if (ocrParams != null) {
            ocrParam = ocrParams.get(model);
        }
        if (ocrParam != null && ocrParam.getRefId() != null) {
            Map<String, OCRResource> gOcrParams = globalOcrParams.get(model);
            if (gOcrParams != null) {
                OCRResource globalOcrParam = gOcrParams.get(ocrParam.getRefId());
                if (globalOcrParam != null) {
                    ocrParam.assignment(globalOcrParam);
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
            OCRResource ocrParam = null;
            Map<String, OCRResource> dOcrParams = null;
            Map<String, OCRResource> gOcrParams = null;
            int eventType = 0;
            while (XmlPullParser.END_DOCUMENT != (eventType = parser.next())) {
                switch (eventType) {
                case XmlPullParser.START_TAG:
                    nodeName = parser.getName();
                    if ("ocr".equalsIgnoreCase(nodeName)) {
                        ocrParam = new OCRResource();
                        if ("device".equalsIgnoreCase(nodeStack.peek())) {
                            attrCount = parser.getAttributeCount();
                            for (int i = 0; i < attrCount; i++) {
                                attrName = parser.getAttributeName(i);
                                if ("id".equalsIgnoreCase(attrName)) {
                                    ocrParam.setKey(parser.getAttributeValue(i));
                                    gOcrParams.put(ocrParam.getKey(), ocrParam);
                                } else if ("area".equalsIgnoreCase(attrName)) {
                                    ocrParam.setArea(parser.getAttributeValue(i));
                                } else if ("trim".equalsIgnoreCase(attrName)) {
                                    ocrParam.setTrim(parser.getAttributeValue(i));
                                } else if ("join".equalsIgnoreCase(attrName)) {
                                    ocrParam.setJoin(parser.getAttributeValue(i));
                                } else if ("scale".equalsIgnoreCase(attrName)) {
                                    ocrParam.setScale(Integer.parseInt(parser.getAttributeValue(i)));
                                } else if ("threshold".equalsIgnoreCase(attrName)) {
                                    ocrParam.setThreshold(Integer.parseInt(parser.getAttributeValue(i)));
                                } else if ("lang".equalsIgnoreCase(attrName)) {
                                    ocrParam.setLang(parser.getAttributeValue(i));
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
                            dOcrParams = new HashMap<String, OCRResource>();
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
                            ocrParam = new OCRResource();
                            ocrParam.setKey(key);
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
                                    ocrParam.setScale(Integer.parseInt(parser.getAttributeValue(i)));
                                } else if ("threshold".equalsIgnoreCase(attrName)) {
                                    ocrParam.setThreshold(Integer.parseInt(parser.getAttributeValue(i)));
                                } else if ("lang".equalsIgnoreCase(attrName)) {
                                    ocrParam.setLang(parser.getAttributeValue(i));
                                }
                            }
                            dOcrParams.put(deviceModel, ocrParam);
                        } else if ("Global".equalsIgnoreCase(nodeStack.peek())) {
                            attrCount = parser.getAttributeCount();
                            for (int i = 0; i < attrCount; i++) {
                                attrName = parser.getAttributeName(i);
                                if ("model".equalsIgnoreCase(attrName)) {
                                    gOcrParams = new HashMap<String, OCRResource>();
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
                        ocrParam.setExpect(parser.getText());
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

    /**
     * is modfiy
     * 
     * @return
     */
    private boolean isModfiy() {
        final File file = new File(path);
        if (!file.exists()) {
            Log.d(TAG, "the file " + path + "not found");
        } else {
            final long modify = file.lastModified();
            Log.d(TAG, "lastModfiy:" + lastModfiy + " modify:" + modify);
            if (this.lastModfiy < modify) {
                return true;
            }
        }
        return false;
    }

}
