package com.yeadun.bigdata.platform.client;

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
    ClientEventHandler(ProtocolProto.protocol protocol){
        this.infoUtil = new ProtocolInfoUtil();
        this.protocol = protocol;
    }

    /**
     * send Protocol which contains ProtocolConstructor to Server.
     * @param ctx ChannelHandler Context.
     * @exception Exception this exception is throw by checkRequestIsValid function.
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("client socket [Active], is built successful.");
//        checkRequestIsValid(req);
        logger.info("Client send request to Server - " + infoUtil.protoInfo(this.protocol)
                + ", " + infoUtil.reqInfo(this.protocol) + ", "
                + this.protocol.getProtocolType());
        // send request protocol to server.
        ctx.writeAndFlush(this.protocol);
    }

    /**
     * Client receive Protocol which contains Response.proto.proto from Server, and handling.
     * @param ctx ChannelHandlerContext
     * @param resp Client receive response from Server.
     * */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object resp) throws Exception {
        logger.info("client receive server response.");
        ProtocolProto.protocol protocol = (ProtocolProto.protocol) resp;
        logger.info("client receive Protocol Info is " + infoUtil.protoInfo(protocol) +
                ", Response.proto.proto Info is " + infoUtil.respInfo(protocol));
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
