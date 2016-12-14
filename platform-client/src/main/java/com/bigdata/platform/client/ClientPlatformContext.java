package com.bigdata.platform.client;


import com.bigdata.platform.PlatformContext;

public class ClientPlatformContext extends PlatformContext {
    @Override
    public void send(){
        PlatformClient pc = new PlatformClient();
        pc.start(this);
    }

    @Override
    public void startServer() {
        // Do not anything.
    }
}
