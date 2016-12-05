package com.yeadun.bigdata.platform.client;

import com.yeadun.bigdata.platform.util.LogUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by chen on 16-12-5.
 */
public class EventClientHandler extends ChannelInboundHandlerAdapter {

    private LogUtil logger = new LogUtil(EventClientHandler.class);
    @Override
    public void channelActive(ChannelHandlerContext ctx){
        String body = "this is test.$";
        logger.info("client : " + body + " [1. client send request]");
        ctx.writeAndFlush(Unpooled.copiedBuffer(body.getBytes()));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        logger.info("client : " + body + "[4. client recv result.]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception{
        logger.info("client : [5. read complete]");
        ctx.channel().close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
