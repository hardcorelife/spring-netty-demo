package com.example.imserver;

import com.example.common.protocol.Packet;
import com.example.common.protocol.PacketCodeC;
import com.example.common.protocol.request.LoginRequestPacket;
import com.example.common.protocol.response.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author qiweigang
 * @date 2020-01-10 11:15
 */
@Slf4j
public class ImServerHandler extends ChannelInboundHandlerAdapter {
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        super.channelActive(ctx);
//    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(new Date() + ": 客户端开始登录……");
        ByteBuf requestByteBuf = (ByteBuf) msg;

        log.info(new Date() + ": 服务端读到数据 -> " + requestByteBuf.toString(CharsetUtil.UTF_8));

        Packet packet = PacketCodeC.INSTANCE.decode(requestByteBuf);

        if (packet instanceof LoginRequestPacket) {
            // 登录流程
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(packet.getVersion());
            if (valid(loginRequestPacket)) {
                loginResponsePacket.setSuccess(true);
                System.out.println(new Date() + ": 登录成功!");
            } else {
                loginResponsePacket.setReason("账号密码校验失败");
                loginResponsePacket.setSuccess(false);
                System.out.println(new Date() + ": 登录失败!");
            }
            // 登录响应
            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        }
//        log.info(new Date() + ": 服务端读到数据 -> " + msg.toString());
//        // 回复数据到客户端
//        log.info(new Date() + ": 服务端写出数据");
//
//        byte[] bytes = "客户端，你好，收到，请讲， over！".getBytes(Charset.forName("utf-8"));
//        ByteBuf buffer = ctx.alloc().heapBuffer(bytes.length);
//        buffer.writeBytes(bytes);
//        ctx.channel().writeAndFlush(buffer);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
