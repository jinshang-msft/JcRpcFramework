package com.jcshang.jcrpc.server;

import com.jcshang.jcrpc.Request;
import com.jcshang.jcrpc.common.utils.ReflectionUtils;

/**
 * Invoke the service based on the given service instance and request details.
 *
 * @author JcShang
 */
public class ServiceInvoker {
    public Object invoke(ServiceInstance service, Request request) {
        return ReflectionUtils.invoke(service.getTarget(), service.getMethod(), request.getParameters());
    }
}
