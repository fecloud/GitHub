/**
 * @(#) ClientCount.java Created on 2012-12-13
 *
 * Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.android;

/**
 * The class <code>ClientCount</code>
 * 
 * count client num
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class ClientCount {

    private int clientCount;

    public synchronized int add() {
        clientCount++;
        return clientCount;
    }

    public synchronized int delete() {
        clientCount--;
        return clientCount;
    }

    public synchronized int get() {
        return clientCount;
    }

}
