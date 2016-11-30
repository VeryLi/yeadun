package com.yeadun.bigdata.platform.client;

import com.yeadun.bigdata.platform.protocol.*;
/**
 * Created by chen on 16-11-29.
 */
public class PlatformClient {
    private int srvPort;
    private String srvAddress;

    public PlatformClient(String srvAddress, int srvPort){
        this.srvAddress = srvAddress;
        this.srvPort = srvPort;
    }

    public boolean init(){
        return true;
    }

    public void sendMsg(Protocol p, int timeout){}
}
