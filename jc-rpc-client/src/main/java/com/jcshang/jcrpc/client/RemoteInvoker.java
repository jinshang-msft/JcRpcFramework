package com.jcshang.jcrpc.client;

import com.jcshang.jcrpc.Request;
import com.jcshang.jcrpc.Response;
import com.jcshang.jcrpc.ServiceDescriptor;
import com.jcshang.jcrpc.codec.Decoder;
import com.jcshang.jcrpc.codec.Encoder;
import com.jcshang.jcrpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Proxy class to invoke remote service.
 *
 * @author JcShang
 */
@Slf4j
public class RemoteInvoker implements InvocationHandler {
    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector transportSelector;

    RemoteInvoker(Class clazz,
                  Encoder encoder,
                  Decoder decoder,
                  TransportSelector transportSelector) {
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.transportSelector = transportSelector;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        Request request = new Request();
        request.setService(ServiceDescriptor.from(clazz, method));
        request.setParameters(objects);

        Response response = invokeRemote(request);
        if (response == null || response.getCode() != 0) {
            throw new IllegalStateException("Failed to invoke the remote service: {}." + response);
        }

        return response.getData();
    }

    private Response invokeRemote(Request request) {
        TransportClient client = null;
        Response response = null;

        try {
            client = transportSelector.select();
            byte[] outputBytes = encoder.encode(request);
            InputStream received = client.write(new ByteArrayInputStream(outputBytes));

            byte[] inputBytes = IOUtils.readFully(received, received.available());
            response = decoder.decode(inputBytes, Response.class);
        } catch (IOException e) {
            log.warn(e.getMessage(), e);
            response = new Response();
            response.setCode(1);
            response.setMessage("RpcClient got an error: " + e.getClass().getName() + " - " +  e.getMessage());
        } finally {
            if (client != null) {
                transportSelector.release(client);
            }
        }

        return response;
    }
}
