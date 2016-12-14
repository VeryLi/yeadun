package com.yeadun.bigdata.platform.protocol;

import com.yeadun.bigdata.platform.PlatformContext;

import java.util.List;
import java.util.UUID;

public abstract class ProtocolConstructor {

    private ProtocolProto.protocol protocol;
    private PlatformContext ctx;
    private ProtocolProto.protocol.Builder builder;
    private RequestProto.request.Builder reqBuilder;
    private ResponseProto.response.Builder respBuilder;
    private List<DataPairProto.DataPair> reqMessage;
    private List<DataPairProto.DataPair> respMessage;

    /**
     * Client using this constructor.
     *
     * this construct function due to create ProtocolConstructor at Client. It will
     * create a new Protocol instance, and generic Protocol, Request, Response ID via UUID.
     * @param ctx Platform Context.
     * @param type Protocol Type. [e.g. HDFS, HBase, Other, Hive, Kafka, Hive, Spark.]
     * */
    public ProtocolConstructor(PlatformContext ctx, ProtocolProto.ProtocolType type){
        this.ctx = ctx;
        this.protocol = this.ctx.getProtocol();
        this.builder = this.protocol.toBuilder();
        this.reqBuilder = this.builder.getRequestBuilder();
        this.respBuilder = this.builder.getResponseBuilder();
        this.builder.setProtocolType(type);
        this.reqMessage = this.reqBuilder.getMessageBodyList();
        this.respMessage = this.reqBuilder.getMessageBodyList();
        genID();
    }

    /**
     * Server using this constructor.
     *
     * this construct function due to create ProtocolConstructor at Server.
     * It will use protocol which is passed from client, rather than creating a new
     * Protocol instance.
     * @param protocol the protocol from client.
     * */
    public ProtocolConstructor(ProtocolProto.protocol protocol){
        this.protocol = protocol;
        this.builder = this.protocol.toBuilder();
        this.reqBuilder = this.builder.getRequestBuilder();
        this.respBuilder = this.builder.getResponseBuilder();
        this.builder.setProtocolType(protocol.getProtocolType());
        this.reqMessage = this.reqBuilder.getMessageBodyList();
        this.respMessage = this.respBuilder.getMessageBodyList();
    }

    private void genID(){
        String id = UUID.randomUUID().toString();
        this.builder.setId(id);
        this.reqBuilder.setId(id);
        this.respBuilder.setId(id);
    }

    public void flush(){
        flushProtocol();
        this.ctx.setProtocol(this.protocol);
    }

    public ProtocolProto.protocol getProtocol(){
        return this.protocol;
    }

    public ProtocolConstructor flushProtocol(){
        this.builder.setRequest(this.reqBuilder.build());
        this.builder.setResponse(this.respBuilder.build());
        this.protocol = this.builder.build();
        return this;
    }

    protected ProtocolConstructor setProtocolName(String name){
        this.builder.setName(name);
        return this;
    }

    protected ProtocolConstructor setRequestName(String name){
        this.reqBuilder.setName(name);
        return this;
    }

    protected ProtocolConstructor setResponseName(String name){
        this.respBuilder.setName(name);
        return this;
    }

    private void putDataPair(DataPairProto.DataPair data
            , List<DataPairProto.DataPair> message){
        message.add(data);
    }

    private void putDataPair(String name, String key, String value, List<DataPairProto.DataPair> message){
        DataPairProto.DataPair data = DataPairProto.DataPair.newBuilder()
                .setName(name)
                .setKey(key)
                .setVal(value)
                .build();
        putDataPair(data, message);
    }

    protected ProtocolConstructor putRequestDataPair(DataPairProto.DataPair data){
        putDataPair(data, this.reqMessage);
        return this;
    }

    protected ProtocolConstructor putRequestDataPair(String name, String key, String value){
        putDataPair(name, key, value, this.reqMessage);
        return this;
    }

    protected ProtocolConstructor putResponseDataPair(DataPairProto.DataPair data){
        putDataPair(data, this.respMessage);
        return this;
    }

    protected ProtocolConstructor putResponseDataPair(String name, String key, String value){
        putDataPair(name, key, value, this.respMessage);
        return this;
    }
}
