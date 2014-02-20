/**
 * @(#) IPFind.java Created on 2012-8-31
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.braver.test;

import java.io.IOException;

import org.junit.Test;

import com.braver.tools.IPFind;

/**
 * The class <code>IPFind</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class IPFindTest {

    @Test
    public void testIpFind() {
        try {
            new IPFind(getClass().getResourceAsStream("ips.txt")).find(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
