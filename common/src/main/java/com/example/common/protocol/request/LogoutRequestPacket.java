package com.example.common.protocol.request;

import com.example.common.protocol.Packet;
import lombok.Data;

import static com.example.common.protocol.command.Command.LOGOUT_REQUEST;

/**
 * @author qiweigang
 * @date 2020-01-14 15:51
 */
@Data
public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {

        return LOGOUT_REQUEST;
    }
}
