package com.yeadun.bigdata.platform.server;

import com.yeadun.bigdata.platform.PlatformContext;
import com.yeadun.bigdata.platform.protocol.ProtocolProto;
import com.yeadun.bigdata.platform.util.LogUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class ServerEventHandler extends ChannelInboundHandlerAdapter {
    private LogUtil logger = new LogUtil(ServerEventHandler.class);
    private PlatformContext ctx;
    private String clientKey;
    public ServerEventHandler(PlatformContext ctx){
        this.ctx = ctx;
    }

    /**
     * receive request from Client, and execute.
     * */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object req){
        ProtocolProto.protocol.Builder reqBuild = ((ProtocolProto.protocol) req).toBuilder();
        logger.info("server receive request from client : name -> " + reqBuild.getName() + ", id -> " + reqBuild.getId());
        reqBuild.setName("this is test. <- I known");
        reqBuild.setId(111);
        logger.info("server has executed.");
        logger.info(reqBuild.build().getName() + "--->"+reqBuild.build().getId());
        ctx.writeAndFlush(reqBuild.build());
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
        logger.info("platform server socket is waiting for client request.");

    }
}
