package com.yeadun.bigdata.platform.protocol;

/**
 * Created by chen on 16-12-6.
 */
public abstract class Message {
    // this message has been executed, or not .
    protected boolean finished;
    protected MessageType type;
    protected MessageType getType(){
        return this.type;
    }
    public abstract void write(Object msg);
    public abstract Object read();
}





