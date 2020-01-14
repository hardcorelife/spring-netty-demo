package com.example.imserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;


/**
 * @author qiweigang
 * @date 2020-01-10 10:18
 */
@Slf4j
public class ImServer {
    private static final int PORT = 8090;

    public void start() {
        //new 主线程组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        //new 工作线程组
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        final AttributeKey<Object> clientKey = AttributeKey.newInstance("clientKey");
        final ServerBootstrap bootstrap = new ServerBootstrap()
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .attr(AttributeKey.newInstance("serverName"), "nettyServer")
                .childAttr(clientKey, "clientValue")
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ImServerInitializer());


        bind(bootstrap, PORT);
    }

    private void bind(final ServerBootstrap bootstrap, final int port) {
        bootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    log.info("端口[" + port + "]绑定成功!");
                } else {
                    log.info("端口[" + port + "]绑定失败!");
                    bind(bootstrap, port + 1);
                }
            }
        });
    }
}
