package com.example.common.protocol.response;

import com.example.common.protocol.Packet;
import lombok.Data;

import java.util.List;

import static com.example.common.protocol.command.Command.CREATE_GROUP_RESPONSE;

/**
 * @author qiweigang
 * @date 2020-01-14 15:52
 */
@Data
public class CreateGroupResponsePacket extends Packet {
    private boolean success;

    private String groupId;

    private List<String> userNameList;

    @Override
    public Byte getCommand() {

        return CREATE_GROUP_RESPONSE;
    }
}
