package com.jcshang.jcrpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * Deserializer implementation based on JSON.
 */
public class JsonDecoder implements Decoder {
    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}
