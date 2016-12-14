package com.bigdata.platform.util;

import com.bigdata.platform.protocol.ProtocolProto;


public class ProtocolInfoUtil {

    public String reqInfo(ProtocolProto.protocol protocol){
        // get ProtocolConstructor info.
        String id = protocol.getRequest().getId();
        String name = protocol.getRequest().getName();
        String message = protocol.getRequest().toString();
        return "Request [id - " + id + " , name - " + name + " , message - " + message + "]";
    }

    public String respInfo(ProtocolProto.protocol protocol){
        // get Response.proto.proto info.
        String id = protocol.getResponse().getId();
        String name = protocol.getResponse().getName();
        String message = protocol.getResponse().toString();
        return "Response.proto.proto [id - " + id + " , name - " + name + " , type - " + message + "]";
    }

    public String protoInfo(ProtocolProto.protocol protocol){
        // get Protocol info.
        String id = protocol.getId();
        String name = protocol.getName();
        String type = protocol.getProtocolType().toString();
        return "Protocol [id - " + id + " , name - " + name + " , type - " + type + "]";
    }

    public String allInfo(ProtocolProto.protocol protocol){
        return protoInfo(protocol) + ", " + reqInfo(protocol) + ", " + respInfo(protocol);
    }
}
