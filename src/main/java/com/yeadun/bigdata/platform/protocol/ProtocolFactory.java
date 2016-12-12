package com.yeadun.bigdata.platform.protocol;


import com.yeadun.bigdata.platform.protocol.request.*;
import com.yeadun.bigdata.platform.protocol.response.*;
import com.yeadun.bigdata.platform.util.LogUtil;

public class ProtocolFactory {
    private ProtocolProto.ProtocolType type;
    private ProtocolProto.protocol protocol;
    private static LogUtil logger = new LogUtil(ProtocolFactory.class);
    private ProtocolFactory(ProtocolProto.protocol protocol, ProtocolProto.ProtocolType type){
        this.type = type;
        this.protocol = protocol;
    }

    public static MessageConstructor createRequest(ProtocolProto.protocol protocol, ProtocolProto.ProtocolType reqType){
        MessageConstructor result = null;
        try {
            result =  new ProtocolFactory(protocol, reqType).initRequest();
        } catch (InvalidProtocolTypeException e) {
            logger.err(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public static MessageConstructor createResponse(ProtocolProto.protocol protocol){
        MessageConstructor result = null;
        try {
            result =  new ProtocolFactory(protocol, protocol.getProtocolType()).initResponse();
        } catch (InvalidProtocolTypeException e) {
            logger.err(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    private MessageConstructor initRequest() throws InvalidProtocolTypeException {
        switch (this.type.getNumber()){
            case 1 :
                return new HDFSRequest(this.protocol);
            case 2 :
                return new HBaseRequest(this.protocol);
            case 3 :
                return new OtherRequest(this.protocol);
            case 4 :
                return new SparkRequest(this.protocol);
            case 5 :
                return new KafkaRequest(this.protocol);
            case 6 :
                return new HiveRequest(this.protocol);
            default:
                logger.err("This Type is not defined. ERROR [" + type + "]");
                throw new InvalidProtocolTypeException("This Type is not defined. ERROR [" + type + "]");
        }
    }

    private MessageConstructor initResponse() throws InvalidProtocolTypeException{
        switch (this.type.getNumber()){
            case 1 :
                return new HDFSResponse(this.protocol);
            case 2 :
                return new HBaseResponse(this.protocol);
            case 3 :
                return new OtherResponse(this.protocol);
            case 4 :
                return new SparkResponse(this.protocol);
            case 5 :
                return new KafkaResponse(this.protocol);
            case 6 :
                return new HiveResponse(this.protocol);
            default:
                logger.err("This Type is not defined. ERROR [" + type + "]");
                throw new InvalidProtocolTypeException("This Type is not defined. ERROR [" + type + "]");
        }
    }
}
