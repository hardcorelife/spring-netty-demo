package com.example.common.protocol.response;

import com.example.common.protocol.Packet;
import lombok.Data;

import static com.example.common.protocol.command.Command.MESSAGE_RESPONSE;

/**
 * @author qiweigang
 * @date 2020-01-10 16:26
 */
@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {

        return MESSAGE_RESPONSE;
    }
}
