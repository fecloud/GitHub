///**
// * @(#) TouchManager.java Created on 2012-11-9
// *
// * Copyright (c) 2012 Aspire. All Rights Reserved
// */
//package com.aspire.uare.agent.monitor.event;
//
//import android.util.Log;
//
//
///**
// * The class <code>TouchMonitor</code>
// * 
// * @author ouyangfeng
// * @version 1.0
// */
//public class TouchMonitor extends InputReader {
//
//    private static final String TAG = "TouchMonitor";
//
//    public native boolean nativeTouchInit(String filename, CallBack mCallBack);
//
//    public native boolean nativeTouchStart();
//
//    public native boolean nativeTouchStop();
//
//    /**
//     * Constructor
//     * 
//     * @param device
//     */
//    public TouchMonitor(String device) {
//        super(device);
//        nativeTouchInit(device, mCallBack);
//    }
//
//    /**
//     * {@inheritDoc}
//     * 
//     * @see com.inspurworld.agent.InputReader.manager.InputManager#process(com.inspurworld.agent.getevent.InputEvent)
//     */
//    @Override
//    public boolean process(InputEvent event) {
//        Log.d(TAG, "process");
//        return false;
//    }
//
//    /**
//     * {@inheritDoc}
//     * @see com.inspurworld.agent.monitor.event.InputReader#nativeStart()
//     */
//    @Override
//    public boolean nativeStart() {
//        nativeTouchStart();
//        return true;
//    }
//
//    /**
//     * {@inheritDoc}
//     * @see com.inspurworld.agent.monitor.event.InputReader#nativeStop()
//     */
//    @Override
//    public boolean nativeStop() {
//        nativeTouchStop();
//        return true;
//    }
//
//}
