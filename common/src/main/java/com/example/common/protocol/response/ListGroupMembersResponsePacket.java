package com.example.common.protocol.response;

import com.example.common.protocol.Packet;
import com.example.common.session.Session;
import lombok.Data;

import java.util.List;

import static com.example.common.protocol.command.Command.LIST_GROUP_MEMBERS_RESPONSE;

/**
 * @author qiweigang
 * @date 2020-01-14 18:43
 */
@Data
public class ListGroupMembersResponsePacket extends Packet {
    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {

        return LIST_GROUP_MEMBERS_RESPONSE;
    }
}
