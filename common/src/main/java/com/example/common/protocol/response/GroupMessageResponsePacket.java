package com.example.common.protocol.response;

import com.example.common.protocol.Packet;
import com.example.common.session.Session;
import lombok.Data;

import static com.example.common.protocol.command.Command.GROUP_MESSAGE_RESPONSE;

/**
 * @author qiweigang
 * @date 2020-01-15 14:48
 */
@Data
public class GroupMessageResponsePacket extends Packet {

    private String fromGroupId;

    private Session fromUser;

    private String message;

    @Override
    public Byte getCommand() {

        return GROUP_MESSAGE_RESPONSE;
    }
}
