package com.yeadun.bigdata.platform.protocol;

import com.esotericsoftware.kryo.pool.KryoPool;
import com.yeadun.bigdata.platform.PlatformContext;
import com.yeadun.bigdata.platform.util.LogUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class KryoDecoder extends ByteToMessageDecoder {

    private LogUtil logger = new LogUtil(KryoDecoder.class);
    private KryoPool pool;
    public KryoDecoder(PlatformContext ctx){
        KryoPoolFactory kryoPoolFactory = new KryoPoolFactory(ctx);
        this.pool = kryoPoolFactory.getPool();
        logger.info("kryo Decoder get kryo pool.");
    }

    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        logger.info("kryo Decoder start decoding.");
        Protocol prot = this.pool.run(new KryoDecodeCallback(in));
        out.add(prot);
    }
}
