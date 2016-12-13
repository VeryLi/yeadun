package com.yeadun.bigdata.platform.client;

import com.yeadun.bigdata.platform.PlatformContext;
import com.yeadun.bigdata.platform.protocol.ProtocolProto;
import com.yeadun.bigdata.platform.util.LogUtil;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;


class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    private ProtocolProto.protocol protocol;
    private LogUtil logger = new LogUtil(ClientChannelInitializer.class);

    ClientChannelInitializer(ProtocolProto.protocol protocol){
        this.protocol = protocol;
    }

    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
        ch.pipeline().addLast(new ProtobufDecoder(ProtocolProto.protocol.getDefaultInstance()));
        ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
        ch.pipeline().addLast(new ProtobufEncoder());
        ch.pipeline().addLast(new ClientEventHandler(this.protocol));
        logger.info("Client Channel initialize finish.");
    }
}