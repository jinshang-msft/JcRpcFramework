package com.jcshang.jcrpc.server;

import com.jcshang.jcrpc.Request;
import com.jcshang.jcrpc.Response;
import com.jcshang.jcrpc.codec.Decoder;
import com.jcshang.jcrpc.codec.Encoder;
import com.jcshang.jcrpc.common.utils.ReflectionUtils;
import com.jcshang.jcrpc.transport.RequestHandler;
import com.jcshang.jcrpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
public class RpcServer {
    private RpcServerConfig config;
    private TransportServer transportServer;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;

    public RpcServer() {
        this(new RpcServerConfig());
    }

    public RpcServer(RpcServerConfig config) {
        this.config = config;
        this.transportServer = ReflectionUtils.newInstance(config.getTransportClass());
        this.transportServer.initialize(config.getPort(), this.handler);

        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();
    }

    public <T> void register(Class<T> interfaceClass, T bean) {
        serviceManager.register(interfaceClass, bean);
    }

    public void start() {
        this.transportServer.start();
    }

    public void stop() {
        this.transportServer.stop();
    }

    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream receive, OutputStream response) {
            Response res = new Response();

            try {
                byte[] inputBytes = IOUtils.readFully(receive, receive.available());
                Request request = decoder.decode(inputBytes, Request.class);

                log.info("Get request from client: {}.", request);

                ServiceInstance serviceInstance = serviceManager.lookup(request);
                Object returnValue = serviceInvoker.invoke(serviceInstance, request);

                res.setData(returnValue);
            } catch (Exception e) {
                log.warn(e.getMessage(), e);
                res.setCode(1);
                res.setMessage("Rpc server got an error: " + e.getClass().getName() + " - " + e.getMessage());
            } finally {
                try {
                    byte[] outputBytes = encoder.encode(res);
                    response.write(outputBytes);

                    log.info("Sent response to the client for request {}.", receive.getClass().getName());
                } catch (IOException e) {
                    log.warn(e.getMessage(), e);
                }
            }
        }
    };
}
