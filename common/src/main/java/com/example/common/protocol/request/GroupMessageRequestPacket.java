package com.example.common.protocol.request;

import com.example.common.protocol.Packet;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.example.common.protocol.command.Command.GROUP_MESSAGE_REQUEST;

/**
 * @author qiweigang
 * @date 2020-01-15 14:44
 */
@Data
@NoArgsConstructor
public class GroupMessageRequestPacket extends Packet {
    private String toGroupId;
    private String message;

    public GroupMessageRequestPacket(String toGroupId, String message) {
        this.toGroupId = toGroupId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_REQUEST;
    }
}
