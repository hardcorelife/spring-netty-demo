package com.example.common.protocol.request;

import com.example.common.protocol.Packet;

import static com.example.common.protocol.command.Command.HEARTBEAT_REQUEST;

/**
 * @author qiweigang
 * @date 2020-01-15 17:25
 */
public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_REQUEST;
    }
}
