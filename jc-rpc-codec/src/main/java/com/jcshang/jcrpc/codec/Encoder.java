package com.jcshang.jcrpc.codec;

/**
 * Serializes the object into byte array.
 */
public interface Encoder {
    byte[] encode(Object obj);
}
