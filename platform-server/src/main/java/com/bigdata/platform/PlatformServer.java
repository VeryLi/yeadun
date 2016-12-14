package com.bigdata.platform;

import com.bigdata.platform.util.LogUtil;

/**
 * To start Platform Server.
 * */
public class PlatformServer {
    private static LogUtil logger = new LogUtil(PlatformServer.class);
    public static void main(String[] args){
        logger.info("******** Platform Server Start ********");
        ServerPlatformContext server = new ServerPlatformContext();
        server.startServer();
    }
}
