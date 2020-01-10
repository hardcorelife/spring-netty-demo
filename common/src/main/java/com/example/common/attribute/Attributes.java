package com.example.common.attribute;

import io.netty.util.AttributeKey;

/**
 * @author qiweigang
 * @date 2020-01-10 16:32
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
