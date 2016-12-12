package com.yeadun.bigdata.platform.protocol.response;


import com.yeadun.bigdata.platform.protocol.MessageConstructor;
import com.yeadun.bigdata.platform.protocol.MessageProto;
import com.yeadun.bigdata.platform.protocol.ProtocolProto;

public class OtherResponse extends MessageConstructor {
    public OtherResponse(ProtocolProto.protocol protocol){
        super(protocol, MessageProto.MessageType.RESPONSE);

    }
    protected void setInputData() {}

    protected void setOutputData() {}

    public void setOutput(String key, String val){
        setRequestPair(key, val, "Other out value.");
        flush();
    }
}
