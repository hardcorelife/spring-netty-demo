package com.example.common.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.example.common.serialize.Serializer;
import com.example.common.serialize.SerializerAlogrithm;

/**
 * @author qiweigang
 * @date 2020-01-10 15:28
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializerAlogrithm() {
        return SerializerAlogrithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {

        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {

        return JSON.parseObject(bytes, clazz);
    }

}
