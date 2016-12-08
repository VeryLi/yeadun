package com.yeadun.bigdata.platform.protocol;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.pool.KryoCallback;
import io.netty.buffer.ByteBuf;

class KryoDecodeCallback implements KryoCallback<Protocol> {

    private Input inBuf;
    KryoDecodeCallback(ByteBuf in){
        this.inBuf = new Input(in.array());
    }

    public Protocol execute(Kryo kryo) {
        return kryo.readObject(inBuf, Protocol.class);
    }
}
