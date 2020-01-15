package com.example.common.protocol.response;

import com.example.common.protocol.Packet;
import lombok.Data;

import static com.example.common.protocol.command.Command.QUIT_GROUP_RESPONSE;

/**
 * @author qiweigang
 * @date 2020-01-14 18:43
 */
@Data
public class QuitGroupResponsePacket extends Packet {
    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {

        return QUIT_GROUP_RESPONSE;
    }
}
