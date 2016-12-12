package com.yeadun.bigdata.platform.protocol;

import com.yeadun.bigdata.platform.util.LogUtil;

import java.util.ArrayList;
import java.util.UUID;

public abstract class MessageConstructor {
    private MessageProto.message message;
    private ArrayList<MessageProto.DataPair> msgBodys;
    private LogUtil logger = new LogUtil(MessageConstructor.class);

    protected abstract void setInputData();

    protected abstract void setOutputData();

    protected MessageConstructor(ProtocolProto.protocol protocol, MessageProto.MessageType type){
        this.message = protocol.getRequest();
        this.message.toBuilder().setId(UUID.randomUUID().toString());
        this.message.toBuilder().setType(type);
        this.msgBodys = this.message.getMessageBodyList();
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
        this.message.toBuilder().setName(name);
    }

}
