package com.yeadun.bigdata.platform.worker;

import com.yeadun.bigdata.platform.protocol.ProtocolProto;
import com.yeadun.bigdata.platform.protocol.constructor.OtherProtocolConstructor;

public class OtherWorker {

    private ProtocolProto.protocol protocol;

    public OtherWorker(ProtocolProto.protocol protocol){
        this.protocol = protocol;
    }

    public ProtocolProto.protocol execute(){
        String key = this.protocol.getRequest().getMessageBodyList().get(0).getKey();
        String value = this.protocol.getRequest().getMessageBodyList().get(0).getVal();

        String key1 = key + " +";
        String value1 = value + " +";

        OtherProtocolConstructor opc = new OtherProtocolConstructor(this.protocol);
        return opc.setResponseName("OtherResponse")
                .putOutputData(key1, value1)
                .flushProtocol()
                .getProtocol();
    }
}
