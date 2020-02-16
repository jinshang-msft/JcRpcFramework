package com.jcshang.jcrpc.client;

import com.jcshang.jcrpc.Peer;
import com.jcshang.jcrpc.codec.Decoder;
import com.jcshang.jcrpc.codec.Encoder;
import com.jcshang.jcrpc.codec.JsonDecoder;
import com.jcshang.jcrpc.codec.JsonEncoder;
import com.jcshang.jcrpc.transport.HttpTransportClient;
import com.jcshang.jcrpc.transport.TransportClient;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class RpcClientConfig {
    private Class<? extends TransportClient> transportClass = HttpTransportClient.class;
    private Class<? extends Encoder> encoderClass = JsonEncoder.class;
    private Class<? extends Decoder> decoderClass = JsonDecoder.class;
    private Class<? extends TransportSelector> transportSelectorClass = RandomTransportSelector.class;
    private int connectCount = 1;
    private List<Peer> servers = Arrays.asList(
            new Peer("127.0.0.1", 3000)
    );
}
