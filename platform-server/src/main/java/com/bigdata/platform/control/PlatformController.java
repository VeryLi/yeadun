package com.bigdata.platform.control;

import com.bigdata.platform.exception.InvalidProtocolTypeException;
import com.bigdata.platform.util.LogUtil;
import com.bigdata.platform.worker.OtherWorker;
import com.bigdata.platform.protocol.ProtocolProto;

/**
 * PlatformController class is resolving ProtocolConstructor Message, according to it's type, distributing to
 * corresponding handler to handle.
 */
public class PlatformController {
    private ProtocolProto.protocol protocol;
    private ProtocolProto.ProtocolType type;
    private static LogUtil logger = new LogUtil(PlatformController.class);

    /**
     * resolve protocol type, according to ProtocolType, pass protocol to Worker.
     * */
    public static ProtocolProto.protocol passReqToWorker(ProtocolProto.protocol protocol){
        try {
            PlatformController controller = new PlatformController(protocol);
            return controller.choseWork();
        } catch (InvalidProtocolTypeException e) {
            logger.err(e.getMessage());
            e.printStackTrace();
        }
        return protocol;
    }

    private PlatformController(ProtocolProto.protocol protocol){
        this.protocol = protocol;
        this.type = this.protocol.getProtocolType();
    }

    private ProtocolProto.protocol choseWork() throws InvalidProtocolTypeException {
        switch (this.type.getNumber()){
            case 1 :
                return hdfsWork();
            case 2 :
                return hbaseWork();
            case 3 :
                return otherWork();
            case 4 :
                return sparkWork();
            case 5 :
                return kafkaWork();
            case 6 :
                return hiveWork();
            default:
                logger.err("This Type is not defined. ERROR [" + this.type + "]");
                throw new InvalidProtocolTypeException("This Type is not defined. ERROR [" + type + "]");
        }
    }

    private ProtocolProto.protocol hbaseWork(){
        return null;
    }

    private ProtocolProto.protocol hdfsWork(){
        return null;
    }

    private ProtocolProto.protocol hiveWork(){
        return null;
    }

    private ProtocolProto.protocol kafkaWork(){
        return null;
    }

    private ProtocolProto.protocol otherWork(){
        return new OtherWorker(this.protocol)
                .resolveReqProtocol()
                .execute()
                .returnProtocol();
    }

    private ProtocolProto.protocol sparkWork(){
        return null;
    }
}
