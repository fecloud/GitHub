package com.braver.protobuffer.rpc;

/**
 * @(#) Server.java Created on 2012-11-20
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */

import java.util.concurrent.Executors;

import com.braver.protobuffer.rpc.service.impl.PhoneServiceImpl;
import com.googlecode.protobuf.socketrpc.RpcServer;
import com.googlecode.protobuf.socketrpc.ServerRpcConnectionFactory;
import com.googlecode.protobuf.socketrpc.SocketRpcConnectionFactories;

/**
 * The class <code>Server</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class Server {
    private int port;
    private int threadPoolSize;

    public Server(int port, int threadPoolSize) {
        this.port = port;
        this.threadPoolSize = threadPoolSize;
    }

    public void run() {
        // Start server
        ServerRpcConnectionFactory rpcConnectionFactory = SocketRpcConnectionFactories
                .createServerRpcConnectionFactory(port);
        RpcServer server = new RpcServer(rpcConnectionFactory, Executors.newFixedThreadPool(threadPoolSize), true);
        server.registerBlockingService(PersonProtos.PhoneService.newReflectiveBlockingService(new PhoneServiceImpl()));
        server.run();
    }

    public static void main(String[] args) {
        args = new String[] { "8080", "2" };
        if (args.length != 2) {
            System.out.println("Usage: Server port thread_pool_size");
            return;
        }

        int port = Integer.parseInt(args[0]);
        int size = Integer.parseInt(args[1]);

        new Server(port, size).run();
    }
}
