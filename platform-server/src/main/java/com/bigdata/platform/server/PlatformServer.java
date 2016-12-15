package com.bigdata.platform.server;

import com.bigdata.platform.PlatformConf;
import com.bigdata.platform.PlatformContext;
import com.bigdata.platform.util.LogUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class PlatformServer {

    private LogUtil logger = new LogUtil(PlatformServer.class);
    private String hostName;
    private int port;
    private int soBacklog;

    public PlatformServer(PlatformContext ctx){
        PlatformConf conf = ctx.getConf();
        this.hostName = conf._server_host.getStrValue();
        this.port = conf._server_port.getIntValue();
        this.soBacklog = conf._so_backlog.getIntValue();
    }
    public void start(){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap srvBoot = new ServerBootstrap();
        srvBoot.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ServerChannelInitializer())
                .option(ChannelOption.SO_BACKLOG, soBacklog)
                .option(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true);

        try {
            // Bind and start to accept incoming connections.
            logger.info("PlatformServerMain is starting. Address is " + this.hostName + ", port is " + this.port + ".");
            ChannelFuture cf = srvBoot.bind(this.hostName, this.port).sync();
            logger.info("PlatformServerMain has been started successfully.");

            // Wait until the server socket is closed.
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            this.logger.err(e.getMessage());
            e.printStackTrace();
        }finally {
            logger.info("PlatformServerMain is shutting down workGroup, bossGroup successful.");
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
