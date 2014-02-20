/**
 * @(#) CaptureTask.java Created on 2012-12-18
 *
 * Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.android.screen.capture;

/**
 * The class <code>CaptureTask</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public final class CaptureTask {

    protected int len;

    protected byte[] array;

    protected CaptureTaskCallBack back;

    public CaptureTask(byte[] array, CaptureTaskCallBack back) {
        super();
        this.array = array;
        this.back = back;
    }

    public byte[] getArray() {
        return array;
    }

    public void setArray(byte[] array) {
        this.array = array;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public CaptureTaskCallBack getBack() {
        return back;
    }

    public void setBack(CaptureTaskCallBack back) {
        this.back = back;
    }

    public interface CaptureTaskCallBack {

        public void callBack(CaptureTask captureTask);

    }

}
