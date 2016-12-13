package com.yeadun.bigdata.platform.control;

import com.yeadun.bigdata.platform.protocol.InvalidProtocolTypeException;
import com.yeadun.bigdata.platform.protocol.ProtocolProto;
import com.yeadun.bigdata.platform.util.LogUtil;

/**
 * PlatformController class is resolving ProtocolConstructor Message, according to it's type, distributing to
 * corresponding handler to handle.
 */
public class PlatformController {
    private ProtocolProto.protocol protocol;
    private ProtocolProto.ProtocolType type;
    private static LogUtil logger = new LogUtil(PlatformController.class);

    public static void passReqToWorker(ProtocolProto.protocol protocol){
        PlatformController controller = new PlatformController(protocol);
        try {
            controller.choseWork();
        } catch (InvalidProtocolTypeException e) {
            logger.err(e.getMessage());
            e.printStackTrace();
        }

    }

    private PlatformController(ProtocolProto.protocol protocol){
        this.protocol = protocol;
        this.type = this.protocol.getProtocolType();
    }

    private void choseWork() throws InvalidProtocolTypeException {
        switch (this.type.getNumber()){
            case 1 :
                hdfsWork();
            case 2 :
                hbaseWork();
            case 3 :
                otherWork();
            case 4 :
                sparkWork();
            case 5 :
                kafkaWork();
            case 6 :
                hiveWork();
            default:
                logger.err("This Type is not defined. ERROR [" + this.type + "]");
                throw new InvalidProtocolTypeException("This Type is not defined. ERROR [" + type + "]");
        }
    }

    private void hbaseWork(){}
    private void hdfsWork(){}
    private void hiveWork(){}
    private void kafkaWork(){}
    private void otherWork(){
    }
    private void sparkWork(){}
}
