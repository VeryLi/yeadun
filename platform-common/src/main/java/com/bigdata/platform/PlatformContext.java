package com.bigdata.platform;

import com.bigdata.platform.exception.PlatformContextIsNotInitialization;
import com.bigdata.platform.protocol.ProtocolProto;
import com.bigdata.platform.util.LogUtil;

/**
 * The PlatformContext is very important class.
 * this class responsibility is :
 *  - Client:
 *    1. Create a Protocol which contain Request and Response, and going to put into Channel.
 *    2. Start Client send Protocol.
 *
 *  - Server:
 *    1. Start PlatformServer.
 * */
public abstract class PlatformContext {
    private PlatformConf conf;
    private LogUtil logger = new LogUtil(PlatformContext.class);
    private ProtocolProto.protocol protocol;
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
     * initialise PlatformContext.
     * creating a new protocol.
     * */
    private void init(){
        this.protocol = ProtocolProto.protocol.getDefaultInstance();
        this.isInit = true;
        this.logger.info("platform context has been initialized successfully.");
    }

    private void checkIsInit() throws PlatformContextIsNotInitialization {
        if(!isInit){
            logger.err("platform context is not initialization.");
            throw new PlatformContextIsNotInitialization("platform context is not initialization.");
        }
    }

    public abstract void send();

    public abstract void startServer();

    public PlatformConf getConf(){
        return this.conf;
    }

    public ProtocolProto.protocol getProtocol(){
        return this.protocol;
    }

    public void setProtocol(ProtocolProto.protocol protocol){
        this.protocol = protocol;
    }

}
