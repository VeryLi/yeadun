package com.yeadun.bigdata.platform;

import com.yeadun.bigdata.platform.client.PlatformClient;
import com.yeadun.bigdata.platform.protocol.ProtocolProto;
import com.yeadun.bigdata.platform.server.PlatformServer;
import com.yeadun.bigdata.platform.util.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by chen on 16-12-8.
 */
public class PlatformContext {
    private PlatformConf conf;
    private LogUtil logger = new LogUtil(PlatformContext.class);
    private ProtocolProto.protocol.Builder protocolBuilder;
    private String ctxName;
    private HashMap<String, String> clientRecord = null;

    /**
     * PlatformContext constructor
     * */
    private PlatformContext (boolean onlyUseDefault) {
        this.conf = new PlatformConf(onlyUseDefault);
        init();
    }
    public PlatformContext () {
        this(true);
    }

    /**
     * initialise PlatformContext.
     * */
    private void init(){
        this.clientRecord = new HashMap<String, String>();
        this.protocolBuilder = ProtocolProto.protocol.newBuilder();
        this.logger.info("now platformContext is initializing.");
    }

    public void startClient(){
        PlatformClient pc = new PlatformClient(this);
        pc.start();
    }

    public void startServer(){
        PlatformServer ps = new PlatformServer(this);
        ps.start();
    }

    public void setCtxName(String name){
        this.ctxName = name;
    }

    public PlatformConf getConf(){
        return this.conf;
    }

    public ProtocolProto.protocol getProtocol(){
        return this.protocolBuilder.build();
    }

    public ProtocolProto.protocol.Builder getProtocolBuilder(){
        return this.protocolBuilder;
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
