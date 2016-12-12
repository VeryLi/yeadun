package com.yeadun.bigdata.platform.client;

import com.yeadun.bigdata.platform.PlatformContext;
import com.yeadun.bigdata.platform.protocol.MessageProto;
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
    private PlatformContext ctx;
    private ProtocolInfoUtil infoUtil ;
    ClientEventHandler(PlatformContext ctx){
        this.infoUtil = new ProtocolInfoUtil();
        this.ctx = ctx;
    }

    /**
     * send Protocol which contains ProtocolFactory to Server.
     * @param ctx ChannelHandler Context.
     * @exception Exception this exception is throw by checkRequestIsValid function.
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("client socket [Active], is built successful.");
        ProtocolProto.protocol req = this.ctx.getProtocol();
//        checkRequestIsValid(req);
        logger.info("Client send request to Server - " + infoUtil.protoInfo(req) + ", " + infoUtil.reqInfo(req) + ", " + req.getProtocolType());
        // send request protocol to server.
        ctx.writeAndFlush(req);
    }

    /**
     * Client receive Protocol which contains Response from Server, and handling.
     * @param ctx ChannelHandlerContext
     * @param resp Client receive response from Server.
     * */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object resp) throws Exception {
        logger.info("client receive server response.");
        ProtocolProto.protocol protocol = (ProtocolProto.protocol) resp;
        logger.info("client receive Protocol Info is " + infoUtil.protoInfo(protocol) +
                ", Response Info is " + infoUtil.respInfo(protocol));
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

    /**
     * check ProtocolFactory Message which in Protocol, is valid, or not.
     * @param protocol Protocol contains ProtocolFactory Message.
     * */
    private void checkRequestIsValid(ProtocolProto.protocol protocol) throws Exception {
        MessageProto.message request = protocol.getRequest();
        if(request.getFinished()){
            throw new Exception(infoUtil.protoInfo(protocol) + ", " + infoUtil.reqInfo(protocol) + " - " + request.getMessageBodyList().toString());
        }

        if(!request.hasType()){
            throw new Exception(infoUtil.protoInfo(protocol) + ", " + infoUtil.reqInfo(protocol) + " dose not have Message Type. ");
        }

        if(request.getType().getNumber() != 1){
            throw new Exception(infoUtil.protoInfo(protocol) + ", " + infoUtil.reqInfo(protocol) + " is not a request.");
        }

        if(request.getMessageBodyList() == null){
            logger.warn(infoUtil.protoInfo(protocol) + ", " + infoUtil.reqInfo(protocol) + " is null.");
        }
    }
}
