package com.example.common.protocol.request;

import com.example.common.protocol.Packet;
import lombok.Data;

import java.util.List;

import static com.example.common.protocol.command.Command.CREATE_GROUP_REQUEST;

/**
 * @author qiweigang
 * @date 2020-01-14 15:49
 */
@Data
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {

        return CREATE_GROUP_REQUEST;
    }
}
