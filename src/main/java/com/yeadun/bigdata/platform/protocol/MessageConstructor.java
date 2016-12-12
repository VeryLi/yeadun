package com.yeadun.bigdata.platform.protocol;

import com.yeadun.bigdata.platform.util.LogUtil;

import java.util.ArrayList;
import java.util.UUID;

public abstract class MessageConstructor {
    private MessageProto.message message;
    private ArrayList<MessageProto.DataPair> msgBodys;
    private ProtocolProto.protocol protocol;
    private LogUtil logger = new LogUtil(MessageConstructor.class);

    protected abstract void setInputData();

    protected abstract void setOutputData();

    protected MessageConstructor(ProtocolProto.protocol protocol, MessageProto.MessageType type){
        this.protocol = protocol;
        this.message = protocol.getRequest();
        this.message = this.message.toBuilder().setId(UUID.randomUUID().toString()).build();
        this.message = this.message.toBuilder().setType(type).build();
        this.msgBodys = this.message.getMessageBodyList();

    }

    protected void flush(){
        ProtocolProto.protocol.Builder builder = this.protocol.toBuilder();
        this.protocol = builder.setRequest(this.message).build();
    }

    private void putDataPairIntoList(MessageProto.DataPair data){
        this.msgBodys.add(data);
    }

    protected void setRequestPair(String key, String val, String name){
        MessageProto.DataPair.Builder dataPair = MessageProto.DataPair.newBuilder();
        dataPair.setKey(key);
        dataPair.setName(name);
        dataPair.setVal(val);
        putDataPairIntoList(dataPair.build());
    }

    protected void setReqName(String name){
        this.message = this.message.toBuilder().setName(name).build();
    }
}
