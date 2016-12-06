package com.yeadun.bigdata.platform.client;

import com.yeadun.bigdata.platform.server.PlatformConf;
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
    private PlatformConf conf = new PlatformConf();
    private int port = conf._server_port.getIntValue();
    private String hostName = conf._server_host.getStrValue();
    private String delimiter = conf._data_delimiter.getStrValue();
    private int bufferSize = conf._buffer_size.getIntValue();
    private int soBacklog = conf._so_backlog.getIntValue();
    private int timeout = conf._client_connect_timeout.getIntValue();

    public void run(){
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap boot = new Bootstrap();
        boot.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ByteBuf delimiterByte = Unpooled.copiedBuffer(delimiter.getBytes());
                        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(bufferSize, delimiterByte));
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new EventClientHandler());
                    }
                })
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeout)
                .option(ChannelOption.TCP_NODELAY, true);

        try {
            // Start the client.
            logger.info("client is connecting to " + this.hostName + ":" + this.port);
            ChannelFuture cf = boot.connect(this.hostName, this.port).sync();
            logger.info("client connect to server finish.");

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
