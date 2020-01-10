package com.example.common.protocol.request;

import com.example.common.protocol.Packet;
import lombok.Data;

import static com.example.common.protocol.command.Command.MESSAGE_REQUEST;

/**
 * @author qiweigang
 * @date 2020-01-10 16:24
 */
@Data
public class MessageRequestPacket extends Packet {
    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
