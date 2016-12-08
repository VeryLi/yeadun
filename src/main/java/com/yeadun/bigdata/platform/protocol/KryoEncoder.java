package com.yeadun.bigdata.platform.protocol;

import com.esotericsoftware.kryo.pool.KryoPool;
import com.yeadun.bigdata.platform.PlatformContext;
import com.yeadun.bigdata.platform.util.LogUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class KryoEncoder extends MessageToByteEncoder<Protocol>{

    private KryoPool pool;
    private LogUtil logger = new LogUtil(KryoEncoder.class);
    public KryoEncoder(PlatformContext ctx){
        KryoPoolFactory kryoPoolFactory = new KryoPoolFactory(ctx);
        this.pool = kryoPoolFactory.getPool();
        logger.info("kryo Encode get kryo pool.");
    }

    protected void encode(ChannelHandlerContext ctx, Protocol msg, ByteBuf out) throws Exception {
        byte[] msgByte = this.pool.run(new KryoEncodeCallback(msg));
        out.writeBytes(msgByte);
    }
}
