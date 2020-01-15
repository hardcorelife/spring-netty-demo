package com.example.common.protocol.response;

import com.example.common.protocol.Packet;

import static com.example.common.protocol.command.Command.HEARTBEAT_RESPONSE;

/**
 * @author qiweigang
 * @date 2020-01-15 17:26
 */
public class HeartBeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_RESPONSE;
    }
}
