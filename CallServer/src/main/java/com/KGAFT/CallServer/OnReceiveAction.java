package com.KGAFT.CallServer;

import java.io.IOException;
import java.net.DatagramPacket;

public interface OnReceiveAction {
    void action(DatagramPacket packet, byte[] preparedData) throws IOException;
}
