package com.yeadun.bigdata.platform;

import com.yeadun.bigdata.platform.client.PlatformClient;
import com.yeadun.bigdata.platform.control.PlatformController;
import com.yeadun.bigdata.platform.protocol.ProtocolProto;
import com.yeadun.bigdata.platform.server.PlatformServer;
import com.yeadun.bigdata.platform.util.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PlatformContext {
    private PlatformConf conf;
    private LogUtil logger = new LogUtil(PlatformContext.class);
    private ProtocolProto.protocol protocol;
    private HashMap<String, String> clientRecord = null;
    private boolean isInit = false;

    /**
     * PlatformContext constructor
     * @param onlyUseDefault whether using default configuration.
     * */
    private PlatformContext (boolean onlyUseDefault) {
        this.conf = new PlatformConf(onlyUseDefault);
        init();
    }
    public PlatformContext () {
        this(true);
    }

    /**
     * initialise PlatformContext. creating a new protocol.
     * */
    private void init(){
        this.clientRecord = new HashMap<String, String>();
        this.protocol = ProtocolProto.protocol.getDefaultInstance();
        this.logger.info("now platformContext is initialized");
        this.isInit = true;
    }

    private void checkIsInit() throws PlatformConextIsNotInitialization{
        if(!isInit){
            logger.err("Platform is not initialization.");
            throw new PlatformConextIsNotInitialization("Platform is not initialization.");
        }
    }

    public void send(){
        try {
            checkIsInit();
        } catch (PlatformConextIsNotInitialization platformConextIsNotInitialization) {
            logger.err(platformConextIsNotInitialization.getMessage());
            platformConextIsNotInitialization.printStackTrace();
        }
        PlatformClient pc = new PlatformClient();
        pc.start(this.protocol);
    }

    public void startServer(){
        try {
            checkIsInit();
        } catch (PlatformConextIsNotInitialization platformConextIsNotInitialization) {
            logger.err(platformConextIsNotInitialization.getMessage());
            platformConextIsNotInitialization.printStackTrace();
        }
        PlatformServer ps = new PlatformServer(this);
        ps.start();
    }

    public PlatformConf getConf(){
        return this.conf;
    }

    public ProtocolProto.protocol getProtocol(){
        return this.protocol;
    }

    public void setProtocol(ProtocolProto.protocol protocol){
        this.protocol = protocol;
    }

    public ProtocolProto.protocol.Builder getProtocolBuilder(){
        return this.protocol.toBuilder();
    }

    public void writeInfoIntoCtx(String clientId, String clientInfo){
        this.clientRecord.put(clientId, clientInfo);
        logger.info("client - " + clientId + ", " + clientInfo + ", has connected platform Server.");
    }

    public void removeInfoIntoCtx(String clientId){
        this.clientRecord.remove(clientId);
        logger.info("client - " + clientId + ", " + "has disconnected.");
    }
}
