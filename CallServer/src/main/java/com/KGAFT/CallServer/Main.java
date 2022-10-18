package com.KGAFT.CallServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    private static List<ClientCommand> commands = new ArrayList<>();
    private static HashMap<String, String> allowedUsers = new HashMap<>();
    private static void prepareCommands(DatagramSocket socket){
        commands.add(new ClientCommand((byte) 12, (packet, preparedData) -> {
            String token = new String(preparedData, 0, preparedData.length-1);
            allowedUsers.put(packet.getAddress().getHostAddress(), packet.getAddress().getHostName());
            System.out.println("Authorized");
            System.out.println(token);
            byte[] text = "Hell".getBytes();
            socket.send(new DatagramPacket(text, text.length, packet.getAddress(), packet.getPort()));
        }));
    }
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(9393);
        prepareCommands(socket);
        byte[] buffer = new byte[4097];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        while(true){
            socket.receive(packet);
            commands.forEach(command->{
                if(command.getLiteral()==packet.getData()[0]){
                    byte[] preparedData = new byte[packet.getLength()];
                    for(int counter = 1; counter<packet.getLength(); counter++){
                        preparedData[counter-1] = packet.getData()[counter];
                    }
                    try {
                        command.getAction().action(packet, preparedData);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
        }

    }
}
