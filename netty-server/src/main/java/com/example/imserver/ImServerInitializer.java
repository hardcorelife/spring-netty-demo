package com.example.imserver;

import com.example.common.codec.PacketDecoder;
import com.example.common.codec.PacketEncoder;
import com.example.common.codec.Spliter;
import com.example.imserver.handler.LoginRequestHandler;
import com.example.imserver.handler.MessageRequestHandler;
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
//        nioSocketChannel.pipeline().addLast(new ImServerHandler());
        nioSocketChannel.pipeline().addLast(new Spliter());
        nioSocketChannel.pipeline().addLast(new PacketDecoder());
        nioSocketChannel.pipeline().addLast(new LoginRequestHandler());
        nioSocketChannel.pipeline().addLast(new MessageRequestHandler());
        nioSocketChannel.pipeline().addLast(new PacketEncoder());
    }
}
