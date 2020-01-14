package com.example.common.protocol.request;

import com.example.common.protocol.Packet;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.example.common.protocol.command.Command.MESSAGE_REQUEST;

/**
 * @author qiweigang
 * @date 2020-01-10 16:24
 */
@Data
@NoArgsConstructor
public class MessageRequestPacket extends Packet {

    private String toUserId;
    private String message;

    public MessageRequestPacket(String toUserId, String message) {
        this.toUserId = toUserId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
