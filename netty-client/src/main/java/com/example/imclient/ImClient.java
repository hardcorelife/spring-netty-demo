package com.example.imclient;


import com.example.common.protocol.PacketCodeC;
import com.example.common.protocol.request.LoginRequestPacket;
import com.example.common.protocol.request.MessageRequestPacket;
import com.example.common.util.LoginUtil;
import com.example.common.util.SessionUtil;
import com.example.imclient.console.ConsoleCommandManager;
import com.example.imclient.console.LoginConsoleCommand;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


/**
 * @author qiweigang
 * @date 2020-01-10 10:30
 */
@Slf4j
public class ImClient {

    private static final int MAX_RETRY = 5;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8090;

    public void start() {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap()
                // 1.指定线程模型
                .group(workerGroup)
                // 2.指定 IO 类型为 NIO
                .channel(NioSocketChannel.class)
                // 绑定自定义属性到 channel
                .attr(AttributeKey.newInstance("clientName"), "nettyClient")
                // 设置TCP底层属性
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                // 3.IO 处理逻辑
                .handler(new ImClientInitializer());

        // 4.建立连接
        connect(bootstrap, HOST, PORT , MAX_RETRY);
    }

    /**
     * 连接
     *
     * @param bootstrap
     * @param host
     * @param port
     */
    private void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 连接成功，启动控制台线程……");
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
            } else if (retry == 0) {
                log.info("重试次数已用完，连接失败！");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                log.info(new Date() + ": 连接失败，第" + order + "次重连……");
                // 本次重连的间隔
                int delay = 1 << order;
                bootstrap.config().group().schedule(() ->
                        connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }

    private void startConsoleThread(Channel channel) {
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        Scanner sc = new Scanner(System.in);
//        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (!SessionUtil.hasLogin(channel)) {
                    loginConsoleCommand.exec(sc, channel);
                } else {
                    consoleCommandManager.exec(sc, channel);
                }
            }
//            while (!Thread.interrupted()) {
//                if (!SessionUtil.hasLogin(channel)) {
//                    System.out.println("输入消息发送至服务端: ");
//                    Scanner sc = new Scanner(System.in);
//                    String line = sc.nextLine();
//
//                    channel.writeAndFlush(new MessageRequestPacket(line));
//                }
//            }
//            while (!Thread.interrupted()) {
//                if (!SessionUtil.hasLogin(channel)) {
//                    System.out.print("输入用户名登录: ");
//                    String username = sc.nextLine();
//                    loginRequestPacket.setUsername(username);
//
//                    // 密码使用默认的
//                    loginRequestPacket.setPassword("pwd");
//
//                    // 发送登录数据包
//                    channel.writeAndFlush(loginRequestPacket);
//                    waitForLoginResponse();
//                } else {
//                    String toUserId = sc.next();
//                    String message = sc.next();
//                    channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
//                }
//            }
        }).start();
    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
