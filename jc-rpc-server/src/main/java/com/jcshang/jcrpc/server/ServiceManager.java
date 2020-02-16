package com.jcshang.jcrpc.server;

import com.jcshang.jcrpc.Request;
import com.jcshang.jcrpc.ServiceDescriptor;
import com.jcshang.jcrpc.common.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class that manages the services exposed by RPC.
 *
 * @author JcShang
 */
@Slf4j
public class ServiceManager {
    private Map<ServiceDescriptor, ServiceInstance> services;

    public ServiceManager() {
        this.services = new ConcurrentHashMap<>();
    }

    public <T> void register(Class<T> interfaceClass, T bean) {
        Method[] methods = ReflectionUtils.getPublicMethods(interfaceClass);
        for (Method m : methods) {
            ServiceInstance service = new ServiceInstance(bean, m);
            ServiceDescriptor serviceDescriptor = ServiceDescriptor.from(interfaceClass, m);

            services.put(serviceDescriptor, service);
            log.info("Service {} from class {} registered.", serviceDescriptor.getMethodName(), serviceDescriptor.getClassName());
        }
    }

    public ServiceInstance lookup(Request request) {
        ServiceDescriptor serviceDescriptor = request.getService();
        return services.get(serviceDescriptor);
    }
}
