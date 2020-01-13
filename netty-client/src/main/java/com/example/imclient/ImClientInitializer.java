package com.example.imclient;

import com.example.common.codec.PacketDecoder;
import com.example.common.codec.PacketEncoder;
import com.example.imclient.handler.LoginResponseHandler;
import com.example.imclient.handler.MessageResponseHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;


/**
 * @author qiweigang
 * @date 2020-01-10 11:14
 */
public class ImClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // 指定连接数据读写逻辑
//        socketChannel.pipeline().addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
//        socketChannel.pipeline().addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
//        socketChannel.pipeline().addLast(new ImClientHandler());
        socketChannel.pipeline().addLast(new PacketDecoder());
        socketChannel.pipeline().addLast(new LoginResponseHandler());
        socketChannel.pipeline().addLast(new MessageResponseHandler());
        socketChannel.pipeline().addLast(new PacketEncoder());
    }
}
