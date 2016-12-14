package com.yeadun.bigdata.platform.server;

import com.yeadun.bigdata.platform.PlatformConf;
import com.yeadun.bigdata.platform.PlatformContext;
import com.yeadun.bigdata.platform.util.LogUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by chen on 16-11-30.
 */
public class PlatformServer {

    private LogUtil logger = new LogUtil(PlatformServer.class);
    private PlatformConf conf = new PlatformConf();
    private String hostName = conf._server_host.getStrValue();
    private int port = conf._server_port.getIntValue();
    private int soBacklog = conf._so_backlog.getIntValue();
    private PlatformContext ctx;

    public PlatformServer(PlatformContext ctx){
        this.ctx = ctx;
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
            logger.info("PlatformServer is starting, and address => [" + this.hostName + "], port => [" + this.port + "].");
            ChannelFuture cf = srvBoot.bind(this.hostName, this.port).sync();
            logger.info("PlatformServer has been started successful.");

            // Wait until the server socket is closed.
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            this.logger.err(e.getMessage());
            e.printStackTrace();
        }finally {
            logger.info("PlatformServer is shutting down workGroup, bossGroup successful.");
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
