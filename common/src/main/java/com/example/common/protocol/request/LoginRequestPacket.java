package com.example.common.protocol.request;

import com.example.common.protocol.Packet;
import lombok.Data;

import static com.example.common.protocol.command.Command.LOGIN_REQUEST;

/**
 * @author qiweigang
 * @date 2020-01-10 15:24
 */
@Data
public class LoginRequestPacket extends Packet {
    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
