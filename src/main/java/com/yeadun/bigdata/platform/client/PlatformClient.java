package com.yeadun.bigdata.platform.client;

import com.yeadun.bigdata.platform.util.LogUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Created by chen on 16-11-29.
 */
public class PlatformClient {
    private LogUtil logger = new LogUtil(PlatformClient.class);
    private int port;
    private String hostName = "localhost";

    public PlatformClient(int port){
        this.port = port;
    }
    public PlatformClient(String hostName,int port){
        this.port = port;
        this.hostName = hostName;
    }

    public void run(){
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap boot = new Bootstrap();
        boot.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ByteBuf delimiter = Unpooled.copiedBuffer("$".getBytes());
                        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(2048, delimiter));
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new EventClientHandler());
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 100)
                .option(ChannelOption.TCP_NODELAY, true);

        try {
            // Start the client.
            logger.info("client is connecting to " + this.hostName + ":" + this.port);
            ChannelFuture cf = boot.connect(this.hostName, this.port).sync();

            // Wait until the connection is closed.
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            this.logger.err(e.getMessage());
            e.printStackTrace();
        }finally {
            logger.info("shutting down group gracefully.");
            group.shutdownGracefully();
        }
    }
}
