package com.example.imclient.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author qiweigang
 * @date 2020-01-14 15:45
 */
public interface ConsoleCommand {
    void exec(Scanner scanner, Channel channel);
}
