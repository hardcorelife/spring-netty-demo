package com.example.common.protocol.response;

import com.example.common.protocol.Packet;
import lombok.Data;

import static com.example.common.protocol.command.Command.LOGOUT_RESPONSE;

/**
 * @author qiweigang
 * @date 2020-01-14 15:53
 */
@Data
public class LogoutResponsePacket extends Packet {
    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {

        return LOGOUT_RESPONSE;
    }
}
