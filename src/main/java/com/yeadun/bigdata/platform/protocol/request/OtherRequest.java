package com.yeadun.bigdata.platform.protocol.request;


import com.yeadun.bigdata.platform.protocol.MessageConstructor;
import com.yeadun.bigdata.platform.protocol.MessageProto;
import com.yeadun.bigdata.platform.protocol.ProtocolProto;
import com.yeadun.bigdata.platform.util.LogUtil;

public class OtherRequest extends MessageConstructor {
    private LogUtil logger = new LogUtil(OtherRequest.class);
    public OtherRequest(ProtocolProto.protocol protocol){
        super(protocol, MessageProto.MessageType.REQUEST);
        setReqName("This is other type request.");
    }

    protected void setInputData() {}

    protected void setOutputData() {}

    public void setInput(String key, String val){
        setRequestPair(key, val, "Other in value.");
    }

}
