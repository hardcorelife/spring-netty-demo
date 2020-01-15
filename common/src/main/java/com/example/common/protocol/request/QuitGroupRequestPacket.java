package com.example.common.protocol.request;

import com.example.common.protocol.Packet;
import lombok.Data;

import static com.example.common.protocol.command.Command.QUIT_GROUP_REQUEST;

/**
 * @author qiweigang
 * @date 2020-01-14 18:41
 */
@Data
public class QuitGroupRequestPacket extends Packet {
    private String groupId;

    @Override
    public Byte getCommand() {

        return QUIT_GROUP_REQUEST;
    }
}
