package com.yeadun.bigdata.platform.protocol;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.pool.KryoCallback;
import com.yeadun.bigdata.platform.util.LogUtil;
import io.netty.buffer.ByteBuf;

class KryoDecodeCallback implements KryoCallback<Protocol> {

    private Input inBuf;
    private LogUtil logger = new LogUtil(KryoDecodeCallback.class);
    KryoDecodeCallback(ByteBuf in){
        this.inBuf.setBuffer(in.array());
    }

    public Protocol execute(Kryo kryo) {
        logger.info("------========-------");
        return kryo.readObject(this.inBuf, Protocol.class);
    }
}
