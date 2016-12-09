package com.yeadun.bigdata.platform.server;

import com.yeadun.bigdata.platform.PlatformContext;
import com.yeadun.bigdata.platform.protocol.ProtocolProto;
import com.yeadun.bigdata.platform.util.LogUtil;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    private PlatformContext ctx;
    private LogUtil logger = new LogUtil(ServerChannelInitializer.class);
    ServerChannelInitializer(PlatformContext ctx){
        this.ctx = ctx;
    }
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
        ch.pipeline().addLast(new ProtobufDecoder(ProtocolProto.protocol.getDefaultInstance()));
        ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
        ch.pipeline().addLast(new ProtobufEncoder());
        ch.pipeline().addLast(new ServerEventHandler(this.ctx));
        logger.info("Server Channel initialize finished.");
    }
}
