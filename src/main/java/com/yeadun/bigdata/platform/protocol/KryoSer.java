package com.yeadun.bigdata.platform.protocol;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.yeadun.bigdata.platform.server.PlatformConf;
import com.yeadun.bigdata.platform.util.LogUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by chen on 16-12-5.
 */
public class KryoSer extends MessageToByteEncoder<Protocol>{

    private Kryo kryo = new Kryo();
    private LogUtil logger = new LogUtil(KryoSer.class);
    private PlatformConf conf = new PlatformConf();
    private int SerBufferSize = conf._ser_buffer_size.getIntValue();

    protected void encode(ChannelHandlerContext ctx, Protocol msg, ByteBuf out) throws Exception {
        Output outBuf = new Output();
        kryo.setReferences(false);
        kryo.register(Protocol.class, Protocol.class.hashCode());
        kryo.writeClassAndObject(outBuf, msg);
        outBuf.flush();
        out.writeBytes(outBuf.getBuffer());
    }
}
