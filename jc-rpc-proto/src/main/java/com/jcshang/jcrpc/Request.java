package com.jcshang.jcrpc;

import lombok.Data;

/**
 * A request in the RPC communication.
 *
 * @author JcShang
 */

@Data
public class Request {

    private ServiceDescriptor service;

    private Object[] parameters;
}
