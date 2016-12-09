package com.yeadun.bigdata.platform.client;

import com.yeadun.bigdata.platform.PlatformContext;
import com.yeadun.bigdata.platform.protocol.MessageProto;
import com.yeadun.bigdata.platform.protocol.ProtocolProto;
import com.yeadun.bigdata.platform.util.LogUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientEventHandler extends ChannelInboundHandlerAdapter {

    private LogUtil logger = new LogUtil(ClientEventHandler.class);
    private PlatformContext ctx;
    public ClientEventHandler(PlatformContext ctx){
        this.ctx = ctx;
    }

    /**
     * send request from Client to Server.
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("client socket is built successful.");
        ProtocolProto.protocol req = this.ctx.getProtocol();
        checkRequestIsValid(req);
        logger.info("Client send request to Server - [" + req.getId() + "] " + req.getName() + ", " + req.getProtocolType());
        // send request protocol to server.
        ctx.writeAndFlush(req);
    }

    /**
     * Client receive response from Server, and execute.
     * */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object resp) throws Exception {
        logger.info("reach client again...");
        ProtocolProto.protocol msg = (ProtocolProto.protocol) resp;
        logger.info("client receive response from server : name -> " + msg.getName()
                + ", id -> " + msg.getId() + ", type -> " + msg.getProtocolType());
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

    private void checkRequestIsValid(ProtocolProto.protocol protocol) throws Exception {
        MessageProto.message request = protocol.getRequest();
        if(request.getFinished()){
            throw new Exception("Request : [" + request.getId() +"]:" + request.getName() + ", " + request.getType()
                    + " has been executed. - " + request.getMessageBodyList().toString());
        }

        if(!request.hasType()){
            throw new Exception("Request : [" + request.getId() +"]:" + request.getName() + " dose not have Message Type. ");
        }

        if(request.getType().getNumber() != 1){
            throw new Exception("Request : [" + request.getId() +"]:" + request.getName() + " is not a request.");
        }

        if(request.getMessageBodyList() == null){
            logger.warn("Request : [" + request.getId() +"]:" + request.getName() + " is null.");
        }
    }
}
