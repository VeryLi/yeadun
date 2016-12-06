package com.yeadun.bigdata.platform.protocol.messages;

/**
 * Created by chen on 16-12-6.
 */

import com.yeadun.bigdata.platform.protocol.Message;
import com.yeadun.bigdata.platform.protocol.MessageType;

/**
 * Other Message
 * */
public class OtherMessage extends Message{

    private String in;
    private String out;
    private MessageType type;
    public OtherMessage(){
        this.type = MessageType.OTHER;
    }
    public void write(Object msg) {
        this.in = (String)msg;
    }

    public Object read() {
        return this.out;
    }
}

