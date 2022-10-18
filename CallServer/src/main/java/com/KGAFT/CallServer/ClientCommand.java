package com.KGAFT.CallServer;

public class ClientCommand {
    private byte literal;
    private OnReceiveAction action;

    public ClientCommand(byte literal, OnReceiveAction action) {
        this.literal = literal;
        this.action = action;
    }

    public ClientCommand() {
    }

    public OnReceiveAction getAction() {
        return action;
    }

    public void setAction(OnReceiveAction action) {
        this.action = action;
    }

    public void setLiteral(byte literal) {
        this.literal = literal;
    }

    public byte getLiteral() {
        return literal;
    }
}
