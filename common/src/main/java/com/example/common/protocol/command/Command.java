package com.example.common.protocol.command;

/**
 * @author qiweigang
 * @date 2020-01-10 15:22
 */
public interface Command {

    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE = 4;
}