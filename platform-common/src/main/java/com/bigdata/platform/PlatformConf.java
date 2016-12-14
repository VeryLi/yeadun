package com.bigdata.platform;

import com.bigdata.platform.util.LogUtil;
import com.bigdata.platform.util.PropUtil;

import java.io.IOException;

public class PlatformConf {

    private LogUtil logger = new LogUtil(PlatformConf.class);
    private PropUtil propUtil;

    // platform config
    public PlatformDefaultConf _server_host    = PlatformDefaultConf.SERVER_HOST;
    public PlatformDefaultConf _server_port    = PlatformDefaultConf.SERVER_PORT;
    public PlatformDefaultConf _client_connect_timeout = PlatformDefaultConf.CLIENT_CONNECT_TIMEOUT;
    public PlatformDefaultConf _so_backlog     = PlatformDefaultConf.SO_BACKLOG;

    PlatformConf(boolean usePlatformConf){
        boolean usePlatformConf1 = true;
        try {
            String confPath = System.getProperty("platform.conf.dir");
            confPath = confPath + "/platform.conf";
            usePlatformConf1 = usePlatformConf;
            this.propUtil = new PropUtil(confPath);
        } catch (IOException e) {
            this.logger.err(e.getMessage());
            e.printStackTrace();
        }
        if (usePlatformConf1){
            updateAllProps();
        }
    }
    public PlatformConf(){
        new PlatformConf(true);
    }

    private PlatformConf updateFromConfigure(PlatformDefaultConf prop){
        String key = prop.name().replace("_", ".").toLowerCase();
        String value = this.propUtil.getPropWithKey(key);
        if(value != null){
            prop.setValue(value);
        }
        this.logger.debug(prop.name() + " => " + prop.getValue());
        return this;
    }

    private PlatformConf updateAllProps(){
        PlatformDefaultConf[] ps = PlatformDefaultConf.values();
        for (PlatformDefaultConf p : ps){
            updateFromConfigure(p);
        }
        return this;
    }
}
