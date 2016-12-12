package com.yeadun.bigdata.platform.worker;

import com.yeadun.bigdata.platform.protocol.MessageProto;
import com.yeadun.bigdata.platform.protocol.ProtocolFactory;
import com.yeadun.bigdata.platform.protocol.ProtocolProto;
import com.yeadun.bigdata.platform.protocol.response.OtherResponse;
import com.yeadun.bigdata.platform.util.LogUtil;

public class OtherWorker {
    private ProtocolProto.protocol protocol;
    private MessageProto.message request;
    private LogUtil logger = new LogUtil(OtherWorker.class);

    public OtherWorker(ProtocolProto.protocol protocol){
        this.protocol = protocol;
        this.request = protocol.getRequest();
    }

    public void handle(){
        this.request.toBuilder().setFinished(true);
        String inputKey = request.getMessageBodyList().get(0).getKey();
        String inputVal = request.getMessageBodyList().get(0).getVal();
        logger.info("Other Client receive request key is " + inputKey + ", value is " + inputVal + ".");
        String outputKey = "out";
        String outputVal = inputVal + "[ weili.chen ]";
        OtherResponse resp = (OtherResponse) ProtocolFactory.createResponse(this.protocol);
        resp.setOutput(outputKey, outputVal);
    }
}
