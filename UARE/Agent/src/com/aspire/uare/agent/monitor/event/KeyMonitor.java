///**
// * @(#) KeyManager.java Created on 2012-11-8
// *
// * Copyright (c) 2012 Aspire. All Rights Reserved
// */
//package com.aspire.uare.agent.monitor.event;
//
//import android.util.Log;
//
///**
// * The class <code>KeyMonitor</code>
// * 
// * @author ouyangfeng
// * @version 1.0
// */
//public class KeyMonitor extends InputReader {
//
//    private static final String TAG = "KeyMonitor";
//
//    public native boolean nativeKeyInit(String filename, CallBack mCallBack);
//
//    public native boolean nativeKeyStart();
//
//    public native boolean nativeKeyStop();
//
//    /**
//     * Constructor
//     * 
//     * @param device
//     */
//    public KeyMonitor(String device) {
//        super(device);
//        nativeKeyInit(device, mCallBack);
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
//     * 
//     * @see com.inspurworld.agent.InputReader.manager.InputManager#stop()
//     */
//    @Override
//    public boolean stop() {
//        return nativeKeyStop();
//    }
//
//    /**
//     * {@inheritDoc}
//     * 
//     * @see com.inspurworld.agent.monitor.event.InputReader#nativeStart()
//     */
//    @Override
//    public boolean nativeStart() {
//        nativeKeyStart();
//        return true;
//    }
//
//    /**
//     * {@inheritDoc}
//     * @see com.inspurworld.agent.monitor.event.InputReader#nativeStop()
//     */
//    @Override
//    public boolean nativeStop() {
//        nativeKeyStop();
//        return true;
//    }
//    
//    //public interface KeyDispather(int key);
//
//}
