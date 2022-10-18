package com.kgaft.michatmessengerserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class MessengerServerApplication {

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication.run(MessengerServerApplication.class, args);

    }

}
