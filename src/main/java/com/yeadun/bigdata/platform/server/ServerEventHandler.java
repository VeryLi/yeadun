package com.yeadun.bigdata.platform.server;

import com.yeadun.bigdata.platform.PlatformContext;
import com.yeadun.bigdata.platform.protocol.Protocol;
import com.yeadun.bigdata.platform.util.LogUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by chen on 16-12-5.
 */
public class ServerEventHandler extends ChannelInboundHandlerAdapter {
    private LogUtil logger = new LogUtil(ServerEventHandler.class);
    private PlatformContext ctx;
    public ServerEventHandler(PlatformContext ctx){
        this.ctx = ctx;
    }

    /**
     * receive request from Client, and execute.
     * */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object req){
        Protocol request = (Protocol) req;
        logger.info("Server receive request from Client , " + request.readMessage());
        Protocol response = execute(request);
        response.execute();
        logger.info("Server execute...");
        this.ctx.writeResponseProtocol(response);
        logger.info("Server response , " + this.ctx.getResponseProtocol().readMessage());
        ctx.writeAndFlush(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        logger.err(cause.getMessage());
        cause.printStackTrace();
        ctx.close();
    }

    private Protocol execute(Protocol request){
        String req = (String)request.readMessage();
        String resp = req + "[finished!]";
        request.writeMessage(resp);
        return request;
    }
}
