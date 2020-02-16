package com.jcshang.jcrpc;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Node in network communication.
 *
 * @author JcShang
 */

@Data
@AllArgsConstructor
public class Peer {

    private String host;

    private int port;
}
