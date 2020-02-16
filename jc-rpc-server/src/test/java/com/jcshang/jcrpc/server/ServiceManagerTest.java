package com.jcshang.jcrpc.server;

import com.jcshang.jcrpc.Request;
import com.jcshang.jcrpc.ServiceDescriptor;
import com.jcshang.jcrpc.common.utils.ReflectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ServiceManagerTest {

    ServiceManager serviceManager;

    @Before
    public void init() {
        serviceManager = new ServiceManager();
    }

    @Test
    public void register() {
        TestInterface bean = new TestClass();
        serviceManager.register(TestInterface.class, bean);
    }

    @Test
    public void lookup() {
        TestInterface bean = new TestClass();
        serviceManager.register(TestInterface.class, bean);

        Method method =  ReflectionUtils.getPublicMethods(TestInterface.class)[0];
        ServiceDescriptor serviceDescriptor = ServiceDescriptor.from(TestInterface.class, method);

        Request request = new Request();
        request.setService(serviceDescriptor);

        ServiceInstance serviceInstance = serviceManager.lookup(request);
        assertNotNull(serviceInstance);
    }
}
