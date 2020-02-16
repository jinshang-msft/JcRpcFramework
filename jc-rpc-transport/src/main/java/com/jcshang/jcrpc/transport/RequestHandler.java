package com.jcshang.jcrpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Class that handles the network request.
 *
 * @author JcShang
 */
public interface RequestHandler {
    void onRequest(InputStream receive, OutputStream response);
}
