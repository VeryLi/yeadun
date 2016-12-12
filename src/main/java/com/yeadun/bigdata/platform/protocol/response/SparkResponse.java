package com.yeadun.bigdata.platform.protocol.response;

import com.yeadun.bigdata.platform.protocol.MessageConstructor;
import com.yeadun.bigdata.platform.protocol.MessageProto;
import com.yeadun.bigdata.platform.protocol.ProtocolProto;

public class SparkResponse extends MessageConstructor {
    public SparkResponse(ProtocolProto.protocol protocol){
        super(protocol, MessageProto.MessageType.RESPONSE);

    }
    protected void setInputData() {}

    protected void setOutputData() {}
}
