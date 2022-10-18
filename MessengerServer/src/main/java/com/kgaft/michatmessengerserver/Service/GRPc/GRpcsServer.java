package com.kgaft.michatmessengerserver.Service.GRPc;

import com.kgaft.michatmessengerserver.Service.GRPc.Calls.CallService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
@Scope(scopeName = "singleton")
public class GRpcsServer {
    @Autowired
    private MessageService messageService;
    @Autowired
    private CallService callService;
    @PostConstruct
    public void start(){
        new Thread(()->{
            try {
                Server server = ServerBuilder.forPort(8383).addService(messageService).build();
                server.start();
                System.out.println("Grpc started");
                server.awaitTermination();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
        new Thread(()->{
            try {
                Server server = ServerBuilder.forPort(9393).addService(callService).build();
                server.start();
                System.out.println("CallServer started");
                server.awaitTermination();
            }catch (Exception e){

            }
        }).start();
    }
}
