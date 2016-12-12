package com.yeadun.bigdata.platform.util;

import com.yeadun.bigdata.platform.protocol.ProtocolProto;


public class ProtocolInfoUtil {

    public String reqInfo(ProtocolProto.protocol protocol){
        // get ProtocolFactory info.
        String reqId = protocol.getRequest().getId();
        String reqName = protocol.getRequest().getName();
        String reqType = protocol.getRequest().getType().toString();
        return "Request [id - " + reqId + " , name - " + reqName + " , type - " + reqType + "]";
    }

    public String respInfo(ProtocolProto.protocol protocol){
        // get Response info.
        String respId = protocol.getResponse().getId();
        String respName = protocol.getResponse().getName();
        String respType = protocol.getResponse().getType().toString();
        return "Response [id - " + respId + " , name - " + respName + " , type - " + respType + "]";
    }

    public String protoInfo(ProtocolProto.protocol protocol){
        // get Protocol info.
        String protoId = protocol.getId();
        String protoName = protocol.getName();
        String protoType = protocol.getProtocolType().toString();
        return "Protocol [id - " + protoId + " , name - " + protoName + " , type - " + protoType + "]";
    }

    public String allInfo(ProtocolProto.protocol protocol){
        return protoInfo(protocol) + ", " + reqInfo(protocol) + ", " + respInfo(protocol);
    }
}
