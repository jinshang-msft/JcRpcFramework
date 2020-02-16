package com.jcshang.jcrpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * Serializer implementation based on JSON.
 */
public class JsonEncoder implements Encoder {
    @Override
    public byte[] encode(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}
