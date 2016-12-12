package com.yeadun.bigdata.platform.protocol.request;

import com.yeadun.bigdata.platform.protocol.MessageConstructor;
import com.yeadun.bigdata.platform.protocol.MessageProto;
import com.yeadun.bigdata.platform.protocol.ProtocolProto;

public class HDFSRequest extends MessageConstructor {
    public HDFSRequest(ProtocolProto.protocol protocol){
        super(protocol, MessageProto.MessageType.REQUEST);
    }

    protected void setInputData() {}

    protected void setOutputData() {}
}
