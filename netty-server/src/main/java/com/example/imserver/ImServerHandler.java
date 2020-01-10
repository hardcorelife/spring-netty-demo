package com.example.imserver;

import com.example.common.protocol.Packet;
import com.example.common.protocol.PacketCodeC;
import com.example.common.protocol.request.LoginRequestPacket;
import com.example.common.protocol.request.MessageRequestPacket;
import com.example.common.protocol.response.LoginResponsePacket;
import com.example.common.protocol.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

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
        ByteBuf requestByteBuf = (ByteBuf) msg;

//        log.info(new Date() + ": 服务端读到数据 -> " + requestByteBuf.toString(CharsetUtil.UTF_8));

        Packet packet = PacketCodeC.INSTANCE.decode(requestByteBuf);

        if (packet instanceof LoginRequestPacket) {
            System.out.println(new Date() + ": 客户端开始登录……");
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
        } else if (packet instanceof MessageRequestPacket) {
            // 客户端发来消息
            MessageRequestPacket messageRequestPacket = ((MessageRequestPacket) packet);

            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());
            messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
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
