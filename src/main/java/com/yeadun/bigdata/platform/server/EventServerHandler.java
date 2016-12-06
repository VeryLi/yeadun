package com.yeadun.bigdata.platform.server;

import com.yeadun.bigdata.platform.util.LogUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by chen on 16-12-5.
 */
public class EventServerHandler extends ChannelInboundHandlerAdapter {
    private LogUtil logger = new LogUtil(EventServerHandler.class);
    private PlatformConf conf = new PlatformConf();
    private String delimiter = conf._data_delimiter.getStrValue();
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        String body = (String) msg;
        logger.info("server : " + body + "[2. server recv request]");
        logger.info("server : " + body + "[3. server execute ........]");
        body += "[3. server execute.......]";
        body += delimiter;
        ByteBuf message = Unpooled.copiedBuffer(body.getBytes());
        logger.info("server : " + body);
        ctx.writeAndFlush(message);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        logger.err(cause.getMessage());
        cause.printStackTrace();
        ctx.close();
    }
}
