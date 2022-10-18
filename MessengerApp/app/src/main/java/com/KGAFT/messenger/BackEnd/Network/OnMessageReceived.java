package com.KGAFT.messenger.BackEnd.Network;

import com.KGAFT.messenger.BackEnd.Entities.Message;

public interface OnMessageReceived {

    void messageReceived(Message message);
}
