package com.example.imserver;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;

import lombok.extern.slf4j.Slf4j;

/**
 * @author qiweigang
 * @date 2020-01-10 11:15
 */
@Slf4j
public class ImServerInitializer extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
        log.info("服务端启动中");
        //指定连接数据读写逻辑
//        nioSocketChannel.pipeline().addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
//        nioSocketChannel.pipeline().addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
        nioSocketChannel.pipeline().addLast(new ImServerHandler());
    }
}
