package com.example.imclient;

import com.example.common.codec.PacketDecoder;
import com.example.common.codec.PacketEncoder;
import com.example.common.codec.Spliter;
import com.example.imclient.handler.*;
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
        socketChannel.pipeline().addLast(new Spliter());
        socketChannel.pipeline().addLast(new PacketDecoder());
        // 登录响应处理器
        socketChannel.pipeline().addLast(new LoginResponseHandler());
        // 收消息处理器
        socketChannel.pipeline().addLast(new MessageResponseHandler());
        // 创建群响应处理器
        socketChannel.pipeline().addLast(new CreateGroupResponseHandler());
        // 加群响应处理器
        socketChannel.pipeline().addLast(new JoinGroupResponseHandler());
        // 退群响应处理器
        socketChannel.pipeline().addLast(new QuitGroupResponseHandler());
        // 获取群成员响应处理器
        socketChannel.pipeline().addLast(new ListGroupMembersResponseHandler());
        // 登出响应处理器
        socketChannel.pipeline().addLast(new LogoutResquestHandler());
        socketChannel.pipeline().addLast(new PacketEncoder());
    }
}
