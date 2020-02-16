package com.jcshang.jcrpc.example;

import com.jcshang.jcrpc.server.RpcServer;

public class Server {
    public static void main(String[] args) {
        RpcServer server = new RpcServer();
        server.register(CalculatorService.class, new CalculatorServiceImpl());
        server.start();
    }
}
