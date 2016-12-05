package com.yeadun.bigdata.platform.server;

import com.yeadun.bigdata.platform.util.LogUtil;
import com.yeadun.bigdata.platform.util.PropUtil;

import java.io.IOException;

/**
 * Created by chen on 16-12-5.
 */
public class PlatformConf {
    
    private LogUtil logger = new LogUtil(PlatformConf.class);
    private PropUtil propUtil;
    private String srvHost = "localhost";
    private int srvPort = 9999;

    public PlatformConf(){
        try {
            this.propUtil = new PropUtil();
        } catch (IOException e) {
            logger.err(e.getMessage());
            e.printStackTrace();
        }
    }

    public void init(){

    }
    
}
