package com.example.common.util;

import java.util.UUID;

/**
 * @author qiweigang
 * @date 2020-01-14 15:55
 */
public class IDUtil {
    public static String randomId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
