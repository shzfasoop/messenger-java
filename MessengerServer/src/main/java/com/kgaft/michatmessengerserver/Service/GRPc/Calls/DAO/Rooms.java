package com.kgaft.michatmessengerserver.Service.GRPc.Calls.DAO;

import com.kgaft.michatmessengerserver.Service.AuthorizationService;
import com.kgaft.michatmessengerserver.Service.GRPc.Calls.Entity.Room;
import com.kgaft.michatmessengerserver.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.function.UnaryOperator;

@Component
@Scope(scopeName = "singleton")
public class Rooms {
    private ArrayList<Room> rooms = new ArrayList<>();

    @Autowired
    private UserInfoService infoService;

    public List<Room> getRooms() {
        return rooms;
    }
    public Room findRoomById(long roomId){
        for (Room room : rooms) {
            if(room.getRoomId()==roomId){
                return room;
            }
        }
        return null;
    }
    public void saveRoom(Room room){
        try{
            rooms.replaceAll(room1 -> {
                if(room1.getRoomId()==room.getRoomId()){
                    return room;
                }
                return room1;
            });
        }catch (Exception e){

        }
    }
    public long addRoom(Room room) {
        Random random = new Random();
        room.setRoomId(random.nextLong());
        while(rooms.contains(room)){
            room.setRoomId(random.nextLong());
        }
        this.rooms.add(room);
        return room.getRoomId();
    }
    public boolean exist(Room room){
        return rooms.contains(room);
    }

}
