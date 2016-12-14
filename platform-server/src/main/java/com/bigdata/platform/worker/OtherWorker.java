package com.bigdata.platform.worker;

import com.bigdata.platform.protocol.ProtocolProto;
import com.bigdata.platform.protocol.constructor.OtherProtocolConstructor;

public class OtherWorker implements Worker {

    private ProtocolProto.protocol protocol;
    private String key;
    private String value;

    public OtherWorker(ProtocolProto.protocol protocol){
        this.protocol = protocol;
    }

    public OtherWorker resolveReqProtocol() {
        this.key = this.protocol.getRequest().getMessageBodyList().get(0).getKey();
        this.value = this.protocol.getRequest().getMessageBodyList().get(0).getVal();
        return this;
    }

    public OtherWorker execute(){
        this.key = key + "(Down)";
        this.value = value + "(Down)";
        return this;
    }

    public ProtocolProto.protocol returnProtocol() {
        OtherProtocolConstructor opc = new OtherProtocolConstructor(this.protocol);
        return opc.setResponseName("OtherResponse")
                .putOutputData(key, value)
                .flushProtocol()
                .getProtocol();
    }
}
