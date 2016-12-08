package com.yeadun.bigdata.platform.client;

import com.yeadun.bigdata.platform.PlatformContext;
import com.yeadun.bigdata.platform.protocol.Protocol;
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
    public void channelActive(ChannelHandlerContext ctx){
        logger.info("Client send request to Server , message type is " + this.ctx.getRequestProtocol().getType() + ".");
        // send request protocol to server.
        ctx.writeAndFlush(this.ctx.getRequestProtocol());
    }

    /**
     * Client receive response from Server, and execute.
     * */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object resp) throws Exception {
        Protocol response = (Protocol) resp;
        String context = (String) response.readMessage();
        logger.info("Client receive response from Server , message is " + context);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception{
        logger.info("Client is closing");
        ctx.channel().close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
