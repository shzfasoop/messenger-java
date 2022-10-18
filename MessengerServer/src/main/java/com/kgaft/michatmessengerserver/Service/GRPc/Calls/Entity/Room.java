package com.kgaft.michatmessengerserver.Service.GRPc.Calls.Entity;
import com.KGAFT.michatmessengerserverproto.CallServiceOuterClass;
import com.kgaft.michatmessengerserver.Database.Entity.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Room {
    private long roomId;
    private long creationDate;
    private long lastPacketDate;
    public List<User> allowedUsers = new ArrayList<>();

    private HashMap<User, CallServiceOuterClass.CallData> audioLastData = new HashMap<>();
    private HashMap<User, CallServiceOuterClass.VideoCallData> videoLastData = new HashMap<>();

    public Room(long roomId) {
        this.roomId = roomId;
    }

    public Room() {
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }


    public void addAllowedUsers(User user) {
        this.allowedUsers.add(user);
    }

    public List<User> getAllowedUsers() {
        return allowedUsers;
    }

    public void setAllowedUsers(List<User> allowedUsers) {
        this.allowedUsers = allowedUsers;
    }

    public HashMap<User, CallServiceOuterClass.CallData> getAudioLastData() {
        return audioLastData;
    }

    public void setAudioLastData(HashMap<User, CallServiceOuterClass.CallData> audioLastData) {
        this.audioLastData = audioLastData;
    }

    public HashMap<User, CallServiceOuterClass.VideoCallData> getVideoLastData() {
        return videoLastData;
    }

    public void setVideoLastData(HashMap<User, CallServiceOuterClass.VideoCallData> videoLastData) {
        this.videoLastData = videoLastData;
    }



    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public long getLastPacketDate() {
        return lastPacketDate;
    }

    public void setLastPacketDate(long lastPacketDate) {
        this.lastPacketDate = lastPacketDate;
    }
    @Override
    public boolean equals(Object obj) {
        return ((Room)obj).getRoomId()==this.roomId;
    }
}
