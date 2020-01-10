package com.example.common.util;

import com.example.common.attribute.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @author qiweigang
 * @date 2020-01-10 16:33
 */
public class LoginUtil {
    public static void markAsLogin(Channel channel) {

        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);

        return loginAttr.get() != null;
    }
}
