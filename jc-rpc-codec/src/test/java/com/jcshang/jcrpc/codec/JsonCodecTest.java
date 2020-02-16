package com.jcshang.jcrpc.codec;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class JsonCodecTest {

    @Test
    public void serializationTest() {
        Encoder encoder = new JsonEncoder();
        TestBean bean = new TestBean();

        bean.setName("jcshang");
        bean.setAge(27);

        byte[] bytes = encoder.encode(bean);

        assertNotNull(bytes);

        Decoder decoder = new JsonDecoder();
        TestBean bean2 = decoder.decode(bytes, TestBean.class);

        assertEquals(bean.getName(), bean2.getName());
        assertEquals(bean.getAge(), bean2.getAge());
    }
}
