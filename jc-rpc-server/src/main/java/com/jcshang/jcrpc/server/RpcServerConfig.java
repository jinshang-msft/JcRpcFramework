package com.jcshang.jcrpc.server;

import com.jcshang.jcrpc.codec.Decoder;
import com.jcshang.jcrpc.codec.Encoder;
import com.jcshang.jcrpc.codec.JsonDecoder;
import com.jcshang.jcrpc.codec.JsonEncoder;
import com.jcshang.jcrpc.transport.HttpTransportServer;
import com.jcshang.jcrpc.transport.TransportServer;
import lombok.Data;

/**
 * Server configuration class.
 *
 * @author JcShang
 */
@Data
public class RpcServerConfig {
    private Class<? extends TransportServer> transportClass = HttpTransportServer.class;
    private Class<? extends Encoder> encoderClass = JsonEncoder.class;
    private Class<? extends Decoder> decoderClass = JsonDecoder.class;
    private int port = 3000;
}
