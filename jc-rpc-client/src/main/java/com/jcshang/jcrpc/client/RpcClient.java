package com.jcshang.jcrpc.client;

import com.jcshang.jcrpc.codec.Decoder;
import com.jcshang.jcrpc.codec.Encoder;
import com.jcshang.jcrpc.common.utils.ReflectionUtils;

import java.lang.reflect.Proxy;

public class RpcClient {
    private RpcClientConfig config;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector transportSelector;

    public RpcClient() {
        this(new RpcClientConfig());
    }

    public RpcClient(RpcClientConfig config) {
        this.config = config;

        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());

        this.transportSelector = ReflectionUtils.newInstance(config.getTransportSelectorClass());
        this.transportSelector.initialize(
                this.config.getServers(),
                this.config.getConnectCount(),
                this.config.getTransportClass()
        );
    }

    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[] { clazz },
                new RemoteInvoker(clazz, encoder, decoder, transportSelector)
        );
    }
}
