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
    private boolean usePlatformConf = true;

    // platform config
    public PlatformDefaultProps _server_host       = PlatformDefaultProps.SERVER_HOST;
    public PlatformDefaultProps _data_delimiter    = PlatformDefaultProps.DATA_DELIMITER;
    public PlatformDefaultProps    _server_port    = PlatformDefaultProps.SERVER_PORT;
    public PlatformDefaultProps    _buffer_size    = PlatformDefaultProps.BUFFER_SIZE;

    public PlatformConf(boolean usePlatformConf){
        try {
            String confPath = "conf/platform.conf";
            this.usePlatformConf = usePlatformConf;
            this.propUtil = new PropUtil(confPath);
        } catch (IOException e) {
            this.logger.err(e.getMessage());
            e.printStackTrace();
        }
        if (this.usePlatformConf){
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
