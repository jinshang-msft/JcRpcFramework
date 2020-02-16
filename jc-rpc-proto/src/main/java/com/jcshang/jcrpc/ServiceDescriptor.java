package com.jcshang.jcrpc;

import javafx.concurrent.Service;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Describes a service.
 * @author JcShang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor {
    private String className;
    private String methodName;
    private String returnType;
    private String[] parameterTypes;

    public static ServiceDescriptor from(Class clazz, Method method) {
        ServiceDescriptor serviceDescriptor = new ServiceDescriptor();
        serviceDescriptor.setClassName(clazz.getName());
        serviceDescriptor.setMethodName(method.getName());
        serviceDescriptor.setReturnType(method.getReturnType().getName());

        Class[] parameterClasses = method.getParameterTypes();
        String[] parameterTypes = new String[parameterClasses.length];
        for (int i = 0; i < parameterClasses.length; i++) {
            parameterTypes[i] = parameterClasses[i].getName();
        }

        serviceDescriptor.setParameterTypes(parameterTypes);

        return serviceDescriptor;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        ServiceDescriptor that = (ServiceDescriptor) obj;
        return this.hashCode() == that.hashCode();
    }

    @Override
    public String toString() {
        return className + methodName + returnType + Arrays.toString(parameterTypes);
    }
}
