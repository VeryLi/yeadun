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
    private PlatformConf conf = new PlatformConf();
    private String delimiter = conf._data_delimiter.getStrValue();
    private String hostName = conf._server_host.getStrValue();
    private int port = conf._server_port.getIntValue();
    private int bufferSize = conf._buffer_size.getIntValue();
    private int soBacklog = conf._so_backlog.getIntValue();

    public void run(){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap srvBoot = new ServerBootstrap();
        srvBoot.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ByteBuf delimiterByte = Unpooled.copiedBuffer(delimiter.getBytes());
                        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(bufferSize, delimiterByte));
//                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new EventServerHandler());
                    }
                })
                .option(ChannelOption.SO_BACKLOG, soBacklog)
                .option(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true);

        try {
            // Bind and start to accept incoming connections.
            logger.info("platform is binding on " + this.hostName + ", port is " + this.port);
            ChannelFuture cf = srvBoot.bind(this.hostName, this.port).sync();
            logger.info("platform server start finish.");

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
