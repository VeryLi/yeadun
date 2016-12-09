package com.yeadun.bigdata.platform.client;

import com.yeadun.bigdata.platform.PlatformContext;
import com.yeadun.bigdata.platform.PlatformConf;
import com.yeadun.bigdata.platform.util.LogUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;


public class PlatformClient {
    private LogUtil logger = new LogUtil(PlatformClient.class);
    private PlatformConf conf = new PlatformConf();
    private int port = conf._server_port.getIntValue();
    private String hostName = conf._server_host.getStrValue();
    private int timeout = conf._client_connect_timeout.getIntValue();
    private PlatformContext ctx;

    public PlatformClient (PlatformContext ctx) {
        this.ctx = ctx;
    }

    public void start(){
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap boot = new Bootstrap();
        try {
            boot.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChannelInitializer(this.ctx))
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeout)
                    .option(ChannelOption.TCP_NODELAY, true);
        }catch(Exception e){
            this.logger.err(e.getMessage());
            e.printStackTrace();
        }
        try {
            // Start the client.
            logger.info("client is connecting to " + this.hostName + ":" + this.port + ".");
            ChannelFuture cf = boot.connect(this.hostName, this.port).sync();
            logger.info("client has been connected to server successful.");

            // Wait until the connection is closed.
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            this.logger.err(e.getMessage());
            e.printStackTrace();
        }finally {
            logger.info("client is shutting down group successful.");
            group.shutdownGracefully();
        }
    }
}
