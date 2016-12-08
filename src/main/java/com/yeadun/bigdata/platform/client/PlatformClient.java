package com.yeadun.bigdata.platform.client;

import com.yeadun.bigdata.platform.PlatformContext;
import com.yeadun.bigdata.platform.protocol.MessageType;
import com.yeadun.bigdata.platform.protocol.Protocol;
import com.yeadun.bigdata.platform.PlatformConf;
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
        boot.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ClientChannelInitializer(this.ctx))
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeout)
                .option(ChannelOption.TCP_NODELAY, true);

        try {
            // Start the client.
            logger.info("client is connecting to " + this.hostName + ":" + this.port);
            ChannelFuture cf = boot.connect(this.hostName, this.port).sync();
            logger.info("client connect to server finish.");

            // Wait until the connection is closed.
            cf.channel().closeFuture();
        } catch (InterruptedException e) {
            this.logger.err(e.getMessage());
            e.printStackTrace();
        }finally {
            logger.info("shutting down group gracefully.");
            group.shutdownGracefully();
        }
    }
}
