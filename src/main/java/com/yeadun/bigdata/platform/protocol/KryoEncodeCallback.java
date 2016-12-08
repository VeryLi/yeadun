package com.yeadun.bigdata.platform.protocol;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.pool.KryoCallback;
import com.yeadun.bigdata.platform.util.LogUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

class KryoEncodeCallback implements KryoCallback<ByteBuf> {

    private LogUtil logger = new LogUtil(KryoEncodeCallback.class);
    private Output out;
    private Protocol msg;

    KryoEncodeCallback(Protocol msg){
        this.msg = msg;
        this.out = new Output(1024);
    }

    public ByteBuf execute(Kryo kryo) {
        logger.info("Kryo Encode Callback get protocol and executing...");

        logger.info("xxxx");
        kryo.writeClassAndObject(out, msg);
        logger.info("yyyy");
        out.flush();
        byte[] msgBody = out.getBuffer();
        int msgLength = msgBody.length;
        ByteBuf bbf = Unpooled.buffer(4 + msgLength);
        bbf.writeInt(msgLength);
        bbf.writeBytes(msgBody);
        out.close();
        return bbf;
    }
}
