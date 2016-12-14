package com.yeadun.bigdata.platform.server;

import com.yeadun.bigdata.platform.control.PlatformController;
import com.yeadun.bigdata.platform.protocol.ProtocolProto;
import com.yeadun.bigdata.platform.util.LogUtil;
import com.yeadun.bigdata.platform.util.ProtocolInfoUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * ServerEventHandler class responsibility is received Protocol from Client, and resolving ProtocolConstructor in the Protocol.
 * All kinds of Requests will be passed to corresponding RequestHandler to handle. Finally, put Result into Response.proto.proto,
 * and put Response.proto.proto into Protocol, sending this Protocol to Client.
 */
class ServerEventHandler extends ChannelInboundHandlerAdapter {
    private LogUtil logger = new LogUtil(ServerEventHandler.class);
    private ProtocolInfoUtil infoUtil;
    ServerEventHandler(){
        this.infoUtil = new ProtocolInfoUtil();
    }

    /**
     * receive Protocol contain ProtocolConstructor from Client, and handling.
     * @param ctx ChannelHandlerContext.
     * @param req Request Protocol from client.
     * */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object req){
        ProtocolProto.protocol protocol = (ProtocolProto.protocol) req;
        logger.info("server has received request from client, "
                + this.infoUtil.reqInfo(protocol));

        // pass protocol to PlatformController to handle.
        protocol = PlatformController.passReqToWorker(protocol);
        // put result into channel.
        ctx.writeAndFlush(protocol);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        logger.err(cause.toString());
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx){
        ctx.fireChannelReadComplete();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        ctx.fireChannelActive();
        logger.info("platform Server and Client to establish a connection. Now is waiting for client request.");

    }
}
