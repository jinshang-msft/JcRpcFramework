package com.jcshang.jcrpc.client;

import com.jcshang.jcrpc.Peer;
import com.jcshang.jcrpc.common.utils.ReflectionUtils;
import com.jcshang.jcrpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class RandomTransportSelector implements TransportSelector {
    private List<TransportClient> clients;

    public RandomTransportSelector() {
        clients = new ArrayList<>();
    }

    @Override
    public synchronized void initialize(List<Peer> peers, int count, Class<? extends TransportClient> transportClientClass) {
        count = Math.max(count, 1);

        for (Peer peer : peers) {
            for (int i = 0; i < count; i++) {
                TransportClient client = ReflectionUtils.newInstance(transportClientClass);
                client.connect(peer);
                clients.add(client);
            }

            log.info("Connected to server: {}.", peer.getHost());
        }
    }

    @Override
    public synchronized TransportClient select() {
        int i = new Random().nextInt(clients.size());
        return clients.remove(i);
    }

    @Override
    public synchronized void release(TransportClient client) {
        clients.add(client);
    }

    @Override
    public synchronized void close() {
        for (TransportClient client : clients) {
            client.close();
        }

        clients.clear();
    }
}
