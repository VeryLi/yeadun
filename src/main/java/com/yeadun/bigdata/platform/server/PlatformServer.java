package com.yeadun.bigdata.platform.server;

import com.yeadun.bigdata.platform.util.LogUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Created by chen on 16-11-30.
 */
public class PlatformServer implements Runnable {

    private LogUtil logger = new LogUtil(PlatformServer.class);
    private String DELIMITER = PlatformDefaultProps.DATA_DELIMITER.getStrValue();
    private String hostName = PlatformDefaultProps.SERVER_HOST.getStrValue();
    private int port;


    public PlatformServer(int port){
        this.port = port;
    }
    public PlatformServer(int port, String hostName){
        this.port = port;
        this.hostName = hostName;
    }

    public void run(){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap srvBoot = new ServerBootstrap();
        srvBoot.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ByteBuf delimiter = Unpooled.copiedBuffer(DELIMITER.getBytes());
                        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(2048, delimiter));
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new EventServerHandler());
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 100)
                .option(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true);

        try {
            // Bind and start to accept incoming connections.
            logger.info("platform is binding on " + this.hostName + ", port is " + this.port);
            ChannelFuture cf = srvBoot.bind(this.hostName, this.port).sync();

            // Wait until the server socket is closed.
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            this.logger.err(e.getMessage());
            e.printStackTrace();
        }finally {
            logger.info("shutting down workGroup, bossGroup gracefully.");
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
