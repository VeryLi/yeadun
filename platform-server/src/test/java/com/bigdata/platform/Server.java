package com.bigdata.platform;

import com.bigdata.platform.util.LogUtil;

public class Server {
    private static LogUtil logger = new LogUtil(Server.class);

    public static void main(String[] args) throws Exception{
        logger.info("---- start server. ----");
        ServerPlatformContext server = new ServerPlatformContext();
        server.startServer();
    }

}
