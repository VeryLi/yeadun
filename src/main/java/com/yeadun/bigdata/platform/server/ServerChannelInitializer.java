package com.yeadun.bigdata.platform.server;

import com.yeadun.bigdata.platform.PlatformContext;
import com.yeadun.bigdata.platform.protocol.KryoDecoder;
import com.yeadun.bigdata.platform.protocol.KryoEncoder;
import com.yeadun.bigdata.platform.util.LogUtil;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    private PlatformContext ctx;
    private LogUtil logger = new LogUtil(ServerChannelInitializer.class);
    ServerChannelInitializer(PlatformContext ctx){
        this.ctx = ctx;
    }
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(2147483647, 0, 4, 0, 4));
        ch.pipeline().addLast(new KryoDecoder(this.ctx));
        ch.pipeline().addLast(new KryoEncoder(this.ctx));
        ch.pipeline().addLast(new ServerEventHandler(this.ctx));
        logger.info("Server Channel initialize finished.");
    }
}
