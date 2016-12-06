package com.yeadun.bigdata.platform.server;

import com.yeadun.bigdata.platform.util.LogUtil;
import com.yeadun.bigdata.platform.util.PropUtil;

import java.io.IOException;

/**
 * Created by chen on 16-12-6.
 */
public class PlatformConf {

    private LogUtil logger = new LogUtil(PlatformConf.class);
    private PropUtil propUtil;

    // platform config
    public PlatformDefaultProps _server_host    = PlatformDefaultProps.SERVER_HOST;
    public PlatformDefaultProps _data_delimiter = PlatformDefaultProps.DATA_DELIMITER;
    public PlatformDefaultProps _server_port    = PlatformDefaultProps.SERVER_PORT;
    public PlatformDefaultProps _buffer_size    = PlatformDefaultProps.BUFFER_SIZE;
    public PlatformDefaultProps _client_connect_timeout = PlatformDefaultProps.CLIENT_CONNECT_TIMEOUT;
    public PlatformDefaultProps _so_backlog     = PlatformDefaultProps.SO_BACKLOG;
    public PlatformDefaultProps _ser_buffer_size = PlatformDefaultProps.SER_BUFFER_SIZE;
    public PlatformDefaultProps _deser_buffer_size = PlatformDefaultProps.DESER_BUFFER_SIZE;

    public PlatformConf(boolean usePlatformConf){
        boolean usePlatformConf1 = true;
        try {
            String confPath = "conf/platform.conf";
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

    private PlatformConf updateFromConfigure(PlatformDefaultProps prop){
        String key = prop.name().replace("_", ".").toLowerCase();
        String value = this.propUtil.getPropWithKey(key);
        if(value != null){
            prop.setValue(value);
        }
        this.logger.debug(prop.name() + " => " + prop.getValue());
        return this;
    }

    private PlatformConf updateAllProps(){
        PlatformDefaultProps[] ps = PlatformDefaultProps.values();
        for (PlatformDefaultProps p : ps){
            updateFromConfigure(p);
        }
        return this;
    }
}
