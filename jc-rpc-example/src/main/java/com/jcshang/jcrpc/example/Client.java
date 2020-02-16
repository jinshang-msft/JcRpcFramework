package com.jcshang.jcrpc.example;

import com.jcshang.jcrpc.client.RpcClient;

public class Client {
    public static void main (String[] args) {
        RpcClient client = new RpcClient();
        CalculatorService calculatorService = client.getProxy(CalculatorService.class);

        int r1 = calculatorService.add(1, 2);
        int r2 = calculatorService.subtract(2, 1);

        System.out.println(r1);
        System.out.println(r2);
    }
}
