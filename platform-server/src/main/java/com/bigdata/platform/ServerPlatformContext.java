package com.bigdata.platform;


import com.bigdata.platform.server.PlatformServer;

public class ServerPlatformContext extends PlatformContext {
    @Override
    public void send() {
        // do not anything.
    }

    @Override
    public void startServer() {
        PlatformServer ps = new PlatformServer(this);
        ps.start();
    }
}
