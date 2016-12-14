package com.yeadun.bigdata.platform.client;

import com.yeadun.bigdata.platform.PlatformContext;
import com.yeadun.bigdata.platform.control.PlatformController;
import com.yeadun.bigdata.platform.protocol.ProtocolProto;
import com.yeadun.bigdata.platform.util.LogUtil;
import com.yeadun.bigdata.platform.util.ProtocolInfoUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * ClientEventHandler major responsibility is two points.
 * 1. when Client has connected to Server, sending request to Server.
 * 2. when Client has received response from Server, passing response to user.
 * */
class ClientEventHandler extends ChannelInboundHandlerAdapter {

    private LogUtil logger = new LogUtil(ClientEventHandler.class);
    private ProtocolProto.protocol protocol;
    private ProtocolInfoUtil infoUtil ;
    private PlatformContext ctx;

    ClientEventHandler(PlatformContext ctx){
        this.infoUtil = new ProtocolInfoUtil();
        this.ctx = ctx;
        this.protocol = this.ctx.getProtocol();
    }

    /**
     * send Request Protocol which contains ProtocolConstructor to Server.
     * @param ctx ChannelHandler Context.
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("server and client connection established.");
        logger.info("client send Request Protocol to Server, " + infoUtil.protoInfo(this.protocol)
                + ", " + infoUtil.reqInfo(this.protocol) + ", "
                + this.protocol.getProtocolType());
        // put Protocol which in PlatformContext into channel.
        ctx.writeAndFlush(this.protocol);
    }

    /**
     * Client receive Protocol which contains Response.proto.proto from Server, and handling.
     * @param ctx ChannelHandlerContext
     * @param resp Client receive Response Protocol from Server.
     * */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object resp) throws Exception {
        logger.info("client receive server response.");
        ProtocolProto.protocol protocol = (ProtocolProto.protocol) resp;
        // Write Response Protocol into PlatformContext.
        this.ctx.setProtocol(protocol);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception{
        logger.info("Client is closing.");
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.err(cause.getMessage());
        cause.printStackTrace();
        ctx.close();
    }
}
