package com.yeadun.bigdata.platform.client;

import com.yeadun.bigdata.platform.PlatformContext;
import com.yeadun.bigdata.platform.protocol.KryoDecoder;
import com.yeadun.bigdata.platform.protocol.KryoEncoder;
import com.yeadun.bigdata.platform.util.LogUtil;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;


class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    private PlatformContext ctx;
    private LogUtil logger = new LogUtil(ClientChannelInitializer.class);

    ClientChannelInitializer(PlatformContext ctx){
        this.ctx = ctx;
    }

    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(2147483647, 0, 4, 0, 4));
        ch.pipeline().addLast(new KryoDecoder(this.ctx));
        ch.pipeline().addLast(new KryoEncoder(this.ctx));
        ch.pipeline().addLast(new ClientEventHandler(this.ctx));
        logger.info("Client Channel initialize finish.");
    }
}