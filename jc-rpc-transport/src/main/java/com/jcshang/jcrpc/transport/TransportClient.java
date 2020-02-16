package com.jcshang.jcrpc.transport;

import com.jcshang.jcrpc.Peer;

import java.io.InputStream;

/**
 * Transport client class that creates the connection, sends out the
 * data and waits for the response. Finally, it closes the connection.
 *
 * @author JcShang
 */
public interface TransportClient {
    void connect(Peer peer);
    InputStream write(InputStream data);
    void close();
}
