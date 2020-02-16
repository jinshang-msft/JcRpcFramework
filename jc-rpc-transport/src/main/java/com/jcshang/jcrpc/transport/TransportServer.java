package com.jcshang.jcrpc.transport;

/**
 * Transport server class that listens to the port after being fired up.
 * Handle request upon being called.
 * Stops listening to the port.
 *
 * @author JcShang
 */
public interface TransportServer {
    void initialize(int port, RequestHandler handler);
    void start();
    void stop();
}
