package com.jcshang.jcrpc;

import lombok.Data;

/**
 * A response in the RPC communication.
 *
 * @author JcShang
 */

@Data
public class Response {

    /**
     * Status code: 0 - Success. Failure, otherwise.
     */
    private int code = 0;

    /**
     * Error message.
     */
    private String message = "ok";

    /**
     * Payload of the response.
     */
    private Object data;
}
