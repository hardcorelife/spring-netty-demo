package com.example.common.protocol.response;

import com.example.common.protocol.Packet;
import lombok.Data;

import static com.example.common.protocol.command.Command.LOGIN_RESPONSE;

/**
 * @author qiweigang
 * @date 2020-01-10 15:50
 */
@Data
public class LoginResponsePacket extends Packet {
    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
