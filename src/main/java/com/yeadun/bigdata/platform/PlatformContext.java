package com.yeadun.bigdata.platform;

import com.yeadun.bigdata.platform.client.PlatformClient;
import com.yeadun.bigdata.platform.protocol.KryoPoolFactory;
import com.yeadun.bigdata.platform.protocol.Protocol;
import com.yeadun.bigdata.platform.server.PlatformServer;
import com.yeadun.bigdata.platform.util.LogUtil;

/**
 * Created by chen on 16-12-8.
 */
public class PlatformContext {
    private PlatformConf conf;
    private LogUtil logger = new LogUtil(PlatformContext.class);
    private Protocol requestProtocol = null;
    private Protocol responseProtocol = null;
    private KryoPoolFactory kryoPool;

    /**
     * PlatformContext constructor
     * */
    public PlatformContext (boolean onlyUseDefault) {
        this.conf = new PlatformConf(onlyUseDefault);
        init();
    }
    public PlatformContext () {
        new PlatformContext(true);
    }

    /**
     * initialise PlatformContext.
     * */
    private void init(){
        this.logger.info("now platformContext is initializing.");
        this.kryoPool = new KryoPoolFactory(this);
    }

    public void startClient(){
        PlatformClient pc = new PlatformClient(this);
        pc.start();
    }

    public void startServer(){
        PlatformServer ps = new PlatformServer(this);
        ps.start();
    }

    public PlatformConf getConf(){
        return this.conf;
    }

    public Protocol getRequestProtocol(){
        return this.requestProtocol;
    }

    public Protocol getResponseProtocol(){
        return this.responseProtocol;
    }

    public void writeRequestProtocol(Protocol protocol){
        this.requestProtocol = protocol;
    }

    public void writeResponseProtocol(Protocol protocol){
        this.responseProtocol = protocol;

    }
}
