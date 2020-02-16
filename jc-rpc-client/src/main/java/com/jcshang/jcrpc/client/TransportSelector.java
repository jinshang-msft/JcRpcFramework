package com.jcshang.jcrpc.client;

import com.jcshang.jcrpc.Peer;
import com.jcshang.jcrpc.transport.TransportClient;

import java.util.List;

/**
 * Select the server to be connected.
 *
 * @author JcShang
 */
public interface TransportSelector {
    void initialize(List<Peer> peers, int count, Class<? extends TransportClient> transportClientClass);
    TransportClient select();
    void release(TransportClient client);
    void close();
}
