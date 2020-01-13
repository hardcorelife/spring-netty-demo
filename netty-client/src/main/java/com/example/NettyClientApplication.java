package com.example;

import com.example.imclient.ImClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NettyClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(NettyClientApplication.class, args);

        //启动netty客户端
        ImClient client = new ImClient();
        client.start();
    }

}
