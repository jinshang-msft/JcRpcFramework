package com.jcshang.jcrpc.codec;

/**
 * Deserializes the byte array into designated object.
 */
public interface Decoder {
    <T> T decode(byte[] bytes, Class<T> clazz);
}
