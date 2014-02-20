/**
 * @(#) Client.java Created on 2012-11-20
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.braver.protobuffer.rpc;

import com.braver.protobuffer.rpc.PersonProtos.Person;
import com.braver.protobuffer.rpc.PersonProtos.PhoneService;
import com.braver.protobuffer.rpc.PersonProtos.PhoneService.BlockingInterface;
import com.google.protobuf.BlockingRpcChannel;
import com.google.protobuf.RpcController;
import com.google.protobuf.ServiceException;
import com.googlecode.protobuf.socketrpc.RpcChannels;
import com.googlecode.protobuf.socketrpc.RpcConnectionFactory;
import com.googlecode.protobuf.socketrpc.SocketRpcConnectionFactories;
import com.googlecode.protobuf.socketrpc.SocketRpcController;

/**
 * The class <code>Client</code>
 * 
 * @author ouyangfeng
 * @version 1.0
 */
public class Client {
    private int port;
    private String host;
    private int size;
    private int count;

    public Client(int port, String host, int size, int count) {
        super();
        this.port = port;
        this.host = host;
        this.size = size;
        this.count = count;
    }

    public long run() {
        // Create channel
        RpcConnectionFactory connectionFactory = SocketRpcConnectionFactories.createRpcConnectionFactory(host, port);
        BlockingRpcChannel channel = RpcChannels.newBlockingRpcChannel(connectionFactory);

        // Call service
        BlockingInterface service = PhoneService.newBlockingStub(channel);
        RpcController controller = new SocketRpcController();
        // initiate the message
        Person.Builder message = Person.newBuilder();
        message.setName("value");
        message.setId(123);
        // …

        long start = 0;
        long end = 0;
        try {
            start = System.currentTimeMillis();
            for (int i = 0; i < count; i++) {
                Person person = service.getPhone(controller, message.build());
               // System.out.println(person);
            }
            end = System.currentTimeMillis();
            System.out.println(end - start);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        // Check success
        if (controller.failed()) {
            System.err.println(String.format("Rpc failed %s : %s", ((SocketRpcController) controller).errorReason(),
                    controller.errorText()));
        }

        return end - start;
    }

    public static void main(String[] args) {
        args = new String[]{"127.0.0.1","8080" , "10" , "100000"};
        if (args.length != 4) {
            System.out.println("Usage: Client host port dataSize count");
            return;
        }
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        int size = Integer.parseInt(args[2]);
        int count = Integer.parseInt(args[3]);

        new Client(port, host, size, count).run();
    }
}
